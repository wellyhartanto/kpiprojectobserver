package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;

public class EnumsTableModel extends AbstractTableModel {
	private  String[] columnNames ;
private ResourceBundle bundle;
	
	private List<Enum> enums;

	public EnumsTableModel(Locale locale) {
		super();
		enums = new ArrayList<Enum>();
		
		bundle = MyResourceBundle.getResourceBundle(locale);

		columnNames = new String[] {
				bundle.getString("table.enumstable.column.name") };

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
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column].toString();
	}
}
