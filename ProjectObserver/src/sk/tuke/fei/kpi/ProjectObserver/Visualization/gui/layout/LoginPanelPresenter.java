package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import sk.tuke.fei.kpi.ProjectObserver.Integration.Model;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.MainFrame;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.Project;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.service.ProjectService;

public class LoginPanelPresenter extends BasicPresenter<LoginPanelDisplay> {

	
	
	
	public LoginPanelPresenter() {

		display = new LoginPanelView();
		bind();
	}

	@Override
	protected void onBind() {
		super.onBind();

		display.setOpenAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Project selectedProject = display.getSelectedProject();
				if (selectedProject != null) {
					MainFrame.getMainFrame().setPanel(new MainPanelPresenter(selectedProject).getDisplay().asComponent());
				}
			}
		});
		
		
		display.setCreateAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Project project = new Project();

				display.setNameAndDescription(project);
				
				project.setCreationDate(new Date());
				project.setModel(new Model());
				
				ProjectService.saveProject(project);
				
				MainFrame.getMainFrame().setPanel(new MainPanelPresenter(project).getDisplay().asComponent());
				
				
			}
		});
		
		
		display.setDeleteAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Project selectedProject = display.getSelectedProject();
				
				display.removeProjectFromList(selectedProject);
				
				if (selectedProject != null) {

					ProjectService.deleteProject(selectedProject);
				
				}
				
			}
		});
		

	}
}
