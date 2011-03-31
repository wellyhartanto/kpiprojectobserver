package sk.tuke.fei.kpi.akAgent.integration.metamodel.java;

/**
 * Parameter of {@link Method} or {@link  Constructor}.
 */
public class Param extends Element {
	private static final long serialVersionUID = -4982603891161171221L;
	private String type;
	private int order;

	/**
	 * Constructor.
	 */
	public Param() {
		super();
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

	/**
	 * Sets order number.
	 * @param order number
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	
	/**
	 * Gets order number in list of parameters.
	 * @return number
	 */
	public int getOrder() {
		return order;
	}

	@Override
	public String toString() {
		return type + " " + getName();
	}

	@Override
	public int compareTo(Element o) {
		if (!(o instanceof Param)) {
			return 1;
		}
		return new Integer(order).compareTo(((Param) o).order);
	}
}
