package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.sourcecode;

import java.awt.Component;

import org.w3c.dom.Document;

import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;

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

	/**
	 * 
	 * @param graph
	 */
	public ClassGraphComponent(Class sourceClass, mxGraph graph) {
		super(graph);

		this.umlClass = sourceClass;
		mxGraphView graphView = new mxGraphView(graph);

		mxCodec codec = new mxCodec();
		Document doc = mxUtils.loadDocument(ClassGraphComponent.class.getResource(
				"/sk/tuke/fei/kpi/ProjectObserver/Visualization/gui/layout/graphics/default-style.xml").toString());
		codec.decode(doc.getDocumentElement(), graph.getStylesheet());
		getViewport().setOpaque(false);
		graph.setView(graphView);

	}

	public Component[] createComponents(mxCellState state) {
		if (getGraph().getModel().isVertex(state.getCell())) {
			return new Component[] { new JPanelRenderer(umlClass) };
		}

		return null;
	}

}
