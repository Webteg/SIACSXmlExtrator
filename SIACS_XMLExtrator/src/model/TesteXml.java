package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
			doc = db.parse("/Users/filipebrito/Downloads/curriculo.xml");
		}catch (Exception e){
		}
		
		List<model.business.Element> list = new ArrayList<model.business.Element>();
 		go(list, doc.getDocumentElement());
 		
 		Iterator it = list.iterator();
 		while(it.hasNext()){
 			model.business.Element ele = (model.business.Element) it.next();
 			System.out.println(ele.toString());
 		}
 		
 		//System.out.println(list);
	}

	public void go(List<model.business.Element> list, Element e){
        final NodeList children = e.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
            	model.business.Element ele = new model.business.Element();
            	ele.setChildren_name(n.getNodeName());
            	ele.setRoot_name(n.getParentNode().toString().replace(": null]", "").replace("[", ""));
            	
                list.add(ele);
                //System.out.println(n.getParentNode().toString());
                go(list, (Element) n);
            }
        }
    }	
	
}