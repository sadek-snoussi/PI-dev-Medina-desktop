/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.ArrayList;
import java.util.List;
import pidev.edu.souk.entities.Commande;
import pidev.edu.souk.entities.Panier;

/**
 *
 * @author Sofienne
 */
public interface ICommande {
    
    public void ajouterCommande(Commande cmd);
    public List<Commande> selectAllCommande();
    public void validerCommande(int id);
    public double calculerTotalCommande(int idUser);
    public void setFlag(int idUser);
    public void modifStockProduit(int idProduit, int qtDispoProduit , int qtVenduProduit );
    public void ModifStock(int idUser);
    public ArrayList<Panier> selectPanierWithFlag(int idUser);
    
    
}
