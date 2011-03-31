package sk.tuke.fei.kpi.akAgent.integration.metamodel.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.akAgent.integration.search.Finder;

/**
 * Abstraction of application implements in JAVA programming language.
 */
public class Application implements Serializable {
	private static final long serialVersionUID = 1754398216708453319L;
	private List<Class> classes = new ArrayList<Class>();
	private List<Enum> enums = new ArrayList<Enum>();
	private List<Package> packages = new ArrayList<Package>();
	private String name;
	private List<Interface> interfaces = new ArrayList<Interface>();
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
	public Application() {
		classes = new ArrayList<Class>();
		enums = new ArrayList<Enum>();
		packages = new ArrayList<Package>();
		interfaces = new ArrayList<Interface>();
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
	 * Sets enums in application.
	 * @param enums list to set
	 */
	public void setEnums(List<Enum> enums) {
		this.enums = enums;
	}

	/**
	 * Gets packages in this application.
	 * @return list of packages
	 */
	public List<Package> getPackages() {
		return packages;
	}
	
	/**
	 * Sets packages in this application.
	 * @param packages list to set
	 */
	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
	
	/**
	 * Sets application's name.
	 * @param name name of application
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets application's name.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets interfaces in this package.
	 * @param interfaces list to set
	 */
	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	/**
	 * Gets interfaces in application.
	 * @return list of interfaces
	 */
	public List<Interface> getInterfaces() {
		return interfaces;
	}

	/**
	 * Gets all classes and interfaces in application.
	 * This is raw list without package hierarchy.
	 * @return list of classes and interfaces
	 */
	public List<TypeElement> getAllClasses() {
		return allClasses;
	}

	/**
	 * Sets all classes and interfaces in application.
	 * This is raw list without package hierarchy. It is used for search.
	 * @param allClasses list of classes and interfaces
	 */
	public void setAllClasses(List<TypeElement> allClasses) {
		this.allClasses = allClasses;
	}

	/**
	 * Gets all packages in application.
	 * This is raw list without tree hierarchy.
	 * @return list of packages
	 */
	public List<Package> getAllPackages() {
		return allPackages;
	}

	/**
	 * Sets all packages in application.
	 * This is raw list without tree hierarchy. It is used for search.
	 * @param allPackages list of packages
	 */
	public void setAllPackages(List<Package> allPackages) {
		this.allPackages = allPackages;
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Search classes and interfaces in application which name starts with passed parameter.
	 * Search is case insensitive.
	 * @param name name to search
	 * @return list of classes that matches passed name.
	 */
	public List<TypeElement> searchClasses(String name) {
		return Finder.searchJavaElements(name, allClasses);
	}

	/**
	 * Search packages in application which name starts with passed parameter.
	 * Search is case insensitive.
	 * @param name name to search
	 * @return list of packages that matches passed name.
	 */
	public List<Package> searchPackages(String name) {
		return Finder.searchJavaElements(name, allPackages);
	}
}
