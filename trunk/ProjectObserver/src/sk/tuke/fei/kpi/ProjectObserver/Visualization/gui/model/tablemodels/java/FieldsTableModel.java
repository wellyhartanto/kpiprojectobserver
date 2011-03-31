package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java;

import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;

public class FieldsTableModel extends GenericTableModel<Field> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4989849825096506542L;
	private List<Field> extraFields;

	public FieldsTableModel() {
		super();

		data = new ArrayList<Field>();
		extraFields = new ArrayList<Field>();

		columnNames = new String[] { Messages.getMessage("table.fieldstable.column.visibility"), Messages.getMessage("table.fieldstable.column.type"),
				Messages.getMessage("table.fieldstable.column.name") };

	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return data.get(row).getVisibility();
		case 1:
			return data.get(row).getType();
		case 2:
			return data.get(row).getName();
		default:
			break;
		}
		return "";
	}

	public void setExtraFields(List<Field> extraFields) {
		this.extraFields = extraFields;
	}

	public List<Field> getExtraFields() {
		return extraFields;
	}
}
