package sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram;

/**
 * Field of {@link Class} or {@link Interface}. 
 */
public class Field extends Element {
	private static final long serialVersionUID = -8910247458912757575L;
	private String type;
	private String defaultValue;
	private boolean isArray;
	private Integer size;

	/**
	 * Constructor.
	 */
	public Field() {
		super();
	}

	/**
	 * States whether this field is array type.
	 * @return true if field is array
	 */
	public boolean isArray() {
		return isArray;
	}

	/**
	 * Sets flag which signalizes that this field has array type.
	 * @param isArray flag
	 */
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}

	/**
	 * Gets size of array
	 * @return size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * Sets size of array
	 * @param size size
	 */
	public void setSize(Integer size) {
		this.size = size;
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

	@Override
	public String toString() {
		return visibility + " " + modifiers + " " + type + " " + name + " = " + defaultValue;
	}

	/**
	 * Sets field's default value.
	 * @param defaultValue value
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Gets default value of field.
	 * @return default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
}
