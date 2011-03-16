package sk.tuke.fei.kpi.ProjectObserver.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Utilities for operations with XML documents.
 *
 */
public class XMLUtils {

	/**
	 * Reads XML document as raw string.
	 * @param document XML document
	 * @return Text content of XML document.
	 */
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

	/**
	 * Reads XML document as raw string.
	 * @param file XML file
	 * @return Text content of XML document.
	 */
	public static String readXmlDocument(File file) {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			return XMLToString(document);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Finds nodes by its name in list of nodes.
	 * @param name node name
	 * @param nodeList list of nodes
	 * @return collection of nodes
	 */
	public static List<Node> getNodesByName(String name, NodeList nodeList){
		List<Node> list = new ArrayList<Node>();
		for(int i =0; i < nodeList.getLength();i++){
			if(name.equals(nodeList.item(i).getNodeName())){
				list.add(nodeList.item(i));
			}
		}
		return list;
	}
	
	/**
	 * Finds node by name in list of nodes
	 * @param name node name
	 * @param nodeList list of nodes
	 * @return node
	 */
	public static Node getNodeByName(String name, NodeList nodeList){		
		for(int i =0; i < nodeList.getLength();i++){
			if(name.equals(nodeList.item(i).getNodeName())){
				return nodeList.item(i);
			}
		}
		return null;
	}

	/**
	 * Finds node by name between child nodes of parent node.
	 * @param name node name
	 * @param node parent node
	 * @return nodes
	 */
	public static Node getNodeByName(String name, Node node) {
		return getNodeByName(name, node.getChildNodes());
	}
	
	/**
	 * Finds nodes by name between child nodes of parent node.
	 * @param name node name
	 * @param node parent node
	 * @return collection of nodes
	 */
	public static List<Node> getNodesByName(String name, Node node) {
		return getNodesByName(name, node.getChildNodes());
	}
}
