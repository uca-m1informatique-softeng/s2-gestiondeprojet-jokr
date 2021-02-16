package utilitaire_jeu;

import objet_commun.Carte;
import objet_commun.Merveille;
import metier.EnumRessources;
import utils.affichage.Colors;
import utils.affichage.LoggerSevenWonders;

import java.util.Arrays;
import java.util.List;


public class SetInventaire extends Inventaire {


    /**
     * Constructeur de setInventaire
     * @param id l'ID du joueur
     * @param name le nom du joueur
     */

    public SetInventaire(int id,String url_player, String name) {
        super(id ,url_player, name);
    }

    /**
     * Permet d'augmenter la quantité d'une ressource donnée
     * @param s la ressource à modifier
     * @param n valeur à ajouter
     */
    public void increaseValue(EnumRessources s, int n){
        int oldValue = sac.get(s);
        sac.replace(s, oldValue + n);
    }

    /**
     * Permet de diminuer la quantité d'une ressource donnée
     * @param s la ressource à modifier
     * @param n valeur à diminuer
     * @return vrai si la décrémentation a réussi
     */
    public boolean decreaseValue(EnumRessources s, int n) {
        if(n>this.getValue(s)) {
            return false;
        }
        sac.replace(s, sac.get(s)-n);
        return true;
    }

    /**
     * En cas de décision de défausse cette méthode est appelée pour incrémenter 3 pièces à l'inventaire du joueur qui aura choisit de défausser
     */

    public void casDefausse(){
        LoggerSevenWonders.ajoutln(joueurName +Colors.itStandard(" défausse")+" sa carte et reçoit"+Colors.gJaune(" 3 ")+Colors.itStandard("pièces")+" !\n");
        increaseValue(EnumRessources.PIECE, 3);
    }

    /**
     * Méthode d'affichage pour la console : choix de la carte par le joueur
     * @param carte la carte
     */
    public void afficheChoixCarte(Carte carte){
        String choisit = " choisit dans sa main la carte ";
        if(carte.getCouleur().equals(EnumRessources.MARRON)){
            LoggerSevenWonders.ajoutln(joueurName + choisit + Colors.gBlanc(carte.getNom().toString()));
        }
        else if(carte.getCouleur().equals(EnumRessources.GRISE)){
            LoggerSevenWonders.ajoutln(joueurName + choisit + Colors.gStandard(carte.getNom().toString()));
        }
        else if(carte.getCouleur().equals(EnumRessources.BLEUE)){
            LoggerSevenWonders.ajoutln(joueurName + choisit + Colors.gBleu(carte.getNom().toString()));
        }
        else if(carte.getCouleur().equals(EnumRessources.JAUNE)){
            LoggerSevenWonders.ajoutln(joueurName + choisit + Colors.gJaune(carte.getNom().toString()));
        }
        else if(carte.getCouleur().equals(EnumRessources.ROUGE)){
            LoggerSevenWonders.ajoutln(joueurName + choisit + Colors.gRouge(carte.getNom().toString()));
        }
        else if(carte.getCouleur().equals(EnumRessources.VERTE)){
            LoggerSevenWonders.ajoutln(joueurName + choisit + Colors.gVert(carte.getNom().toString()));
        }
        else{
            LoggerSevenWonders.ajoutln(joueurName + choisit + Colors.gViolet(carte.getNom().toString()));
        }
    }

    /**
     * En cas de construction de la merveille réussite fait gagner les ressources de l'étage en question
     * @param carte la carte
     * @param defausse la défausse
     * @return le paquet de carte de la défausse (au cas ou il aurait été modifié)
     */
    public List<Carte> ressourceMerveille(Carte carte, List<Carte> defausse){
        int score = 0;
        for(int i=0; i<carte.getGain().size(); i++) {
            EnumRessources ressource = carte.getGain().get(i);
            increaseValue(ressource, 1);
        }
        LoggerSevenWonders.ajoutln(joueurName +" gagne les ressources suivantes : " + carte.getGain().get(0).toString());
        // il remporte les pièces si il y en a
        if (carte.getGain().get(0).equals(EnumRessources.BONUSDEFAUSSEG1)) {
            score = 1;
        }
        else if (carte.getGain().get(0).equals(EnumRessources.BONUSDEFAUSSEG2)) {
            score = 2;
        }
        else if (carte.getGain().get(0).equals(EnumRessources.BONUSCARTEAGEG2P)) {
            score = 2;
        }
        else if (carte.getGain().get(0).equals(EnumRessources.BONUSCARTEAGEG3P)) {
            score = 3;
        }
        if(score > 0) { // recevoir les pièces du bonus
            increaseValue(EnumRessources.SCORE, score);
            LoggerSevenWonders.ajoutln(joueurName+ " gagne "+ score + Colors.gJaune(" pièce(s) !\n"));
        }else{
            LoggerSevenWonders.ajoutln("\n");
        }

        return defausse;
    }


