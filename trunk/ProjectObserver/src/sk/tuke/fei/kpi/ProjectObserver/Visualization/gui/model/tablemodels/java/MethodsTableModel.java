package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;

public class MethodsTableModel extends GenericTableModel<Method> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 872988681095105283L;

	public MethodsTableModel() {
		super();
		data = new ArrayList<Method>();

		columnNames = new String[] { Messages.getMessage("table.methodstable.column.name"),
				Messages.getMessage("table.methodstable.column.visibility"),
				Messages.getMessage("table.methodstable.column.returntype") };

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
