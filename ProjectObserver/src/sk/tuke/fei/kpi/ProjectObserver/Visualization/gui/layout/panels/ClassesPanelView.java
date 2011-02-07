package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.ClassesTableModel;

public class ClassesPanelView extends JPanel implements ClassesPanelDisplay {

	private static final long serialVersionUID = 7823088581581224167L;

	public ClassesPanelView(List<Class> classes) {
		setLayout(new MigLayout("fillx"));
		add(new JScrollPane(createClassesTable(classes)), "growx");

	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JXTable createClassesTable(List<Class> classes) {

		ClassesTableModel classesTableModel = new ClassesTableModel();
		classesTableModel.setData(classes);

		JXTable classesTable = new JXTable(classesTableModel);
		classesTable.getTableHeader().setFont(MyFonts.font3);
		classesTable.setRolloverEnabled(true);
		classesTable.setHorizontalScrollEnabled(true);
		classesTable.setFillsViewportHeight(true);
		classesTable.setEditable(true);

		classesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(classesTable);
		classesTable.getSelectionModel().addListSelectionListener(listener);
		classesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

		classesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, classesTable);
			}
		});

		return classesTable;
	}
}
