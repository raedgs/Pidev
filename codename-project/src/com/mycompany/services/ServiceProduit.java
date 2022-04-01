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
import com.mycomany.entities.Produit;
import com.mycomany.utils.Statics;
import static com.mycompany.services.ServiceCategorie.resultOK;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ServiceProduit {
    public static ServiceProduit instance=null;
    private ConnectionRequest req;
   public ArrayList<Produit> produit;
   
    public static ServiceProduit getInstance(){
    if(instance==null)
        instance = new ServiceProduit();
    return instance;
    } 
      public ServiceProduit(){
             req= new ConnectionRequest();
         }
   
    public void ajoutProduit(Produit pr) {
         ConnectionRequest con = new ConnectionRequest();
       String Url = Statics.BASE_URL+"/addProduitJSON/new?Libelle="+pr.getLibelle()+"&Description="+pr.getDescription()+"&prix="+pr.getPrix(); 
         
        con.setUrl(Url);

       con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
         NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public ArrayList<Produit> getList(String json){

             try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> prods = j.parseJSON(new CharArrayReader(json.toCharArray()));
          
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) prods.get("root");

            for (Map<String, Object> obj : list) {
                        Produit prod = new Produit();
                       //  ServiceCategorie c = new ServiceCategorie();
                        float idproduit= Float.parseFloat(obj.get("idproduit").toString());
                        String libelle=obj.get("Libelle").toString();
                        System.out.println(libelle);
                       String description=obj.get("Description").toString();
                        //String image=obj.get("image").toString();
                        double prix=Double.parseDouble(obj.get("prix").toString());
                    // String LibelleCategorie;
                     //LibelleCategorie=(String) ((Map<String,Object>)obj.get("categorie")).get("LibelleCategorie");
                    // Map<String, Object> categoryMap = (Map<String, Object>) obj.get("categorie");
                  // c.setLibelleCategorie(categoryMap.get("LibelleCategorie").toString());
                
                                     
                        prod.setIdproduit((int)idproduit);
                        prod.setLibelle(libelle);
                        prod.setDescription(description);
                        prod.setPrix((int)prix);
                       // prod.setImage(image);
                    //  prod.setLibelleCategorie(c);
                
                        
                        prodlist.add(prod);
            }

        } catch (IOException ex) {
        }
       
        return prodlist;
        
    }
        ArrayList<Produit> prodlist = new ArrayList<>();
    public ArrayList<Produit> ShowProds(){
        String url="http://127.0.0.1:8000/AllProduits";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               ServiceProduit ser = new ServiceProduit();
                prodlist = ser.getList(new String(req.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prodlist;
          
            }

       public boolean DeleteProd(int idproduit){
        String url = "http://127.0.0.1:8000/deleteProduitJSON/"+idproduit;
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
       
       public boolean ModifierProduit(Produit pr){
        String url = "http://127.0.0.1:8000/updateProduitJSON/"+pr.getIdproduit()+"&Libelle="+pr.getLibelle()+"&Description"+pr.getDescription();
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
}
