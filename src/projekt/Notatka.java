package projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/***
 * Klasa reprezentująca notatkę poprzez unikalny tytuł.
 */
public class Notatka implements Serializable {
    /** Zmienna String zawierająca tytuł notatki */
    String tytulNotatki;
    /** Przycisk otwierający okno z treścią notatki */
    JButton btNotatki;
    /** Przycisk usuwający notatke */
    JButton btUsunNotatke;

    /**
     * Konstruktor, który jako parametr przyjmuje tytuł notatki
     * @param tytulNotatki - tytuł tworzonej notatki
     */
    Notatka(String tytulNotatki){
        this.tytulNotatki = tytulNotatki;
        btNotatki = new JButton("" + tytulNotatki);
        btNotatki.setPreferredSize(new Dimension(150,20));
        btUsunNotatke = new JButton("Usuń");
        btNotatki.setMaximumSize(new Dimension(150,20));
        btUsunNotatke.setPreferredSize(new Dimension(150,20));
        btUsunNotatke.setMaximumSize(new Dimension(150,20));

        this.btNotatki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame();
                OknoTrescNotatki ok = new OknoTrescNotatki();
                ok.getTfTytulNotatki().setText(tytulNotatki);
                frame.setContentPane(ok.getPanelOknoNotatki());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                System.out.println(tytulNotatki);
                try {
                    FileReader odczyt = new FileReader("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projektNotatki\\Zapisane\\" + tytulNotatki + ".txt");
                    BufferedReader br = new BufferedReader(odczyt); //buforowanie znakow w celu sprawnego odczytu
                    (ok.gettAtrescNotatki()).read(br, null);
                    br.close();
                    (ok.gettAtrescNotatki()).requestFocus();
                    (ok.gettAtrescNotatki()).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        this.btUsunNotatke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btNotatki.setVisible(false);
                btUsunNotatke.setVisible(false);
                File f2 = new File("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projekt"+
                        "Notatki\\"+"zapis.txt");
                f2.delete();
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
                File f = new File("D:\\Programy\\IntelliJ IDEA Community Edition 2021.2.2\\projekt"+
                        "Notatki\\Zapisane\\" + tytulNotatki + ".txt");
                f.delete();
            };
        });
    }
}