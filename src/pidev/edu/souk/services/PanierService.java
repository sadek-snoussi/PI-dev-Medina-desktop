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
import java.util.NoSuchElementException;
import pidev.edu.souk.Iservices.IPanier;
import pidev.edu.souk.entities.Panier;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author Sofienne
 */
public class PanierService implements IPanier {

    @Override
    public void ajouterPanier(Panier p) {
        
        try {
            String requete = "INSERT INTO panier (user_id,produit_id,flag) values (?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, p.getUserId().getId());
            pst.setInt(2, p.getProduitId().getIdProduit());
            pst.setInt(3, 0);
            pst.executeUpdate();
            System.out.println("*************** Produit ajouter au panier **********************");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Panier> selectPanierById(int iduser,int flag) {
        List<Panier> list = new ArrayList<Panier>();
        String requete = "SELECT * FROM panier WHERE user_id="+iduser+" and flag="+flag;
        
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Panier pn = new Panier();
                Produit pr = new Produit();
                pn.setId(rs.getInt(1));
                pr.setIdProduit(rs.getInt(3));
                pn.setProduitId(pr);
                pn.setQuantiteProduit(rs.getInt(5));
                list.add(pn);
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }

    @Override
    public ArrayList<Produit> selectProduitById(int idproduit) {
        ArrayList<Produit> list = new ArrayList<Produit>();
        String requete="SELECT * FROM produit WHERE idProduit="+idproduit;
        
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Produit produit = new Produit();
                produit.setIdProduit(rs.getInt(1));
                produit.setNomProduit(rs.getString(2));
                produit.setUrlImgProduit(rs.getString(9));
                produit.setPrixVenteProduit(rs.getDouble(6));
                produit.setQteDispoProduit(rs.getInt(12));
                list.add(produit);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        return list;
    }

    @Override
    public void supprimerPduPanier(int idPanier) {
        String requete = "DELETE FROM panier WHERE id="+idPanier;
        try {
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public boolean findProduitInPanierBoolean(Panier p ) {
        boolean var;  
       
            if(p!=null){   
            var=true;
            System.out.println("je suis dans la fonction != null nrml true le res est "+var);
        }
        else{
            var=false;
            System.out.println("je suis dans la fonction else nrml false le res est "+var);
        }
        
       
        return var;
    }

    @Override
    public Panier findProduitInPanier(int idProduit,int idUser) {
        Panier panier = new Panier();
        ArrayList<Panier> listPanier = new ArrayList<Panier>();
        String requete = "SELECT * FROM panier WHERE produit_id="+idProduit+" AND user_id="+idUser;
        
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                panier.setId(rs.getInt(1));
                listPanier.add(panier);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Panier panierFinale = listPanier.stream().findFirst().get();
        return panierFinale;
    }
    
    

    @Override
    public void ajouterQuantite(Panier p,int nouvelleValeur) {
        String requete = "Update  panier set quantiteProduit=? where id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
          //  pst.setInt(1, );
            nouvelleValeur=selectQuantiteProduit(p)+1;
            pst.setInt(1, nouvelleValeur);
                pst.setInt(2, p.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public int selectQuantiteProduit(Panier p)  {
        
        int quatiteProduit=0;
        try {
            ArrayList<Integer> listQuantiteProduit = new ArrayList<Integer>();
            String requete = "SELECT * FROM panier where id='"+p.getId()+"'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                int x=rs.getInt("quantiteProduit");
                listQuantiteProduit.add(x);
            }
            try{
                quatiteProduit=listQuantiteProduit.stream().findFirst().get();
            }catch(NoSuchElementException ex){
                
            }
            
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          return quatiteProduit;
    }

    @Override
    public void quantiteProduitInPanier(int quantite,int idPanier) {
        String requete = "Update  panier set quantiteProduit=? where id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, quantite);
            pst.setInt(2, idPanier);
            pst.executeUpdate();
            System.out.println("updateQuantite");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void moinsStockProduit(int quantite,int idProduit) {
        
    }

    @Override
    public void plusStockProduit(int quantite,int idProduit) {
       
    }

    @Override
    public void restoreQuantiteProduit(int quantite,int idProduit) {
        
    }

    @Override
    public Panier findPanierByIdProduit(int idProduit) {
        Panier pfinale=new Panier();
        Panier panier = new Panier();
        ArrayList<Panier> list = new ArrayList<Panier>();
        String requete = "SELECT * FROM panier where produit_id="+idProduit;
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                User u = new User();
                Produit p = new Produit();
                panier.setId(rs.getInt(1));
                u.setId(rs.getInt(2));
                panier.setUserId(u);
                p.setIdProduit(rs.getInt(3));
                panier.setProduitId(p);
                panier.setFlag(rs.getInt(4));
                panier.setQuantiteProduit(rs.getInt(5));
                list.add(panier);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        pfinale=list.stream().findFirst().get();
        
        return pfinale;
    }
    
}
