package projekt;

import javax.swing.*;

/**
 * Klasa służąca do wyświetlenia okna informującego o błędzie,
 * błąd mówi o próbie utworzenia notatki o nazwie, która określa już inna notatkę.
 */
public class OknoBlad {
    /**
     * Funkcja zwracająca panelStworzNotatke,
     * @return panelStworzNotatke
     */
    public JPanel getPanelStworzNotatke() {return panelStworzNotatke;}
    private JPanel panelStworzNotatke;
    /**
     * Konstruktor bezargumentowy klasy OknoBlad.
     */
    public OknoBlad() { }
}


