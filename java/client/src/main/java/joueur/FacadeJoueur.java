package joueur;

import metier.Strategy;
import utilitaire_jeu.DataToClient;
import java.util.HashMap;
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
     * @return Le nouveau joueur associé aux paramètres fournis
     */
    public static void newJoueur(int id, Strategy strategie, String url, String name) {
        Joueur new_player = new Joueur(id, strategie, name);
        joueursConnect.put(url,new_player);
    }

    /**
     * Étant donné une carte et un plateau, renvoie si le joueur veut défausser la carte ou non
     * @param data les données transmise par Json contenant une liste de carte un inventaire et un objet plateau
     * @param url l'url du joueur
     * @return true si la carte est à défausser, false sinon
     */
    public static boolean jouerDefausse(String url, DataToClient data) {
        Joueur j = joueursConnect.get(url);
        return j.jouerdefausse(data.getListCarte(), data.getInvJoueur(), data.getPlateau());
    }


    /**
     * Demande à l'IA du joueur quelle carte de la défausse elle souhaite jouer
     * @param data les données transmise par Json contenant une liste de carte un inventaire et un objet plateau
     * @param url l'url du joueur
     * @return Index de la carte à jouer depuis la défausse
     */
    public static int jouerGratuitementDanslaDefausse(String url, DataToClient data){
        Joueur j = joueursConnect.get(url);
        return j.jouerGratuitementDanslaDefausse(data.getListCarte(), data.getInvJoueur(), data.getPlateau());
    }

    /**
     * Étant donné un plateau et une main, renvoie si le joueur souhaite oui ou non construire une étape de merveille
     * @param data les données transmise par Json contenant une liste de carte un inventaire et un objet plateau
     * @param url l'url du joueur
     * @return true si le joueur veut construire une étape, false sinon
     */
    public static boolean jouerMerveille(String url, DataToClient data) {
        Joueur j = joueursConnect.get(url);
        return j.jouerMerveille(data.getListCarte(), data.getInvJoueur(), data.getPlateau());
    }

    /**
     * Étant donné un plateau et une main, renvoie la carte que le joueur choisirait pour construire une étape de
     * merveille
     * @param data les données transmise par Json contenant une liste de carte un inventaire et un objet plateau
     * @param url l'url du joueur
     * @return l'indice de la carte à sacrifier
     */
    public static int constructMerveille(String url, DataToClient data) {
        Joueur j = joueursConnect.get(url);
        return j.constructMerveille(data.getListCarte(), data.getInvJoueur(), data.getPlateau());
    }

    /**
     * Étant donné un plateau et une main, renvoie la carte que le joueur choisirait
     * @param data les données transmise par Json contenant une liste de carte un inventaire et un objet plateau
     * @param url l'url du joueur
     * @return l'indice de la carte renvoyée
     */
    public static int choixCarte(String url, DataToClient data) {
        Joueur j = joueursConnect.get(url);
        return j.choixCarte(data.getListCarte(), data.getInvJoueur(), data.getPlateau());
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
