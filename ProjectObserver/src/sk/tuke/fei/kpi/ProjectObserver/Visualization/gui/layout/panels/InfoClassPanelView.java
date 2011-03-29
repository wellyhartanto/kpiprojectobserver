package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Constructor;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util.StringUtil;

public class InfoClassPanelView extends JPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoClassPanelView(Class cl) {
		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(Messages.getMessage("info.class") + " "
				+ cl.getName());
		name.setFont(CommonFonts.tahoma14);
		add(name, "growx,wrap");

		if (cl.getSuperClass() != null) {
			add(new JLabel(Messages.getMessage("info.extends") + " "
					+ cl.getSuperClass().getName()), "growx,wrap");
		}

		if (cl.getImplemented() != null && !cl.getImplemented().isEmpty()) {
			add(new JLabel("<html>"
					+ Messages.getMessage("info.implementedinterfaces")
					+ StringUtil.prepareArrayToString(cl.getImplemented())
					+ "</html>"), "growx,wrap");
		}

		if (cl.getConstructors() != null) {
			add(new JLabel(Messages.getMessage("info.konstructors")),
					"growx,wrap");
			for (Constructor constructor : cl.getConstructors()) {
				add(new JLabel("<html>"
						+ StringUtil.convertConstructorToString(constructor)
						+ "</html>"), "growx,wrap");
			}
		}

		if (!cl.getFields().isEmpty()) {
			JLabel fieldsNumber = new JLabel(String.format(Messages
					.getMessage("info.numberoffields"), String.valueOf(cl
					.getFields().size())));
			add(fieldsNumber, "growx,wrap");
		}
		if (!cl.getMethods().isEmpty()) {
			JLabel methodsNumber = new JLabel(String.format(Messages
					.getMessage("info.numberofmethods"), String.valueOf(cl
					.getMethods().size())));
			add(methodsNumber, "growx,wrap");
		}
		if (!cl.getClasses().isEmpty()) {
			JLabel classesNumber = new JLabel(String.format(Messages
					.getMessage("info.numberofclasses"), String.valueOf(cl
					.getClasses().size())));
			add(classesNumber, "growx,wrap");
		}
		if (!cl.getEnums().isEmpty()) {
			JLabel enumsNumber = new JLabel(String.format(Messages
					.getMessage("info.numberofenums"), String.valueOf(cl
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
