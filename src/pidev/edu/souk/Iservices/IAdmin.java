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
public interface IAdmin {
       public List<Videodiy> afficherVideoNonValide();
       public void validerVideoDIY(int id);
       public void rejeterVideoDIY(int id);

    
}
