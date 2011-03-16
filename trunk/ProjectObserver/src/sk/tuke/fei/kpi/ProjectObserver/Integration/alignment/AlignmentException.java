package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import java.io.Serializable;

/**
 * Alignment exceptions. Signalizes unexpected state or error in process of alignment.
 */
public class AlignmentException extends Exception implements Serializable {
	private static final long serialVersionUID = 2023274462589193419L;

	/**
	 * Constructor.
	 */
	public AlignmentException() {
		super();
	}

	/**
	 * Constructor.
	 * @param message message of exception
	 */
	public AlignmentException(String message) {
		super(message);
	}
	
	/**
	 * Constructor.
	 * @param cause cause of exception.
	 */
	public AlignmentException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * @param message message of exception
	 * @param cause cause of exception
	 */
	public AlignmentException(String message, Throwable cause) {
		super(message, cause);
	}
}
