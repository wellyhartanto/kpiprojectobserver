package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.java;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.BehavioralElement;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Constructor;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Element;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.TypeElement;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Element.Modifiers;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Element.Visibility;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.Parser;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.ParserException;
import sk.tuke.fei.kpi.ProjectObserver.utils.Disposable;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;

public class JavaParser implements Parser<Application>, Disposable {
	private static final String PREFIX = "http://www.jscc.sk/ontology/OOMOntology.owl#";
	private static Logger logger = Logger.getLogger(JavaParser.class);
	private Map<String, sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package> packages;
	private Map<String, TypeElement> classes;
	private OntModel ontology;
	private Application application;

	public JavaParser() {
		packages = new HashMap<String, sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package>(20);
		classes = new HashMap<String, TypeElement>(150);
	}

	@Override
	public Application parse(String pathname) throws ParserException {
		return parse(new File(pathname));
	}

	@Override
	public Application parse(File file) throws ParserException {
		initModel(file.getPath());
		application = new Application();
		utils = new OwlUtils(ontology);
		loadModel();
		// new ClassHierarchy().showHierarchy(System.out, ontology);
		return application;
	}

	private void initModel(String path) {
		ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
		ontology.getDocumentManager().addAltEntry("http://www.jscc.sk/ontology/OOMOntology", "file:" + path);
		ontology.getDocumentManager().addAltEntry("http://www.jscc.sk/ontology/OOMOntology.owl", "file:" + path);
		ontology.read("http://www.jscc.sk/ontology/OOMOntology");
	}

	private void loadModel() {
		loadPackages();
		loadInterfaces();
		loadClasses();

		for (String key : classes.keySet()) {
			TypeElement p = classes.get(key);
			for (String value : p.getImplementedNames()) {
				p.getImplemented().add(classes.get(value));
				// logger.info(classes.get(value));
			}
			if (p.getSuperClassName() != null) {
				p.setSuperClass(classes.get(p.getSuperClassName()));
				// logger.info(p.getSuperClass());
			}
			// logger.info(p.getFullName() + " -> " + p.getParent() + " " + p.getVisibility());
		}

		loadMethods();
		loadAttributes();
		// loadParameters();
		loadConstructors();
	}

