/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Lenovo
 */
//taw n7oto fi description
public class Utilisateur {
    
    private int id;
    private String username;
    private String email;
    private String image;
    private String motdepasse;
    private String roles;


    public Utilisateur(String username, String email, String photo, String motdepasse) {
        this.username = username;
        this.email = email;
        this.image = photo;
        this.motdepasse = motdepasse;
    }
    
    

      public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }


    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(String username, String email) {
        this.username = username;
        this.email = email;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return image;
    }

    public void setPhoto(String photo) {
        this.image = photo;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", email=" + email + ", image=" + image + ", roles=" + roles + '}';
    }

    public Utilisateur(int id, String username, String email, String photo) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.image = photo;     
    }
    
    public Utilisateur(int id, String username, String email, String photo, String roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.image = photo;
        this.roles = roles;
    }

    public Utilisateur(String username, String email, String photo) {
        this.username = username;
        this.email = email;
        this.image = photo;
    }
    
    
    
    
    
}