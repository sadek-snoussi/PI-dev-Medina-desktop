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
public class Categorie  {

   

    private Integer idCategorie;
    private String nomCategorie;
    private String typeCategorie;
    private Collection<Produit> produitCollection;
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    

    public Categorie() {
    }
    
    
  

    public Categorie(String nomCategorie, String typeCategorie) {
        this.nomCategorie = nomCategorie;
        this.typeCategorie = typeCategorie;
    }

    

    public Categorie(Integer idCategorie, String nomCategorie, String typeCategorie, Collection<Produit> produitCollection) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
        this.typeCategorie = typeCategorie;
        this.produitCollection = produitCollection;
    }

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------    


    

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getTypeCategorie() {
        return typeCategorie;
    }

    public void setTypeCategorie(String typeCategorie) {
        this.typeCategorie = typeCategorie;
    }

    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategorie != null ? idCategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.idCategorie == null && other.idCategorie != null) || (this.idCategorie != null && !this.idCategorie.equals(other.idCategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Categorie[ idCategorie=" + idCategorie + " ]";
    }
    
}
