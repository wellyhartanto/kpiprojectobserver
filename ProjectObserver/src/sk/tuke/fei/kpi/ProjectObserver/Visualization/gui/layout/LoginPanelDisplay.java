package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionListener;
import java.io.File;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.DisplayComponent;
import sk.tuke.fei.kpi.akAgent.integration.Project;

public interface LoginPanelDisplay extends DisplayComponent {

	void setLanguageChangeAction(ActionListener actionListener);

	void setOpenAction(ActionListener actionListener);

	void setCreateAction(ActionListener actionListener);

	void setImportAction(ActionListener actionListener);

	void setExportAction(ActionListener actionListener);

	void setDeleteAction(ActionListener actionListener);

	void setLoadSourceAction(ActionListener actionListener);

	void setLoadUmlAction(ActionListener actionListener);
	
	void setHelpAction(ActionListener actionListener);
	
	void setAboutAction(ActionListener actionListener);
	

	boolean isNewProjectCorrect(File umlFile, File sourceCodeFile);

	Project getSelectedProject();

	void removeProjectFromList(Project project);

	void setNameAndDescription(Project project);

	void setSourceCodeFileLabel(String filename);

	void setUmlFileLabel(String filename);

	void refreshTableModel();

	Object getSelectedLanguage();
	
}
