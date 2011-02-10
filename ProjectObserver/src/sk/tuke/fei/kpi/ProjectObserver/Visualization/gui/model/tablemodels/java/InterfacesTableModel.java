package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;

public class InterfacesTableModel extends GenericTableModel<Interface> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1629050875612660093L;

	public InterfacesTableModel() {
		super();
		data = new ArrayList<Interface>();

		columnNames = new String[] { MyResourceBundle.getMessage("table.interfacestable.column.name"),

		MyResourceBundle.getMessage("table.interfacestable.column.visibility") };

	}

	@Override
	public Object getValueAt(int row, int column) {

		switch (column) {
		case 0:
			return data.get(row).getName();

		case 1:

			return data.get(row).getVisibility();

		default:
			break;
		}

		return "";
	}

}
