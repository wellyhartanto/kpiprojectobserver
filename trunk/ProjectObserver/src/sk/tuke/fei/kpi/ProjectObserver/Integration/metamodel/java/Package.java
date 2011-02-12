package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Package implements Serializable {
	private static final long serialVersionUID = 1980055108799234195L;
	private List<Class> classes = new ArrayList<Class>();
	private List<Enum> enums = new ArrayList<Enum>();
	private List<Package> packages = new ArrayList<Package>();
	private String name;
	private List<Interface> interfaces = new ArrayList<Interface>();

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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	public List<Interface> getInterfaces() {
		return interfaces;
	}

	@Override
	public String toString() {
		return getName();
	}
}
