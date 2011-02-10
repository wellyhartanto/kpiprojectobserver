package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class MainPanelPresenter extends BasicPresenter<MainPanelDisplay> {

	public MainPanelPresenter() {

		display = new MainPanelView();
		bind();
	}
}
