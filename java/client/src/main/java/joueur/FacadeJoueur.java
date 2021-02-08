package joueur;

import metier.EnumRessources;
import metier.Strategy;
import objet_commun.Carte;
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
    static Joueur newJoueur(int id, Strategy strategie, String name, Inventaire inv) {
        return new Joueur(id, strategie, name, inv);
    }

    /**
     * Étant donné une carte et un plateau, renvoie si le joueur veut défausser la carte ou non
     * @param j le Joueur qui choisit la carte
     * @param carte la carte à défausser ou non
     * @param plateau le plateau sur lequel est le joueur
     * @return true si la carte est à défausser, false sinon
     */
    static boolean jouerDefausse(Joueur j, Carte carte, Plateau plateau) {
        return j.jouerdefausse(carte, plateau);
    }

    /**
     * Demande à l'IA du joueur si elle veut acheter à gauche ou à droite
     * @param j le joueur
     * @param ressource la ressource à acheter
     * @param gauche l'inventaire du joueur de gauche
     * @param droite l'inventaire du joueur de droite
     * @return True Gauche / False Droite
     */
    static Boolean achatRessource(Joueur j, EnumRessources ressource, Inventaire gauche, Inventaire droite){
        return j.achatRessource(ressource,gauche,droite);
    }

    /**
     * Demande à l'IA du joueur quelle carte de la défausse elle souhaite jouer
     * @param j le joueur
     * @param paquetDefausse Le paquet de défausse
     * @param plateau Le plateau de jeu (contenant les inventaires des autres joueur)
     * @return Index de la carte à jouer depuis la défausse
     */
    static int jouerGratuitementDanslaDefausse(Joueur j, List<Carte> paquetDefausse, Plateau plateau){
        return j.jouerGratuitementDanslaDefausse(paquetDefausse,plateau);
    }

    /**
     * Étant donné un plateau et une main, renvoie si le joueur souhaite oui ou non construire une étape de merveille
     * @param j le Joueur qui choisit si il doit construire une étape de merveille
     * @param main la main du joueur
     * @param plateau le plateau sur lequel est le joueur
     * @return true si le joueur veut construire une étape, false sinon
     */
    static boolean jouerMerveille(Joueur j, List<Carte> main, Plateau plateau) {
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
    static int constructMerveille(Joueur j, List<Carte> main, Plateau plateau) {
        return j.constructMerveille(main, plateau);
    }

    /**
     * Étant donné un plateau et une main, renvoie la carte que le joueur choisirait
     * @param j le Joueur qui choisit la carte
     * @param main la main du joueur
     * @param plateau le plateau sur lequel est le joueur
     * @return l'indice de la carte renvoyée
     */
    static int choixCarte(Joueur j, List<Carte> main, Plateau plateau) {
        return j.choixCarte(main, plateau);
    }

    /**
     * Cette méthode donne le nom d'un joueur donné
     * @param j un joueur
     * @return le nom du joueur
     */
    static String getName(Joueur j){
        return j.getName();
    }

    /**
     * Cette méthode donne l'ID d'un joueur donné
     * @param j un joueur
     * @return l'ID du joueur
     */
    static int getId(Joueur j){
        return j.getId();
    }

    /**
     * Cette méthode donne l'inventaire d'un joueur donné
     * @param j un joueur
     * @return l'inventaire du joueur
     */
    static Inventaire getInv(Joueur j){
        return j.getInv();
    }

    /**
     * Cette méthode donne la stratégie d'un joueur donné
     * @param j un joueur
     * @return la stratégie du joueur
     */
    static String getStrategie(Joueur j){
        return j.getStrategie();
    }

}
