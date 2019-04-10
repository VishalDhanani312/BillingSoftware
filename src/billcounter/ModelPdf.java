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
import java.awt.Desktop;
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



public class ModelPdf {
    
       private List<ModelTable> items = null;
       private  Font font = new Font(Font.FontFamily.UNDEFINED,14, Font.NORMAL);
       
       private double totalf = 0;
       private Document document;
       private PdfWriter writer;
       private String pName,date;
       private String fname;
    public ModelPdf(List<ModelTable> items,String pName,String date) {
        
        this.items = items;
        this.date = date;
        this.pName = pName;
    }
       
       
    
       public void print() throws FileNotFoundException, DocumentException {
           try {
               document = new Document();
               Paragraph par = new Paragraph();
               
               for(int i=0; i<1; i++){
                   Chunk chunk = new Chunk("                          ");
                   par.add(chunk);
                   
               }
               PdfPTable table = new PdfPTable(new float[] {1,2,2,2,2,2});
               
               table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
               table.setSpacingBefore(30);
               table.addCell("Index");
               table.addCell("Chain Name");
               table.addCell("Touch");
               table.addCell("Chain Weight");
               table.addCell("Your Touch");
               table.addCell("Fine Weight");
               table.setHeaderRows(1);
               PdfPCell[] cells = table.getRow(0).getCells();
               for (int j=0;j<cells.length;j++){
                   cells[j].setBackgroundColor(BaseColor.GRAY);
                   
               }
               
               for (int i=0;i<items.size();i++){
                   
                   
                   
                   ModelTable obj = items.get(i);
                   
                   table.addCell(new Phrase(obj.getIndex(), new Font(Font.FontFamily.COURIER, 12, Font.ITALIC)));
                   
                   table.addCell(obj.getChainname());
                   table.addCell(obj.getTouch());
                   
                   table.addCell(obj.getChainweight());
                   table.addCell(obj.getYtouch());
                   table.addCell(obj.getFweight());
                   
                   totalf+= Double.parseDouble(obj.getFweight());
               }
               
               PdfPCell cellOne = new PdfPCell(new Phrase(""));
               PdfPCell celltwo = new PdfPCell(new Phrase(""));
               PdfPCell cellthree = new PdfPCell(new Phrase(""));
               PdfPCell cellfour = new PdfPCell(new Phrase(""));
               PdfPCell cellfive = new PdfPCell(new Phrase("Total Fine Weight:"));
               PdfPCell cellsix = new PdfPCell(new Phrase(String.valueOf(totalf)));
               
               cellOne.setBorder(Rectangle.NO_BORDER);
               cellOne.setBackgroundColor(BaseColor.WHITE);
               
               celltwo.setBorder(Rectangle.NO_BORDER);
               celltwo.setBackgroundColor(BaseColor.WHITE);
               cellthree.setBorder(Rectangle.NO_BORDER);
               cellthree.setBackgroundColor(BaseColor.WHITE);
               cellfour.setBorder(Rectangle.NO_BORDER);
               cellfour.setBackgroundColor(BaseColor.WHITE);
               cellfive.setBorder(Rectangle.BOX);
               cellfive.setBackgroundColor(BaseColor.WHITE);
               cellfive.setHorizontalAlignment(Element.ALIGN_CENTER);
               cellsix.setBorder(Rectangle.BOX);
               cellsix.setBackgroundColor(BaseColor.WHITE);
               cellsix.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(cellOne);
               table.addCell(celltwo);
               table.addCell(cellthree);
               table.addCell(cellfour);
               table.addCell(cellfive);
               table.addCell(cellsix);
               fname = System.getProperty("user.home") +"/Documents" + new Date().getTime() + ".pdf";
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
               new PathSec().pdf(fname);
               } catch (Exception ex) {
               Logger.getLogger(GoldInPdf.class.getName()).log(Level.SEVERE, null, ex);
               }
               }
               });
               
               
               
               
               
               System.out.println("Done");
           } catch (IOException ex) {
               Logger.getLogger(ModelPdf.class.getName()).log(Level.SEVERE, null, ex);
           }
      }
       
       
        public void onStartPage(String pName,String date) {
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Date:"+pName,font),490,800, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Mr."+date,font), 70, 800, 0);
    }

    public void onEndPage() {
        
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Ashwin Patadia-9825307557 ",font), 480, 25, 0);
    }
    
    public void watermark(){
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

             if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                      System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
                      System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
                } else {
                             System.out.println("No Selection ");
                     }
        
        try {
                
               PdfReader reader = new PdfReader(fname);
               PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(chooser.getSelectedFile()+"/"+new Date().getTime()+".pdf"));
               int n = reader.getNumberOfPages();
               PdfContentByte under = stamper.getUnderContent(1);
               Font f = new Font(FontFamily.HELVETICA, 70);
               Phrase p;
               PdfContentByte over;
               
               for(int i=1;i<=n;i++){
               p = new Phrase("Patadia Chain", f);
               over = stamper.getOverContent(i);
               over.saveState();
               PdfGState gs1 = new PdfGState();
               gs1.setFillOpacity(0.3f);
               over.setGState(gs1);
               ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, 297, 450, 45);
               over.restoreState();
               
               }
               stamper.close();
               reader.close();
           } catch (Exception ex) {
               Logger.getLogger(ModelPdf.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        
        
    }
    
}

