package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;

public class MethodsTableModel extends AbstractTableModel {
	private final String[] columnNames = { "Methods" };

	private List<Method> methods;

	public MethodsTableModel() {
		super();
		methods = new ArrayList<Method>();
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return methods.size();
	}

	@Override
	public Object getValueAt(int row, int column) {

		return methods.get(row).toString();
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
}
