package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element.Visibility;
public class ClassMethodsTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 2436272229115341013L;

	protected String[] columnNames ={ "visibility","type","name"};

	protected List<Method> data;

	public ClassMethodsTableModel() {
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
			return data.get(row).getReturnType();
		case 2:
			return data.get(row).getName();
		default:
			break;
		}
		return "";
	}

	public void setData(List<Method> data) {
		if (data != null) {
			this.data = data;
		} else {
			this.data = new ArrayList<Method>();
			
		Method m=	new Method();
		m.setName("testmethodname");
		m.setVisibility(Visibility.PUBLIC);
		m.setReturnType("String");
		this.data.add(m);
		
		}

	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column].toString();
	}

	public List<Method> getData() {
		return data;
	}
}
