package sw_aventure.joueur;

import objet_commun.Carte;
import sw_aventure.seven_wonders.FacadeMoteur;
import sw_aventure.seven_wonders.Plateau;
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
    public int choixMain(Joueur j, List<Carte> main, Plateau plateau, boolean prix){
        for(int i = 0 ; i < main.size() ; i++) {
            if (FacadeMoteur.permisDeConstruction(main.get(i), j.getInv(), FacadeMoteur.joueurGauche(plateau,j).getInv(), FacadeMoteur.joueurDroit(plateau,j).getInv(),plateau)) {
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
    public boolean choixMerveille(Joueur j, List<Carte> main, Plateau plateau){
        if(Boolean.TRUE.equals(FacadeMoteur.getMerveille(j.getInv()).peutAmeliorerMerveille())){
            for(int i = 0 ; i < main.size() ; i++) {
                if (FacadeMoteur.permisDeConstruction( FacadeMoteur.getMerveille(j.getInv()).getCarteAConstruire(),j.getInv(), FacadeMoteur.joueurGauche(plateau,j).getInv(), FacadeMoteur.joueurDroit(plateau,j).getInv(),plateau)) {
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
    public int choisirCarteDeLaDefausse(Joueur j, List<Carte> paquetDefausse, Plateau plateau){
        return choixMain(j,paquetDefausse,plateau,false);
    }

}














