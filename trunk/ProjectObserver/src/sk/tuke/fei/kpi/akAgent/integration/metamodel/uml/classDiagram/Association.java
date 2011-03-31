package sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram;

import java.io.Serializable;

/**
 * Models various types associations between elements.
 */
public class Association implements Serializable {
	private static final long serialVersionUID = -1907106917153875707L;

	/**
	 * Association types.
	 */
	public enum AssociationType {
		/**
		 * Aggregation
		 */
		AGGREGATION, 
		/**
		 * Composition
		 */
		COMPOSITION, 
		/**
		 * Unknown.
		 */
		UNKNOWN;
		public static AssociationType fromString(String value) {
			if ("none".equals(value)) {
				return UNKNOWN;
			} else if ("aggregate".equals(value)) {
				return AGGREGATION;
			} else {
				return COMPOSITION;
			}
		}
	};

	/**
	 * Cardinality types.
	 */
	public enum Cardinality {
		/**
		 * Cardinality 0..1
		 */
		O_ONE, 
		/**
		 * Cardinality 0..*
		 */
		O_N, 
		/**
		 * Cardinality 1..1
		 */
		ONE_ONE, 
		/**
		 * Cardinality 1..*
		 */
		ONE_N, 
		/**
		 * Cardinality m..n
		 */
		M_N, 
		/**
		 * Unknown cardinality
		 */
		UNKNOWN;
		/**
		 * Creates cardinality from integer values.
		 * @param a cardinality of start element
		 * @param b cardinality of end element
		 * @return cardinality
		 */
		public static Cardinality get(Integer a, Integer b) {
			if (a == null && b == null) {
				return UNKNOWN;
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
	private AssociationType type = AssociationType.UNKNOWN;
	private Cardinality cardinalityFrom;
	private Cardinality cardinalityTo;
	private String name;

	/**
	 * COnstructor.
	 */
	public Association() {

	}

	/**
	 * Gets start element of association.
	 * @return start element
	 */
	public TypeElement getFrom() {
		return from;
	}

	/**
	 * Sets start element of association.
	 * @param from start element
	 */
	public void setFrom(TypeElement from) {
		this.from = from;
	}
	/**
	 * Gets end element of association.
	 * @return end element
	 */
	public TypeElement getTo() {
		return to;
	}

	/**
	 * Sets end element of association.
	 * @param to end element
	 */
	public void setTo(TypeElement to) {
		this.to = to;
	}

	/**
	 * Gets type of association.
	 * @return association type
	 */
	public AssociationType getType() {
		return type;
	}

	/**
	 * Sets association type.
	 * @param type association type
	 */
	public void setType(AssociationType type) {
		this.type = type;
	}

	/**
	 * Gets cardinality of start element.
	 * @return cardinality of start element
	 */
	public Cardinality getCardinalityFrom() {
		return cardinalityFrom;
	}

	/**
	 *  Sets cardinality of start element.
	 * @param cardinalityFrom cardinality
	 */
	public void setCardinalityFrom(Cardinality cardinalityFrom) {
		this.cardinalityFrom = cardinalityFrom;
	}

	/**
	 * Gets cardinality of end element.
	 * @return cardinality of end element
	 */
	public Cardinality getCardinalityTo() {
		return cardinalityTo;
	}

	/**
	 * Sets cardinality of end element.
	 * @param cardinalityTo cardinality
	 */
	public void setCardinalityTo(Cardinality cardinalityTo) {
		this.cardinalityTo = cardinalityTo;
	}

	/**
	 * Sets association's name. 
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets association's name. 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + " " + type + " " + cardinalityFrom + " " + cardinalityTo;
	}
}
