package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.MethodsTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;

public class MethodCellRenderer extends DefaultTableCellRenderer {

	public MethodCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		MethodsTableModel tableModel = (MethodsTableModel) table.getModel();
		Method method = tableModel.getData().get(table.convertRowIndexToModel(row));

		if (!isSelected) {
			comp.setBackground(getColor(tableModel, method));
			comp.setForeground(Color.BLACK);
		} else {
			comp.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
			comp.setForeground(isSelected ? Color.WHITE : Color.BLACK);
		}

		return comp;
	}

	private Color getColor(MethodsTableModel tableModel, Method method) {
		if (tableModel.getExtraMethods().contains(method)) {
			return new Color(173, 255, 47);
		} else {
			return new JTable().getBackground();
		}
	}
}
