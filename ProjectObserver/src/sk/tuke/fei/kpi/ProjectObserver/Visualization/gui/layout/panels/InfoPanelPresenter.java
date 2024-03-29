package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Application;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;

public class InfoPanelPresenter extends BasicPresenter<InfoPanelDisplay> {

	public InfoPanelPresenter(Object object) {

		if (object instanceof Application) {
			display = new InfoApplicationPanelView((Application) object);
		}
		if (object instanceof Package) {
			display = new InfoPackagePanelView((Package) object);
		}
		if (object instanceof sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class) {
			display = new InfoClassPanelView((Class) object);
		}
		if (object instanceof Interface) {
			display = new InfoInterfacePanelView((Interface) object);
		}
		if (object instanceof Field) {
			display = new InfoFieldPanelView((Field) object);
		}
		if (object instanceof sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Enum) {
			display = new InfoEnumPanelView((Enum) object);
		}
		if (object instanceof Method) {
			display = new InfoMethodPanelView((Method) object);
		}

		bind();
	}

}
