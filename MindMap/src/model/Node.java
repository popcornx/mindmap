package model;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import static util.IdGenerator.generateUniqueId;

public class Node extends StackPane {
    private int idNode;
    private Ellipse ellipse;
    private Text text;

    public Node(Ellipse ellipse, Text text) {
        super(ellipse, text);
        this.idNode = generateUniqueId();
        this.ellipse = ellipse;
        this.text = text;
    }


    public int getIdNode() {
        return idNode;
    }

    public Ellipse getEllipse() {
        return ellipse;
    }

    public Text getText() {
        return text;
    }


}
