package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

/**
 * Enumeration class (JAVA).
 */
public class Enum extends TypeElement {
	private static final long serialVersionUID = 1184099714623574598L;
	private String[] values = {};

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
