package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;


public class Method extends BehavioralElement {
	private static final long serialVersionUID = 2015355208730487925L;
	private String returnType;

	public Method() {
		super();
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
}
