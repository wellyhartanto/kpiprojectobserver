package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util;

import java.util.List;

import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Constructor;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Param;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.TypeElement;

public class StringUtil {

	private static String prepareMethodParameters(List<Param> params) {

		String result = "";

		for (int i = 0; i < params.size(); i++) {
			result += params.get(i).toString();
			if (i < params.size() - 1) {
				result += ", ";
			}
		}
		return result;
	}

	private static String prepareUMLMethodParameters(List<sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Param> params) {
		String result = "";

		for (int i = 0; i < params.size(); i++) {
			result += params.get(i).toString();
			if (i < params.size() - 1) {
				result += ", ";
			}
		}
		return result;
	}
	
	public static String convertMethodToString(Method method) {
		String fullnametext = method.getVisibility() + " " + method.getReturnType() + " " + method.getName() + "("
				+ StringUtil.prepareMethodParameters(method.getParams()) + ")";
		return fullnametext;
	}
	
	public static String convertUMLMethodToString(sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method method) {
		String fullnametext = method.getVisibility() + " " + method.getReturnType() + " " + method.getName() + "("
				+ StringUtil.prepareUMLMethodParameters(method.getParams()) + ")";
		return fullnametext;
	}

	public static String convertMethodNameToString(Method method) {
		String fullnametext = method.getName() + "(" + StringUtil.prepareMethodParameters(method.getParams()) + ")";
		return fullnametext;
	}

	public static String convertConstructorToString(Constructor constructor) {
		String fullnametext = constructor.getVisibility() + " " + constructor.getName() + "("
				+ StringUtil.prepareMethodParameters(constructor.getParams()) + ")";
		return fullnametext;
	}

	public static String convertClassToString(sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class clas) {
		String fullnametext = clas.getVisibility() + " class " + clas.getName();
		if (clas.getSuperClass() != null) {
			fullnametext += " extends " + clas.getSuperClass().getName();
		}
		if (clas.getImplemented() != null && !clas.getImplemented().isEmpty()) {
			fullnametext += " implements " + prepareArrayToString(clas.getImplemented());
		}
		return fullnametext;
	}
	
	public static String convertUMLClassToString(sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Class clas) {
		String fullnametext = clas.getVisibility() + " class " + clas.getName();
		if (clas.getSuperClass() != null) {
			fullnametext += " extends " + clas.getSuperClass().getName();
		}
		return fullnametext;
	}

	public static String convertInterfaceToString(sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface interfac) {
		String fullnametext = interfac.getVisibility() + " interface " + interfac.getName();
		if (interfac.getSuperClass() != null) {
			fullnametext += " extends " + interfac.getSuperClass().getName();
		}
		if (interfac.getImplemented() != null && !interfac.getImplemented().isEmpty()) {
			fullnametext += " implements " + prepareArrayToString(interfac.getImplemented());
		}
		return fullnametext;
	}
	
	
	public static String convertFieldToString(Field field) {
		String fullnametext = field.getVisibility() + " " + field.getType() + " " + field.getName();
		return fullnametext;
	}
	
	public static String convertUMLInterfaceToString(sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Interface interfac) {
		String fullnametext = interfac.getVisibility() + " interface " + interfac.getName();
		return fullnametext;
	}
	
	
	public static String convertUMLFieldToString(sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Field field) {
		String fullnametext = field.getVisibility() + " " + field.getType() + " " + field.getName();
		return fullnametext;
	}
	
	

	public static String prepareArrayToString(List<TypeElement> elements) {
		String result = "";
		for (int i = 0; i < elements.size(); i++) {
			result += elements.get(i).toString();
			if (i < elements.size() - 1) {
				result += ", ";
			}
		}
		return result;
	}
}
