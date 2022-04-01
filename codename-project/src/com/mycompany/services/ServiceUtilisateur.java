/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Reclamation;
import com.mycomany.entities.Utilisateur;
import com.mycomany.utils.Statics;
import com.mycompany.gui.AjoutReclamationForm;
import com.mycompany.gui.ListProduitForm;
import com.mycompany.gui.ListReclamationForm;
import com.mycompany.gui.ListeUserForm;
import com.mycompany.gui.SignInForm;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.SessionManager;
import static com.mycompany.services.ServiceUtilisateur.resultOk;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Lenovo
 */
public class ServiceUtilisateur {
    
    
  //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField username,TextField password,TextField email,TextField confirmPassword, Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/user/signup?username="+username.getText().toString()+"&email="+email.getText().toString()+
                "&password="+password.getText().toString();
        
        req.setUrl(url);
                   
       
        //Control saisi
        if(username.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);   
        }
       
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
          String json = new String(req.getResponseData()) + "";
          if(json.equals("email invalide")) {
                Dialog.show("Echec","email invalide","OK",null);
            }
          else if(json.equals("password is short")) {
                Dialog.show("Echec","Mot de pass 8 caracteres minimum","OK",null);
            }
          else if(json.equals("username is short")) {
                Dialog.show("Echec","Username 4 caracteres minimum","OK",null);
            }
          else {
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
            Dialog.show("Success","account is created","OK",null);
            new SignInForm(res).show();
          }
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    
    //SignIn
    
    public void signin(TextField username,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/signin?username="+username.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("user not found")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else if(json.equals("password not found")) {
                Dialog.show("Echec d'authentification","mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("username").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setRoles(user.get("roles").toString());
                
                //photo 
                String current_role = SessionManager.getRoles();
                
                if(user.get("image") != null)
                    SessionManager.setPhoto(user.get("image").toString());
                
                
                if(user.size() >0 )  // l9a user
                {
                    if( current_role.equals("[ROLE_CLIENT, ]"))
                    {
                          new ListProduitForm(rs).show();
                    }
                    else 
                    {
                         new ListeUserForm(rs).show();
                    }
                }
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                   
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    //affichage
    
    public ArrayList<Utilisateur>ListeUser() {
        ArrayList<Utilisateur> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/user/afficher";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapUtilisateur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapUtilisateur.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Utilisateur user = new Utilisateur();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String username = obj.get("username").toString();
                        
                        String email = obj.get("email").toString();
                         String roles = obj.get("roles").toString();
                       
                        
                        user.setId((int)id);
                        user.setUsername(username);
                        user.setEmail(email);
                        user.setRoles(roles);
                        //insert data into ArrayList result
                        result.add(user);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
     //Delete 
    public boolean deleteUser(int id ) {
        String url = Statics.BASE_URL +"/user/deletee?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
  
     //Update 
    public boolean modifierUser(Utilisateur user) {
        String url = Statics.BASE_URL +"/user/modifier?id="+user.getId()+"&username="+user.getUsername()+"&email="+user.getEmail()+"&password="+user.getMotdepasse();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
    
    //edit profile
    public static void EditUser(String username, String email, String password, String image, boolean imageEdited)
    {
        String url = Statics.BASE_URL+"/user/editProfile?username="+username+"&email="+email+"&password="+password+"&image="+image;
        MultipartRequest req = new MultipartRequest();
            req.setFilename("image", "person.jpg");
            req.setUrl(url);
            req.setPost(true);
            req.addArgument("id", String.valueOf(SessionManager.getId()));
            req.addArgument("username", username);
            req.addArgument("email", email);
            req.addArgument("password", password);
            //req.addArgument("image", image);
           

            if (imageEdited) {
            try {
                req.addData("image", SessionManager.getPhoto(), "image/jpeg"); // kanet jpeg
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            req.addArgumentNoEncoding("image", SessionManager.getPhoto());
        }
             image = SessionManager.getPhoto();
             
          //  System.out.println(email);
            req.addResponseListener((response)-> {
               byte[] data = (byte[]) response.getMetaData();
               String s = new String(data);
               System.out.println(s);
               
               
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
               
              if(json.equals("profile modified")) {
                   Dialog.show("success","Profile modifié avec success","OK", null);
            }
            else if(json.equals("email invalide")) {
                   Dialog.show("Erreur","email invalide","OK",null);
            }
              else if(json.equals("username is short")) {
                   Dialog.show("Erreur","username < 4 ?","OK",null);
            }
              else if(json.equals("password is short")) {
                   Dialog.show("Erreur","password < 8 ?","OK",null);
            }
               else  {
                   Dialog.show("success","Profile modifié avec success","OK", null);
            }
              /* if (s.equals("profile modified"))
               {
                   Dialog.show("success","Profile modifié avec success","OK", null);
               }
               else 
               {
                   Dialog.show("Erreur","Echec de modification","OK",null);
               }*/
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            
    }
    

  //heki 5dmtha taw nhabtha ala description
    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }

}
