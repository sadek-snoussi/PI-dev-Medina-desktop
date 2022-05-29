/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.entities;

import java.util.Collection;


/**
 *
 * @author admin
 */
public class Panier {

   
    private Integer id;
    private int flag;
    private Collection<Commande> commandeCollection;
    private User userId;
    private Produit produitId;
    private int quantiteProduit;
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Panier() {
    }

    public Panier(Integer id) {
        this.id = id;
    }

    public Panier(Integer id, int flag, Collection<Commande> commandeCollection, User userId, Produit produitId) {
        this.id = id;
        this.flag = flag;
        this.commandeCollection = commandeCollection;
        this.userId = userId;
        this.produitId = produitId;
    }

     //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    
    public Collection<Commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<Commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Produit getProduitId() {
        return produitId;
    }

    public void setProduitId(Produit produitId) {
        this.produitId = produitId;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }
    
    

    
    
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Panier)) {
            return false;
        }
        Panier other = (Panier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       return "entity.Panier[ id=" + id + " ]";
       //return " "+produitId.getNomProduit()+" "+ produitId.getPrixVenteProduit()+" "+produitId.getQteDispoProduit();
    }
    
}
