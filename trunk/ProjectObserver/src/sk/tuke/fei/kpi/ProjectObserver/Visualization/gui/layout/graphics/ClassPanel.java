package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphComponent.mxGraphControl;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

public class ClassPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7007225006753337933L;

	protected mxGraphComponent graphComponent;

	public ClassPanel() {
		super();

		setBackground(Color.WHITE);
		

		graphComponent = new ClassGraphComponent(new mxGraph());
		graphComponent.getGraph().setCellsResizable(true);
		mxGraph graph = graphComponent.getGraph();

		 //graph.setBorder(0);

		Object parent = graph.getDefaultParent();
		graph.setAutoSizeCells(true);
		graph.setCellsResizable(false);
		
		mxCell cell;

		
		graph.getModel().beginUpdate();
		try {

			cell = (mxCell) graph
					.insertVertex(parent, null, "", 50, 20, 200, 200);

		} finally {
			graph.getModel().endUpdate();
		}

		double height = graphComponent.getGraphControl().getComponent(0)
				.getPreferredSize().getHeight();
		double width = graphComponent.getGraphControl().getComponent(0)
				.getPreferredSize().getWidth();

		graph.resizeCell(cell, new mxRectangle(50, 20, width, height));

		setLayout(new BorderLayout());

		add(graphComponent, BorderLayout.CENTER);

	}

}
