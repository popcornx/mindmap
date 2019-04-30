package controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenubarController {
    @FXML
    private MenuItem MIsave;
    @FXML
    private MenuItem MIload;
    private MainController mainController;
    @FXML
    public void initialize(){
         MIsave.setOnAction(e -> mainController.save());
         MIload.setOnAction(e -> mainController.load());
    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
}
