package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonColors;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.FieldsTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;

public class FieldCellRenderer extends DefaultTableCellRenderer {

	public FieldCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		FieldsTableModel tableModel = (FieldsTableModel) table.getModel();
		Field field = tableModel.getData().get(table.convertRowIndexToModel(row));

		if (!isSelected) {
			comp.setBackground(getColor(tableModel, field,row));
			comp.setForeground(Color.BLACK);
		} else {
			comp.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
			comp.setForeground(isSelected ? Color.WHITE : Color.BLACK);
		}

		return comp;
	}

	private Color getColor(FieldsTableModel tableModel, Field field,int row) {
		if (tableModel.getExtraFields().contains(field)) {
			return new Color(173, 255, 47);
		} else {
			return  (row%2==0 )? Color.WHITE : CommonColors.TABLE_ROW;
		}
	}
	
}
