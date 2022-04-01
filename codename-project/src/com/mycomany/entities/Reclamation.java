/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Reclamation {
    //nemchio taw nchofo entity fi symfony
    
    private int id;
    private String objet,description;
    private String date;
    private int etat;
    
    private int iduser;

    public Reclamation() {
    }

    
    
    
    public Reclamation(int id, String objet, String description, String date, int etat) {
        this.id = id;
        this.objet = objet;
        this.description = description;
        this.date = date;
        this.etat = etat;
    }

    public Reclamation(String objet, String description, String date, int etat ,int iduser ) {
        this.objet = objet;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.iduser = iduser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    
    
    
    
    
    }
