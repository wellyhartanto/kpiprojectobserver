package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml;
 
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import sk.tuke.fei.kpi.akAgent.integration.alignment.difference.Difference;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Class;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;

public class ClassPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7007225006753337933L;

	protected mxGraphComponent graphComponent;

	public ClassPanel() {
		// TODO Auto-generated constructor stub
	}

	public ClassPanel(Class umlclass, Difference difference) {
		super();

		setBackground(Color.WHITE);

		graphComponent = new ClassGraphComponent(umlclass, new mxGraph(), difference);
		// graphComponent.getGraph().setCellsResizable(true);
		mxGraph graph = graphComponent.getGraph();

		// graph.setBorder(0);

		Object parent = graph.getDefaultParent();
		graph.setAutoSizeCells(true);
		graph.setCellsResizable(false);
		graph.setCellsCloneable(false);
		mxCell cell;

		graph.getModel().beginUpdate();
		try {

			cell = (mxCell) graph.insertVertex(parent, null, "", 50, 20, 200, 200);

		} finally {
			graph.getModel().endUpdate();
		}

		double height = graphComponent.getGraphControl().getComponent(0).getPreferredSize().getHeight();
		double width = graphComponent.getGraphControl().getComponent(0).getPreferredSize().getWidth();

		graph.resizeCell(cell, new mxRectangle(50, 20, width, height));

		setLayout(new BorderLayout());

		add(graphComponent, BorderLayout.CENTER);
		repaint();
	}
}
