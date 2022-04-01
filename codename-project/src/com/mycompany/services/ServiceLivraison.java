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
import com.mycomany.entities.Livraison;
import static com.mycompany.services.ServiceCommande.resultOk;
import com.mycomany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ServiceLivraison {
       public static ServiceLivraison instance=null;
    public static boolean resultOk = true;
    //initialisation connection request
    private ConnectionRequest req;
    
    public static ServiceLivraison getInstance(){
    if(instance == null)
        instance = new ServiceLivraison();
    return instance;
    }
    

    public ServiceLivraison() {
   req = new ConnectionRequest();
   }
   
   public void ajouterLivraison(Livraison livraison){
        String url=Statics.BASE_URL+"/addLivraisonJSON/new?lieux="+livraison.getLieux();
        req.setUrl(url);
        req.addResponseListener((e)->{
            
            String str = new String(req.getResponseData());
            System.out.println("data =="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   }
    //affichage
    
    public ArrayList<Livraison>affichageLivraisons() {
        ArrayList<Livraison> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/AlllivraisonJSON";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapLivraisons = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapLivraisons.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Livraison re = new Livraison();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String lieux = obj.get("lieux").toString();
                        
                        
                        
                        re.setId((int)id);
                        re.setLieux(lieux);
                      
                        
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
    public boolean deleteLivraison(int id ) {
        String url = Statics.BASE_URL +"/deleteLivraisonJSON/="+id;
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
    public boolean modifierLivraison(Livraison livraison) {
        String url = Statics.BASE_URL +"/updateLivraisonJSON/?id="+livraison.getId()+"&lieux="+livraison.getLieux();
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