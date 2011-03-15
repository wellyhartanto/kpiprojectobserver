package sk.tuke.fei.kpi.ProjectObserver.Integration.search;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;

class JavaFinder {
	private Application application;

	public JavaFinder( Application app) {
		this.application = app;		
	}
	
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
