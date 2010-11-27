package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.Locale;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;

public class MethodsTableModel extends GenericTableModel<Method> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 872988681095105283L;

	public MethodsTableModel(Locale locale) {
		super(locale);
		data = new ArrayList<Method>();
		
		

		columnNames = new String[] {bundle.getString("table.methodstable.column.name")
				};

	}

	
}
