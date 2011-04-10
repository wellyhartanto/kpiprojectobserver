package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.MainFrame;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.service.PdfService;
import sk.tuke.fei.kpi.akAgent.integration.Project;

public class ExportDialogPresenter extends BasicPresenter<ExportDialogDisplay> {

	private Project project;

	public ExportDialogPresenter(Project project) {

		display = new ExportDialogView();
		this.project = project;
		bind();
	}

	@Override
	protected void onBind() {
		super.onBind();

		display.setOKAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				JFileChooser saveFileChooser = new JFileChooser();
				// int returnVal=
				// saveFileChooser.showDialog(display.asComponent(),Messages.getMessage("loginpanel.buttons.export"));
				int returnVal = saveFileChooser.showSaveDialog(MainFrame.getMainFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = saveFileChooser.getSelectedFile();
					// PdfService.exportAllFromSource(project, file.getPath());

					if (display.getSelectedExportType() == 1) {
						PdfService.exportMissing(project, file.getPath());
					}
					if (display.getSelectedExportType() == 2) {
						PdfService.exportExtra(project, file.getPath());
					}
					if (display.getSelectedExportType() == 3) {
						PdfService.exportAllFromSource(project, file.getPath());
					}
					if (display.getSelectedExportType() == 4) {
						PdfService.exportAllFromUML(project, file.getPath());
					}
					
					display.showDialog(false);
				}

			}
		});

		display.setCancelAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				display.showDialog(false);
			}
		});
	}

}
