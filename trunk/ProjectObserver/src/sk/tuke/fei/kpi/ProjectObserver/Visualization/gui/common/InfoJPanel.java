package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class InfoJPanel extends JPanel{

	public InfoJPanel() {
		// TODO Auto-generated constructor stub
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		// Paint a gradient from top to bottom
		GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter(), 0, h, CommonColors.TABLE_ROW);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}
	
	
}
