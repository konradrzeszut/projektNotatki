package projekt;

import projekt.KoniecPliku;
import projekt.Notatka;
import projekt.OknoNotatek;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.Formatter;

/**
 * Klasa tworzy nowe okno podczas otwierania poszczególnych notatek, otware okno zawiera treść notatki.
 */
public class OknoTrescNotatki extends JFrame {
    /** Pole tekstowe zawierające treść notatki */
    private JTextArea tAtrescNotatki;
    /** Panel klasy OknoTrescNotatki */
    private JPanel panelOknoNotatki;
    /** Etykieta, wyświetla tytuł notatki */
    private JLabel tfTytulNotatki;
    /** Przycisk zapisujący treść notatki */
    private JButton zapiszButton;

    /**
     * Funkcja zwraca treść notatki
     * @return tAtrescNotatki
     */
    public JTextArea gettAtrescNotatki() {return tAtrescNotatki;}

    /**
     * Funkcja zwraca tytuł notatki
     * @return tfTytulNotatki
     */
    public JLabel getTfTytulNotatki() {return tfTytulNotatki;}

    /**
     * Funkcja zwraca panel panelOknoNotatki
     * @return panelOknoNotatki
     */
    public JPanel getPanelOknoNotatki() {return panelOknoNotatki;}

    /**
     * Konstruktor bezargumentowy, dodaje obsługe zdarzeń dla przycisku zapiszButton.
     */
    OknoTrescNotatki() {
        zapiszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                zapiszNotatke();
            }});
    }

    /**
     * Funkcja pozwala na zapisanie notatki z wykorzystaniem przycisku notatki,
     * po naciśnięciu przycisku notatka jest zapisywana na dysku z wykorzystaniem unikalneg tytułu
     */
    void zapiszNotatke(){
        try {
            ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream("zapis.txt"));
            for (Notatka n : OknoNotatek.lista) {
                if (n.btNotatki.isVisible()) objout.writeObject(n);
            }
            objout.writeObject(new KoniecPliku());
            objout.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        File f = new File("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projekt" +
                "Notatki\\Zapisane\\" + getTfTytulNotatki().getText() + ".txt");
        f.delete();
        File f2 = new File("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projekt" +
                "Notatki\\Zapisane\\" + getTfTytulNotatki().getText() + ".txt");
        try {
            f.createNewFile();                             //tworzenie pliku
            System.out.println("Plik utworzony");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (f2.canWrite()) {                                     //sprawdzenie czy nasz plik moze zapisywac
            try {
                FileWriter fw = new FileWriter(f.getAbsoluteFile(), true);
                Formatter fm = new Formatter(fw);               //Formatter przekazuje sformatowany tekst do FileWritera,
                // ktory ma dostep do naszego pliku f
                fm.format("%s \r\n", gettAtrescNotatki().getText());        //Zapis do pliku
                fm.close();
                fw.close();
                System.out.println("Zapisano!");
                Notatka notatka = new Notatka(getTfTytulNotatki().getText());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
