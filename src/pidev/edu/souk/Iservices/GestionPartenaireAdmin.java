/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.List;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.Videodiy;

/**
 *
 * @author amalb
 */
public interface GestionPartenaireAdmin {
    
     public void validerDemandePartenariat(List<User>users);
     public void supprimerDemandePartenariat(List<User>users);
      public void supprimerClient(List<User> user);
      public List<User>listClientAdmin();
      public List<User>listPartenaireAdmin();
      public List<User>listPartenaireNonValidesAdmin();
      public List<Produit>listProduitsByUser(User user);
      public List<Videodiy>listVideosByUser(User user);
      public User finUserById(int id);
      
}
