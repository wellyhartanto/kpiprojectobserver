package sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.akAgent.integration.search.Finder;

/**
 * Class diagram representation.
 * Class diagram from UML Language abstraction.
 */
public class ClassDiagram implements Serializable {
	private static final long serialVersionUID = -1965268218122677260L;
	private List<Class> classes;
	private List<Enum> enums;
	private List<Package> packages;
	private String name;
	private List<Interface> interfaces;
	/**
	 * All packages of model.
	 */
	private List<Package> allPackages;
	/**
	 * All classes and interfaces of model.
	 */
	private List<TypeElement> allClasses;

	/**
	 * Constructor.
	 */
	public ClassDiagram() {
		classes = new ArrayList<Class>();
		enums = new ArrayList<Enum>();
		packages = new ArrayList<Package>();
		interfaces = new ArrayList<Interface>();
		allPackages = new ArrayList<Package>();
		allClasses = new ArrayList<TypeElement>();		
	}	

	/**
	 * Gets list of classes in application.
	 * @return classes in application.
	 */
	public List<Class> getClasses() {
		return classes;
	}

	/**
	 * Sets list of classes in application.
	 * @param classes list to set.
	 */
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	/**
	 * Gets enums in application.
	 * @return list of enum.
	 */
	public List<Enum> getEnums() {
		return enums;
	}

	/**
	 * Sets enums in classdiagram.
	 * @param enums list to set
	 */
	public void setEnums(List<Enum> enums) {
		this.enums = enums;
	}

	/**
	 * Gets packages in this package.
	 * @return list of packages
	 */
	public List<Package> getPackages() {
		return packages;
	}

	/**
	 * Sets packages in this package.
	 * @param packages list to set
	 */
	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	/**
	 * Gets model's name.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets model's name
	 * @param name name of class diagram model
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets interfaces in this package.
	 * @param interfaces list to set
	 */
	public List<Interface> getInterfaces() {
		return interfaces;
	}

	/**
	 * Sets interfaces in this package.
	 * @param interfaces list to set
	 */
	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}
	
	/**
	 * Gets all packages in classdiagram.
	 * This is raw list without tree hierarchy.
	 * @return list of packages
	 */
	public List<Package> getAllPackages() {
		return allPackages;
	}
	
	/**
	 * Sets all packages in classdiagram.
	 * This is raw list without tree hierarchy. It is used for search.
	 * @param allPackages list of packages
	 */
	public void setAllPackages(List<Package> allPackages) {
		this.allPackages = allPackages;
	}
	
	/**
	 * Gets all classes and interfaces in classdiagram.
	 * This is raw list without package hierarchy.
	 * @return list of classes and interfaces
	 */
	public List<TypeElement> getAllClasses() {
		return allClasses;
	}
	
	/**
	 * Sets all classes and interfaces in classdiagram.
	 * This is raw list without package hierarchy. It is used for search.
	 * @param allClasses list of classes and interfaces
	 */
	public void setAllClasses(List<TypeElement> allClasses) {
		this.allClasses = allClasses;
	}
	
	@Override
	public String toString() {
		return name + packages.toString();
	}
	
	/**
	 * Search classes and interfaces in classdiagram which name starts with passed parameter.
	 * Search is case insensitive.
	 * @param name name to search
	 * @return list of classes that matches passed name.
	 */
	public List<TypeElement> searchClasses(String name){
		return Finder.searchUmlElements(name, allClasses);
	}
	
	/**
	 * Search packages in classdiagram which name starts with passed parameter.
	 * Search is case insensitive.
	 * @param name name to search
	 * @return list of packages that matches passed name.
	 */
	public List<Package> searchPackages(String name){
		return Finder.searchUmlElements(name, allPackages);
	}
}
