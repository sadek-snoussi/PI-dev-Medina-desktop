/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.ArrayList;
import java.util.List;
import pidev.edu.souk.entities.Panier;
import pidev.edu.souk.entities.Produit;

/**
 *
 * @author Sofienne
 */
public interface IPanier {
    
    public void ajouterPanier(Panier panier);
    public List <Panier> selectPanierById(int iduser,int flag);
    public Panier findProduitInPanier(int produit,int iduser);
    public boolean findProduitInPanierBoolean(Panier panier);
    public List <Produit> selectProduitById(int idproduit);
    public void supprimerPduPanier(int idProduit);
    public void ajouterQuantite(Panier panier,int x);
    public int selectQuantiteProduit(Panier panier)  ;
    public void quantiteProduitInPanier(int quantite,int idPanier);
    public void moinsStockProduit(int quantite,int idProduit);
    public void plusStockProduit(int quantite,int idProduit);
    public void restoreQuantiteProduit(int quantite,int idProduit);
    public Panier findPanierByIdProduit(int idProduit);
    
}
