/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Commande;
import com.mycompany.services.ServiceCommande;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class AjoutCommandeForm extends BaseForm {
    
    
    Form current;
    public AjoutCommandeForm(Resources res ) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Commande");
        getContentPane().setScrollVisible(false);
        
         super.addSideMenu(res);
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("back-logo.jpeg"),"","",res);
        
        //
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Commandes", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton mesListesP = RadioButton.createToggle("Livraison", barGroup);
        mesListesP.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Ajouter Commandes", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter Livraison", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        ListCommandeForm a = new ListCommandeForm(res);
           a.show();
            refreshTheme();
        });
           mesListesP.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        ListLivraisonForm a = new ListLivraisonForm(res);
           a.show();
            refreshTheme();
        });
 liste.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
          AjoutCommandeForm a = new AjoutCommandeForm(res);
            a.show();
            refreshTheme();
        });
 partage.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
          AjoutLivraisonForm a = new AjoutLivraisonForm(res);
            a.show();
            refreshTheme();
        });
    
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, mesListes,mesListesP, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        //
        
      
          TextField montant = new TextField("", "entrer montant");
        montant.setUIID("TextFieldBlack");
        addStringValue("Montant :", montant);
        
          TextField qte = new TextField("", "entrer quantite");
        qte.setUIID("TextFieldBlack");
        addStringValue("Quantite : ", qte);
        
        
        TextField etat = new TextField("", "entrer etat");
        etat.setUIID("TextFieldBlack");
        addStringValue("Etat :", etat);
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
            
            
            try {
                
                if(etat.getText()=="" || montant.getText() =="" || qte.getText() =="" ){
                    Dialog.show("veuillez verifier les donnees","","Annuler","Ok");
                    
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)
                      Commande r = new Commande(
                                 
                              Integer.valueOf( montant.getText()),
                              Integer.valueOf(qte.getText()),
                              
                           
                            format.format(new Date()),
                              "encour");
                    
                    
                    System.out.println("data  Commande == "+r);
                    
                    
                    //appelle methode ajouterCommande mt3 service Commande bch nzido données ta3na fi base 
                    ServiceCommande.getInstance().ajoutCommande(r);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListCommandeForm
                    new ListCommandeForm(res).show();
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        
    }

    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
      //  if(image.getHeight() < size) {
           // image = image.scaledHeight(size);
       // }
        
        
        
      //  if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
        //    image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
       // }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
        
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
    
   
   
    
}
