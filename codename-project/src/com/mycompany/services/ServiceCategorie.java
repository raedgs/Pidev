/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Categorie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ServiceCategorie {
  
    public static ServiceCategorie instance=null;
    private ConnectionRequest req;
    public ArrayList<Categorie> produit;
    public static boolean resultOK;
    public static ServiceCategorie getInstance(){
    if(instance==null)
        instance = new ServiceCategorie();
    return instance;
    } 
      public ServiceCategorie(){
             req= new ConnectionRequest();
         }
      //ajout
  public void ajoutCategorie(Categorie ca) {
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/addCategorieJSON/new?LibelleCategorie=" + ca.getLibelleCategorie() ;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
         NetworkManager.getInstance().addToQueueAndWait(con);
    }

//affich
        public ArrayList<Categorie> getListTask(String json) {

        ArrayList<Categorie> listCategories = new ArrayList<>();
        

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> categories = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(categories);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) categories.get("root");

            for (Map<String, Object> obj : list) {
                Categorie e = new Categorie();
                 float id= Float.parseFloat(obj.get("id").toString());
                  String libelleCategorie=obj.get("LibelleCategorie").toString();
                        System.out.println(libelleCategorie);
            e.setId((int)id);
            e.setLibelleCategorie(libelleCategorie);

                //System.out.println(e);
                listCategories.add(e);

            }

        } catch (IOException ex) {
        }
       
        return listCategories;
        

    }
     //affich 
     ArrayList<Categorie> listCategorie = new ArrayList<>();
    
    public ArrayList<Categorie> ShowCats(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/AllCategoriesApi");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCategorie ser = new ServiceCategorie();
                listCategorie = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategorie;
    }
    
    //modifier
       public boolean ModifierCategorie(Categorie categorie){
        String url = "http://127.0.0.1:8000/updateCategorieJSON/"+categorie.getId()+"?LibelleCategorie="+categorie.getLibelleCategorie();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOK=req.getResponseCode() == 200;
              req.removeResponseListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
    }

  
       
    
    //supp
       public boolean deleteCategorie(int id){
        String url = "http://127.0.0.1:8000/deleteCategorieJSON/"+id;
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               req.removeResponseCodeListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
    }
}
