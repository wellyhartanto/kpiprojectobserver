package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

/**
 * 
 * @author Martin Vandzura
 * 
 */
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXBusyLabel;

public class ProgressDialog extends JDialog {
    private static final long serialVersionUID = 1793629826990876613L;
    private boolean modal = false;

    public ProgressDialog(String title, String message, Frame frame) {
	super(frame);
	super.setResizable(false);
	super.setTitle(title);
	super.setLayout(new MigLayout("", "[150::]"));
	getContentPane().setBackground(CommonColors.LOGINPANEL_COLOR_TO);
	
	
	JXBusyLabel label = new JXBusyLabel(new Dimension(32, 32));
	label.setToolTipText("");
	label.setBusy(true);
	JLabel messa = new JLabel(message);
	messa.setFont(CommonFonts.getNormalTextFont());
	add(messa, "center,wrap");
	add(label, "center,wrap");
	pack();
	setLocationRelativeTo(getOwner());

    }

    public boolean isModal() {
	return modal;
    }

    public void setModal(boolean modal) {
	this.modal = modal;
    }

}
