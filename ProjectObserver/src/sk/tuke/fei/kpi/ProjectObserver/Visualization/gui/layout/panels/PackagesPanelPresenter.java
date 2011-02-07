package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

public class PackagesPanelPresenter extends BasicPresenter<PackagesPanelDisplay> {

	public PackagesPanelPresenter(List<Package> packages) {
		display = new PackagesPanelView(packages);
		bind();
	}
}
