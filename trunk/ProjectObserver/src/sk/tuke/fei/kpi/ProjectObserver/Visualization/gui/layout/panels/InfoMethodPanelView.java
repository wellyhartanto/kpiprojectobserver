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
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util.StringUtil;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;

public class InfoMethodPanelView extends InfoJPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoMethodPanelView(Method me) {

		setLayout(new MigLayout("fillx"));

		JLabel name = new JLabel(Messages.getMessage("info.method") + " "
				+ me.getName());
	//	name.setFont(CommonFonts.tahoma14);
		add(name, "growx,wrap");

		String fullnametext = StringUtil.convertMethodToString(me);
		JLabel fullname = new JLabel("<html>" + fullnametext + "</html>");
	//	fullname.setFont(CommonFonts.tahoma14);
		add(fullname, "growx,wrap");

		/*
		 * if (!me.getParams().isEmpty()) { JLabel paramsNumber = new
		 * JLabel(String.format(Messages .getMessage("info.numberofparams"),
		 * String.valueOf(me .getParams().size()))); add(paramsNumber, "wrap");
		 * } if (!me.getExceptions().isEmpty()) { JLabel exceptionsNumber = new
		 * JLabel(String.format(Messages .getMessage("info.numberofexceptions"),
		 * String.valueOf(me .getExceptions().size()))); add(exceptionsNumber,
		 * "wrap"); }
		 */

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
