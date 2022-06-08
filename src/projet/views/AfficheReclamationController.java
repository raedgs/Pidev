/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.views;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import projet.base.ConnectionBd;
import projet.crud.CrudKeyValue;
import projet.crud.CrudReclamation;
import projet.entites.Reclamation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficheReclamationController implements Initializable {

    private Reclamation reclamation;
    @FXML
    private TableView<CrudKeyValue> AffReclamation;
    @FXML
    private TableColumn<CrudKeyValue, Integer> id;
    @FXML
    private TableColumn<CrudKeyValue, String> type;
    @FXML
    private TableColumn<CrudKeyValue, String> etat;
    @FXML
    private TableColumn<CrudKeyValue, String> desc;
    ObservableList<CrudKeyValue> obsReclamation = FXCollections.observableArrayList();
    private CrudKeyValue CrudKeyValue;
    @FXML
    private Text msj;
    @FXML
    private TextField recherche;
    @FXML
    private AnchorPane report88;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadData();
    }

    private void LoadData() {
        CrudReclamation Cr = new CrudReclamation();

        Cr.ListJoiture().stream().forEach((p) -> {
            obsReclamation.add(p);
        });

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        AffReclamation.setItems(obsReclamation);

               recherche.textProperty().addListener((obs, oldText, newText) -> {
                   List<CrudKeyValue> ae = Cr.Search(newText);
            AffReclamation.getItems().setAll(ae);

        });

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        CrudReclamation cr = new CrudReclamation();
        CrudKeyValue crr = new CrudKeyValue();

        ObservableList obsUtilisateurlist, Pointage;
        obsUtilisateurlist = AffReclamation.getItems();
        Pointage = AffReclamation.getSelectionModel().getSelectedItems();
        crr = AffReclamation.getSelectionModel().getSelectedItems().get(0);

        cr.supprimer(new Reclamation(crr.getId()));

        msj.setText("Champ suprimee !");
        Pointage.forEach(obsUtilisateurlist::remove);

    }

    @FXML
    private void genererPdf(ActionEvent event) {
        
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
             Window primaryStage = null;
             job.showPrintDialog(primaryStage); 
            
             Node root=this.report88
;   
              job.printPage(root);
              
              job.endJob(); 

    
        
    }
    }

    @FXML
    private void onTableItemSelect(MouseEvent event) {
        CrudKeyValue = AffReclamation.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void AjouterType(ActionEvent event) throws IOException {
         Parent page2 = FXMLLoader.load(getClass().getResource("AjoutType.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    @FXML
    private void ListeType(ActionEvent event) throws IOException {
         Parent page2 = FXMLLoader.load(getClass().getResource("AfficheType.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
}
