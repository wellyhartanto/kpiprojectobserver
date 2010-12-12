package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

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

public class InfoPanelView extends JPanel implements InfoPanelDisplay {

    private static final long serialVersionUID = 159665078173976827L;

    public InfoPanelView(Object object) {

	setLayout(new MigLayout());

	if (object instanceof Application) {

	    Application app = ((Application) object);
	    JLabel name = new JLabel(app.getName());
	    name.setFont(MyFonts.font3);
	    add(name);
	}

	if (object instanceof Package) {
	    Package pa = ((Package) object);
	    JLabel name = new JLabel(pa.getName());
	    name.setFont(MyFonts.font3);
	    add(name);

	}
	if (object instanceof Class) {

	    Class cl = ((Class) object);
	    JLabel name = new JLabel(cl.getName());
	    name.setFont(MyFonts.font3);
	    add(name);

	}
	if (object instanceof Interface) {
	    Interface in = ((Interface) object);
	    JLabel name = new JLabel(in.getName());
	    name.setFont(MyFonts.font3);
	    add(name);
	}
	if (object instanceof Enum) {
	    Enum en = ((Enum) object);
	    JLabel name = new JLabel(en.getName());
	    name.setFont(MyFonts.font3);
	    add(name);
	}

	if (object instanceof Method) {
	    Method me = ((Method) object);
	    JLabel name = new JLabel(me.getName());
	    name.setFont(MyFonts.font3);
	    add(name);
	}
	if (object instanceof Field) {
	    Field fl = ((Field) object);
	    JLabel name = new JLabel(fl.getName());
	    name.setFont(MyFonts.font3);
	    add(name);
	}
    }

    @Override
    public JComponent asComponent() {
	return this;
    }

}
