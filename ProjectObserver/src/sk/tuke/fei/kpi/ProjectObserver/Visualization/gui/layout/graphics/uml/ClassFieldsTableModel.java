package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element.Visibility;

public class ClassFieldsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6208684010103057564L;

	protected String[] columnNames = { "visibility", "type", "name" };

	protected List<Field> data;

	public ClassFieldsTableModel() {
		super();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		if (data != null) {
			return data.size();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return data.get(row).getVisibility();
		case 1:
			return data.get(row).getType();
		case 2:
			return data.get(row).getName();
		default:
			break;
		}
		return "";
	}

	public void setData(List<Field> data) {
		if (data != null) {
			this.data = data;
		} else {
			this.data = new ArrayList<Field>();

			Field m = new Field();
			m.setName("testdieldname");
			m.setVisibility(Visibility.PUBLIC);
			m.setType("String");
			this.data.add(m);

		}

	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column].toString();
	}

	public List<Field> getData() {
		return data;
	}
}
