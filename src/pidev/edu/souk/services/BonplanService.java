/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.souk.entities.Bonplan;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author Khalil
 */
public class BonplanService implements pidev.edu.souk.Iservices.IBonplan {

    @Override
    public void addBonplan(Bonplan b) {

        try {
            String requete = "INSERT INTO bonplan (nombonplan,AdresseBonplan,typeBonplan,imgBonplan,Longitude,Latitude,AvgRating,id_user) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, b.getNombonplan());
            pst.setString(2, b.getAdresseBonplan());
            pst.setString(3, b.getTypeBonplan());
            pst.setString(4, b.getImgBonplan());
            pst.setDouble(5, b.getLongitude());
            pst.setDouble(6, b.getLatitude());

            pst.setDouble(7, 0);
            pst.setInt(8, b.getId_user().getId());
            pst.executeUpdate();
            System.out.println("Bon Plan ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void DeleteBonplan(int idBonplan) {

        try {
            String requete2 = "DELETE FROM bonplan WHERE idBonplan=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setInt(1, idBonplan);
            pst2.executeUpdate();//exucute query select kahaw
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    @Override
    public void updateBonplan(int idBonplan, Bonplan bonplan) {

        try {
            String requete2 = "UPDATE `bonplan` SET `nombonplan`=?,`AdresseBonplan`=?,`typeBonplan`=?,`imgBonplan`=?,`Longitude`=?,`Latitude`=?  WHERE `idBonplan`= ?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setString(1, bonplan.getNombonplan());
            pst2.setString(2, bonplan.getAdresseBonplan());
            pst2.setString(3, bonplan.getTypeBonplan());
            pst2.setString(4, bonplan.getImgBonplan());
            pst2.setDouble(5, bonplan.getLongitude());
            pst2.setDouble(6, bonplan.getLatitude());
            pst2.setInt(7, idBonplan);
            System.out.println(pst2);
            pst2.executeUpdate();//exucute query select kahaw

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    @Override
    public List<Bonplan> afficherBonplanAdmin() {
        ObservableList<Bonplan> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from bonplan";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                User u = new User();
                Bonplan bonplan = new Bonplan();
                bonplan.setIdBonplan(rs.getInt(1));
                bonplan.setNombonplan(rs.getString(2));
                bonplan.setAdresseBonplan(rs.getString(3));
                bonplan.setTypeBonplan(rs.getString(4));
                bonplan.setAvisBonplan(rs.getInt(5));
                bonplan.setImgBonplan(rs.getString(6));
                bonplan.setLatitude(rs.getDouble(7));
                bonplan.setLongitude(rs.getDouble(8));
                bonplan.setAvgRating(rs.getDouble(9));
                u.setId(rs.getInt(10));
                bonplan.setId_user(u);
                myList.add(bonplan);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;

    }

    @Override
    public List<Bonplan> afficherBonplanClient() {
        List<Bonplan> myList = new ArrayList<Bonplan>();

        try {
            RatingBonplanService rbs = new RatingBonplanService();
            String requete3 = "select * from bonplan";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Bonplan bonplan = new Bonplan();
                //rbs.UpdateMoyeneBonPlan(bonplan.getIdBonplan());
                bonplan.setIdBonplan(rs.getInt(1));
                bonplan.setNombonplan(rs.getString(2));
                bonplan.setAdresseBonplan(rs.getString(3));
                bonplan.setTypeBonplan(rs.getString(4));
                bonplan.setAvisBonplan(rs.getInt(5));
                bonplan.setImgBonplan(rs.getString(6));
                bonplan.setLatitude(rs.getDouble(7));
                bonplan.setLongitude(rs.getDouble(8));
                bonplan.setAvgRating(rs.getDouble(9));
                myList.add(bonplan);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }

    @Override
    public List<Bonplan> chercherBonplanParGouvernerat(String tag) {
        List<Bonplan> myList = new ArrayList<Bonplan>();

        try {
            String requete3 = "select * from bonplan where AdresseBonplan like '%" + tag + "%'";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Bonplan bonplan = new Bonplan();
                //rbs.UpdateMoyeneBonPlan(bonplan.getIdBonplan());
                bonplan.setIdBonplan(rs.getInt(1));
                bonplan.setNombonplan(rs.getString(2));
                bonplan.setAdresseBonplan(rs.getString(3));
                bonplan.setTypeBonplan(rs.getString(4));
                bonplan.setAvisBonplan(rs.getInt(5));
                bonplan.setImgBonplan(rs.getString(6));
                bonplan.setLatitude(rs.getDouble(7));
                bonplan.setLongitude(rs.getDouble(8));
                bonplan.setAvgRating(rs.getDouble(9));
                myList.add(bonplan);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }


 

    @Override
    public List<Bonplan> chercherBonplanParRating(Double rate) {

        List<Bonplan> myList = new ArrayList<Bonplan>();

        try {
            RatingBonplanService rbs = new RatingBonplanService();

            String Requete2 = "Select * from bonplan";
            PreparedStatement st2 = MyConnection.getInstance().getCnx().prepareStatement(Requete2);
            ResultSet rs = st2.executeQuery(Requete2);//contenaire n7ot fih eli yji mel base les enregistreme

            System.out.println("je suis ici");

            String requete3 = "SELECT b.*,round(p.AvgRating) from bonplan b join bonplan p on b.idBonplan=p.idBonplan";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs3 = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement

            System.out.println("ok");

            while (rs3.next()) {
                Bonplan bonplan = new Bonplan();
                //rbs.UpdateMoyeneBonPlan(bonplan.getIdBonplan());
                bonplan.setIdBonplan(rs3.getInt(1));
                bonplan.setNombonplan(rs3.getString(2));
                bonplan.setAdresseBonplan(rs3.getString(3));
                bonplan.setTypeBonplan(rs3.getString(4));
                bonplan.setAvisBonplan(rs3.getInt(5));
                bonplan.setImgBonplan(rs3.getString(6));
                bonplan.setLatitude(rs3.getDouble(7));
                bonplan.setLongitude(rs3.getDouble(8));
                bonplan.setAvgRating(rs3.getDouble(11));
                System.out.println("aaaaaaa" + String.valueOf(bonplan.getAvgRating())
                        + "aaaaaaaaa");

                myList.add(bonplan);

                //System.out.println(myList);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        List<Bonplan> finalList = new ArrayList<Bonplan>();

        for (Bonplan bonplan : myList) {

            if (bonplan.getAvgRating().equals(rate)) {

                finalList.add(bonplan);

            }

        }

        System.out.println("++++++++++++++++++");
        System.out.println(myList);
        System.out.println("++++++++++++++++++");
        System.out.println(finalList);
        System.out.println("++++++++++++++++++");

        return finalList;

    }

    @Override
    public List<Bonplan> OrderByNoteUp() {
               List<Bonplan> myList = new ArrayList<Bonplan>();

        try {
            RatingBonplanService rbs = new RatingBonplanService();
            String requete3 = "SELECT b.*,round(p.AvgRating) AS rr from bonplan b join bonplan p on b.idBonplan=p.idBonplan ORDER BY rr Asc ";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Bonplan bonplan = new Bonplan();
                //rbs.UpdateMoyeneBonPlan(bonplan.getIdBonplan());
                bonplan.setIdBonplan(rs.getInt(1));
                bonplan.setNombonplan(rs.getString(2));
                bonplan.setAdresseBonplan(rs.getString(3));
                bonplan.setTypeBonplan(rs.getString(4));
                bonplan.setAvisBonplan(rs.getInt(5));
                bonplan.setImgBonplan(rs.getString(6));
                bonplan.setLatitude(rs.getDouble(7));
                bonplan.setLongitude(rs.getDouble(8));
                bonplan.setAvgRating(rs.getDouble(9));
                myList.add(bonplan);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }

    @Override
    public List<Bonplan> OrderByNoteDown() {
                  List<Bonplan> myList = new ArrayList<Bonplan>();

        try {
            RatingBonplanService rbs = new RatingBonplanService();
            String requete3 = "SELECT b.*,round(p.AvgRating) AS rr from bonplan b join bonplan p on b.idBonplan=p.idBonplan ORDER BY rr Desc ";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Bonplan bonplan = new Bonplan();
                //rbs.UpdateMoyeneBonPlan(bonplan.getIdBonplan());
                bonplan.setIdBonplan(rs.getInt(1));
                bonplan.setNombonplan(rs.getString(2));
                bonplan.setAdresseBonplan(rs.getString(3));
                bonplan.setTypeBonplan(rs.getString(4));
                bonplan.setAvisBonplan(rs.getInt(5));
                bonplan.setImgBonplan(rs.getString(6));
                bonplan.setLatitude(rs.getDouble(7));
                bonplan.setLongitude(rs.getDouble(8));
                bonplan.setAvgRating(rs.getDouble(9));
                myList.add(bonplan);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
            System.out.println(myList);
        return myList;
    }

    @Override
    public List<Bonplan> chercherBonplanParType(String type) {
             List<Bonplan> myList = new ArrayList<Bonplan>();

        try {
            RatingBonplanService rbs = new RatingBonplanService();
            String requete3 = "select * from bonplan where typeBonplan = '" + type + "'";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Bonplan bonplan = new Bonplan();
                //rbs.UpdateMoyeneBonPlan(bonplan.getIdBonplan());
                bonplan.setIdBonplan(rs.getInt(1));
                bonplan.setNombonplan(rs.getString(2));
                bonplan.setAdresseBonplan(rs.getString(3));
                bonplan.setTypeBonplan(rs.getString(4));
                bonplan.setAvisBonplan(rs.getInt(5));
                bonplan.setImgBonplan(rs.getString(6));
                bonplan.setLatitude(rs.getDouble(7));
                bonplan.setLongitude(rs.getDouble(8));
                bonplan.setAvgRating(rs.getDouble(9));
                myList.add(bonplan);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
            
    }

}
