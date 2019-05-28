package util.saveFunctions;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Positions of the Anchors
 */
@XmlType
@XmlEnum
public enum Position {
    TOP, RIGHT, BOTTOM, LEFT;

    /**
     * Used by JAXB
     * @return String representation of Position
     */
    public String value() {
        return name();
    }

    /**
     * Used by JAXB
     * @param v String
     * @return Position from String
     */
    public static Position fromValue(String v) {
        return valueOf(v);
    }
}
