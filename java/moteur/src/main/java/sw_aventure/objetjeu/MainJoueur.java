package sw_aventure.objetjeu;

import objet_commun.Carte;

import java.util.ArrayList;
import java.util.List;

public class MainJoueur {

    /**
     * Main de carte
     */
    private final List<Carte> main;


    /**
     * Initialise l'ArrayList main.
     */
    public MainJoueur() {
        main = new ArrayList<>();
    }


    /**
     * Méthode qui ajoute une carte dans la main.
     * @param carte la carte à ajouter
     */
    public void add(Carte carte) {
        main.add(carte);
    }


    /**
     * Méthode qui retoure la main (ArrayList de CarteAge1).
     * @return ArrayList CarteAge
     */
    public List<Carte> getMain() {
        return main;
    }
}
