package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;

public class EnumsTableModel extends AbstractTableModel {
	private final String[] columnNames = { "Enums" };

	private List<Enum> enums;

	public EnumsTableModel() {
		super();
		enums = new ArrayList<Enum>();
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return enums.size();
	}

	@Override
	public Object getValueAt(int row, int column) {

		return enums.get(row).toString();
	}

	public void setEnums(List<Enum> enums) {
		this.enums = enums;
	}
}
