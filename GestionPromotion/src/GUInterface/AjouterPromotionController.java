/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUInterface;

import gestionpromotion.Entity.Promotion;
import gestionpromotion.Service.ServicePromotion;
import gestionpromotion.Utils.MailerApi;
import gestionpromotion.Utils.SMSApi;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    @FXML
    private TextField CodeCouponTf;
    @FXML
    private TextField DescriptionTf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Acceuil(ActionEvent event) {
    }

    @FXML
    private void Enregister(ActionEvent event) {
                      String erreurs="";
        if(PourcentageTf.getText().trim().isEmpty()){
            erreurs+="Pourcentage vide\n";
        }
        if(PromotionCouponTf.getText().trim().isEmpty()){
            erreurs+="Promotion Coupon vide\n";
        }
        if(CodeCouponTf.getText().trim().isEmpty()){
            erreurs+="Code Coupon vide\n";
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
        Promotion p;
                          p = new Promotion(dateDebut.getValue().atStartOfDay(),
                                  dateFin.getValue().atStartOfDay(),
                                  DescriptionTf.getText(),
                                  Integer.parseInt(PourcentageTf.getText()),
                                  Integer.parseInt(CodeCouponTf.getText()));
                                                 
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
        mailer.SendMail("mahdi.homrani@esprit.tn", "Admin.");
        
                //SEND SMS
        SMSApi sms = new SMSApi();
        sms.sendSMS("+21658932889", "Admin.");
    }
    }

    @FXML
    private void afficherPromotion(ActionEvent event) {
    }

}
