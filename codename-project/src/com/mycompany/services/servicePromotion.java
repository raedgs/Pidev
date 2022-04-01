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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Promotion;
import com.codename1.ui.util.Resources;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author PC
 */
public class servicePromotion {
    public static servicePromotion instance = null;
    public static boolean resultok=true;
    private ConnectionRequest req;

    public static servicePromotion getInstance(){
        if(instance == null)
    instance = new servicePromotion();
    return instance;
}
    public servicePromotion(){
        req = new ConnectionRequest();
    }

    public void ajoutPromotion(Promotion promotion){
        String url =Statics.BASE_URL+"/addPromotionApi?pourcentage="+promotion.getPourcentage()+"&description="+promotion.getDescription()+"&dated="+promotion.getDated()+"&datef="+promotion.getDatef();
    req.setUrl(url);
 req.addResponseListener((e)->{
     String str = new String(req.getResponseData());
     System.out.println("data == "+str);
     
 });
 NetworkManager.getInstance().addToQueueAndWait(req);
 
    }
    public ArrayList<Promotion>affichagePromotion() {
        ArrayList<Promotion> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/promotionApi/liste";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapPromotion = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapPromotion.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Promotion pro = new Promotion();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String description = obj.get("description").toString();
                        float pourcentage = Float.parseFloat(obj.get("pourcentage").toString());
                  
                        String datee= obj.get("dated").toString();
                         String datef= obj.get("datef").toString();
                          //Date 
                        //String DateConverter =  obj.get("Date").toString().substring(obj.get("Date").toString().indexOf("timestamp") + 10 , obj.get("Date").toString().lastIndexOf("}"));
                        
                        //Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                
                        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        //String dateString = formatter.format(currentTime);
                       
                        
                        pro.setId((int)id);
                        pro.setDescription(description);
                        pro.setDated(datee);
                         pro.setDatef(datef);
                  
                         pro.setPourcentage((int)pourcentage);
                     
                    
                        //insert data into ArrayList result
                        result.add(pro);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
public Promotion DetailPromotion(int id , Promotion promotion){
    String url = Statics.BASE_URL+"/detailPromotionApi?"+id;
    req.setUrl(url);
    String str = new String (req.getResponseData());
    req.addResponseListener((evt)-> {
        JSONParser jsonp = new JSONParser();
        try {
            
           Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
        promotion.setDescription(obj.get("description").toString());
         promotion.setPourcentage(obj.get("Pourcentage").toString());
        
        }catch(IOException ex){
            System.out.println("erreur related to sql  :("+ex.getMessage());
        }
        System.out.println("data == "+str);
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return promotion;
}
public boolean deletePromotion(int id){
 String url = Statics.BASE_URL+"/deletePromotionApi?id="+id;
 req.setUrl(url);
 req.addResponseListener(new ActionListener<NetworkEvent>() {
     @Override
     public void actionPerformed(NetworkEvent evt) {
     req.removeResponseCodeListener(this);
     }
 });
 NetworkManager.getInstance().addToQueueAndWait(req);
 return resultok;
}
public boolean modifierPromotion(Promotion promotion){
    String url= Statics.BASE_URL+"/modifierPromotionApi?id="+promotion.getId()+"&pourcentage="+promotion.getPourcentage()+"&description="+promotion.getDescription()+"&dated="+promotion.getDated()+"&codecoupone="+promotion.getCodecoupone();
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
       resultok = req.getResponseCode()==200;
       req.removeResponseCodeListener(this);
       
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultok;
       
}

}
                             