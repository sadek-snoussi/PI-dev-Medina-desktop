/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import com.sun.deploy.association.AssociationService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.souk.entities.Guide;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author Khalil
 */
public class GuideService implements pidev.edu.souk.Iservices.IGuide {

    @Override
    public void addGuide(Guide g) {
        try {
            String requete = "INSERT INTO guide (nomguide,prenomguide,telGuide,villeGuide,mailguide,imgGuide) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, g.getNomGuide());
            pst.setString(2, g.getPrenomGuide());
            pst.setString(3, g.getTelGuide());
            pst.setString(4, g.getVilleGuide());
            pst.setString(5, g.getMailguide());
            pst.setString(6, g.getImgGuide());
            pst.executeUpdate();
            System.out.println("Personne ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void DeleteGuide(int idGuide) {
        try {
            String requete2 = "DELETE FROM guide WHERE idGuide=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setInt(1, idGuide);
            pst2.executeUpdate();//exucute query select kahaw
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    @Override
    public void update(int idGuide, Guide g) {
        try {
            String requete2 = "UPDATE `guide` SET `nomGuide`=?,`prenomGuide`=?,`telGuide`=?,`villeGuide`=?,`mailguide`=?,`imgGuide`=? WHERE `idGuide`= ?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setString(1, g.getNomGuide());
            pst2.setString(2, g.getPrenomGuide());
            pst2.setString(3, g.getTelGuide());
            pst2.setString(4, g.getVilleGuide());
            pst2.setString(5, g.getMailguide());
            pst2.setString(6, g.getImgGuide());
            pst2.setInt(7, idGuide);
            System.out.println(pst2);
            pst2.executeUpdate();//exucute query select kahaw
            System.out.println("ili fil update" + g.toString());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    @Override
    public ObservableList<Guide> afficherGuideAdmin() {
        ObservableList<Guide> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from Guide";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Guide g = new Guide();
                g.setIdGuide(rs.getInt(1));
                g.setNomGuide(rs.getString(2));
                g.setPrenomGuide(rs.getString(3));
                g.setTelGuide(rs.getString(4));
                g.setVilleGuide(rs.getString(5));
                g.setMailguide(rs.getString(6));
                g.setImgGuide(rs.getString(7));
                myList.add(g);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;

    }

    @Override
    public List<Guide> afficherGuideClient() {
        ObservableList<Guide> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from Guide";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Guide g = new Guide();
                g.setIdGuide(rs.getInt(1));
                g.setNomGuide(rs.getString(2));
                g.setPrenomGuide(rs.getString(3));
                g.setTelGuide(rs.getString(4));
                g.setVilleGuide(rs.getString(5));
                g.setMailguide(rs.getString(6));
                g.setImgGuide(rs.getString(7));
                myList.add(g);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }

    @Override
    public List<Guide> chercherGuideParGouvernerat(String nomGouvernerat) {
        ObservableList<Guide> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "SELECT * FROM guide where villeGuide='" + nomGouvernerat + "'";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Guide g = new Guide();
                g.setIdGuide(rs.getInt(1));
                g.setNomGuide(rs.getString(2));
                g.setPrenomGuide(rs.getString(3));
                g.setTelGuide(rs.getString(4));
                g.setVilleGuide(rs.getString(5));
                g.setMailguide(rs.getString(6));
                g.setImgGuide(rs.getString(7));
                myList.add(g);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;

    }

    @Override
    public Boolean ChercheGuideparEmail(String MailGuide) {
        Guide guide = null;
        String req = "select * from guide where mailguide =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, MailGuide);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                guide = new Guide(
                        resultSet.getInt("idGuide"),
                        resultSet.getString("nomGuide"),
                        resultSet.getString("prenomGuide"),
                        resultSet.getString("telGuide"),
                        resultSet.getString("villeGuide"),
                        resultSet.getString("mailguide"),
                        resultSet.getString("imgGuide")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (guide == null) {
            return false;
        }
        return true;

    }

    @Override
    public int chercherGuideParGouverneratcount(String nomGouvernerat) {
        ObservableList<Guide> myList = FXCollections.observableArrayList();
        int nb = 0;
        try {
            String requete3 = "SELECT count(*) FROM guide where villeGuide='" + nomGouvernerat + "'";

            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement

            while (rs.next()) {
                nb = rs.getInt("count(*)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssociationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nb;
    }

}
