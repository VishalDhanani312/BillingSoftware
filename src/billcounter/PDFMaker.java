/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billcounter;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;  
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 *
 * @author Vishu
 */
public class PDFMaker {
    
    PdfDocument pdfDoc = null;
    Document document = null;
    public PDFMaker(String name){
        String dest = "bill/"+name;
        try {
            PdfWriter writer = new PdfWriter(dest);
            pdfDoc = new PdfDocument(writer);
            pdfDoc.addNewPage();
            document = new Document(pdfDoc);
            
            
            
            document.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFMaker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static void change(String src,String dest) throws IOException, DocumentException{
        
       PdfReader reader = new PdfReader(src);
        PdfDictionary dict = reader.getPageN(1);
        PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
        if (object instanceof PRStream) {
            PRStream stream = (PRStream)object;
            byte[] data = PdfReader.getStreamBytes(stream);
            System.out.println(new String(data));
            stream.setData(new String(data).replace("Excellent", "HELLO WORLD").getBytes());
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
        
    }
    
    public void addTitle(String title){
        document.add(new Paragraph(title).setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));
        
    

    }
    
    public void addMobile(String m1,String m2){
    document.add(new Paragraph("Mo No1: "+m1).setTextAlignment(TextAlignment.RIGHT).setMultipliedLeading(0.2f));
    document.add(new Paragraph("Mo No2: "+m2).setMultipliedLeading(.2f));
           
        
    }
    
    public void addTable(){
       Table table = new Table(UnitValue.createPointArray(new float[]{60f, 180f, 50f, 80f, 110f})); 
        
       
    table.addCell(new Paragraph("S.N.O.").setBold());
    table.addCell(new Paragraph("PARTICULARS").setBold());
    table.addCell(new Paragraph("QTY").setBold());
    table.addCell(new Paragraph("RATE").setBold());
    table.addCell(new Paragraph("AMOUNT IN RS.").setBold());
    
    
    document.add(table);
    }
    
    
    
    public static void main(String args[]) throws PrintException, IOException{
        
        

        try {
            InputStream  in = new FileInputStream("C:/Users/Vishu/Documents/1527980081112.pdf");
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
         ;
            PrintService printService1[]=  PrintServiceLookup.lookupPrintServices(flavor, pras);
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
            PrintService service;
            service = ServiceUI.printDialog(null, 200, 200,printService1, defaultService, flavor, pras);
            Doc pdfDoc = new SimpleDoc(in, null, null);
            
            DocPrintJob printJob = service.createPrintJob();
            
            printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
            
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFMaker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
