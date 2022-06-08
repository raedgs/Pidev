/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entites;

/**
 *
 * @author Asus
 */
public class Reclamation {

    private int id;
    private String description;
    private String etat;
    private int idType;
    private String Type;
    public Reclamation() {
    }

    public Reclamation(int id, String description, String etat, int idType) {
        this.id = id;
        this.description = description;
        this.etat = etat;
        this.idType = idType;
    }

    public Reclamation(String description, String etat, int idType) {
        this.description = description;
        this.etat = etat;
        this.idType = idType;
    }

    public Reclamation(int id, String description, String etat, String Type) {
        this.id = id;
        this.description = description;
        this.etat = etat;
        this.Type = Type;
    }



    public Reclamation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", description=" + description + ", etat=" + etat + ", idType=" + idType + '}';
    }

   
}
