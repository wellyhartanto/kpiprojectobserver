package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import java.io.Serializable;

/**
 * Interface for mapping classes and packages between different data sources
 *
 * @param <C> type of classes
 * @param <P> type of packages
 * @param <I> type of interfaces
 */
public interface Mapping <C, P, I>  extends Serializable {
	/**
	 * Finds corresponding class according to className.
	 * @param className full name of class including package name
	 * @return class
	 */
	C getClass(String className);
	/**
	 * Finds corresponding package according to package name.
	 * @param packageName full package name
	 * @return corresponding package
	 */
	P getPackage(String packageName);
	/**
	 * Finds corresponding interface according to interfaceName.
	 * @param interfaceName full name of interface including package name
	 * @return interface
	 */
	I getInterface(String interfaceName);
	
	/**
	 * Adds class pair to mapping.
	 * @param className fully qualified name
	 * @param clazz class
	 */
	void addClassPair(String className,C clazz);
	/**
	 * Adds package pair to mapping.
	 * @param packageName fully qualified name
	 * @param pack package
	 */
	void addPackagePair(String packageName, P pack);
	/**
	 * Adds interface pair to mapping
	 * @param interfaceName fully qualified name
	 * @param iface interface
	 */
	void addInterfacePair(String interfaceName, I iface);
}
