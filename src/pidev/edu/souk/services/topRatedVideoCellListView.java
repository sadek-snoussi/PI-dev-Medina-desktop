/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import javafx.scene.control.ListCell;
import pidev.edu.souk.controller.CelluleVideoController;
import pidev.edu.souk.controller.TopRatedVideoCellController;
import pidev.edu.souk.entities.Videodiy;

/**
 *
 * @author hp
 */
public class topRatedVideoCellListView extends ListCell<Videodiy> {
    
     @Override
    public void updateItem(Videodiy v, boolean empty) {

        super.updateItem(v, empty);

        if (v != null) {

          //  VideoCellFXMLController data = new VideoCellFXMLController();
          //  data.setInfo(v);
          //  setGraphic(data.getVideoCell());
            TopRatedVideoCellController data = new TopRatedVideoCellController();
            data.setInfo(v);
            setGraphic(data.getBorderpaneVideo());
        }
    }
    
}
