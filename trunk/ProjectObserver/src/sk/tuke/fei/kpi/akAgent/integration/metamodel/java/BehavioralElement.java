package sk.tuke.fei.kpi.akAgent.integration.metamodel.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Behavioral element. Elements that are modeling behaviour of application ({@link Method}, {@link Constructor})
 */
public class BehavioralElement extends Element {
	private static final long serialVersionUID = 1839415764070198555L;
	private List<String> exceptions;
	private List<Param> params;
	
	/**
	 * Constructor.
	 */
	public BehavioralElement() {
		super();
		exceptions = new ArrayList<String>();
	}

	/**
	 * Gets exceptions list.
	 * @return list of exception
	 */
	public List<String> getExceptions() {
		return exceptions;
	}

	/**
	 * Set exceptions list.
	 * @param exceptions
	 */
	public void setExceptions(List<String> exceptions) {
		this.exceptions = exceptions;
	}

	/**
	 * Gets {@link Param}s list.
	 * @return list of params.
	 */
	public List<Param> getParams() {
		return params;
	}

	/**
	 * Sets {@link Param}s list
	 * @param params list of param.
	 */
	public void setParams(List<Param> params) {
		this.params = params;
	}
}
