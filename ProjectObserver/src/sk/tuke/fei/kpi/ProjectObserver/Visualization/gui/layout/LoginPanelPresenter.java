package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.MainFrame;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Languages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.ProgressDialog;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog.AboutDialogView;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog.HelpDialogView;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.service.ProjectService;
import sk.tuke.fei.kpi.akAgent.integration.Project;

public class LoginPanelPresenter extends BasicPresenter<LoginPanelDisplay> {

	private File sourceCodeFile;
	private File umlFile;

	public LoginPanelPresenter() {

		Preferences p = Preferences.userNodeForPackage(LoginPanelPresenter.class);
		int defaultLanguage = p.getInt(CommonConstants.DEFAULT_LANGUAGE, Languages.SK.ordinal());
		Languages.setLanguage(defaultLanguage);

		display = new LoginPanelView();
		bind();
	}

	@Override
	protected void onBind() {
		super.onBind();

		display.setLanguageChangeAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Languages selectedLanguage = (Languages) display.getSelectedLanguage();
				// Languages.setLanguage(selectedLanguage.ordinal());

				Preferences p = Preferences.userNodeForPackage(LoginPanelPresenter.class);
				p.putInt(CommonConstants.DEFAULT_LANGUAGE, selectedLanguage.ordinal());
				try {
					p.flush();
				} catch (BackingStoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainFrame.getMainFrame().setPanel(new LoginPanelPresenter().getDisplay().asComponent());
			}
		});

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

					String label = Messages.getMessage("message.info.creatingproject");
					final ProgressDialog progressDialog = new ProgressDialog(label, label, findParentFrame());
					final SwingWorker<String, String> sw = new SwingWorker<String, String>() {
						Project project = new Project(umlFile, sourceCodeFile);

						@Override
						protected String doInBackground() throws Exception {
							progressDialog.setVisible(true);

							try {
								project.createModel();
								project.alignModels();
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
				fileChooser.setFont(CommonFonts.getNormalTextFont());
				fileChooser.setMultiSelectionEnabled(false);
				int ret = fileChooser.showOpenDialog(display.asComponent());
				if (ret == JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					if (f.getName().toLowerCase().endsWith(".observer")) {
						ProjectService.importProject(f);
						display.refreshTableModel();
					} else {
						showWrongFileTypeWarning();
					}
				}
			}
		});

		display.setExportAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setSelectedFile(new File(display.getSelectedProject().getName()));

				int ret = fileChooser.showSaveDialog(display.asComponent());
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					ProjectService.exportProject(display.getSelectedProject(), file);
				}
			}
		});

		display.setLoadSourceAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(false);
				int ret = fileChooser.showOpenDialog(display.asComponent());
				if (ret == JFileChooser.APPROVE_OPTION) {
					if (fileChooser.getSelectedFile().getName().toLowerCase().endsWith(".owl")) {
						sourceCodeFile = fileChooser.getSelectedFile();
						display.setSourceCodeFileLabel(sourceCodeFile.getName());
					} else {
						showWrongFileTypeWarning();
					}
				}
			}
		});

		display.setLoadUmlAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(false);
				int ret = fileChooser.showOpenDialog(display.asComponent());

				if (ret == JFileChooser.APPROVE_OPTION) {
					if (fileChooser.getSelectedFile().getName().toLowerCase().endsWith(".xml")) {
						umlFile = fileChooser.getSelectedFile();
						display.setUmlFileLabel(umlFile.getName());
					} else {
						showWrongFileTypeWarning();
					}
				}
			}
		});

		display.setHelpAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpDialogView().setVisible(true);
			}
		});
		display.setAboutAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutDialogView().setVisible(true);
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

	private void showWrongFileTypeWarning() {
		JOptionPane.showMessageDialog(display.asComponent(), Messages.getMessage("message.warning.invalidfile"), "", JOptionPane.WARNING_MESSAGE);
	}
}
