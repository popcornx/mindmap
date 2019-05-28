package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import model.Node;
import util.saveFunctions.XMLConverter;
import javax.xml.bind.UnmarshalException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MenubarController {
    @FXML
    private MenuItem MInew;
    @FXML
    private MenuItem MIsave;
    @FXML
    private MenuItem MIload;
    @FXML
    private Slider sliderScale;
    @FXML
    private MenuItem deleteButton;
    @FXML
    private MenuItem helpButton;
    private Double scale = 1.0;
    private MainController mainController;
    private String currentDirectory = new File("").getAbsolutePath();
    final String helpFile = currentDirectory+"/src/view/textFiles/help.txt";

    /**
     * Initializes the Menubar Controller and sets the Button handling for the Menubar
     */
    @FXML
    public void initialize() {
        MInew.setOnAction(e -> {
            mainController.getCanvasController().drawNew();
        });
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
                for (Node node : mainController.getMap().getNodes()) {
                    node.setScale(sliderScale.getValue());
                    scale = sliderScale.getValue();
                }
            }
        });
        deleteButton.setOnAction(e -> {
            if (mainController.getCanvasController().getSelectedConnection() != null) {
                mainController.deleteConnection();
            }
            if (mainController.getCanvasController().getSelectedNode() != null) {
                mainController.deleteNode();
            }
        });

        helpButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hilfe!");
            TextArea area = new TextArea(readFile(helpFile));
            area.setWrapText(true);
            area.setEditable(false);
            alert.getDialogPane().setContent(area);
            alert.setResizable(true);
            alert.getDialogPane().setPrefSize(500, 500);
            alert.setHeaderText("Hilfe!");
            alert.showAndWait();
        });

    }


    /**
     * @return scale
     */
    Double getScale() {
        return scale;
    }

    /**
     * @param scale double scale
     */
    public void setScale(double scale) {
        this.scale = scale;
        sliderScale.setValue(scale);
    }

    /**
     * @param mainController inject mainController
     */
    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * @param type String
     * @param text Text
     *             generates a Alert when a error occurs while loading or saving a file
     */
    void showAlert(String type, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR, type, ButtonType.OK);
        alert.setTitle("Error");
        alert.setResizable(true);
        alert.setContentText(text);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }


    /**
     * @param filename File which needs to be read
     * @return returns the File content as a String
     */
    private String readFile(String filename) {
        File f = new File(filename);
        try {
            byte[] bytes = Files.readAllBytes(f.toPath());
            return new String(bytes,"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}