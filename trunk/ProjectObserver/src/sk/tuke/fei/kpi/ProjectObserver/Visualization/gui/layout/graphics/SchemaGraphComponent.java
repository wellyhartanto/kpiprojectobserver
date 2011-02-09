package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics;

import java.awt.Component;

import com.mxgraph.io.mxCodec;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;

public class SchemaGraphComponent extends mxGraphComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1152655782652932774L;

	/**
	 * 
	 * @param graph
	 */
	public SchemaGraphComponent(mxGraph graph) {
		super(graph);

		mxGraphView graphView = new mxGraphView(graph);

		mxCodec codec = new mxCodec();
		// Document doc =
		// mxUtils.loadDocument(SchemaGraphComponent.class.getResource(
		// "/sk/tuke/fei/kpi/ProjectObserver/Visualization/gui/layout/graphics/default-style.xml").toString());
		// codec.decode(doc.getDocumentElement(), graph.getStylesheet());

		// Sets the background to white
		// getViewport().setOpaque(false);

		graph.setView(graphView);
	}

	public Component[] createComponents(mxCellState state) {
		if (getGraph().getModel().isVertex(state.getCell())) {
			return new Component[] { new JTableRenderer(state.getCell(), this) };
		}

		return null;
	}
}
