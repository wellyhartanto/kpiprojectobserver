package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDocument;
import org.jdesktop.swingx.autocomplete.ListAdaptor;

import sk.tuke.fei.kpi.ProjectObserver.Integration.Project;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.TypeElement;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;

public class SearchDialog extends JDialog implements DocumentListener {

	private static final long serialVersionUID = -8274637867008423672L;
	private JTextField entry;
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JLabel status;

	final static Color HILIT_COLOR = Color.LIGHT_GRAY;
	final static Color ERROR_COLOR = Color.PINK;
	final static String CANCEL_ACTION = "cancel-search";

	final Color entryBg;
	final Highlighter hilit;
	final Highlighter.HighlightPainter painter;

	private JList list;
	private Project project;

	public SearchDialog(Project project) {
		this.project = project;
		initComponents();

		hilit = new DefaultHighlighter();
		painter = new DefaultHighlighter.DefaultHighlightPainter(HILIT_COLOR);

		entryBg = entry.getBackground();
		entry.getDocument().addDocumentListener(this);

		InputMap im = entry.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = entry.getActionMap();
		im.put(KeyStroke.getKeyStroke("ESCAPE"), CANCEL_ACTION);
		am.put(CANCEL_ACTION, new CancelAction());
	}

	private void initComponents() {
		entry = new JTextField();
		status = new JLabel();
		jLabel1 = new JLabel();

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setTitle(Messages.getMessage("dialog.search.title"));

		
		list = new JList();

		jScrollPane1 = new JScrollPane(list);

		jLabel1.setText(Messages.getMessage("dialog.search.searchtext"));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		// Create a parallel group for the horizontal axis
		ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);

		// Create a sequential and a parallel groups
		SequentialGroup h1 = layout.createSequentialGroup();
		ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);

		// Add a container gap to the sequential group h1
		h1.addContainerGap();

		// Add a scroll pane and a label to the parallel group h2
		h2.addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
		h2.addComponent(status, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);

		// Create a sequential group h3
		SequentialGroup h3 = layout.createSequentialGroup();
		h3.addComponent(jLabel1);
		h3.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		h3.addComponent(entry, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE);

		// Add the group h3 to the group h2
		h2.addGroup(h3);
		// Add the group h2 to the group h1
		h1.addGroup(h2);

		h1.addContainerGap();

		// Add the group h1 to the hGroup
		hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
		// Create the horizontal group
		layout.setHorizontalGroup(hGroup);

		// Create a parallel group for the vertical axis
		ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		// Create a sequential group v1
		SequentialGroup v1 = layout.createSequentialGroup();
		// Add a container gap to the sequential group v1
		v1.addContainerGap();
		// Create a parallel group v2
		ParallelGroup v2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
		v2.addComponent(jLabel1);
		v2.addComponent(entry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
		// Add the group v2 tp the group v1
		v1.addGroup(v2);
		v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
		v1.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE);
		v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
		v1.addComponent(status);
		v1.addContainerGap();

		// Add the group v1 to the group vGroup
		vGroup.addGroup(v1);
		// Create the vertical group
		layout.setVerticalGroup(vGroup);
		pack();

		entry.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					list.requestFocus();
				}
			}
		});

		search();

	}

	public void search() {
		String searchtext = entry.getText();
		List<TypeElement> li = project.getJavaModel().searchClasses(searchtext);
		this.list.setListData(li.toArray());
		if (this.list.getModel().getSize() > 0) {
			this.list.setSelectedIndex(0);
		}
	}

	void message(String msg) {
		status.setText(msg);
	}

	// DocumentListener methods

	public void insertUpdate(DocumentEvent ev) {
		search();
	}

	public void removeUpdate(DocumentEvent ev) {
		search();
	}

	public void changedUpdate(DocumentEvent ev) {
		search();
	}

	class CancelAction extends AbstractAction {
		public void actionPerformed(ActionEvent ev) {
			entry.setText("");
			entry.setBackground(entryBg);
		}
	}
}
