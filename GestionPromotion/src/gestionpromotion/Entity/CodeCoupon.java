/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionpromotion.Entity;

import java.time.LocalDateTime;

public class CodeCoupon {
    private int id;
    private String code;
    private int pourcentage_p;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPourcentage_p() {
        return pourcentage_p;
    }

    public void setPourcentage_p(int pourcentage_p) {
        this.pourcentage_p = pourcentage_p;
    }

    public CodeCoupon() {
    }

    public CodeCoupon(int id, String code, int pourcentage_p) {
        this.id = id;
        this.code = code;
        this.pourcentage_p = pourcentage_p;
    }

    public CodeCoupon(String code, int pourcentage_p) {
        this.code = code;
        this.pourcentage_p = pourcentage_p;
    }

    @Override
    public String toString() {
        return "CodeCoupon{" + "id=" + id + ", code=" + code + ", pourcentage_p=" + pourcentage_p + '}';
    }
    
}
