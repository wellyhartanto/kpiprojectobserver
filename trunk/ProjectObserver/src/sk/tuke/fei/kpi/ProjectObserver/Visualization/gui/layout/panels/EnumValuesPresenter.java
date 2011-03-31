package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Enum;

public class EnumValuesPresenter extends BasicPresenter<EnumValuesDisplay> {
	private static EnumValuesPresenter instance;

	public static EnumValuesPresenter getInstance(String[] values) {
		if (instance == null) {
			instance = new EnumValuesPresenter(values);
		} else {
			instance.getDisplay().setData(values);
		}
		return instance;
	}

	private EnumValuesPresenter(String[] values) {
		display = new EnumValuesView(values);
		bind();
	}

}
