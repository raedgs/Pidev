/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.l10n.ParseException;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Type;
import com.mycompany.services.ServiceType;
import java.io.IOException;
import java.util.Map;


/**
 *
 * @author eya dhaouadi
 */
public class TypeForm extends BaseForm{
    
               ServiceType ts = new ServiceType();

              Resources theme;
    
     public TypeForm(Resources res) {
                super("Types", BoxLayout.y());
            theme = UIManager.initFirstTheme("/theme");
            super.addSideMenu(res);
     this.getToolbar().setUIID("tb");
         Label logi = new Label("");
        logi.setUIID("login");
        this.add(logi);
            this.getToolbar().addCommandToRightBar(null, theme.getImage("log.png"), ev -> {
            //new LoginForm().showBack();
        });
    
     this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToOverflowMenu("Add Type", null, ev->{
        Form addEvent = new Form("Add Type",BoxLayout.y());
        Label AJOUT = new Label("ADD Type");
            AJOUT.setUIID("login");
            addEvent.add(AJOUT);
            TextField Nature = new TextField("", "Titre", 20, TextArea.TEXT_CURSOR);
           Nature.setUIID("txtn");
        TextField description = new TextField("", "Lieu", 20, TextArea.TEXT_CURSOR);
         description.setUIID("txtn");
        TextField DISCRIPTION = new TextField("", "Discription", 20, TextArea.TEXT_CURSOR);
         DISCRIPTION.setUIID("txtn");
    
            Button save = new Button("Ajouter");
        save.setUIID("vtnvalid");
        addEvent.add("Nature : ").add(Nature);
        addEvent.add("Description : ").add(description);
        addEvent.add(save);
        
    
        save.addActionListener(l
                                -> {

                            if (Nature.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Nature ", "OK", null);

                            }  else if (description.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Description ", "OK", null);
                            }

                            else {
                           
                                Type t = new Type();
                                t.setDescription(description.getText());
                                t.setNature(Nature.getText());
                                System.out.println("forms.addEvet.addItem()"+t);
                                if ( ts.addType(t) == true) {
                                    Dialog.show("Ajouter Type", "Ajouter Type aves success ", "OK", null);
                                    new TypeForm(res).show();

                                    
                                } else {
                                    Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                }

                            }

                        }
                
                        );
             addEvent.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
        
        addEvent.show();
 });
              for(Type c:ts.getAllTypes()){
           
 
                    try {    
                        this.add(addItem(c,res));
                    } catch (IOException ex) {
                    }
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Type e,Resources res) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label titre=new Label(e.getNature());
          Label libelle_titre = new Label("Nature");
          libelle_titre.setUIID("type1");
        Label lieu=new Label(e.getDescription());
        Label libelle_lieu = new Label("Description");
          libelle_lieu.setUIID("type1");



        cn2.add(libelle_titre).add(titre);
        
        cn2.add(libelle_lieu).add(lieu);
            
                EncodedImage enc = EncodedImage.create("/trait.png");
   
            Image im1 = URLImage.createToStorage(enc, "/trait.png" , lieu+"/trait.png");
        Button btn=new Button("Details");
        btn.setUIID("vtnvalid");    
        cn3.add(btn).add(im1);
        
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label titrem=new Label(e.getNature());
       

        Label lieum=new Label(e.getDescription());

        

     Button Modifier = new Button("Modifier");
     Button Supprimer = new Button("Supprimer");
       Modifier.setUIID("vtnvalid");
         Supprimer.setUIID("vtnvalid");
     Modifier.addActionListener(mod -> 
     
     {
         
         Form fmodifier = new Form("Modifier Evenement", BoxLayout.y());
         
           Label modif = new Label("EDIT Accessoire");
                modif.setUIID("login");
                fmodifier.add(modif);
         fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
            fmodifier.getToolbar().setUIID("tb");
         Button submit = new Button("Submit");
         submit.setUIID("vtnvalid");
         AutoCompleteTextField titre2 =  new AutoCompleteTextField(e.getNature());
          
         titre2.setMinimumElementsShownInPopup(1);
         titre2.setUIID("txtn");
         AutoCompleteTextField lieu2=  new AutoCompleteTextField(e.getDescription());
         lieu2.setMinimumElementsShownInPopup(1);
         lieu2.setUIID("txtn");

          Label lib_titre = new Label("Nature");
                lib_titre.setUIID("pass");
         fmodifier.add(lib_titre).add(titre2);
              Label lib_Lieu = new Label("Description");
                lib_Lieu.setUIID("pass");
         fmodifier.add(lib_Lieu).add(lieu2);

         fmodifier.add(submit);
         
          fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
         submit.addActionListener(sub ->
                 
         {
             Type es = new Type();
             es.setId(e.getId());
             es.setNature(titre2.getText());
             es.setDescription(lieu2.getText());
             System.out.println("forms.type.addItem()"+es);
             if ( ts.ModifierType(es) == true) {
                 Dialog.show("Modifier Type", "Type Modifier aves success ", "OK", null);
                 
                 
             } else {
                 Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
             }
             new TypeForm(res).show();
             
         }
                 
         );
         fmodifier.show();
     } 
     );
     
       Supprimer.addActionListener(sup ->  
       
       {
           
            if (ts.DeleteType(e.getId())) {
                                        Dialog.show("Supprimer Type", "Type Supprimer aves success ", "OK", null);
                                        
                new TypeForm(res).show();

                                    } else {
                                        Dialog.show("Erreur", " Erreur de suppression ", "OK", null);
                                    }
           
           
           
         
       
       
       }
       
       );
         
             f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
               Label lib_titre = new Label("Nature");
                lib_titre.setUIID("pass");
      
              Label lib_Lieu = new Label("Description");
                lib_Lieu.setUIID("pass");
   
              Label lib_Description = new Label("Description");
                lib_Description.setUIID("pass");
                
            f2.add(im1).add(lib_titre).add(titrem).add(lib_Lieu).add(lieum).add(lib_Description).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
   
     
     
}
    
    
    

