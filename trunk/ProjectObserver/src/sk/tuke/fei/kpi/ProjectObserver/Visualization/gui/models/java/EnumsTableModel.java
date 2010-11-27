package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;

public class EnumsTableModel extends GenericTableModel<Enum> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5104451800038762465L;

	public EnumsTableModel(Locale locale) {
		super(locale);
		data = new ArrayList<Enum>();
		
		
		columnNames = new String[] {
				bundle.getString("table.enumstable.column.name") };

	}

	
}
