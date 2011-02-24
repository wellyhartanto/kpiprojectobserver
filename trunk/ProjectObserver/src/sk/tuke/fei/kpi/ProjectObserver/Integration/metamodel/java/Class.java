package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.io.Serializable;

public class Class extends TypeElement implements Serializable {
	private static final long serialVersionUID = 1547116046011137373L;
	private boolean enumClass;
	public Class() {
		super();
	}
	
	public boolean isEnumClass() {
		return enumClass;
	}
	
	public void setEnumClass(boolean enumClass) {
		this.enumClass = enumClass;
	}
}
