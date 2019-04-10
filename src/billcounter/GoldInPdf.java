/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billcounter;

/**
 *
 * @author Vishu
 */
import static com.itextpdf.kernel.pdf.PdfName.Color;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;



public class GoldInPdf {
    
       private List<GoldInModel> items = null;
       private  Font font = new Font(Font.FontFamily.UNDEFINED,14, Font.NORMAL);
       
       private double totalf = 0;
       private Document document;
       private PdfWriter writer;
       private String pName,date;
       private String fname;
    public GoldInPdf(List<GoldInModel> items,String pName,String date) {
        
        this.items = items;
        this.date = date;
        this.pName = pName;
    }
       
       
    
       public void print() throws FileNotFoundException, DocumentException {
	  document = new Document();
          Paragraph par = new Paragraph();
         
        for(int i=0; i<1; i++){
                Chunk chunk = new Chunk("                          ");
                par.add(chunk);
                
            }
	  PdfPTable table = new PdfPTable(new float[] {1,2,2,2,3});
          
	  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
          table.setSpacingBefore(30);
	  table.addCell("Index");
          table.addCell("Gold Weight");
          table.addCell("Touch");
          table.addCell("Fine Weight");
          table.addCell("Total Fine Weight");
          
	  table.setHeaderRows(1);
	  PdfPCell[] cells = table.getRow(0).getCells(); 
	  for (int j=0;j<cells.length;j++){
	     cells[j].setBackgroundColor(BaseColor.GRAY);
             
	  }
          
          for (int i=0;i<items.size();i++){
              
             
              GoldInModel obj = items.get(i);
              
    	     table.addCell(obj.getIndex());
             table.addCell(obj.getGoldweight());
             table.addCell(obj.getTouch());
             
    	     table.addCell(obj.getFineweight());
             table.addCell(obj.getTotalfineweight());
             totalf+= Double.parseDouble(obj.getFineweight());
          }
          PdfPCell cellOne = new PdfPCell(new Phrase(""));
          PdfPCell celltwo = new PdfPCell(new Phrase(""));
          PdfPCell cellthree = new PdfPCell(new Phrase(""));
          
          PdfPCell cellfive = new PdfPCell(new Phrase("Total Fine Weight:"));
          PdfPCell cellsix = new PdfPCell(new Phrase(String.valueOf(totalf)));
          
          cellOne.setBorder(Rectangle.NO_BORDER);
          cellOne.setBackgroundColor(BaseColor.WHITE);
         
          celltwo.setBorder(Rectangle.NO_BORDER);
          celltwo.setBackgroundColor(BaseColor.WHITE);
          cellthree.setBorder(Rectangle.NO_BORDER);
          cellthree.setBackgroundColor(BaseColor.WHITE);
          cellfive.setBorder(Rectangle.BOX);
          cellfive.setBackgroundColor(BaseColor.WHITE);
          cellfive.setHorizontalAlignment(Element.ALIGN_CENTER);
          cellsix.setBorder(Rectangle.BOX);
          cellsix.setBackgroundColor(BaseColor.WHITE);
          cellsix.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.addCell(cellOne);
          table.addCell(celltwo);
          table.addCell(cellthree);
          
          table.addCell(cellfive);
          table.addCell(cellsix);
          fname = "bill/" + new Date().getTime() + ".pdf";
	writer =  PdfWriter.getInstance(document, new FileOutputStream(fname));
         
	  document.open();
          
          onStartPage(pName,date);
          document.add(par);
          
          document.add(table);
          onEndPage();
	  document.close();
          PathSec.fname = fname;
           Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Your class that extends Application
                    new PathSec().start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(GoldInPdf.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
	  System.out.println("Done");
      }
       
       
        public void onStartPage(String pName,String date) {
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Mr."+pName,font),70,800, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Date:"+date,font), 490, 800, 0);
    }

    public void onEndPage() {
        
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Ashwin Patadia-9825307557 ",font), 480, 25, 0);
    }
    
     
   
}

