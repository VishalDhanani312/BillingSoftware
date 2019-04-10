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
 
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
 
public class PathSec extends Application {
     private  String path = null;
     public static String fname = null;
     
     
     public void pdf(String path) throws IOException{
         
         Desktop desk = Desktop.getDesktop();
               
               desk.print(new File(path));
     }
    @Override
    public void start(Stage primaryStage) throws Exception {
 
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryChooser);
 
        
                File dir = directoryChooser.showDialog(primaryStage);
                path = dir.getAbsoluteFile().toString();
                         
                watermark(fname);
                System.out.println(path);
                
                
 
        
    }
    
    public void watermark(String fname){
        
       
        
        try {
                
               PdfReader reader = new PdfReader(fname);
               PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(path+"/"+new Date().getTime()+".pdf"));
               int n = reader.getNumberOfPages();
               PdfContentByte under = stamper.getUnderContent(1);
               Font f = new Font(Font.FontFamily.HELVETICA, 70);
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
 
    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
      
        directoryChooser.setTitle("Select Directory");
 
       
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
 
    public static void main(String[] args) {
         Application.launch(args);
    }
 
}