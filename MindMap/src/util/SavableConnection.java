package util;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"start","end"})
public class SavableConnection {
    private Pair start,end;
    private int lineStyle;
    public SavableConnection(){}

    public SavableConnection(Pair start, Pair end, int lineStyle) {
        this.start = start;
        this.end = end;
        this.lineStyle = lineStyle;
    }

    @XmlElement
    public Pair getStart() {
        return start;
    }

    @XmlElement
    public Pair getEnd() {
        return end;
    }
    @XmlAttribute
    public int getLineStyle() {
        return lineStyle;
    }

    public void setStart(Pair start) {
        this.start = start;
    }

    public void setEnd(Pair end) {
        this.end = end;
    }

    public void setLineStyle(int lineStyle) {
        this.lineStyle = lineStyle;
    }
}
