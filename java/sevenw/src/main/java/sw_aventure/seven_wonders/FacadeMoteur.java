package sw_aventure.seven_wonders;

import objet_commun.Carte;
import sw_aventure.joueur.Joueur;
import sw_aventure.objetjeu.*;


/**
 * Interface servant de Facade Moteur dans l'optique d'une prochaine séparation entre le Moteur-de-jeu et les Joueurs-Clients
 */
public interface FacadeMoteur {

    /**
     * Méthode appelée par le client pour connaitre la potentielle possibilité de construction d'une carte.
     * @param carte La carte à construire
     * @param sInventaire son Inventaire
     * @param gauche L'inventaire du joueur de gauche
     * @param droite L'inventaire du joueur de droite
     * @param plateau Le plateau de jeu
     * @return un boolean vis-à-vis de la possibilité de construire la carte
     */
    static boolean permisDeConstruction(Carte carte, Inventaire sInventaire, Inventaire gauche, Inventaire droite, Plateau plateau) {
        Construction construction = new Construction();
        return construction.permisDeConstruction(carte, sInventaire, gauche, droite, plateau);
    }

    /**
     * Permet de savoir si la carte est déjà en possession du joueur
     * @param carte La carte à tester
     * @param sInventaire L'inventaire du joueur
     * @param print Affichage dans la console ( ici False )
     * @return un boolean quant à la possession de la carte par le joueur
     */
    static boolean laConstructionViaDoublons(Carte carte ,Inventaire sInventaire,boolean print){
        Construction construction = new Construction();
        return construction.laConstructionViaDoublons(carte,sInventaire,print);
    }

    /**
     * Permet de connaitre l'Age actuel au cours de la partie
     * @param plateau Le plateau de jeu
     * @return L'Age actuel du jeu
     */
    static int getAge(Plateau plateau){
        return plateau.getAge();
    }

    /**
     * Permet de connaitre le numéro du tour de l'Age actuel de la partie
     * @param plateau Le plateau de jeu
     * @return Le numéro du tour actuel
     */
    static int getTour(Plateau plateau){
        return plateau.getTour();
    }

    /**
     * Permet d'avoir accès au voisin de droite du joueur appelé en paramètre
     * @param plateau Le plateau de Jeu
     * @param joueur Le Joueur auquel on souhaite avoir le voisin de droite
     * @return Le voisin de droite
     */
    static Joueur joueurDroit(Plateau plateau, Joueur joueur) {
        return plateau.joueurDroit(joueur);
    }

    /**
     * Permet d'avoir accès au joueur à gauche du joueur appelé en paramètre
     * @param plateau le plateau de Jeu
     * @param joueur Le Joueur auquel on souhaite avoir le voisin de gauche
     * @return Le voisin de gauche
     */
    static Joueur joueurGauche(Plateau plateau, Joueur joueur) {
        return plateau.joueurGauche(joueur);
    }
}
