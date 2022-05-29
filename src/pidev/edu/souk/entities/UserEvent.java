/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.entities;

import java.util.Date;


/**
 *
 * @author admin
 */
public class UserEvent  {

    
    private Integer idInscri;
    private java.sql.Date dateInscri;
    private String quantite;
    private String nom;
    private String prenom;
    private String adressemail;
    private Event eventId;
    private User userId;
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------

    
    public UserEvent() {
    }

  
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    

    public Integer getIdInscri() {
        return idInscri;
    }

    public void setIdInscri(Integer idInscri) {
        this.idInscri = idInscri;
    }

    public Date getDateInscri() {
        return dateInscri;
    }

    public void setDateInscri(java.sql.Date dateInscri) {
        this.dateInscri = dateInscri;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
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

    public String getAdressemail() {
        return adressemail;
    }

    public void setAdressemail(String adressemail) {
        this.adressemail = adressemail;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

   
   
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInscri != null ? idInscri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEvent)) {
            return false;
        }
        UserEvent other = (UserEvent) object;
        if ((this.idInscri == null && other.idInscri != null) || (this.idInscri != null && !this.idInscri.equals(other.idInscri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserEvent[ idInscri=" + idInscri + " ]";
    }
    
}
