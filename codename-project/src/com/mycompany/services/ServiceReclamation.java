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
import com.mycomany.entities.Reclamation;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceReclamation {
    
    //singleton 
    public static ServiceReclamation instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReclamation getInstance() {
        if(instance == null )
            instance = new ServiceReclamation();
        return instance ;
    }
    
    
    
    public ServiceReclamation() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutReclamation(Reclamation reclamation) {
        
        String url =Statics.BASE_URL+"/addReclamation?objet="+reclamation.getObjet()+"&description="+reclamation.getDescription()+"&user="+reclamation.getIduser(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Reclamation>affichageReclamations() {
        ArrayList<Reclamation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displayReclamations";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Reclamation re = new Reclamation();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String objet = obj.get("objet").toString();
                        
                        String description = obj.get("description").toString();
                        float etat = Float.parseFloat(obj.get("etat").toString());
                        
                        re.setId((int)id);
                        re.setObjet(objet);
                        re.setDescription(description);
                        re.setEtat((int)etat);
                        
                        //Date 
                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        re.setDate(dateString);
                        
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
    
    
    
    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Reclamation DetailRecalamation( int id , Reclamation reclamation) {
        
        String url = Statics.BASE_URL+"/detailReclamation?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                reclamation.setObjet(obj.get("obj").toString());
                reclamation.setDescription(obj.get("description").toString());
                reclamation.setEtat(Integer.parseInt(obj.get("etat").toString()));
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return reclamation;
        
        
    }
    
    
    //Delete 
    public boolean deleteReclamation(int id ) {
        String url = Statics.BASE_URL +"/deleteReclamation?id="+id;
        
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
    public boolean modifierReclamation(Reclamation reclamation) {
        String url = Statics.BASE_URL +"/updateReclamation?id="+reclamation.getId()+"&objet="+reclamation.getObjet()+"&description="+reclamation.getDescription()+"&etat="+reclamation.getEtat();
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
    

    
}