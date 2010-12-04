package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;

public class ClassesTableModel extends GenericTableModel<Class> {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2895295854299696658L;

    public ClassesTableModel() {
	super();
	data = new ArrayList<Class>();

	columnNames = new String[] { bundle.getString("table.classestable.column.name"),
		bundle.getString("table.classestable.column.visibility") };

    }

    @Override
    public Object getValueAt(int row, int column) {

	switch (row) {
	case 0:
	    return data.get(row).getName();

	case 1:

	    return data.get(row).getVisibility();

	default:
	    break;
	}

	return "";
    }

}
