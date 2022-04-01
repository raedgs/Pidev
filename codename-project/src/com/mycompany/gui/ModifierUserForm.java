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
import com.mycomany.entities.Utilisateur;
import com.mycompany.services.ServiceReclamation;
import com.mycompany.services.ServiceUtilisateur;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.BaseForm;

/**
 *
 * @author Lenovo
 */
public class ModifierUserForm extends BaseForm {
    
    Form current;
    public ModifierUserForm(Resources res , Utilisateur user) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier User");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
      
        TextField Username = new TextField(user.getUsername() , "Username" , 20 , TextField.ANY);
        TextField Email = new TextField(user.getEmail() , "Email" , 20 , TextField.EMAILADDR);
        TextField Password = new TextField(user.getMotdepasse() , "Password" , 20 , TextField.PASSWORD);
              // TextField etat = new TextField(String.valueOf(user.getEtat()) , "Etat" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
      /*  
        ComboBox etatCombo = new ComboBox();
        
        etatCombo.addItem("Non Traiter");
        
        etatCombo.addItem("Traiter");
        
        if(r.getEtat() == 0 ) {
            etatCombo.setSelectedIndex(0);
        }
        else 
            etatCombo.setSelectedIndex(1);
        */
        
        
        
        
        Username.setUIID("TextFieldBlack");
        Email.setUIID("TextFieldBlack");
        Password.setUIID("TextFieldBlack");
        
       // Username.setSingleLineTextArea(true);
         addStringValue("Username", Username);
         addStringValue("Email", Email);
         addStringValue("Password", Password);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           user.setUsername(Username.getText());
           user.setEmail(Email.getText());
           user.setMotdepasse(Password.getText());
           
         /*  if(etatCombo.getSelectedIndex() == 0 ) {
               user.setEtat(0);
           }
           else 
               user.setEtat(1); */
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceUtilisateur.getInstance().modifierUser(user)) { // if true
           new ListeUserForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListeUserForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
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
