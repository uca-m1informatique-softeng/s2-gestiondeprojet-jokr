package sw_aventure.seven_wonders;

import sw_aventure.joueur.Joueur;
import sw_aventure.objetjeu.*;



public interface FacadeMoteur {

    public static boolean permisDeConstruction(Carte carte, Inventaire sInventaire, Inventaire gauche, Inventaire droite, Plateau plateau) {
        Construction construction = new Construction();
        return construction.permisDeConstruction(carte, sInventaire, gauche, droite, plateau);
    }

    public static boolean laConstructionViaDoublons(Carte carte ,Inventaire sInventaire,boolean print){
        Construction construction = new Construction();
        return laConstructionViaDoublons(carte,sInventaire,print);
    }

    public static int getAge(Plateau plateau){
        return plateau.getAge();
    }

    public static int getTour(Plateau plateau){
        return plateau.getTour();
    }

    public static Joueur joueurDroit(Plateau plateau, Joueur joueur) {
        return plateau.joueurDroit(joueur);
    }

    public static Joueur joueurGauche(Plateau plateau, Joueur joueur) {
        return plateau.joueurGauche(joueur);
    }
}
