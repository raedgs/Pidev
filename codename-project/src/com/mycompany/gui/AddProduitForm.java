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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Categorie;
import com.mycompany.gui.SessionManager;
import com.mycomany.entities.Produit;
import com.mycompany.services.ServiceProduit;

/**
 *
 * @author lenovo
 */
public class AddProduitForm extends BaseForm{
    
    Form current;
    public AddProduitForm(Resources res){
      super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Produit");
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
        RadioButton mesListes = RadioButton.createToggle("Mes Categories", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton mesListesP = RadioButton.createToggle("Mes Produits", barGroup);
        mesListesP.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Produit", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Categorie", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        ListCategorieForm a = new ListCategorieForm(res);
           a.show();
            refreshTheme();
        });
           mesListesP.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        ListProduitForm a = new ListProduitForm(res);
           a.show();
            refreshTheme();
        });
 liste.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
          AddProduitForm a = new AddProduitForm(res);
            a.show();
            refreshTheme();
        });
 partage.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
          AddCategorieForm a = new AddCategorieForm(res);
            a.show();
            refreshTheme();
        });

       String role = SessionManager.getRoles();
     if (role.equals("[ROLE_CLIENT, ]"))
     {
         add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, mesListesP),
                FlowLayout.encloseBottom(arrow)
        ));
     }
     else {
         add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, mesListes, mesListesP, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));
     }

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
        
      
       
        
        TextField libelle = new TextField(""," entrer Libelle");
                libelle.setUIID("TextFieldBlack");
                addStringValue("Libelle",libelle);
       TextField Description = new TextField(""," entrer description");
                Description.setUIID("TextFieldBlack");
                addStringValue("Description",Description);
      TextField prix = new TextField(""," entrer prix");
                prix.setUIID("TextFieldBlack");
                addStringValue("prix",prix);
      
        Button btnAjouter = new Button("Ajouter");
         addStringValue("", btnAjouter);
         
                btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try{
                if ((libelle.getText().equals("")) && (Description.getText().equals(""))&&(prix.getText().length()==0))
                { Dialog.show("verifier les données","", "Annuler","OK");
              
                 }
                else{
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Produit p = new Produit(
                    String.valueOf(libelle.getText()),
                   String.valueOf(Description.getText()),
                   Integer.parseInt(prix.getText())
                    
                    );
             
                            
                    System.err.println("data Produit=="+p);
                    ServiceProduit.getInstance().ajoutProduit(p);
                    idialog.dispose();
                   Dialog.show("Succes", "Produit ajouter avec succes ", new Command("OK"));
                 new ListProduitForm(res).show();
                refreshTheme();
            
                   
            }
        }catch(Exception ex){
                    ex.printStackTrace();
           }
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
        
/*       if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }*/
        
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

