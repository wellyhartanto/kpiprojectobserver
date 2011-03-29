package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;

public class InfoPackagePanelView extends JPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoPackagePanelView(Package pa) {

		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(Messages.getMessage("info.package") + " "
				+ pa.getName());
		name.setFont(CommonFonts.tahoma14);
		add(name, "growx,wrap");

		if (!pa.getPackages().isEmpty()) {
			JLabel packagesNumber = new JLabel(String.format(Messages
					.getMessage("info.numberofpackages"), String.valueOf(pa
					.getPackages().size())));
			add(packagesNumber, "growx,wrap");
		}
		if (!pa.getInterfaces().isEmpty()) {
			JLabel interfacesNumber = new JLabel(String.format(Messages
					.getMessage("info.numberofinterfaces"), String.valueOf(pa
					.getInterfaces().size())));
			add(interfacesNumber, "growx,wrap");
		}
		if (!pa.getClasses().isEmpty()) {
			JLabel classesNumber = new JLabel(String.format(Messages
					.getMessage("info.numberofclasses"), String.valueOf(pa
					.getClasses().size())));
			add(classesNumber, "growx,wrap");
		}
		if (!pa.getEnums().isEmpty()) {
			JLabel enumsNumber = new JLabel(String.format(Messages
					.getMessage("info.numberofenums"), String.valueOf(pa
					.getEnums().size())));
			add(enumsNumber, "growx,wrap");
		}

		List<Component> components = Arrays.asList(getComponents());
		for (Component component : components) {
			if (component instanceof JLabel) {
				component.setFont(CommonFonts.tahoma14);
			}
		}
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

}
