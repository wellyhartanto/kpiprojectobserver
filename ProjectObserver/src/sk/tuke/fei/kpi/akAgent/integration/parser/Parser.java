package sk.tuke.fei.kpi.akAgent.integration.parser;

import java.io.File;

/**
 * Parser interface. Describe parsing process.
 *
 * @param <T> class which is return type of parse method
 */
public interface Parser<T> {
	/**
	 * Parses file specified by pathname to object representation.
	 * @param pathname pathname of input file
	 * @return Instance of class <T> which holds object representation of data in input file.
	 * @throws ParserException
	 */
	T parse(String pathname) throws ParserException;
	/**
	 * Parses specified file to object representation.
	 * @param file input file
	 * @return Instance of class <T> which holds object representation of data in input file.
	 * @throws ParserException
	 */
	T parse(File file) throws ParserException;
}
