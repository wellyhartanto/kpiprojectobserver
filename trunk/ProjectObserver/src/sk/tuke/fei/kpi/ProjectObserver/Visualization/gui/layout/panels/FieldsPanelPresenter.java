package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class FieldsPanelPresenter extends BasicPresenter<FieldsPanelDisplay> {
	private static FieldsPanelPresenter instance;

	public static FieldsPanelPresenter getInstance(List<Field> fields) {
		if (instance == null) {
			instance = new FieldsPanelPresenter(fields);
		} else {
			instance.getDisplay().setData(fields);
		}
		return instance;
	}

	private FieldsPanelPresenter(List<Field> fields) {
		display = new FieldsPanelView(fields);
		bind();
	}

	public void setExtraFields(List<Field> fields) {
		if (instance != null) {
			display.setExtraFields(fields);
		}
	}
}
