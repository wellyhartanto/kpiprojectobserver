package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.util.ArrayList;
import java.util.List;

public class Method extends Element {
	private static final long serialVersionUID = 7860060186187411059L;
	private String returnType;
	private String[] exceptions;
	private List<Param> params;

	public Method() {
		super();
		params = new ArrayList<Param>();
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

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return parent + "." + name;
	}

	@Override
	public String getFullyQualifiedName() {
		return parent == null ? name : parent.getFullyQualifiedName() + "." + name;
	}
}