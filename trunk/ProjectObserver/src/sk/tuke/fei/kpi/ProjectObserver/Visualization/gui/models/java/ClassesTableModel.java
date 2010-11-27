package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;

public class ClassesTableModel extends GenericTableModel<Class> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895295854299696658L;

	public ClassesTableModel(Locale locale) {
		super(locale);
		data = new ArrayList<Class>();

		columnNames = new String[] {
				bundle.getString("table.classestable.column.name"),
				bundle.getString("table.classestable.column.visibility") };

	}

}
