package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

public class Param extends Element {
	private static final long serialVersionUID = -4982603891161171221L;
	private String type;
	private int order;

	public Param() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}
}
