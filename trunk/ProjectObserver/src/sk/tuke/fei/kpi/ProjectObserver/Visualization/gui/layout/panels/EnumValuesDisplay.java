package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseListener;
import java.util.List;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.DisplayComponent;

public interface EnumValuesDisplay extends DisplayComponent {
	void setMouseListener(MouseListener l);

	JXTable getTable();

	void setData(String[] values);
}
