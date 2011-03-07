package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Alignable;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;

public class Package extends Element implements Serializable, Alignable {
	private static final long serialVersionUID = 1980055108799234195L;
	private List<Class> classes = new ArrayList<Class>();
	private List<Enum> enums = new ArrayList<Enum>();
	private List<Package> packages = new ArrayList<Package>();
	private String fullName;
	private List<Interface> interfaces = new ArrayList<Interface>();
	private transient String parentName;

	public Package() {
		classes = new ArrayList<Class>();
		enums = new ArrayList<Enum>();
		packages = new ArrayList<Package>();
		interfaces = new ArrayList<Interface>();
	}

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	public List<Enum> getEnums() {
		return enums;
	}

	public void setEnums(List<Enum> enums) {
		this.enums = enums;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	public List<Interface> getInterfaces() {
		return interfaces;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public String getFullyQualifiedName() {
		return fullName;
	}

	@Override
	public boolean matches(Object object, AlignStrategy alignStrategy) {
		if (object == null) {
			return false;
		}
		if (object instanceof sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package) {
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package pack = (sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package) object;
			switch (alignStrategy) {
			case EXACT:
				return getFullyQualifiedName().equalsIgnoreCase(pack.getFullyQualifiedName());
			case IGNORE_PACKAGE:
				return getName().equalsIgnoreCase(pack.getName());
			case MANUAL:
				return false;
			case APPROXIMATION:
				return getFullyQualifiedName().equalsIgnoreCase(pack.getFullyQualifiedName());
			default:
				throw new IllegalStateException("Align strategy " + alignStrategy + "is not supported ");
			}
		}
		return false;
	}
}
