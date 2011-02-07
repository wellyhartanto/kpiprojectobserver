package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class ExceptionsPanelPresenter extends BasicPresenter<ExceptionsPanelDisplay> {

	public ExceptionsPanelPresenter(String[] exceptions) {
		display = new ExceptionsPanelView(exceptions);
		bind();
	}
}
