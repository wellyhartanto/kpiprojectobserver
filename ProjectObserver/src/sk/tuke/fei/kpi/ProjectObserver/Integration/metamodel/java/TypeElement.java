package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Alignable;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;

/**
 * Type element is common representation of {@link Class} and {@link Interface}.
 */
public class TypeElement extends Element implements Alignable {
	private static final long serialVersionUID = 7982624404245180466L;
	private List<Method> methods = new ArrayList<Method>();
	private List<Field> fields = new ArrayList<Field>();
	private List<Enum> enums = new ArrayList<Enum>();
	private List<Class> classes = new ArrayList<Class>();
	private List<TypeElement> implemented = new ArrayList<TypeElement>();
	private List<Constructor> constructors = new ArrayList<Constructor>();
	private transient List<String> implementedNames = new ArrayList<String>();
	private TypeElement superClass;
	private transient String superClassName;

	private boolean external;

	/**
	 * Constructor.
	 */
	public TypeElement() {
		super();
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
	 * Gets list of inner classes.
	 * @return classes
	 */
	public List<Class> getClasses() {
		return classes;
	}

	/**
	 * Sets list of inner classes.
	 * @param classes list to set
	 */
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	/**
	 * Gets list of enums.
	 * @return enums
	 */
	public List<Enum> getEnums() {
		return enums;
	}

	/**
	 * Sets list of enums.
	 * @param enums
	 */
	public void setEnums(List<Enum> enums) {
		this.enums = enums;
	}

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

	/**
	 * Gets super class.
	 * @return super class object
	 */
	public TypeElement getSuperClass() {
		return superClass;
	}

	/**
	 * Sets super class
	 * @param superClass super class object
	 */
	public void setSuperClass(TypeElement superClass) {
		this.superClass = superClass;
	}

	/**
	 * Gets list of classes/interfaces which this class extends/implements.
	 * @return list of classes/interfaces
	 */
	public List<TypeElement> getImplemented() {
		return implemented;
	}

	/**
	 * Sets list of classes/interfaces which this class extends/implements.
	 * @param implemented list to set
	 */
	public void setImplemented(List<TypeElement> implemented) {
		this.implemented = implemented;
	}

	/**
	 * Gets super class name.
	 * Transient property. Don't use it. It should be used only in process of creating model from input file.
	 * @return class name 
	 */
	public String getSuperClassName() {
		return superClassName;
	}

	/**
	 * Sets super class name.
	 * Transient property. Don't use it. It should be used only in process of creating model from input file.
	 * @param superClassName superclass name
	 */
	public void setSuperClassName(String superClassName) {
		this.superClassName = superClassName;
	}

	/**
	 * Gets names of classes/interfaces which this class extends/implements.
	 * Transient property. Don't use it. It should be used only in process of creating model from input file.
	 * @return
	 */
	public List<String> getImplementedNames() {
		return implementedNames;
	}
	/**
	 * Sets names of classes/interfaces which this class extends/implements.
	 * Transient property. Don't use it. It should be used only in process of creating model from input file.
	 * @param implementedNames
	 */
	public void setImplementedNames(List<String> implementedNames) {
		this.implementedNames = implementedNames;
	}

	/**
	 * Gets list of constructors.
	 * @return constructors
	 */
	public List<Constructor> getConstructors() {
		return constructors;
	}

	/**
	 * Sets list of constructors.
	 * @param constructors list to set
	 */
	public void setConstructors(List<Constructor> constructors) {
		this.constructors = constructors;
	}

	@Override
	public boolean matches(Object object, AlignStrategy alignStrategy) {
		if (object == null) {
			return false;
		}
		if (object instanceof sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.TypeElement) {
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.TypeElement element = (sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.TypeElement) object;
			switch (alignStrategy) {
			case EXACT:
				return getFullyQualifiedName().equalsIgnoreCase(element.getFullyQualifiedName());
			case IGNORE_PACKAGE:
				return getName().equalsIgnoreCase(element.getName());
			case HEURISTIC:
				boolean preCondition = this.getSuperClass().getFullyQualifiedName().equals(element.getFullyQualifiedName());
				return getFullyQualifiedName().equalsIgnoreCase(element.getFullyQualifiedName()) || preCondition;
			case APPROXIMATION:
				if(getName().equalsIgnoreCase("Calltest")){
					System.out.println("ahoj");
				}
				boolean result = getMethods().size() == element.getMethods().size();
				if (result) {
					for (int i = 0; i < getMethods().size(); i++) {
						for (int j = 0; j < element.getMethods().size(); j++) {
							if (!getMethods().get(i).matches(element.getMethods().get(j), AlignStrategy.APPROXIMATION)) {
								return false;
							}
						}
					}
				}
				return result;
			case MANUAL:
				return false;
			default:
				throw new IllegalStateException("Align strategy " + alignStrategy + "is not supported ");
			}
		}
		return false;
	}
}
