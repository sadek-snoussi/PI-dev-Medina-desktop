/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.entities;

import java.sql.Date;



/**
 *
 * @author admin
 */
public class Commande {

    private Integer idCommande;
    private Date dateCommande;
    private String typeCommande;
    private String modePayementCommande;
    private String etatCommande;
    private double totalPrixCommande;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String pays;
    private String gouvernorat;
    private String ville;
    private String adresse;
    private String codepostale;
    private User userId;
    private Panier panierId;
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    

    public Commande() {
    }

    public Commande(Integer idCommande, Date dateCommande, String typeCommande, String modePayementCommande, String etatCommande, double totalPrixCommande, String nom, String prenom, String email, String tel, String pays, String gouvernorat, String ville, String adresse, String codepostale, User userId, Panier panierId) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.typeCommande = typeCommande;
        this.modePayementCommande = modePayementCommande;
        this.etatCommande = etatCommande;
        this.totalPrixCommande = totalPrixCommande;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.pays = pays;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.adresse = adresse;
        this.codepostale = codepostale;
        this.userId = userId;
        this.panierId = panierId;
    }
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------    


    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getTypeCommande() {
        return typeCommande;
    }

    public void setTypeCommande(String typeCommande) {
        this.typeCommande = typeCommande;
    }

    public String getModePayementCommande() {
        return modePayementCommande;
    }

    public void setModePayementCommande(String modePayementCommande) {
        this.modePayementCommande = modePayementCommande;
    }

    public String getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
    }

    public double getTotalPrixCommande() {
        return totalPrixCommande;
    }

    public void setTotalPrixCommande(double totalPrixCommande) {
        this.totalPrixCommande = totalPrixCommande;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodepostale() {
        return codepostale;
    }

    public void setCodepostale(String codepostale) {
        this.codepostale = codepostale;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Panier getPanierId() {
        return panierId;
    }

    public void setPanierId(Panier panierId) {
        this.panierId = panierId;
    }
    

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCommande != null ? idCommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.idCommande == null && other.idCommande != null) || (this.idCommande != null && !this.idCommande.equals(other.idCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Commande[ idCommande=" + idCommande + " ]";
    }
    
}
