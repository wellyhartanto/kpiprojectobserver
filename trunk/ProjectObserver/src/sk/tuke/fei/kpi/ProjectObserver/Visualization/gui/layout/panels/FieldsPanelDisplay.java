package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.DisplayComponent;

public interface FieldsPanelDisplay extends DisplayComponent {
void setMouseListener(MouseListener l);
	
	JTable getTable();
	
	void setData(List<Field> fields);
	
	void setExtraFields(List<Field> fields);
	
	
}
