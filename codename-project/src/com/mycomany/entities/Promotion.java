/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Promotion {
    
    private int id;
    private String description;
    private String dated;
    private String datef;
    private int pourcentage;
    private int codecoupone;
    public Promotion(){
        
    }
  
    public Promotion(int id,int pourcentage, String description , String dated, String datef){
        this.id=id;
        this.dated=dated;
        this.datef=datef;
        this.description=description;
        this.pourcentage=pourcentage;
    }
     public Promotion(String description , String dated, String datef,int pourcentage){
     
        this.dated=dated;
        this.datef=datef;
        this.description=description;
        this.pourcentage=pourcentage;
    }

    public String getDated() {
        return dated;
    }

    public String getDatef() {
        return datef;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public void setDatef(String datef) {
        this.datef = datef;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public int getCodecoupone() {
        return codecoupone;
    }

    public void setCodecoupone(int codecoupone) {
        this.codecoupone = codecoupone;
    }

    public void setPourcentage(String pourcentage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
