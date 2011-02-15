package sk.tuke.fei.kpi.ProjectObserver.Integration.parser;

import java.io.File;

/**
 * Parser interface. Describe parsing process.
 * @author Maroš Tyrpák
 *
 * @param <T> class which is return type of parse method
 */
public interface Parser<T> {
	T parse(String pathname) throws ParserException;
	T parse(File file) throws ParserException;
}
