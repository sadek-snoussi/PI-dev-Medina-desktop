/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import javafx.collections.ObservableList;
import pidev.edu.souk.entities.Commentaire;
import pidev.edu.souk.entities.Tags;
import pidev.edu.souk.entities.Videodiy;

/**
 *
 * @author hp
 */
public interface ITags {
    
    public void ajouterTag(Tags tag);
    public ObservableList<Tags> listeTags(int userID);
    
}
