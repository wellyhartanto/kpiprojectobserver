package sk.tuke.fei.kpi.akAgent.integration.alignment;

import java.util.HashMap;
import java.util.Map;

import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Application;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.ClassDiagram;

/**
 * Maps classes, packages and interfaces  from {@link ClassDiagram} to their equivalents from {@link Application}.
 */
public class Uml2JavaMapping implements Mapping<Class, Package,Interface> {
	private static final long serialVersionUID = -2633946238475556976L;
	private Map<String, Package> packageMap;
	private Map<String, Class> classMap;
	private Map<String, Interface> interfaceMap;

	public Uml2JavaMapping() {
		packageMap = new HashMap<String, Package>();
		classMap = new HashMap<String, Class>();
		interfaceMap = new HashMap<String, Interface>();
	}

	@Override
	public Class getClass(String className) {
		return classMap.get(className);
	}

	@Override
	public Package getPackage(String packageName) {
		return packageMap.get(packageName);
	}
	
	@Override
	public Interface getInterface(String interfaceName) {
		return interfaceMap.get(interfaceName);
	}

	@Override
	public void setClassPair(String className, Class clazz) {
		if (classMap.containsKey(className)) {
			classMap.remove(className);
		}
		classMap.put(className, clazz);
	}

	@Override
	public void setPackagePair(String packageName, Package pack) {
		if (packageMap.containsKey(packageName)) {
			packageMap.remove(packageName);
		}
		packageMap.put(packageName, pack);		
	}
	
	@Override
	public void setInterfacePair(String interfaceName, Interface iface) {
		if (interfaceMap.containsKey(interfaceName)) {
			interfaceMap.remove(interfaceName);
		}
		interfaceMap.put(interfaceName, iface);		
	}
}
