package sw_aventure.objetjeu;

import metier.EnumRessources;
import metier.Wonder;
import sw_aventure.joueur.Joueur;


import java.util.List;
import java.util.Objects;

public class Merveille {

    private final Wonder nom;
    private final EnumRessources gain;
    private final List<Carte> etape;
    private final Joueur joueur;
    private int stade;

    /**
     * Constructeur pour les Merveilles
     * @param nom le nom de la merveille
     * @param gain le gain de base de la merveille
     * @param etape le nombre d'étage de la merveille
     * @param joueur le joueur associé à la merveille
     */
    public Merveille(Wonder nom, EnumRessources gain, List<Carte> etape, Joueur joueur) {
        this.nom = nom;
        this.gain = gain;
        this.etape = etape;
        this.joueur = joueur;
        stade = 0;
    }

    /**
     * @return renvoie le joueur associé à la merveille
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Si il reste des étages de merveille à construire renvoie True sinon False
     * @return True si il en reste / False sinon
     */
    public Boolean peutAmeliorerMerveille() {
        return stade < etape.size();
    }

    /**
     * Retourne la carte merveille représentant l'étage à construire
     * @return Carte
     */
    public Carte getCarteAConstruire() {
        return etape.get(stade);
    }

    /**
     * @return le nom de la Merveille
     */
    public Wonder getNom() {
        return nom;
    }

    /**
     * @return le stade d'avancement de la merveille
     */
    public int getStade() {
        return stade;
    }

    /**
     * Augmente le stade de 1 (après la construction d'un étage)
     */
    public void incrementeStade(){
        stade += 1 ;
    }

    /**
     * @return le gain de base de la Merveille
     */
    public EnumRessources getGain() {
        return gain;
    }


    /**
     * @return l'ArrayList des Carte associées pour chaque étage de la merveille
     */
    public List<Carte> getEtape() {
        return etape;
    }


    /**
     * Redefinition de la la méthode equals
     * @param obj un objet à comparer
     * @return TRUE si égale / False sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Merveille) {
            Merveille merveille = (Merveille) obj;
            return this.nom.equals(merveille.nom);
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
