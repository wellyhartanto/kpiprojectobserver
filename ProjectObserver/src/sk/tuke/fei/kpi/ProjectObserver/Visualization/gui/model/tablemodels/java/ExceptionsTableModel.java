package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;

public class ExceptionsTableModel extends GenericTableModel<String> {
	private static final long serialVersionUID = -1178155466701373851L;

	public ExceptionsTableModel() {
		super();
		data = new ArrayList<String>();

		columnNames = new String[] { Messages.getMessage("table.exceptionstable.column.name") };
	}

}
