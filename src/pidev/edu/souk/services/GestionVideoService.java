/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev.edu.souk.Iservices.GestionVideoAdmin;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author hp
 */
public class GestionVideoService implements GestionVideoAdmin {

    @Override
    public List<Videodiy> afficherVideoNonValide() {

        List<Videodiy> listeVideoNonValide = new ArrayList<Videodiy>();

        try {
            String requete3 = "select * from videodiy where valid=0";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Videodiy v = new Videodiy();
                v.setIdVideo(rs.getInt(2));
                v.setTitre(rs.getString(8));
                //  v.setVideo(rs.getString(9));
                v.setDescriptionVideo(rs.getString(3));
                listeVideoNonValide.add(v);
                // Button valider = new Button("Valider");
                // Button rejeter = new Button("Rejeter");

                /*modifier.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {

                    }
                });*/
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return listeVideoNonValide;

    }

    @Override
    public void validerVideoDIY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rejeterVideoDIY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
