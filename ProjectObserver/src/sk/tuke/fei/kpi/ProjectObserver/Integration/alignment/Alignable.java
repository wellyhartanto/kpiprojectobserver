package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;

/**
 * Alignable interface.
 * Object implementing this interface can be compared to the other objects according to select Alignment strategy. 
 */
public interface Alignable {
	/**
	 * Compares objects using specified align strategy.
	 * Object can have different types
	 * @param object
	 * @param alignStrategy align strategy,
	 * @return true if object match together
	 */
	boolean matches(Object object, AlignStrategy alignStrategy);
}
