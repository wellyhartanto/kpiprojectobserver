package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;

public class EnumsTableModel extends GenericTableModel<Enum> {

    /**
	 * 
	 */
    private static final long serialVersionUID = -5104451800038762465L;

    public EnumsTableModel() {
	super();
	data = new ArrayList<Enum>();

	columnNames = new String[] { bundle.getString("table.enumstable.column.name") };

    }

}
