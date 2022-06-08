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
public class Typereclamation {
    private int id;
    private String type;

    public Typereclamation(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Typereclamation() {
    }

    public Typereclamation(String type) {
        this.type = type;
    }

    public Typereclamation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  type ;
    }
    
}
