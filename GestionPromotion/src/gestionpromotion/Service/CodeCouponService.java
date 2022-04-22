package gestionpromotion.Service;


import gestionpromotion.Entity.CodeCoupon;
import gestionpromotion.ISerivce.IService;
import gestionpromotion.Utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CodeCouponService implements IService<CodeCoupon>{
        Connection cnx;
    public CodeCouponService (){
        cnx = Myconnexion.getInstance().getCnx();}

/*****************************Add CodeCoupon***************************/
    @Override
    public void ajouter(CodeCoupon codeCoupon) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `codecoupone`(`code`, `pourcentage_p`) VALUES ('"+codeCoupon.getCode()+"','"+codeCoupon.getPourcentage_p()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
        }

    @Override
    public List<CodeCoupon> afficher() throws SQLException {
        List<CodeCoupon> LR = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM codecoupone";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        CodeCoupon codeCoupon = new CodeCoupon();   
        codeCoupon.setId(rs.getInt("id"));
        codeCoupon.setCode(rs.getString("code"));
        codeCoupon.setPourcentage_p(rs.getInt("pourcentage_p"));
        LR.add(codeCoupon);
        }
        return LR;
    }

    
 /******************************* Delete CodeCoupon *********************************************/
    @Override
    public void supprimer(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from codecoupone where id="+id;
        stm.executeUpdate(query);
    }

    

    
/******************************* FindById CodeCoupon *********************************************/
    public CodeCoupon SearchById(long id) throws SQLException{
        Statement stm = cnx.createStatement();
        CodeCoupon codeCoupon = new CodeCoupon();
        String query = "select * from codecoupone where id="+id;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            codeCoupon.setId(rs.getInt("id"));
        codeCoupon.setCode(rs.getString("code"));
        codeCoupon.setPourcentage_p(rs.getInt("pourcentage_p"));}
        return codeCoupon;
        
        }     
    
/******************************* Modifier CodeCoupon *********************************************/
    @Override
    public void modifier(int id_Modifier, CodeCoupon codeCoupon) throws SQLException {
        Statement stm = cnx.createStatement();
        CodeCoupon r =SearchById(id_Modifier);
        String query = "UPDATE `codecoupone` SET `code`='"+codeCoupon.getCode()+"',`pourcentage_p`='"+codeCoupon.getPourcentage_p()+"'where id="+r.getId();
        stm.executeUpdate(query);
    }
/******************************* Nombre Coupon *********************************************/     
    public int nbCoupon(){
        int nbReclamation = 0;
        try {
        ResultSet set = Myconnexion.getInstance().
        getCnx().prepareStatement("SELECT COUNT(id) FROM codecoupone")
        .executeQuery();
        if (set.next()) {
        nbReclamation = set.getInt(1);}}
        catch (SQLException ex) {
        Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);}
        return nbReclamation;
        }    
}


