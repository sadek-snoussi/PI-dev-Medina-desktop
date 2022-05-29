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
public class Rating {

    
    private Integer ratingId;
    private Double rating;
    private Videodiy videoId;
    private User userId;
    
    
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    
    
    public Rating() {
    }

    public Rating(Integer ratingId, Double rating, Videodiy videoId, User userId) {
        this.ratingId = ratingId;
        this.rating = rating;
        this.videoId = videoId;
        this.userId = userId;
    }

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Videodiy getVideoId() {
        return videoId;
    }

    public void setVideoId(Videodiy videoId) {
        this.videoId = videoId;
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
        hash += (ratingId != null ? ratingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.ratingId == null && other.ratingId != null) || (this.ratingId != null && !this.ratingId.equals(other.ratingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rating[ ratingId=" + ratingId + " ]";
    }
    
}
