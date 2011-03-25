package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;

public class EnumsTableModel extends GenericTableModel<Enum> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5104451800038762465L;

	public EnumsTableModel() {
		super();
		data = new ArrayList<Enum>();

		columnNames = new String[] { Messages.getMessage("table.classestable.column.visibility"), Messages.getMessage("table.enumstable.column.name") };

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
