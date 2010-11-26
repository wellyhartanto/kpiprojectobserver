package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;

public class MethodsTableModel extends AbstractTableModel {
	private  String[] columnNames ;

	private List<Method> methods;
	
	private ResourceBundle bundle;

	public MethodsTableModel(Locale locale) {
		super();
		methods = new ArrayList<Method>();
		
		bundle = MyResourceBundle.getResourceBundle(locale);

		columnNames = new String[] {"chyba bundle"
				};

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
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column].toString();
	}
	
}
