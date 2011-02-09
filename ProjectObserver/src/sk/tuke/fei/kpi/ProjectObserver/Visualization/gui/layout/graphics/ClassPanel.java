package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;

public class ClassPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7007225006753337933L;

	protected mxGraphComponent graphComponent;
	
	private mxIGraphModel model;

	public ClassPanel() {
		super();

		setBackground(Color.WHITE);

		graphComponent = new SchemaGraphComponent(new mxGraph());

		graphComponent.getGraph().setCellsResizable(true);

		mxGraph graph = graphComponent.getGraph();

		
		
		
		
		Object parent = graph.getDefaultParent();
		
		

		graph.setAutoSizeCells(true);

		mxCell v1;
		
		model = graph.getModel();
		
		graph.getModel().beginUpdate();
		try {
			
			 v1 = (mxCell) graph.insertVertex(parent, null, "", 20, 20, 200,200 );
			
			graph.cellSizeUpdated(v1, false);
			
		} finally {
			graph.getModel().endUpdate();
		}

		setLayout(new BorderLayout());
		
		add(graphComponent, BorderLayout.CENTER);
		
		
	}
	
	
}
