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
	
	public void addPackagePair(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package p1,
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package p2){
		getJava2UmlMapping().addPackagePair(p2.getFullyQualifiedName(), p1);
		getUml2JavaMapping().addPackagePair(p1.getFullyQualifiedName(), p2);
	}
	
	public void addClassPair(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class c1,
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class c2){
		getJava2UmlMapping().addClassPair(c2.getFullyQualifiedName(), c1);
		getUml2JavaMapping().addClassPair(c1.getFullyQualifiedName(), c2);
	}
	
	public void addInterfacePair(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface i1,
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface i2){
		getJava2UmlMapping().addInterfacePair(i2.getFullyQualifiedName(), i1);
		getUml2JavaMapping().addInterfacePair(i1.getFullyQualifiedName(), i2);
	}
}
