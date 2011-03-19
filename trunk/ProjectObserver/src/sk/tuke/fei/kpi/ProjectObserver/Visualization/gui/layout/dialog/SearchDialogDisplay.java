package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.DisplayComponent;

public interface SearchDialogDisplay extends DisplayComponent {

	void setListSelectionAction(ListSelectionListener l);

	void setEntryKeyAction(KeyListener l);

	void setListClickedAction(MouseListener m);

	void setStatus(String status);

	JList getList();
	
}
