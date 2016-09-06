package braunimmobilien.sitemesh.itext.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import braunimmobilien.model.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class UserPdfBuilder extends AbstractItextDocumentBuilder{
	 private User user;
	  public UserPdfBuilder(User user) {
	  this.user=user;
	  }
	  protected String getFileName(){
			return  "user-" + user.getId() + ".pdf";
			  
		  }
	  protected void buildDocument(Document document) throws DocumentException {
	 
	  PdfPTable header = new PdfPTable(2);
	  header.setWidthPercentage(100);
	  PdfPCell cell = new PdfPCell(new Paragraph("ZenContact"));
	  cell.setBorder(Rectangle.NO_BORDER);
	  cell.setPadding(10);
	  header.addCell(cell);
	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	  cell = new PdfPCell(new Paragraph(sdf.format(new Date())));
	  cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	  cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
	  cell.setBorder(Rectangle.NO_BORDER);
	  cell.setPadding(10);
	  header.addCell(cell);
	  document.add(header);
	  document.add(new Paragraph(""));
	  document.add(new LineSeparator());
	  document.add(new Paragraph(""));
	  document.add(new Paragraph("Id : " + user.getId()));
	  document.add(new Paragraph("Kurzbeschreibung : " + user.getFullName()));
	  document.add(new Paragraph("Lagebeschreibung : " + user.getEmail()));
	  }
	  }
	  