    /**
     * En cas de non défausse, la carte est jouée et cette méthode est appelée pour incrémenter chaque ressources de la carte dans l'inventaire du joueur
     * @param carte la carte
     */

    public void ressourceCarte(Carte carte, Inventaire gauche, Inventaire droit){
        EnumRessources couleur = carte.getCouleur();
        increaseValue(couleur,1);
        List<EnumRessources> listebonus = Arrays.asList(EnumRessources.BONUS1MDJG,
                EnumRessources.BONUS2GDJG,
                EnumRessources.BONUS11J,
                EnumRessources.BONUS11M,
                EnumRessources.BONUS22G,
                EnumRessources.BONUS31R,
                EnumRessources.BONUS31MERVEILLE);

        if(listebonus.contains(carte.getGain().get(0))){
            piecebonus(carte.getGain().get(0), gauche, droit);
        }else {
            for (int i = 0; i < carte.getGain().size(); i++) {
                EnumRessources ressource = carte.getGain().get(i);
                increaseValue(ressource, 1);
            }
            EnumRessources gain = carte.getGain().get(0);
            if(carte.getCouleur().equals(EnumRessources.VIOLETTE)){
                LoggerSevenWonders.ajoutln(joueurName + " gagne le bonus : " + Colors.gStandard(gain.toString()) + "\n");
            }else {
                LoggerSevenWonders.ajoutln(joueurName + " gagne les ressources : " + carte.getGain() + "\n");
            }
        }
        ajoutCarteInv(carte);
    }

    /**
     * Attribue les pièces en fonction des bonus de cartes
     * @param bonus le bonus
     */
    public void piecebonus(EnumRessources bonus, Inventaire gauche, Inventaire droit){
        int gain = 0 ;

        if(bonus.equals(EnumRessources.BONUS1MDJG)){
            gain = gauche.getValue(EnumRessources.MARRON)+getValue(EnumRessources.MARRON)+droit.getValue(EnumRessources.MARRON);
        }
        else if(bonus.equals(EnumRessources.BONUS2GDJG)){
            gain = 2*(gauche.getValue(EnumRessources.GRISE)+getValue(EnumRessources.GRISE)+droit.getValue(EnumRessources.GRISE));
        }
        else if(bonus.equals(EnumRessources.BONUS11J)){
            gain = getValue(EnumRessources.JAUNE);
        }
        else if(bonus.equals(EnumRessources.BONUS11M)){
            gain = getValue(EnumRessources.MARRON);
        }
        else if(bonus.equals(EnumRessources.BONUS22G)){
            gain = (2*getValue(EnumRessources.GRISE));
        }
        else if(bonus.equals(EnumRessources.BONUS31R)){
            gain = (3*getValue(EnumRessources.ROUGE));
        }
        else if(bonus.equals(EnumRessources.BONUS31MERVEILLE)){
            gain = (3*getMerveille().getStade());
        }

        LoggerSevenWonders.ajoutln(joueurName + " gagne le bonus : " + Colors.gStandard(bonus.toString()) + ", et gagne donc "+ gain + Colors.gJaune(" pièces !\n"));
        increaseValue(EnumRessources.PIECE,gain);
    }

    /**
     * Ajoute le nom de la carte à l'inventaire
     * @param carte la carte à ajouter
     */
    public void ajoutCarteInv(Carte carte){
        getListeCarte().add(carte.getNom());
    }

    /**
     * Attribue la merveille au joueur donc l'ajoute à l'inventaire
     * @param laMerveille la merveille
     */
    public void modifMerveille(Merveille laMerveille){
        merveille = laMerveille;
    }



    /** Methode servant aux tests */
    public void clear() {
        initSac();
    }

}


