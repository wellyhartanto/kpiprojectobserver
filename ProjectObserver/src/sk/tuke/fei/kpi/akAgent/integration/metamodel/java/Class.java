package sk.tuke.fei.kpi.akAgent.integration.metamodel.java;

import java.io.Serializable;

/**
 * Java class.
 */
public class Class extends TypeElement implements Serializable {
	private static final long serialVersionUID = 1547116046011137373L;
	private boolean enumClass;
	/**
	 * Constructor.
	 */
	public Class() {
		super();
	}
	
	/**
	 * States whether this class is Enum.
	 * @return true if it is Enum
	 */
	public boolean isEnumClass() {
		return enumClass;
	}
	/**
	 * Sets flag that this class is Enum.
	 * @param enumClass flag
	 */
	public void setEnumClass(boolean enumClass) {
		this.enumClass = enumClass;
	}
}
