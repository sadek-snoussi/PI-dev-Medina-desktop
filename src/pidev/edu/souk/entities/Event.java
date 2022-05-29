/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.entities;

import java.awt.BorderLayout;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Event {

    private Integer idEvent;
    private String nomEvent;
    private String urlafficheevent;
    private Date dateEvent;
    private Integer nbreStand;
    private String objectifEvent;
    private String lieux;
    private Integer nbrePlace;
    private String descriptionevent;
    private String typeEvent;
    private Collection<Stand> standCollection;
    private Collection<UserEvent> userEventCollection;

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------    
    public Event() {
    }

    public Event(Integer idEvent, String nomEvent, String lieux, String descriptionevent) {
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.lieux = lieux;
        this.descriptionevent = descriptionevent;
    }

  
 




  

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------   
    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getUrlafficheevent() {
        return urlafficheevent;
    }

    public void setUrlafficheevent(String urlafficheevent) {
        this.urlafficheevent = urlafficheevent;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Integer getNbreStand() {
        return nbreStand;
    }

    public void setNbreStand(Integer nbreStand) {
        this.nbreStand = nbreStand;
    }

    public String getObjectifEvent() {
        return objectifEvent;
    }

    public void setObjectifEvent(String objectifEvent) {
        this.objectifEvent = objectifEvent;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public Integer getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(Integer nbrePlace) {
        this.nbrePlace = nbrePlace;
    }

    public String getDescriptionevent() {
        return descriptionevent;
    }

    public void setDescriptionevent(String descriptionevent) {
        this.descriptionevent = descriptionevent;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public Collection<Stand> getStandCollection() {
        return standCollection;
    }

    public void setStandCollection(Collection<Stand> standCollection) {
        this.standCollection = standCollection;
    }

    public Collection<UserEvent> getUserEventCollection() {
        return userEventCollection;
    }

    public void setUserEventCollection(Collection<UserEvent> userEventCollection) {
        this.userEventCollection = userEventCollection;
    }

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvent != null ? idEvent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.idEvent == null && other.idEvent != null) || (this.idEvent != null && !this.idEvent.equals(other.idEvent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String mot1 = "";
        String mot2 = "";
        String mot3 = " ";

        String Newligne = System.getProperty("line.separator");
        String resultat = mot1 +Newligne+descriptionevent+ Newligne + mot2+Newligne+lieux+Newligne+mot3+Newligne+ dateEvent;
        return resultat;

    }

}
