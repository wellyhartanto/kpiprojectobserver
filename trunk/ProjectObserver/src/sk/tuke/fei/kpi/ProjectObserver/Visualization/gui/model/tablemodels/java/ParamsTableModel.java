package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;

public class ParamsTableModel extends GenericTableModel<Param> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6075399042830099414L;

	public ParamsTableModel() {
		super();
		data = new ArrayList<Param>();

		columnNames = new String[] { MyResourceBundle.getMessage("table.paramstable.column.name") };
	}

}
