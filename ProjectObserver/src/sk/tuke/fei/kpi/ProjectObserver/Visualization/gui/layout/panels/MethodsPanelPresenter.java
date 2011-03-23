package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class MethodsPanelPresenter extends BasicPresenter<MethodsPanelDisplay> {
	private static MethodsPanelPresenter instance;

	public static MethodsPanelPresenter getInstance(List<Method> methods) {
		if (instance == null) {
			instance = new MethodsPanelPresenter(methods);
		} else {
			instance.getDisplay().setData(methods);
		}
		return instance;
	}

	private MethodsPanelPresenter(List<Method> methods) {
		display = new MethodsPanelView(methods);
		bind();
	}

	public void setExtraMethods(List<Method> methods) {
		if (instance != null) {
			display.setExtraMethods(methods);
		}
	}

}
