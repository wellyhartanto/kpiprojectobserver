package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.Project;

public class ProjectsTableModel extends GenericTableModel<Project>{

	
	private static final long serialVersionUID = -1237649981122766844L;

	public ProjectsTableModel() {
		super();
		data = new ArrayList<Project>();

		columnNames = new String[] { "Name","Date","Description" };

	}

	@Override
	public Object getValueAt(int row, int column) {

		switch (column) {
		case 0:
			return data.get(row).getName();

		case 1:

			return data.get(row).getCreationDate();

		case 2:

			return data.get(row).getDescription();

		default:
			break;
		}

		return "";
	}
	
}
