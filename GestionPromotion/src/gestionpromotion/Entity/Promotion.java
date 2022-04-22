/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionpromotion.Entity;

import java.time.LocalDateTime;

public class Promotion {
    private int id;
    private LocalDateTime dated;
    private LocalDateTime datef;
    private String description;
    private int pourcentage;
    private String codecoupone_id;

    public Promotion(LocalDateTime atStartOfDay) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Promotion(LocalDateTime atStartOfDay, LocalDateTime atStartOfDay0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDated() {
        return dated;
    }

    public void setDated(LocalDateTime dated) {
        this.dated = dated;
    }

    public LocalDateTime getDatef() {
        return datef;
    }

    public void setDatef(LocalDateTime datef) {
        this.datef = datef;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getCodecoupone_id() {
        return codecoupone_id;
    }

    public void setCodecoupone_id(String codecoupone_id) {
        this.codecoupone_id = codecoupone_id;
    }

    public Promotion(int id, LocalDateTime dated, LocalDateTime datef, String description, int pourcentage, String codecoupone_id) {
        this.id = id;
        this.dated = dated;
        this.datef = datef;
        this.description = description;
        this.pourcentage = pourcentage;
        this.codecoupone_id = codecoupone_id;
    }

    public Promotion(LocalDateTime dated, LocalDateTime datef, String description, int pourcentage, String codecoupone_id) {
        this.dated = dated;
        this.datef = datef;
        this.description = description;
        this.pourcentage = pourcentage;
        this.codecoupone_id = codecoupone_id;
    }

    public Promotion() {
    }
    
    

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", dated=" + dated + ", datef=" + datef + ", description=" + description + ", pourcentage=" + pourcentage + ", codecoupone_id=" + codecoupone_id + '}';
    }



    
}
