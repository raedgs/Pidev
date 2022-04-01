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
import com.mycomany.entities.Service;
import com.mycomany.entities.Type;
import com.mycompany.services.ServiceServiceAv;
import com.mycompany.services.ServiceType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author eya dhaouadi
 */
public class ServiceAvForm extends Form{
    
               ServiceServiceAv ts = new ServiceServiceAv();
               ServiceType st = new ServiceType() ;               

              Resources theme;
    
     public ServiceAvForm() {
                super("Services", BoxLayout.y());
            theme = UIManager.initFirstTheme("/theme");
     this.getToolbar().setUIID("tb");
         Label logi = new Label("");
        logi.setUIID("login");
        this.add(logi);
            this.getToolbar().addCommandToRightBar(null, theme.getImage("log.png"), ev -> {
            //new LoginForm().showBack();
        });
    
     this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToOverflowMenu("Add Type", null, ev->{
        Form addEvent = new Form("Add Service",BoxLayout.y());
        Label AJOUT = new Label("ADD Service");
            AJOUT.setUIID("login");
            addEvent.add(AJOUT);
            TextField Nature = new TextField("", "Nom Produit", 20, TextArea.TEXT_CURSOR);
           Nature.setUIID("txtn");
        TextField description = new TextField("", "Description", 20, TextArea.TEXT_CURSOR);
         description.setUIID("txtn");
        TextField DISCRIPTION = new TextField("", "Type", 20, TextArea.TEXT_CURSOR);
         DISCRIPTION.setUIID("txtn");
         
         
         
            ComboBox<Map<String, Object>> combo = new ComboBox<> ();
            for(Type t : st.getAllTypes()){
             combo.addItem(createListEntry(t.getNature(), t.getId()));
            }
           
  combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
  


    
            Button save = new Button("Ajouter");
        save.setUIID("vtnvalid");
        addEvent.add("Nom Prpduit : ").add(Nature);
        addEvent.add("Description : ").add(description).add(combo);
                ;

        addEvent.add(save);
        
    
        save.addActionListener(l
                                -> {
            System.out.println("hhhhh"+combo.getSelectedItem().get("line"));

                            if (Nature.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Nature ", "OK", null);

                            }  else if (description.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Description ", "OK", null);
                            }

                            else {
                           
                                Service t = new Service();
                                t.setDescription(description.getText());
                                t.setNomP(Nature.getText());
                                t.setIdT((int)combo.getSelectedItem().get("line"));
                                System.out.println("forms.addEvet.addItem()"+combo.getSelectedItem());
                                if ( ts.addService(t) == true) {
                                    Dialog.show("Ajouter Type", "Ajouter Service aves success ", "OK", null);
                                    new ServiceAvForm().show();

                                    
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
              for(Service c:ts.getAllService()){
           
 
                    try {
                        this.add(addItem(c));
                    } catch (IOException ex) {
                    }
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Service e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label titre=new Label(e.getNomP());
          Label libelle_titre = new Label("Nom Produit");
          libelle_titre.setUIID("type1");
        Label lieu=new Label(e.getDescription());
        Label libelle_lieu = new Label("Description");
          libelle_lieu.setUIID("type1");



        cn2.add(libelle_titre).add(titre);
        
        cn2.add(libelle_lieu).add(lieu);
            
                EncodedImage enc = EncodedImage.create("/sep.png");
   
            Image im1 = URLImage.createToStorage(enc, "/sep.png" , lieu+"/sep.png");
        Button btn=new Button("Details");
        btn.setUIID("vtnvalid");    
        cn3.add(btn);
        cn3.add(im1);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label titrem=new Label(e.getNomP());
       

        Label lieum=new Label(e.getDescription());

        

     Button Modifier = new Button("Modifier");
     Button Supprimer = new Button("Supprimer");
       Modifier.setUIID("vtnvalid");
         Supprimer.setUIID("vtnvalid");
     Modifier.addActionListener(mod -> 
     
     {
         
         Form fmodifier = new Form("Modifier Service", BoxLayout.y());
         
   
         fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
            fmodifier.getToolbar().setUIID("tb");
         Button submit = new Button("Submit");
         submit.setUIID("vtnvalid");
         AutoCompleteTextField titre2 =  new AutoCompleteTextField(e.getNomP());
          
         titre2.setMinimumElementsShownInPopup(1);
         titre2.setUIID("txtn");
         AutoCompleteTextField lieu2=  new AutoCompleteTextField(e.getDescription());
         lieu2.setMinimumElementsShownInPopup(1);
         lieu2.setUIID("txtn");
                     ComboBox<Map<String, Object>> combo2 = new ComboBox<> ();
            for(Type t : st.getAllTypes()){
             combo2.addItem(createListEntry(t.getNature(), t.getId()));
            }
           
  combo2.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
  
          Label lib_titre = new Label("Nature");
                lib_titre.setUIID("pass");
         fmodifier.add(lib_titre).add(titre2);
              Label lib_Lieu = new Label("Description");
                lib_Lieu.setUIID("pass");
         fmodifier.add(lib_Lieu).add(lieu2);
         fmodifier.add(combo2);
         



         fmodifier.add(submit);
         
          fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
         submit.addActionListener(sub ->
                 
         {
             Service es = new Service();
             es.setIds(e.getIds());
             es.setNomP(titre2.getText());
             es.setDescription(lieu2.getText());
             es.setIdT((int)combo2.getSelectedItem().get("line"));
             System.out.println("forms.type.addItem()"+es);
             if ( ts.ModifierType(es) == true) {
                 Dialog.show("Modifier Type", "Type Modifier aves success ", "OK", null);
                 
                 
             } else {
                 Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
             }
             new ServiceAvForm().show();
             
         }
                 
         );
         fmodifier.show();
     } 
     );
     
       Supprimer.addActionListener(sup ->  
       
       {
           
            if (ts.DeleteType(e.getIds())) {
                                        Dialog.show("Supprimer Type", "Type Supprimer aves success ", "OK", null);
                                        
                new ServiceAvForm().show();

                                    } else {
                                        Dialog.show("Erreur", " Erreur de suppression ", "OK", null);
                                    }
           
           
           
         
       
       
       }
       
       );
         
             f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
               Label lib_titre = new Label("Titre");
                lib_titre.setUIID("pass");
      
              Label lib_Lieu = new Label("Lieu");
                lib_Lieu.setUIID("pass");
   
              Label lib_Description = new Label("Description");
                lib_Description.setUIID("pass");
 
   
  
                
            f2.add(im1).add(lib_titre).add(titrem).add(lib_Lieu).add(lieum).add(lib_Description).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
   private Map<String, Object> createListEntry(String name, int id) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("line", id);
    return entry;
}
     
     
}
    
    
    

