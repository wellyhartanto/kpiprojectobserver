package sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram;

/**
 * Enumeration class (UML).
 */
public class Enum extends Element {
	private static final long serialVersionUID = -7656030684090007523L;
	private String[] values;

	/**
	 * Enumeration constructor.
	 */
	public Enum() {
		super();
	}

	/**
	 * Sets values of enumeration
	 * @param values values
	 */
	public void setValues(String[] values) {
		this.values = values;
	}

	/**
	 * Gets values of enumeration.
	 * @return enumeration values
	 */
	public String[] getValues() {
		return values;
	}

}
