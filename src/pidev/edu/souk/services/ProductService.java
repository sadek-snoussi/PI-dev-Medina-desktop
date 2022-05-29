/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.edu.souk.Iservices.IserviceProduct;
import pidev.edu.souk.entities.Categorie;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author admin
 */
public class ProductService implements IserviceProduct{
    
    
     //*****************************************************************************************
     //*************************************** CRUD **************************************************
     //*****************************************************************************************

    
    
     @Override
     public void add(Produit p) {

        try {
            
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());

            String requete = "INSERT INTO Produit (nomProduit,MatiereProduit,PrixBaseProduit,PrixVenteProduit,dateExpirationProduit,reference_prod,qteDispoProduit,IdCategorie,IdUser,dateExpoProduit,validite_produit,urlImgProduit) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            prst.setString(1, p.getNomProduit());
            prst.setString(2, p.getMatiereProduit());    
            prst.setDouble(3, p.getPrixBaseProduit());
            prst.setDouble(4, p.getPrixVenteProduit());
            prst.setDate(5, (Date) p.getDateExpirationProduit());
            prst.setString(6, p.getReferenceProd());
            prst.setInt(7, p.getQteDispoProduit());
            prst.setInt(8, p.getIdCategorie().getIdCategorie());
            prst.setInt(9, p.getIdUser().getId());
            prst.setDate(10,date_sql);
            prst.setInt(11, 0);
            prst.setString(12, p.getUrlImgProduit());
            

            prst.executeUpdate();
            System.out.println("Produit Ajouté.");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
    
    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    
     @Override
      public void update(Produit p,int id){

                  try {
            
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());

            String requete = "UPDATE  Produit SET nomProduit=?,MatiereProduit=?,PrixBaseProduit=?,PrixVenteProduit=?,reference_prod=?,qteDispoProduit=?,IdCategorie=?,dateExpoProduit=?,validite_produit=? "
                    + " WHERE idProduit=?";
            PreparedStatement prst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            prst.setString(1, p.getNomProduit());
            prst.setString(2, p.getMatiereProduit());    
            prst.setDouble(3, p.getPrixBaseProduit());
            prst.setDouble(4, p.getPrixVenteProduit());
            //prst.setDate(4, (Date) p.getDateExpirationProduit());
            prst.setString(5, p.getReferenceProd());
            prst.setInt(6, p.getQteDispoProduit());
            prst.setInt(7, p.getIdCategorie().getIdCategorie());
            prst.setDate(8,date_sql);
            prst.setInt(9, 0);
            prst.setInt(10, id);


            
            //prst.setString(9, p.getUrlImgProduit());
            

            prst.executeUpdate();
            System.out.println("Produit Modifié.");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }














 }
      
      
      
    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    
    @Override
      public List<Produit> list(){
    
        ObservableList<Produit> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Produit";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
            
//             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
//             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
//             ResultSet rs2= st2.executeQuery(req2);
//             Categorie c=new Categorie();
//             
//             while(rs2.next()){
//                 
//                 c.setIdCategorie(rs2.getInt(1));
//                 c.setNomCategorie(rs2.getString(2));
//                 c.setTypeCategorie(rs2.getString(3));
//             }
//             
//             
//             System.out.println(c);
            
            
            while(rs.next()){
                
                
             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categorie c=new Categorie();
             
             while(rs2.next()){
                 
                 c.setIdCategorie(rs2.getInt(1));
                 c.setNomCategorie(rs2.getString(2));
                 c.setTypeCategorie(rs2.getString(3));
             }
             
             
             System.out.println(c);
                
                

                Produit p = new Produit();
                
                
                p.setIdProduit(rs.getInt(1));
                p.setReferenceProd(rs.getString(10));
                p.setDateExpoProduit(rs.getDate(7));
                p.setNomProduit(rs.getString(2));
                p.setIdCategorie(c);
                p.setMatiereProduit(rs.getString(4));
                p.setUrlImgProduit(rs.getString(9));
                p.setValiditeProduit(rs.getInt(13));
                p.setPrixBaseProduit(rs.getDouble(5));
                p.setPrixVenteProduit(rs.getDouble(6));
                p.setQteDispoProduit(rs.getInt(12));
                p.setDateExpirationProduit(rs.getDate(8));
                p.setQteVenduProduit(rs.getInt(14));

                System.out.println(rs.getString(8));
                
            Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        
        
            
    
    return Produits;
}
      
    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
      
      
      public List<Produit> ProductsForClients(){
    
        List<Produit> Produits=new ArrayList();
    
        try {
            
            
            String req="SELECT * FROM Produit where validite_produit=2";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
            
//             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
//             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
//             ResultSet rs2= st2.executeQuery(req2);
//             Categorie c=new Categorie();
//             
//             while(rs2.next()){
//                 
//                 c.setIdCategorie(rs2.getInt(1));
//                 c.setNomCategorie(rs2.getString(2));
//                 c.setTypeCategorie(rs2.getString(3));
//             }
//             
//             
//             System.out.println(c);
            
            
            while(rs.next()){
                
                
             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categorie c=new Categorie();
             
             while(rs2.next()){
                 
                 c.setIdCategorie(rs2.getInt(1));
                 c.setNomCategorie(rs2.getString(2));
                 c.setTypeCategorie(rs2.getString(3));
             }
             
             
             System.out.println(c);
                
                

                Produit p = new Produit();
                
                
                p.setIdProduit(rs.getInt(1));
                p.setReferenceProd(rs.getString(10));
                p.setDateExpoProduit(rs.getDate(7));
                p.setNomProduit(rs.getString(2));
                p.setIdCategorie(c);
                p.setMatiereProduit(rs.getString(4));
                p.setUrlImgProduit(rs.getString(9));
                p.setValiditeProduit(rs.getInt(13));
                p.setPrixBaseProduit(rs.getDouble(5));
                p.setPrixVenteProduit(rs.getDouble(6));
                p.setQteDispoProduit(rs.getInt(12));
                p.setDateExpirationProduit(rs.getDate(8));

                System.out.println(rs.getString(8));
                
            Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        
        
            
    
    return Produits;
}
      
      
      
      
      
    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    
     @Override 
     public void delete(int i) {

        try {

            String requete = "DELETE from Produit where idProduit=?";
            PreparedStatement prst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            prst.setInt(1,i);
            prst.executeUpdate();
            System.out.println("Produit Supprimé.");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
     
     
     //*****************************************************************************************
     //**************************************PANEL ADMIN***************************************************
     //*****************************************************************************************

    
    
     
    
     public List<Produit> listValidite(){
    
        ObservableList<Produit> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Produit WHERE validite_produit=0"       ;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
            
//             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
//             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
//             ResultSet rs2= st2.executeQuery(req2);
//             Categorie c=new Categorie();
//             
//             while(rs2.next()){
//                 
//                 c.setIdCategorie(rs2.getInt(1));
//                 c.setNomCategorie(rs2.getString(2));
//                 c.setTypeCategorie(rs2.getString(3));
//             }
//             
//             
//             System.out.println(c);
            
            
            while(rs.next()){
                
             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categorie c=new Categorie();
             
             while(rs2.next()){
                 
                 c.setIdCategorie(rs2.getInt(1));
                 c.setNomCategorie(rs2.getString(2));
                 c.setTypeCategorie(rs2.getString(3));
             }
                
             
             String req3= "SELECT * FROM USER where id='"+rs.getInt(16)+"'";
             Statement st3 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs3= st3.executeQuery(req3);
             User u=new User();
             
             while(rs3.next()){
                 
                 u.setId(rs3.getInt(1));
                 u.setUsername(rs3.getString(2));
                 u.setTelUser(rs3.getString(17));
        
                 
                 
             }
                    

                Produit p = new Produit();
                
                p.setIdProduit(rs.getInt(1));
                p.setReferenceProd(rs.getString(10));
                p.setDateExpoProduit(rs.getDate(7));
                p.setNomProduit(rs.getString(2));
                p.setIdCategorie(c);
                p.setMatiereProduit(rs.getString(4));
                p.setUrlImgProduit(rs.getString(9));
                p.setValiditeProduit(rs.getInt(13));
                p.setPrixBaseProduit(rs.getDouble(5));
                p.setPrixVenteProduit(rs.getDouble(6));
                p.setQteDispoProduit(rs.getInt(12));
                p.setDateExpirationProduit(rs.getDate(8));
                p.setIdUser(u);

                
                
            Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        
        
            
    
    return Produits;
     
     
     }
     
     public void AcceptProduct(int id) {
         
         
         try {
             String requete = "UPDATE Produit SET validite_produit=2 "
                     + " WHERE idProduit=?";
             PreparedStatement prst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
             
             prst.setInt(1, id);
             
             prst.executeUpdate();
             
             System.out.println("Produit Accepté.");
         } catch (SQLException ex) {
             Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     public void DenyProduct(int id){
         
         try {
             String requete = "UPDATE Produit SET validite_produit=1 "
                     + " WHERE idProduit=?";
             PreparedStatement prst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
             
             prst.setInt(1,id);
             
             prst.executeUpdate();
             
             
             
             System.out.println("Produit Refusé.");
         } catch (SQLException ex) {
             Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
         }

     }
     
     
     
     //*************************************************************************   
     //******************************RechercheBy && OerBy***********************   
     //*************************************************************************    
     

      public List<Produit> FindByName(String key){
          
        ObservableList<Produit> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Produit where validite_produit=2 and nomProduit LIKE '"+key+"%' ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
                        
            
            while(rs.next()){
                
                
             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categorie c=new Categorie();
             
             while(rs2.next()){
                 
                 c.setIdCategorie(rs2.getInt(1));
                 c.setNomCategorie(rs2.getString(2));
                 c.setTypeCategorie(rs2.getString(3));
             }
             
             
                
                

                Produit p = new Produit();
                
                
                p.setIdProduit(rs.getInt(1));
                p.setReferenceProd(rs.getString(10));
                p.setDateExpoProduit(rs.getDate(7));
                p.setNomProduit(rs.getString(2));
                p.setIdCategorie(c);
                p.setMatiereProduit(rs.getString(4));
                p.setUrlImgProduit(rs.getString(9));
                p.setValiditeProduit(rs.getInt(13));
                p.setPrixBaseProduit(rs.getDouble(5));
                p.setPrixVenteProduit(rs.getDouble(6));
                p.setQteDispoProduit(rs.getInt(12));
                p.setDateExpirationProduit(rs.getDate(8));

                System.out.println(rs.getString(8));
                
                Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        
                System.out.println(Produits);

            
    
    return Produits;
      }
      
      //-----------------------------------------------------------------------
      
      
      public List<Produit> FindByPrice(double min,double max){
          
        ObservableList<Produit> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Produit where validite_produit=2 AND prixVenteProduit BETWEEN '"+min+"' AND '"+max+"' " ;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
                        
            
            while(rs.next()){
                
                
             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categorie c=new Categorie();
             
             while(rs2.next()){
                 
                 c.setIdCategorie(rs2.getInt(1));
                 c.setNomCategorie(rs2.getString(2));
                 c.setTypeCategorie(rs2.getString(3));
             }

                Produit p = new Produit();
                
                
                p.setIdProduit(rs.getInt(1));
                p.setReferenceProd(rs.getString(10));
                p.setDateExpoProduit(rs.getDate(7));
                p.setNomProduit(rs.getString(2));
                p.setIdCategorie(c);
                p.setMatiereProduit(rs.getString(4));
                p.setUrlImgProduit(rs.getString(9));
                p.setValiditeProduit(rs.getInt(13));
                p.setPrixBaseProduit(rs.getDouble(5));
                p.setPrixVenteProduit(rs.getDouble(6));
                p.setQteDispoProduit(rs.getInt(12));
                p.setDateExpirationProduit(rs.getDate(8));

                
                Produits.add(p);
            }
            
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
                   
    
    return Produits;
      
      }  
 //---------------------------------------------------------------------------
      
      public List<Produit> FindByCategory(int i){
          
                    
        ObservableList<Produit> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Produit where validite_produit=2 AND  idCategorie="+i+" " ;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
                        
            
            while(rs.next()){
                
                
             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categorie c=new Categorie();
             
             while(rs2.next()){
                 
                 c.setIdCategorie(rs2.getInt(1));
                 c.setNomCategorie(rs2.getString(2));
                 c.setTypeCategorie(rs2.getString(3));
             }
             
             
                
                

                Produit p = new Produit();
                
                
                p.setIdProduit(rs.getInt(1));
                p.setReferenceProd(rs.getString(10));
                p.setDateExpoProduit(rs.getDate(7));
                p.setNomProduit(rs.getString(2));
                p.setIdCategorie(c);
                p.setMatiereProduit(rs.getString(4));
                p.setUrlImgProduit(rs.getString(9));
                p.setValiditeProduit(rs.getInt(13));
                p.setPrixBaseProduit(rs.getDouble(5));
                p.setPrixVenteProduit(rs.getDouble(6));
                p.setQteDispoProduit(rs.getInt(12));
                p.setDateExpirationProduit(rs.getDate(8));

                
                Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
              
    
    return Produits;
          
      }     
      
    //--------------------------------------------------------------------------
      
      public List<Produit> OrderByAsc(){
          
          ObservableList<Produit> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM PRODUIT WHERE validite_produit=2 ORDER BY prixVenteProduit ASC ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
            

            while(rs.next()){
                
                
             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categorie c=new Categorie();
             
             while(rs2.next()){
                 
                 c.setIdCategorie(rs2.getInt(1));
                 c.setNomCategorie(rs2.getString(2));
                 c.setTypeCategorie(rs2.getString(3));
             }
             
                Produit p = new Produit();
                
                p.setIdProduit(rs.getInt(1));
                p.setReferenceProd(rs.getString(10));
                p.setDateExpoProduit(rs.getDate(7));
                p.setNomProduit(rs.getString(2));
                p.setIdCategorie(c);
                p.setMatiereProduit(rs.getString(4));
                p.setUrlImgProduit(rs.getString(9));
                p.setValiditeProduit(rs.getInt(13));
                p.setPrixBaseProduit(rs.getDouble(5));
                p.setPrixVenteProduit(rs.getDouble(6));
                p.setQteDispoProduit(rs.getInt(12));
                p.setDateExpirationProduit(rs.getDate(8));
                
                Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        
        
            
    
    return Produits;
      }     

      //------------------------------------------------------------------------
      
      public List<Produit> OrderByDesc(){
          
          ObservableList<Produit> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Produit where validite_produit=2 ORDER BY prixVenteProduit DESC";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
            

            while(rs.next()){
                
                
             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categorie c=new Categorie();
             
             while(rs2.next()){
                 
                 c.setIdCategorie(rs2.getInt(1));
                 c.setNomCategorie(rs2.getString(2));
                 c.setTypeCategorie(rs2.getString(3));
             }
             
             
                 Produit p = new Produit();
                
                
                p.setIdProduit(rs.getInt(1));
                p.setReferenceProd(rs.getString(10));
                p.setDateExpoProduit(rs.getDate(7));
                p.setNomProduit(rs.getString(2));
                p.setIdCategorie(c);
                p.setMatiereProduit(rs.getString(4));
                p.setUrlImgProduit(rs.getString(9));
                p.setValiditeProduit(rs.getInt(13));
                p.setPrixBaseProduit(rs.getDouble(5));
                p.setPrixVenteProduit(rs.getDouble(6));
                p.setQteDispoProduit(rs.getInt(12));
                p.setDateExpirationProduit(rs.getDate(8));
                
                Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        
        
            
    
    return Produits;
      }     
      
      //*************************************************************************             
     //********************************Recherche du stock*****************************************        
     
       public List<Produit> rechercheStock(int idUser,String key){
          
          ObservableList<Produit> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Produit where idUser="+idUser+" and nomProduit LIKE '"+key+"%' ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
            

            while(rs.next()){
                
                
             String req2= "SELECT * FROM categorie where idCategorie='"+rs.getInt(15)+"'";
             Statement st2 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categorie c=new Categorie();
             
             while(rs2.next()){
                 
                 c.setIdCategorie(rs2.getInt(1));
                 c.setNomCategorie(rs2.getString(2));
                 c.setTypeCategorie(rs2.getString(3));
             }
             
             
                 Produit p = new Produit();
                
                
                p.setIdProduit(rs.getInt(1));
                p.setReferenceProd(rs.getString(10));
                p.setDateExpoProduit(rs.getDate(7));
                p.setNomProduit(rs.getString(2));
                p.setIdCategorie(c);
                p.setMatiereProduit(rs.getString(4));
                p.setUrlImgProduit(rs.getString(9));
                p.setValiditeProduit(rs.getInt(13));
                p.setPrixBaseProduit(rs.getDouble(5));
                p.setPrixVenteProduit(rs.getDouble(6));
                p.setQteDispoProduit(rs.getInt(12));
                p.setDateExpirationProduit(rs.getDate(8));
                
                Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        
        
            
    
    return Produits;
      }     
      
      
     
     
     //*************************************************************************   
     //******************************UTIL*******************************************    
     //*************************************************************************    
     
     
     
     
    public void saveFile(File f,String name) {


        InputStream inStream = null;
	OutputStream outStream = null;
		
    	try{
    		
            
    	    File Copyfile =new File("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\ImgProduit\\"+name);
    		
    	    inStream = new FileInputStream(f);
    	    outStream = new FileOutputStream(Copyfile);
        	
    	    byte[] buffer = new byte[1024];
    		
    	    int length;
    	    //copy the file content in bytes 
    	    while ((length = inStream.read(buffer)) > 0){
    	  
    	    	outStream.write(buffer, 0, length);
    	 
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    
    	    //delete the original file
    	    //f.delete();
    	    
    	    System.out.println("File is copied successful!");
    	    
    	}catch(IOException e){
            
    	}
        

    
    
}

     
     
     
     
     
     
}
