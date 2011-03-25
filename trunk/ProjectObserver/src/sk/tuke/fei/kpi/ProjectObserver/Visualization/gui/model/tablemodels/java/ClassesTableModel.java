package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;

public class ClassesTableModel extends GenericTableModel<Class> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895295854299696658L;

	public ClassesTableModel() {
		super();
		data = new ArrayList<Class>();

		columnNames = new String[] { Messages.getMessage("table.classestable.column.visibility"), Messages.getMessage("table.classestable.column.name") };

	}

	@Override
	public Object getValueAt(int row, int column) {

		switch (column) {
		case 0:
			return data.get(row).getVisibility();

		case 1:
			return data.get(row).getName();

		default:
			break;
		}

		return "";
	}

}
