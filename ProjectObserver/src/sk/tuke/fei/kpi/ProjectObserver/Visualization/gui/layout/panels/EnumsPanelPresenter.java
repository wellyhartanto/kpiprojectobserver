package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class EnumsPanelPresenter extends BasicPresenter<EnumsPanelDisplay> {
	public EnumsPanelPresenter(List<Enum> enums) {
		display = new EnumsPanelView(enums);
		bind();
	}
}
