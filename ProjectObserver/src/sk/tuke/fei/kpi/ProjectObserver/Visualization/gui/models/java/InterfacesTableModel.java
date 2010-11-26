package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;

public class InterfacesTableModel extends AbstractTableModel {
	private String[] columnNames;

	private List<Interface> interfaces;
	private ResourceBundle bundle;

	public InterfacesTableModel(Locale locale) {
		super();
		interfaces = new ArrayList<Interface>();
		bundle = MyResourceBundle.getResourceBundle(locale);

		columnNames = new String[] {
				bundle.getString("table.interfacestable.column.name") };

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

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column].toString();
	}

}
