package sk.tuke.fei.kpi.ProjectObserver.Integration.parser;

import java.io.Serializable;

/**
 * Parser exception. It signalizes unexpected or error state which can occur in parsing process.
 */
public class ParserException extends Exception implements Serializable {
	private static final long serialVersionUID = -8921943858445236089L;
	/**
	 * Constructor.
	 */
	public ParserException() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param message description of exception
	 */
	public ParserException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 * @param cause cause of exception
	 */
	public ParserException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor.
	 * @param message description of exception
	 * @param cause cause of exception
	 */
	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
