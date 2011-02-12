package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;

public class Association implements Serializable {
	private static final long serialVersionUID = -1907106917153875707L;

	public enum AssociationType {
		AGGREGATION, ASSOCIATION, COMPOSITION, GENERALIZATION, IMPLEMENTATION
	};

	public enum Cardinality {
		ONE_ONE, ONE_N, N_ONE, M_N, NONE
	};

	private AssociationElement from;
	private AssociationElement to;
	private AssociationType type;
	private Cardinality cardinality;

	public Association() {
		// TODO Auto-generated constructor stub
	}

	public AssociationElement getFrom() {
		return from;
	}

	public void setFrom(AssociationElement from) {
		this.from = from;
	}

	public AssociationElement getTo() {
		return to;
	}

	public void setTo(AssociationElement to) {
		this.to = to;
	}

	public AssociationType getType() {
		return type;
	}

	public void setType(AssociationType type) {
		this.type = type;
	}

	public Cardinality getCardinality() {
		return cardinality;
	}

	public void setCardinality(Cardinality cardinality) {
		this.cardinality = cardinality;
	}
}
