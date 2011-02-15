package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities;

public class Comment extends XmiElement {
	private String parentId;

	public Comment(String name, String xmiId) {
		super(name, xmiId);
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return parentId;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + parentId;
	}
}
