/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import pidev.edu.souk.Iservices.IserviceCategory;
import pidev.edu.souk.entities.Categorie;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author admin
 */
public class CategoryService implements IserviceCategory{
    
    @Override
    public void add(Categorie c) {

        try {

            String requete = "INSERT INTO categorie (nomcategorie,typecategorie) VALUES (?,?)";
            
            PreparedStatement prst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            prst.setString(1, c.getNomCategorie());
            prst.setString(2, c.getTypeCategorie());

            prst.executeUpdate();
            System.out.println("Catégorie Ajoutée.");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
    
    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    
      @Override
      public void update(Categorie c,int i){
        
        try {
            String req="UPDATE categorie SET nomcategorie=?,typecategorie=? where idcategorie=?";
            PreparedStatement prep=MyConnection.getInstance().getCnx().prepareStatement(req);
            
            prep.setInt(3,i);            
            prep.setString(1, c.getNomCategorie());
            prep.setString(2, c.getTypeCategorie());
            
            prep.executeUpdate();
            System.out.println("Catégorie Modifiée.");

            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
        
        
    }
      
      
      
    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    
      @Override
      public List<Categorie> list(){
    
          ObservableList categories=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM categorie";
            
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs= st.executeQuery(req);
            
            while(rs.next()){
                
                Categorie c = new Categorie();
                
                c.setIdCategorie(rs.getInt("idCategorie"));
                c.setNomCategorie(rs.getString("nomcategorie"));
                c.setTypeCategorie(rs.getString("typecategorie"));
                
                System.out.println(c);

            
            categories.add(c);
           
            }
            System.out.println(categories);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
            
    
    return categories;
}
      
    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    
     @Override
     public void delete(int i) {

        try {

            String requete = "DELETE from Categorie where idcategorie=?";
            PreparedStatement prst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            prst.setInt(1,i);
            prst.executeUpdate();
            System.out.println("Catgorie Supprimée.");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }   
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
}

