package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities;

/**
 * Class diagram comment.
 * It is based on {@link XmiElement}
 */
public class Comment extends XmiElement {
	private String parentId;
	/**
	 * Constructor
	 * @param name name of element
	 * @param xmiId xmi id
	 */	
	public Comment(String name, String xmiId) {
		super(name, xmiId);
	}
	/**
	 * Sets parent id.
	 * @param parentId identifier of xmi element.
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets parent id.
	 * Identifier of element which is described by comment.
	 * @return xmi id of parent element
	 */
	public String getParentId() {
		return parentId;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + parentId;
	}
}
