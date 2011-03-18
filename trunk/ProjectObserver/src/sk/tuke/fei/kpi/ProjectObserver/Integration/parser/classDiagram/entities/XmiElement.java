package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities;

/**
 * XMI Element abstraction. 
 */
public class XmiElement {

	String name;
	String xmiId;
	
	/**
	 * Constructor.
	 * @param xmiId xmi id
	 */
	public XmiElement(String xmiId) {
		this(null,xmiId);
	}

	/**
	 * Constructor
	 * @param name name of element
	 * @param xmiId xmi id
	 */
	public XmiElement(String name, String xmiId) {
		this.name = name;
		this.xmiId = xmiId;
	}

	/**
	 * Gets name of xmi element
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of xmi elemnet
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets element id.
	 * @return id
	 */
	public String getXmiId() {
		return xmiId;
	}

	/**
	 * Sets element id.
	 * @param xmiId id
	 */
	public void setXmiId(String xmiId) {
		this.xmiId = xmiId;
	}

	@Override
	public String toString() {
		return name + ", " + xmiId;
	}
}
