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
import pidev.edu.souk.entities.Event;
import pidev.edu.souk.entities.Stand;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author ASUS I7
 */
public class StandService {

    public void addStand(Stand s) {
        try {
            //////// Avec statement////////////
            String requete = "INSERT INTO stand (superficieStand,emplacementStand,couleur,prix,etat)	 VALUES (?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setDouble(1, s.getSuperficieStand());
            pst.setString(2, s.getEmplacementStand());
            pst.setString(3, s.getCouleur());
            pst.setDouble(4, s.getPrix());
            pst.setBoolean(5, s.getEtat());
            pst.executeUpdate();

            System.out.println("ajout effectuer avec succes");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Stand> listerStand() {
        ArrayList<Stand> myList = new ArrayList<Stand>();
        try {

            String req3 = "SELECT * FROM stand";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(req3);
            while (rs.next()) {

                Stand stand = new Stand();
                stand.setNumStand(rs.getInt("numStand"));
                stand.setSuperficieStand(rs.getDouble("superficieStand"));
                stand.setEmplacementStand(rs.getString("emplacementStand"));

                stand.setCouleur(rs.getString("couleur"));

                stand.setEtat(rs.getBoolean("etat"));

                stand.setPrix(rs.getDouble("prix"));

                myList.add(stand);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public void UpdateStand(Stand s, int id) {
        try {
            String requete2 = "UPDATE stand SET   emplacementStand=?,prix=? WHERE numStand=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);

            pst2.setString(1, s.getEmplacementStand());

            pst2.setDouble(2, s.getPrix());
            pst2.setInt(3, id);

            pst2.executeUpdate();
            System.out.println("Modification effectuée avec succés");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Stand findStandById(int id) {
        Stand stand = new Stand();

        try {
            String requete = "select * from stand where numStand='" + id + "')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                stand.setNumStand(id);

                stand.setSuperficieStand(rs.getDouble("superficieStand"));
                stand.setEmplacementStand(rs.getString("emplacementStand"));
                stand.setCouleur(rs.getString("couleur"));
                stand.setEtat(rs.getBoolean("etat"));
                stand.setPrix(rs.getDouble("prix"));

            }

        } catch (SQLException ex) {

        }

        return stand;
    }

    public ArrayList<Stand> findStandBydispo() {
        ArrayList<Stand> myList = new ArrayList<Stand>();
        try {
            String requete = "select * from stand where etat=0";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                Stand stand = new Stand();
                stand.setNumStand(rs.getInt("numStand"));
                stand.setSuperficieStand(rs.getDouble("superficieStand"));
                stand.setEmplacementStand(rs.getString("emplacementStand"));
                stand.setCouleur(rs.getString("couleur"));

                stand.setPrix(rs.getDouble("prix"));
                System.out.println(stand);
                myList.add(stand);
            }

        } catch (SQLException ex) {

        }

        return myList;
    }

    public void deleteStand(Stand s) {
        try {
            // Event event =new Event();
            String req = "Delete from stand Where numStand=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst2.setInt(1, s.getNumStand());
            pst2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reservation(Stand s, int id) {
        try {
            String requete2 = "UPDATE stand SET user_id=?,eventstand=?,etat=1 WHERE numStand=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setInt(1, s.getUserId().getId());

            System.out.println("0000000");

            pst2.setInt(2, s.getEventstand().getIdEvent());
            System.out.println("    88888888s");
            // pst2.setBoolean(3, s.getEtat());
            pst2.setInt(3, id);
            pst2.executeUpdate();
        } catch (SQLException ex) {

        }

    }

    public void downNbstand(Event e, int id) {
        try {
            String req6 = "UPDATE event SET nbreStand=? WHERE idEvent=? ";
            PreparedStatement pst8 = MyConnection.getInstance().getCnx().prepareStatement(req6);
            pst8.setInt(1, e.getNbreStand());
            pst8.setInt(2, id);
            pst8.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public ArrayList<Stand> findStandBySuperficie(Double Superficie) {
        ArrayList<Stand> myList = new ArrayList<Stand>();
        try {
            String requete = "select * from stand where etat="+0+" and  superficieStand >='" + Superficie + "'";
            System.out.println("************" + Superficie);
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                Stand stand = new Stand();
                stand.setNumStand(rs.getInt("numStand"));
                stand.setSuperficieStand(rs.getDouble("superficieStand"));
                stand.setEmplacementStand(rs.getString("emplacementStand"));
                stand.setCouleur(rs.getString("couleur"));

                stand.setPrix(rs.getDouble("prix"));
                System.out.println(stand);
                myList.add(stand);
            }

        } catch (SQLException ex) {

        }

        return myList;
    }

    public ArrayList<Stand> findStandByPrice(Double prix) {
        ArrayList<Stand> myList = new ArrayList<Stand>();
        try {
            String requete9 = "select * from stand where etat=0 and  prix >='" + prix + "'";
            System.out.println("************" + prix);
            Statement st6 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs0 = st6.executeQuery(requete9);

            while (rs0.next()) {

                Stand stand = new Stand();
                stand.setNumStand(rs0.getInt("numStand"));
                stand.setSuperficieStand(rs0.getDouble("superficieStand"));
                stand.setEmplacementStand(rs0.getString("emplacementStand"));
                stand.setCouleur(rs0.getString("couleur"));

                stand.setPrix(rs0.getDouble("prix"));
                System.out.println(stand);
                myList.add(stand);
            }

        } catch (SQLException ex) {

        }

        return myList;
    }

}
