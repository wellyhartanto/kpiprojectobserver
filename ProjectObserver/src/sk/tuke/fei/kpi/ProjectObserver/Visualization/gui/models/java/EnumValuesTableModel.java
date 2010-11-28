package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.Locale;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;

public class EnumValuesTableModel extends GenericTableModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3452931757055861929L;

	public EnumValuesTableModel(Locale locale) {
		super(locale);
		data = new ArrayList<String>();
		
		
		columnNames = new String[] {bundle.getString("table.enumvaluestable.column.name")};

	}

}
