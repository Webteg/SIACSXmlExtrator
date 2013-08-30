package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.business.Attribute;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TesteXml {
	public static void main(String[] args){
		TesteXml t = new TesteXml();
		t.iniciando();
	}

	public void iniciando(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		try {
			db = dbf.newDocumentBuilder();
		}catch (Exception e){
		}

		Document doc = null;
		try {
			doc = db.parse("C:\\Users\\filipebrito\\Desktop\\curriculo.xml");
		}catch (Exception e){
		}

 		HashMap<String, Element> hashElement = new HashMap<String, Element>();
 		HashMap<String, Node> hashNode = new HashMap<String, Node>();

 		go(hashElement, hashNode, doc.getDocumentElement());
		//seeHash(hashElement,hashNode );
	}
	

	public void go(HashMap<String, Element> hashElement, HashMap<String, Node> hashNode, Element e){
 		NodeList children = e.getChildNodes();
 		hashElement.put(e.getNodeName(), e);
 		System.out.println(e.getNodeName());
 		System.out.println("===========================");
 		Element aux;
 		for (int i = 0; i < children.getLength(); i++) {
 			Node n = children.item(i);
 			if (i == 0 )
 				aux = (Element) n;
 			System.out.println(n.getNodeName());
 			hashNode.put(n.getNodeName(), n);
 			go(hashElement, hashNode, (Element) n);
 		}
			go(hashElement, hashNode, );
	}

	private void seeHash(HashMap<String, Element> hashElement, HashMap<String, Node> hashNode ) {
		Iterator it = hashElement.values().iterator();
		Iterator it2 = hashNode.values().iterator();
		
		while(it.hasNext()){
			Element e = (Element) it.next();
			System.out.println(" --- NO PAI ---- ");
			System.out.println(e.getNodeName());
			/*s
			while (it2.hasNext()) {
				Node n = (Node) it.next();
				System.out.println(n.getNodeName());
			}
			*/
		}		
	}

/*
 * 
 *  	public void go(List<String> list, Element e){
 		HashMap<Element, Node> hash = new HashMap<Element, Node>();
 		System.out.println(e.getNodeName());
 		System.out.println("========");
 		NodeList children = e.getChildNodes();
 		for (int i = 0; i < children.getLength(); i++) {
 			Node n = children.item(i);
 			System.out.println(n.getNodeName());
 			
 			if (n.getNodeType() == Node.ELEMENT_NODE) {
 				System.out.println("oi");
 			//	list.add(n.getNodeName());s
 				//go(list, (Element) n);
 			}
 			
 		}
	}	
 * 
 */
	
}
