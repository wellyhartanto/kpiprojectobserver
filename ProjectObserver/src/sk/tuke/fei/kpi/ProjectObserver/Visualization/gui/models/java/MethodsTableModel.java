package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.Locale;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;

public class MethodsTableModel extends GenericTableModel<Method> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 872988681095105283L;

	public MethodsTableModel(Locale locale) {
		super(locale);
		data = new ArrayList<Method>();

		columnNames = new String[] {
				bundle.getString("table.methodstable.column.name"),
				bundle.getString("table.methodstable.column.visibility"),
				bundle.getString("table.methodstable.column.returntype") };

	}

	@Override
	public Object getValueAt(int row, int column) {

		switch (column) {
		case 0:
			return data.get(row).getName();

		case 1:

			return data.get(row).getVisibility();
		case 2:

			return data.get(row).getReturnType();
		default:
			break;
		}

		return "";
	}

}
