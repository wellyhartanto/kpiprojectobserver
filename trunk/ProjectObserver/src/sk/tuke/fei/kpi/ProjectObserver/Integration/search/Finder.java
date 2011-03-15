package sk.tuke.fei.kpi.ProjectObserver.Integration.search;

import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;

public class Finder {

	public static <T extends sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Element> T findJavaElement(String name, List<T> list) {
		for (T t : list) {
			if (t.getFullyQualifiedName().equals(name)) {
				return t;
			}
		}
		return null;
	}
	
	public static <T extends sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Element> List<T> searchJavaElements(String name, List<T> list) {
		List<T> result = new ArrayList<T>();
		String searchString = name.toUpperCase();
		for (T element : list) {
			if(element.getName().toUpperCase().startsWith(searchString)){
				result.add(element);
			}
		}
		return result;
	}

	public static <T extends sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element> T findUmlElement(String name, List<T> list) {
		for (T t : list) {
			if (t.getFullyQualifiedName().equals(name)) {
				return t;
			}
		}
		return null;
	}

	public static <T extends sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element> List<T> searchUmlElements(String name, List<T> list) {
		List<T> result = new ArrayList<T>();
		String searchString = name.toUpperCase();
		for (T element : list) {
			if(element.getName().toUpperCase().startsWith(searchString)){
				result.add(element);
			}
		}
		return result;
	}

	public static <T extends sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element> T findUmlElement(
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Element object, List<T> list, AlignStrategy alignStrategy) {
		for (T t : list) {
			if (object.matches(t, alignStrategy)) {
				return t;
			}
		}
		return null;
	}
}
