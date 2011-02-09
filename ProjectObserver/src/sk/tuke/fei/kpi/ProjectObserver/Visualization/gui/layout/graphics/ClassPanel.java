package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
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

		graphComponent = new SchemaGraphComponent(new mxGraph());

		graphComponent.getGraph().setCellsResizable(false);

		mxGraph graph = graphComponent.getGraph();

		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try {
			mxCell v1 = (mxCell) graph.insertVertex(parent, null, "", 20, 20, 200, 280);
		} finally {
			graph.getModel().endUpdate();
		}

		setLayout(new BorderLayout());
		add(graphComponent, BorderLayout.CENTER);

	}
}
