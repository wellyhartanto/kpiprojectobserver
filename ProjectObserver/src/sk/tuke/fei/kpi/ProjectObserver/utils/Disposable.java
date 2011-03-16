package sk.tuke.fei.kpi.ProjectObserver.utils;
/**
 * Interface for objects which hold some resources, that must be released.
 *
 */
public interface Disposable {
	/**
	 * Releases all resources.
	 */
	void dispose();
}
