package projekt;

import org.junit.Assert;
import org.junit.Test;
import java.io.File;

/**
 * Klasa wykorzystywana do testów jednostkowych.
 */
public class OknoNotatekTest {
    /**
     * Funkcja sprawdza poprawność działania funkcji projekt.OknoNotatek.sprawdzCzyNotatkaIstnieje
     * w przypadku kiedy inna notatka z takim samym tytułem już istnieje.
     */
    @Test
    public void sprawdzCzyNotatkaIstnieje_Istnieje() {
        OknoNotatek oknoNotatek = new OknoNotatek();
        File f = new File("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projekt" +
                "Notatki\\Zapisane\\" + "tytulTestowy" + ".txt");
        try {
            if (f.exists()) f.delete();
            f.createNewFile();
        }catch (Exception e){
            e.getMessage();
        }
        oknoNotatek.tfTytul.setText("tytulTestowy");
        Assert.assertEquals("projekt.Notatka istnieje",oknoNotatek.sprawdzCzyNotatkaIstnieje());
    }

    /**
     * Funkcja sprawdza poprawność działania funkcji projekt.OknoNotatek.sprawdzCzyNotatkaIstnieje,
     * w przypadku kiedy inna notatka z takim samym tytułem nie istnieje.
     */
    @Test
    public void sprawdzCzyNotatkaIstnieje_NieIstnieje(){
        OknoNotatek oknoNotatek = new OknoNotatek();
        File f = new File("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projekt" +
                "Notatki\\Zapisane\\" + "notatkaNieIstnieje" + ".txt");
        if (f.exists()) f.delete();
        oknoNotatek.tfTytul.setText("notatkaNieIstnieje");
        Assert.assertEquals("projekt.Notatka nie istnieje",oknoNotatek.sprawdzCzyNotatkaIstnieje());
    }

    /**
     * Funkcja sprawdza poprawność działania funkcji projekt.OknoNotatek.utworzNotatke.
     */
    @Test
    public void utworzNotatke() {
        OknoNotatek oknoNotatek = new OknoNotatek();
        oknoNotatek.tfTytul.setText("testowyTytul");
        oknoNotatek.utworzNotatke();
        File f = new File("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projekt" +
                "Notatki\\Zapisane\\" + "testowyTytul" + ".txt");
        Assert.assertTrue(f.exists());
    }

    /**
     * Funkcja sprawdza poprawność działania funkcji projekt.OknoNotatek.wczytajNotatki.
     */
    @Test
    public void wczytajNotatki() {
        OknoNotatek oknoNotatek = new OknoNotatek();
        oknoNotatek.wczytajNotatki();
        Assert.assertTrue(!oknoNotatek.lista.isEmpty());
    }

    /**
     * Funkcja sprawdza poprawność działania funkcji projekt.OknoNotatek.zapiszNotatki.
     */
    @Test
    public void zapiszNotatki() {
        OknoNotatek oknoNotatek = new OknoNotatek();
        oknoNotatek.zapiszNotatki();
        File f = new File("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projekt" +
                "Notatki\\"+"zapis.txt");
        Assert.assertTrue(f.exists());
    }
}