package util;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Position {
    TOP, RIGHT, BOTTOM, LEFT;

    public String value() {
        return name();
    }

    public static Position fromValue(String v) {
        return valueOf(v);
    }
}
