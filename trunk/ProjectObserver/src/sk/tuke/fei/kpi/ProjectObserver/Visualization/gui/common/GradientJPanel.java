package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class GradientJPanel extends JPanel{
	final Color OS_X_UNIFIED_TOOLBAR_FOCUSED_BOTTOM_COLOR = new Color(64, 64, 64);
	final Color OS_X_UNIFIED_TOOLBAR_UNFOCUSED_BORDER_COLOR = new Color(135, 135, 135);
	
	private boolean topOrDown = true;
	
	public GradientJPanel(boolean topOrDown) {
		this.topOrDown =topOrDown;
		setBackground(CommonColors.MAIN_BACKGROUND_COLOR);
	}
	

	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		// Paint a gradient from top to bottom
		GradientPaint gp=null;
		if(topOrDown){
			gp = new GradientPaint(0, 0, getBackground(), 0, h, getBackground().darker());
		}else{
			gp = new GradientPaint(0,h, getBackground(), 0,0, getBackground().darker());
		}
		 
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}

//	@Override
//	public Border getBorder() {
//		Window window = SwingUtilities.getWindowAncestor(this);
//		return window != null && window.isFocused() ? BorderFactory.createMatteBorder(0, 0, 1, 0, OS_X_UNIFIED_TOOLBAR_FOCUSED_BOTTOM_COLOR)
//				: BorderFactory.createMatteBorder(0, 0, 1, 0, OS_X_UNIFIED_TOOLBAR_UNFOCUSED_BORDER_COLOR);
//	}


	
}
