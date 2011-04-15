package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXLabel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.InfoJPanel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util.StringUtil;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;

public class InfoInterfacePanelView extends InfoJPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoInterfacePanelView(Interface in) {

		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(Messages.getMessage("info.interface") + " " + in.getName());
		// name.setFont(CommonFonts.tahoma14);
		add(name, "growx,wrap");

		if (in.getImplemented() != null && !in.getImplemented().isEmpty()) {
			JXLabel jx = new JXLabel(Messages.getMessage("info.implementedinterfaces") + StringUtil.prepareArrayToString(in.getImplemented()));
			jx.setLineWrap(true);
			add(jx, "growx,wrap");
		}

		if (!in.getFields().isEmpty()) {
			JLabel fieldsNumber = new JLabel(String.format(Messages.getMessage("info.numberoffields"), String.valueOf(in.getFields().size())));
			add(fieldsNumber, "growx,wrap");
		}

		if (!in.getMethods().isEmpty()) {
			JLabel methodsNumber = new JLabel(String.format(Messages.getMessage("info.numberofmethods"), String.valueOf(in.getMethods().size())));
			add(methodsNumber, "growx,wrap");
		}

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
