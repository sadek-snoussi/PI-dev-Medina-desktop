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
public class Commentaire {



    private Integer idcommentaire;
    private String contenuCommentaire;
    private Date dateCommentaire;
    private User idUser;
    private Videodiy idVideo;

    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------    
    
    
    public Commentaire() {
    }

    public Commentaire(Integer idcommentaire, String contenuCommentaire, Date dateCommentaire, User idUser, Videodiy idVideo) {
        this.idcommentaire = idcommentaire;
        this.contenuCommentaire = contenuCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.idUser = idUser;
        this.idVideo = idVideo;
    }

    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------    
    
    
   
    public Integer getIdcommentaire() {
        return idcommentaire;
    }

    public void setIdcommentaire(Integer idcommentaire) {
        this.idcommentaire = idcommentaire;
    }

    public String getContenuCommentaire() {
        return contenuCommentaire;
    }

    public void setContenuCommentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Videodiy getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(Videodiy idVideo) {
        this.idVideo = idVideo;
    }
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcommentaire != null ? idcommentaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.idcommentaire == null && other.idcommentaire != null) || (this.idcommentaire != null && !this.idcommentaire.equals(other.idcommentaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         String mot1= ""+idUser.getNomUser() ; 
        String mot2="" + contenuCommentaire;
        //String mot3="id:"+ idVideo;
       
        String Newligne=System.getProperty("line.separator"); 
        String resultat=mot1+Newligne+mot2; 
        
        return resultat;
    }
    
}
