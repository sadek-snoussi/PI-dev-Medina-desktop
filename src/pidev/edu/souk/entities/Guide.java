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
public class Guide {

   
    private Integer idGuide;
    private String nomGuide;
    private String prenomGuide;
    private String telGuide;
    private String villeGuide;
    private String mailguide;
    private String imgGuide;
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Guide() {
    }

    public Guide(Integer idGuide, String nomGuide, String prenomGuide, String telGuide, String villeGuide, String mailguide, String imgGuide) {
        this.idGuide = idGuide;
        this.nomGuide = nomGuide;
        this.prenomGuide = prenomGuide;
        this.telGuide = telGuide;
        this.villeGuide = villeGuide;
        this.mailguide = mailguide;
        this.imgGuide = imgGuide;
    }

    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Integer getIdGuide() {
        return idGuide;
    }

    public void setIdGuide(Integer idGuide) {
        this.idGuide = idGuide;
    }

    public String getNomGuide() {
        return nomGuide;
    }

    public void setNomGuide(String nomGuide) {
        this.nomGuide = nomGuide;
    }

    public String getPrenomGuide() {
        return prenomGuide;
    }

    public void setPrenomGuide(String prenomGuide) {
        this.prenomGuide = prenomGuide;
    }

    public String getTelGuide() {
        return telGuide;
    }

    public void setTelGuide(String telGuide) {
        this.telGuide = telGuide;
    }

    public String getVilleGuide() {
        return villeGuide;
    }

    public void setVilleGuide(String villeGuide) {
        this.villeGuide = villeGuide;
    }

    public String getMailguide() {
        return mailguide;
    }

    public void setMailguide(String mailguide) {
        this.mailguide = mailguide;
    }

    public String getImgGuide() {
        return imgGuide;
    }

    public void setImgGuide(String imgGuide) {
        this.imgGuide = imgGuide;
    }
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGuide != null ? idGuide.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guide)) {
            return false;
        }
        Guide other = (Guide) object;
        if ((this.idGuide == null && other.idGuide != null) || (this.idGuide != null && !this.idGuide.equals(other.idGuide))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Guide[ idGuide=" + idGuide + " ]";
    }
    
}
