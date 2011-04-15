package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.InfoJPanel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;

public class InfoFieldPanelView extends InfoJPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoFieldPanelView(Field fl) {

		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(Messages.getMessage("info.field") + " " + fl.getName());
		// name.setFont(CommonFonts.tahoma14);
		add(name, "growx,wrap");

		add(new JLabel(fl.getVisibility() + " " + fl.getType() + " " + fl.getName()), "growx,wrap");

		List<Component> components = Arrays.asList(getComponents());
		for (Component component : components) {
			if (component instanceof JLabel) {
				component.setFont(CommonFonts.getNormalTextFont());
			}
		}
		name.setFont(CommonFonts.getInfoLabelFont());
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

}
