package sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram;

import java.io.Serializable;

/**
 * Parameter of {@link Method} or {@link  Constructor}.
 */
public class Param implements Serializable {
	private static final long serialVersionUID = -6610602685289634372L;
	private String name;
	private String type;

	/**
	 * Constructor.
	 */
	public Param() {
	}
	
	/**
	 * Constructor.
	 * @param name parameter name
	 * @param type name of parameter's data type
	 */
	public Param(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Gets name of parameter.
	 * @return name of parameter
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of parameter.
	 * @param name parameter name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets data type of parameter.
	 * @return name of data type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets data type of parameter.
	 * @param type name of data type.
	 */
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type + " " + name;
	}
}
