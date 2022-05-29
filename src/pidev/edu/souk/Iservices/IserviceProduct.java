/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.List;
import pidev.edu.souk.entities.Produit;

/**
 *
 * @author admin
 */
public interface IserviceProduct {
    
    
    //---------------------------CRUD--------------------------

    
    public void add(Produit p);
    public void update(Produit p,int id);
    public void delete(int i);
    public List<Produit> list();
    
    //-----------------------------------------------------
    //-----------------------------------------------------
    
    
    
    
    
}
