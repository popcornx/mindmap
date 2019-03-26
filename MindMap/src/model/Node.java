package model;
import controller.GuiController;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import util.IdGenerator;
import view.Main;


/**
 * Class for generating Nodes, class is extended from a Stackpane
 * to put the textarea and the ellipse together.
 */
public class Node extends StackPane {
    private int idNode;
    private Ellipse ellipse;
    private Text text;
    private double x;
    private double y;
    private Color color;
    private TextField textField = new TextField();
    private boolean edit = false;


    //Helpers for drag and drop
    private double orgX, orgY;
    private double orgTranslateX, orgTranslateY;
    /**
     * @param ellipse ellipse
     * @param text text
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @param color Color of the ellipse
     */
    public Node(Ellipse ellipse, Text text, double x, double y, Color color) {
        super(ellipse, text);
        this.idNode = IdGenerator.id.incrementAndGet();
        this.ellipse = ellipse;
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        styleNode();
    }
    /**
     * Styles the Ellipse after creation to Standard Values, also defines all MouseListeners of the
     * Object.
     */
    void styleNode(){
        this.setLayoutX(this.x-150);
        this.setLayoutY(this.y-150);
        this.ellipse.setRadiusX(150);
        this.ellipse.setRadiusY(100);
        this.ellipse.setStroke(this.color);
        this.ellipse.setStrokeWidth(2);
        this.ellipse.setFill(Color.WHITE);

        this.setOnMousePressed(e ->{
            orgX = e.getSceneX();
            orgY = e.getSceneY();
            orgTranslateX = this.getTranslateX();
            orgTranslateY = this.getTranslateY();
        });

        this.setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - orgX;
            double offsetY = e.getSceneY() - orgY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            this.setTranslateX(newTranslateX);
            this.setTranslateY(newTranslateY);
        });


        text.setOnMouseClicked(e-> {
            if (e.getButton().equals(MouseButton.PRIMARY)){
                if (e.getClickCount()==2){
                    this.getChildren().remove(text);
                    this.getChildren().add(textField);
                    edit = true;
                }
            }
        });

        this.setOnMouseClicked(e-> {
            if(e.getButton().equals(MouseButton.SECONDARY)){
                if(edit) {
                    text.setText(textField.getText());
                    this.getChildren().remove(textField);
                    this.getChildren().add(text);
                    edit = false;
                }
            }
            if (e.getButton().equals(MouseButton.PRIMARY)){
                Main.controller.nodeSelected(this);
            }
        });
    }


    /**
     * @param color color
     */
    public void changeColor(Color color) {
        this.color = color;
        this.ellipse.setStroke(this.color);
    }


    /**
     * @return Ellipse
     */
    public Ellipse getEllipse() {
        return ellipse;
    }

    /**
     * @return Text
     */
    public Text getText() {
        return text;
    }

    /**
     * @return IdNode
     */
    public int getIdNode() {return idNode; }
}

