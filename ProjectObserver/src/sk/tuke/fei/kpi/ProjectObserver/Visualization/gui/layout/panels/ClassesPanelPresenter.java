package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class ClassesPanelPresenter extends BasicPresenter<ClassesPanelDisplay> {
	public ClassesPanelPresenter(List<Class> classes) {
		display = new ClassesPanelView(classes);
		bind();
	}
}
