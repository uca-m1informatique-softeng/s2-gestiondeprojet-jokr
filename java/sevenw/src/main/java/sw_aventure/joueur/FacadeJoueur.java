package sw_aventure.joueur;

import metier.Strategy;
import sw_aventure.objetjeu.Carte;
import sw_aventure.objetjeu.Inventaire;
import sw_aventure.seven_wonders.Plateau;

import java.util.List;

public interface FacadeJoueur {

    /**
     * Instancie un joueur en vue de fournir un web service associé
     * @param id Unique IDentifier
     * @param strategie La stratégie que le joueur doit avoir
     * @param name Le nom
     * @param inv L'inventaire dont le joueur dépend
     * @return Le nouveau joueur associé aux paramètres fournis
     */
    public static Joueur newJoueur(int id, Strategy strategie, String name, Inventaire inv) {
        return new Joueur(id, strategie, name, inv);
    }

    /**
     * Étant donné un plateau et une main, renvoie la carte que le joueur choisirait
     * @param j le Joueur qui choisit la carte
     * @param main la main du joueur
     * @param plateau le plateau sur lequel est le joueur
     * @return l'indice de la carte renvoyée
     */
    public static int choixCarte(Joueur j, List<Carte> main, Plateau plateau) {
        return j.choixCarte(main, plateau);
    }

    /**
     * Étant donné un plateau et une main, renvoie si le joueur souhaite oui ou non construire une étape de merveille
     * @param j le Joueur qui choisit si il doit construire une étape de merveille
     * @param main la main du joueur
     * @param plateau le plateau sur lequel est le joueur
     * @return true si le joueur veut construire une étape, false sinon
     */
    public static boolean jouerMerveille(Joueur j, List<Carte> main, Plateau plateau) {
        return j.jouerMerveille(main, plateau);
    }

    /**
     * Étant donné un plateau et une main, renvoie la carte que le joueur choisirait pour construire une étape de
     * merveille
     * @param j le Joueur qui choisit la carte
     * @param main la main du joueur
     * @param plateau le plateau sur lequel est le joueur
     * @return l'indice de la carte à sacrifier
     */
    public static int constructMerveille(Joueur j, List<Carte> main, Plateau plateau) {
        return j.constructMerveille(main, plateau);
    }

    /**
     * Étant donné une carte et un plateau, renvoie si le joueur veut défausser la carte ou non
     * @param j le Joueur qui choisit la carte
     * @param carte la carte à défausser ou non
     * @param plateau le plateau sur lequel est le joueur
     * @return true si la carte est à défausser, false sinon
     */
    public static boolean jouerDefausse(Joueur j, Carte carte, Plateau plateau) {
        return j.jouerdefausse(carte, plateau);
    }
}
