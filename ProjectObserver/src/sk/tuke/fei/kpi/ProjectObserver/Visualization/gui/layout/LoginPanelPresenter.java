package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileFilter;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.ProgressDialog;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.MainFrame;
import sk.tuke.fei.kpi.ProjectObserver.Integration.AlignmentException;
import sk.tuke.fei.kpi.ProjectObserver.Integration.Project;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.ParserException;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
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
					MainFrame.getMainFrame().setPanel(new MainPanelPresenter(selectedProject).getDisplay().asComponent());
				}
			}
		});

		display.setCreateAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (display.isNewProjectCorrect(umlFile, sourceCodeFile)) {

					String label = MyResourceBundle.getMessage("message.info.creatingproject");
					final ProgressDialog progressDialog = new ProgressDialog(label, label, findParentFrame());
					final SwingWorker<String, String> sw = new SwingWorker<String, String>() {
						Project project = new Project(umlFile, sourceCodeFile);

						@Override
						protected String doInBackground() throws Exception {
							  progressDialog.setVisible(true);
							
							try {
								project.createModel();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							display.setNameAndDescription(project);

							ProjectService.saveProject(project);
							return "";
						}

						protected void done() {
							  progressDialog.setVisible(false);
							MainFrame.getMainFrame().setPanel(new MainPanelPresenter(project).getDisplay().asComponent());

							
						};

					};
					  progressDialog.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
							    super.windowClosing(e);
							    sw.cancel(true);

							}
						    });
					sw.execute();

				}

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

				ProjectService.importProject(fileChooser.getSelectedFile());

				display.refreshTableModel();

			}
		});

		display.setExportAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setSelectedFile(new File(display.getSelectedProject().getName()));

				fileChooser.showSaveDialog(display.asComponent());

				File file = fileChooser.getSelectedFile();
				ProjectService.exportProject(display.getSelectedProject(), file);
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

	private Frame findParentFrame() {
		Component c = display.asComponent().getParent();

		while (true) {
			if (c instanceof Frame)
				return (Frame) c;

			c = c.getParent();
		}
	}
}
