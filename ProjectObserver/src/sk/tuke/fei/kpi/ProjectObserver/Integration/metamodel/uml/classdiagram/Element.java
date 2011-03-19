package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;
import java.util.List;

/**
 * Class diagram elements.
 */
public abstract class Element implements Serializable, Comparable<Element> {
	private static final long serialVersionUID = 2140263151359000290L;

	/**
	 * Class diagram element's visibility modifier.
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
		 * @param value lowercase value
		 * @return visibility
		 */
		public static Visibility fromString(String value) {
			if ("private".equalsIgnoreCase(value)) {
				return PRIVATE;
			} else if ("public".equalsIgnoreCase(value)) {
				return PUBLIC;
			} else if ("protected".equalsIgnoreCase(value)) {
				return PROTECTED;
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
	 * Class diagram element's modifier.
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
		 * None. It is not  modifier. It is return if any modifier cannot be parsed from string value.
		 */
		UNKNOWN;

		public static Modifiers fromString(String value) {
			if ("".equals(value)) {
				return FINAL;
			} else if ("".equals(value)) {
				return STATIC;
			} else if ("".equals(value)) {
				return ABSTRACT;
			} else {
				return UNKNOWN;
			}
		}
	};

	String name;
	String xmiId;
	Visibility visibility = Visibility.DEFAULT;
	List<Modifiers> modifiers;
	Element parent;

	/**
	 * Constructor.
	 */
	public Element() {
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
	 * Sets XMI id.
	 * Transient property.
	 * @param xmiId
	 */
	public void setXmiId(String xmiId) {
		this.xmiId = xmiId;
	}

	/**
	 * Gets XMI id.
	 * Transient property.
	 * @return xmi id
	 */
	public String getXmiId() {
		return xmiId;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * Sets parent element.
	 * @param parent parent
	 */
	public void setParent(Element parent) {
		this.parent = parent;
	}
	/**
	 * Gets parent element
	 * @return parent
	 */
	public Element getParent() {
		return parent;
	}

	/**
	 * Gets fully qualified name.
	 * It includes fully qualified name of parent.
	 * @return
	 */
	public String getFullyQualifiedName() {
		return parent == null?name : parent.getFullyQualifiedName()+"."+name;
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
