package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Node;
import util.saveFunctions.XMLConverter;

import javax.xml.bind.UnmarshalException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MenubarController {
    @FXML
    private MenuItem MIsave;
    @FXML
    private MenuItem MIload;
    @FXML
    private Slider sliderScale;
    private Double scale = 1.0;
    private MainController mainController;

    /**
     * Initializes the Menubar Controller and sets the Button handling for the Menubar
     */
    @FXML
    public void initialize() {
        MIsave.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Save Mindmap");
            fc.setInitialFileName("map.xml");
            File file = fc.showSaveDialog(new Stage());
            if (file != null) {
                try (FileOutputStream stream = new FileOutputStream(file)) {
                    XMLConverter.saveMap(mainController.getMap(), stream, scale);
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

            if (file != null) {
                try {
                    mainController.setMap(XMLConverter.loadMap(file));
                    mainController.getCanvasController().drawMap();
                    sliderScale.setValue(XMLConverter.scale);
                } catch (UnmarshalException ex) {
                    showAlert("Loading Error",
                            "An error occurred when trying to load the Mindmap.\n" +
                                    "This file is either corrupted or not a Mindmap.");
                } catch (Exception exe) {
                    exe.printStackTrace();
                }
            }
        });

        sliderScale.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                for (Node node : mainController.getMap().getNodes()){
                   node.setScale(sliderScale.getValue());
                   scale = sliderScale.getValue();
                }
            }
        });
    }
    /**
     * @return scale
     */
    Double getScale() {
        return scale;
    }

    /**
     * @param mainController inject mainController
     */
    void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    /**
     * @param type String
     * @param text Text
     * generates a Alert when a error occurs while loading or saving a file
     */
    void showAlert(String type, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR, type, ButtonType.OK);
        alert.setTitle("Error");
        alert.setResizable(true);
        alert.setContentText(text);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
