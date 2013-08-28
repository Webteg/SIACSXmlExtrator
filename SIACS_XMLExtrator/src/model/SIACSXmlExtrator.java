package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import model.business.Tag;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class SIACSXmlExtrator {
	
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
		System.out.println(caminho_do_xml);
		try {
			XStream xStream = new XStream(new Dom4JDriver());
			xStream.alias("tag", ArrayList.class);
            xStream.processAnnotations(Tag.class);
            
            BufferedReader input = new BufferedReader(new FileReader(caminho_do_xml));
            ArrayList<Tag> tag = (ArrayList) xStream.fromXML(input);
            input.close();
            for (Tag tag_ : tag) {
                System.out.println("Tag: " + tag_.getRoot_tag() );
            }
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
