package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp;

/**
 * Presenter for MPV patter fot GUI 
 * @author pepow
 *
 */
public interface Presenter<D extends Display> {

	/**
	 * Called where presenter is binding to Display
	 */
	void bind();
	
	/**
	 * Called where presenter is unbinding to Display
	 */
	void unbind();
	
	/**
	 * Return associated Presenter 
	 * @return
	 */
	D getDisplay();
}
