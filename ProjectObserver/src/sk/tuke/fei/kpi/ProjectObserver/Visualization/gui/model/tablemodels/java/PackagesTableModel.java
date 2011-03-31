package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;

public class PackagesTableModel extends GenericTableModel<Package> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1882685369928607188L;

	public PackagesTableModel() {
		super();
		data = new ArrayList<Package>();

		columnNames = new String[] { Messages.getMessage("table.packagestable.column.name") };
	}

}
