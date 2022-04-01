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
public class Type {
    
   private int id ;
   private String nature ,Description ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Type(int id, String nature, String Description) {
        this.id = id;
        this.nature = nature;
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Type{" + "id=" + id + ", nature=" + nature + ", Description=" + Description + '}';
    }

    public Type() {
    }
   
   
    
}
