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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Type;

import com.mycomany.utils.DataSource;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rzouga
 */
public class ServiceType {
    
    
    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Type> types;

    public ServiceType() {
            request = DataSource.getInstance().getRequest();

    }
       public boolean addType(Type type) {
     
        String url = Statics.BASE_URL + "/api/addType?nature=" + type.getNature()+ "&description=" + type.getDescription();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    
        public ArrayList<Type> getAllTypes() {
        String url = Statics.BASE_URL + "/api/allType";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    types = parseType(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return types;
    }
        
          public ArrayList<Type> parseType(String jsonText) throws ParseException {
        try {
            types = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("id").toString());
            String nature = obj.get("nature").toString();
            String Description = obj.get("Description").toString();
            

           
                  
                types.add(new Type(id,nature,Description));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return types;
    }

       public boolean ModifierType(Type type) {
     
        String url = Statics.BASE_URL + "/api/update?nature=" + type.getNature()+ "&description=" + type.getDescription()+ "&id=" + type.getId();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
          public boolean DeleteType(int id) {
     
        String url = Statics.BASE_URL + "/api/delete/"+id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
    
}
