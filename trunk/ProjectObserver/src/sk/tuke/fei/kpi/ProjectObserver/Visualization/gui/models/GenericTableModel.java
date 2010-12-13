package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;

public abstract class GenericTableModel<T> extends AbstractTableModel {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2436272229115341013L;

    protected String[] columnNames;

    protected List<T> data;
    protected ResourceBundle bundle;

    public GenericTableModel() {
	super();

	bundle = MyResourceBundle.getResourceBundle(Locale.getDefault());

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