package objet_commun;
import metier.EnumCarte;
import metier.EnumRessources;

import java.util.*;

public class Carte {

    private final EnumCarte nom;
    private final List<EnumRessources> gain;
    private int nbJoueur ;
    private int age ;
    private EnumRessources couleur;
    private final List<EnumRessources> prix;

    /**
     * Constructeur de cartes Age
     * @param nom le nom de la carte
     * @param prix le prix de la carte
     * @param gain le gain de la carte
     * @param nbJoueur le nombre minimum de joueur nécessaire pour que la carte puisse être dans la partie
     * @param age l'Age auquel la carte dois être distribuée
     * @param couleur la couleur de la carte
     */
    public Carte(EnumCarte nom, List<EnumRessources> prix, List<EnumRessources> gain , int nbJoueur, int age, EnumRessources couleur) {
        this.nom = nom;
        this.gain = gain;
        this.prix = prix;
        this.nbJoueur = nbJoueur;
        this.age = age ;
        this.couleur = couleur ;
    }

    /**
     * Constructeur de carte merveille
     * @param nom le nom de la carte merveille
     * @param prix le prix de la carte merveille
     * @param gain le gain de la carte merveille
     */
    public Carte(EnumCarte nom,List<EnumRessources> prix, List<EnumRessources> gain) {
        this.nom = nom;
        this.gain = gain;
        this.prix = prix;
    }

    /**
     * Retourne l'Age/Epoque de la carte
     * @return l'age de la carte
     */
    public int getAge() {
        return age;
    }

    /**
     * Retourne le nombre minimum de joueurs requis pour jouer cette carte
     * @return le nombre minimum de joueurs requis pour que la carte soit dans le jeu
     */
    public int getNbJoueur() {
        return nbJoueur;
    }

    /**
     * Retourne le prix de construction de la carte
     * @return le prix de la carte
     */
    public List<EnumRessources> getPrix() {
        return prix;
    }

    /**
     * Retourne la Couleur de la carte
     * @return la couleur de la carte
     */
    public EnumRessources getCouleur() {
        return couleur;
    }


    /**
     * Retourne le nom de la carte
     * @return le nom de la carte
     */
    public EnumCarte getNom() {
        return nom;
    }

    /**
     * Retourne la liste de ressources gain de la carte
     * @return le gain de la carte
     */
    public List<EnumRessources> getGain() {
        return gain;
    }


    /**
     * Redefinition de la la méthode equals
     * @param obj un objet à comparer
     * @return True si les deux objets sont égaux
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Carte) {
            Carte carteAge1 = (Carte)obj;
            return this.nom.equals(carteAge1.nom);
        }
        return false;
    }

    /**
     * Redéfinition de la méthode hashCode
     * @return Hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
