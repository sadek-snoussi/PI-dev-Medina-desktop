/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.List;
import pidev.edu.souk.entities.Gallerie;


/**
 *
 * @author Khalil
 */
public interface IGallerie {
    public void ajouterGallerie(Gallerie e);
    public void supprimerGallerie(int idGallerie);
    public void modifierGallerie(int idGallerie, Gallerie e);
    public List<Gallerie>afficherGallerieAdmin();
    public List<Gallerie>afficherGallerieClient();
    public List<Gallerie> chercherGallerieParTag(String tag);
    public int chercherGallerieParTagCount(String tag);
    public List<Gallerie> chercherGallerieParGouvernerat(String nomGouvernerat);
    public int chercherGallerieParGouvCount(String tag);
    public List<Gallerie> chercherGallerieParType(String TypeGallerie);
    public int chercherGallerieParTypeCount(String tag);
    public List<Gallerie> chercherGallerieALL(String tag,String TypeGallerie, String nomGouvernerat);
    public int chercherGallerieALLCount(String TypeGallerie, String nomGouvernerat,String tag);
    public List<Gallerie> trieup();
    public List<Gallerie> triedown();
    
    }
