package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;

public class PackagesTableModel extends GenericTableModel<Package> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1882685369928607188L;

	public PackagesTableModel() {
		super();
		data = new ArrayList<Package>();

		columnNames = new String[] { MyResourceBundle.getMessage("table.packagestable.column.name") };
	}

}
