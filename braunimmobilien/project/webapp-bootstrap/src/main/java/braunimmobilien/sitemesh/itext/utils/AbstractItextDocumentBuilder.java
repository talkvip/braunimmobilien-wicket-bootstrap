package braunimmobilien.sitemesh.itext.utils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class AbstractItextDocumentBuilder extends PdfDocument{
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
	  PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
	  document.open();
	  buildDocument(document);
	  } catch (Exception e) {
	  e.printStackTrace();
	  } finally {
	  document.close();
	  }
	  }
	  protected abstract String getFileName();
	  protected abstract void buildDocument(Document document) throws DocumentException, MalformedURLException,
	  IOException;
	  }

