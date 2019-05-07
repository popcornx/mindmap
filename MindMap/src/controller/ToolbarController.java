package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import model.Node;

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

    @FXML
    public void initialize() {
        //DefaultValue
        ColorSwitch.setValue(Color.BLACK);
        LineStyle.setValue("Primary Line Style");

        LineStyle.setOnAction(e -> {
            if(mainController.getSelectedConnection() != null) {
                mainController.getSelectedConnection().changeStroke((String) LineStyle.getValue());
            }
        });

        BtnConnection.setOnAction(e -> {
            setConnectionMode();
        });

        ColorSwitch.setOnAction(e -> {
            if (mainController.getSelectedNode() != null) {
                mainController.getSelectedNode().changeColor(ColorSwitch.getValue());
            }
        });

        //To be Implemented!!
        BtnOrder.setOnAction(e -> {
            for (Node node : mainController.getMap().getNodes()) {
                System.out.println(node.getIdNode());
            }
        });

        BtnSubNode.setOnAction(e -> {
            setConnectionMode();
        });

    }

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

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public boolean btnConnectionSelected (){
        return BtnConnection.isSelected();
    }
    public boolean btnNodeSelected (){
        return BtnNode.isSelected();
    }
    public boolean btnSubNodeSelected(){
        return BtnSubNode.isSelected();
    }
    public Color getColor(){
        return ColorSwitch.getValue();
    }
}
