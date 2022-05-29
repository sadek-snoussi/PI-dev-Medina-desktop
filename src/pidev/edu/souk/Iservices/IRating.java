/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import pidev.edu.souk.entities.Rating;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.Videodiy;

/**
 *
 * @author hp
 */
public interface IRating {
    public void ajouterNote(Rating note);
    public Rating suspendre(int userID,int videoID);
    public Double compterNote(Videodiy v);
    public void modifierNote(int userID,int videoID,Double rating);
   
    
  
    
    
}
