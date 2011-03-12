package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import java.util.HashMap;
import java.util.Map;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package;

public class Java2UmlMapping implements Mapping<Class, Package,Interface> {
	private static final long serialVersionUID = 6433956366664574971L;
	private Map<String, Package> packageMap;
	private Map<String, Class> classMap;
	private Map<String, Interface> interfaceMap;

	public Java2UmlMapping() {
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
	public void addClassPair(String className, Class clazz) {
		if (!classMap.containsKey(className)) {
			classMap.put(className, clazz);
		}
	}

	@Override
	public void addPackagePair(String packageName, Package pack) {
		if (!packageMap.containsKey(packageName)) {
			packageMap.put(packageName, pack);
		}
	}
	
	@Override
	public void addInterfacePair(String interfaceName, Interface iface) {
		if (!interfaceMap.containsKey(interfaceName)) {
			interfaceMap.put(interfaceName, iface);
		}		
	}
}