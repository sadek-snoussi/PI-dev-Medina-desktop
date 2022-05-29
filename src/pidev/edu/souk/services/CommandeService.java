/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.edu.souk.entities.Commande;
import pidev.edu.souk.utils.MyConnection;
import pidev.edu.souk.Iservices.ICommande;
import pidev.edu.souk.entities.Panier;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.entities.User;

/**
 *
 * @author Sofienne
 */
public class CommandeService implements ICommande{

    @Override
    public void ajouterCommande(Commande commande) {
        
        try {
            String requete = "INSERT INTO commande (user_id,panier_id,dateCommande,etatCommande,totalPrixCommande,nom,prenom,email,tel,pays,gouvernorat,ville,adresse,codepostale) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, commande.getUserId().getId());
            pst.setInt(2, commande.getPanierId().getId());
            pst.setDate(3, (Date)commande.getDateCommande());
            pst.setString(4, "En cours de validation");
            pst.setDouble(5, commande.getTotalPrixCommande());
            pst.setString(6, commande.getNom());
            pst.setString(7, commande.getPrenom());
            pst.setString(8,commande.getEmail() );
            pst.setString(9, commande.getTel());
            pst.setString(10, commande.getPays());
            pst.setString(11, commande.getGouvernorat());
            pst.setString(12, commande.getVille());
            pst.setString(13, commande.getAdresse());
            pst.setString(14, commande.getCodepostale());
            pst.executeUpdate();
            System.out.println("Commande ajouter avec succes");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }

    @Override
    public List<Commande> selectAllCommande() {
        List<Commande> commandeList = new ArrayList<>();
        String requete = "SELECT * FROM commande";
            try{
                Statement statement = MyConnection.getInstance().getCnx().createStatement();
                ResultSet resultSet = statement.executeQuery(requete);
                while(resultSet.next()){
                    Commande commande = new Commande();
                    commande.setNom(resultSet.getString(9));
                    commande.setPrenom(resultSet.getString(10));
                    commande.setEtatCommande(resultSet.getString("etatCommande"));
                    commande.setIdCommande(resultSet.getInt(3));
                    commandeList.add(commande);
                }
            
            }
            
            catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
        
        return commandeList;
    }

    @Override
    public void validerCommande(int id) {
        try {
            String requete="UPDATE commande SET etatcommande=? WHERE idCommande=?";
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(2, id);
            pst.setString(1, "valide");
            pst.executeUpdate();
            System.out.println("Commande modifiée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public double calculerTotalCommande(int idUser) {
        double totale=0;
        double prix;
        double produit;
        int quantite;
         String requete = "SELECT prixVenteProduit AS prix, quantiteProduit AS quantite FROM produit JOIN panier ON produit.idProduit=panier.produit_id WHERE panier.user_id='"+idUser+"' AND panier.flag="+0;
        
           try{
                Statement statement = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = statement.executeQuery(requete);
                while(rs.next()){
                  prix=rs.getDouble("prix");
                  System.out.println("le prix est "+prix);
                  quantite=rs.getInt("quantite");
                  System.out.println("le quantite est "+quantite);
                  produit = prix*quantite;
                  System.out.println("le produit est "+produit);
                  totale=totale+produit;
                  System.out.println("le totale est "+totale);
                }
            
            }
            
            catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
        
        
        return totale; 
    }

    @Override
    public void setFlag(int idUser) {
         String requete = "UPDATE panier SET flag=? WHERE user_id='"+idUser+"'";
         try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,1);
            pst.executeUpdate();
            System.out.println("Commande ajouter avec succes");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     @Override
    public void modifStockProduit(int idProduit, int qtDispoProduit, int qtVenduProduit ) {
        String requete = "UPDATE produit set qteDispoProduit=?, qteVenduProduit=? WHERE idProduit='"+idProduit+"'";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,qtDispoProduit);
            pst.setInt(2,qtVenduProduit);
            pst.executeUpdate();
            System.out.println("Stock modifie avec succes");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public void ModifStock(int idUser) {
       String requete = "SELECT qteDispoProduit AS qtDispo, qteVenduProduit AS qtVendu,  idProduit as id, quantiteProduit AS quantite FROM produit JOIN panier ON produit.idProduit=panier.produit_id WHERE panier.user_id='"+idUser+"' AND panier.flag='"+1+"'" ;
        try{
                Statement statement = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = statement.executeQuery(requete);
                int qtDispoProduit;
                int qtVenduProduit;
                int quantite;
                int id;
                while(rs.next()){
                    quantite=rs.getInt("quantite");
                    qtDispoProduit=rs.getInt("qtDispo");
                    qtVenduProduit=rs.getInt("qtVendu");
                    id=rs.getInt("id");
                    qtDispoProduit=qtDispoProduit-quantite;
                    qtVenduProduit=qtVenduProduit+quantite;
                    modifStockProduit(id, qtDispoProduit, qtVenduProduit);
                }
            
            }
            
            catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
       
    }

    @Override
    public ArrayList<Panier> selectPanierWithFlag(int idUser) {
        ArrayList<Panier> list = new ArrayList<Panier>();
        String requete = "SELECT * FROM panier WHERE user_id="+idUser+" AND flag="+1;
        
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Panier pn = new Panier();
                Produit pr = new Produit();
                User u = new User();
                pn.setId(rs.getInt(1));
                u.setId(rs.getInt(2));
                pr.setIdProduit(rs.getInt(3));
                pn.setUserId(u);
                pn.setProduitId(pr);
                pn.setFlag(1);
                pn.setQuantiteProduit(rs.getInt(5));
                list.add(pn);
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }

   
    
    
    
   
    
    
    
    
}
