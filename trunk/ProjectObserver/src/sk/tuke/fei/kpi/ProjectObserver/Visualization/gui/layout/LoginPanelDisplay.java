package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionListener;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.Project;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.DisplayComponent;

public interface LoginPanelDisplay extends DisplayComponent {

	void setOpenAction(ActionListener actionListener);

	void setCreateAction(ActionListener actionListener);

	void setDeleteAction(ActionListener actionListener);

	Project getSelectedProject();
	
	void removeProjectFromList(Project project);

}
