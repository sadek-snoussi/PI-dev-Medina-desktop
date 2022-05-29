/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.List;
import pidev.edu.souk.entities.Bonplan;


/**
 *
 * @author Khalil
 */
public interface IBonplan {
    public void addBonplan(Bonplan b);
    public void DeleteBonplan(int idBonplan); 
    public void updateBonplan ( int idGuide,Bonplan bonplan);
    public List<Bonplan>afficherBonplanAdmin();
    public List<Bonplan>afficherBonplanClient();
    public List<Bonplan> chercherBonplanParGouvernerat(String nomGouvernerat);   
    public List<Bonplan> chercherBonplanParType(String type);
    public List<Bonplan> chercherBonplanParRating(Double rate);
    public List<Bonplan> OrderByNoteUp();    
    public List<Bonplan> OrderByNoteDown();    
            
}
