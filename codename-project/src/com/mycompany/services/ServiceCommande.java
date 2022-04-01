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

//import java.nio.channels.NetworkChannel;

import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Commande;
import com.mycomany.utils.Statics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author lenovo
 */
public class ServiceCommande {
    public static ServiceCommande instance=null;
    public static boolean resultOk = true;
    //initialisation connection request
    private ConnectionRequest req;
    
    public static ServiceCommande getInstance(){
    if(instance == null)
        instance = new ServiceCommande();
    return instance;
    }
    public ServiceCommande(){
        req = new ConnectionRequest();
    }
    
    
   public void ajoutCommande (Commande commande){
       
        String url=Statics.BASE_URL+"/addCommandeJSON/new?montant="+commande.getMontant()+"&qte="+commande.getQte()+"&etat="+commande.getEtat();
        req.setUrl(url);
        req.addResponseListener((e)->{
            
            String str = new String(req.getResponseData());
            System.out.println("data =="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
      
      
    //affichage
    
    public ArrayList<Commande>affichageCommandes() {
        ArrayList<Commande> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/AllcommandeJSON";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCommandes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCommandes.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Commande re = new Commande();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
           
                        double montant= Double.parseDouble(obj.get("montant").toString());
                        double qte=Double.parseDouble(obj.get("qte").toString());
                     //String etat = obj.get("etat").toString();
                     //   System.out.println("etat");
                        
                        re.setId((int)id);
                       re.setMontant((int)montant);
                        re.setQte((int)qte);
                     // re.setEtat(etat);
                        
                        //Date 
                      //  String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                    //    Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                     //  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                     //  String dateString = formatter.format(currentTime);
                     //   re.setDate(dateString);
                      //  
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
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
    public boolean deleteCommande(int id ) {
        String url = Statics.BASE_URL +"/deleteCommandeJSON/{id}?id="+id;
       // String url = Statics.BASE_URL +"/deleteReclamation?id="+id;
        
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
    public boolean modifierCommande(Commande commande) {
        String url = Statics.BASE_URL +"/updateCommandeJSON/{id}?id="+commande.getId()+"&montant="+commande.getMontant()+"&qte="+commande.getQte()+"&etat="+commande.getEtat();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent  evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    

}