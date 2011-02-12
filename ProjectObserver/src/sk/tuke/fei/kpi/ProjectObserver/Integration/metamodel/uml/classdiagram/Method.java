package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

public class Method extends Element {
	private static final long serialVersionUID = 7860060186187411059L;
	private String returnType;
	private String[] exceptions;
	private Param[] params;

	public Method() {
		super();
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String[] getExceptions() {
		return exceptions;
	}

	public void setExceptions(String[] exceptions) {
		this.exceptions = exceptions;
	}

	public Param[] getParams() {
		return params;
	}

	public void setParams(Param[] params) {
		this.params = params;
	}
}