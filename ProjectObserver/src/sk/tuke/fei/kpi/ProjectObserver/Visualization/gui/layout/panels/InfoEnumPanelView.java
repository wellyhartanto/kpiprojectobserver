package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;

public class InfoEnumPanelView extends JPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoEnumPanelView(Enum en) {

		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(en.getName());
		name.setFont(MyFonts.font3);
		add(name, "wrap");

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
