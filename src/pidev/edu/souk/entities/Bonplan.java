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

public class Bonplan {

  
    private Integer idBonplan;
    private String nombonplan;
    private String adresseBonplan;
    private String typeBonplan;
    private Integer avisBonplan;
    private String imgBonplan;
    private Double longitude;
    private Double latitude;
    private Double AvgRating;

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }
    
    private User id_user;
    
    

    public Double getAvgRating() {
        return AvgRating;
    }

    public void setAvgRating(Double AvgRating) {
        this.AvgRating = AvgRating;
    }
   
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    public Bonplan() {
    }

    public Bonplan(Integer idBonplan, String nombonplan, String adresseBonplan, String typeBonplan, Integer avisBonplan, String imgBonplan, Double longitude, Double latitude) {
        this.idBonplan = idBonplan;
        this.nombonplan = nombonplan;
        this.adresseBonplan = adresseBonplan;
        this.typeBonplan = typeBonplan;
        this.avisBonplan = avisBonplan;
        this.imgBonplan = imgBonplan;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    

    public Integer getIdBonplan() {
        return idBonplan;
    }

    public void setIdBonplan(Integer idBonplan) {
        this.idBonplan = idBonplan;
    }

    public String getNombonplan() {
        return nombonplan;
    }

    public void setNombonplan(String nombonplan) {
        this.nombonplan = nombonplan;
    }

    public String getAdresseBonplan() {
        return adresseBonplan;
    }

    public void setAdresseBonplan(String adresseBonplan) {
        this.adresseBonplan = adresseBonplan;
    }

    public String getTypeBonplan() {
        return typeBonplan;
    }

    public void setTypeBonplan(String typeBonplan) {
        this.typeBonplan = typeBonplan;
    }

    public Integer getAvisBonplan() {
        return avisBonplan;
    }

    public void setAvisBonplan(Integer avisBonplan) {
        this.avisBonplan = avisBonplan;
    }

    public String getImgBonplan() {
        return imgBonplan;
    }

    public void setImgBonplan(String imgBonplan) {
        this.imgBonplan = imgBonplan;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBonplan != null ? idBonplan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bonplan)) {
            return false;
        }
        Bonplan other = (Bonplan) object;
        if ((this.idBonplan == null && other.idBonplan != null) || (this.idBonplan != null && !this.idBonplan.equals(other.idBonplan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bonplan{" + "idBonplan=" + idBonplan + ", nombonplan=" + nombonplan + ", adresseBonplan=" + adresseBonplan + ", typeBonplan=" + typeBonplan + ", avisBonplan=" + avisBonplan + ", imgBonplan=" + imgBonplan + ", longitude=" + longitude + ", latitude=" + latitude + ", AvgRating=" + AvgRating + ", id_user=" + id_user + '}';
    }


    
}
