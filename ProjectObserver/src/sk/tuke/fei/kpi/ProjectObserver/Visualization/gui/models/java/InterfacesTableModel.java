package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;

public class InterfacesTableModel extends AbstractTableModel {
	private final String[] columnNames = { "Interfaces" };

	private List<Interface> interfaces;

	public InterfacesTableModel() {
		super();
		interfaces = new ArrayList<Interface>();
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return interfaces.size();
	}

	@Override
	public Object getValueAt(int row, int column) {

		return interfaces.get(row).toString();
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}
}
