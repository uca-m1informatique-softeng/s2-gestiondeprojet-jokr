package joueur;

import metier.EnumRessources;
import metier.Strategy;
import objet_commun.Carte;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface FacadeJoueur {

    Map<String,Joueur> joueursConnect = new HashMap<>();

    /**
     * Instancie un joueur en vue de fournir un web service associé
     * @param id Unique IDentifier
     * @param strategie La stratégie que le joueur doit avoir
     * @param name Le nom
     * @param inv L'inventaire dont le joueur dépend
     * @return Le nouveau joueur associé aux paramètres fournis
     */
    static void newJoueur(int id, Strategy strategie,String url, String name, Inventaire inv) {
        Joueur new_player = new Joueur(id, strategie, name, inv);
        joueursConnect.put(url,new_player);
    }

    /**
     * Étant donné une carte et un plateau, renvoie si le joueur veut défausser la carte ou non
     * @param carte la carte à défausser ou non
     * @param plateau le plateau sur lequel est le joueur
     * @return true si la carte est à défausser, false sinon
     */
    static boolean jouerDefausse(String url, Carte carte, Plateau plateau) {
        Joueur j = joueursConnect.get(url);
        return j.jouerdefausse(carte, plateau);
    }

    /**
     * Demande à l'IA du joueur si elle veut acheter à gauche ou à droite
     * @param ressource la ressource à acheter
     * @param gauche l'inventaire du joueur de gauche
     * @param droite l'inventaire du joueur de droite
     * @return True Gauche / False Droite
     */
    static Boolean achatRessource(String url, EnumRessources ressource, Inventaire gauche, Inventaire droite){
        Joueur j = joueursConnect.get(url);
        return j.achatRessource(ressource,gauche,droite);
    }

    /**
     * Demande à l'IA du joueur quelle carte de la défausse elle souhaite jouer
     * @param paquetDefausse Le paquet de défausse
     * @param plateau Le plateau de jeu (contenant les inventaires des autres joueur)
     * @return Index de la carte à jouer depuis la défausse
     */
    static int jouerGratuitementDanslaDefausse(String url, List<Carte> paquetDefausse, Plateau plateau){
        Joueur j = joueursConnect.get(url);
        return j.jouerGratuitementDanslaDefausse(paquetDefausse,plateau);
    }

    /**
     * Étant donné un plateau et une main, renvoie si le joueur souhaite oui ou non construire une étape de merveille
     * @param main la main du joueur
     * @param plateau le plateau sur lequel est le joueur
     * @return true si le joueur veut construire une étape, false sinon
     */
    static boolean jouerMerveille(String url, List<Carte> main, Plateau plateau) {
        Joueur j = joueursConnect.get(url);
        return j.jouerMerveille(main, plateau);
    }

    /**
     * Étant donné un plateau et une main, renvoie la carte que le joueur choisirait pour construire une étape de
     * merveille
     * @param main la main du joueur
     * @param plateau le plateau sur lequel est le joueur
     * @return l'indice de la carte à sacrifier
     */
    static int constructMerveille(String url, List<Carte> main, Plateau plateau) {
        Joueur j = joueursConnect.get(url);
        return j.constructMerveille(main, plateau);
    }

    /**
     * Étant donné un plateau et une main, renvoie la carte que le joueur choisirait
     * @param main la main du joueur
     * @param plateau le plateau sur lequel est le joueur
     * @return l'indice de la carte renvoyée
     */
    static int choixCarte(String url, List<Carte> main, Plateau plateau) {
        Joueur j = joueursConnect.get(url);
        return j.choixCarte(main, plateau);
    }

    /**
     * Cette méthode donne le nom d'un joueur donné
     * @return le nom du joueur
     */
    static String getName(String url){
        Joueur j = joueursConnect.get(url);
        return j.getName();
    }

    /**
     * Cette méthode donne l'ID d'un joueur donné
     * @return l'ID du joueur
     */
    static int getId(String url){
        Joueur j = joueursConnect.get(url);
        return j.getId();
    }

    /**
     * Cette méthode donne l'inventaire d'un joueur donné
     * @return l'inventaire du joueur
     */
    static Inventaire getInv(String url){
        Joueur j = joueursConnect.get(url);
        return j.getInv();
    }

    /**
     * Cette méthode donne la stratégie d'un joueur donné
     * @return la stratégie du joueur
     */
    static String getStrategie(String url){
        Joueur j = joueursConnect.get(url);
        return j.getStrategie();
    }

}
