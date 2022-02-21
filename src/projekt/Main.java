package projekt;
import java.awt.*;

/**
 * @author Konrad Rzeszutek
 * @version 1.0
 */
public class Main {
    /**
     * Funkcja main.
     * @param args
     */
    public static void main(String[] args) {
       EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OknoNotatek();
            }
        });
    }
}