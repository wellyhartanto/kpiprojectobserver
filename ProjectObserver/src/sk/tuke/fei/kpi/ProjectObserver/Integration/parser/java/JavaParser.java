package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.java;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.TypeElement;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Element.Visibility;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.Parser;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.ParserException;
import sk.tuke.fei.kpi.ProjectObserver.utils.Disposable;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;

public class JavaParser implements Parser<Application>, Disposable {
	private static final String PREFIX = "http://www.jscc.sk/ontology/OOMOntology.owl#";
	private static Logger logger = Logger.getLogger(JavaParser.class);
	private Map<String, sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package> packages;
	private Map<String, TypeElement> classes;
	private Map<String, TypeElement> interfaces;
	private OntModel ontology;
	private Application application;

	// public static void main(String[] args) {
	// Date start = new Date();
	// OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
	//
	// // we have a local copy of the wine ontology
	// m.getDocumentManager().addAltEntry("http://www.w3.org/2001/sw/WebOnt/guide-src/wine", "file:wine.owl");
	// m.getDocumentManager().addAltEntry("http://www.w3.org/2001/sw/WebOnt/guide-src/wine.owl", "file:wine.owl");
	// m.getDocumentManager().addAltEntry("http://www.w3.org/2001/sw/WebOnt/guide-src/food", "file:food.owl");
	// m.getDocumentManager().addAltEntry("http://www.w3.org/2001/sw/WebOnt/guide-src/food.owl", "file:food.owl");
	// m.getDocumentManager().addAltEntry("http://www.jscc.sk/ontology/OOMOntology", "file:full.owl");
	// m.getDocumentManager().addAltEntry("http://www.jscc.sk/ontology/OOMOntology.owl", "file:full.owl");
	//
	// m.read("http://www.jscc.sk/ontology/OOMOntology");
	//
	// new ClassHierarchy().showHierarchy(System.out, m);
	// Date start2 = new Date();
	// for (Iterator<Individual> i = m.listIndividuals(new ResourceImpl(PREFIX + "Package")); i.hasNext();) {
	// Individual individual = i.next();
	// System.out.println(individual.getPropertyValue(new PropertyImpl(PREFIX + "hasFullName")));
	// System.out.println(individual.getPropertyValue(new PropertyImpl(PREFIX + "hasName")));
	// }
	// for (Iterator<Individual> i = m.listIndividuals(new ResourceImpl(PREFIX + "Interface")); i.hasNext();) {
	// Individual individual = i.next();
	// StmtIterator iterator = individual.listProperties();
	// while (iterator.hasNext()) {
	// System.out.println(iterator.next());
	// }
	// System.out.println();
	// }
	//
	// for (Iterator<Individual> i = m.listIndividuals(new ResourceImpl(PREFIX + "Class")); i.hasNext();) {
	// Individual individual = i.next();
	// StmtIterator iterator = individual.listProperties();
	// while (iterator.hasNext()) {
	// System.out.println(iterator.next());
	// }
	// System.out.println();
	// }
	//
	// for (Iterator<Individual> i = m.listIndividuals(new ResourceImpl(PREFIX + "Method")); i.hasNext();) {
	// Individual individual = i.next();
	// StmtIterator iterator = individual.listProperties();
	// while (iterator.hasNext()) {
	// System.out.println(iterator.next());
	// }
	// System.out.println();
	// }
	//
	// // for (Iterator<Individual> i = m.listIndividuals(new ResourceImpl(PREFIX + "Attribute")); i.hasNext();) {
	// // Individual individual = i.next();
	// // StmtIterator iterator = individual.listProperties();
	// // while(iterator.hasNext()){
	// // System.out.println(iterator.next());
	// // }
	// // System.out.println();
	// // }
	//
	// System.out.println(new Date().getTime() - start.getTime());
	// System.out.println(new Date().getTime() - start2.getTime());
	// m.close();
	// }

	public JavaParser() {
		packages = new HashMap<String, sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package>(20);
		classes = new HashMap<String, TypeElement>(100);
		interfaces = new HashMap<String, TypeElement>(20);
	}

	@Override
	public Application parse(String pathname) throws ParserException {
		return parse(new File(pathname));
	}

	@Override
	public Application parse(File file) throws ParserException {
		// logger.info(XMLUtils.readXmlDocument(file));
		initModel(file.getPath());
		application = new Application();
		loadModel();
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
		//
		// for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Class")); i.hasNext();) {
		// Individual individual = i.next();
		// StmtIterator iterator = individual.listProperties();
		// while (iterator.hasNext()) {
		// logger.info(iterator.next());
		// }
		// }
		//
		// for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Method")); i.hasNext();) {
		// Individual individual = i.next();
		// StmtIterator iterator = individual.listProperties();
		// while (iterator.hasNext()) {
		// logger.info(iterator.next());
		// }
		// }
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
			// StmtIterator iterator = individual.listProperties();
			// while (iterator.hasNext()) {
			// logger.info(iterator.next());
			// }
			Interface iface = new Interface();
			iface.setName(individual.getPropertyValue(new PropertyImpl(PREFIX + "hasName")).toString());

			iface.setFullName(individual.getPropertyValue(new PropertyImpl(PREFIX + "hasFullName")).toString());
			Package parent = getPackage(iface.getFullName(), '.');
			if (parent != null) {
				parent.getInterfaces().add(iface);
				iface.setParent(parent);
			} else if (!iface.getName().equals(iface.getFullName())) {
				iface.setExternal(true);
			}
			RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "hasModifier"));
			if (node != null) {
				iface.setVisibility(Visibility.fromString(node.toString()));
			}
			// if (!iface.isExternal()) {
			interfaces.put(iface.getFullName(), iface);
			if (iface.getParent() == null) {
				application.getInterfaces().add(iface);
			}
			// }
		}

		for (String key : interfaces.keySet()) {
			TypeElement p = interfaces.get(key);
			logger.info(p.getFullName() + " -> " + p.getParent());
		}
	}
	
	private void loadClasses() {
		for (Iterator<Individual> i = ontology.listIndividuals(new ResourceImpl(PREFIX + "Class")); i.hasNext();) {
			Individual individual = i.next();
			 StmtIterator iterator = individual.listProperties();
			 while (iterator.hasNext()) {
			 logger.info(iterator.next());
			 }
//			Interface iface = new Interface();
//			iface.setName(individual.getPropertyValue(new PropertyImpl(PREFIX + "hasName")).toString());
//
//			iface.setFullName(individual.getPropertyValue(new PropertyImpl(PREFIX + "hasFullName")).toString());
//			Package parent = getPackage(iface.getFullName(), '.');
//			if (parent != null) {
//				parent.getInterfaces().add(iface);
//				iface.setParent(parent);
//			} else if (!iface.getName().equals(iface.getFullName())) {
//				iface.setExternal(true);
//			}
//			RDFNode node = individual.getPropertyValue(new PropertyImpl(PREFIX + "hasModifier"));
//			if (node != null) {
//				iface.setVisibility(Visibility.fromString(node.toString()));
//			}
//			// if (!iface.isExternal()) {
//			interfaces.put(iface.getFullName(), iface);
//			if (iface.getParent() == null) {
//				application.getInterfaces().add(iface);
//			}
			// }
		}

		for (String key : interfaces.keySet()) {
			TypeElement p = interfaces.get(key);
			logger.info(p.getFullName() + " -> " + p.getParent());
		}
	}

	private Package getPackage(String value, char separator) {
		String packageName = value.substring(0, value.lastIndexOf(separator));
		return packages.get(packageName);
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
