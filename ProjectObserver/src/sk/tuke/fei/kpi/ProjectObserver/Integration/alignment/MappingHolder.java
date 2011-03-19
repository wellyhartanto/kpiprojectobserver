package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.difference.Difference;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;

/**
 * Holds mapping from {@link Application} elements to {@link ClassDiagram} elements and vice versa.
 * It contains differences between classes in application and uml model (missing methods, extra methods).
 */
public class MappingHolder implements Serializable {
	private static final long serialVersionUID = 1600573250608950944L;

	private Uml2JavaMapping uml2JavaMapping;

	private Java2UmlMapping java2UmlMapping;
	
	private Map<String,Difference> difference;

	/**
	 * Constructor
	 */
	public MappingHolder() {
		uml2JavaMapping = new Uml2JavaMapping();
		java2UmlMapping = new Java2UmlMapping();
		difference = new HashMap<String, Difference>();
	}

	/**
	 * Gets Java2Uml Mapping.
	 * @return java2UmlMapping object
	 */
	public Java2UmlMapping getJava2UmlMapping() {
		return java2UmlMapping;
	}

	/**
	 * Gets Uml2Java Mapping.
	 * @return Uml2JavaMapping object
	 */
	public Uml2JavaMapping getUml2JavaMapping() {
		return uml2JavaMapping;
	}
	
	/**
	 * Adds package pair to mapping
	 * @param p1 {@link ClassDiagram} (UML) package
	 * @param p2 {@link Application} (JAVA) package
	 */
	public void addPackagePair(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package p1,
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package p2){
		getJava2UmlMapping().setPackagePair(p2.getFullyQualifiedName(), p1);
		getUml2JavaMapping().setPackagePair(p1.getFullyQualifiedName(), p2);
	}
	
	/**
	 * Adds class pair to mapping
	 * @param c1 {@link ClassDiagram} (UML) class
	 * @param c2 {@link Application} (JAVA) class
	 */
	public void addClassPair(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class c1,
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class c2){
		getJava2UmlMapping().setClassPair(c2.getFullyQualifiedName(), c1);
		getUml2JavaMapping().setClassPair(c1.getFullyQualifiedName(), c2);
		difference.put(c2.getFullyQualifiedName(), new Difference(c2, c1));
	}
	
	/**
	 * Adds interface pair to mapping
	 * @param i1 {@link ClassDiagram} (UML) class 
	 * @param i2 {@link Application} (JAVA) class
	 */
	public void addInterfacePair(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface i1,
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface i2){
		getJava2UmlMapping().setInterfacePair(i2.getFullyQualifiedName(), i1);
		getUml2JavaMapping().setInterfacePair(i1.getFullyQualifiedName(), i2);
		difference.put(i2.getFullyQualifiedName(), new Difference(i2, i1));
	}
	
	/**
	 * Gets difference object for class or interface with specified name
	 * @param name fully qualified name of interface or class.
	 * @return difference object
	 */
	public Difference getDifference(String name){
		return difference.get(name);		
	}
}
