package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;

import org.w3c.dom.Document;

import sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Interface;

import com.mxgraph.io.mxCodec;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;

public class InterfaceGraphComponent extends mxGraphComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1152655782652932774L;

	private Interface umlInterface;

	/**
	 * 
	 * @param graph
	 */
	public InterfaceGraphComponent(Interface umlInterface, mxGraph graph) {
		super(graph);

		this.umlInterface = umlInterface;
		mxGraphView graphView = new mxGraphView(graph);

		mxCodec codec = new mxCodec();
		Document doc = mxUtils.loadDocument(InterfaceGraphComponent.class.getResource(
				"/sk/tuke/fei/kpi/ProjectObserver/Visualization/gui/layout/graphics/default-style.xml").toString());
		codec.decode(doc.getDocumentElement(), graph.getStylesheet());
		getViewport().setOpaque(true);
		graph.setView(graphView);
		graph.setCellsEditable(false);
		setBorder(BorderFactory.createEmptyBorder());

		getViewport().setBackground(Color.WHITE);

	}

	public Component[] createComponents(mxCellState state) {
		if (getGraph().getModel().isVertex(state.getCell())) {
			return new Component[] { new InterfacePanelRenderer(umlInterface) };
		}

		return null;
	}

}
