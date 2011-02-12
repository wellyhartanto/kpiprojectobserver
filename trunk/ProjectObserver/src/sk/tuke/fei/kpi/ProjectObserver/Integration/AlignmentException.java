package sk.tuke.fei.kpi.ProjectObserver.Integration;

import java.io.Serializable;

public class AlignmentException extends Exception implements Serializable {
	private static final long serialVersionUID = 2023274462589193419L;

	public AlignmentException() {
		super();
	}

	public AlignmentException(String message) {
		super(message);
	}

	public AlignmentException(Throwable cause) {
		super(cause);
	}

	public AlignmentException(String message, Throwable cause) {
		super(message, cause);
	}
}
