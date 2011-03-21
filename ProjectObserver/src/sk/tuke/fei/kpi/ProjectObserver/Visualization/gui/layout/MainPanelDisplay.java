package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.TypeElement;
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

public interface MainPanelDisplay extends DisplayComponent {

	void setChangeProjectAction(ActionListener l);

	void setSearchAction(ActionListener l);

	void setTreeValueChangedAction();

	void setDetailSelection(JXTable table);

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

}
