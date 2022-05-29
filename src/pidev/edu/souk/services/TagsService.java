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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.souk.Iservices.ITags;
import pidev.edu.souk.controller.DetailVideoController;
import pidev.edu.souk.entities.Tags;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author hp
 */
public class TagsService implements ITags {

    @Override
    public void ajouterTag(Tags tag) {
        String requete = "INSERT INTO tags (tag,user_id)"
                + " VALUES (?,?)";

        PreparedStatement prst;
        try {
            prst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            prst.setString(1, tag.getTag());
            prst.setInt(2, tag.getUserId().getId());

            prst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DetailVideoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Tags> listeTags(int userID) {

        ObservableList<Tags> listTags = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from tags where user_id='"+userID+"' ORDER BY tag_id DESC";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
           
            while (rs.next()) {
                Tags t = new Tags();
                t.setTag(rs.getString(3));
                t.setTagId(rs.getInt(1));
              

                listTags.add(t);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return listTags;
    }
}


