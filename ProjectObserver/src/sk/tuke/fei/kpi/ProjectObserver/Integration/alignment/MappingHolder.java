package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import java.io.Serializable;

public class MappingHolder implements Serializable {
	private static final long serialVersionUID = 1600573250608950944L;

	private Uml2JavaMapping uml2JavaMapping;

	private Java2UmlMapping java2UmlMapping;

	public MappingHolder() {
		uml2JavaMapping = new Uml2JavaMapping();
		java2UmlMapping = new Java2UmlMapping();
	}

	public Java2UmlMapping getJava2UmlMapping() {
		return java2UmlMapping;
	}

	public Uml2JavaMapping getUml2JavaMapping() {
		return uml2JavaMapping;
	}

	public void setUml2JavaMapping(Uml2JavaMapping uml2JavaMapping) {
		this.uml2JavaMapping = uml2JavaMapping;
	}

	public void setJava2UmlMapping(Java2UmlMapping java2UmlMapping) {
		this.java2UmlMapping = java2UmlMapping;
	}
}
