package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml;

import java.awt.Component;

import org.w3c.dom.Document;

import com.mxgraph.io.mxCodec;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;
 
public class PackageGraphComponent extends mxGraphComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1152655782652932774L;

	private sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Package umlPackage;

	/**
	 * 
	 * @param graph
	 */
	public PackageGraphComponent(sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Package umlPackage, mxGraph graph) {
		super(graph);

		this.umlPackage = umlPackage;
		mxGraphView graphView = new mxGraphView(graph);

		mxCodec codec = new mxCodec();
		Document doc = mxUtils.loadDocument(PackageGraphComponent.class.getResource(
				"/sk/tuke/fei/kpi/ProjectObserver/Visualization/gui/layout/graphics/default-style.xml").toString());
		codec.decode(doc.getDocumentElement(), graph.getStylesheet());
		getViewport().setOpaque(false);
		graph.setView(graphView);
		graph.setCellsEditable(false);

	}

	public Component[] createComponents(mxCellState state) {
		if (getGraph().getModel().isVertex(state.getCell())) {
			return new Component[] { new PackagePanelRenderer(umlPackage) };
		}

		return null;
	}

}
