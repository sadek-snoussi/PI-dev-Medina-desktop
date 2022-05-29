/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.List;
import javafx.collections.ObservableList;
import pidev.edu.souk.entities.Videodiy;

/**
 *
 * @author hp
 */
public interface IVideoDIY {

    public void ajouterVideoDIY(Videodiy v);

    public ObservableList<Videodiy> afficherVideoPartenaire(int userID);

    public ObservableList<Videodiy> afficherVideoClient();

    //public Videodiy aficherDetailVideoDIY(Videodiy v);

    public void supprimerVideoDIY(int id);

    public void modifierVideoDIY(int id);
    
    public ObservableList<Videodiy> chercherVideo(String tags);
    
    public void modifierAvgRating(Videodiy v, int videoID);
    
     public ObservableList<Videodiy> topRatedVideo();
     public ObservableList<Videodiy> afficherVideoRecc(String tag1, String tag2, String tag3);
     
}
