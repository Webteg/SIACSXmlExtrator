package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import model.business.Attribute;
import model.business.Element;
import model.business.Value;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SIACSXmlExtrator {
	private HashMap<String, Element> hashElement = new HashMap<String, Element>();
	private HashMap<String, Attribute> hashAttribute = new HashMap<String, Attribute>();
	private List<Value> listValue = new ArrayList<Value>(); 

	private ZipInputStream zis;
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
			String unzipXmlPath = "";
			
			while(ze != null){
				String fileName = ze.getName();
				File newFile = new File(destino + File.separator + fileName);
				unzipXmlPath += newFile.getAbsoluteFile();

				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);             
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();   
				
				ze = zis.getNextEntry();
	     	}
			this.readXML(unzipXmlPath);
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	//#3
	public void readXML(String unzipXmlPath){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
					System.out.println(qName);
					/*
					Element element = new Element();
					element.setName(qName);
					hashElement.put(qName, element);

					int length = attributes.getLength();
					for (int i=0; i<length; i++) {
						String attributeName = attributes.getQName(i);
						Attribute attribute = new Attribute();
						attribute.setName(attributeName);
						attribute.setElement(element);
						hashAttribute.put(attributeName, attribute);

						String content = attributes.getValue(attributeName);
						if(!content.equals("")){
							Value value = new Value();
							value.setContent(attributes.getValue(attributeName));
							value.setAttribute(attribute);
							listValue.add(value);
						}
					}
					 */
				}

			};
			saxParser.parse(unzipXmlPath, handler);
			//seeElements();
			//seeAttributes();
			//seeValues();
		}catch (Exception e) {
			e.printStackTrace();
		}		    
	}
		/*
		public void seeElements(){
			Iterator it = this.hashElement.values().iterator();
			while(it.hasNext()){
				Element element = (Element) it.next();
				System.out.println(element.getName());
			}
		}
		
		public void seeAttributes(){
			Iterator it = this.hashAttribute.values().iterator();
			while(it.hasNext()){
				Attribute attribute = (Attribute) it.next();				
				System.out.println(attribute.getName());
				System.out.println("Element: " + attribute.getElement().getName());
				System.out.println("================");
			}
		}		
		
		public void seeValues(){
			Iterator it = this.listValue.iterator();
			while(it.hasNext()){
				Value value = (Value) it.next();
				System.out.println(value.getContent());
				System.out.println("Attribute: " + value.getAttribute().getName());
				System.out.println("================");
			}
		}	
		*/	
}