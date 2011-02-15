package sk.tuke.fei.kpi.ProjectObserver.Integration.parser;

import java.io.File;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.utils.XMLUtils;

public class JavaParser implements Parser<Application> {

	@Override
	public Application parse(String pathname) throws ParserException {
		return parse(new File(pathname));
	}

	@Override
	public Application parse(File file) throws ParserException {
		System.out.println(XMLUtils.readXmlDocument(file));
		return null;
	}

}
