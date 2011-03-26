package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;

public class InfoInterfacePanelView extends JPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoInterfacePanelView(Interface in) {

		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(in.getName());
		name.setFont(MyFonts.tahoma14);
		add(name, "wrap");

		if (in.getImplemented() != null && !in.getImplemented().isEmpty()) {
			add(new JLabel(Messages.getMessage("info.implementedinterfaces") + in.getImplemented().toString()), "span,wrap");
		}
		
		if (!in.getFields().isEmpty()) {
			JLabel fieldsNumber = new JLabel(String.format(Messages.getMessage("info.numberoffields"), String.valueOf(in.getFields().size())));
			add(fieldsNumber, "wrap");
		}

		if (!in.getMethods().isEmpty()) {
			JLabel methodsNumber = new JLabel(String.format(Messages.getMessage("info.numberofmethods"), String.valueOf(in.getMethods().size())));
			add(methodsNumber, "wrap");
		}

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
