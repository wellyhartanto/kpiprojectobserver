package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;

import javax.jnlp.FileContents;
import javax.jnlp.FileSaveService;
import javax.jnlp.ServiceManager;
import javax.jnlp.ServiceManagerStub;
import javax.jnlp.UnavailableServiceException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.MainFrame;
import sk.tuke.fei.kpi.ProjectObserver.Integration.Project;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.service.ProjectService;

public class LoginPanelPresenter extends BasicPresenter<LoginPanelDisplay> {

	private File sourceCodeFile;
	private File umlFile;

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
					MainFrame.getMainFrame().setPanel(
							new MainPanelPresenter(selectedProject)
									.getDisplay().asComponent());
				}
			}
		});

		display.setCreateAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Project project = new Project(sourceCodeFile, umlFile);

				display.setNameAndDescription(project);


				ProjectService.saveProject(project);

				MainFrame.getMainFrame().setPanel(
						new MainPanelPresenter(project).getDisplay()
								.asComponent());

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

		display.setImportAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.showOpenDialog(display.asComponent());

			}
		});

		display.setExportAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setSelectedFile(new File(display
						.getSelectedProject().getName()));

				fileChooser.showSaveDialog(display.asComponent());

				File file = fileChooser.getSelectedFile();
				ProjectService
						.exportProject(display.getSelectedProject(), file);
			}

		});

		display.setLoadSourceAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.showOpenDialog(display.asComponent());
				sourceCodeFile = fileChooser.getSelectedFile();
				display.setSourceCodeFileLabel(sourceCodeFile.getName());
			}
		});

		display.setLoadUmlAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.showOpenDialog(display.asComponent());
				umlFile = fileChooser.getSelectedFile();

				display.setUmlFileLabel(umlFile.getName());
			}
		});

	}
}