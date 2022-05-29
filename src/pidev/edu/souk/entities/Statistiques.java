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
public class Statistiques  {

    
    private Integer id;
    private int nbproduitsVendu;
    private int nbvideoPost;
    private String grade;
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Statistiques() {
    }

    

    public Statistiques(Integer id, int nbproduitsVendu, int nbvideoPost, String grade) {
        this.id = id;
        this.nbproduitsVendu = nbproduitsVendu;
        this.nbvideoPost = nbvideoPost;
        this.grade = grade;
    }
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNbproduitsVendu() {
        return nbproduitsVendu;
    }

    public void setNbproduitsVendu(int nbproduitsVendu) {
        this.nbproduitsVendu = nbproduitsVendu;
    }

    public int getNbvideoPost() {
        return nbvideoPost;
    }

    public void setNbvideoPost(int nbvideoPost) {
        this.nbvideoPost = nbvideoPost;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statistiques)) {
            return false;
        }
        Statistiques other = (Statistiques) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Statistiques[ id=" + id + " ]";
    }
    
}
