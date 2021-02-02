package metier;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;

/**
 *      Cette classe les données d'une partie
 * @see #nomJoueur
 *          Le nom du joueur
 * @see #strategie
 *          La statégie du joueur
 * @see #merveille
 *          La merveille du joueur
 * @see #cartes
 *          La liste des cartes du joueur en fin de partie
 * @see #sac
 *          L'inventaire du joueur
 */
public class Data {

    private String nomJoueur;
    private String strategie;
    private String merveille;
    private List<String> cartes;
    private Map<String, Integer> sac;


    /**
     * Constructeur vide pour JSON
     */
    public Data() { }


    /**
     * Constructeur pour la classe DATA
     * @param nomJoueur Le nom du joueur
     * @param strategie La statégie du joueur
     * @param sac L'inventaire du joueur
     * @param merveille La merveille du joueur
     * @param cartes La liste des cartes du joueur
     */
    public Data(String nomJoueur, String strategie, Map<String, Integer> sac, String merveille, List<String> cartes) {
        this.nomJoueur = nomJoueur;
        this.strategie = strategie;
        this.sac = sac;
        this.merveille = merveille;
        this.cartes = cartes;
    }


    /**
     * Getter du nom du joueur
     * @return le nom du joueur
     */
    public String getNomJoueur() {
        return nomJoueur;
    }


    /**
     * Setter du nom de joueur
     * @param nomJoueur Le nom du joueur
     */
    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }


    /**
     * Getter de la stratégie utilisé du joueur
     * @return La stratégie du joueur
     */
    public String getStrategie() {
        return strategie;
    }


    /**
     * Setter de la stratégie du joueur
     * @param stategie La stratégie du joueur
     */
    public void setStrategie(String stategie) {
        this.strategie = stategie;
    }


    /**
     * Getter de la Map du joueur (qui représente son inventaire)
     * @return L'inventaire du joueur (HashMap)
     */
    public Map<String, Integer> getSac() {
        return sac;
    }


    /**
     * Setter de la Map du joueur (qui représente son inventaire)
     * @param sac L'inventaire du joueur (HashMap)
     */
    public void setSac(Map<String, Integer> sac) {
        this.sac = sac;
    }


    /**
     * Permet d'obtenir la quantité d'une ressource donnée que le joueur possède
     * @param s Chaine de caractère (key)
     * @return la valeur associée à la chaîne
     */
    public int getValue(String s){
        return sac.get(s);
    }


    /**
     * Getter du nom de la merveille
     * @return le nom de la merveille
     */
    public String getMerveille() {
        return merveille;
    }


    /**
     * Setter du nom de la merveille
     * @param merveille le nom de la merveille
     */
    public void setMerveille(String merveille) {
        this.merveille = merveille;
    }


    /**
     * Getter de la liste des cartes du joueur
     * @return La liste des cartes du joueur
     */
    public List<String> getCartes() {
        return cartes;
    }


    /**
     * Setter de la liste des cartes du joueur
     * @param cartes La liste des cartes du joueur
     */
    public void setCartes(List<String> cartes) {
        this.cartes = cartes;
    }


    /**
     * Retourne l'objet JSON de Data
     * @return Objet JSON de toutes les données de la partie du joueur
     */
    public JSONObject toJSON() throws JSONException {
        JSONObject data = new JSONObject();
        data.put("nomJoueur", nomJoueur);
        data.put("strategie", strategie);
        data.put("sac", sac);
        data.put("merveille", merveille);
        data.put("cartes", cartes);
        return data;
    }
}
