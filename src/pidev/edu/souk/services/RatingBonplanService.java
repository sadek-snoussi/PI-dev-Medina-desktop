/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.edu.souk.Iservices.IRatingBonplan;
import pidev.edu.souk.entities.RatingBonPlan;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author khali
 */
public class RatingBonplanService implements IRatingBonplan {

    @Override
    public void AjouterNote(RatingBonPlan RB) {
        try {
            String requete = "INSERT INTO rating_bonplan (id_bonplan,id_user,rating)" + " VALUES (?,?,?)";
            PreparedStatement prst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            prst.setInt(1, RB.getId_bonplan().getIdBonplan());
            prst.setInt(2, RB.getId_user().getId());
            prst.setDouble(3, RB.getRating());
            prst.executeUpdate();
            System.out.println("Note Ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(RatingBonplanService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void UpdateMoyeneBonPlan(int idBonPlan){
        System.out.println("Entree dans update");
        String requete = "UPDATE bonplan SET  AvgRating =? where idBonplan="+idBonPlan;
        
        PreparedStatement pst2;
        try {
            pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete);
            System.out.println("avant appele calcule");
            pst2.setDouble(1,calculerNote(idBonPlan) );
            System.out.println("apres appelle calcul");
            pst2.executeUpdate();
            System.out.println("Note Modifie");
        } catch (SQLException ex) {
            Logger.getLogger(RatingBonplanService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @Override
    public Double calculerNote(int idBp) {
                System.out.println("entre dans calcul");            
        Double i=0.0;

        ResultSet rs2;
        String sql = "SELECT AVG(rating) as moyenne FROM rating_bonplan where id_bonplan=" +idBp;

        try {
            Statement stl = MyConnection.getInstance().getCnx().createStatement();
            rs2 = stl.executeQuery(sql);
            System.out.println("Affichage Done");
            while (rs2.next()) {
                i = rs2.getDouble("moyenne");
                System.out.println("la moyenne est "+i);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return i;
    }
}

