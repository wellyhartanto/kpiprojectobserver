package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class InfoPanelPresenter extends BasicPresenter<InfoPanelDisplay> {

    public InfoPanelPresenter(Object object) {

	display = new InfoPanelView(object);
	bind();
    }

}
