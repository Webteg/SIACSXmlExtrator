package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
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

import org.xml.sax.Attributes;






import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;






import model.business.Tag;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class SIACSXmlExtrator {
	private List<String> rootTagList = new ArrayList<String>();
	private List<String> childrenTagList = new ArrayList<String>();
	private List<Tag> tagList = new ArrayList<Tag>();
	
	
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
			ZipInputStream zis = new ZipInputStream(new FileInputStream(caminho_do_zip));
			ZipEntry ze = zis.getNextEntry();
			byte[] buffer = new byte[1024];
			String unzipFilePath = "";
			
			while(ze != null){
				String fileName = ze.getName();
				File newFile = new File(destino + File.separator + fileName);
				unzipFilePath += newFile.getAbsoluteFile();

				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);             
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();   
				
				ze = zis.getNextEntry();
	     	}
			this.readXMLTest(unzipFilePath);
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
			
	}
	
	//#3
	public void readXMLTest(String caminho_do_xml){
	    try {
	    	SAXParserFactory factory = SAXParserFactory.newInstance();
	    	SAXParser saxParser = factory.newSAXParser();
	     
	    	DefaultHandler handler = new DefaultHandler() {
	     
	    		public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
	    			//System.out.println(qName);
	    			rootTagList.add(qName);
	    			
	    			int length = attributes.getLength();
	    			for (int i=0; i<length; i++) {
	    				childrenTagList.add(attributes.getQName(i));
	    			}
					
	    			
	    			/*
	    			System.out.println("Start Element :" + qName);
	    			// pega o valor de um elemento atraves da sua tag
	    			// System.out.println(attributes.getValue("DATA-ATUALIZACAO"));
	    			 */
	    		}
	    		
	    		/*
	    		public void endElement(String uri, String localName, String qName) throws SAXException {
	    			System.out.println("End Element :" + qName);
	    		}

	    		public void characters(char ch[], int start, int length) throws SAXException {
	    			System.out.println("Salary : " + new String(ch, start, length));
	    		}
	    		*/
	    	};
	    	
	    	// /Users/filipebrito/Desktop/teste.xml
	        saxParser.parse(caminho_do_xml, handler);
	     
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
		//verRootTagList();
		//verChildrenTagList();
		addTagList();
		//verListaPrincipal();

	}
	
	
	public void verRootTagList(){
		Iterator<String> it = this.rootTagList.iterator();
		while(it.hasNext()){			
			String rootTagName = it.next();
			System.out.println(rootTagName);
			Tag tag_ = new Tag();
			tag_.setRoot_tag(rootTagName);
			tagList.add(tag_);
		}
		System.out.println("---------");
	}

	
	public void verChildrenTagList(){
		Iterator<String> it = this.childrenTagList.iterator();
		while(it.hasNext()){
			String childrenTagName = it.next();
			System.out.println(childrenTagName);
			Tag tag_ = new Tag();
			tag_.setChildren_tag(childrenTagName);
			tagList.add(tag_);
		}
		System.out.println("========== COMECOU ==============");
	}
	
	public void addTagList(){
		Iterator<String> it = this.rootTagList.iterator();
		Iterator<String> it2 = this.childrenTagList.iterator();
		while(it.hasNext()){			
			String rootTagName = it.next();
			System.out.println(rootTagName);
			Tag tag_ = new Tag();
			tag_.setRoot_tag(rootTagName);
			while(it2.hasNext()){	
				String childrenTagName = it2.next();
				System.out.println(childrenTagName);
				tag_.setChildren_tag(childrenTagName);
			}
			System.out.println("----------------");
			tagList.add(tag_);
		}
		System.out.println("---------");
		
	}
	
	
	public void verListaPrincipal(){
		Iterator<Tag> it = this.tagList.iterator();
		while(it.hasNext()){
			Tag tag_ = (Tag) it.next();
			System.out.println("Elemento: " + tag_.getRoot_tag());
			System.out.println("Atributo: " + tag_.getChildren_tag());
		}
		
	}

}
