package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class ExceptionsPanelPresenter extends BasicPresenter<ExceptionsPanelDisplay> {
	private static ExceptionsPanelPresenter instance;

	public static ExceptionsPanelPresenter getInstance(String[] exceptions) {
		if (instance == null) {
			instance = new ExceptionsPanelPresenter(exceptions);
		} else {
			instance.getDisplay().setData(exceptions);
		}
		return instance;
	}

	private ExceptionsPanelPresenter(String[] exceptions) {
		display = new ExceptionsPanelView(exceptions);
		bind();
	}
}
