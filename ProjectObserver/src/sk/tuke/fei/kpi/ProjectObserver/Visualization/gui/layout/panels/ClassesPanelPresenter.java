package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;

public class ClassesPanelPresenter extends BasicPresenter<ClassesPanelDisplay> {

	private static ClassesPanelPresenter instance;

	public static ClassesPanelPresenter getInstance(List<Class> classes) {
		if (instance == null) {
			instance = new ClassesPanelPresenter(classes);
		} else {
			instance.getDisplay().setData(classes);
		}
		return instance;
	}

	private ClassesPanelPresenter(List<Class> classes) {

		display = new ClassesPanelView(classes);
		bind();

	}

	@Override
	protected void onBind() {
		super.onBind();

	}

}
