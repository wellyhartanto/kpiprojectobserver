package sk.tuke.fei.kpi.ProjectObserver.utils;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XMLUtils {

	public static String XMLToString(Document document) {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult result = new StreamResult(new StringWriter());
			transformer.transform(new DOMSource(document), result);
			return result.getWriter().toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String readXmlDocument(File file) {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			return XMLToString(document);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
