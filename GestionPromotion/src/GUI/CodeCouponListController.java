/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import gestionpromotion.Entity.CodeCoupon;
import gestionpromotion.Service.CodeCouponService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class CodeCouponListController implements Initializable {

    @FXML
    private TextField PourcentageTf;

    @FXML
    private TableView<CodeCoupon> CodeCouponTab;
    @FXML
    private TableColumn<CodeCoupon, Integer> IDCouponTab;
    @FXML
    private TableColumn<CodeCoupon, String> CodeTab;
    @FXML
    private TableColumn<CodeCoupon, Integer> PourcentageTab;
    @FXML
    private TextField TFSearch;
    @FXML
    private Label NombreCoupon;
    @FXML
    private TextField CodeCouponTf;
    /**
     * Initializes the controller class.
     */
    
    CodeCouponService Ccoupon =new CodeCouponService();
    int id;
    CodeCoupon c;
    ObservableList<CodeCoupon> data=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            NombreCoupon.setText(Integer.toString(Ccoupon.nbCoupon()));
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

                public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(Ccoupon.afficher());
            //System.out.println(data);
            FilteredList<CodeCoupon> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    else if(p.getCode().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    if (String.valueOf(p.getPourcentage_p()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<CodeCoupon> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(CodeCouponTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		CodeCouponTab.setItems(sortedData);
             
        }
            
        public void refreshlist() throws SQLException{
            data.clear();
            data = FXCollections.observableArrayList(Ccoupon.afficher());
            CodeCouponTab.setEditable(true);
            CodeCouponTab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            IDCouponTab.setCellValueFactory(new PropertyValueFactory<>("id"));
            CodeTab.setCellValueFactory(new PropertyValueFactory<>("code"));
            PourcentageTab.setCellValueFactory(new PropertyValueFactory<>("pourcentage_p"));
            CodeCouponTab.setItems(data);
    }
        
    @FXML
    private void AjouterCodeCoupon(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/AjouterCodeCoupon.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCodeCouponController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteCoupon(ActionEvent event) {
                int Id;
        Id=CodeCouponTab.getSelectionModel().getSelectedItem().getId();
        try {
            Ccoupon.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateCoupon() throws SQLException{
        CodeCoupon c = new CodeCoupon( CodeCouponTf.getText(),
                                        Integer.parseInt(PourcentageTf.getText()));
        Ccoupon.modifier(id, c);
        refreshlist(); 
    }
    @FXML
    private void EditCoupon(ActionEvent event) {
        try {
            updateCoupon();
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CodeCouponListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fillforum(MouseEvent event) {
        CodeCoupon c=CodeCouponTab.getSelectionModel().getSelectedItem();
        id=c.getId();
        CodeCouponTf.setText(c.getCode());
        PourcentageTf.setText(Integer.toString(c.getPourcentage_p()));
    }
    
}
