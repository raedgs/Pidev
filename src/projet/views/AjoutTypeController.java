/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projet.crud.CrudTypeReclamation;
import projet.entites.Typereclamation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutTypeController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private Text err;
    @FXML
    private Text succ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit(ActionEvent event) {
        if(type.getText().equals(""))
        {
                        succ.setText("");

            err.setText("Champ Obligatoire !");
        }else{
            CrudTypeReclamation crt = new CrudTypeReclamation();
            crt.ajouter(new Typereclamation(type.getText()));
            succ.setText("ajout avec succes");
                        err.setText("");

        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
                Parent page2 = FXMLLoader.load(getClass().getResource("AfficheReclamation.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();

    }
    
}
