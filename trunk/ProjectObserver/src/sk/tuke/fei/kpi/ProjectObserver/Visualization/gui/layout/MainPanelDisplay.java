package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTree;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.ClassesPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.EnumValuesPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.EnumsPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.ExceptionsPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.FieldsPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.InfoPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.InterfacesPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.MethodParamsPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.MethodsPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.PackagesPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.DisplayComponent;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.TypeElement;

public interface MainPanelDisplay extends DisplayComponent {

	void setChangeProjectAction(ActionListener l);

	void setSearchAction(ActionListener l);
	
	void setExportAction(ActionListener l);
	
	void setTreeValueChangedAction();

	void setDetailSelection(JTable table);

	void setNavigationTree(JTree tree);

	void searchAndSelectElement(TypeElement element);

	ClassesPanelPresenter getClassesPanelPresenter();

	InfoPanelPresenter getInfoPanelPresenter();

	PackagesPanelPresenter getPackagesPanelPresenter();

	MethodsPanelPresenter getMethodsPanelPresenter();

	FieldsPanelPresenter getFieldsPanelPresenter();

	InterfacesPanelPresenter getInterfacesPanelPresenter();

	EnumsPanelPresenter getEnumsPanelPresenter();

	EnumValuesPresenter getEnumValuesPresenter();

	ExceptionsPanelPresenter getExceptionsPanelPresenter();

	MethodParamsPresenter getMethodParamsPresenter();

	public void saveWindowPrefs();

	public void restoreWindowPrefs();

}
