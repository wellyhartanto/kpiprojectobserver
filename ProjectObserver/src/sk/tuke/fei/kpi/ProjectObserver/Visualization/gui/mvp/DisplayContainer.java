package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp;

import java.util.List;


/**
 * Represents container for Component display
 * @author pepow
 *
 */
public interface DisplayContainer extends Display {
	
	/**
	 * Add display
	 * @param display
	 */
	void setDisplays(List<Display> displays);
}
