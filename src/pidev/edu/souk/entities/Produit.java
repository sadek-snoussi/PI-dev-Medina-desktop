/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.entities;


import java.util.Collection;
import java.util.Date;


/**
 *
 * @author admin
 */

public class Produit {

   
    private Integer idProduit;
    private String nomProduit;
    private String tailleProduit;
    private String matiereProduit;
    private double prixBaseProduit;
    private double prixVenteProduit;
    private Date dateExpoProduit;
    private Date dateExpirationProduit;
    private String urlImgProduit;
    private String referenceProd;
    private Double promotionProduit;
    private Integer qteDispoProduit;
    private Integer validiteProduit;
    private Integer qteVenduProduit;
    private Categorie idCategorie;
    private User idUser;
    private Collection<Panier> panierCollection;
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Produit() {
    }

    public Produit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Produit(Integer idProduit, String nomProduit, String tailleProduit, String matiereProduit, double prixBaseProduit, double prixVenteProduit, Date dateExpoProduit, Date dateExpirationProduit, String urlImgProduit, String referenceProd, Double promotionProduit, Integer qteDispoProduit, Integer validiteProduit, Integer qteVenduProduit, Categorie idCategorie, User idUser, Collection<Panier> panierCollection) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.tailleProduit = tailleProduit;
        this.matiereProduit = matiereProduit;
        this.prixBaseProduit = prixBaseProduit;
        this.prixVenteProduit = prixVenteProduit;
        this.dateExpoProduit = dateExpoProduit;
        this.dateExpirationProduit = dateExpirationProduit;
        this.urlImgProduit = urlImgProduit;
        this.referenceProd = referenceProd;
        this.promotionProduit = promotionProduit;
        this.qteDispoProduit = qteDispoProduit;
        this.validiteProduit = validiteProduit;
        this.qteVenduProduit = qteVenduProduit;
        this.idCategorie = idCategorie;
        this.idUser = idUser;
        this.panierCollection = panierCollection;
    }

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getTailleProduit() {
        return tailleProduit;
    }

    public void setTailleProduit(String tailleProduit) {
        this.tailleProduit = tailleProduit;
    }

    public String getMatiereProduit() {
        return matiereProduit;
    }

    public void setMatiereProduit(String matiereProduit) {
        this.matiereProduit = matiereProduit;
    }

    public double getPrixBaseProduit() {
        return prixBaseProduit;
    }

    public void setPrixBaseProduit(double prixBaseProduit) {
        this.prixBaseProduit = prixBaseProduit;
    }

    public double getPrixVenteProduit() {
        return prixVenteProduit;
    }

    public void setPrixVenteProduit(double prixVenteProduit) {
        this.prixVenteProduit = prixVenteProduit;
    }

    public Date getDateExpoProduit() {
        return dateExpoProduit;
    }

    public void setDateExpoProduit(Date dateExpoProduit) {
        this.dateExpoProduit = dateExpoProduit;
    }

    public Date getDateExpirationProduit() {
        return dateExpirationProduit;
    }

    public void setDateExpirationProduit(Date dateExpirationProduit) {
        this.dateExpirationProduit = dateExpirationProduit;
    }

    public String getUrlImgProduit() {
        return urlImgProduit;
    }

    public void setUrlImgProduit(String urlImgProduit) {
        this.urlImgProduit = urlImgProduit;
    }

    public String getReferenceProd() {
        return referenceProd;
    }

    public void setReferenceProd(String referenceProd) {
        this.referenceProd = referenceProd;
    }

    public Double getPromotionProduit() {
        return promotionProduit;
    }

    public void setPromotionProduit(Double promotionProduit) {
        this.promotionProduit = promotionProduit;
    }

    public Integer getQteDispoProduit() {
        return qteDispoProduit;
    }

    public void setQteDispoProduit(Integer qteDispoProduit) {
        this.qteDispoProduit = qteDispoProduit;
    }

    public Integer getValiditeProduit() {
        return validiteProduit;
    }

    public void setValiditeProduit(Integer validiteProduit) {
        this.validiteProduit = validiteProduit;
    }

    public Integer getQteVenduProduit() {
        return qteVenduProduit;
    }

    public void setQteVenduProduit(Integer qteVenduProduit) {
        this.qteVenduProduit = qteVenduProduit;
    }

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    
    public Collection<Panier> getPanierCollection() {
        return panierCollection;
    }

    public void setPanierCollection(Collection<Panier> panierCollection) {
        this.panierCollection = panierCollection;
    }
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduit != null ? idProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProduit == null && other.idProduit != null) || (this.idProduit != null && !this.idProduit.equals(other.idProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", tailleProduit=" + tailleProduit + ", matiereProduit=" + matiereProduit + ", prixBaseProduit=" + prixBaseProduit + ", prixVenteProduit=" + prixVenteProduit + ", dateExpoProduit=" + dateExpoProduit + ", dateExpirationProduit=" + dateExpirationProduit + ", urlImgProduit=" + urlImgProduit + ", referenceProd=" + referenceProd + ", promotionProduit=" + promotionProduit + ", qteDispoProduit=" + qteDispoProduit + ", validiteProduit=" + validiteProduit + ", qteVenduProduit=" + qteVenduProduit + ", idCategorie=" + idCategorie + ", idUser=" + idUser + ", panierCollection=" + panierCollection + '}';
    }

   
    
}
