package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.MainFrame;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonColors;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;

public class ExportDialogView extends JDialog implements ExportDialogDisplay {

	private static final long serialVersionUID = -8274637867008423672L;
	private JRadioButton all;
	private JRadioButton missing;
	private JRadioButton extra;
	private JRadioButton alluml;

	private JButton okButton;
	private JButton cancelButton;

	final static String CANCEL_ACTION = "cancel-search";

	public ExportDialogView() {
		super(MainFrame.getMainFrame());

		initComponents();

		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		setLocationRelativeTo(MainFrame.getMainFrame());

		setVisible(true);
	}

	private void initComponents() {
		setResizable(false);
		
		all = new JRadioButton(Messages.getMessage("dialog.export.all"));
		extra = new JRadioButton(Messages.getMessage("dialog.export.extra"));
		missing = new JRadioButton(Messages.getMessage("dialog.export.missing"));
		alluml = new JRadioButton(Messages.getMessage("dialog.export.alluml"));
		ButtonGroup bg = new ButtonGroup();
		
		missing.setSelected(true);

		all.setFont(CommonFonts.getNormalTextFont());
		extra.setFont(CommonFonts.getNormalTextFont());
		missing.setFont(CommonFonts.getNormalTextFont());
		alluml.setFont(CommonFonts.getNormalTextFont());
		
		bg.add(missing);
		bg.add(extra);
		bg.add(all);
		bg.add(alluml);

		okButton = new JButton(Messages.getMessage("loginpanel.buttons.export"));
		cancelButton = new JButton(Messages.getMessage("dialog.cancel"));
		okButton.setMinimumSize(new Dimension(75, okButton.getHeight()));
		cancelButton.setMinimumSize(okButton.getPreferredSize());
		
		okButton.setFont(CommonFonts.getNormalTextFont());
		cancelButton.setFont(CommonFonts.getNormalTextFont());
		
		getRootPane().setDefaultButton(okButton);

		// setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setTitle(Messages.getMessage("dialog.export.title"));

		setLayout(new MigLayout("fill,insets 5", "20[][]20", "20[][][][]30[]20"));
		getContentPane().setBackground(CommonColors.LOGINPANEL_COLOR_TO);
		
		
		add(missing, "span,wrap");
		add(extra, "span,wrap");
		add(all, "span,wrap");
		add(alluml, "span,wrap");

		add(okButton, "span,split 2,align right");
		add(cancelButton, "align right");

		pack();

	}

	class CancelAction extends AbstractAction {
		public void actionPerformed(ActionEvent ev) {
			setVisible(false);
		}
	}

	@Override
	public JComponent asComponent() {
		return this.asComponent();
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
	public int getSelectedExportType() {

		if (missing.isSelected()) {
			return 1;
		}
		if (extra.isSelected()) {
			return 2;
		}
		if (all.isSelected()) {
			return 3;
		}
		if (alluml.isSelected()) {
			return 4;
		}
		return 0;
	}

}
