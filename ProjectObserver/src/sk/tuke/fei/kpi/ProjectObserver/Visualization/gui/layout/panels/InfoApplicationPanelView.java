package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;

public class InfoApplicationPanelView extends JPanel implements
		InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoApplicationPanelView(Application app) {

		setLayout(new MigLayout("fillx"));

		JLabel about = new JLabel(Messages.getMessage("info.about"));
		about.setFont(MyFonts.tahoma14);
		add(about, "gapleft 30,gapright 30,gaptop 30,span,growx,wrap");

		add(new JLabel(Messages.getMessage("info.explanation")), "gapleft 30,growx,wrap");

		ImageIcon iconPackage = new ImageIcon(getClass().getResource(
				CommonConstants.IMAGES_FOLDER_PATH + "package_obj.gif"));
		ImageIcon iconInterface = new ImageIcon(getClass().getResource(
				CommonConstants.IMAGES_FOLDER_PATH + "int_obj.gif"));
		ImageIcon iconClass = new ImageIcon(getClass().getResource(
				CommonConstants.IMAGES_FOLDER_PATH + "classes.gif"));

		ImageIcon iconAdded = new ImageIcon(getClass().getResource(
				CommonConstants.IMAGES_FOLDER_PATH + "added.png"));

		JLabel packagelbl = new JLabel();
		packagelbl.setText(Messages.getMessage("info.packagename"));
		packagelbl.setForeground(Color.RED);
		packagelbl.setIcon(iconPackage);
		packagelbl.setFont(MyFonts.dejavuSansBold10);
		add(packagelbl, "gapleft 30,growx");
		JLabel packageexpl = new JLabel(Messages
				.getMessage("info.packageexplanation"));
		add(packageexpl, "growx,wrap");

		JLabel classlbl = new JLabel();
		classlbl.setText(Messages.getMessage("info.classname"));
		classlbl.setForeground(Color.RED);
		classlbl.setIcon(iconClass);
		classlbl.setFont(MyFonts.dejavuSansBold10);
		add(classlbl, "gapleft 30,growx");
		JLabel classlblexpl = new JLabel(Messages
				.getMessage("info.classexplanation"));
		add(classlblexpl, "growx,wrap");

		JLabel interfacelbl = new JLabel();
		interfacelbl.setText(Messages.getMessage("info.interfacename"));
		interfacelbl.setForeground(Color.RED);
		interfacelbl.setIcon(iconInterface);
		interfacelbl.setFont(MyFonts.dejavuSansBold10);
		add(interfacelbl, "gapleft 30,growx");
		JLabel interfaceexpl = new JLabel(Messages
				.getMessage("info.interfaceexplanation"));
		add(interfaceexpl, "growx,wrap");

		JLabel extra = new JLabel(Messages.getMessage("info.fieldmethod"));
		extra.setBackground(new Color(173, 255, 47));
		extra.setOpaque(true);
		add(extra, "gapleft 30,growx");
		add(new JLabel(Messages.getMessage("info.extra")), "growx,wrap");

		JLabel missing = new JLabel(Messages.getMessage("info.fieldmethod"));
		missing.setForeground(Color.RED);
		add(missing, "gapleft 30,growx");
		add(new JLabel(Messages.getMessage("info.missing")), "growx,wrap");

		// if (!app.getPackages().isEmpty()) {
		// JLabel packagesNumber = new
		// JLabel(String.format(Messages.getMessage("info.numberofpackages"),
		// String.valueOf(app.getPackages().size())));
		// add(packagesNumber, "wrap");
		// }
		// if (!app.getInterfaces().isEmpty()) {
		// JLabel interfacesNumber = new
		// JLabel(String.format(Messages.getMessage("info.numberofinterfaces"),
		// String.valueOf(app.getInterfaces().size())));
		// add(interfacesNumber, "wrap");
		// }
		// if (!app.getClasses().isEmpty()) {
		// JLabel classesNumber = new
		// JLabel(String.format(Messages.getMessage("info.numberofclasses"),
		// String.valueOf(app.getClasses().size())));
		// add(classesNumber, "wrap");
		// }
		// if (!app.getEnums().isEmpty()) {
		// JLabel enumsNumber = new
		// JLabel(String.format(Messages.getMessage("info.numberofenums"),
		// String.valueOf(app.getEnums().size())));
		// add(enumsNumber, "wrap");
		// }

		List<Component> components = Arrays.asList(getComponents());
		for (Component component : components) {
			if (component instanceof JLabel) {
				component.setFont(MyFonts.tahoma14);
			}
		}

	}

	@Override
	public JComponent asComponent() {
		return this;
	}

}
