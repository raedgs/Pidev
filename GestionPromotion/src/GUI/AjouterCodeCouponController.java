/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import gestionpromotion.Entity.CodeCoupon;
import gestionpromotion.Service.CodeCouponService;
import gestionpromotion.Utils.MailerApi;
import gestionpromotion.Utils.MailerApiCoupon;
import gestionpromotion.Utils.SMSApi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class AjouterCodeCouponController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField CodeCouponTf;
    @FXML
    private TextField PromotionCouponTf;
    @FXML
    private ImageView image_view;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        if(CodeCouponTf.getText().trim().isEmpty()){
            erreurs+="Code Coupon vide\n";
        }
        if(PromotionCouponTf.getText().trim().isEmpty()){
            erreurs+="Promotion Coupon vide\n";
        }
       
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Coupon");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        CodeCouponService Ccoupon = new CodeCouponService();
        CodeCoupon c = new CodeCoupon( CodeCouponTf.getText(),
                                        Integer.parseInt(PromotionCouponTf.getText()));
                                                  

                  Ccoupon.ajouter(c);        
                                                                                 //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Votre Coupon créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(10000));
        
        // SEND MAIL
        MailerApiCoupon mailer = new MailerApiCoupon();
        mailer.SendMail("raed.guesmi@esprit.tn", "Admin.");

    }
    }

    @FXML
    private void afficherCoupon(ActionEvent event) {
                 try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/CodeCouponList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCodeCouponController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
