package sk.tuke.fei.kpi.akAgent.integration.alignment;

import sk.tuke.fei.kpi.akAgent.integration.alignment.Aligner.AlignStrategy;

/**
 * Alignable interface.
 * Object implementing this interface can be compared to the other objects according to selected {@link AlignStrategy}. 
 */
public interface Alignable {
	/**
	 * Compares objects using specified align strategy.
	 * Object can have different types
	 * @param object object to compare
	 * @param alignStrategy align strategy,
	 * @return true if objects match together
	 */
	boolean matches(Object object, AlignStrategy alignStrategy);
}
