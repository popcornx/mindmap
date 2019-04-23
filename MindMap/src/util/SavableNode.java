package util;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.awt.*;

@XmlRootElement
@XmlType(propOrder = {"id", "x", "y", "text"})
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

    @XmlAttribute(name="color")
    public int getColorInt() {
        return color.getRGB();
    }

    public Color getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setColorInt(int color) {
        this.color = new Color(color);
    }
}
