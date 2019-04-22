package util;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pair {
    private int i;
    private Position p;

    public Pair(){}

    public Pair(int i, Position p) {
        this.i = i;
        this.p = p;
    }

    @XmlAttribute(name="NodeID")
    public int getI() {
        return i;
    }

    @XmlAttribute(name="Position")
    public Position getP() {
        return p;
    }
}
