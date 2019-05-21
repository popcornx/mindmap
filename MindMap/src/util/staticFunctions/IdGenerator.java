package util.staticFunctions;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Static counter so that every Node as a unique ID
 */
public class IdGenerator {
    public static AtomicInteger id = new AtomicInteger(0);
}


