package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;

public class PackageTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895295854299696658L;

	private final String[] columnNames = { "Classes" };

	private List<Class> classes;

	public PackageTableModel() {
		super();
		classes = new ArrayList<Class>();
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

}
