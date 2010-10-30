package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.util.ArrayList;
import java.util.List;

public class Package {
	private List<Class> classes;
	private List<Enum> enums;
	private List<Package> packages;
	private String name;
	private List<Interface> interfaces;
	
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
}
