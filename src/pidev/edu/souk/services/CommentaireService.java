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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.souk.Iservices.ICommentaire;
import pidev.edu.souk.entities.Commentaire;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author hp
 */
public class CommentaireService implements ICommentaire{

    @Override
    public void ajouterCommentaire(Commentaire c) {
    }

    @Override
    public void supprimerCommentaire(Commentaire c) {
    }

    @Override
    public void modifierCommentaire(Commentaire c) {
    }

    @Override
    public ObservableList<Commentaire> afficherCommentaires() {
        return null;
         
    }

    @Override
    public List<Commentaire> chercherCommentaireParNom(String nomUti) {
        List<Commentaire> l = new ArrayList<Commentaire>();
        return l;
    }
    
}
