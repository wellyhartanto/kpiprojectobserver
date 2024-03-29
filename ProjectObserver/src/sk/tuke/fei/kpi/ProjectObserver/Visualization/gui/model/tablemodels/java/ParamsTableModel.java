package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Param;

public class ParamsTableModel extends GenericTableModel<Param> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6075399042830099414L;

	public ParamsTableModel() {
		super();
		data = new ArrayList<Param>();
		columnNames = new String[] { Messages.getMessage("table.paramstable.column.name") };
	}

}
