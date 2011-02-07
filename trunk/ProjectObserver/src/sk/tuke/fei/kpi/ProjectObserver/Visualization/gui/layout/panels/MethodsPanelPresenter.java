package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class MethodsPanelPresenter extends BasicPresenter<MethodsPanelDisplay> {
	public MethodsPanelPresenter(List<Method> methods) {
		display = new MethodsPanelView(methods);
		bind();
	}

}
