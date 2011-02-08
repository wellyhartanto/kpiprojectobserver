package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxMultiplicity;  

public class ClassPanel extends JPanel {
	
	public ClassPanel() {

		
		Document xmlDocument = mxUtils.createDocument();
		Element sourceNode = xmlDocument.createElement("Source");
		

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		
		try
		{
			Object v1 = graph.insertVertex(parent, null, sourceNode, 20, 20,
					80, 30);
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph); 
		add(graphComponent);  


	}
	
}
