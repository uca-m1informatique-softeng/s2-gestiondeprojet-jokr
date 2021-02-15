package joueur;

import objet_commun.Carte;
import utilitaire_jeu.Construction;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;

import java.util.List;


public class IAmerveille implements IA {



    /**
     * Renvoie l'index de la première carte constructible sinon renvoie 1
     * @param j le joueur
     * @param main les cartes de la main
     * @param plateau le plateau de jeu
     * @param prix si les cartes sont payantes ou non
     * @return Index de la carte à jouer dans la main
     */
    @Override
    public int choixMain(Joueur j, List<Carte> main, Inventaire invJoueur, Plateau plateau, boolean prix){
        for(int i = 0 ; i < main.size() ; i++) {
            if (Construction.permisDeConstruction(main.get(i), invJoueur, plateau.joueurGauche(invJoueur), plateau.joueurDroit(invJoueur), plateau)) {
                return i;
            }
        } return 1 ;
    }

    /**
     * renvoie si oui ou non il souhaite construire sa merveille
     * @param j joueur
     * @param main les cartes de la main
     * @param plateau le plateau de jeu
     * @return True construire / False sinon
     */
    @Override
    public boolean choixMerveille(Joueur j, List<Carte> main,Inventaire invJoueur, Plateau plateau){
        if(Boolean.TRUE.equals(invJoueur.getMerveille().peutAmeliorerMerveille())){
            for(int i = 0 ; i < main.size() ; i++) {
                if (Construction.permisDeConstruction( invJoueur.getMerveille().getCarteAConstruire(),invJoueur, plateau.joueurGauche(invJoueur), plateau.joueurDroit(invJoueur),plateau)) {
                    return true; // Si l'étage de la merveille est constructible par le joueur alors il décide de construire la merveille
                }
            }
        }
        return false;
    }

    /**
     * Choisit une carte à jouer depuis la défausse
     * @param j joueur
     * @param paquetDefausse le paquet de carte de la défausse où le joueur pourra jouer une carte gratuitement
     * @param plateau le plateau de jeu
     * @return Index de la carte à jouer depuis la défausse
     */
    public int choisirCarteDeLaDefausse(Joueur j, List<Carte> paquetDefausse,Inventaire invJoueur, Plateau plateau){
        return choixMain(j,paquetDefausse,invJoueur,plateau,false);
    }

}














