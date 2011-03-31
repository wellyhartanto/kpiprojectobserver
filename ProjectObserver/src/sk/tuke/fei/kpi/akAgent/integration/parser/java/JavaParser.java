package sk.tuke.fei.kpi.akAgent.integration.parser.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Application;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.BehavioralElement;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Constructor;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Element;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Param;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.TypeElement;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Element.Modifiers;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Element.Visibility;
import sk.tuke.fei.kpi.akAgent.integration.parser.Parser;
import sk.tuke.fei.kpi.akAgent.integration.parser.ParserException;
import sk.tuke.fei.kpi.akAgent.utils.Disposable;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;

public class JavaParser implements Parser<Application>, Disposable {
	private static final String PREFIX = "http://www.jscc.sk/ontology/OOMOntology.owl#";
	private static Logger logger = Logger.getLogger(JavaParser.class);
	private Map<String, sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package> packages;
	private Map<String, TypeElement> classes;
	private OntModel ontology;
	private Application application;

	/**
	 * Constructor
	 */
	public JavaParser() {
		packages = new HashMap<String, sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package>(20);
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
		application.setAllClasses(new ArrayList<TypeElement>(classes.values()));
		application.setAllPackages(new ArrayList<Package>(packages.values()));
		return application;
	}

	/**
	 * Initialize ontology model from input file specified by its pathname.
	 * @param pathname pathname
	 */
	private void initModel(String pathname) {
		ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
		ontology.getDocumentManager().addAltEntry("http://www.jscc.sk/ontology/OOMOntology", "file:" + pathname);
		ontology.getDocumentManager().addAltEntry("http://www.jscc.sk/ontology/OOMOntology.owl", "file:" + pathname);
		ontology.read("http://www.jscc.sk/ontology/OOMOntology");
	}

	/**
	 * Loads application model.
	 */
	private void loadModel() {
		loadPackages();		
		loadInterfaces();
		loadClasses();
		loadMethods();
		loadAttributes();
		loadConstructors();
		for (String key : classes.keySet()) {
			TypeElement p = classes.get(key);
			for (String value : p.getImplementedNames()) {
				p.getImplemented().add(classes.get(value));
			}
			if (p.getSuperClassName() != null) {
				p.setSuperClass(classes.get(p.getSuperClassName()));
			}
			logger.info(p.getFullyQualifiedName());
			Collections.sort(p.getClasses());
			Collections.sort(p.getImplemented());
			Collections.sort(p.getMethods());
			Collections.sort(p.getFields());
		}
		for (String key : packages.keySet()) {
			Package p = packages.get(key);
			Collections.sort(p.getClasses());
			Collections.sort(p.getPackages());
			Collections.sort(p.getInterfaces());			
		}
	}

