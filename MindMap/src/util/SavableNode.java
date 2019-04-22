package util;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.awt.*;

@XmlRootElement
@XmlType(propOrder = {"id", "x", "y", "text", "color"})
public class SavableNode {
    private int id;
    private String text;
    private double x, y;
    private Color color;

    public SavableNode() {}

    public SavableNode (int id, String text, double x, double y, Color color) {
        this.id = id;
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    @XmlAttribute
    public String getText() {
        return text;
    }

    @XmlAttribute
    public double getX() {
        return x;
    }

    @XmlAttribute
    public double getY() {
        return y;
    }

    @XmlAttribute
    public int getColor() {
        return color.getRGB();
    }

}
