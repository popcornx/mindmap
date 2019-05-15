package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.XMLConverter;

import javax.xml.bind.UnmarshalException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MenubarController {
    @FXML
    private MenuItem MIsave;
    @FXML
    private MenuItem MIload;
    private MainController mainController;
    @FXML
    public void initialize(){
         MIsave.setOnAction(e -> {
             FileChooser fc = new FileChooser();
             fc.setTitle("Save Mindmap");
             fc.setInitialFileName("map.xml");
             File file = fc.showSaveDialog(new Stage());

             if (file != null) {
                 try (FileOutputStream stream = new FileOutputStream(file)) {
                    XMLConverter.saveMap(mainController.getMap(), stream);
                 } catch (FileNotFoundException ex) {
                     showAlert("Saving Error", "An error occurred when trying to save the Mindmap.");
                 } catch (Exception exe) {
                     exe.printStackTrace();
                 }
             }
         });

         MIload.setOnAction(e -> {
             FileChooser fc = new FileChooser();
             fc.setTitle("Open Mindmap");
             FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml");
             fc.getExtensionFilters().add(filter);
             File file = fc.showOpenDialog(new Stage());

             if(file != null) {
                 try {
                     mainController.setMap(XMLConverter.loadMap(file));
                     mainController.drawMap();
                 }
                 catch (UnmarshalException ex) {
                     showAlert("Loading Error",
                             "An error occurred when trying to load the Mindmap.\n" +
                                     "This file is either corrupted or not a Mindmap.");
                 }
                 catch (Exception exe) {
                     exe.printStackTrace();
                 }
             }
         });
    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void showAlert(String type, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR, type, ButtonType.OK);
        alert.setTitle("Error");
        alert.setResizable(true);
        alert.setContentText(text);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
