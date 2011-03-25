package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util.StringUtil;

public class InfoMethodPanelView extends JPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoMethodPanelView(Method me) {

		setLayout(new MigLayout("fillx"));

		String fullnametext = StringUtil.convertMethodToString(me);
		JLabel fullname = new JLabel(fullnametext);
		fullname.setFont(MyFonts.font3);
		add(fullname, "wrap");

		JLabel name = new JLabel(me.getName());
		name.setFont(MyFonts.font3);
		add(name, "wrap");

		if (!me.getParams().isEmpty()) {
			JLabel paramsNumber = new JLabel(String.format(Messages.getMessage("info.numberofparams"), String.valueOf(me.getParams().size())));
			add(paramsNumber, "wrap");
		}
		if (!me.getExceptions().isEmpty()) {
			JLabel exceptionsNumber = new JLabel(String.format(Messages.getMessage("info.numberofexceptions"), String.valueOf(me.getExceptions().size())));
			add(exceptionsNumber, "wrap");
		}

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
