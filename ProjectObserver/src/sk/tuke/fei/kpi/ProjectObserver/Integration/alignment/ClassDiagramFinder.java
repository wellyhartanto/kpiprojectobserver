package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package;

public class ClassDiagramFinder {
	private ClassDiagram classDiagram;

	public ClassDiagramFinder(ClassDiagram cd) {
		this.classDiagram = cd;
	}

	public Package findPackage(String name) {
		Package pack = Finder.findUmlElement(name, classDiagram.getPackages());
		if (pack == null) {
			for (Package p : classDiagram.getPackages()) {
				pack = findPackage(name, p);
				if (pack != null) {
					break;
				}
			}
		}
		return pack;
	}

	public Package findPackage(String name, Package parent) {
		Package pack = Finder.findUmlElement(name, parent.getPackages());
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
		Class clazz = Finder.findUmlElement(name, classDiagram.getClasses());
		if (clazz == null) {
			for (Package p : classDiagram.getPackages()) {
				clazz = findClass(name, p);
				if (clazz != null) {
					break;
				}
			}			
		}
		return clazz;
	}

	public Class findClass(String name, Package parent) {
		Class clazz = Finder.findUmlElement(name, parent.getClasses());
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
		Interface iface = Finder.findUmlElement(name, classDiagram.getInterfaces());
		if (iface == null) {
			for (Package p : classDiagram.getPackages()) {
				iface = findInterface(name, p);
				if (iface != null) {
					break;
				}
			}			
		}
		return iface;
	}

	public Interface findInterface(String name, Package parent) {
		Interface iface = Finder.findUmlElement(name, parent.getInterfaces());
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
