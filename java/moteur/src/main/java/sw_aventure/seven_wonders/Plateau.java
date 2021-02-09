package sw_aventure.seven_wonders;
import sw_aventure.objetjeu.Inventaire;
import joueur.Joueur;
import java.util.List;

/**
 * Classe regroupant les inventaires et les joueurs
 */

public class Plateau {
    private final List<Joueur> listeJoueur;
    private final List<Inventaire> listeInventaire;
    private int tour ;
    private int age = 0 ;

    /**
     * Constructeur de l'objet Plateau
     * @param listeInventaire liste des inventaires
     * @param listeJoueur liste des joueurs
     */
    public Plateau(List<Inventaire> listeInventaire, List<Joueur> listeJoueur) {
        this.listeInventaire = listeInventaire;
        this.listeJoueur = listeJoueur;
    }


    /**
     * @return  numéro de l'age en cours
     */

    public int getAge() {
        return age;
    }

    /**
     * augmente le compteur Age de 1
     */
    public void incrementeAge(){
        age+=1;
    }

    /**
     * augmente le compteur tour de 1
     */
    public void incrementeTour(){
        tour+=1;
    }

    /**
     * reinitialise le compteur de tour pour un nouvel age
     */
    public void restartTour(){
        tour = 0 ;
    }

    /**
     * @return numéro du tour en cours
     */

    public int getTour(){
        return tour;
    }

    /**
     * @return la liste de tous les inventaires
     */

    public List<Inventaire> getListeInventaire() {
        return listeInventaire;
    }

    /**
     * @return liste de tous les joueurs
     */

    public List<Joueur> getListeJoueur() {
        return listeJoueur;
    }

    /**
     * @param joueur le joueur
     * @return Le joueur à droite du joueur en paramètre
     */

    public Joueur joueurDroit(Joueur joueur) {
        for (int i = 0; i < listeJoueur.size(); i++) {
            if (listeJoueur.get(i) == joueur) {
                return listeJoueur.get((i + 1) % listeJoueur.size());
            }
        }
        return null;
    }

    /**
     * @param joueur le joueur
     * @return Le joueur à gauche du joueur en paramètre
     */

    public Joueur joueurGauche(Joueur joueur) {
        for (int i = 0; i < listeJoueur.size(); i++) {
            if (listeJoueur.get(i).equals(joueur)) {
                if((i-1) % listeJoueur.size()<0){
                    return listeJoueur.get(listeJoueur.size() + ((i-1)% listeJoueur.size()));
                }
                return listeJoueur.get((i - 1) % listeJoueur.size());
            }
        }
        return null;
    }

}
