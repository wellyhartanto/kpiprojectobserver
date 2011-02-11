package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.Project;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class MainPanelPresenter extends BasicPresenter<MainPanelDisplay> {

	
	
	private Project project;
	
	
	
	public MainPanelPresenter(Project project) {

		this.project = project;
		
		display = new MainPanelView(this.project);
		bind();
	}
}
