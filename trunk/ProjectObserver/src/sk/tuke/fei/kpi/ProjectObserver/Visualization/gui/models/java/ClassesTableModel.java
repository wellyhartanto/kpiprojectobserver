package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;

public class ClassesTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895295854299696658L;

	private ResourceBundle bundle;
	private String[] columnNames;
	private List<Class> classes;

	public ClassesTableModel(Locale locale) {
		super();
		classes = new ArrayList<Class>();
		bundle = MyResourceBundle.getResourceBundle(locale);

		columnNames = new String[] {
				bundle.getString("table.classestable.column.name"),
				bundle.getString("table.classestable.column.visibility") };

	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return classes.size();
	}

	@Override
	public Object getValueAt(int row, int column) {

		return classes.get(row).toString();
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column].toString();
	}

}
