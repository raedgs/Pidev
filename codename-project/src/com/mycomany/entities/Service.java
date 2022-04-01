/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Rzouga
 */
public class Service {
    
  private int ids ,idT;
  private String Description,NomP ;

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getNomP() {
        return NomP;
    }

    public void setNomP(String NomP) {
        this.NomP = NomP;
    }

    public Service(int ids, int idT, String Description, String NomP) {
        this.ids = ids;
        this.idT = idT;
        this.Description = Description;
        this.NomP = NomP;
    }

    public Service() {
    }
  
  
    
}
