package braunimmobilien.sitemesh.itext.utils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class AbstractItextXML2PdfDocumentBuilder extends PdfDocument{
	
	private Document document;
	  private File pdfFile;
	  public File getPdFFile() {
	  if (pdfFile == null)
	  createDocument();
	  return pdfFile;
	  }
	  private void createDocument() {
	  document = new Document();
	  try {
	  pdfFile = new File(getFileName());
	  PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
	  writer.setTagged();
	  document.open();
	  buildDocument(document,writer);
	  } catch (Exception e) {
	  e.printStackTrace();
	  } finally {
	  document.close();
	  }
	  }
	  protected abstract String getFileName();
	  protected abstract void buildDocument(Document document,PdfWriter writer) throws DocumentException, MalformedURLException,
	  IOException, SAXException,ParserConfigurationException;
	  }

