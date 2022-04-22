package gestionpromotion.Service;


import gestionpromotion.Entity.Promotion;
import gestionpromotion.ISerivce.IService;
import gestionpromotion.Utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServicePromotion implements IService<Promotion>{
        Connection cnx;
    public ServicePromotion (){
        cnx = Myconnexion.getInstance().getCnx();}

/*****************************Add promotion***************************/
    @Override
    public void ajouter(Promotion promotion) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `promotion`(`dated`, `datef`, `description`, `pourcentage`, `codecoupone_id`) VALUES ('"+promotion.getDated()+"','"+promotion.getDatef()+"','"+promotion.getDescription()+"','"+promotion.getPourcentage()+"','"+promotion.getCodecoupone_id()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
        }

    @Override
    public List<Promotion> afficher() throws SQLException {
        List<Promotion> LR = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM promotion";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        Promotion promotion = new Promotion();   
        promotion.setId(rs.getInt("id"));
        promotion.setDated(rs.getTimestamp(2).toLocalDateTime());
        promotion.setDatef(rs.getTimestamp(3).toLocalDateTime());
        promotion.setDescription(rs.getString("description"));
        promotion.setPourcentage(rs.getInt("pourcentage"));
        promotion.setCodecoupone_id(rs.getString("codecoupone_id"));
        LR.add(promotion);
        }
        return LR;
    }

    
 /******************************* Delete promotion *********************************************/
    @Override
    public void supprimer(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from promotion where id="+id;
        stm.executeUpdate(query);
    }

    

    
/******************************* FindById promotion *********************************************/
    public Promotion SearchById(long id_Promotion) throws SQLException{
        Statement stm = cnx.createStatement();
        Promotion promotion = new Promotion();
        String query = "select * from promotion where id="+id_Promotion;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        promotion.setId(rs.getInt("id"));
        promotion.setDated(rs.getTimestamp(2).toLocalDateTime());
        promotion.setDatef(rs.getTimestamp(3).toLocalDateTime());
        promotion.setDescription(rs.getString("description"));
        promotion.setPourcentage(rs.getInt("pourcentage"));
        promotion.setCodecoupone_id(rs.getString("codecoupone_id"));}
        return promotion;
        
        }     
    
/******************************* Modifier promotion *********************************************/
    @Override
    public void modifier(int id_Modifier, Promotion promotion) throws SQLException {
        Statement stm = cnx.createStatement();
        Promotion p =SearchById(id_Modifier);
        String query = "UPDATE `promotion` SET `dated`='"+promotion.getDated()+"',`datef`='"+promotion.getDatef()+"',`description`='"+promotion.getDescription()+"',`pourcentage`='"+promotion.getPourcentage()+"',`codecoupone_id`='"+promotion.getCodecoupone_id()+"' where id="+p.getId();
        stm.executeUpdate(query);
    }
    
/******************************* Nombre Promotion *********************************************/     
    public int nbPromotion(){
        int nbReclamation = 0;
        try {
        ResultSet set = Myconnexion.getInstance().
        getCnx().prepareStatement("SELECT COUNT(id) FROM promotion")
        .executeQuery();
        if (set.next()) {
        nbReclamation = set.getInt(1);}}
        catch (SQLException ex) {
        Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);}
        return nbReclamation;
        }
    
    public HashMap<String, Double> StatistiquePoucentage() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT pourcentage, COUNT(*) as nb FROM promotion GROUP BY pourcentage;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("pourcentage");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
}
}
