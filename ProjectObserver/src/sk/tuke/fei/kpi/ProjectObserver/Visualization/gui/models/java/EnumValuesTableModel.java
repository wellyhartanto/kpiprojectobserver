package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;

public class EnumValuesTableModel extends GenericTableModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3452931757055861929L;

	public EnumValuesTableModel() {
		super();
		data = new ArrayList<String>();

		columnNames = new String[] { MyResourceBundle.getMessage("table.enumvaluestable.column.name") };

	}

}
