package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;

public class InterfacesTableModel extends GenericTableModel<Interface> {

	private static final long serialVersionUID = -1629050875612660093L;

	public InterfacesTableModel() {
		super();
		data = new ArrayList<Interface>();
		columnNames = new String[] { Messages.getMessage("table.interfacestable.column.visibility"), Messages.getMessage("table.interfacestable.column.name")
		};

	}

	@Override
	public Object getValueAt(int row, int column) {

		switch (column) {
		case 0:
			return data.get(row).getVisibility();
		case 1:
			return data.get(row).getName();
		default:
			break;
		}
		return "";
	}

}
