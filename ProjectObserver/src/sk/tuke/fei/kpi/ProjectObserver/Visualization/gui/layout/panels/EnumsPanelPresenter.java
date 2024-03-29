package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Enum;

public class EnumsPanelPresenter extends BasicPresenter<EnumsPanelDisplay> {

	private static EnumsPanelPresenter instance;

	public static EnumsPanelPresenter getInstance(List<Enum> enums) {
		if (instance == null) {
			instance = new EnumsPanelPresenter(enums);
		} else {
			instance.getDisplay().setData(enums);
		}
		return instance;
	}

	private EnumsPanelPresenter(List<Enum> enums) {
		display = new EnumsPanelView(enums);
		bind();
	}
}
