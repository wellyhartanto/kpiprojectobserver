package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels;

import sk.tuke.fei.kpi.ProjectObserver.Integration.Project;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.service.ProjectService;

public class ProjectsTableModel extends GenericTableModel<Project> {
	private static final long serialVersionUID = -1237649981122766844L;

	public ProjectsTableModel() {
		super();
		data = ProjectService.loadProjects();
		columnNames = new String[] { MyResourceBundle.getMessage("table.projectstable.column.name"),
				MyResourceBundle.getMessage("table.projectstable.column.sourcecodefile"), MyResourceBundle.getMessage("table.projectstable.column.umlfile"),
				MyResourceBundle.getMessage("table.projectstable.column.creationdate") };
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return data.get(row).getName();
		case 1:
			return data.get(row).getJavaFile().getName();
		case 2:
			return data.get(row).getUmlFile().getName();
		case 3:
			return data.get(row).getCreationDate();
		default:
			break;
		}
		return "";
	}

}
