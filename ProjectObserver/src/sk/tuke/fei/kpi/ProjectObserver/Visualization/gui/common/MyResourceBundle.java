package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.util.Locale;
import java.util.ResourceBundle;

public class MyResourceBundle {

	public static String getMessage(String key) {

		return ResourceBundle
				.getBundle(
						"sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.resources.resources",
						Locale.getDefault()).getString(key);
	}

}
