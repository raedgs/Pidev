/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionpromotion;

import gestionpromotion.Entity.CodeCoupon;
import gestionpromotion.Entity.Promotion;
import gestionpromotion.Service.CodeCouponService;
import gestionpromotion.Service.ServicePromotion;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahdi
 */
public class GestionPromotion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException  {
/******************************************************************************** CodeCoupon *********************************************************************/
       
        CodeCouponService Scode = new CodeCouponService();
        CodeCoupon codeCoupon =new CodeCoupon("dsffsdfs",16);
        System.out.println(Scode.SearchById(10));
       
/******************************* Ajouter CodeCoupon *********************************************/
//            Scode.ajouter(codeCoupon);
        
///******************************* Modifier CodeCoupon *********************************************/
//            Scode.modifier(14, codeCoupon);
/******************************* Supprimer CodeCoupon *********************************************/
//              Scode.supprimer(3);
             
/******************************* Afficher CodeCoupon*********************************************/
//            System.out.println(Scode.afficher());

/******************************* SearchById CodeCoupon *********************************************/
//            System.out.println(Scode.SearchById(4));

/******************************* SearchById CodeCoupon *********************************************/
//            System.out.println(Scode.SearchByTitle("aa"));

/******************************* SearchByDate CodeCoupon *********************************************/
//            System.out.println(Scode.SearchByDate(LocalDateTime.of(2022, Month.APRIL, 10, 0, 0)));

/******************************* Number CodeCoupon *********************************************/
//            System.out.println(Scode.nbReclamation());
       

//Scode.modifierc(13, codeCoupon);
/******************************************************************************** Promotion *********************************************************************/
        
        ServicePromotion Spromotion = new ServicePromotion();
        Promotion promotion = new Promotion(LocalDateTime.now(), LocalDateTime.now(), "cc", 10,"10");

/******************************* Ajouter Promotion *********************************************/
//            Spromotion.ajouter(promotion);

/******************************* Modifier Promotion *********************************************/
//            Spromotion.modifier(79, promotion);

/******************************* Supprimer Promotion *********************************************/
//              Spromotion.supprimer(79);
             
/******************************* Afficher Promotion *********************************************/
//            System.out.println(Spromotion.afficher());

///******************************* SearchById Promotion *********************************************/
//            System.out.println(Spromotion.SearchById(77));


    }
    
}
