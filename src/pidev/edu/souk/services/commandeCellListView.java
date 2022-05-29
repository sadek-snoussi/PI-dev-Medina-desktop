/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import java.util.ArrayList;
import javafx.scene.control.ListCell;
import pidev.edu.souk.controller.CellCommandeController;
import pidev.edu.souk.controller.CellPanierController;
import pidev.edu.souk.controller.SingninController;
import pidev.edu.souk.entities.Panier;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.entities.User;

/**
 *
 * @author Sofienne
 */
public class commandeCellListView extends ListCell<Panier> {
    
    @Override
    public void updateItem(Panier panier,boolean empty){
        super.updateItem(panier, empty);
        if (panier != null){
            CellCommandeController data = new CellCommandeController();
            PanierService ps = new PanierService();
            ArrayList<Produit> list = ps.selectProduitById(panier.getProduitId().getIdProduit());  
            Produit produit =list.stream().findFirst().get();
            User u = new User();
            u.setId(SingninController.userIden);
            panier.setProduitId(produit);
            panier.setUserId(u);
            data.setInfo(panier);
            setGraphic(data.getCellCommande());
        }
    }
}
