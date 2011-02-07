package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class InterfacesPanelPresenter extends BasicPresenter<InterfacesPanelDisplay> {
	public InterfacesPanelPresenter(List<Interface> interfaces) {
		display = new InterfacesPanelView(interfaces);
		bind();
	}
}
