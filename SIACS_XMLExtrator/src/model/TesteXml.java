package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.db.ManagerDB;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TesteXml {
	List<model.business.Element> elementList = new ArrayList<model.business.Element>();
	List<model.business.Attribute> attributeList = new ArrayList<model.business.Attribute>();
	List<model.business.Value> valueList = new ArrayList<model.business.Value>();
	
	ManagerDB managerDB = ManagerDB.getInstance();

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
		
		getXmlAllContent(elementList, attributeList, valueList, doc.getDocumentElement());
 		
		int elementsize = elementList.size();
 		for(int i = 0; i < elementsize ; ++i){
 			model.business.Element element = (model.business.Element) elementList.get(i);	
 			System.out.println(element);
 			managerDB.insertElement(element);
 		}

		int attributesize = attributeList.size();
 		for(int i = 0; i < attributesize ; ++i){
 			model.business.Attribute attr = (model.business.Attribute) attributeList.get(i);	
 			System.out.println(attr);
 			managerDB.insertAttribute(attr);
 		}

 		int valuesize = attributeList.size();
 		for(int i = 0; i < valuesize ; ++i){
 			model.business.Value val = (model.business.Value) valueList.get(i);	
 			//System.out.println(val);
 			managerDB.insertValue(val);
 		}
	}

	//#4
	public void getXmlAllContent(List<model.business.Element> elementList, 
			List<model.business.Attribute> attributeList, List<model.business.Value> valueList, Element e){
		
		model.business.Element element = new model.business.Element();
		model.business.Element parentElement = new model.business.Element();
		
		String nameparentElement =  e.getParentNode().toString().replace(": null]", "").replace("[", "");
		if(nameparentElement.equals("#document"))
			nameparentElement = null;
		
		element.setName(e.getNodeName());
		parentElement.setName(nameparentElement);
		
		element.setParent_element(parentElement);
		elementList.add(element);
		
		 if( e.hasAttributes()) {
		      NamedNodeMap attrs = e.getAttributes();  
		      for(int i = 0 ; i<attrs.getLength() ; i++) {
		    	  Attr attribute = (Attr)attrs.item(i);
		    	  model.business.Attribute attrib = new model.business.Attribute();
		    	  model.business.Value val = new model.business.Value();
		    	  
		    	  attrib.setName(attribute.getName());
		    	  attrib.setElement(element);
		    	  
		    	  val.setContent(attribute.getValue());
		    	  val.setAttribute(attrib);
		    	  
		    	  attributeList.add(attrib);
		    	  if(!val.getContent().equals(""))
		    	     valueList.add(val);
		      }
		 }
		
        final NodeList children = e.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
            	getXmlAllContent(elementList, attributeList, valueList, (Element) n);
            }
        }
    }	
}