package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;

public class EnumValuesTableModel extends GenericTableModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3452931757055861929L;

	public EnumValuesTableModel() {
		super();
		data = new ArrayList<String>();

		columnNames = new String[] { Messages.getMessage("table.enumvaluestable.column.name") };

	}

}
