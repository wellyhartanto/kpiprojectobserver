package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import org.jdesktop.swingx.JXLabel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.InfoJPanel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MultiLineLabel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util.StringUtil;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Constructor;

public class InfoClassPanelView extends InfoJPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoClassPanelView(Class cl) {
		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(Messages.getMessage("info.class") + " " + cl.getName());
		// name.setFont(CommonFonts.tahoma14);
		add(name, "growx,wrap");

		if (cl.getSuperClass() != null) {
			add(new JLabel(Messages.getMessage("info.extends") + " " + cl.getSuperClass().getName()), "growx,wrap");
		}

		if (cl.getImplemented() != null && !cl.getImplemented().isEmpty()) {
			add(new JLabel("<html>" + Messages.getMessage("info.implementedinterfaces") + StringUtil.prepareArrayToString(cl.getImplemented()) + "</html>"),
					"growx,wrap");
		}

		if (cl.getConstructors() != null) {
			add(new JLabel(Messages.getMessage("info.konstructors")), "growx,wrap");
			for (Constructor constructor : cl.getConstructors()) {
				JXLabel jl = new JXLabel();
				jl.setLineWrap(true);
				jl.setFont(CommonFonts.getNormalTextFont());
				jl.setText(StringUtil.convertConstructorToString(constructor));

				add(jl, "growx,wrap");
			}
		}

		if (!cl.getFields().isEmpty()) {
			JLabel fieldsNumber = new JLabel(String.format(Messages.getMessage("info.numberoffields"), String.valueOf(cl.getFields().size())));
			add(fieldsNumber, "growx,wrap");
		}
		if (!cl.getMethods().isEmpty()) {
			JLabel methodsNumber = new JLabel(String.format(Messages.getMessage("info.numberofmethods"), String.valueOf(cl.getMethods().size())));
			add(methodsNumber, "growx,wrap");
		}
		if (!cl.getClasses().isEmpty()) {
			JLabel classesNumber = new JLabel(String.format(Messages.getMessage("info.numberofclasses"), String.valueOf(cl.getClasses().size())));
			add(classesNumber, "growx,wrap");
		}
		if (!cl.getEnums().isEmpty()) {
			JLabel enumsNumber = new JLabel(String.format(Messages.getMessage("info.numberofenums"), String.valueOf(cl.getEnums().size())));
			add(enumsNumber, "growx,wrap");
		}

		List<Component> components = Arrays.asList(getComponents());
		for (Component component : components) {
			if (component instanceof JLabel) {
				component.setFont(CommonFonts.getNormalTextFont());
			}
		}

	}

	@Override
	public JComponent asComponent() {
		return this;
	}

}
