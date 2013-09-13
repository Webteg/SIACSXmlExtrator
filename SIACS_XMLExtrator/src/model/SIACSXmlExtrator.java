package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.db.ManagerDB;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class SIACSXmlExtrator {
	List<model.business.Element> elementList = new ArrayList<model.business.Element>();
	List<model.business.Attribute> attributeList = new ArrayList<model.business.Attribute>();

	private ZipInputStream zis;
	ManagerDB managerDB = ManagerDB.getInstance();
	private static SIACSXmlExtrator singleton = null;


	public static SIACSXmlExtrator getInstance() {
		if(singleton == null){
			return new SIACSXmlExtrator();
		}
		return singleton;
	}
	
	private SIACSXmlExtrator(){}
	
	//#1
	public void uploadFileToServer(ServletFileUpload uploader, HttpServletRequest request){
		try{
			String caminho_do_zip = "";	
			String destino = "";

			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			while(fileItemsIterator.hasNext()){
				FileItem fileItem = fileItemsIterator.next();
				File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
				fileItem.write(file);
				caminho_do_zip += request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName();
				destino += request.getServletContext().getAttribute("FILES_DIR");
			}
			this.unzipFile(caminho_do_zip, destino);
		}catch (FileUploadException e){
			System.out.println(e.getMessage());
		}catch (Exception e){
			System.out.println(e.getMessage());
		}			
	}	
	
	//#2
	public void unzipFile(String caminho_do_zip, String destino){
		try{
			zis = new ZipInputStream(new FileInputStream(caminho_do_zip));
			ZipEntry ze = zis.getNextEntry();
			byte[] buffer = new byte[1024];
			String unzippedXmlPath = "";
			
			while(ze != null){
				String fileName = ze.getName();
				File newFile = new File(destino + File.separator + fileName);
				unzippedXmlPath += newFile.getAbsoluteFile();

				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);             
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();   
				
				ze = zis.getNextEntry();
	     	}
			this.readXML(unzippedXmlPath);
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	//#3
	public void readXML(String unzippedXmlPath){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		try {
			db = dbf.newDocumentBuilder();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		Document doc = null;
		
		try {
			doc = db.parse(unzippedXmlPath);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}	    
		
		getXmlElements(elementList, doc.getDocumentElement());
 		
		int size = elementList.size();
 		for(int i = 0; i < size ; ++i){
 			model.business.Element element = (model.business.Element) elementList.get(i);	
 			managerDB.insertElement(element);
 		}
	
 		getXmlAttributes(elementList, doc.getDocumentElement());
	}

	//#4
	public void getXmlElements(List<model.business.Element> elementList, Element e){
		model.business.Element element = new model.business.Element();
		model.business.Element parentElement = new model.business.Element();
		
		String nameparentElement =  e.getParentNode().toString().replace(": null]", "").replace("[", "");
		if(nameparentElement.equals("#document"))
			nameparentElement = null;
		
		element.setName(e.getNodeName());
		parentElement.setName(nameparentElement);
		
		element.setParent_element(parentElement);
		elementList.add(element);
		
        final NodeList children = e.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
            	getXmlElements(elementList, (Element) n);
            }
        }
    }	
	
	//#5
	public void getXmlAttributes(List<model.business.Element> elementList, Element e){
		model.business.Attribute attribute = new model.business.Attribute();
		
		int size = elementList.size();
 		for(int i = 0; i < size ; ++i){
 			model.business.Element element = (model.business.Element) elementList.get(i);	
 			e.getAttribute(element.getName());
 		}		
		
	}
}