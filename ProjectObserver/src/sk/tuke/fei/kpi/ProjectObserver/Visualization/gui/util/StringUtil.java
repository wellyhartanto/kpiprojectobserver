package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Constructor;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;

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
				+ " )";

		return fullnametext;
	}

}