	/**
	 * Loads constructors from ontology.
	 */
	private void loadConstructors() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Constructor")); i.hasNext();) {
			Individual individual = i.next();
			Constructor constructor = new Constructor();
			setElement(constructor, individual);
			RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "isConstructorOf"));
			String className = OwlUtils.getValue(node.toString(), '#');
			TypeElement parent = classes.get(className);
			if(parent!=null){
			constructor.setParent(parent);
			parent.getConstructors().add(constructor);
			setParams(constructor);
			} else{
				System.out.println(className);
			}
		}
	}
	
	/**
	 * Loads packages from ontology.
	 */
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
			} else {
				application.getPackages().add(p);
			}
		}
	}

	/**
	 * Load iterfaces from ontology.
	 */
	private void loadInterfaces() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Interface")); i.hasNext();) {
			Individual individual = i.next();
			Interface iface = new Interface();
			setElement(iface, individual);
			Package parent = getPackage(iface.getFullName(), '.');
			if (parent != null) {
				parent.getInterfaces().add(iface);
				iface.setParent(parent);
				classes.put(iface.getFullName(), iface);
				if (iface.getParent() == null) {
					application.getInterfaces().add(iface);
				}
			} else if (!iface.getName().equals(iface.getFullName())) {
				iface.setExternal(true);
			}
		}
	}

	/**
	 * Loads classes from ontology.
	 */
	private void loadClasses() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Class")); i.hasNext();) {
			Individual individual = i.next();
			Class clazz = new Class();
			setElement(clazz, individual);
			Package parent = getPackage(clazz.getFullName(), '.');
			if (parent != null) {
				parent.getClasses().add(clazz);
				clazz.setParent(parent);
				classes.put(clazz.getFullName(), clazz);
				setSuperClasses(clazz, individual);
			} else if (!clazz.getName().equals(clazz.getFullName())) {
				clazz.setExternal(true);
			} else {
			}	
		}
	}

	private OwlUtils utils;

	/**
	 * Load methods from ontology.
	 */
	private void loadMethods() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Method")); i.hasNext();) {
			Individual individual = i.next();
			Method method = new Method();
			setElement(method, individual);
			RDFNode returnType = individual.getPropertyValue(new PropertyImpl(PREFIX + "hasReturnType"));
			if (returnType != null)
				method.setReturnType(filterType(OwlUtils.getValue(returnType.toString(), '#')));
			RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "isDefiningBehaviorOf"));
			if (node != null) {
				TypeElement element = classes.get(OwlUtils.getValue(node.toString(), '#'));
				if (element != null) {
					method.setParent(element);
					element.getMethods().add(method);
				}
			}
			setParams(method);
			Collections.sort(method.getParams());
		}
	}

	/**
	 * Sets params of behavioral element(Method or constructor). 
	 * @param element method or constructor
	 */
	private void setParams(BehavioralElement element) {
		if (element.getFullName() != null && element.getFullName().charAt(element.getFullName().length() - 1) != ':') {
			element.setParams(utils.runParamQuery(PREFIX + element.getFullName()));
		} else {
			element.setParams(new ArrayList<Param>());
		}
	
		
	}

	/**
	 * Loads field from ontology
	 */
	private void loadAttributes() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Attribute")); i.hasNext();) {
			Individual individual = i.next();
			Field field = new Field();
			setElement(field, individual);
			RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "isFieldOf"));
			if (node != null) {
				TypeElement element = classes.get(OwlUtils.getValue(node.toString(), '#'));
				if(element !=null){
					field.setParent(element);
					element.getFields().add(field);
				} else {
					System.out.println(node.toString());
				}
			}
			field.setType(filterType(OwlUtils.getValue(individual.getPropertyValue(new PropertyImpl(PREFIX + "hasType")).toString(), '#')));
		}
	}

	/**
	 * Gets package from element URI.
	 * @param value URI of element
	 * @param separator separator
	 * @return Package of object with specified URI
	 */
	private Package getPackage(String value, char separator) {
		try {
			return packages.get(value.substring(0, value.lastIndexOf(separator)));
		} catch (Exception ex) {
			// ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Sets modifiers of element.
	 * @param element element to set
	 * @param individual individual which is ontology representation of element
	 */
	private static void setModifiers(Element element, Individual individual) {
		NodeIterator iterator = individual.listPropertyValues(new PropertyImpl(PREFIX + "hasModifier"));
		while (iterator.hasNext()) {
			String mod = OwlUtils.getValue(iterator.next().toString(), '$');
			Modifiers modifier = Modifiers.fromString(mod);
			if (modifier != Modifiers.UNKNOWN) {
				element.getModifiers().add(modifier);
			} else {
				Visibility visibility = Visibility.fromString(mod);
				element.setVisibility(visibility);
			}
		}
	}

	/**
	 * Sets superclass of element
	 * @param element element to set
	 * @param individual individual which is ontology representation of element
	 */
	private void setSuperClasses(TypeElement element, Individual individual) {
		NodeIterator iterator = individual.listPropertyValues(new PropertyImpl(PREFIX + "implements"));
		while (iterator.hasNext()) {
			String iface = OwlUtils.getValue(iterator.next().toString(), '#');
			//logger.info(iface);
			TypeElement te = classes.get(iface);
			if(te!=null){
			element.getImplemented().add(te);
			element.getImplementedNames().add(iface);
			}
		}
		RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "isExtending"));
		if (node != null) {
			element.setSuperClassName(OwlUtils.getValue(node.toString(), '#'));
			//logger.info(element.getSuperClassName());
		}
	}

	/**
	 * Sets element name, fullname and modifiers.
	 * @param element element to set
	 * @param individual individual which is ontology representation of element
	 */
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
	
	/**
	 * Extract java Type from value.
	 * For example value primitiveType$int id transformed to int.
	 * @param value value
	 * @return type
	 */
	public static String filterType(String value){
		if(value !=null && value.contains("$")){
			return value.substring(value.indexOf('$')+1,value.length());
		} else {
			return value;
		}
	}
}
