package sk.tuke.fei.kpi.akAgent.integration.metamodel.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.akAgent.integration.alignment.Alignable;
import sk.tuke.fei.kpi.akAgent.integration.alignment.Aligner.AlignStrategy;

/**
 * Java Elements.
 */
public abstract class Element implements Serializable, Alignable, Comparable<Element> {
	private static final long serialVersionUID = -6146256352210642770L;

	/**
	 * Java visibility modifier
	 */
	public enum Visibility {
		/**
		 * Private visibility.
		 */
		PRIVATE,
		/**
		 * Default visibility.
		 */
		DEFAULT, 
		/**
		 * Protected visibility.
		 */
		PROTECTED, 
		/**
		 * Public visibility.
		 */
		PUBLIC;

		/**
		 * Creates visibility from string value. 
		 * @param string lowercase value
		 * @return visibility
		 */
		public static Visibility fromString(String string) {
			if ("public".equals(string)) {
				return PUBLIC;
			} else if ("protected".equals(string)) {
				return PROTECTED;
			} else if ("private".equals(string)) {
				return PRIVATE;
			} else {
				return DEFAULT;
			}
		}

		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	};

	/**
	 * Java modifiers.
	 */
	public enum Modifiers {
		/**
		 * Final.
		 */
		FINAL,
		/**
		 * Abstract.
		 */
		ABSTRACT,
		/**
		 * Static.
		 */
		STATIC, 
		/**
		 * Native.
		 */
		NATIVE,
		/**
		 * Transient.
		 */
		TRANSIENT,
		/**
		 * Volatile.
		 */
		VOLATILE, 
		/**
		 * Synchronized.
		 */
		SYNCHRONIZED, 
		/**
		 * Strictfp.
		 */
		STRICTFP,
		/**
		 * Unknown. It is not java modifier. It is return if any modifier cannot be parsed from string value.
		 */
		UNKNOWN;

		/**
		 * Creates modifier from string value. 
		 * @param value lowercase value.
		 * @return Modifier
		 */
		public static Modifiers fromString(String value) {
			if ("final".equals(value)) {
				return FINAL;
			} else if ("static".equals(value)) {
				return STATIC;
			} else if ("abstract".equals(value)) {
				return ABSTRACT;
			} else if ("native".equals(value)) {
				return NATIVE;
			} else if ("volatile".equals(value)) {
				return VOLATILE;
			} else if ("synchronized".equals(value)) {
				return SYNCHRONIZED;
			} else if ("strictfp".equals(value)) {
				return STRICTFP;
			} else {
				return UNKNOWN;
			}
		}
	};

	private String name;
	private String fullName;
	private Visibility visibility = Visibility.DEFAULT;
	private List<Modifiers> modifiers;
	private Element parent;

	/**
	 * Constructor.
	 */
	public Element() {
		modifiers = new ArrayList<Modifiers>();
	}

	/**
	 * Gets name.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets Visibility.
	 * @return visibility
	 */
	public Visibility getVisibility() {
		return visibility;
	}

	/**
	 * Sets visibility.
	 * @param visibility visibility
	 */
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	/**
	 * Gets element's modifiers.
	 * @return list of modifiers.
	 */
	public List<Modifiers> getModifiers() {
		return modifiers;
	}

	/**
	 * Sets element's modifiers.
	 * @param modifiers modifiers
	 */
	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}

	/**
	 * Gets parent element
	 * @return parent
	 */
	public Element getParent() {
		return parent;
	}

	/**
	 * Sets parent element.
	 * @param parent parent
	 */
	public void setParent(Element parent) {
		this.parent = parent;
	}

	/**
	 * Gets full name.
	 * @return full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets fullname.
	 * @param fullName full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Gets fully qualified name.
	 * It includes fully qualified name of parent.
	 * @return
	 */
	public String getFullyQualifiedName() {
		return parent == null ? name : parent.getFullyQualifiedName() + "." + name;
	}
	
	@Override
	public boolean matches(Object object, AlignStrategy alignStrategy) {
		if (object == null) {
			return false;
		}
		if (object instanceof sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Element) {
			sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Element element = (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Element) object;
			switch (alignStrategy) {
			case EXACT:
				return getFullyQualifiedName().equalsIgnoreCase(element.getFullyQualifiedName());
			case IGNORE_PACKAGE:
				return getName().equalsIgnoreCase(element.getName());
			case MANUAL:
				return false;
			default:
				throw new IllegalStateException("Align strategy " + alignStrategy + "is not supported ");
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(Element o) {
		if(name==null)
			return -1;
		if(o.name == null){
			return 1;
		}
		return name.compareTo(o.name);
	}
}
