package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.JXTable;

public class SelectionListener implements ListSelectionListener {
	JTable table;

	public SelectionListener(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {

		} else if (e.getSource() == table.getColumnModel().getSelectionModel() && table.getColumnSelectionAllowed()) {

		}

	}

}
