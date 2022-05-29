/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import com.sun.prism.impl.Disposer.Record;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import pidev.edu.souk.utils.MyConnection;
import pidev.edu.souk.Iservices.IVideoDIY;
import pidev.edu.souk.entities.Videodiy;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
//import pidev.edu.souk.controller.AfficheVideoPartenaireFXMLController;

/**
 *
 * @author hp
 */
public class VideoDIYService implements IVideoDIY {

    //  public AfficheVideoPartenaireFXMLController videoController = new AfficheVideoPartenaireFXMLController();
    @Override
    public void ajouterVideoDIY(Videodiy v) {
        try {

            String requete = "INSERT INTO videodiy (titre,descriptionVideo,tags,video,dateExpoVideo,valid,id_user)"
                    + " VALUES (?,?,?,?,?,?,?)";
            java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
            System.out.println(date_sql);
            int nonVerifie = 0;
            PreparedStatement prst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            prst.setString(1, v.getTitre());

            prst.setString(2, v.getDescriptionVideo());
            prst.setString(3, v.getTags());
            prst.setString(4, v.getVideo());
            prst.setDate(5, date_sql);
            prst.setInt(7, v.getIdUser().getId());
            prst.setInt(6, nonVerifie);

            prst.executeUpdate();
            System.out.println("Video Ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public ObservableList<Videodiy> afficherVideoPartenaire(int userID) {
        ObservableList<Videodiy> data;
        data = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from videodiy where id_user=" + userID;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Videodiy v = new Videodiy();
                v.setIdVideo(rs.getInt(2));
                v.setTitre(rs.getString(8));
                v.setVideo(rs.getString(9));
                v.setDescriptionVideo(rs.getString(3));
                v.setValid(rs.getInt(10));

                v.setDateExpoVideo(rs.getDate(5));
                // Button supprimer = new Button("supprimer");
                /*  Button modifier = new Button("modifier");
                //v.setSupprimer(supprimer);
                v.setModifier(modifier);
                
                 modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                videoController.modifierVideo(event);
                
            }
        });*/


 /*modifier.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {

                    }
                });*/
                data.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return data;
    }

    @Override
    public ObservableList<Videodiy> afficherVideoClient() {

        ObservableList<Videodiy> listVideo = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from videodiy where valid=1";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Videodiy v = new Videodiy();
                v.setVideo(rs.getString(9));
                v.setTitre(rs.getString(8));
                v.setDescriptionVideo(rs.getString(3));
                v.setIdVideo(rs.getInt(2));

                listVideo.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return listVideo;
    }

    @Override
    public void supprimerVideoDIY(int id) {
        try {
            String requete2 = "DELETE from videodiy WHERE idVideo=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setInt(1, id);

            int i = pst2.executeUpdate();//exucute query select kahaw
            if (i == 1) {
                //AlertDialog.display("Message de Retour", "aaaaaaaaaaa");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    @Override
    public void modifierVideoDIY(int id) {
        String requete2 = "UPDATE videodiy SET titre=?,descriptionVideo=? WHERE idVideo=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete2);

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public void saveFile(File f, String name) {

        InputStream inStream = null;
        OutputStream outStream = null;

        try {

            File Copyfile = new File("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\videos\\" + name);

            inStream = new FileInputStream(f);
            outStream = new FileOutputStream(Copyfile);

            byte[] buffer = new byte[(int) f.length()];

            int length;
            //copy the file content in bytes 
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            //delete the original file
            //f.delete();
            System.out.println("File is copied successful!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ObservableList<Videodiy> chercherVideo(String tags) {
        ObservableList<Videodiy> listVideo = FXCollections.observableArrayList();
        try {
            String requete3 = "select * from videodiy where tags LIKE '%" + tags + "%'";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete3);
            // pst2.setString(1,"%" + tags + "%");
            ResultSet rs = pst2.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement

            while (rs.next()) {
                Videodiy v = new Videodiy();

                v.setVideo(rs.getString(9));
                v.setTitre(rs.getString(8));
                v.setDescriptionVideo(rs.getString(3));
                v.setIdVideo(rs.getInt(2));
                // v.setDateExpoVideo(rs.getDate(5));

                listVideo.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return listVideo;

    }

    @Override
    public void modifierAvgRating(Videodiy v, int videoID) {
        try {
            String requete2 = "UPDATE videodiy SET avg_rating=? WHERE idVideo=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setDouble(1, v.getAvgRating());
            pst2.setInt(2, videoID);
            pst2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VideoDIYService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Videodiy> topRatedVideo() {

        ObservableList<Videodiy> listVideo = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from videodiy where valid=1 and avg_rating>=1 ORDER BY avg_rating DESC";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {

                Videodiy v = new Videodiy();
                v.setVideo(rs.getString(9));
                v.setTitre(rs.getString(8));
                v.setDescriptionVideo(rs.getString(3));
                v.setIdVideo(rs.getInt(2));

                listVideo.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return listVideo;
    }
    

    @Override
    public ObservableList<Videodiy> afficherVideoRecc(String tag1, String tag2, String tag3) {
        ObservableList<Videodiy> listVideo = FXCollections.observableArrayList();
        try {
           String requete3 = "select * from videodiy where (( tags LIKE '%" + tag1 + "%' ) OR ( tags LIKE '%" + tag2 + "%' ) OR ( tags LIKE '%" + tag3 + "%' )) AND ( valid=1 ) ";
          //  String requete3 = "select * from videodiy where ( tags LIKE '%" + tag1 + "%'  OR tags LIKE '%" + tag2 + "%'  OR  tags LIKE '%" + tag3 + "%' AND valid=1 ) ";

            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                
                Videodiy v = new Videodiy();
                v.setVideo(rs.getString(9));
                v.setTitre(rs.getString(8));
                v.setDescriptionVideo(rs.getString(3));
                v.setIdVideo(rs.getInt(2));

                listVideo.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return listVideo;
    }
}
