package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import java.io.Serializable;

/**
 * Interface for mapping classes and packages between different data sources
 * @author Maroš Tyrpák
 *
 * @param <C> type of classes
 * @param <P> type of packages
 */
public interface Mapping <C, P, I>  extends Serializable{
	/**
	 * Finds corresponding class according to classname
	 * @param className full name of class including package name
	 * @return
	 */
	C getClass(String className);
	/**
	 * finds corresponding package according to package name
	 * @param packageName full package name
	 * @return corresponding package
	 */
	P getPackage(String packageName);
	
	I getInterface(String interfaceName);
	
	void addClassPair(String className,C clazz);
	
	void addPackagePair(String packageName, P pack);
	
	void addInterfacePair(String interfaceName, I iface);
}
