package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

public class Method extends Element {
    private String returnType;
    private String[] exceptions = {};
    private Param[] params = {};
    private Method[] callsMethods = {};

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

    public Method[] getCallsMethods() {
	return callsMethods;
    }

    public void setCallsMethods(Method[] callsMethods) {
	this.callsMethods = callsMethods;
    }

}