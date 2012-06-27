package com.ericsson.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ericsson.entry.Address;

/**
 * 解析XML文件的工具类
 * 
 * @version ab-0.0.1
 * @author XCF email:xcfhr@126.com
 * @since JDK 1.6
 */
public class HandleXml {

	// junit use
	public static int flag = 0;

	/**
	 * 向filename所指定的文件添加address实体
	 * 
	 * @param filename
	 *            XML文件名（含路径）
	 * @param address
	 *            Address对象
	 */
	public static void add(String filename, Address address) {
		File file = new File(filename);
		initFile(file);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filename);
			doc.normalize();

			Element addressNode = doc.createElement("address");

			Element nameNode = doc.createElement("name");
			nameNode.appendChild(doc.createTextNode(address.getName()));
			addressNode.appendChild(nameNode);

			Element mobileNode = doc.createElement("mobile");
			mobileNode.appendChild(doc.createTextNode(address.getMobileNo()));
			addressNode.appendChild(mobileNode);

			Element homeAddressNode = doc.createElement("homeAddress");
			homeAddressNode.appendChild(doc.createTextNode(address
					.getHomeAddress()));
			addressNode.appendChild(homeAddressNode);
			doc.getDocumentElement().appendChild(addressNode);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileOutputStream(file));
			transformer.transform(source, result);

			flag = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int verifyAdd(String filename, Address address) {
		HandleXml.add(filename, address);
		return flag;
	}

	/**
	 * 在filename所指定的文件中,根据搜索选项option,搜索与value值 相匹配的address节点，并打印出搜索结果
	 * 
	 * @param filename
	 *            XML文件名（含路径）
	 * @param option
	 *            节点名称，如name,mobile,homeAddress
	 * @param value
	 *            可为字符串或正则表达式
	 */
	public static void search(String filename, String option, String value) {
		File file = new File(filename);
		initFile(file);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filename);
			doc.normalize();
			ArrayList<Node> matchNodeList = new ArrayList<Node>();

			Element root = doc.getDocumentElement();
			NodeList childList = root.getChildNodes();
			if ("name".equals(option)) {
				for (int i = 0; i < childList.getLength(); i++) {
					if (childList.item(i).getNodeName().equals("#text"))
						continue;
					NodeList iChildList = childList.item(i).getChildNodes();
					// 精确匹配和正则表达式匹配均可
					if (iChildList.item(1).getTextContent().matches(value))
						matchNodeList.add(childList.item(i));
				}

				System.out.println("search by name," + matchNodeList.size()
						+ " matched records:");
				for (Node node : matchNodeList)
					System.out.println(node.getChildNodes().item(1)
							.getTextContent()
							+ "; "
							+ node.getChildNodes().item(3).getTextContent()
							+ "; "
							+ node.getChildNodes().item(5).getTextContent());
				flag = 1;
				return;
			} else if ("mobile".equals(option)) {
				for (int i = 0; i < childList.getLength(); i++) {
					if (childList.item(i).getNodeName().equals("#text"))
						continue;
					NodeList iChildList = childList.item(i).getChildNodes();
					if (iChildList.item(3).getTextContent().matches(value))
						matchNodeList.add(childList.item(i));
				}
				System.out.println("search by mobile," + matchNodeList.size()
						+ " matched records:");
				for (Node node : matchNodeList) {
					System.out.println(node.getChildNodes().item(1)
							.getTextContent()
							+ "; "
							+ node.getChildNodes().item(3).getTextContent()
							+ "; "
							+ node.getChildNodes().item(5).getTextContent());
				}

				flag = 2;
				return;
			} else if ("address".equals(option)) {
				for (int i = 0; i < childList.getLength(); i++) {
					if (childList.item(i).getNodeName().equals("#text"))
						continue;
					NodeList iChildList = childList.item(i).getChildNodes();
					if (iChildList.item(5).getTextContent().matches(value))
						matchNodeList.add(childList.item(i));
				}
				System.out.println("search by address," + matchNodeList.size()
						+ " matched records:");
				for (Node node : matchNodeList) {
					System.out.println(node.getChildNodes().item(1)
							.getTextContent()
							+ "; "
							+ node.getChildNodes().item(3).getTextContent()
							+ "; "
							+ node.getChildNodes().item(5).getTextContent());
				}

				flag = 3;
				return;
			} else {
				System.out.println("Error command!");
				flag = 4;
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		flag = 0;

	}

	public static int verifySearch(String filename, String option, String value) {
		HandleXml.search(filename, option, value);
		return flag;
	}

	/**
	 * 在filename指定的文件中,根据搜索选项option,删除所有与value值相匹配 的address节点
	 * 
	 * @param filename
	 *            XML文件名（含路径）
	 * @param option
	 *            节点名称，如name,mobile,homeAddress
	 * @param value
	 *            可为字符串或正则表达式
	 */
	public static void delete(String filename, String option, String value) {
		File file = new File(filename);
		initFile(file);
		int befeoreDelete = 0; // node amount before delete
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filename);
			doc.normalize();

			Element root = doc.getDocumentElement();
			NodeList childList = root.getChildNodes();
			befeoreDelete = childList.getLength();
			if ("name".equals(option)) {
				for (int i = 0; i < childList.getLength(); i++) {
					if (childList.item(i).getNodeName().equals("#text"))
						continue;
					NodeList iChildList = childList.item(i).getChildNodes();
					if (iChildList.item(1).getTextContent().matches(value))
						root.removeChild(childList.item(i));
				}
				flag = 1;
			} else if ("mobile".equals(option)) {
				for (int i = 0; i < childList.getLength(); i++) {
					if (childList.item(i).getNodeName().equals("#text"))
						continue;
					NodeList iChildList = childList.item(i).getChildNodes();
					if (iChildList.item(3).getTextContent().matches(value))
						root.removeChild(childList.item(i));
				}
				flag = 2;
			} else if ("address".equals(option)) {
				for (int i = 0; i < childList.getLength(); i++) {
					if (childList.item(i).getNodeName().equals("#text"))
						continue;
					NodeList iChildList = childList.item(i).getChildNodes();
					if (iChildList.item(5).getTextContent().matches(value))
						root.removeChild(childList.item(i));
				}
				flag = 3;
			} else {
				System.out.println("Error command!");
				flag = 4;
			}

			NodeList childListD = root.getChildNodes();
			System.out.println(befeoreDelete - childListD.getLength()
					+ " address entries are deleted.");
			for (int i = 0; i < childListD.getLength(); i++) {
				if (childListD.item(i).getNodeName().equals("#text"))
					root.removeChild(childListD.item(i));
			}
			Element newRoot = root;
			doc.replaceChild(newRoot, root);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileOutputStream(file));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int verifyDelete(String filename, String option, String value) {
		HandleXml.delete(filename, option, value);
		return flag;
	}

	/**
	 * 初始化XML文件，若没有则创建 并向该文件中添加根节点<Addresses>
	 * 
	 * @param file
	 *            XML文件所对应的文件对象
	 */
	private static void initFile(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.newDocument();
				Element root = doc.createElement("Addresses");
				doc.appendChild(root);
				TransformerFactory tFactory = TransformerFactory.newInstance();
				Transformer transformer = tFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new FileOutputStream(
						file));
				transformer.transform(source, result);
				flag = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		flag = 2;
	}

	public static int verifyInitFile(File file) {
		HandleXml.initFile(file);
		return flag;
	}
}
