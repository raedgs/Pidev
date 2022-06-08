/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projet.crud.CrudTypeReclamation;
import projet.entites.Typereclamation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficheTypeController implements Initializable {

    @FXML
    private TableColumn<Typereclamation, Integer> id;
    @FXML
    private TableColumn<Typereclamation, String> type;
    @FXML
    private TableView<Typereclamation> typeRec;
    ObservableList<Typereclamation> obsReclamation = FXCollections.observableArrayList();
    private Typereclamation Typereclamation;
    @FXML
    private Text msj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadData();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        CrudTypeReclamation cr = new CrudTypeReclamation();
        Typereclamation tyr = new Typereclamation();

        ObservableList obsUtilisateurlist, Pointage;
        obsUtilisateurlist = typeRec.getItems();
        Pointage = typeRec.getSelectionModel().getSelectedItems();
        tyr = typeRec.getSelectionModel().getSelectedItems().get(0);

        cr.supprimer(new Typereclamation(tyr.getId()));

        msj.setText("Champ suprimee !");
        Pointage.forEach(obsUtilisateurlist::remove);

    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("AfficheReclamation.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    private void LoadData() {
        
                typeRec.setEditable(true);

        CrudTypeReclamation Cr = new CrudTypeReclamation();

        Cr.afficher().stream().forEach((p) -> {
            obsReclamation.add(p);
        });

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Typereclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Typereclamation, String> event) {
                Typereclamation uti = event.getRowValue();
                uti.setType(event.getNewValue());
                CrudTypeReclamation utIm = new CrudTypeReclamation();
                utIm.modifier(uti);
                msj.setText("Champ modifer!");
//uti.modifier(new Utilisateur(Utilisateur.getIdU(), nom.getText(), prenom.getText(), adresse.getText(), email.getText(), photo.getText(), pays.getText(), role.getText(), Integer.parseInt(isActive.getText())));

            }

        });
        typeRec.setItems(obsReclamation);

    }

    @FXML
    private void onTableItemSelect(MouseEvent event) {
        Typereclamation = typeRec.getSelectionModel().getSelectedItem();

    }

}
