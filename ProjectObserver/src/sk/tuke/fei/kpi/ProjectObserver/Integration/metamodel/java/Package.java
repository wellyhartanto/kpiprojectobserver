package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Alignable;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;

/**
 * Java package.
 */
public class Package extends Element implements Serializable, Alignable {
	private static final long serialVersionUID = 1980055108799234195L;
	private List<Class> classes = new ArrayList<Class>();
	private List<Enum> enums = new ArrayList<Enum>();
	private List<Package> packages = new ArrayList<Package>();
	private String fullName;
	private List<Interface> interfaces = new ArrayList<Interface>();
	private transient String parentName;

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
	 * Gets name of parent package.
	 * @return parent name, null if package doesn't have parent.
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * Sets name of parent package.
	 * @param parentName parent name
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	/**
	 * Sets full name of package.
	 * @param fullName full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets full name of package, including names of parent packages, separated by '.'.
	 * @return full name
	 */
	public String getFullName() {
		return fullName;
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * @see #getFullName()
	 */
	@Override
	public String getFullyQualifiedName() {
		return fullName;
	}

	@Override
	public boolean matches(Object object, AlignStrategy alignStrategy) {
		if (object == null) {
			return false;
		}
		if (object instanceof sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package) {
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package pack = (sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package) object;
			switch (alignStrategy) {
			case EXACT:
				return getFullyQualifiedName().equalsIgnoreCase(pack.getFullyQualifiedName());
			case IGNORE_PACKAGE:
				return getName().equalsIgnoreCase(pack.getName());
			case MANUAL:
				return false;
			case APPROXIMATION:
				return getFullyQualifiedName().equalsIgnoreCase(pack.getFullyQualifiedName());
			default:
				throw new IllegalStateException("Align strategy " + alignStrategy + "is not supported ");
			}
		}
		return false;
	}
}
