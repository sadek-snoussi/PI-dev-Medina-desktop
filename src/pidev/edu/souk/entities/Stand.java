/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.entities;


/**
 *
 * @author admin
 */
public class Stand {

    
    private Integer numStand;
    private Double superficieStand;
    private String emplacementStand;
    private String couleur;
    private Boolean etat;
    private Double prix;
    private Event eventstand;
    private User userId;
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Stand() {
    }

    public Stand(Integer numStand, Double superficieStand, String emplacementStand, String couleur, Boolean etat, Double prix, Event eventstand, User userId) {
        this.numStand = numStand;
        this.superficieStand = superficieStand;
        this.emplacementStand = emplacementStand;
        this.couleur = couleur;
        this.etat = etat;
        this.prix = prix;
        this.eventstand = eventstand;
        this.userId = userId;
    }
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    

    public Integer getNumStand() {
        return numStand;
    }

    public void setNumStand(Integer numStand) {
        this.numStand = numStand;
    }

    public Double getSuperficieStand() {
        return superficieStand;
    }

    public void setSuperficieStand(Double superficieStand) {
        this.superficieStand = superficieStand;
    }

    public String getEmplacementStand() {
        return emplacementStand;
    }

    public void setEmplacementStand(String emplacementStand) {
        this.emplacementStand = emplacementStand;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Boolean getEtat() {
        return etat;
    }
    
    
    public String getEtat1(){
    if(this.etat==true){
    return "Reservé" ;}
    else {
    
    
    return "non Reservé" ;
            }
        
        
    
    
    
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Event getEventstand() {
        return eventstand;
    }

    public void setEventstand(Event eventstand) {
        this.eventstand = eventstand;
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
        hash += (numStand != null ? numStand.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stand)) {
            return false;
        }
        Stand other = (Stand) object;
        if ((this.numStand == null && other.numStand != null) || (this.numStand != null && !this.numStand.equals(other.numStand))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Stand[ numStand=" + numStand + " ]";
    }
    
}
