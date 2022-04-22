/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import com.jfoenix.controls.JFXComboBox;
import gestionpromotion.Entity.Promotion;
import gestionpromotion.Service.CodeCouponService;
import gestionpromotion.Service.ServicePromotion;
import gestionpromotion.Utils.JfreeChartApi;
import gestionpromotion.Utils.Myconnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
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
import javafx.scene.control.DatePicker;
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
public class PromotionListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField PourcentageTf;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Promotion> PromotionTab;
    @FXML
    private TableColumn<Promotion, Integer> IDPromotionTab;
    @FXML
    private TableColumn<Promotion, String> DescriptionTab;
    @FXML
    private TableColumn<Promotion, Integer> PourcentageTab;
    @FXML
    private TableColumn<Promotion, Integer> CodeCouponTab;
    @FXML
    private TableColumn<Promotion, LocalDateTime> DateDebutTab;
    @FXML
    private TableColumn<Promotion, LocalDateTime> DateFinTab;
    @FXML
    private TextField TFSearch;
    @FXML
    private Label NombrePromotion;
    private TextField CodeCouponTf;
    @FXML
    private TextField DescriptionTf;
    @FXML
    private Button Statistique;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;

    
    ServicePromotion sp =new ServicePromotion();
    int id;
    Promotion p;
    ObservableList<Promotion> data=FXCollections.observableArrayList();
    CodeCouponService Cs ;
    ObservableList<String> options=FXCollections.observableArrayList();
    
    @FXML
    private JFXComboBox<String> cb_coupon;
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            NombrePromotion.setText(Integer.toString(sp.nbPromotion()));
            refreshlist();
            fillcombo();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionListController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
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
    private void AjouterPromotion(ActionEvent event) {
                    try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/AjouterPromotion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(sp.afficher());
            //System.out.println(data);
            FilteredList<Promotion> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    else if(String.valueOf(p.getDated()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getDatef()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(p.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getPourcentage()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }  
                    else if(String.valueOf(p.getCodecoupone_id()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }                        
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Promotion> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(PromotionTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		PromotionTab.setItems(sortedData);   
        }
     
         public void refreshlist() throws SQLException{
            data.clear();
            data = FXCollections.observableArrayList(sp.afficher());
            PromotionTab.setEditable(true);
            PromotionTab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            IDPromotionTab.setCellValueFactory(new PropertyValueFactory<>("id"));
            DescriptionTab.setCellValueFactory(new PropertyValueFactory<>("description"));
            PourcentageTab.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
            CodeCouponTab.setCellValueFactory(new PropertyValueFactory<>("codecoupone_id"));
            DateDebutTab.setCellValueFactory(new PropertyValueFactory<>("dated"));
            DateFinTab.setCellValueFactory(new PropertyValueFactory<>("datef"));
            PromotionTab.setItems(data);
    }
    
     
    @FXML
    private void DeletePromotion(ActionEvent event) {
        int Id;
        Id=PromotionTab.getSelectionModel().getSelectedItem().getId();
        try {
            sp.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePromotion() throws SQLException{
        Promotion p = new Promotion(dateDebut.getValue().atStartOfDay(),
                                    dateFin.getValue().atStartOfDay(),
                                    DescriptionTf.getText(),
                                    Integer.parseInt(PourcentageTf.getText()),
                                    cb_coupon.getValue());
        sp.modifier(id, p);
        refreshlist(); 
    }

    @FXML
    private void EditPromotion(ActionEvent event) {
                try {
            updatePromotion();
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fillforum(MouseEvent event) {
        Promotion p=PromotionTab.getSelectionModel().getSelectedItem();
        id=p.getId();
        DescriptionTf.setText(p.getDescription());
        PourcentageTf.setText(Integer.toString(p.getPourcentage()));
        cb_coupon.setValue(p.getCodecoupone_id());
        dateDebut.setValue(p.getDated().toLocalDate());
        dateFin.setValue(p.getDatef().toLocalDate());
    }

    @FXML
    private void Statistique(ActionEvent event) {
        HashMap<String, Double> data = sp.StatistiquePoucentage();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }
    
}
