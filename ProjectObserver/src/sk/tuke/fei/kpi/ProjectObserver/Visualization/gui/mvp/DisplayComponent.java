package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp;

import javax.swing.JComponent;

/**
 * Component display 
 * @author pepow
 *
 */
public interface DisplayComponent extends Display {
	
	/**
	 * Returns as component. Can returns itself, or other instance 
	 * what represents widget 
	 * @return
	 */
	JComponent asComponent();
	
}
