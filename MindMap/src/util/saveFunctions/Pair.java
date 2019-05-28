package util.saveFunctions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A pair of integer and position.
 */
@XmlRootElement
public class Pair {
    private int i;
    private Position p;

    /**
     * Default constructor used by JAXB
     */
    public Pair(){}

    /**
     * @param i integer
     * @param p position
     */
    public Pair(int i, Position p) {
        this.i = i;
        this.p = p;
    }

    /**
     * @return Integer
     */
    @XmlAttribute(name="NodeID")
    public int getI() {
        return i;
    }

    /**
     * @return Position
     */
    @XmlAttribute(name="Position")
    public Position getP() {
        return p;
    }

    /**
     * @param i Integer
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     * @param p Position
     */
    public void setP(Position p) {
        this.p = p;
    }
}
