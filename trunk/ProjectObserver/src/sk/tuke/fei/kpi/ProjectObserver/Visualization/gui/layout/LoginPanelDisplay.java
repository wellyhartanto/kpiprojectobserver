package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionListener;

import sk.tuke.fei.kpi.ProjectObserver.Integration.Project;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.DisplayComponent;

public interface LoginPanelDisplay extends DisplayComponent {

	void setOpenAction(ActionListener actionListener);

	void setCreateAction(ActionListener actionListener);

	void setImportAction(ActionListener actionListener);

	void setExportAction(ActionListener actionListener);

	void setDeleteAction(ActionListener actionListener);

	void setLoadSourceAction(ActionListener actionListener);

	void setLoadUmlAction(ActionListener actionListener);
	
	boolean isNewProjectCorrect();

	Project getSelectedProject();

	void removeProjectFromList(Project project);

	void setNameAndDescription(Project project);

	void setSourceCodeFileLabel(String filename);

	void setUmlFileLabel(String filename);
	
	void refreshTableModel();
}
