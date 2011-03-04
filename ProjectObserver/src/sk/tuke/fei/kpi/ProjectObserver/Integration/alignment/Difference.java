package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import java.util.ArrayList;
import java.util.List;

public class Difference {
	public enum DifferenceBy{
		MODIFIERS, VISIBILITY, METHODS, FIELDS, CONSTRUCTORS,PACKAGE
	}

	public enum DifferenceType{
		MISSING, MORE, DIFFERENT, EXACT
	}
	private class DifferenceWrapper {
		DifferenceType type;
		DifferenceBy by;
	}

	private List<DifferenceWrapper> differences;
	
	public Difference() {
		differences = new ArrayList<DifferenceWrapper>();
	}
	
	public boolean hasDifferentVisibility(){
		return hasDifferent(DifferenceBy.VISIBILITY) == DifferenceType.DIFFERENT;
	}
	public boolean hasDifferentModifiers(){
		return hasDifferent(DifferenceBy.MODIFIERS) == DifferenceType.DIFFERENT;		
	}
	public DifferenceType hasDifferentMethods(){
		return hasDifferent(DifferenceBy.METHODS);
	}
	public DifferenceType hasDifferentFields(){
		return hasDifferent(DifferenceBy.FIELDS);
	}
	public DifferenceType hasDifferentConstructors(){
		return hasDifferent(DifferenceBy.CONSTRUCTORS);
	}
	public DifferenceType hasDifferentPackage(){
		return hasDifferent(DifferenceBy.PACKAGE);
	}
	
	public DifferenceType hasDifferent(DifferenceBy dif){
		DifferenceWrapper obj = find(dif);
		if(obj!=null)
			return obj.type;
		else 
			return DifferenceType.EXACT;
	}
	
	private DifferenceWrapper find(DifferenceBy dif){
		for (DifferenceWrapper obj : differences) {
			if(obj.by == dif){
				return obj;
			}
		}
		return null;
	}
}
