/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import projet.crud.CrudKeyValue;
import projet.crud.CrudReclamation;
import projet.crud.CrudTypeReclamation;
import projet.entites.Reclamation;
import projet.entites.Typereclamation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutreclamationController implements Initializable {

    @FXML
    private TextField etat;
    @FXML
    private TextField desc;
    int idType;
    @FXML
    private ChoiceBox<Typereclamation> TypeField;
        CrudTypeReclamation cr = new CrudTypeReclamation();
    @FXML
    private Text textError;
    @FXML
    private Text succ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cr.afficher().forEach((p)->{
        TypeField.getItems().add(new Typereclamation(p.getId(),p.getType()));      
        TypeField.setOnAction(event -> {
        idType=TypeField.getValue().getId();  
              });  
        });  


    }    

    @FXML
    private void Submit(ActionEvent event) {

   if(etat.getText().equals("")||desc.getText().equals("")||TypeField.equals("")){
       textError.setText("Tous les champs Sont obligatoire !!");
   }else{
       CrudReclamation crr = new CrudReclamation();
                         String descript  = crr.makeFine(desc.getText());

       crr.ajouter(new Reclamation(descript,etat.getText(),idType));
              textError.setText("");
              succ.setText("Ajout Avec succes !!");
   }

    }
    
}
