package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util;

import java.util.List;

import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Constructor;
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

	public static String convertMethodToString(Method method) {

		String fullnametext = method.getVisibility() + " " + method.getReturnType() + " " + method.getName() + "("
				+ StringUtil.prepareMethodParameters(method.getParams()) + ")";

		return fullnametext;
	}

	public static String convertMethodNameToString(Method method) {

		String fullnametext = method.getName() + "(" + StringUtil.prepareMethodParameters(method.getParams()) + ")";

		return fullnametext;
	}

	public static String convertConstructorToString(Constructor constructor) {

		String fullnametext = constructor.getVisibility() + " " + constructor.getName() + "(" + StringUtil.prepareMethodParameters(constructor.getParams())
				+ ")";

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
