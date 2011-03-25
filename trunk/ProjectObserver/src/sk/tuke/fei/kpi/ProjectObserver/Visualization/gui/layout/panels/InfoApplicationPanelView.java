package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;

public class InfoApplicationPanelView extends JPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoApplicationPanelView(Application app) {

		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(app.getName());
		name.setFont(MyFonts.font3);
		add(name, "wrap");

		if (!app.getPackages().isEmpty()) {
			JLabel packagesNumber = new JLabel(String.format(Messages.getMessage("info.numberofpackages"), String.valueOf(app.getPackages().size())));
			add(packagesNumber, "wrap");
		}
		if (!app.getInterfaces().isEmpty()) {
			JLabel interfacesNumber = new JLabel(String.format(Messages.getMessage("info.numberofinterfaces"), String.valueOf(app.getInterfaces().size())));
			add(interfacesNumber, "wrap");
		}
		if (!app.getClasses().isEmpty()) {
			JLabel classesNumber = new JLabel(String.format(Messages.getMessage("info.numberofclasses"), String.valueOf(app.getClasses().size())));
			add(classesNumber, "wrap");
		}
		if (!app.getEnums().isEmpty()) {
			JLabel enumsNumber = new JLabel(String.format(Messages.getMessage("info.numberofenums"), String.valueOf(app.getEnums().size())));
			add(enumsNumber, "wrap");
		}

		List<Component> components = Arrays.asList(getComponents());
		for (Component component : components) {
			if (component instanceof JLabel) {
				component.setFont(MyFonts.font3);
			}
		}

	}

	@Override
	public JComponent asComponent() {
		return this;
	}

}
