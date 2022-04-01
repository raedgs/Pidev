/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Storage;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.utils.Statics;
import com.mycompany.gui.SessionManager;
import com.mycompany.services.ServiceUtilisateur;
import com.mycomany.entities.Utilisateur;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    
    private ConnectionRequest connectionRequest;
    private Image img;
    private String imgPath;
    ImageViewer imageIV;
      Resources theme = UIManager.initFirstTheme("/theme");

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
         String photo = SessionManager.getPhoto();
        if (photo != null) {
            String url = Statics.USER_IMAGE_URL + SessionManager.getPhoto();
            Image image = URLImage.createToStorage(
                    EncodedImage.createFromImage(theme.getImage("profile-pic.jpg").fill(100, 100), false),
                    url,
                    url,
                    URLImage.RESIZE_SCALE
            );
            imageIV = new ImageViewer(image);
            if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                
                FlowLayout.encloseCenterBottom(
                       imageIV )
        ));
        
        } else {
            imageIV = new ImageViewer(theme.getImage("profile-pic.jpg").fill(500, 500));
                    if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        }
        imageIV.setFocusable(false);
        
      
        
     String role = SessionManager.getRoles();
     if (role.equals("[ROLE_CLIENT, ]"))
     {
          tb.addMaterialCommandToSideMenu("Shop", FontImage.MATERIAL_UPDATE, e -> new ListProduitForm(res).show());
          tb.addMaterialCommandToSideMenu("Mes Commandes", FontImage.MATERIAL_ADD_SHOPPING_CART, e -> new ListCommandeForm(res).show());
          tb.addMaterialCommandToSideMenu("Gestion Promotion", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new ListPromotion(res).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
        tb.addMaterialCommandToSideMenu("Deconnexion", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            new SignInForm(res).show();
            SessionManager.pref.clearAll(); // nfaragh session
            Storage.getInstance().clearStorage();
            Storage.getInstance().clearCache();           
            System.out.println(SessionManager.getUserName());
        });
        refreshTheme();
     }
     else {
         
        tb.addMaterialCommandToSideMenu("Gestion Produit", FontImage.MATERIAL_UPDATE, e -> new ListProduitForm(res).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
         tb.addMaterialCommandToSideMenu("Gestion User", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new ListeUserForm(res).show());
         tb.addMaterialCommandToSideMenu("Gestion Commande", FontImage.MATERIAL_ADD_SHOPPING_CART, e -> new AjoutCommandeForm(res).show());
         tb.addMaterialCommandToSideMenu("Gestion Reclamation", FontImage.MATERIAL_ADD_TASK, e -> new ListeUserForm(res).show());
         tb.addMaterialCommandToSideMenu("Gestion Promotion", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new AjoutePromotion(res).show());
         tb.addMaterialCommandToSideMenu("Gestion Service", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new ServiceAvForm().show());
          tb.addMaterialCommandToSideMenu("Type", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new TypeForm(res).show());
        tb.addMaterialCommandToSideMenu("Deconnexion", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            new SignInForm(res).show();
            SessionManager.pref.clearAll(); // nfaragh session
            Storage.getInstance().clearStorage();
            Storage.getInstance().clearCache();           
            System.out.println(SessionManager.getUserName());
        });
        refreshTheme();  
          }
    }
}
