package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import model.Node;
import util.staticFunctions.orderObjects;

public class ToolbarController {
    @FXML
    private ToggleButton BtnSubNode;
    @FXML
    private Button BtnOrder;
    @FXML
    private ColorPicker ColorSwitch;
    @FXML
    private ToggleButton BtnNode;
    @FXML
    private ToggleButton BtnConnection;
    @FXML
    private ToggleGroup tools;
    @FXML
    private ComboBox LineStyle;

    private MainController mainController;
    private Boolean connectionMode = false;

    /**
     * Initializes the Toolbar Controller and sets the handling for the Buttons.
     */
    @FXML
    public void initialize() {
        ColorSwitch.setValue(Color.BLACK);
        LineStyle.setValue("Primary Line Style");

        LineStyle.setOnAction(e -> {
            if(mainController.getCanvasController().getSelectedConnection() != null) {
                if(LineStyle.getValue().equals("Secondary Line Style")){
                    mainController.getCanvasController().getSelectedConnection().changeLineStyle(2);
                } else {
                    mainController.getCanvasController().getSelectedConnection().changeLineStyle(1);
                }
            }
        });

        BtnConnection.setOnAction(e -> {
            setConnectionMode();
        });

        ColorSwitch.setOnAction(e -> {
            if (mainController.getCanvasController().getSelectedNode() != null) {
                mainController.getCanvasController().getSelectedNode().changeColor(ColorSwitch.getValue());
            }
        });

        BtnOrder.setOnAction(e -> {
            orderObjects.orderNodes();
        });

        BtnSubNode.setOnAction(e -> {
            setConnectionMode();
        });

    }
    /**
     * Activates the Anchors and makes them visible to connect them together.
     */
    private void setConnectionMode(){
        if(connectionMode){
            for(Node node : mainController.getMap().getNodes()){
                node.deactivate();
                if (node.getActiveAnchor() != null){
                    node.getActiveAnchor().deactivate();
                }
                node.connectionMode(false);
                connectionMode = false;
            }
        }else {
            for(Node node : mainController.getMap().getNodes()){
                node.connectionMode(true);
                connectionMode = true;
            }
        }
    }

    /**
     * @param mainController injects the Maincontroller
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * @return boolean connection button is selected or not
     */
    public boolean btnConnectionSelected (){
        return BtnConnection.isSelected();
    }

    /**
     * @return boolean node button is selected or not
     */
    public boolean btnNodeSelected (){
        return BtnNode.isSelected();
    }

    /**
     * @return boolean subnode button is selected or not
     */
    public boolean btnSubNodeSelected(){
        return BtnSubNode.isSelected();
    }

    /**
     * @return Color
     */
    public Color getColor(){
        return ColorSwitch.getValue();
    }
}
