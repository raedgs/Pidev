/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.List;

/**
 *
 * @author lenovo
 */
public class Categorie {
  public int id;
    public String LibelleCategorie;
   public List<Produit> produitList;

    public Categorie( String LibelleCategorie) {
        
        this.LibelleCategorie = LibelleCategorie;
       this.produitList = produitList;
    }

    public Categorie() {
    }

   

    public String getLibelleCategorie() {
        return LibelleCategorie;
    }

    public void setLibelleCategorie(String LibelleCategorie) {
        this.LibelleCategorie = LibelleCategorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 public List<Produit> getProduitList() {
    return produitList;
  }

   public void setProduitList(List<Produit> produitList) {
    this.produitList = produitList;
   }

    @Override
    public String toString() {
        return "Categorie{" + ", LibelleCategorie=" + LibelleCategorie + '}';
    }

    
}
 
