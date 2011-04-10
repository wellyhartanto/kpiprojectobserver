package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.event.ActionListener;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.DisplayComponent;

public interface ExportDialogDisplay extends DisplayComponent {

	int getSelectedExportType();

	void setCancelAction(ActionListener l);

	void setOKAction(ActionListener l);

	void showDialog(boolean visible);

}
