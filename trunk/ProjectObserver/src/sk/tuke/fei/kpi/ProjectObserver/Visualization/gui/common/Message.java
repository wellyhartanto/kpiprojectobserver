package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Message {

	JDialog popup;

	private final Timer timer = new Timer(3000, new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			popup.setVisible(false);
			timer.stop();
		}
	});

	public void pushMessage(String message, JComponent c) {
		JDialog error = new JDialog();
		popup = new JDialog(error);
		JLabel image = new JLabel();
//		image
//				.setIcon(new ImageIcon(
//						getClass()
//								.getResource(
//										"/de/softproject/werkstattclient/resources/images/Exception.png")));
		JLabel messageLabel = new JLabel(message + " ");
		popup.getContentPane().setLayout(new FlowLayout());
		popup.setUndecorated(true);
		popup.getContentPane().setBackground(new Color(243, 255, 159));
	//	popup.getContentPane().add(image);
		popup.getContentPane().add(messageLabel);
		popup.setFocusableWindowState(false);
		//c.setBackground(Color.PINK);
		popup.setSize(0, 0);
		popup.setLocationRelativeTo(c);
		Point point;
		point = popup.getLocation();
		Dimension cDim;
		cDim = c.getSize();
		popup.setLocation(point.x - (int) cDim.getWidth() / 2, point.y
				- (int) cDim.getHeight() / 2);
		popup.pack();
		popup.setVisible(true);
		if (!timer.isRunning()) {
			timer.start();
		}
	}

	public void deleteMessage() {
		if (popup != null) {
			popup.setVisible(false);
		}
	}
}