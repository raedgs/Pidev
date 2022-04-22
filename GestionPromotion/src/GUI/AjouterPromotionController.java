/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import com.jfoenix.controls.JFXComboBox;
import gestionpromotion.Entity.Promotion;
import gestionpromotion.Service.CodeCouponService;
import gestionpromotion.Service.ServicePromotion;
import gestionpromotion.Utils.MailerApi;
import gestionpromotion.Utils.Myconnexion;
import gestionpromotion.Utils.SMSApi;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AjouterPromotionController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField PourcentageTf;
    private TextField PromotionCouponTf;
    @FXML
    private ImageView image_view;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    private TextField CodeCouponTf;
    @FXML
    private TextField DescriptionTf;
    @FXML
    private JFXComboBox<String> cb_coupon;

    /**
     * Initializes the controller class.
     */
    
        CodeCouponService Cs ;
  ObservableList<String> options=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillcombo();
    }   
    
   public void fillcombo(){
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            String req = " select * from codecoupone ";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("code"));
                
            }
            cb_coupon.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(AjouterPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void Acceuil(ActionEvent event) {
                            try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/HomePage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Enregister(ActionEvent event) {
                      String erreurs="";
        if(PourcentageTf.getText().trim().isEmpty()){
            erreurs+="Pourcentage vide\n";
        }
        if(DescriptionTf.getText().trim().isEmpty()){
            erreurs+="Promotion Coupon vide\n";
        }
        if(dateDebut.getValue()==null){
            erreurs+="date Debut vide\n";
        }
        if(dateFin.getValue()==null){
            erreurs+="date Fin vide\n";
        }
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Promotion");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        ServicePromotion Sp = new ServicePromotion();
        Promotion p = new Promotion(dateDebut.getValue().atStartOfDay(),
                                    dateFin.getValue().atStartOfDay(),
                                    DescriptionTf.getText(),
                                    Integer.parseInt(PourcentageTf.getText()),
                                    cb_coupon.getValue());
                                                 
                  Sp.ajouter(p);         
                                                                                 //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Reclamation créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(10000));
        
        // SEND MAIL
        MailerApi mailer = new MailerApi();
        mailer.SendMail("raed.guesmi@esprit.tn", "Admin.");
        
                //SEND SMS
        SMSApi sms = new SMSApi();
        sms.sendSMS("+21629774931", "Admin.");
    }
    }

    @FXML
    private void afficherPromotion(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/PromotionList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PromotionListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
