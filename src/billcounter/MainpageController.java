/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billcounter;

import com.itextpdf.text.DocumentException;
import javafx.scene.control.TextFormatter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.Paint;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dharmik
 */
public class MainpageController implements Initializable {
    DecimalFormat selling=new DecimalFormat(".###"),regular=new DecimalFormat(".###");
    DecimalFormat taking=new DecimalFormat(".####"),money=new DecimalFormat("#.0");
    DecimalFormat customer1=new DecimalFormat(".###"),customer2=new DecimalFormat(".###");
    private HashMap<String,String> bucket = new HashMap();
    private DatabaseUtil database = new DatabaseUtil();
    @FXML
    private JFXComboBox<String> chainincombobox;
    private List<String> list = new ArrayList<>();
    @FXML
    private Label chainintgw;
    @FXML
    private JFXComboBox<String> chaininchainname;
    @FXML
    private JFXTextField chainintouch;
    @FXML
    private JFXTextField chainincweight;
    @FXML
    private JFXTextField chaininytouch;
    @FXML
    private JFXButton chainindone;
    @FXML
    private JFXButton chaininclr;
    @FXML
    private Label chaininbalance;
    @FXML
    private JFXDatePicker chainindate;
    @FXML
    private JFXTextField chaininfineweight;
    @FXML
    private JFXComboBox<String> goldoutcombobox;
    @FXML
    private Label goldouttgw;
    @FXML
    private JFXTextField goldouttouch;
    @FXML
    private JFXTextField goldoutgweight;
    @FXML
    private JFXTextField goldoutfineweight;
    @FXML
    private JFXButton goldoutdone;
    @FXML
    private JFXButton goldoutclr;
    @FXML
    private Label goldoutbalance;
    @FXML
    private JFXDatePicker goldoutdate;
    @FXML
    private JFXComboBox<String> chainoutchainname;
    @FXML
    private JFXComboBox<String> chainouttouch;
    @FXML
    private JFXTextField chainoutchainweight;
    @FXML
    private JFXTextField chainoutytouch;
    @FXML
    private JFXButton chainoutdone;
    @FXML
    private JFXButton chainoutclr;
    @FXML
    private JFXButton chainoutprint;
    @FXML
    private JFXTextField chainoutname;
    @FXML
    private JFXDatePicker chainoutdate;
    @FXML
    private JFXTextField chainoutfineweight;
    @FXML
    private JFXButton cashindone;
    @FXML
    private JFXButton cashinclr;
    @FXML
    private JFXTextField cashinname;
    @FXML
    private JFXDatePicker cashindate;
    @FXML
    private JFXTextField cashintouch;
    @FXML
    private JFXTextField cashingoldweight;
    @FXML
    private JFXTextField cashinfinalweight;
    @FXML
    private JFXTextField cashincash;
    @FXML
    private JFXTextField cashingoldprice;
    @FXML
    private JFXButton cashincheck;
    @FXML
    private JFXTextField cashinremainweight;
    @FXML
    private JFXButton cashinadd;
    @FXML
    private JFXTextField billingname;
    @FXML
    private JFXTextField billingtotalmoney;
    @FXML
    private JFXTextField billinggoldprice;
    @FXML
    private JFXTextField billingcgst;
    @FXML
    private JFXTextField billingigst;
    @FXML
    private JFXTextField billinginitialprice;
    @FXML
    private JFXTextField billinggoldweight;
    private JFXComboBox<String> billingcombobox;
    @FXML
    private JFXButton billingdone;
    @FXML
    private JFXButton billingclr;
    @FXML
    private JFXButton billingprint;
    @FXML
    private JFXDatePicker billingdate;
    @FXML
    private JFXComboBox<String> infocombobox;
    @FXML
    private Label infocredittgw;
    @FXML
    private Label infocreditbalance;
    @FXML
    private Label infodebittgw;
    @FXML
    private Label infodebitbalance;
    @FXML
    private JFXTextField printperson;
    @FXML
    private JFXComboBox<String> printcombobox;
    @FXML
    private JFXTextField printchainname;
    @FXML
    private JFXTextField printtouch;
    @FXML
    private JFXTextField printchainweight;
    @FXML
    private JFXTextField printytouch;
    @FXML
    private JFXDatePicker printdate;
    @FXML
    private JFXTextField goldoutinfo;
    @FXML
    private JFXTextField printyfineweight;
    @FXML
    private JFXButton cashinreset;
    @FXML
    private JFXComboBox<String> chainindtouch;
    @FXML
    private Label infocredittgw11;
    @FXML
    private JFXComboBox<String> chaininchainname11;
    @FXML
    private JFXComboBox<String> chainindtouch11;
    @FXML
    private Label infocredittgw1;
    private String cTouch,cName;
    private String coTouch,coName;
    private String pName,date;
    @FXML
    private TableView<ModelTable> chainouttable;
    private List<ModelTable> items = new ArrayList<>();
    private List<ModelTable> pvitems = new ArrayList<>();
    private List<GoldInModel> giitems = new ArrayList<>();
    private static int chainOutIndex = 1;
    private static int pvIndex = 1;
    private static int giIndex = 1;
    private static double totalf = 0 ;
    @FXML
    private TableColumn<ModelTable,String> index;
    @FXML
    private TableColumn<ModelTable,String> cname;
    @FXML
    private TableColumn<ModelTable,String> touch;
    @FXML
    private TableColumn<ModelTable,String> cweight;
    @FXML
    private TableColumn<ModelTable,String> ytouch;
    @FXML
    private TableColumn<ModelTable,String> fweight;
    @FXML
    private TableView<ModelTable> pvtable;
    @FXML
    private TableColumn<ModelTable,String> pvindex;
    @FXML
    private TableColumn<ModelTable,String> pvcname;
    @FXML
    private TableColumn<ModelTable,String> pvtouch;
    @FXML
    private TableColumn<ModelTable,String> pvcweight;
    @FXML
    private TableColumn<ModelTable,String> pvytouch;
    @FXML
    private TableColumn<ModelTable,String> pvfweight;
    @FXML
    private TableView<GoldInModel> gitable;
    @FXML
    private TableColumn<GoldInModel,String> giindex;
    @FXML
    private TableColumn<GoldInModel,String> giweight;
    @FXML
    private TableColumn<GoldInModel,String> gitouch;
    @FXML
    private TableColumn<GoldInModel,String> gifweight;
    @FXML
    private TableColumn<GoldInModel,String> gitweight;
    @FXML
    private Label achainweight;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     chainincombobox.getItems().addAll("Arham Gold (AG)","Adinath Gold (AG)","Raju Patel (RP)","Hafiz Bhai (H)","Hanif Bhai(HC)","Suraj Chain (SMC)","Mahendra Bhai(MM)","Labh Laxmi(LLC)","Shaktibhai Handmade(SMC)","Jain Expo(JE)");
     goldoutcombobox.getItems().addAll("Arham Gold (AG)","Adinath Gold (AG)","Raju Patel (RP)","Hafiz Bhai (H)","Hanif Bhai(HC)","Suraj Chain (SMC)","Mahendra Bhai(MM)","Labh Laxmi(LLC)","Shaktibhai Handmade(SMC)","Jain Expo(JE)");
     printcombobox.getItems().addAll("Arham Gold (AG)","Adinath Gold (AG)","Raju Patel (RP)","Hafiz Bhai (H)","Hanif Bhai(HC)","Suraj Chain (SMC)","Mahendra Bhai(MM)","Labh Laxmi(LLC)","Shaktibhai Handmade(SMC)","Jain Expo(JE)");
     infocombobox.getItems().addAll("Arham Gold (AG)","Adinath Gold (AG)","Raju Patel (RP)","Hafiz Bhai (H)","Hanif Bhai(HC)","Suraj Chain (SMC)","Mahendra Bhai(MM)","Labh Laxmi(LLC)","Shaktibhai Handmade(SMC)","Jain Expo(JE)");
     chaininchainname.getItems().addAll("3box","2box","A","B","C","D","E","F","G","H","I");
     chainoutchainname.getItems().addAll("3box","2box","A","B","C","D","E","F","G","H","I");
     chaininchainname11.getItems().addAll("3box","2box","A","B","C","D","E","F","G","H","I");
     chainindtouch.getItems().addAll("77","82","84","86","88","92");
     chainouttouch.getItems().addAll("77","82","84","86","88","92");
     chainindtouch11.getItems().addAll("77","82","84","86","88","92");
     selling.setRoundingMode(RoundingMode.DOWN);
     taking.setRoundingMode(RoundingMode.UP);
     customer1.setRoundingMode(RoundingMode.UP);
     customer2.setRoundingMode(RoundingMode.DOWN);
     
     
     chaininchainname11.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
            
