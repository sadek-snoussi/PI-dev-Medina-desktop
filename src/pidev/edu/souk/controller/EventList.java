/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import javafx.scene.control.ListCell;
import pidev.edu.souk.entities.Event;

/**
 *
 * @author ASUS I7
 */
public class EventList extends ListCell<Event>{
  

    
    public void updateItem(Event e, boolean empty) {

        super.updateItem(e, empty);

        if (e != null) {

          //  VideoCellFXMLController data = new VideoCellFXMLController();
          //  data.setInfo(v);
          //  setGraphic(data.getVideoCell());
            CelluleFXMLController data = new CelluleFXMLController();
            data.affiche(e);
            
            setGraphic(data.getListEvent());
            
            
            
        }
    }
}

       

