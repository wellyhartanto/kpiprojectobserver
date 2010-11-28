package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.Locale;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;

public class ParamsTableModel extends GenericTableModel<Param> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6075399042830099414L;

	public ParamsTableModel(Locale locale) {
		super(locale);
		data = new ArrayList<Param>();

		columnNames = new String[] { bundle
				.getString("table.paramstable.column.name") };
	}

}
