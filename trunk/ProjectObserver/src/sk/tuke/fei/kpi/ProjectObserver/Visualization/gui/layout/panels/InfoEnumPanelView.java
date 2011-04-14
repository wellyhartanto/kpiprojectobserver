package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.InfoJPanel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Enum;

public class InfoEnumPanelView extends InfoJPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoEnumPanelView(Enum en) {

		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(en.getName());
	//	name.setFont(CommonFonts.tahoma14);
		add(name, "wrap");

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
