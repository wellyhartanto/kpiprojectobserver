package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class GenericTableModel<T> extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2436272229115341013L;

	protected String[] columnNames;

	protected List<T> data;

	public GenericTableModel() {
		super();

	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int column) {

		return data.get(row).toString();
	}

	public void setData(List<T> data) {
		if (data != null) {
			this.data = data;
		} else {
			this.data = new ArrayList<T>();
		}

	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column].toString();
	}

	public List<T> getData() {
		return data;
	}

}
