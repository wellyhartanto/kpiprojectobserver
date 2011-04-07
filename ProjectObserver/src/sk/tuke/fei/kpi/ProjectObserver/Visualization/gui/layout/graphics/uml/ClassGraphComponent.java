package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml;

import java.awt.Component;

import org.w3c.dom.Document;

import sk.tuke.fei.kpi.akAgent.integration.alignment.difference.Difference;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Class;

import com.mxgraph.io.mxCodec;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;

public class ClassGraphComponent extends mxGraphComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1152655782652932774L;

	private Class umlClass;
	private Difference difference;

	/**
	 * 
	 * @param graph
	 */
	public ClassGraphComponent(Class umlClass, mxGraph graph, Difference difference) {
		super(graph);

		this.difference = difference;
		this.umlClass = umlClass;
		mxGraphView graphView = new mxGraphView(graph);

		mxCodec codec = new mxCodec();
		Document doc = mxUtils.loadDocument(ClassGraphComponent.class.getResource(
				"/sk/tuke/fei/kpi/ProjectObserver/Visualization/gui/layout/graphics/default-style.xml").toString());
		codec.decode(doc.getDocumentElement(), graph.getStylesheet());
		getViewport().setOpaque(false);
		graph.setView(graphView);

		graph.setCellsEditable(false);

		graph.setConnectableEdges(false);
		
	}

	public Component[] createComponents(mxCellState state) {
		if (getGraph().getModel().isVertex(state.getCell())) {
			return new Component[] { new ClassPanelRenderer(umlClass, difference) };
		}

		return null;
	}

}
