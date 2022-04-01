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

import com.codename1.io.FileSystemStorage;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import static com.codename1.io.rest.Rest.patch;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycomany.utils.Statics;
import com.mycompany.services.ServiceUtilisateur;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.NewsfeedForm;
import java.util.Vector;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;



/**
 * The user profile form
 *
 * @author Mr-Freez
 */
public class ProfileForm extends BaseForm {
    
    public static String i;
    /* private ConnectionRequest connectionRequest;
    private Image img;
    private String imgPath;*/
     String selectedImage;
    boolean imageEdited = false;
    Label imageLabel;
     ImageViewer imageIV;
    Button selectImageButton;
     Resources theme = UIManager.initFirstTheme("/theme");
    
    //image 
        protected String saveFileToDevice(String hi, String txt) throws IOException, URISyntaxException
        {
            URI uri;
            try
            {
                uri = new URI(hi);
                String path = uri.getPath();
                int index = hi.lastIndexOf("/");
                hi = hi.substring(index + 1);
                return hi;
            } catch (URISyntaxException ex)
            {
            }
            return "famech photo";
        }
    

    public ProfileForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Mon Profile");
        getContentPane().setScrollVisible(true);
        
        super.addSideMenu(res);
        
       /* Image img = res.getImage("back-logo.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL); */
       
        // Welcome current user
        
        System.out.println("user connecté id ="+ SessionManager.getId());
        
        
        
        System.out.println("user connecté username ="+ SessionManager.getUserName());
        
        System.out.println("user connecté password ="+ SessionManager.getPassowrd());
        
        System.out.println("user connecté email ="+ SessionManager.getEmail());
        System.out.println("user connecté role ="+ SessionManager.getRoles());
        System.out.println("user connecté image ="+ SessionManager.getPhoto());
        
        Button modifier = new Button("Modifier");
      
        Button btnAnnuler = new Button("return");
        btnAnnuler.addActionListener(back -> {
           new NewsfeedForm(res).show();
       });
        //Label pp = new Label(ServiceUtilisateur.UrlImage(SessionManager.getPhoto()),"PictureWhiteBackground");
        
        
      /*  add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
                    )
                )
        ));*/
        
        String uname = SessionManager.getUserName();
        System.out.println(uname);

        TextField username = new TextField(uname);
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField(SessionManager.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
         imageLabel = new Label("Image : ");
        imageLabel.setUIID("labelDefault");
        selectImageButton = new Button("Modifier image");
         addStringValue("",imageLabel); 
        addStringValue("",selectImageButton);
        
       
        modifier.setUIID("Edit");
       
        addStringValue("",modifier);
        btnAnnuler.setUIID("return");
        addStringValue("",btnAnnuler);    
        
        
       

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
           
        
        } else {
            imageIV = new ImageViewer(theme.getImage("profile-pic.jpg").fill(500, 500));
                
        }
        imageIV.setFocusable(false);
        
        selectImageButton.addActionListener(a -> {
            selectedImage = Capture.capturePhoto(500, -1);
            try {
                imageEdited = true;
                imageIV.setImage(Image.createImage(selectedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectImageButton.setText("Modifier l'image");
        });
       
       modifier.addActionListener((edit)->{
          InfiniteProgress ip = new InfiniteProgress();
          final Dialog ipDlg = ip.showInfiniteBlocking();
          ServiceUtilisateur.EditUser(username.getText(), email.getText(), password.getText(), selectedImage, imageEdited);
          SessionManager.setUserName(username.getText());
          SessionManager.setEmail(email.getText());
          SessionManager.setPassowrd(password.getText());
          SessionManager.setPhoto(selectedImage);
         // Dialog.show("success","Profile modifié avec success","OK", null);
          ipDlg.dispose();
          refreshTheme();
       });
        

      
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
