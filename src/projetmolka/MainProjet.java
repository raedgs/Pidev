/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetmolka;

import projet.crud.CrudReclamation;
import projet.crud.CrudTypeReclamation;
import projet.entites.Reclamation;
import projet.entites.Typereclamation;

/**
 *
 * @author Asus
 */
public class MainProjet {
        public static void main(String[] args) {
        
        
        
            CrudReclamation cr = new CrudReclamation();
           //cr.ajouter(new Reclamation("test Desct","test Etat",2));
        
           System.out.println(cr.ListJoiture());
        //cr.supprimer(new Reclamation(1));
        
            CrudTypeReclamation crr = new CrudTypeReclamation();
           // crr.ajouter(new Typereclamation("Test2"));
           
    //       System.out.println(crr.afficher());

           
           
        }
}
