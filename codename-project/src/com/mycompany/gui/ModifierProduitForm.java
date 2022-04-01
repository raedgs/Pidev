/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Produit;
import com.mycompany.services.ServiceProduit;
/**
 *
 * @author lenovo
 */
public class ModifierProduitForm extends BaseForm{
    
        Form current;
    public ModifierProduitForm(Resources res , Produit r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier produit");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
     
        TextField libelle= new TextField(r.getLibelle() , "libelle" , 20 , TextField.ANY);
               
        TextField description= new TextField(r.getDescription() , "description" , 20 , TextField.ANY);
           
//        TextField prix= new TextField(r.getPrix() , "libelle" , 20 , TextField.ANY);
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        

        
        
        
        
        
        libelle.setUIID("TextFieldBlack");
        
         libelle.setSingleLineTextArea(true);
          
        description.setUIID("TextFieldBlack");
        
         description.setSingleLineTextArea(true);
            
     //   prix.setUIID("NewsTopLine");
        
       //  prix.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
          
           r.setLibelle(libelle.getText());
           r.setDescription(description.getText());
           //r.setPrix(prix.getText());
         
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceProduit.getInstance().ModifierProduit(r)) { // if true
           new ListProduitForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListProduitForm(res).show();
       });
       
       
   ;
       
        Label l1 = new Label();
         Label l2 = new Label();
       //   Label l3 = new Label();
        Container content = BoxLayout.encloseY(
                l1, l2,
               
                new FloatingHint(libelle),
                createLineSeparator(),
            new FloatingHint(description),
                createLineSeparator(),
                 // new FloatingHint(prix),
               // createLineSeparator(),
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
}
