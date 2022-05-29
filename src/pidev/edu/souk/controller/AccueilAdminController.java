/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import pidev.edu.souk.services.GradeService;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class AccueilAdminController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private LineChart<?, ?> LineChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GradeService apr = new GradeService();
        //ProprietaireEcole pe=new ProprietaireEcole();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Silver " + apr.CountGradeSilver() + " %", apr.CountGradeSilver()),
                new PieChart.Data("Gold " + apr.CountGradeGold() + " %", apr.CountGradeGold()),
                new PieChart.Data("platinuim " + apr.CountGradePlatinuim() + " %", apr.CountGradePlatinuim())
        );
        piechart.setData(pieChartData);

        //*****************LineChaart************************
        GradeService gs1=new GradeService();
        gs1.calculernbreCommandesPerMonth();
        gs1.calculernbreCommandesPerCategoryProduct();
        //System.out.println("i m heereeee"+GradeService.totaleACat);
       
        XYChart.Series series = new XYChart.Series(); 
        XYChart.Series seriesCatEpicerie = new XYChart.Series(); 
        XYChart.Series seriesCatPoterie = new XYChart.Series(); 
        XYChart.Series seriesCatPatisserie = new XYChart.Series(); 
        XYChart.Series seriesCatBijouterie = new XYChart.Series(); 
        XYChart.Series seriesCatTextile = new XYChart.Series(); 

        series.setName("Nombre Total de commandes par mois");
        series.getData()
                .add(new XYChart.Data("Janvier", GradeService.totaleJ));
        series.getData()
                .add(new XYChart.Data("Fevrier", GradeService.totaleF));
        series.getData()
                .add(new XYChart.Data("Mars", GradeService.totaleM));
        series.getData()
                .add(new XYChart.Data("Avril", GradeService.totaleA));
        series.getData()
                .add(new XYChart.Data("Mai", GradeService.totaleMai));
        series.getData()
                .add(new XYChart.Data("Juin", GradeService.totaleJuin));
        series.getData()
                .add(new XYChart.Data("Juillet", GradeService.totaleJuillet));
        series.getData()
                .add(new XYChart.Data("Aout", GradeService.totaleAout));

        
        
         seriesCatEpicerie.setName("Nombre Total de commandes des Produits de Catégorie Epicerie");
        seriesCatEpicerie.getData()
                .add(new XYChart.Data("Janvier", GradeService.totaleCatEpicerieJ));
        seriesCatEpicerie.getData()
                .add(new XYChart.Data("Fevrier", GradeService.totaleCatEpicerieF));
        seriesCatEpicerie.getData()
                .add(new XYChart.Data("Mars", GradeService.totaleCatEpicerieM));
        seriesCatEpicerie.getData()
                .add(new XYChart.Data("Avril", GradeService.totaleCatEpicerieA));
        seriesCatEpicerie.getData()
                .add(new XYChart.Data("Mai", GradeService.totaleCatEpicerieMai));
        seriesCatEpicerie.getData()
                .add(new XYChart.Data("Juin", GradeService.totaleCatEpicerieJuin));
        seriesCatEpicerie.getData()
                .add(new XYChart.Data("Juillet", GradeService.totaleCatEpicerieJuillet));
        seriesCatEpicerie.getData()
                .add(new XYChart.Data("Aout", GradeService.totaleCatEpicerieAout));
        
        seriesCatPoterie.setName("Nombre Total de commandes des Produits de Catégorie Poterie");
        seriesCatPoterie.getData()
                .add(new XYChart.Data("Janvier", GradeService.totalePoterieJ));
        seriesCatPoterie.getData()
                .add(new XYChart.Data("Fevrier", GradeService.totalePoterieF));
        seriesCatPoterie.getData()
                .add(new XYChart.Data("Mars", GradeService.totalePoterieM));
        seriesCatPoterie.getData()
                .add(new XYChart.Data("Avril", GradeService.totalePoterieA));
        seriesCatPoterie.getData()
                .add(new XYChart.Data("Mai", GradeService.totalePoterieMai));
        seriesCatPoterie.getData()
                .add(new XYChart.Data("Juin", GradeService.totalePoterieJuin));
        seriesCatPoterie.getData()
                .add(new XYChart.Data("Juillet", GradeService.totalePoterieJuillet));
        seriesCatPoterie.getData()
                .add(new XYChart.Data("Aout", GradeService.totalePoterieAout));
        
          seriesCatPatisserie.setName("Nombre Total de commandes des Produits de Catégorie Patisserie");
        seriesCatPatisserie.getData()
                .add(new XYChart.Data("Janvier", GradeService.totaleCatPatisserieJ));
        seriesCatPatisserie.getData()
                .add(new XYChart.Data("Fevrier", GradeService.totaleCatPatisserieF));
        seriesCatPatisserie.getData()
                .add(new XYChart.Data("Mars", GradeService.totaleCatPatisserieM));
        seriesCatPatisserie.getData()
                .add(new XYChart.Data("Avril", GradeService.totaleCatPatisserieA));
        seriesCatPatisserie.getData()
                .add(new XYChart.Data("Mai", GradeService.totaleCatPatisserieMai));
        seriesCatPatisserie.getData()
                .add(new XYChart.Data("Juin", GradeService.totaleCatPatisserieJuin));
        seriesCatPatisserie.getData()
                .add(new XYChart.Data("Juillet", GradeService.totaleCatPatisserieJuillet));
        seriesCatPatisserie.getData()
                .add(new XYChart.Data("Aout", GradeService.totaleCatPatisserieAout));
        
          seriesCatBijouterie.setName("Nombre Total de commandes des Produits de Catégorie Bijouterie");
        seriesCatBijouterie.getData()
                .add(new XYChart.Data("Janvier", GradeService.totaleCatBijouterieJ));
        seriesCatBijouterie.getData()
                .add(new XYChart.Data("Fevrier", GradeService.totaleCatBijouterieF));
        seriesCatBijouterie.getData()
                .add(new XYChart.Data("Mars", GradeService.totaleCatBijouterieM));
        seriesCatBijouterie.getData()
                .add(new XYChart.Data("Avril", GradeService.totaleCatBijouterieA));
        seriesCatBijouterie.getData()
                .add(new XYChart.Data("Mai", GradeService.totaleCatBijouterieMai));
        seriesCatBijouterie.getData()
                .add(new XYChart.Data("Juin", GradeService.totaleCatBijouterieJuin));
        seriesCatBijouterie.getData()
                .add(new XYChart.Data("Juillet", GradeService.totaleCatBijouterieJuillet));
        seriesCatBijouterie.getData()
                .add(new XYChart.Data("Aout", GradeService.totaleCatBijouterieAout));
        
         seriesCatTextile.setName("Nombre Total de commandes des Produits de Catégorie Textile");
        seriesCatTextile.getData()
                .add(new XYChart.Data("Janvier", GradeService.totaleCatBijouterieJ));
        seriesCatTextile.getData()
                .add(new XYChart.Data("Fevrier", GradeService.totaleCatBijouterieF));
        seriesCatTextile.getData()
                .add(new XYChart.Data("Mars", GradeService.totaleCatBijouterieM));
        seriesCatTextile.getData()
                .add(new XYChart.Data("Avril", GradeService.totaleCatBijouterieA));
        seriesCatTextile.getData()
                .add(new XYChart.Data("Mai", GradeService.totaleCatBijouterieMai));
        seriesCatTextile.getData()
                .add(new XYChart.Data("Juin", GradeService.totaleCatBijouterieJuin));
        seriesCatTextile.getData()
                .add(new XYChart.Data("Juillet", GradeService.totaleCatBijouterieJuillet));
        seriesCatTextile.getData()
                .add(new XYChart.Data("Aout", GradeService.totaleCatBijouterieAout));

        LineChart.getData()
                .addAll(series,seriesCatEpicerie,seriesCatPoterie,seriesCatPatisserie,seriesCatBijouterie,seriesCatTextile);

    }

}
