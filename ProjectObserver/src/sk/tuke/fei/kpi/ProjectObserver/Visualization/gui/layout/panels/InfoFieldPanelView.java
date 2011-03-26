package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;

public class InfoFieldPanelView extends JPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoFieldPanelView(Field fl) {

		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(fl.getName());
		name.setFont(MyFonts.tahoma14);
		add(name, "wrap");

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
