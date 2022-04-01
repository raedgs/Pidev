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
import com.mycomany.entities.Promotion;
import com.mycompany.services.servicePromotion;



/**
 *
 * @author PC
 */
public class updatePromotion extends BaseForm {
Form current;
    public updatePromotion(Resources res , Promotion p){
         super("Newsfeed",BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this ; 
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier Promotion");
        getContentPane().setScrollVisible(false);
        super.addSideMenu((Resources) res);
   TextField description = new TextField(p.getDescription(),"Description",20,TextField.ANY);
  TextField pourcentage = new TextField(String.valueOf(p.getPourcentage()),"Pourcentage",20,TextField.ANY);
   TextField dated = new TextField(String.valueOf(p.getDated()),"Date debut",20,TextField.ANY);
   TextField cp = new TextField(String.valueOf(p.getCodecoupone()) , "CodeCoupon" , 20 , TextField.ANY);
   ComboBox couponCombo = new ComboBox();
   couponCombo.addItem("44");
   couponCombo.addItem("50");
   if(p.getCodecoupone() == 1){
       couponCombo.setSelectedIndex(0);
   }
   else 
       couponCombo.setSelectedIndex(1);
   
   
   description.setUIID("newsTopLine");
   pourcentage.setUIID("newsTopLine");
   dated.setUIID("newsTopLine");
   description.setSingleLineTextArea(true);
   pourcentage.setSingleLineTextArea(true);
   dated.setSingleLineTextArea(true);
    cp.setUIID("newsTopLine");
            cp.setSingleLineTextArea(true);
        Button btnModifier =  new Button("Modifier");
        btnModifier.setUIID("Button");
        btnModifier.addPointerPressedListener(l -> {
            p.setPourcentage(pourcentage.getText());
            p.setDescription(description.getText());
            p.setDated(dated.getText());
             if(couponCombo.getSelectedIndex() == 0 ) {
               p.setCodecoupone(0);
           }
           else 
               p.setCodecoupone(1);
        }
             
        
        
        );
        if(servicePromotion.getInstance().modifierPromotion(p)){
            new ListPromotion((Resources) res).show();
        }
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListPromotion((Resources) res).show();
        }
        
        );
        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("");
        Label l1 = new Label();
        Container content = BoxLayout.encloseY(
        l1,l2,
        new FloatingHint(pourcentage),
        createLineSeparator(),
        new FloatingHint(description),
        createLineSeparator(),
        couponCombo,
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
