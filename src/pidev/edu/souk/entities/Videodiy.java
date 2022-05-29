/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.entities;

import java.util.Collection;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
//import pidev.edu.souk.controller.AfficheVideoPartenaireFXMLController;
import pidev.edu.souk.services.VideoDIYService;


/**
 *
 * @author admin
 */
public class Videodiy {
    
    public VideoDIYService videoService = new VideoDIYService();
    //public AfficheVideoPartenaireFXMLController videoController = new AfficheVideoPartenaireFXMLController();
    private Button modifier;

   
    private Integer idVideo;
    private String descriptionVideo;
    private String dureeVideo;
    private Date dateExpoVideo;
    private String commentaire;
    private String tags;
    private String titre;
    private String video;
    private Integer valid;
    private Double avgRating;
    private Collection<Rating> ratingCollection;
    private User idUser;
    private Collection<Commentaire> commentaireCollection;
    

    

    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Videodiy() {
        
       
    }


    
    public Videodiy(Integer idVideo, String descriptionVideo, String dureeVideo, Date dateExpoVideo, String commentaire, String tags, String titre, String video, Integer valid, Double avgRating, Collection<Rating> ratingCollection, User idUser, Collection<Commentaire> commentaireCollection) {
        this.idVideo = idVideo;
        this.descriptionVideo = descriptionVideo;
        this.dureeVideo = dureeVideo;
        this.dateExpoVideo = dateExpoVideo;
        this.commentaire = commentaire;
        this.tags = tags;
        this.titre = titre;
        this.video = video;
        this.valid = valid;
        this.avgRating = avgRating;
        this.ratingCollection = ratingCollection;
        this.idUser = idUser;
        this.commentaireCollection = commentaireCollection;
    }

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Button getModifier() {
        return modifier;
    }

    public void setModifier(Button modifier) {
        this.modifier = modifier;
    }
    
    
    
    

    public Integer getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(Integer idVideo) {
        this.idVideo = idVideo;
    }

    public String getDescriptionVideo() {
        return descriptionVideo;
    }

    public void setDescriptionVideo(String descriptionVideo) {
        this.descriptionVideo = descriptionVideo;
    }

    public String getDureeVideo() {
        return dureeVideo;
    }

    public void setDureeVideo(String dureeVideo) {
        this.dureeVideo = dureeVideo;
    }

    public Date getDateExpoVideo() {
        return dateExpoVideo;
    }

    public void setDateExpoVideo(Date dateExpoVideo) {
        this.dateExpoVideo = dateExpoVideo;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    
    public Collection<Rating> getRatingCollection() {
        return ratingCollection;
    }

    public void setRatingCollection(Collection<Rating> ratingCollection) {
        this.ratingCollection = ratingCollection;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Collection<Commentaire> getCommentaireCollection() {
        return commentaireCollection;
    }

    public void setCommentaireCollection(Collection<Commentaire> commentaireCollection) {
        this.commentaireCollection = commentaireCollection;
    }
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVideo != null ? idVideo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Videodiy)) {
            return false;
        }
        Videodiy other = (Videodiy) object;
        if ((this.idVideo == null && other.idVideo != null) || (this.idVideo != null && !this.idVideo.equals(other.idVideo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
          String mot1=   "Titre : "+titre ; 
        String mot2="Description : " + descriptionVideo;
        //String mot3="id:"+ idVideo;
       
        String Newligne=System.getProperty("line.separator"); 
        String resultat=mot1+Newligne+mot2; 
        
        return resultat;
    }
    
}
