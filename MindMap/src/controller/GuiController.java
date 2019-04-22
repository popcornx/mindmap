package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Connection;
import model.Map;
import model.Node;
import util.SavableMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;

public class GuiController {
    @FXML
    private ToggleButton BtnNode;
    @FXML
    private ToggleButton BtnConnection;
    @FXML
    private Button BtnSubNode;
    @FXML
    private Button BtnOrder;
    @FXML
    private ColorPicker ColorSwitch;
    @FXML
    private AnchorPane pane;
    @FXML
    private MenuItem MIsave;
    private Map map = new Map();
    private Node selectedNode;
    private Connection selectedConnection;

    private Node Start;
    private Node End;
    private Boolean connectionMode = false;


    public void conectionSelected (Connection connection){
        if(selectedConnection != null) {
            selectedConnection.setStroke(Color.SILVER);
        }
        selectedConnection = connection;
        selectedConnection.setStroke(Color.RED);
    }
    public void deleteConnection() {
        if(selectedConnection != null) {
            pane.getChildren().remove(selectedConnection);
            //delete from map
        }
    }

    /**
     * @param node Node
     */
    public void nodeSelected(Node node){
        if(selectedNode != null) {
            selectedNode.getEllipse().setStrokeWidth(2);
        }
        selectedNode = node;
        selectedNode.getEllipse().setStrokeWidth(10);
    }
    public void deleteNode() {
        if(selectedNode != null) {
            pane.getChildren().remove(selectedNode);
            //delete from map
        }
    }

    private void save(){
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Save Mindmap");
            fc.setInitialFileName("map.xml");
            File file = fc.showSaveDialog(new Stage());

            JAXBContext context = JAXBContext.newInstance(SavableMap.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(map.saveMap(), new FileOutputStream(file));
//            output to console for quicker result evaluation, disable filechooser
//            marshaller.marshal(map.saveMap(), System.out);
        }
        catch (Exception exe) {
            exe.printStackTrace();
        }
    }

    public void initialize() {
        //DefaultValue
        ColorSwitch.setValue(Color.BLACK);


        pane.setOnMouseClicked(e -> {
            if (BtnNode.isSelected()) {
                Ellipse ellipse = new Ellipse();
                Text text = new Text("Text");

                Node node = new Node(ellipse, text,e.getSceneX(),e.getSceneY(),ColorSwitch.getValue());
                pane.getChildren().add(node);
                map.addNode(node);
                node.connectionMode(false);
            }
            if (BtnConnection.isSelected()) {
                int count = 0;
                for (Node node : map.getNodes()){
                    if (node.activeNode()){
                        if (count == 0) {
                            Start = node;
                        }else {
                            End = node;
                        }
                        count++;
                    }
                }
                if (count == 2 ) {
                    Connection connection = new Connection(new Pair<>(Start,Start.getActiveAnchor().getPos()), new Pair<>(End,End.getActiveAnchor().getPos()));
                    connection.startYProperty().bind(Start.getActiveAnchor().helpCenterYProperty());
                    connection.startXProperty().bind(Start.getActiveAnchor().helpCenterXProperty());
                    connection.endXProperty().bind(End.getActiveAnchor().helpCenterXProperty());
                    connection.endYProperty().bind(End.getActiveAnchor().helpCenterYProperty());
                    pane.getChildren().add(connection);
                    map.addConnection(connection);
                    Start.deactivate();
                    End.deactivate();
                    Start.getActiveAnchor().deactivate();
                    End.getActiveAnchor().deactivate();
                }
            }
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                if (selectedNode != null){
                    selectedNode.getEllipse().setStrokeWidth(2);
                    selectedNode = null;
                }
                if (selectedConnection != null){
                    selectedConnection.setStroke(Color.SILVER);
                    selectedConnection = null;
                }
            }
        });

        BtnConnection.setOnAction(e-> {
            if(connectionMode){
                for(Node node : map.getNodes()){
                    node.connectionMode(false);
                    connectionMode = false;
                }
            }else {
                for(Node node : map.getNodes()){
                    node.connectionMode(true);
                    connectionMode = true;
                }
            }
        });

        ColorSwitch.setOnAction(e-> {
          if (selectedNode!=null){
              selectedNode.changeColor(ColorSwitch.getValue());
          }
        });

        //To be Implemented!!
        BtnOrder.setOnAction(e-> {
            for (Node node : map.getNodes()){
                System.out.println(node.getIdNode());
            }
        });

        //To be Implemented!!
        BtnSubNode.setOnAction(e-> {
            for (Node node : map.getNodes()){
                System.out.println(node.getIdNode());
            }
        });

        MIsave.setOnAction(e -> save());
    }

}