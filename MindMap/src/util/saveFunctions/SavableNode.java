package util.saveFunctions;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.awt.*;

/**
 * Node Object savable by JAXB
 */
@XmlRootElement
@XmlType(propOrder = {"id", "x", "y", "text", "desc"})
public class SavableNode {
    private int id;
    private String text;
    private double x, y;
    private Color color;
    private String desc;

    /**
     * Default constructor for JAXB
     */
    public SavableNode() {}

    /**
     * @param id Integer
     * @param text String, text
     * @param x x-coordinate
     * @param y y-coordinate
     * @param color Color
     * @param description String, description
     */
    public SavableNode (int id, String text, double x, double y, Color color, String description) {
        this.id = id;
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.desc = description;
    }

    /**
     * @return Integer
     */
    @XmlAttribute
    public int getId() {
        return id;
    }

    /**
     * @return String, text
     */
    @XmlAttribute
    public String getText() {
        return text;
    }

    /**
     * @return x-coordinate
     */
    @XmlAttribute
    public double getX() {
        return x;
    }

    /**
     * @return y-coordinate
     */
    @XmlAttribute
    public double getY() {
        return y;
    }

    /**
     * @return String, description
     */
    @XmlAttribute(name="description")
    public String getDesc() {
        return desc;
    }

    /**
     * @return Integer representation of Color
     */
    @XmlAttribute(name="color")
    public int getColorInt() {
        return color.getRGB();
    }

    /**
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param id Integer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param text String, text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @param x x-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @param desc String, description
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @param color Integer, representation of Color
     */
    public void setColorInt(int color) {
        this.color = new Color(color);
    }
}
