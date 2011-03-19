package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

/**
 * Field of {@link Class} or {@link Interface}. 
 */
public class Field extends Element {
	private static final long serialVersionUID = 105827013816997623L;
	private String type;

	/**
	 * Constructor.
	 */
	public Field() {
		super();
	}

	/**
	 * Sets data type of field.
	 * @param type name of datatype.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets datatype.
	 * @return name of data type.
	 */
	public String getType() {
		return type;
	}
}
