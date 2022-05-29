/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.List;
import pidev.edu.souk.entities.Videodiy;

/**
 *
 * @author hp
 */
public interface GestionVideoAdmin {

    public List<Videodiy> afficherVideoNonValide();

    public void validerVideoDIY();

    public void rejeterVideoDIY();

}
