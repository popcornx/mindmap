package model;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import static util.IdGenerator.generateUniqueId;

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

    /**
     * @param ellipse ellipse
     * @param text text
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @param color Color of the ellipse
     */
    public Node(Ellipse ellipse, Text text, double x, double y, Color color) {
        super(ellipse, text);
        this.idNode = generateUniqueId();
        this.ellipse = ellipse;
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        styleNode();
    }

    /**
     * Styles the Ellipse after creation to Standard Values
     */
    void styleNode(){
        this.setLayoutX(this.x);
        this.setLayoutY(this.y);
        this.ellipse.setRadiusX(150);
        this.ellipse.setRadiusY(100);
        this.ellipse.setStroke(this.color);
        this.ellipse.setStrokeWidth(2);
        this.ellipse.setFill(Color.WHITE);

    }

    /**
     * to be implemented
     */
    public void setText(){

    }

    /**
     * @return text
     */
    public Text getText() {
        return text;
    }

    /**
     * to be implemented
     */
    public void changeColor() {

    }

    /**
     * @return idNode
     */
    public int getIdNode() {
        return idNode;
    }

    /**
     * @return ellipse
     */
    public Ellipse getEllipse() {
        return ellipse;
    }
}
