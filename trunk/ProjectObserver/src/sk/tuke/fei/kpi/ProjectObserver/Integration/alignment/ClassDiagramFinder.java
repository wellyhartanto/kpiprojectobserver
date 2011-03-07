package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package;

public class ClassDiagramFinder {
	private ClassDiagram classDiagram;
	private AlignStrategy alignStrategy;

	public ClassDiagramFinder(ClassDiagram cd, AlignStrategy alignStrategy) {
		this.classDiagram = cd;
		this.alignStrategy = alignStrategy;
	}

	public Package findPackage(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package javaPackage) {
		Package pack = Finder.findUmlElement(javaPackage, classDiagram.getPackages(),alignStrategy);
		if (pack == null) {
			for (Package p : classDiagram.getPackages()) {
				pack = findPackage(javaPackage, p);
				if (pack != null) {
					break;
				}
			}
		}
		return pack;
	}

	public Package findPackage(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package javaPackage, Package parent) {
		Package pack = Finder.findUmlElement(javaPackage, parent.getPackages(),alignStrategy);
		if (pack == null && !parent.getPackages().isEmpty()) {
			for (Package p : parent.getPackages()) {
				pack = findPackage(javaPackage, p);
				if (pack != null) {
					break;
				}
			}
		}
		return pack;
	}

	public Class findClass(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class c) {
		Class clazz = Finder.findUmlElement(c, classDiagram.getClasses(), alignStrategy);
		if (clazz == null) {
			for (Package p : classDiagram.getPackages()) {
				clazz = findClass(c, p);
				if (clazz != null) {
					break;
				}
			}
		}
		return clazz;
	}

	public Class findClass(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class c, Package parent) {
		Class clazz = Finder.findUmlElement(c, parent.getClasses(), alignStrategy);
		if (clazz == null && !parent.getPackages().isEmpty()) {
			for (Package p : parent.getPackages()) {
				clazz = findClass(c, p);
				if (clazz != null) {
					break;
				}
			}
		}
		return clazz;
	}

	public Interface findInterface(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface i) {
		Interface iface = Finder.findUmlElement(i, classDiagram.getInterfaces(),alignStrategy);
		if (iface == null) {
			for (Package p : classDiagram.getPackages()) {
				iface = findInterface(i, p);
				if (iface != null) {
					break;
				}
			}
		}
		return iface;
	}

	public Interface findInterface(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface i, Package parent) {
		Interface iface = Finder.findUmlElement(i, parent.getInterfaces(),alignStrategy);
		if (iface == null && !parent.getPackages().isEmpty()) {
			for (Package p : parent.getPackages()) {
				iface = findInterface(i, p);
				if (iface != null) {
					break;
				}
			}
		}
		return iface;
	}
}
