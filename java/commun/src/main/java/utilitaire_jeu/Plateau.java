package utilitaire_jeu;

import java.util.List;

/**
 * Classe regroupant les inventaires et les joueurs
 */

public class Plateau {
    private List<Inventaire> listeInventaire;
    private int tour ;
    private int age = 0 ;

    /**
     * Constructeur de l'objet Plateau
     * @param listeInventaire liste des inventaires
     */
    public Plateau(List<Inventaire> listeInventaire) {
        this.listeInventaire = listeInventaire;
    }

    public Plateau(){}
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
     * @param joueur le joueur
     * @return Le joueur à droite du joueur en paramètre
     */

    public Inventaire joueurDroit(Inventaire joueur) {
        for (int i = 0; i < listeInventaire.size(); i++) {
            if (listeInventaire.get(i).equals(joueur)) {
                return listeInventaire.get((i + 1) % listeInventaire.size());
            }
        }
        return null;
    }

    /**
     * @param joueur le joueur
     * @return Le joueur à gauche du joueur en paramètre
     */

    public Inventaire joueurGauche(Inventaire joueur) {
        for (int i = 0; i < listeInventaire.size(); i++) {
            if (listeInventaire.get(i).equals(joueur)) {
                if((i-1) % listeInventaire.size()<0){
                    return listeInventaire.get(listeInventaire.size() + ((i-1)% listeInventaire.size()));
                }
                return listeInventaire.get((i - 1) % listeInventaire.size());
            }
        }
        return null;
    }

}
