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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.edu.souk.Iservices.IRating;
import pidev.edu.souk.entities.Rating;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author hp
 */
public class RatingService implements IRating {

    @Override
    public void ajouterNote(Rating note) {

        try {
            String requete = "INSERT INTO rating (video_id,user_id,rating)"
                    + " VALUES (?,?,?)";
            PreparedStatement prst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            prst.setInt(1, note.getVideoId().getIdVideo());
            prst.setInt(2, note.getUserId().getId());
            prst.setDouble(3, note.getRating());

            prst.executeUpdate();
            System.out.println("Note Ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(RatingService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Rating suspendre(int userID, int videoID) {

        ArrayList<Rating> listPanier = new ArrayList<Rating>();
        String requete = "SELECT * FROM rating WHERE user_id='" + userID + "'" + " AND video_id='" + videoID + "'";

        try {
            Rating rating = new Rating();
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            System.out.println("avant rs");
            while (rs.next()) {
                rating.setRating(rs.getDouble(4));
                listPanier.add(rating);
                System.out.println("valeur de rating " + rating.getRating());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Rating ratingFinale = listPanier.stream().findFirst().get();
        return ratingFinale;
    }

    @Override
    public Double compterNote(Videodiy v) {
       
        
        Double i=0.0;

        ResultSet rs2;
        String sql = "SELECT AVG(rating) as moyenne FROM rating where video_id=" + v.getIdVideo() + ";";

        try {
            Statement stl = MyConnection.getInstance().getCnx().createStatement();
            rs2 = stl.executeQuery(sql);
            System.out.println("Affichage Done");
            while (rs2.next()) {
                i = rs2.getDouble("moyenne");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return i;

    }

    @Override
    public void modifierNote(int userID, int videoID, Double rating) {
        
         String requete2 = "UPDATE rating SET rating=? WHERE user_id='" + userID + "'" + " AND video_id='" + videoID + "'";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst.setDouble(1, rating);

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

}


