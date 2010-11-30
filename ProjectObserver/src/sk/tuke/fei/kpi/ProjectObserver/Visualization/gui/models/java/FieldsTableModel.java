package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.Locale;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;

public class FieldsTableModel extends GenericTableModel<Field> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4989849825096506542L;

	public FieldsTableModel(Locale locale) {
		super(locale);

		data = new ArrayList<Field>();
		columnNames = new String[] {
				bundle.getString("table.fieldstable.column.name"),
				bundle.getString("table.fieldstable.column.type"),
				bundle.getString("table.fieldstable.column.visibility") };

	}

	@Override
	public Object getValueAt(int row, int column) {

		switch (column) {
		case 0:
			return data.get(row).getName();

		case 1:

			return data.get(row).getType();
		case 2:

			return data.get(row).getVisibility();
		default:
			break;
		}

		return "";
	}
}
