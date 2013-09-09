package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.db.ElementDAOHibernate;
import model.db.IElementDAO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TesteXml {
	private IElementDAO elementDAO = new ElementDAOHibernate();
	
	public static void main(String[] args) throws Exception{
		TesteXml t = new TesteXml();
		t.iniciando();
	}

	public void iniciando() throws Exception{
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
		
		List<model.business.Element> newList = new ArrayList<model.business.Element>();
 		go(newList, doc.getDocumentElement());
 		
 		Iterator it = newList.iterator();
 		while(it.hasNext()){
 			model.business.Element e = (model.business.Element) it.next();
 			model.business.Element element = new model.business.Element();
 			element.setName(e.getName());
 			model.business.Element parent_element = e.getParent_element();
 			if(parent_element.getName().equals("#document") )
 				parent_element.setName(null);
 			element.setParent_element(parent_element);
 			//elementDAO.save(element);
 			System.out.println(element.toString());
 		}
 		
	}

	public void go(List<model.business.Element> list, Element e){
		model.business.Element element = new model.business.Element();
		model.business.Element element2 = new model.business.Element();
		
		element.setName(e.getNodeName());
		element2.setName(e.getParentNode().toString().replace(": null]", "").replace("[", ""));
		element.setParent_element(element2);
		list.add(element);
		
        final NodeList children = e.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                go(list, (Element) n);
            }
        }
    }	
	
	
	
}