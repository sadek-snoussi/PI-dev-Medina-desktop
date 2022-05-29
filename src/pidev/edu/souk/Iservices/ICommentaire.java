/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.List;
import javafx.collections.ObservableList;
import pidev.edu.souk.entities.Commentaire;
import pidev.edu.souk.entities.Videodiy;

/**
 *
 * @author hp
 */
public interface ICommentaire {
    
    public void ajouterCommentaire(Commentaire c);
    public void supprimerCommentaire(Commentaire c);
    public void modifierCommentaire(Commentaire c);
    public ObservableList<Commentaire>afficherCommentaires();
    public List<Commentaire> chercherCommentaireParNom(String nomUti);
    
}
