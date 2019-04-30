package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import model.Node;

public class ToolbarController {
    @FXML
    private Button BtnSubNode;
    @FXML
    private Button BtnOrder;
    @FXML
    private ColorPicker ColorSwitch;
    @FXML
    private ToggleButton BtnNode;
    @FXML
    private ToggleButton BtnConnection;
    private MainController mainController;
    private Boolean connectionMode = false;

    @FXML
    public void initialize(){
        //DefaultValue
        ColorSwitch.setValue(Color.BLACK);
        BtnConnection.setOnAction(e-> {
            if(connectionMode){
                for(Node node : mainController.getMap().getNodes()){
                    node.connectionMode(false);
                    connectionMode = false;
                }
            }else {
                for(Node node : mainController.getMap().getNodes()){
                    node.connectionMode(true);
                    connectionMode = true;
                }
            }
        });

        ColorSwitch.setOnAction(e-> {
            if (mainController.getSelectedNode()!=null){
                mainController.getSelectedNode().changeColor(ColorSwitch.getValue());
            }
        });

        //To be Implemented!!
        BtnOrder.setOnAction(e-> {
            for (Node node : mainController.getMap().getNodes()){
                System.out.println(node.getIdNode());
            }
        });

        //To be Implemented!!
        BtnSubNode.setOnAction(e-> {
            for (Node node : mainController.getMap().getNodes()){
                System.out.println(node.getIdNode());
            }
        });
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

    public Color getColor(){
        return ColorSwitch.getValue();
    }


}
