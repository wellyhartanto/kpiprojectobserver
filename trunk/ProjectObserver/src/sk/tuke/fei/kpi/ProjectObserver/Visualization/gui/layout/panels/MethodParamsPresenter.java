package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class MethodParamsPresenter extends BasicPresenter<MethodParamsDisplay> {
	private static MethodParamsPresenter instance;

	public static MethodParamsPresenter getInstance(List<Param> params) {
		if (instance == null) {
			instance = new MethodParamsPresenter(params);
		} else {
			instance.getDisplay().setData(params);
		}
		return instance;
	}

	private MethodParamsPresenter(List<Param> params) {
		display = new MethodParamsView(params);
		bind();
	}

}
