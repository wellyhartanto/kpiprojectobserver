package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;

public class ExceptionsTableModel extends GenericTableModel<String> {

    public ExceptionsTableModel() {
	super();
	data = new ArrayList<String>();

	columnNames = new String[] { bundle.getString("table.exceptionstable.column.name") };
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = -1178155466701373851L;

}
