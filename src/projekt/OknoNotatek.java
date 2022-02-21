package projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * Klasa reprezentująca okno, w którym wyświetlona jest utworzona przez użytkownika lista notatek.
 */
public class OknoNotatek extends JFrame implements Serializable {
    /** Przycisk tworzący nową notatkę.*/
    JButton nowaNotatka = new JButton("Utwórz");
    /** Panel główny, który składa się z paneluPrzyciskow(panelbtPrzycisk + panelbtUsunPrzycisk) oraz panelNowaNotatka*/
    JPanel panelGlowny = new JPanel();
    /** Panel przycisków składający się z panelbtPrzycisk oraz panelbtUsunPrzycisk*/
    JPanel panelPrzyciskow = new JPanel();
    /** Panel składający się z przycisku nowaNotatka, etykiety jlnowaNotatka oraz pola tekstowego tfTytul,
     * funkcją panelu jest tworzenie nowych notatek*/
    JPanel panelNowaNotatka = new JPanel();
    /** Panel zawierajacy przyciski nowo powstałych notatek*/
    JPanel panelbtPrzycisk = new JPanel();
    /** Panel zawierający przyciski usuwające utworzone notatki*/
    JPanel panelbtUsunPrzycisk = new JPanel();
    /** Pole tekstowe określające tytuł nowo utworzonej notatki*/
    JTextField tfTytul = new JTextField();
    /** Etykieta określająca cel pola tekstowego tfTytuł*/
    JLabel jlnowNotatka = new JLabel("Wpisz tytuł: ");
    /**Statyczna lista przechowująca utworzone notatki*/
    static ArrayList<Notatka> lista = new ArrayList<>();
    /**Referencja klasy Notatka, wykorzystywana podczas wczytywania zapisanych notatek*/
    Notatka wczytanaN;

    /**
     * Konstruktor bezargumentowy, tworzy okno z utworzonymi notatkami.
     */
    public OknoNotatek() {
        super("Notatki");
        utworzPanel();
        wczytajNotatki();
    }

    /**
     * Funkcja odpowiedzialna za obsługe zdarzeń przycisku nowaNotatka
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        Object przycisk = e.getSource();

        if (przycisk == nowaNotatka) {
            sprawdzCzyNotatkaIstnieje();
            utworzNotatke();
            zapiszNotatki();
        }
    }

    /**
     * Funkcja, która weryfikuje podczas tworzenia nowej notatki istnienie innej notatki o takiej samej nazwie,
     * każda notatka musi posiadać unikalną nazwę
     * @return Zwraca napis "projekt.Notatka istnieje" przy próbie utworzenia notatki z wykorzysywaną nazwą przez inną
     * notatkę lub "projekt.Notatka nie istnieje" w przypadku kiedy nie ma innej notatki o takiej samej nazwie.
     */
    String sprawdzCzyNotatkaIstnieje() {
        File f = new File("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projekt" +
                "Notatki\\Zapisane\\" + tfTytul.getText() + ".txt");      //proba otwarcia pliku - jeszcze nie stworzony
        if (f.exists()) {
            System.out.println("projekt.Notatka juz istnieje");
            JFrame frame = new JFrame("Błąd");
            frame.setContentPane(new OknoBlad().getPanelStworzNotatke());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            return "projekt.Notatka istnieje";
        }
        return "projekt.Notatka nie istnieje";
    }

    /**
     * Funkcja tworzy nową notatke, tworząc obiekt klasy projekt.Notatka oraz tworząc plik o rozszerzeniu txt na dysku.
     */
    void utworzNotatke(){
        File f = new File("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projekt" +
                "Notatki\\Zapisane\\" + tfTytul.getText() + ".txt");
        if (!f.exists()) {
            try {
                f.createNewFile();                             //tworzenie pliku
                System.out.println("Plik utworzony");
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        Notatka notatka = new Notatka(tfTytul.getText());
        lista.add(notatka);
        panelbtPrzycisk.add(notatka.btNotatki);
        panelbtUsunPrzycisk.add(notatka.btUsunNotatke);
        notatka.btNotatki.setVisible(false);
        notatka.btNotatki.setVisible(true);
        notatka.btUsunNotatke.setVisible(false);
        notatka.btUsunNotatke.setVisible(true);
    }

    /**
     * Funkcja wczytuje notatki, a więc obiekty klasy projekt.Notatka z utworzonego pliku zapis.txt.
     */
    void wczytajNotatki(){
        try {
            ObjectInputStream objin13 = new ObjectInputStream(new FileInputStream("zapis.txt"));
            Object obj = null;
            while ((obj = objin13.readObject()) instanceof KoniecPliku == false) {
                wczytanaN = new Notatka(((Notatka) obj).tytulNotatki);
                panelbtPrzycisk.add(wczytanaN.btNotatki);
                panelbtUsunPrzycisk.add(wczytanaN.btUsunNotatke);
                System.out.println(wczytanaN.tytulNotatki);

                (((Notatka) obj).btNotatki).setVisible(true);
                (((Notatka) obj).btUsunNotatke).setVisible(true);
                lista.add(wczytanaN);
            }
            objin13.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Funkcja tworzy panel z utworzonymi notatkami,
     * wywołanie funkcji następuje w konstruktorze klasy projekt.OknoNotatek.
     */
    void utworzPanel(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(380, 450);
        setResizable(false);
        this.add(panelGlowny);

        nowaNotatka.addActionListener(this::actionPerformed);
        nowaNotatka.setVisible(true);

        tfTytul.setVisible(true);
        tfTytul.setSize(150, 20);
        tfTytul.setPreferredSize(new Dimension(150, 22));

        panelNowaNotatka.setLayout(new FlowLayout());
        panelNowaNotatka.add(jlnowNotatka);
        panelNowaNotatka.add(tfTytul);
        panelNowaNotatka.add(nowaNotatka);
        jlnowNotatka.setVisible(true);

        JScrollPane scrollPane = new JScrollPane(panelPrzyciskow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelbtPrzycisk.setLayout(new FlowLayout());
        panelbtPrzycisk.setMaximumSize(new Dimension(160, 600));
        panelbtUsunPrzycisk.setMaximumSize(new Dimension(160, 600));
        panelbtUsunPrzycisk.setLayout(new FlowLayout());
        panelPrzyciskow.setPreferredSize(new Dimension(350, 600));
        panelPrzyciskow.setMaximumSize(new Dimension(350, 600));
        panelPrzyciskow.setLayout(new GridLayout(0, 2));
        panelPrzyciskow.add(panelbtPrzycisk);
        panelPrzyciskow.add(panelbtUsunPrzycisk);
        panelGlowny.setLayout(new BorderLayout());
        panelGlowny.add(BorderLayout.CENTER, scrollPane);
        panelGlowny.add(BorderLayout.SOUTH, panelNowaNotatka);
    }

    /**
     * Funkcja zapisuje do pliku zapis.txt utworzone notatki, a więc obiekty klasy projekt.Notatka.
     */
    void zapiszNotatki(){
        try {
            ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream("zapis.txt"));
            for (Notatka n : OknoNotatek.lista){
                if (n.btNotatki.isVisible()) objout.writeObject(n);
            }
            objout.writeObject(new KoniecPliku());
            objout.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
