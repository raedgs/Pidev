/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceCommande;
import com.mycompany.services.ServiceLivraison;
import com.mycomany.entities.Commande;
import com.mycomany.entities.Livraison;

/**
 *
 * @author lenovo
 */
public class ModifierLivraisonForm extends BaseForm{
 Form current;
    public ModifierLivraisonForm(Resources liv, Livraison r) {
   super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout livraison");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(liv);
        
        TextField lieux = new TextField(String.valueOf(r.getLieux()) , "Lieux" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
      //  ComboBox etatCombo = new ComboBox();
        
      //  etatCombo.addItem("Non Traiter");
      
       // etatCombo.addItem("Traiter");
        
        //if(r.getEtat() == 0 ){
           // etatCombo.setSelectedIndex(0);
       // }
        //else 
         //   etatCombo.setSelectedIndex(1);
        
        
        
        
        
        lieux.setUIID("TextFieldBlack");
       
        
        lieux.setSingleLineTextArea(true);
       
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
         //  r.setMontant(montant.getText());
          // r.setQte(qte.getText());
           
          // if(etatCombo.getSelectedIndex() == 0 ) {
          //     r.setEtat(0);
         //  }
         //  else 
          //     r.setEtat(1);
      
       
       //appel fonction modfier reclamation men service
       
         if(ServiceLivraison.getInstance().modifierLivraison(r)) {  // if true
           new ListLivraisonForm(liv).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListLivraisonForm(liv).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(lieux),
                createLineSeparator(),
               
                
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
