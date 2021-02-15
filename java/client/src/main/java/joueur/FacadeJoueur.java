package joueur;

import metier.EnumRessources;
import metier.Strategy;
import objet_commun.Carte;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FacadeJoueur {

    static Map<String,Joueur> joueursConnect = new HashMap<>();

    public static void setJoueursConnect(Map<String, Joueur> joueursConnect){
        FacadeJoueur.joueursConnect = joueursConnect;
    }

    /**
     * Instancie un joueur en vue de fournir un web service associé
     * @param id Unique IDentifier
     * @param strategie La stratégie que le joueur doit avoir
     * @param name Le nom
     * @param inv L'inventaire dont le joueur dépend
     * @return Le nouveau joueur associé aux paramètres fournis
     */
    public static void newJoueur(int id, Strategy strategie, String url, String name, Inventaire inv) {
        Joueur new_player = new Joueur(id, strategie, name, inv);
        joueursConnect.put(url,new_player);
    }

    /**
     * Étant donné une carte et un plateau, renvoie si le joueur veut défausser la carte ou non
     * @param carte la carte à défausser ou non
     * @param plateau le plateau sur lequel est le joueur
     * @return true si la carte est à défausser, false sinon
     */
    public static boolean jouerDefausse(String url, Carte carte, Plateau plateau) {
        Joueur j = joueursConnect.get(url);
        List<Carte> listCarte = new ArrayList<>();
        listCarte.add(carte);
        return j.jouerdefausse(listCarte, plateau);
    }


    /**
     * Demande à l'IA du joueur quelle carte de la défausse elle souhaite jouer
     * @param paquetDefausse Le paquet de défausse
     * @param plateau Le plateau de jeu (contenant les inventaires des autres joueur)
     * @return Index de la carte à jouer depuis la défausse
     */
    public static int jouerGratuitementDanslaDefausse(String url, List<Carte> paquetDefausse, Plateau plateau){
        Joueur j = joueursConnect.get(url);
        return j.jouerGratuitementDanslaDefausse(paquetDefausse,plateau);
    }

    /**
     * Étant donné un plateau et une main, renvoie si le joueur souhaite oui ou non construire une étape de merveille
     * @param main la main du joueur
     * @param plateau le plateau sur lequel est le joueur
     * @return true si le joueur veut construire une étape, false sinon
     */
    public static boolean jouerMerveille(String url, List<Carte> main, Plateau plateau) {
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
    public static int constructMerveille(String url, List<Carte> main, Plateau plateau) {
        Joueur j = joueursConnect.get(url);
        return j.constructMerveille(main, plateau);
    }

    /**
     * Étant donné un plateau et une main, renvoie la carte que le joueur choisirait
     * @param main la main du joueur
     * @param plateau le plateau sur lequel est le joueur
     * @return l'indice de la carte renvoyée
     */
    public static int choixCarte(String url, List<Carte> main, Plateau plateau) {
        Joueur j = joueursConnect.get(url);
        return j.choixCarte(main, plateau);
    }

    /**
     * Cette méthode donne le nom d'un joueur donné
     * @return le nom du joueur
     */
    public static String getName(String url){
        Joueur j = joueursConnect.get(url);
        return j.getName();
    }


    /**
     * Cette méthode donne la stratégie d'un joueur donné
     * @return la stratégie du joueur
     */
    public static String getStrategie(String url){
        Joueur j = joueursConnect.get(url);
        return j.getStrategie();
    }

}
