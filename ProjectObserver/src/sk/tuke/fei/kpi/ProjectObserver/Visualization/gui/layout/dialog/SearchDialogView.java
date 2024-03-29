package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.MainFrame;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonColors;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.akAgent.integration.Project;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.TypeElement;

public class SearchDialogView extends JDialog implements DocumentListener, SearchDialogDisplay {

	private static final long serialVersionUID = -8274637867008423672L;
	private JTextField entry;
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JLabel status;

	private JButton okButton;
	private JButton cancelButton;

	final static Color HILIT_COLOR = Color.LIGHT_GRAY;
	final static Color ERROR_COLOR = Color.PINK;
	final static String CANCEL_ACTION = "cancel-search";

	final Color entryBg;
	final Highlighter hilit;
	final Highlighter.HighlightPainter painter;

	private JList list;
	private Project project;

	public SearchDialogView(Project project) {
		super(MainFrame.getMainFrame());

		this.project = project;
		initComponents();

		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		hilit = new DefaultHighlighter();
		painter = new DefaultHighlighter.DefaultHighlightPainter(HILIT_COLOR);
		entryBg = entry.getBackground();
		entry.getDocument().addDocumentListener(this);

		InputMap im = entry.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = entry.getActionMap();
		im.put(KeyStroke.getKeyStroke("ESCAPE"), CANCEL_ACTION);
		am.put(CANCEL_ACTION, new CancelAction());

		setLocationRelativeTo(MainFrame.getMainFrame());

		setVisible(true);
	}

	private void initComponents() {
		entry = new JTextField();
		status = new JLabel();
		jLabel1 = new JLabel();

		
		
		entry.setFont(CommonFonts.getNormalTextFont());
		status.setFont(CommonFonts.getNormalTextFont());
		jLabel1.setFont(CommonFonts.getNormalTextFont());

		okButton = new JButton(Messages.getMessage("dialog.ok"));
		cancelButton = new JButton(Messages.getMessage("dialog.cancel"));

		okButton.setFont(CommonFonts.getNormalTextFont());
		cancelButton.setFont(CommonFonts.getNormalTextFont());

		okButton.setMinimumSize(new Dimension(75, okButton.getHeight()));
		cancelButton.setMinimumSize(new Dimension(75, cancelButton.getHeight()));

		getRootPane().setDefaultButton(okButton);

		// setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setTitle(Messages.getMessage("dialog.search.title"));

		list = new JList();
		list.setFont(CommonFonts.getTableContentFont());

		jScrollPane1 = new JScrollPane(list);
		jLabel1.setText(Messages.getMessage("dialog.search.searchtext"));

		setLayout(new MigLayout("fill", "", "[growprio 50][growprio 50][][growprio 50]"));

		getContentPane().setBackground(CommonColors.LOGINPANEL_COLOR_TO);
		add(jLabel1, "growx,wrap");
		add(entry, "growx,wrap");

		JPanel panel3 = new JPanel(new MigLayout("fillx"));
		panel3.add(cancelButton, "align right");
		panel3.add(okButton, "align right");
		panel3.setBackground(CommonColors.LOGINPANEL_COLOR_TO);
		// add(panel1, "grow,wrap");
		add(jScrollPane1, "grow,wrap");
		add(status, "wrap");
		add(panel3, "span,align right");

		setSize(450, 350);

		// pack();

		search();

	}

	private void search() {
		String searchtext = entry.getText();
		List<TypeElement> li = project.getJavaModel().searchClasses(searchtext);

		this.list.setListData(li.toArray());
		if (this.list.getModel().getSize() > 0) {
			this.list.setSelectedIndex(0);
		}
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
			setVisible(false);
		}
	}

	@Override
	public JList getList() {
		return list;
	}

	@Override
	public void setListClickedAction(MouseListener m) {
		list.addMouseListener(m);
	}

	@Override
	public JComponent asComponent() {
		return this.asComponent();
	}

	@Override
	public void setEntryKeyAction(KeyListener l) {
		entry.addKeyListener(l);
	}

	@Override
	public void setStatus(String status) {
		this.status.setText(status);
		this.status.setMaximumSize(new Dimension(this.getSize().width-25,this.getSize().height));
		
		this.status.repaint();
	}

	@Override
	public void setListSelectionAction(ListSelectionListener l) {
		list.addListSelectionListener(l);
	}

	@Override
	public void setCancelAction(ActionListener l) {
		cancelButton.addActionListener(l);
	}

	@Override
	public void setOKAction(ActionListener l) {
		okButton.addActionListener(l);
	}

	@Override
	public void showDialog(boolean visible) {
		setVisible(visible);
	}

	@Override
	public void setListEnterAction(KeyListener l) {
		list.addKeyListener(l);
	}

}
