/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.crud;

/**
 *
 * @author Asus
 */
public class CrudKeyValue {
    
    private int id;
    private String description;
    private String Etat;
    private String type;

    public CrudKeyValue() {
    }

    public CrudKeyValue(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public CrudKeyValue(int id, String description, String Etat, String type) {
        this.id = id;
        this.description = description;
        this.Etat = Etat;
        this.type = type;
    }

    public CrudKeyValue(String description, String Etat, String type) {
        this.description = description;
        this.Etat = Etat;
        this.type = type;
    }

    public CrudKeyValue(int id) {
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
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CrudKeyValue{" + "id=" + id + ", description=" + description + ", Etat=" + Etat + ", type=" + type + '}';
    }
    
    
}
