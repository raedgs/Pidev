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
public class Produit {
     public int idproduit ; 
    public String libelle; 
    public String description; 
    public String image;   
    public Categorie categorie;

    public Produit(String libelle, String description, Categorie categorie, int prix) {
        this.libelle = libelle;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
    }
    public int prix;    
 
    public Produit() {
        
    }
      public Produit( String libelle, String description, String image, Categorie categorie, int prix) {
        
        this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
        this.prix = prix;
    }

  

  

    @Override
    public String toString() {
        return "Produit{" + "idproduit=" + idproduit + ", libelle=" + libelle + ", description=" + description + ", image=" + image + ", categorie=" + categorie + ", prix=" + prix + '}';
    }

    public Produit(String libelle, String description, int prix) {
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
    }

 

    

    public int getIdproduit() {
        return idproduit;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

  

    public int getPrix() {
        return prix;
    }

  

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Produit(int idproduit, String libelle, String description, String image, int prix) {
        this.idproduit = idproduit;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
       
        this.prix = prix;
    }




    
}
