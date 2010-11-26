package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.util.Locale;
import java.util.ResourceBundle;

public class MyResourceBundle {


	
	
	public static ResourceBundle getResourceBundle(Locale locale){
		
		return ResourceBundle
		.getBundle(
				"sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.resources.resources",locale);
	}
	
}
