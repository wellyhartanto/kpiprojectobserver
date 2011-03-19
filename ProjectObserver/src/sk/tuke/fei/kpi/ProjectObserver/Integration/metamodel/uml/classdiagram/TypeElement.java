package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.util.ArrayList;
import java.util.List;

/**
 * Type element is common representation of {@link Class} and {@link Interface}.
 */
public abstract class TypeElement extends Element {
	private static final long serialVersionUID = -4697238786269383432L;
	List<Method> methods;
	List<Field> fields;
	private List<TypeElement> superClasses;
	private transient List<String> superclassesIds;
	private List<Association> associations;
	private List<TypeElement> innerClasses;
	private boolean enumClass;

	/**
	 * Constructor.
	 */
	public TypeElement() {
		super();
		methods = new ArrayList<Method>();
		fields = new ArrayList<Field>();
		superClasses = new ArrayList<TypeElement>();
		superclassesIds = new ArrayList<String>();
		associations = new ArrayList<Association>();
		innerClasses = new ArrayList<TypeElement>();
	}

	/**
	 * Gets list of methods.
	 * @return methods
	 */
	public List<Method> getMethods() {
		return methods;
	}

	/**
	 * Sets list of methods.
	 * @param methods list to set
	 */
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	/**
	 * Gets list of fields.
	 * @return fields
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * Sets list of fields.
	 * @param fields list to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/**
	 * Sets superclasses and implemented interfaces.
	 * @param superClasses list to set
	 */
	public void setSuperClasses(List<TypeElement> superClasses) {
		this.superClasses = superClasses;
	}

	/**
	 * Gets superclasses and implemented interfaces.
	 * @return list of elements
	 */
	public List<TypeElement> getSuperClasses() {
		return superClasses;
	}

	/**
	 * Gets superclasses XMI id.
	 * Transient property. It is used when model is created.
	 * @return list of XMI ids
	 */
	public List<String> getSuperclassesIds() {
		return superclassesIds;
	}
	
	/**
	 * Sets superclasses XMI id.
	 * Transient property. It is used when model is created.
	 * @param superclassesIds list of XMI id
	 */
	public void setSuperclassesIds(List<String> superclassesIds) {
		this.superclassesIds = superclassesIds;
	}

	@Override
	public String toString() {
		return parent == null ? name : parent + "." + name;
	}

	/**
	 * Sets associations of element.
	 * @param associations list of associations
	 */
	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}

	/**
	 * Gets associations of this element.
	 * @return associations
	 */
	public List<Association> getAssociations() {
		return associations;
	}

	/**
	 * States whether element is enum class.
	 * @return true if element is enum
	 */
	public boolean isEnumClass() {
		return enumClass;
	}

	/**
	 * Sets flag , that element is enumeration
	 * @param enumClass flag
	 */
	public void setEnumClass(boolean enumClass) {
		this.enumClass = enumClass;
	}

	/**
	 * Gets list of inner classes.
	 * @return classes
	 */
	public List<TypeElement> getInnerClasses() {
		return innerClasses;
	}

	/**
	 * Sets list of inner classes.
	 * @param innerClasses list to set
	 */
	public void setInnerClasses(List<TypeElement> innerClasses) {
		this.innerClasses = innerClasses;
	}

	@Override
	public String getFullyQualifiedName() {
		return parent == null ? name : parent.getFullyQualifiedName() + "." + name;
	}
}
