package sw_aventure.joueur;

import metier.EnumRessources;
import objet_commun.Carte;
import sw_aventure.objetjeu.Inventaire;
import sw_aventure.seven_wonders.Plateau;
import java.util.List;

/**
 * Classe établissant une stratégie de jeu aléatoire pour l'IA. Elle implémente IA qui par défaut établit déjà la stratégie aléatoire.
 */
public class IArandom implements IA {

    @Override
    public String toString() {
        return "IA random";
    }

    /**
     * choisit aléatoirement entre le joueur de gauche ou de droite pour acheter une ressource
     * @param ressource la ressource a acheter
     * @param j le joueur
     * @param gauche l'inventaire du joueur de gauche
     * @param droite l'inventaire du joueur de droite
     * @return True gauche / False Droite
     */
    @Override
    public Boolean commerceAdjacent(EnumRessources ressource, Joueur j, Inventaire gauche, Inventaire droite){
        int choix = r.nextInt(2);
        return choix == 0;
    }

    /**
     * choisit une carte aléatoirement à sacrifier pour construire la merveille
     * @param joueur le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @return index de la carte à sacrifier dans la main
     */
    @Override
    public int choixCartePourMerveille(Joueur joueur, List<Carte> main, Plateau plateau){
        return r.nextInt(main.size());
    }


    /**
     * Renvoie aléatoirement Vrai ou Faux quand au choix de défausser ou non
     * @param j le joueur
     * @param carte la carte à jouer
     * @param plateau le plateau de jeu
     * @return True defausse / False pas défausse
     */
    @Override
    public boolean choixDefausse(Joueur j, Carte carte, Plateau plateau){
        int choix = r.nextInt(2);
        return choix == 0;
    }

}