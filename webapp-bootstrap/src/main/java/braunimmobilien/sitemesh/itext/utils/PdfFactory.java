package braunimmobilien.sitemesh.itext.utils;

import java.io.File;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;



	public class PdfFactory {
		  public static File createFile(AbstractItextDocumentBuilder document) {
		  return document.getPdFFile();
		  }
		  public static File createFile(AbstractItextXML2PdfDocumentBuilder document) {
			  return document.getPdFFile();
			  }
		  }

