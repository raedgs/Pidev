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
import com.mycomany.entities.Service;
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
public class ServiceServiceAv {
    
    
    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Service> services;

    public ServiceServiceAv() {
            request = DataSource.getInstance().getRequest();

    }
       public boolean addService(Service service) {
     
        String url = Statics.BASE_URL + "/api/addServie?nomproduit=" + service.getNomP()+ "&description=" + service.getDescription()+"&idt="+service.getIdT();
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

    
        public ArrayList<Service> getAllService() {
        String url = Statics.BASE_URL + "/api/allService";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    services = parseType(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return services;
    }
        
          public ArrayList<Service> parseType(String jsonText) throws ParseException {
        try {
            services = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("id").toString());
            String nomp = obj.get("Nomdeproduit").toString();
            String Description = obj.get("Description").toString();
            
            Map map = ((Map) obj.get("typeAV")) ;
            Double idt = (Double) map.get("id");
            

           
                  
                services.add(new Service(id,idt.intValue(), Description, nomp));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return services;
    }

       public boolean ModifierType(Service type) {
     
        String url = Statics.BASE_URL + "/api/updateservice?nomproduit=" + type.getNomP()+ "&description=" + type.getDescription()+"&idt=" + type.getIdT()+ "&id=" + type.getIds();
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
     
        String url = Statics.BASE_URL + "/api/deleteService/"+id;

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
