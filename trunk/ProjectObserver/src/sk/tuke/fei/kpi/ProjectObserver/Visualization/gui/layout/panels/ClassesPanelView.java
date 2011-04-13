package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.ZebraJTable;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.MethodCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.MyTableCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.ClassesTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;

public class ClassesPanelView extends JPanel implements ClassesPanelDisplay {

	private static final long serialVersionUID = 7823088581581224167L;

	private JTable classesTable;
	private ClassesTableModel classesTableModel;
	
	public ClassesPanelView(List<Class> classes) {
		setLayout(new MigLayout("fill,insets 0"));
		add(new JScrollPane(createClassesTable(classes)), "grow");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JTable createClassesTable(List<Class> classes) {

		classesTableModel = new ClassesTableModel();
		classesTableModel.setData(classes);

	//	classesTable = new JXTable(classesTableModel);
		
		classesTable =(JTable) new ZebraJTable(classesTableModel);
		
		
		classesTable.getTableHeader().setFont(CommonFonts.tahoma14);
//		classesTable.setRolloverEnabled(true);
//		classesTable.setHorizontalScrollEnabled(true);
		classesTable.setFillsViewportHeight(true);
//		classesTable.setEditable(true);
		classesTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
		classesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// SelectionListener listener = new SelectionListener(classesTable);
		// classesTable.getSelectionModel().addListSelectionListener(listener);
		// classesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

		classesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		return classesTable;
	}

	@Override
	public void setMouseListener(MouseListener l) {
		classesTable.addMouseListener(l);
	}
	@Override
	public JTable getTable() {
		return classesTable;

	};
	@Override
	public void setData(List<Class> classes) {
		classesTableModel.setData(classes);	
		classesTableModel.fireTableDataChanged();
	}
	
}
