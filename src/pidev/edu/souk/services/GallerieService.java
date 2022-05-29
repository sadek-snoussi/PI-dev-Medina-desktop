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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.souk.entities.Bonplan;
import pidev.edu.souk.entities.Gallerie;
import pidev.edu.souk.entities.Guide;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author Khalil
 */
public class GallerieService implements pidev.edu.souk.Iservices.IGallerie {

    @Override
    public void ajouterGallerie(Gallerie e) {
        try {
            String requete = "INSERT INTO gallerie(typeGallerie, description, ImgGallerie, LieuGallerie, TitreGallerie) VALUES (?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, e.getTypeGallerie());
            pst.setString(2, e.getDescription());
            pst.setString(3, e.getImgGallerie());
            pst.setString(4, e.getLieuGallerie());
            pst.setString(5, e.getTitreGallerie());

            pst.executeUpdate();
            System.out.println("Gallerie ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerGallerie(int idGallerie) {
        try {
            String requete2 = "DELETE FROM gallerie WHERE idGallerie=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setInt(1, idGallerie);
            pst2.executeUpdate();//exucute query select kahaw
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    @Override
    public void modifierGallerie(int idGallerie, Gallerie e) {
        try {
            String requete2 = "UPDATE `gallerie` SET `TitreGallerie`=?,`description`=?,`typeGallerie`=?,`LieuGallerie`=? , `ImgGallerie`=? WHERE `idGallerie`= ?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setString(1, e.getTitreGallerie());
            pst2.setString(2, e.getDescription());
            pst2.setString(3, e.getTypeGallerie());
            pst2.setString(4, e.getLieuGallerie());
            pst2.setString(5, e.getImgGallerie());

            pst2.setInt(6, idGallerie);
            System.out.println(pst2);
            pst2.executeUpdate();//exucute query select kahaw

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    @Override
    public List<Gallerie> afficherGallerieAdmin() {
        ObservableList<Gallerie> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from gallerie";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Gallerie gallerie = new Gallerie();
                gallerie.setIdGallerie(rs.getInt("idGallerie"));

                gallerie.setTypeGallerie(rs.getString("typeGallerie"));
                gallerie.setDescription(rs.getString("description"));
                gallerie.setImgGallerie(rs.getString("ImgGallerie"));
                gallerie.setLieuGallerie(rs.getString("LieuGallerie"));
                gallerie.setTitreGallerie(rs.getString("TitreGallerie"));
                myList.add(gallerie);
                System.out.println(gallerie);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;

    }

    @Override
    public List<Gallerie> afficherGallerieClient() {
        ObservableList<Gallerie> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from gallerie";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Gallerie gallerie = new Gallerie();
                gallerie.setIdGallerie(rs.getInt("idGallerie"));

                gallerie.setTypeGallerie(rs.getString("typeGallerie"));
                gallerie.setDescription(rs.getString("description"));
                gallerie.setImgGallerie(rs.getString("ImgGallerie"));
                gallerie.setLieuGallerie(rs.getString("LieuGallerie"));
                gallerie.setTitreGallerie(rs.getString("TitreGallerie"));
                myList.add(gallerie);
                System.out.println(gallerie);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }

    @Override
    public List<Gallerie> chercherGallerieParTag(String tag) {
        ObservableList<Gallerie> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "SELECT * FROM gallerie where description like '%" + tag + "%'";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Gallerie gallerie = new Gallerie();
                gallerie.setIdGallerie(rs.getInt("idGallerie"));
                gallerie.setTypeGallerie(rs.getString("typeGallerie"));
                gallerie.setDescription(rs.getString("description"));
                gallerie.setImgGallerie(rs.getString("ImgGallerie"));
                gallerie.setLieuGallerie(rs.getString("LieuGallerie"));
                gallerie.setTitreGallerie(rs.getString("TitreGallerie"));
                myList.add(gallerie);
                System.out.println(gallerie);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;

    }

    @Override
    public List<Gallerie> chercherGallerieParGouvernerat(String nomGouvernerat) {
        ObservableList<Gallerie> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "SELECT * FROM gallerie where LieuGallerie = '" + nomGouvernerat + "'";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Gallerie gallerie = new Gallerie();
                gallerie.setIdGallerie(rs.getInt("idGallerie"));

                gallerie.setTypeGallerie(rs.getString("typeGallerie"));
                gallerie.setDescription(rs.getString("description"));
                gallerie.setImgGallerie(rs.getString("ImgGallerie"));
                gallerie.setLieuGallerie(rs.getString("LieuGallerie"));
                gallerie.setTitreGallerie(rs.getString("TitreGallerie"));
                myList.add(gallerie);
                System.out.println(gallerie);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }

    @Override
    public int chercherGallerieParTagCount(String tag) {
        ObservableList<Gallerie> myList = FXCollections.observableArrayList();
        int nb = 0;
        try {
            String requete3 = "SELECT count(*) FROM gallerie where description like '%" + tag + "%'";

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

    @Override
    public int chercherGallerieParGouvCount(String nomGouvernerat) {
        int nb = 0;
        try {
            String requete3 = "SELECT count(*) FROM gallerie where LieuGallerie = '" + nomGouvernerat + "'";

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

    @Override
    public List<Gallerie> chercherGallerieParType(String TypeGallerie) {
        ObservableList<Gallerie> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "SELECT * FROM gallerie where typeGallerie = '" + TypeGallerie + "'";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Gallerie gallerie = new Gallerie();
                gallerie.setIdGallerie(rs.getInt("idGallerie"));

                gallerie.setTypeGallerie(rs.getString("typeGallerie"));
                gallerie.setDescription(rs.getString("description"));
                gallerie.setImgGallerie(rs.getString("ImgGallerie"));
                gallerie.setLieuGallerie(rs.getString("LieuGallerie"));
                gallerie.setTitreGallerie(rs.getString("TitreGallerie"));
                myList.add(gallerie);
                System.out.println(gallerie);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }

    @Override
    public int chercherGallerieParTypeCount(String tag) {
        int nb = 0;
        try {
            String requete3 = "SELECT count(*) FROM gallerie where typeGallerie = '" + tag + "'";

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

    @Override
    public List<Gallerie> chercherGallerieALL(String tag, String TypeGallerie, String nomGouvernerat) {
        ObservableList<Gallerie> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "SELECT * FROM gallerie where typeGallerie = '" + TypeGallerie + "' OR LieuGallerie ='"+ nomGouvernerat + "' OR description like '%" + tag + "%'";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Gallerie gallerie = new Gallerie();
                gallerie.setIdGallerie(rs.getInt("idGallerie"));

                gallerie.setTypeGallerie(rs.getString("typeGallerie"));
                gallerie.setDescription(rs.getString("description"));
                gallerie.setImgGallerie(rs.getString("ImgGallerie"));
                gallerie.setLieuGallerie(rs.getString("LieuGallerie"));
                gallerie.setTitreGallerie(rs.getString("TitreGallerie"));
                myList.add(gallerie);
                System.out.println(gallerie);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }

    @Override
    public List<Gallerie> trieup() {

        ObservableList<Gallerie> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from gallerie order by typeGallerie ASC";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Gallerie gallerie = new Gallerie();
                gallerie.setIdGallerie(rs.getInt("idGallerie"));

                gallerie.setTypeGallerie(rs.getString("typeGallerie"));
                gallerie.setDescription(rs.getString("description"));
                gallerie.setImgGallerie(rs.getString("ImgGallerie"));
                gallerie.setLieuGallerie(rs.getString("LieuGallerie"));
                gallerie.setTitreGallerie(rs.getString("TitreGallerie"));
                myList.add(gallerie);
                System.out.println(gallerie);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }

    @Override
    public List<Gallerie> triedown() {
        ObservableList<Gallerie> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from gallerie order by typeGallerie DESC";
            PreparedStatement st3 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Gallerie gallerie = new Gallerie();
                gallerie.setIdGallerie(rs.getInt("idGallerie"));

                gallerie.setTypeGallerie(rs.getString("typeGallerie"));
                gallerie.setDescription(rs.getString("description"));
                gallerie.setImgGallerie(rs.getString("ImgGallerie"));
                gallerie.setLieuGallerie(rs.getString("LieuGallerie"));
                gallerie.setTitreGallerie(rs.getString("TitreGallerie"));
                myList.add(gallerie);
                System.out.println(gallerie);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }



    @Override
    public int chercherGallerieALLCount(String TypeGallerie, String nomGouvernerat, String tag) {
                 int nb = 0;
        try {
            String requete3 = "SELECT count(*) FROM gallerie where typeGallerie = '" + TypeGallerie + "' OR LieuGallerie ='"+ nomGouvernerat + "' OR description like '%" + tag + "%'";

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
