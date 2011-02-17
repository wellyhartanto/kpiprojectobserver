package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities;

public class XmiElement {

	String name;
	String xmiId;
	
	public XmiElement(String xmiId) {
		this(null,xmiId);
	}

	public XmiElement(String name, String xmiId) {
		this.name = name;
		this.xmiId = xmiId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXmiId() {
		return xmiId;
	}

	public void setXmiId(String xmiId) {
		this.xmiId = xmiId;
	}

	@Override
	public String toString() {
		return name + ", " + xmiId;
	}
}
