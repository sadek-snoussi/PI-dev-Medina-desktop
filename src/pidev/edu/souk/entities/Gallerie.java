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
public class Gallerie  {

   
    private Integer idGallerie;
    private String typeGallerie;
    private String description;
    private String imgGallerie;
    private String lieuGallerie;
    private String titreGallerie;
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Gallerie() {
    }

    public Gallerie(Integer idGallerie, String typeGallerie, String description, String imgGallerie, String lieuGallerie, String titreGallerie) {
        this.idGallerie = idGallerie;
        this.typeGallerie = typeGallerie;
        this.description = description;
        this.imgGallerie = imgGallerie;
        this.lieuGallerie = lieuGallerie;
        this.titreGallerie = titreGallerie;
    }

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    

    public Integer getIdGallerie() {
        return idGallerie;
    }

    public void setIdGallerie(Integer idGallerie) {
        this.idGallerie = idGallerie;
    }

    public String getTypeGallerie() {
        return typeGallerie;
    }

    public void setTypeGallerie(String typeGallerie) {
        this.typeGallerie = typeGallerie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgGallerie() {
        return imgGallerie;
    }

    public void setImgGallerie(String imgGallerie) {
        this.imgGallerie = imgGallerie;
    }

    public String getLieuGallerie() {
        return lieuGallerie;
    }

    public void setLieuGallerie(String lieuGallerie) {
        this.lieuGallerie = lieuGallerie;
    }

    public String getTitreGallerie() {
        return titreGallerie;
    }

    public void setTitreGallerie(String titreGallerie) {
        this.titreGallerie = titreGallerie;
    }
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGallerie != null ? idGallerie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gallerie)) {
            return false;
        }
        Gallerie other = (Gallerie) object;
        if ((this.idGallerie == null && other.idGallerie != null) || (this.idGallerie != null && !this.idGallerie.equals(other.idGallerie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Gallerie[ idGallerie=" + idGallerie + " ]";
    }
    
}
