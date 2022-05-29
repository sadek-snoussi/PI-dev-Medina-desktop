/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import pidev.edu.souk.entities.User;

/**
 *
 * @author amalb
 */
public interface GestionPartenaires {

    public void ajouterClient(User user);

    public void modifierProfileClient(User user, int id);

    public void modifierProfilePro(User user, int id);

    public void modifierProfileFree(User user, int id);

    public void listPartenaireContact();

    public void RechercherPartenaire();

    public void ajoutDemandePartenariatPro(User user, int id);

    public void ajoutDemandePartenariatFree(User user, int id);

    public User finUserById(int id);

    public Boolean findUserByEmail(String email);

    public Boolean findUserByUsername(String username);

    public int gestionPromotionCommande(int iduser);

    public void modifierPointFidelite(User user, int id);
    public int selectQuantiteProduit(int  idUser);

    
     
     
    
}