	private void loadConstructors() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Constructor")); i.hasNext();) {
			Individual individual = i.next();
			Constructor constructor = new Constructor();
			StmtIterator iterator = individual.listProperties();
			setElement(constructor, individual);
			RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "isConstructorOf"));
			String className = OwlUtils.getValue(node.toString(), '#');
			TypeElement parent = classes.get(className);
			constructor.setParent(parent);
			parent.getConstructors().add(constructor);
			logger.info(constructor.getName() + constructor.getParent().getName());
			setParams(constructor);
			// while (iterator.hasNext()) {
			// logger.info(iterator.next());
			// }
		}
	}

	private void loadParameters() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Parameter")); i.hasNext();) {
			Individual individual = i.next();
			Param param = new Param();
			StmtIterator iterator = individual.listProperties();
			setElement(param, individual);
			while (iterator.hasNext()) {
				logger.info(iterator.next());
			}
		}
	}

	private void loadPackages() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Package")); i.hasNext();) {
			Individual individual = i.next();
			Package p = new Package();
			p.setName(individual.getPropertyValue(new PropertyImpl(PREFIX + "hasName")).toString());
			p.setFullName(individual.getPropertyValue(new PropertyImpl(PREFIX + "hasFullName")).toString());
			RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "isMemberOfPackage"));
			if (node != null) {
				p.setParentName(node.toString());
			}
			packages.put(p.getFullName(), p);
		}
		for (String key : packages.keySet()) {
			Package p = packages.get(key);

			if (p.getParentName() != null) {
				Package parent = packages.get(p.getParentName().split("#")[1]);
				parent.getPackages().add(p);
				p.setParent(parent);
				logger.info(p.getFullName());
			} else {
				application.getPackages().add(p);
			}
		}
		//		
		// for (String key:packages.keySet()){
		// Package p = packages.get(key);
		// logger.info(p.getFullName()+" -> "+p.getParent());
		// }
	}

	private void loadInterfaces() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Interface")); i.hasNext();) {
			Individual individual = i.next();
			Interface iface = new Interface();
			setElement(iface, individual);
			Package parent = getPackage(iface.getFullName(), '.');
			if (parent != null) {
				parent.getInterfaces().add(iface);
				iface.setParent(parent);
			} else if (!iface.getName().equals(iface.getFullName())) {
				iface.setExternal(true);
			}
			classes.put(iface.getFullName(), iface);
			if (iface.getParent() == null) {
				application.getInterfaces().add(iface);
			}
		}
	}

	private void loadClasses() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Class")); i.hasNext();) {
			Individual individual = i.next();
			Class clazz = new Class();
			setElement(clazz, individual);
			Package parent = getPackage(clazz.getFullName(), '.');
			if (parent != null) {
				parent.getClasses().add(clazz);
				clazz.setParent(parent);
			} else if (!clazz.getName().equals(clazz.getFullName())) {
				clazz.setExternal(true);
			}
			// TODO isExtending

			setSuperClasses(clazz, individual);
			classes.put(clazz.getFullName(), clazz);
		}

		// for (String key : classes.keySet()) {
		// TypeElement p = classes.get(key);
		// logger.info(p.getVisibility() + " " + p.getFullName() + " -> " + p.getParent());
		// }
	}

	private OwlUtils utils;

	// PREFIX jscc: <http://www.jscc.sk/ontology/OOMOntology.owl#> SELECT * WHERE {?param a jscc:Parameter; jscc:hasName ?name;jscc:hasType ?type; jscc:isParameterOf
	// <'de.softproject.elos.model.web.ServiceMobile$setDfZeit:Date'>.}
	private void loadMethods() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Method")); i.hasNext();) {
			Individual individual = i.next();
			Method method = new Method();
			setElement(method, individual);
			// if(method.getFullName()!=null){
			// logger.info(method.getFullName());
			// }
			RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "isDefiningBehaviorOf"));
			if (node != null) {
				TypeElement element = classes.get(OwlUtils.getValue(node.toString(), '#'));
				method.setParent(element);
				element.getMethods().add(method);
			}
			setParams(method);
		}
	}

	private void setParams(BehavioralElement element) {
//		try {
			if (element.getFullName()!=null && element.getFullName().charAt(element.getFullName().length() - 1) != ':') {
				List<Param> list = utils.runParamQuery(PREFIX + element.getFullName());
//				for (Param param : list) {
//					logger.info(param.getName() + param.getType());
//				}
			}
//		} catch (NullPointerException ex) {
//			ex.printStackTrace();
//		}
	}

	private void loadAttributes() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Attribute")); i.hasNext();) {
			Individual individual = i.next();
			Field field = new Field();
			setElement(field, individual);
			RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "isFieldOf"));
			if (node != null) {
				TypeElement element = classes.get(OwlUtils.getValue(node.toString(), '#'));
				field.setParent(element);
				element.getFields().add(field);
			}
			field.setType(OwlUtils.getValue(individual.getPropertyValue(new PropertyImpl(PREFIX + "hasType")).toString(), '#'));
			logger.info(field.getName() + " " + field.getParent());
		}
	}

	private Package getPackage(String value, char separator) {
		try {
			return packages.get(value.substring(0, value.lastIndexOf(separator)));
		} catch (Exception ex) {
			// ex.printStackTrace();
			return null;
		}
	}

	private static void setModifiers(Element element, Individual individual) {
		NodeIterator iterator = individual.listPropertyValues(new PropertyImpl(PREFIX + "hasModifier"));
		while (iterator.hasNext()) {
			String mod = OwlUtils.getValue(iterator.next().toString(), '$');
			Modifiers modifier = Modifiers.fromString(mod);
			if (modifier != Modifiers.NONE) {
				element.getModifiers().add(modifier);
			} else {
				Visibility visibility = Visibility.fromString(mod);
				element.setVisibility(visibility);
			}
		}
	}

	private void setSuperClasses(TypeElement element, Individual individual) {
		NodeIterator iterator = individual.listPropertyValues(new PropertyImpl(PREFIX + "implements"));
		while (iterator.hasNext()) {
			String iface = OwlUtils.getValue(iterator.next().toString(), '#');
			logger.info(iface);
			element.getImplemented().add(classes.get(iface));
			element.getImplementedNames().add(iface);
		}
		RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "isExtending"));
		if (node != null) {
			element.setSuperClassName(OwlUtils.getValue(node.toString(), '#'));
			logger.info(element.getSuperClassName());
		}
	}

	private static void setElement(Element element, Individual individual) {
		RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "hasName"));
		if (node != null) {
			element.setName(node.toString().replace("]", ""));

		}
		node = individual.getPropertyValue(new PropertyImpl(PREFIX + "hasFullName"));
		if (node != null) {
			element.setFullName(node.toString().replace("]", ""));
		}
		setModifiers(element, individual);

	}

	@Override
	public void dispose() {
		if (ontology != null) {
			ontology.close();
		}
		ontology = null;
		packages = null;
		classes = null;
	}
}
