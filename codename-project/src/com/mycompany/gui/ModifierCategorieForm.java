/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Categorie;
import com.mycompany.services.ServiceCategorie;

/**
 *
 * @author lenovo
 */
public class ModifierCategorieForm extends BaseForm{
    
        Form current;
    public ModifierCategorieForm(Resources res , Categorie r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
     
        TextField libelleCategorie = new TextField(r.getLibelleCategorie() , "libelleCategorie" , 20 , TextField.ANY);
            
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        

        
        
        
        
        
        libelleCategorie.setUIID("TextFieldBlack");
         addStringValue("libelleCategorie", libelleCategorie);
  
     //   libelleCategorie.setSingleLineTextArea(true);
       
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
          
           r.setLibelleCategorie(libelleCategorie.getText());
           
         
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceCategorie.getInstance().ModifierCategorie(r)) { // if true
           new ListCategorieForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListCategorieForm(res).show();
       });
       
       
   ;
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1,
          
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
