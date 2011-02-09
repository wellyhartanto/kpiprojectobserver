package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics;

import java.awt.Color;

import javax.swing.JPanel;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

public class ClassPanel extends JPanel {

	public ClassPanel() {

		setBackground(Color.WHITE);

		createGraph();

	}

	private void createGraph() {

		mxGraph graph = new mxGraph() {
			// Make all edges unmovable
			public boolean isCellMovable(Object cell) {
				return !getModel().isEdge(cell);
			}

			// Make all vertex boxes unresizable
			public boolean isCellResizable(Object cell) {
				return !getModel().isVertex(cell);
			}

		};

		Object parent = graph.getDefaultParent();
		// Make all vertices and edges uneditable
		graph.setCellsEditable(false);
		// Make all edges unbendable
		graph.setCellsBendable(false);

		graph.getModel().beginUpdate();
		try {
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30);
			Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30, "ROUNDED");
			graph.insertEdge(parent, null, "Edge", v1, v2);
			// To modify the color of a vertex:
			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "#00FF00", new Object[] { v1 });
			graph.setCellStyles(mxConstants.STYLE_FONTCOLOR, "BLACK", new Object[] { v1 });

		} finally {
			graph.getModel().endUpdate();
		}

		graph.getModel().endUpdate();

		mxGraphComponent graphComponent = new mxGraphComponent(graph);

		add(graphComponent);

	}

}
