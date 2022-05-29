/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;
import java.util.List;
import pidev.edu.souk.entities.Guide;




/**
 *
 * @author Khalil
 */
public interface IGuide {
    public void addGuide(Guide g);
    public void DeleteGuide(int idGuide); 
    public void update ( int idGuide, Guide g);
    public List<Guide>afficherGuideAdmin();
    public List<Guide>afficherGuideClient();
    public List<Guide> chercherGuideParGouvernerat(String nomGouvernerat); 
    public int chercherGuideParGouverneratcount(String nomGouvernerat); 
    public Boolean ChercheGuideparEmail(String MailGuide);
}
