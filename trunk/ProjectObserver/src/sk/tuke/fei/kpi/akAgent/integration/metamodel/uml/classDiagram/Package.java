package sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * UML package.
 */
public class Package  extends Element implements Serializable {
	private static final long serialVersionUID = -4748629040047693896L;
	private List<Class> classes;
	private List<Enum> enums;
	private List<Package> packages;
	private List<Interface> interfaces;
	private String fullName;
	
	/**
	 * Constructor.
	 */
	public Package() {
		classes = new ArrayList<Class>();
		enums = new ArrayList<Enum>();
		packages = new ArrayList<Package>();
		interfaces = new ArrayList<Interface>();
	}

	/**
	 * Gets list of classes in package.
	 * @return classes in package.
	 */
	public List<Class> getClasses() {
		return classes;
	}

	/**
	 * Sets list of classes in package.
	 * @param classes list to set.
	 */
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	/**
	 * Gets enums in package.
	 * @return list of enum.
	 */
	public List<Enum> getEnums() {
		return enums;
	}

	/**
	 * Sets enums in package.
	 * @param enums list to set
	 */
	public void setEnums(List<Enum> enums) {
		this.enums = enums;
	}

	/**
	 * Gets subpackages in this package .
	 * @return list of packages
	 */
	public List<Package> getPackages() {
		return packages;
	}

	/**
	 * Sets subpackages in this package.
	 * @param packages list to set
	 */
	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	/**
	 * Sets interfaces in this package.
	 * @param interfaces
	 */
	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	/**
	 * Gets interfaces in package.
	 * @return list of interfaces
	 */
	public List<Interface> getInterfaces() {
		return interfaces;
	}
	
	/**
	 * Gets full name of package, including names of parent packages, separated by '.'.
	 * @return full name
	 */
	public String getFullName() {
		return fullName;
	}
	
	/**
	 * Sets full name of package.
	 * @param fullName full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Override
	public String toString() {
		return getFullName();
	}
	
	@Override
	public String getFullyQualifiedName() {
		return fullName;
	}
}
