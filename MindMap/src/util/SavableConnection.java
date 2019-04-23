package util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"start","end"})
public class SavableConnection {
    private Pair start,end;
    public SavableConnection(){}

    public SavableConnection(Pair start, Pair end) {
        this.start = start;
        this.end = end;
    }

    @XmlElement
    public Pair getStart() {
        return start;
    }

    @XmlElement
    public Pair getEnd() {
        return end;
    }

    public void setStart(Pair start) {
        this.start = start;
    }

    public void setEnd(Pair end) {
        this.end = end;
    }
}
