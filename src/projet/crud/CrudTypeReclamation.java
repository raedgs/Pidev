/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import projet.base.ConnectionBd;
import projet.entites.Reclamation;
import projet.entites.Typereclamation;
import projet.service.Iservice;

/**
 *
 * @author Asus
 */
public class CrudTypeReclamation implements Iservice<Typereclamation> {

    Connection cnx = ConnectionBd.getInstance().getCnx();

    @Override
    public void ajouter(Typereclamation t) {
        try {
            String req = "INSERT INTO typereclamation(type)VALUES(?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, t.getType());
            pst.executeUpdate();
            System.out.println("TypeReclamation Ajoutée !!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Typereclamation t) {
        try {
            String req = "DELETE FROM typereclamation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("typereclamation suprimée !");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void modifier(Typereclamation t) {
        try {
            String req = "UPDATE typereclamation SET type=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(2, t.getId());
            pst.setString(1, t.getType());
            pst.executeUpdate();
            System.out.println("typereclamation " + t.getType() + " Modifiée !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Typereclamation> afficher() {
        List<Typereclamation> list = new ArrayList<>();
        try {
            String req = "SELECT * from typereclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Typereclamation(rs.getInt("id"), rs.getString("type")));
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return list;
    }

}
