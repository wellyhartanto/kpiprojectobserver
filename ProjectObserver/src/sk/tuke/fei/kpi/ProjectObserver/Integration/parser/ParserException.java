package sk.tuke.fei.kpi.ProjectObserver.Integration.parser;

import java.io.Serializable;

public class ParserException extends Exception implements Serializable {
	private static final long serialVersionUID = -8921943858445236089L;

	public ParserException() {
		super();
	}

	public ParserException(String message) {
		super(message);
	}

	public ParserException(Throwable cause) {
		super(cause);
	}

	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
