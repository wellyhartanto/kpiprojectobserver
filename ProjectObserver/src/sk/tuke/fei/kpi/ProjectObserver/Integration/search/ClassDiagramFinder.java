package sk.tuke.fei.kpi.ProjectObserver.Integration.search;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package;
/**
 * Performs search task used in alignment process in class diagram.
 */
public class ClassDiagramFinder {
	private ClassDiagram classDiagram;
	private AlignStrategy alignStrategy;
	/**
	 * Constructor
	 * @param cd Class diagram
	 * @param alignStrategy strategy which is used to find elements
	 */
	public ClassDiagramFinder(ClassDiagram cd, AlignStrategy alignStrategy) {
		this.classDiagram = cd;
		this.alignStrategy = alignStrategy;
	}

	/**
	 * Finds package in class diagram which corresponds to package in model of application
	 * @param javaPackage java package
	 * @return package in class diagram
	 */
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

	/**
	 * Finds package in class diagram which corresponds to package in model of application and is member of parent package.
	 * @param javaPackage java package
	 * @param parent parent package
	 * @return package
	 */
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
	/**
	 * Finds class in class diagram which corresponds to class in model of application.
	 * @param c class in application model
	 * @return class in class diagram
	 */
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

	/**
	 * Finds class in class diagram which corresponds to class in model of application and is member of parent package.
	 * @param c class in application model
	 * @param parent parent package
	 * @return class in class diagram
	 */
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

	/**
	 * Finds interface in class diagram which corresponds to interface in model of application.
	 * @param i interface in application model
	 * @return interface from class diagram
	 */
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

	/**
	 * Finds interface in class diagram which corresponds to interface in model of application and is member of parent package.
	 * @param i interface in application model
	 * @param parent parent package
	 * @return interface from class diagram
	 */
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
