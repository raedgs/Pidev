/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author lenovo
 */
public class Commande {
    public int id,qte;
    public float montant;
    public String date;
    public String etat;

    public Commande() {
    }

    public Commande(int qte, float montant, String etat) {
        this.qte = qte;
        this.montant = montant;
        this.etat = etat;
    }

    
    
    
    public Commande(int id, int qte, float montant, String date, String etat) {
        this.id = id;
        this.qte = qte;
        this.montant = montant;
        this.date = date;
        this.etat = etat;
    }

    public Commande(int qte, float montant, String date, String etat) {
        this.qte = qte;
        this.montant = montant;
        this.date = date;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

   

  


    
    
    
    
    
}
