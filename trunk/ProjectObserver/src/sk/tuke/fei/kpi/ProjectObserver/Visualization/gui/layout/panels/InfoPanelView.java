package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util.StringUtil;

public class InfoPanelView extends JPanel implements InfoPanelDisplay {

	private static final long serialVersionUID = 159665078173976827L;

	public InfoPanelView(Object object) {

		setLayout(new MigLayout("fillx"));

		if (object instanceof Application) {

			Application app = ((Application) object);
			JLabel name = new JLabel(app.getName());
			name.setFont(MyFonts.font3);
			add(name, "wrap");

			JLabel classesNumber = new JLabel(Messages.getMessage("info.numberofclasses"));
			JLabel interfacesNumber = new JLabel(Messages.getMessage("info.numberofinterfaces"));
			JLabel packagesNumber = new JLabel(Messages.getMessage("info.numberofpackages"));
			JLabel enumsNumber = new JLabel(Messages.getMessage("info.numberofenums"));
			add(packagesNumber);
			add(new JLabel(String.valueOf(app.getPackages().size())), "wrap");

			add(classesNumber);
			add(new JLabel(String.valueOf(app.getClasses().size())), "wrap");

			add(interfacesNumber);
			add(new JLabel(String.valueOf(app.getInterfaces().size())), "wrap");

			add(enumsNumber);
			add(new JLabel(String.valueOf(app.getEnums().size())), "wrap");

		}

		if (object instanceof Package) {
			Package pa = ((Package) object);
			JLabel name = new JLabel(pa.getName());
			name.setFont(MyFonts.font3);
			add(name, "wrap");

			JLabel classesNumber = new JLabel(Messages.getMessage("info.numberofclasses"));
			JLabel interfacesNumber = new JLabel(Messages.getMessage("info.numberofinterfaces"));
			JLabel packagesNumber = new JLabel(Messages.getMessage("info.numberofpackages"));
			JLabel enumsNumber = new JLabel(Messages.getMessage("info.numberofenums"));
			add(packagesNumber);
			add(new JLabel(String.valueOf(pa.getPackages().size())), "wrap");

			add(classesNumber);
			add(new JLabel(String.valueOf(pa.getClasses().size())), "wrap");

			add(interfacesNumber);
			add(new JLabel(String.valueOf(pa.getInterfaces().size())), "wrap");

			add(enumsNumber);
			add(new JLabel(String.valueOf(pa.getEnums().size())), "wrap");

		}
		if (object instanceof Class) {

			Class cl = ((Class) object);
			JLabel name = new JLabel(cl.getName());
			name.setFont(MyFonts.font3);
			add(name, "wrap");

			JLabel fieldsNumber = new JLabel(Messages.getMessage("info.numberoffields"));
			JLabel methodsNumber = new JLabel(Messages.getMessage("info.numberofmethods"));
			JLabel classesNumber = new JLabel(Messages.getMessage("info.numberofclasses"));
			JLabel enumsNumber = new JLabel(Messages.getMessage("info.numberofenums"));

			add(classesNumber);
			add(new JLabel(String.valueOf(cl.getClasses().size())), "wrap");
			add(methodsNumber);
			add(new JLabel(String.valueOf(cl.getMethods().size())), "wrap");
			add(enumsNumber);
			add(new JLabel(String.valueOf(cl.getEnums().size())), "wrap");
			add(fieldsNumber);
			add(new JLabel(String.valueOf(cl.getFields().size())), "wrap");

		}
		if (object instanceof Interface) {
			Interface in = ((Interface) object);
			JLabel name = new JLabel(in.getName());
			name.setFont(MyFonts.font3);
			add(name, "wrap");
			JLabel fieldsNumber = new JLabel(Messages.getMessage("info.numberoffields"));
			JLabel methodsNumber = new JLabel(Messages.getMessage("info.numberofmethods"));

			add(methodsNumber);
			add(new JLabel(String.valueOf(in.getMethods().size())), "wrap");

			add(fieldsNumber);
			add(new JLabel(String.valueOf(in.getFields().size())), "wrap");

		}
		if (object instanceof Enum) {
			Enum en = ((Enum) object);
			JLabel name = new JLabel(en.getName());
			name.setFont(MyFonts.font3);
			add(name, "wrap");

		}

		if (object instanceof Method) {
			Method me = ((Method) object);

			String fullnametext = me.getVisibility() + " " + me.getReturnType() + " " + me.getName() + "(" + StringUtil.prepareMethodParameters(me.getParams())
					+ ")";
			JLabel fullname = new JLabel(fullnametext);
			fullname.setFont(MyFonts.font3);
			add(fullname, "wrap");

			JLabel name = new JLabel(me.getName());
			name.setFont(MyFonts.font3);
			add(name, "wrap");

			JLabel exceptionsNumber = new JLabel(Messages.getMessage("info.numberofexceptions"));
			JLabel paramsNumber = new JLabel(Messages.getMessage("info.numberofparams"));

			add(exceptionsNumber);
			add(new JLabel(String.valueOf(me.getExceptions().size())), "wrap");

			add(paramsNumber);
			add(new JLabel(String.valueOf(me.getParams().size())), "wrap");

		}
		if (object instanceof Field) {
			Field fl = ((Field) object);
			JLabel name = new JLabel(fl.getName());
			name.setFont(MyFonts.font3);
			add(name, "wrap");

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
