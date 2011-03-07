package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;

public interface Alignable {

	boolean matches(Object object, AlignStrategy alignStrategy);
}
