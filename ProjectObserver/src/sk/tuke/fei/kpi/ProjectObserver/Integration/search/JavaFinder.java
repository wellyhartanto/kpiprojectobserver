package sk.tuke.fei.kpi.ProjectObserver.Integration.search;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
/**
 * Performs search tasks in model of application;.
 */
public class JavaFinder {
	private Application application;

	/**
	 * Constructor
	 * @param app Model of java application
	 */
	public JavaFinder( Application app) {
		this.application = app;		
	}
	
	/**
	 * Finds package by name
	 * @param name name of package
	 * @return package
	 */
	public Package findPackage(String name) {
		Package pack = Finder.findJavaElement(name, application.getPackages());
		if (pack == null) {
			for (Package p : application.getPackages()) {
				pack = findPackage(name, p);
				if (pack != null) {
					break;
				}
			}
		}
		return pack;
	}
	/**
	 * Finds package by name, which is one of subpackages of specified parent
	 * @param name name of package
	 * @param parent parent package
	 * @return package
	 */
	public Package findPackage(String name, Package parent) {
		Package pack = Finder.findJavaElement(name, parent.getPackages());
		if (pack == null && !parent.getPackages().isEmpty()) {
			for (Package p : parent.getPackages()) {
				pack = findPackage(name, p);
				if (pack != null) {
					break;
				}
			}
		}
		return pack;
	}

	/**
	 * Finds class in application by its name
	 * @param name name of class
	 * @return class
	 */
	public Class findClass(String name) {
		Class clazz = Finder.findJavaElement(name, application.getClasses());
		if (clazz == null) {
			for (Package p : application.getPackages()) {
				clazz = findClass(name, p);
				if (clazz != null) {
					break;
				}
			}			
		}
		return clazz;
	}

	/**
	 * Finds class in specified package by its name.
	 * @param name name of class
	 * @param parent parent package
	 * @return class
	 */
	public Class findClass(String name, Package parent) {
		Class clazz = Finder.findJavaElement(name, parent.getClasses());
		if (clazz == null && !parent.getPackages().isEmpty()) {
			for (Package p : parent.getPackages()) {
				clazz = findClass(name, p);
				if (clazz != null) {
					break;
				}
			}
		}
		return clazz;
	}
	/**
	 * Finds interface in application by name.
	 * @param name name of interface
	 * @return interface
	 */
	public Interface findInterface(String name) {
		Interface iface = Finder.findJavaElement(name, application.getInterfaces());
		if (iface == null) {
			for (Package p : application.getPackages()) {
				iface = findInterface(name, p);
				if (iface != null) {
					break;
				}
			}			
		}
		return iface;
	}

	/**
	 * Finds interface in package by its name
	 * @param name name of interface 
	 * @param parent parent package
	 * @return interface
	 */
	public Interface findInterface(String name, Package parent) {
		Interface iface = Finder.findJavaElement(name, parent.getInterfaces());
		if (iface == null && !parent.getPackages().isEmpty()) {
			for (Package p : parent.getPackages()) {
				iface = findInterface(name, p);
				if (iface != null) {
					break;
				}
			}
		}
		return iface;
	}
}
