/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.List;
import pidev.edu.souk.entities.Categorie;

/**
 *
 * @author admin
 */
public interface IserviceCategory {
    
    public void add(Categorie c);
    public void update(Categorie c,int i);
    public void delete(int i);
    public List<Categorie> list();

    
}
