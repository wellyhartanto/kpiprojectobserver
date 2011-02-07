package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class MethodParamsPresenter extends BasicPresenter<MethodParamsDisplay> {

	public MethodParamsPresenter(Param[] params) {
		display = new MethodParamsView(params);
		bind();
	}

}
