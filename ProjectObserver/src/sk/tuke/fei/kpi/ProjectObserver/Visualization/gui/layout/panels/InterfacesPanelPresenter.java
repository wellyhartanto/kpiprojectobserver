package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class InterfacesPanelPresenter extends BasicPresenter<InterfacesPanelDisplay> {
	private static InterfacesPanelPresenter instance;

	public static InterfacesPanelPresenter getInstance(List<Interface> interfaces) {
		if (instance == null) {
			instance = new InterfacesPanelPresenter(interfaces);
		} else {
			instance.getDisplay().setData(interfaces);
		}
		return instance;
	}

	private InterfacesPanelPresenter(List<Interface> interfaces) {
		display = new InterfacesPanelView(interfaces);
		bind();
	}
}
