package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.util.ArrayList;
import java.util.List;

public class BehavioralElement extends Element {
	private static final long serialVersionUID = 1839415764070198555L;
	private List<String> exceptions;
	private List<Param> params;
	private List<Method> callsMethods;
	
	public BehavioralElement() {
		super();
		exceptions = new ArrayList<String>();
		params = new ArrayList<Param>();
		callsMethods = new ArrayList<Method>();
	}

	public List<String> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<String> exceptions) {
		this.exceptions = exceptions;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

	public List<Method> getCallsMethods() {
		return callsMethods;
	}

	public void setCallsMethods(List<Method> callsMethods) {
		this.callsMethods = callsMethods;
	}

}
