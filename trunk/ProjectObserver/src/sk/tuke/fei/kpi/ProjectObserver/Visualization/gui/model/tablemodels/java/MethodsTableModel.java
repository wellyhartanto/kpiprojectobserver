package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util.StringUtil;

public class MethodsTableModel extends GenericTableModel<Method> {

	private static final long serialVersionUID = 872988681095105283L;

	private List<Method> extraMethods;

	public MethodsTableModel() {
		super();
		data = new ArrayList<Method>();
		extraMethods = new ArrayList<Method>();

		columnNames = new String[] { Messages.getMessage("table.methodstable.column.visibility"), Messages.getMessage("table.methodstable.column.returntype"),
				Messages.getMessage("table.methodstable.column.name") };

	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return data.get(row).getReturnType();
		case 1:
			return data.get(row).getVisibility();
		case 2:
			return StringUtil.convertMethodNameToString(data.get(row));
		default:
			break;
		}
		return "";
	}

	public void setExtraMethods(List<Method> extraMethods) {
		this.extraMethods = extraMethods;
		// this.data.addAll(extraMethods);
	}

	public List<Method> getExtraMethods() {
		return extraMethods;
	}

}
