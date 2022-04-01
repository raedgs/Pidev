/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author lenovo
 */
public class Livraison {
    public int id;
     public String date_livraison;
    public String lieux;
    
    
    
 public Livraison() {
    }

    public Livraison(int id, String date_livraison, String lieux) {
        this.id = id;
        this.date_livraison = date_livraison;
        this.lieux = lieux;
    }

    public Livraison(String lieux, String date_livraison) {
        this.date_livraison = date_livraison;
        this.lieux = lieux;
    }

    public int getId() {
        return id;
    }

    public String getDate_livraison() {
        return date_livraison;
    }

    public String getLieux() {
        return lieux;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_livraison(String date_livraison) {
        this.date_livraison = date_livraison;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }
  
}
