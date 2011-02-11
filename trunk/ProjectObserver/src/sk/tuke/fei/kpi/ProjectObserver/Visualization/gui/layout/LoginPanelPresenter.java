package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

				Project pr = new Project();
				pr.setName("persistedproject");
				pr.setCreationDate(new Date());
				pr.setDescription("desc");
				
				ProjectService.saveProject(pr);
				
				
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
