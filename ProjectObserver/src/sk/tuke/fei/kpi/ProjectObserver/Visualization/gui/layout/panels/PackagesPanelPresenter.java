package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class PackagesPanelPresenter extends BasicPresenter<PackagesPanelDisplay> {
	private static PackagesPanelPresenter instance;

	public static PackagesPanelPresenter getInstance(List<Package> packages) {
		if (instance == null) {
			instance = new PackagesPanelPresenter(packages);
		} else {
			instance.getDisplay().setData(packages);
		}
		return instance;
	}

	private PackagesPanelPresenter(List<Package> packages) {
		display = new PackagesPanelView(packages);
		bind();
	}
}
