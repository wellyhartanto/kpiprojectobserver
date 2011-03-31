package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JTable;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.DisplayComponent;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;

public interface MethodsPanelDisplay extends DisplayComponent {
void setMouseListener(MouseListener l);
	
	JTable getTable();
	
	void setData(List<Method> methods);

	void setExtraMethods(List<Method> methods);
}
