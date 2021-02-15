package joueur;

import metier.Wonder;
import objet_commun.Carte;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;

import java.util.List;

public class IAcomposite implements IA {

    IA botChameleon;

    /**
     * Appel l'IA concerné pour prendre ce choix en fonction de la merveille acquise au début du jeu
     * choix de défausser la carte ou non
     * @param j le joueur
     * @param carte la carte à défausser
     * @param plateau le plateau de jeu
     * @return True défausse / False sinon
     */
    @Override
    public boolean choixDefausse(Joueur j, Carte carte, Inventaire invJoueur, Plateau plateau) {
        Wonder merv = invJoueur.getMerveille().getNom();
        botChameleon = choixMerveilleStrat(merv);
        return botChameleon.choixDefausse(j,carte,invJoueur,plateau);
    }
    /**
     * Appel l'IA concerné pour prendre ce choix en fonction de la merveille acquise au début du jeu
     * choix de la carte a jouer dans la main
     * @param j le joueur
     * @param main les cartes de la main
     * @param plateau le plateau de jeu
     * @param prix si les cartes sont payante ou non
     * @return Index de la carte à jouer depuis la main
     */
    @Override
    public int choixMain(Joueur j, List<Carte> main,Inventaire invJoueur, Plateau plateau,boolean prix) {
        Wonder merv = invJoueur.getMerveille().getNom();
        botChameleon = choixMerveilleStrat(merv);
        return botChameleon.choixMain(j,main,invJoueur,plateau,prix);
    }

    /**
     * Appel l'IA concerné pour prendre ce choix en fonction de la merveille acquise au début du jeu
     * choix de construire ou non la merveille
     * @param j le joueur
     * @param main les cartes de la main
     * @param plateau le plateau de jeu
     * @return True construire merveille / false sinon
     */
    @Override
    public boolean choixMerveille(Joueur j, List<Carte> main,Inventaire invJoueur, Plateau plateau) {
        Wonder merv = invJoueur.getMerveille().getNom();
        botChameleon = choixMerveilleStrat(merv);
        return botChameleon.choixMerveille(j,main,invJoueur,plateau);
    }


    /**
     * Appel l'IA concerné pour prendre ce choix en fonction de la merveille acquise au début du jeu
     * choix de la carte a sacrifier pour la merveille
     * @param j le joueur
     * @param main les cartes de la mains
     * @param plateau le plateau de jeu
     * @return Index de la carte à sacrifier depuis la main pour construire la merveille
     */
    @Override
    public int choixCartePourMerveille(Joueur j, List<Carte> main,Inventaire invJoueur, Plateau plateau) {
        Wonder merv = invJoueur.getMerveille().getNom();
        botChameleon = choixMerveilleStrat(merv);
        return botChameleon.choixCartePourMerveille(j,main,invJoueur,plateau);
    }

    /**
     * Décide de la stratégie à prendre en fonction de la merveille acquise en début de partie
     * @param nomMerveille le nom de la merveille du joueur
     * @return l' IA adapté à jouer la merveille
     */
    public IA choixMerveilleStrat(Wonder nomMerveille ) {
        if (nomMerveille.equals(Wonder.EPHESOS)) {
            return new IAcivil();
        }
        if (nomMerveille.equals(Wonder.EPHESOSNUIT)){
            return new IAcivil();
        }
        if (nomMerveille.equals(Wonder.RHODOS)) {
            return new IAcivil();
        }
        if (nomMerveille.equals(Wonder.RHODOSNUIT)){
            return new IAcivil();
        }
        if (nomMerveille.equals(Wonder.BABYLON)){
            return new IAscientifique();
        }
        if (nomMerveille.equals(Wonder.BABYLONNUIT)){
            return new  IAscientifique();
        }
        if (nomMerveille.equals(Wonder.OLYMPIA)){
            return new IAcivil();
        }
        if (nomMerveille.equals(Wonder.OLYMPIANUIT)){
            return new IAcivil();
        }
        if (nomMerveille .equals(Wonder.GIZAH)) {
            return new IAcivil();
        }
        if (nomMerveille.equals(Wonder.GIZAHNUIT)){
            return new IAcivil();
        }
        if (nomMerveille.equals(Wonder.HALIKARNASSOS)) {
            return new IAcivil();
        }
        if (nomMerveille.equals(Wonder.HALIKARNASSOSNUIT)){
            return new IAscientifique();
        }
        if (nomMerveille.equals(Wonder.ALEXANDRIA)) {
            return new IAcivil();
        }
        if (nomMerveille.equals(Wonder.ALEXANDRIANUIT)){
            return new IAcivil();
        }
        return new IAcivil();
    }
}

