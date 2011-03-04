package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;


public class Param extends Element implements Comparable<Param> {
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
	
	@Override
	public String toString() {
		return type +" "+getName();
	}
	@Override
	public int compareTo(Param o) {
		return new Integer(order).compareTo(o.order);
	}
}
