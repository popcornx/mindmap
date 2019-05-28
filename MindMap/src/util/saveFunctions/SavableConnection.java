package util.saveFunctions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Connection Object savable through JAXB
 */
@XmlRootElement
@XmlType(propOrder={"start","end"})
public class SavableConnection {
    private Pair start,end;
    private int lineStyle;

    /**
     * Default constructor used by JAXB
     */
    public SavableConnection(){}

    /**
     * @param start Pair, starting point of the connection
     * @param end Pair, end point of the connection
     * @param lineStyle Integer, style of the connection
     */
    public SavableConnection(Pair start, Pair end, int lineStyle) {
        this.start = start;
        this.end = end;
        this.lineStyle = lineStyle;
    }

    /**
     * @return Pair, starting point of the connection
     */
    @XmlElement
    public Pair getStart() {
        return start;
    }

    /**
     * @return Pair, end point of the connection
     */
    @XmlElement
    public Pair getEnd() {
        return end;
    }

    /**
     * @return Integer, style of the connection
     */
    @XmlAttribute
    public int getLineStyle() {
        return lineStyle;
    }

    /**
     * @param start Pair, starting point of the connection
     */
    public void setStart(Pair start) {
        this.start = start;
    }

    /**
     * @param end Pair, end point of the connection
     */
    public void setEnd(Pair end) {
        this.end = end;
    }

    /**
     * @param lineStyle Integer, style of the connection
     */
    public void setLineStyle(int lineStyle) {
        this.lineStyle = lineStyle;
    }
}
