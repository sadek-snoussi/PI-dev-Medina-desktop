/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import pidev.edu.souk.controller.AffichageVideoPartenaireController;
import pidev.edu.souk.controller.CelluleVideoController;
import pidev.edu.souk.controller.CelluleVideoPartenaireController;
import pidev.edu.souk.controller.VideoCellFXMLController;
import pidev.edu.souk.entities.Videodiy;

/**
 *
 * @author hp
 */
public class videoCellListViewPartenaire extends ListCell<Videodiy> {

    @Override
    public void updateItem(Videodiy v, boolean empty) {

        super.updateItem(v, empty);
        
        if (empty || v == null) {
         setText(null);
         setGraphic(null);
     } else {
            CelluleVideoPartenaireController data = new CelluleVideoPartenaireController();
            
            data.setInfo(v);
            
            setGraphic(data.getCellList());
     }

//        if (v != null) {
//
//          //  VideoCellFXMLController data = new VideoCellFXMLController();
//          //  data.setInfo(v);
//          //  setGraphic(data.getVideoCell());
//            CelluleVideoPartenaireController data = new CelluleVideoPartenaireController();
//            
//            data.setInfo(v);
//            
//            setGraphic(data.getCellList());
//            
//            
//            
        
    }

}
