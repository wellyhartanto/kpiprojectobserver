package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.InfoPanelDisplay;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.InfoPanelView;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class InfoPanelPresenter extends BasicPresenter<InfoPanelDisplay> {

    public InfoPanelPresenter(Object object) {

	display = new InfoPanelView(object);
	bind();
    }

}
