package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;

public class Association implements Serializable {
	private static final long serialVersionUID = -1907106917153875707L;

	public enum AssociationType {
		AGGREGATION, COMPOSITION, NONE;
		public static AssociationType fromString(String value) {
			if ("none".equals(value)) {
				return NONE;
			} else if ("aggregate".equals(value)) {
				return AGGREGATION;
			} else {
				return COMPOSITION;
			}
		}
	};

	public enum Cardinality {
		O_ONE, O_N, ONE_ONE, ONE_N, M_N, NONE;
		public static Cardinality get(Integer a, Integer b) {
			if (a == null && b == null) {
				return NONE;
			} else if (a == 0 && b == 1) {
				return O_ONE;
			} else if (a == 0 && b == -1) {
				return O_N;
			} else if (a == 1 && b == 1) {
				return ONE_ONE;
			} else if (a == 1 && b == -1) {
				return ONE_N;
			} else {
				return M_N;
			}
		}
	};

	private TypeElement from;
	private TypeElement to;
	private AssociationType type = AssociationType.NONE;
	private Cardinality cardinalityFrom;
	private Cardinality cardinalityTo;
	private String name;

	public Association() {

	}

	public TypeElement getFrom() {
		return from;
	}

	public void setFrom(TypeElement from) {
		this.from = from;
	}

	public TypeElement getTo() {
		return to;
	}

	public void setTo(TypeElement to) {
		this.to = to;
	}

	public AssociationType getType() {
		return type;
	}

	public void setType(AssociationType type) {
		this.type = type;
	}

	public Cardinality getCardinalityFrom() {
		return cardinalityFrom;
	}

	public void setCardinalityFrom(Cardinality cardinalityFrom) {
		this.cardinalityFrom = cardinalityFrom;
	}

	public Cardinality getCardinalityTo() {
		return cardinalityTo;
	}

	public void setCardinalityTo(Cardinality cardinalityTo) {
		this.cardinalityTo = cardinalityTo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " " + type + " " + cardinalityFrom + " " + cardinalityTo;
	}
}
