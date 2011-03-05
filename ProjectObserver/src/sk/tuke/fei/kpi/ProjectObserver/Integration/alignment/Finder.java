package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import java.util.List;

public class Finder {

	public static <T extends sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Element> T findJavaElement(String name, List<T> list) {
		for (T t : list) {
			if (t.getFullyQualifiedName().equals(name)) {
				return t;
			}
		}
		return null;
	}

	public static <T extends sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element> T findUmlElement(String name, List<T> list) {
		for (T t : list) {
			if (t.getFullyQualifiedName().equals(name)) {
				return t;
			}
		}
		return null;
	}
}