            cName = newValue;
            if(cTouch != null && cName != null){
                loadField(cTouch,cName);
                System.out.println("called");
            }
            
        }
});
        chainindtouch11.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
            
            cTouch = newValue;
            if(cTouch != null && cName != null){
                
                loadField(cTouch,cName);
                
                System.out.println("caled");
            }
            
          
        }
});
        
          chainoutchainname.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
            
            coName = newValue;
            if(coTouch != null && coName != null){
                loadChainWeight(coName,coTouch);
                System.out.println("called");
            }
            
        }
});
        chainouttouch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
            
            coTouch = newValue;
            if(coTouch != null && coName != null){
                
                loadChainWeight(coName,coTouch);
                
                System.out.println("caled");
            }
            
          
        }
});
      
      infocombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {

             loadDC(newValue);            
          
        }
});
    
                
                }
    
    
    private void loadDC(String cName){
        
        switch(cName){
            
            case "Arham Gold (AG)":
            
                      cName= "arhamgold";
                       break;
             case "Adinath Gold (AG)":
            
                     cName= "adinathgold";
                       break;
              case "Raju Patel (RP)":
            
                     cName= "rajupatel"; 
                       break;          
            case "Hafiz Bhai (H)":
                    
                       cName= "hafizbhai";
                       break;
             case "Hanif Bhai(HC)":
            
                     cName= "hanifbhai"; 
                       break;
              case "Suraj Chain (SMC)":
            
                     cName= "surajchain";
                       break;            
            case "Mahendra Bhai(MM)":
            
                      cName= "mahendrabhai";
                       break;
             case "Labh Laxmi(LLC)":
            
                    cName= "labhlaxmi";
                       break;
              case "Shaktibhai Handmade(SMC)":
            
                   cName= "shaktibhaihandmade";
                       break;            
              case "Jain Expo(JE)":
                   cName= "jainexpo";
            
                       break;                        
        }
        String sql = "SELECT * FROM " +cName.toLowerCase().trim();
       
       PreparedStatement statement; 
        try {
            statement = database.getChainInDb().prepareStatement(sql);
        
     
      ResultSet rs = statement.executeQuery();
       double bal=0,tgw=0;
       while(rs.next()){
           
          bal= rs.getDouble("balance");
          tgw = rs.getDouble("totalgrossweight");
       }
       infocredittgw.setTextFill(Color.BLACK);
       infocredittgw.setAlignment(Pos.CENTER);
       infocreditbalance.setTextFill(Color.BLACK);
       infocreditbalance.setAlignment(Pos.CENTER);
       infocredittgw.setText(""+tgw);
       infocreditbalance.setText(""+bal);
       
      statement = database.getGoldOutDb().prepareStatement(sql);
        
     bal=0;
     tgw=0;
       rs = statement.executeQuery();
       
       while(rs.next()){
           
          bal= rs.getDouble("totalbalance");
          tgw = rs.getDouble("totalgrossweight");
       }
       infodebittgw.setTextFill(Color.BLACK);
       infodebittgw.setAlignment(Pos.CENTER);
       infodebitbalance.setTextFill(Color.BLACK);
       infodebitbalance.setAlignment(Pos.CENTER);       
       infodebittgw.setText(""+tgw);
       infodebitbalance.setText(""+bal);
       

        } catch (SQLException ex) {
            Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void loadField(String cTouch,String cName){
        
         String sql = "SELECT * FROM _" +cTouch+  " WHERE chainname=?";
       
       PreparedStatement statement; 
        try {
            statement = database.getStock().prepareStatement(sql);
        
      statement.setString(1,cName);
      ResultSet rs = statement.executeQuery();
     
       if(rs.next()){
           System.out.println(""+rs.getDouble("grossweight"));
                    
                    infocredittgw1.setTextFill(Color.BLACK);
                    infocredittgw1.setAlignment(Pos.CENTER);
                    infocredittgw1.setText(""+rs.getDouble("grossweight"));
       }
        } catch (SQLException ex) {
            Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               
        
        
    }
    private Double[] getWeight(String src,double x1,double y1){
        
        double x =0, y=0;
        FileInputStream fin = null;
        FileOutputStream fout = null;

        try {
            fin = new FileInputStream(src);
            int read = -1;
            int p = 0;
            char b[] = new char[50];
            
            while((read = fin.read()) != -1){
                b[p++] = (char)read;
            }
            
            String str = new String(b);
            String arg[] = str.split("\\s");
            
                x =  Double.parseDouble(arg[0])+x1 ;
                y = Double.parseDouble(arg[1])+ y1; 
             fout = new FileOutputStream(src);
            
          
            String outs = x+ " "+ y;
            fout.write(outs.getBytes());
            
            
            
        } catch (IOException ex){
            Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
            fin.close();
            fout.close();
            }
            catch(IOException e){
                Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        
        return new Double[]{x,y};
        
    }
    
    private Double getWeight(String src,double x1,boolean isInc){
        
        double x =0, y=0;
        FileInputStream fin = null;
        FileOutputStream fout = null;

        try {
            fin = new FileInputStream(src);
            int read = -1;
            int p = 0;
            char b[] = new char[50];
            
            while((read = fin.read()) != -1){
                b[p++] = (char)read;
            }
            
            String str = new String(b);
            String arg[] = str.split("\\s");
                
            if(isInc){
                x =  Double.parseDouble(arg[0])+x1 ;
            }
            else{
                x =  Double.parseDouble(arg[0])-x1 ;
            }
             fout = new FileOutputStream(src);
            
          
            String outs = x+"";
            fout.write(outs.getBytes());
            
            
            
        } catch (IOException ex){
            Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
            fin.close();
            fout.close();
            }
            catch(IOException e){
                Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        
        return x;
        
    }
    
    
 private void insert(String name){
     
     Double info[] = getWeight("src/Files/goldout/"+name+ ".txt",0,0);
     
      double cifw = Double.parseDouble(bucket.get("chaininfineweight"));
     try{
         
   String  date=chainindate.getValue().toString();
   String chainname = chaininchainname.getValue().toString();

         System.out.println(bucket.get("chainintouch"));
       
       String sql = "INSERT INTO " + name  
               +"(chainname,touch,grossweight,yourtouch,fineweight,totalgrossweight,totalfineweight,balance,date) VALUES (?,?,?,?,?,?,?,?,?)";
       PreparedStatement statement = database.getChainInDb().prepareStatement(sql);  
       Double xy[] = getWeight("src/Files/chainin/"+name+ ".txt", Double.parseDouble(bucket.get("chainincweight")),cifw);
       double balance = info[1] - xy[1];
       chaininbalance.setText(String.valueOf(balance));
       chainintgw.setTextFill(Color.BLACK);
       chainintgw.setAlignment(Pos.CENTER);
       chainintgw.setText(String.valueOf(xy[0]));
       statement.setString(1,chainname);
       
       statement.setDouble(2,Double.parseDouble(bucket.get("chainintouch")));
       statement.setDouble(3,Double.parseDouble(bucket.get("chainincweight")));
       statement.setDouble(4,Double.parseDouble(bucket.get("chaininytouch")));
       statement.setDouble(5,Double.parseDouble(bucket.get("chaininfineweight")));
       
       statement.setDouble(6,xy[0]);
       statement.setDouble(7,xy[1]);
       statement.setDouble(8,balance) ; 
       statement.setString(9,date);
       
       statement.executeUpdate();
       
       
       
          
       }
       catch(SQLException ex){
           
       }
     }    
    @FXML
    private void chainindonebtn(ActionEvent event) throws IOException, SQLException {
        
          String  date=chainindate.getValue().toString();
        
        double weight=Double.parseDouble(chainincweight.getText());
        double touch=Double.parseDouble(chainintouch.getText());
        double ytouch=Double.parseDouble(chaininytouch.getText());
        double fweight=Double.parseDouble(selling.format(weight*(touch+ytouch)/100));
        
        chaininchainname.setEditable(false);
        chainintouch.setEditable(false);
        chainincweight.setEditable(false);
        chaininytouch.setEditable(false);
        String select =  chainincombobox.getValue();
        chaininfineweight.setText(""+fweight);
        
        bucket.put("chaininchainname",chaininchainname.getValue());
        bucket.put("chainintouch",chainintouch.getText());
        bucket.put("chainincweight",chainincweight.getText());
        bucket.put("chaininytouch",chaininytouch.getText());
        bucket.put("chaininfineweight",chaininfineweight.getText());
        bucket.put("chainindate",date);
        
       String sql = "SELECT * FROM _" +chainindtouch.getValue().toString()+  " WHERE chainname=?";
       
       PreparedStatement statement = database.getStock().prepareStatement(sql); 
      statement.setString(1,chaininchainname.getValue().toString());
      ResultSet rs = statement.executeQuery();
      double oldV = 0;
       if(rs.next()){
       
        oldV = rs.getDouble("grossweight");
        oldV += fweight;
        String sql2 = "update _" +chainindtouch.getValue().toString() +" set grossweight='" + oldV +"' WHERE chainname='"+chaininchainname.getValue().toString() +"'";
        statement = database.getStock().prepareStatement(sql2);
        
        
        statement.executeUpdate();
        
               }
        
          try{              
       switch(select){
           case "Arham Gold (AG)":
                         
                        insert("arhamgold");
                        break;
           case "Raju Patel (RP)":
                        insert("rajupatel");
                        break;
           case "Adinath Gold (AG)":
                        insert("adinathgold");
                        break;
           case "Hafiz Bhai (H)":
                        insert("hafizbhai");
                        break;
           case "Hanif Bhai(HC)":
                        insert("hanifbhai");
                        break;             
           
           case "Suraj Chain (SMC)":
                        insert("surajchain");
                        break;
           case "Labh Laxmi(LLC)":
                        insert("labhlaxmi");
                        break;
           case "Shaktibhai Handmade(SMC)":
                        insert("shaktibhaihandmade");
                        break;
           case "Mahendra Bhai(MM)":
                        insert("mahendrabhai");
                        break;
           case "Jain Expo(JE)":
                        insert("jainexpo");
                        break;
       }
        
        }
        
        catch(Exception e)
        {
           Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, e);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Error.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Error !!");
            stage.setScene(new Scene(root1));  
            stage.show();
        }
        
    
    }
    
    private void goinsert(String tablename){
        double gofw = Double.parseDouble(bucket.get("goldoutfweight"));
         Double xy[] = getWeight("src/Files/goldout/"+tablename+ ".txt", Double.parseDouble(bucket.get("goldoutgweight")),gofw);
        Double info[] = getWeight("src/Files/chainin/"+tablename+ ".txt",0,0);
        double balance = xy[1] - info[1];
        try{
         
   String  date=goldoutdate.getValue().toString();
//   String chainname = chaininchainname.getValue().toString();

         System.out.println(bucket.get("chainintouch"));
       
       String sql = "INSERT INTO " + tablename  +"(goldinfo,grossweight,puritytouch,fine,totalgrossweight,totalfineweight,totalbalance,date) VALUES (?,?,?,?,?,?,?,?)";
       PreparedStatement statement = database.getGoldOutDb().prepareStatement(sql);  
       
       goldoutbalance.setText(String.valueOf(balance));
       goldouttgw.setTextFill(Color.BLACK);
       goldouttgw.setAlignment(Pos.CENTER);
       goldouttgw.setText(String.valueOf(xy[0]));
       statement.setString(1,bucket.get("goldoutinfo"));
       
       statement.setDouble(2,Double.parseDouble(bucket.get("goldoutgweight")));
       statement.setDouble(3,Double.parseDouble(bucket.get("goldouttouch")));
       statement.setDouble(4,Double.parseDouble(bucket.get("goldoutfweight")));
       statement.setDouble(5,xy[0]);
       
       statement.setDouble(6,xy[1]);
       statement.setDouble(7,balance); //balance
       
       statement.setString(8,date);
       
       statement.executeUpdate();
       
       
       
          
       }
       catch(SQLException ex){
           
       }
    }
    
    private void goldgrossinsert(double x){
         String sql = "INSERT INTO gold (grossweight) VALUES (?)";
       PreparedStatement statement;  
        try {
            statement = database.getStock().prepareStatement(sql);
            statement.setDouble(1,x);
            System.out.println("siuceed + "+x);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("siuceed");
        }
       
      
        
    }
    @FXML
    private void chaininclrbtn(ActionEvent event) {
      
      
        chainintouch.setEditable(true);
        chainincweight.setEditable(true);
        chaininytouch.setEditable(true);
       
        chainintouch.setText("");
        chainincweight.setText("");
        chaininytouch.setText("");
        chaininfineweight.setText("");
      
    }

    @FXML
    private void goldoutdonebtn(ActionEvent event) throws IOException {
        String date="";
        
 try{ 
         goldouttouch.setEditable(false);
         goldoutgweight.setEditable(false);
         goldoutinfo.setEditable(false);
         double weight=Double.parseDouble(goldoutgweight.getText());
         double touch=Double.parseDouble(goldouttouch.getText());
         double fweight=weight*touch/100;
         fweight=Double.parseDouble(taking.format(fweight));
         double x = getWeight("src/Files/stock/goldgrossweight.txt",fweight,false);
         goldgrossinsert(x);
           date=goldoutdate.getValue().toString();
           
        
            goldoutfineweight.setText(""+fweight);
            
        bucket.put("goldoutinfo",goldoutinfo.getText());
        bucket.put("goldoutgweight",goldoutgweight.getText());
        bucket.put("goldouttouch",goldouttouch.getText());
        bucket.put("goldoutfweight",goldoutfineweight.getText());
        bucket.put("goldoutdate",date);
            
            String select = goldoutcombobox.getValue().toString();
            
             switch(select){
           case "Arham Gold (AG)":
                         
                        goinsert("arhamgold");
                        break;
           case "Raju Patel (RP)":
                        goinsert("rajupatel");
                        break;
           case "Adinath Gold (AG)":
                        goinsert("adinathgold");
                        break;
           case "Hafiz Bhai (H)":
                        goinsert("hafizbhai");
                        break;
           case "Hanif Bhai(HC)":
                        goinsert("hanifbhai");
                        break;             
           
           case "Suraj Chain (SMC)":
                        goinsert("surajchain");
                        break;
           case "Labh Laxmi(LLC)":
                        goinsert("labhlaxmi");
                        break;
           case "Shaktibhai Handmade(SMC)":
                        goinsert("shaktibhaihandmade");
                        break;
           case "Mahendra Bhai(MM)":
                        goinsert("mahendrabhai");
                        break;
           case "Jain Expo(JE)":
                        goinsert("jainexpo");
                        break;
       }
      
        }
       
        catch(Exception e)
        {
             Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, e);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Error.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Error !!");
            stage.setScene(new Scene(root1));  
            stage.show();

        }
        
         
    }

    @FXML
    private void goldoutclrbtn(ActionEvent event) {
    
        goldouttouch.setText("");
         goldoutgweight.setText("");
           goldoutfineweight.setText("");
           
            goldouttouch.setEditable(true);
         goldoutgweight.setEditable(true);
         goldoutinfo.setEditable(true);
          
          
    }

    private void loadChainWeight(String cName,String cTouch){
        try {
            String sql = "SELECT * FROM _" +cTouch+  " WHERE chainname=?";
            
            PreparedStatement statement = database.getStock().prepareStatement(sql);
            statement.setString(1,cName);
            ResultSet rs = statement.executeQuery();
            double oldV = 0;
            if(rs.next()){
                
                oldV = rs.getDouble("grossweight");
               
        infocredittgw11.setText(""+oldV);
        infocredittgw11.setTextFill(Color.BLACK);
       infocredittgw11.setAlignment(Pos.CENTER);
       
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
    }
    @FXML
    private void chainoutdonebtn(ActionEvent event) throws IOException {
        String date="";
        try{
            
            chainoutchainname.setEditable(false);
        chainouttouch.setEditable(false);
        chainoutchainweight.setEditable(false);
        chainoutytouch.setEditable(false);
        date=chainoutdate.getValue().toString();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate ldate = chainoutdate.getValue();
        this.date =formatter.format(ldate).toString();
        
        this.pName = chainoutname.getText().toString();
        double weight=Double.parseDouble(chainoutchainweight.getText());
        double touch=Double.parseDouble(chainouttouch.getValue());
        double ytouch=Double.parseDouble(chainoutytouch.getText());
        double fweight=weight*(touch+ytouch)/100;
        fweight=Double.parseDouble(customer1.format(fweight));
        chainoutfineweight.setText(""+fweight);
        cashinremainweight.setText(""+fweight);
        
        String sql = "SELECT * FROM _" +chainouttouch.getValue().toString()+  " WHERE chainname=?";
       
       PreparedStatement statement = database.getStock().prepareStatement(sql); 
      statement.setString(1,chainoutchainname.getValue().toString());
      ResultSet rs = statement.executeQuery();
      double oldV = 0;
       if(rs.next()){
       
        oldV = rs.getDouble("grossweight");
        
        
        oldV -= fweight;
        infocredittgw11.setText(""+oldV);
        infocredittgw11.setTextFill(Color.BLACK);
       infocredittgw11.setAlignment(Pos.CENTER);
        String sql2 = "update _" +chainouttouch.getValue().toString() +" set grossweight='" + oldV +"' WHERE chainname='"+chainoutchainname.getValue().toString() +"'";
        statement = database.getStock().prepareStatement(sql2);
        
        
        statement.executeUpdate();
        
               }
 
        
       this.index.setCellValueFactory(new PropertyValueFactory<>("index"));
       this.cname.setCellValueFactory(new PropertyValueFactory<>("chainname"));
       this.touch.setCellValueFactory(new PropertyValueFactory<>("touch"));
       this.cweight.setCellValueFactory(new PropertyValueFactory<>("chainweight"));
       this.ytouch.setCellValueFactory(new PropertyValueFactory<>("ytouch"));
       this.fweight.setCellValueFactory(new PropertyValueFactory<>("fweight"));
       
        
        
        chainouttable.setItems(getTableData());
        
        
        
        
        }
        
        catch(Exception e)
        {
                      Logger.getLogger(MainpageController.class.getName()).log(Level.SEVERE, null, e);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Error.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Error !!");
            stage.setScene(new Scene(root1));  
            stage.show();

        }
        bucket.put("chainoutpersonname",chainoutname.getText());
        bucket.put("chainoutchainname",chainoutchainname.getValue());
        bucket.put("chainouttouch",chainouttouch.getValue());
        bucket.put("chainoutchainweight",chainoutchainweight.getText());
        bucket.put("chainoutytouch",chainoutytouch.getText());
        bucket.put("chainoutfweight",chainoutfineweight.getText());
        bucket.put("chainoutate",date);
        
        
        
    }
    
     private ObservableList getTableData() {

         ModelTable cot =  new ModelTable(String.valueOf(chainOutIndex++),chainoutchainname.getValue().toString(),chainouttouch.getValue().toString(),
                                              chainoutchainweight.getText().toString() ,chainoutytouch.getText().toString() ,chainoutfineweight.getText().toString());
       
                 items.add(cot);
         ObservableList  data = FXCollections.observableList(items);
        

         
         return data;
    }


    @FXML
    private void chainoutclrbtn(ActionEvent event) {
       
        chainoutchainweight.setText("");
        chainoutytouch.setText("");
        chainoutfineweight.setText("");
       
        chainoutchainweight.setEditable(true);
        chainoutytouch.setEditable(true);

    }

    @FXML
    private void chainoutprintbtn(ActionEvent event) throws FileNotFoundException, DocumentException {
        
       new ModelPdf(items,this.date,this.pName).print();
       chainouttable.setItems(null);
       items.clear();
       chainOutIndex = 1;
    }

    @FXML
    private void chainincombo(ActionEvent event) {
    }

    @FXML
    private void goldoutcombo(ActionEvent event) {
    }

    @FXML
    private void cashindonebtn(ActionEvent event) throws IOException {
        
        double check=Double.parseDouble(bucket.get("chainoutfweight"));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate ldate = cashindate.getValue();
        this.date =formatter.format(ldate).toString();
        this.pName = cashinname.getText().toString();
        
        try{
        cashingoldweight.setEditable(false);
        cashintouch.setEditable(false);
       
        double weight=Double.parseDouble(cashingoldweight.getText());
        double touch=Double.parseDouble(cashintouch.getText());
        double fweight=weight*touch/100;
        fweight=Double.parseDouble(customer2.format(fweight));
            double x = getWeight("src/Files/stock/goldgrossweight.txt",fweight,true);
         goldgrossinsert(x);
        
        cashinfinalweight.setText(""+fweight);
        check=Double.parseDouble(cashinremainweight.getText());
        check=check-fweight;
        check=Double.parseDouble(customer1.format(check));
         cashinremainweight.setText(""+check);
            
        }
        catch(Exception e)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Error.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Error !!");
            stage.setScene(new Scene(root1));  
            stage.show();

        }
        
        
       try{
        double price=Double.parseDouble(cashingoldprice.getText());
        double cash=price*check/10;
        cash=Double.parseDouble(money.format(cash));
        cashincash.setText(""+cash);}
       catch(Exception e)
       {
       double price=30000;
        double cash=price*check/10;
        cashincash.setText(""+cash);
       }
        
        
    }

    @FXML
    private void cashinclrbtn(ActionEvent event) {
         cashingoldweight.setEditable(true);
        cashintouch.setEditable(true);
        cashintouch.setText("");
        cashingoldweight.setText("");
        cashinfinalweight.setText("");
        cashincash.setText("");
    }

    @FXML
    private void cashincheckbtn(ActionEvent event) {
    }

    @FXML
    private void cashinaddbtn(ActionEvent event) {
        
       this.giindex.setCellValueFactory(new PropertyValueFactory<>("index"));
       this.giweight.setCellValueFactory(new PropertyValueFactory<>("goldweight"));
       this.gitouch.setCellValueFactory(new PropertyValueFactory<>("touch"));
       this.gifweight.setCellValueFactory(new PropertyValueFactory<>("fineweight"));
       this.gitweight.setCellValueFactory(new PropertyValueFactory<>("totalfineweight"));
       
       totalf+= Double.parseDouble(cashinfinalweight.getText().toString());
       GoldInModel cot =  new GoldInModel(String.valueOf(giIndex++),cashingoldweight.getText().toString(),cashintouch.getText().toString(),
                                              cashinfinalweight.getText().toString() ,String.valueOf(totalf));
       
                 giitems.add(cot);
         ObservableList  data = FXCollections.observableList(giitems);
        
       gitable.setItems(data);
     
    }

    @FXML
    private void billingdonebtn(ActionEvent event) throws IOException {
       try{
        
        
        billinggoldprice.setEditable(false);
        billingtotalmoney.setEditable(false);
        double gprice=Double.parseDouble(billinggoldprice.getText());
        double tmoney=Double.parseDouble(billingtotalmoney.getText());
        double imoney=tmoney/1.03;
        double goldweight=imoney/gprice*10;
        goldweight=Double.parseDouble(regular.format(goldweight));
        imoney=Double.parseDouble(money.format(imoney));
        double cgst=imoney*1.5/100;
        cgst=Double.parseDouble(money.format(cgst));
        double igst =cgst;
        billingcgst.setText(""+cgst);
        billingigst.setText(""+igst);
        billinginitialprice.setText(""+imoney);
        billinggoldweight.setText(""+goldweight);
        bucket.put("billingname",billingname.getText());
        bucket.put("billingtotalmoney",billingtotalmoney.getText());
        bucket.put("billingcgst",billingcgst.getText());
        bucket.put("billingigst",billingigst.getText());
        bucket.put("billinginitialprice",billinginitialprice.getText());
        bucket.put("billinggoldweight",billinggoldweight.getText());
        bucket.put("billingdate",billingdate.getValue().toString());
       }
        
        catch(Exception e)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Error.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Error !!");
            stage.setScene(new Scene(root1));  
            stage.show();

        }
        
                
    }

    @FXML
    private void billingclrbtn(ActionEvent event) {
        billinggoldprice.setEditable(true);
        billingtotalmoney.setEditable(true);
        
        billingtotalmoney.setText("");
        billingcgst.setText("");
        billingigst.setText("");
        billinginitialprice.setText("");
        billinggoldweight.setText("");
        
    }

    @FXML
    private void billingprintbtn(ActionEvent event) {
    }

    @FXML
    private void printcombo(ActionEvent event) {
    }

    @FXML
    private void printdonebtn(ActionEvent event) throws IOException {
       try{
        
        printchainname.setEditable(false);
       printtouch.setEditable(false);
       printchainweight.setEditable(false);
       printytouch.setEditable(false);
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate ldate = printdate.getValue();
        this.date =formatter.format(ldate).toString();
       this.pName = printperson.getText().toString();
       double touch=Double.parseDouble(printtouch.getText());
       double weight=Double.parseDouble(printchainweight.getText());
       double ytouch=Double.parseDouble(printytouch.getText());
       double fweight=weight*(touch+ytouch)/100;
       fweight=Double.parseDouble(selling.format(fweight));
       printyfineweight.setText(""+fweight);
       
       
       this.pvindex.setCellValueFactory(new PropertyValueFactory<>("index"));
       this.pvcname.setCellValueFactory(new PropertyValueFactory<>("chainname"));
       this.pvtouch.setCellValueFactory(new PropertyValueFactory<>("touch"));
       this.pvcweight.setCellValueFactory(new PropertyValueFactory<>("chainweight"));
       this.pvytouch.setCellValueFactory(new PropertyValueFactory<>("ytouch"));
       this.pvfweight.setCellValueFactory(new PropertyValueFactory<>("fweight"));
       
       ModelTable cot =  new ModelTable(String.valueOf(pvIndex++),printchainname.getText().toString(),printtouch.getText().toString(),
                                              printchainweight.getText().toString() ,printytouch.getText().toString() ,printyfineweight.getText().toString());
       
                 pvitems.add(cot);
         ObservableList  data = FXCollections.observableList(pvitems);
        
       pvtable.setItems(data);
     
        }
        catch(Exception e)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Error.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Error !!");
            stage.setScene(new Scene(root1));  
            stage.show();

        }
       
              
    }

    @FXML
    private void printclrbtn(ActionEvent event) {
       printchainname.setText("");
       printtouch.setText("");
       printchainweight.setText("");
       printytouch.setText("");
       printyfineweight.setText("");
        printchainname.setEditable(true);
       printtouch.setEditable(true);
       printchainweight.setEditable(true);
       printytouch.setEditable(true);
    }

    @FXML
    private void printprintbtn(ActionEvent event) throws FileNotFoundException, DocumentException {
        
        new ModelPdf(pvitems,this.pName,this.date).print();
       pvtable.setItems(null);
       pvitems.clear();
       pvIndex = 1;
    }

    @FXML
    private void infocombo(ActionEvent event) {
    }

    @FXML
    private void cashinresetbtn(ActionEvent event) throws DocumentException, FileNotFoundException {
       cashingoldweight.setEditable(true);
        cashintouch.setEditable(true);
        cashintouch.setText("");
        cashingoldweight.setText("");
        cashinfinalweight.setText("");
        cashincash.setText("");
        cashingoldprice.setText("");
        cashinremainweight.setText("");
        
        new GoldInPdf(giitems,pName,date).print();
       gitable.setItems(null);
       giitems.clear();
       giIndex = 1;
       totalf = 0;
    }



 
    
}
