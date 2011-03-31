package sk.tuke.fei.kpi.akAgent.integration.search;

import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.akAgent.integration.alignment.Alignable;
import sk.tuke.fei.kpi.akAgent.integration.alignment.Aligner.AlignStrategy;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Application;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.ClassDiagram;

/**
 * Contains methods for finding elements in collection according to its name or using some other strategies.
 */
public class Finder {

	/**
	 * Finds {@link Application} element in list of elements by its name.
	 * @param <T> Type of elements in collections and return type 
	 * @param name name of element to find
	 * @param list list of elements
	 * @return element
	 */
	public static <T extends sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Element> T findJavaElement(String name, List<T> list) {
		for (T t : list) {
			if (t.getFullyQualifiedName().equals(name)) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * Finds {@link Application} elements in in list which name starts with parameter name.
	 * @param name name of element to find
	 * @param list list of elements
	 * @return list of elements
	 */
	public static <T extends sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Element> List<T> searchJavaElements(String name, List<T> list) {
		List<T> result = new ArrayList<T>();
		String searchString = name.toUpperCase();
		for (T element : list) {
			if(element.getName().toUpperCase().startsWith(searchString)){
				result.add(element);
			}
		}
		return result;
	}

	/**
	 * Finds {@link ClassDiagram} element in list of elements by its name.
	 * @param <T> Type of elements in collections and return type 
	 * @param name name of element to find
	 * @param list list of elements
	 * @return element
	 */
	public static <T extends sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Element> T findUmlElement(String name, List<T> list) {
		for (T t : list) {
			if (t.getFullyQualifiedName().equals(name)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Finds {@link ClassDiagram} elements in in list which name starts with parameter name.
	 * @param name name of element to find
	 * @param list list of elements
	 * @return list of elements
	 */
	public static <T extends sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Element> List<T> searchUmlElements(String name, List<T> list) {
		List<T> result = new ArrayList<T>();
		String searchString = name.toUpperCase();
		for (T element : list) {
			if(element.getName().toUpperCase().startsWith(searchString)){
				result.add(element);
			}
		}
		return result;
	}

	/**
	 * Finds {@link ClassDiagram} element in list of elements by its name. Search is based on align strategy and  {@link Alignable#matches} method.
	 * @param <T> Type of elements in collections and return type 
	 * @param name name of element to find
	 * @param list list of elements
	 * @return element
	 */
	public static <T extends sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Element> T findUmlElement(
			sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Element object, List<T> list, AlignStrategy alignStrategy) {
		for (T t : list) {
			if (object.matches(t, alignStrategy)) {
				return t;
			}
		}
		return null;
	}
}
