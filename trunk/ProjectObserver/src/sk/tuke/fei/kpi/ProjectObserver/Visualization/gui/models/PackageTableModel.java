package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PackageTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895295854299696658L;

	private final String[] columnNames = { "Classes" };

	private List<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class> classes;

	public PackageTableModel() {
		super();
		classes = new ArrayList<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class>();
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return classes.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
