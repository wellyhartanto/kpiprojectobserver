package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml;

import java.awt.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;

public class PackagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7007225006753337933L;

	protected mxGraphComponent graphComponent;

	public PackagePanel() {
		// TODO Auto-generated constructor stub
	}

	public PackagePanel(sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Package umlPackage) {
		super();

		setBackground(Color.WHITE);

		graphComponent = new PackageGraphComponent(umlPackage, new mxGraph());
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

			cell = (mxCell) graph.insertVertex(parent, null, "", 150, 20, 200, 200);

		} finally {
			graph.getModel().endUpdate();
		}

		double height = graphComponent.getGraphControl().getComponent(0).getPreferredSize().getHeight();
		double width = graphComponent.getGraphControl().getComponent(0).getPreferredSize().getWidth();

		graph.resizeCell(cell, new mxRectangle(150, 20, width, height));
		
		
		setLayout(new MigLayout("insets 0,fill","","[growprio 50][]"));
//		JLabel title = new JLabel(Messages.getMessage("uml.title"));
//		title.setOpaque(false);
//		title.setFont(CommonFonts.getTableHeaderFont());
//		add(title,"gapleft 4,gaptop 4,wrap");
		
		if (umlPackage != null) {
			add(graphComponent, "grow");

		}
		repaint();
	}
}
