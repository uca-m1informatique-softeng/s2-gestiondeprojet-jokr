package sw_aventure.objetjeu;

import metier.Strategy;
import sw_aventure.joueur.FacadeJoueur;
import sw_aventure.seven_wonders.Plateau;
import metier.EnumRessources;
import utils.affichage.Colors;
import utils.affichage.LoggerSevenWonders;

import java.util.Arrays;
import java.util.List;

import exception.NegativeNumberException;

public class SetInventaire extends Inventaire{


    /**
     * Constructeur de setInventaire
     * @param id l'ID du joueur
     * @param ia l'IA du joueur
     * @param name le nom du joueur
     */

    public SetInventaire(int id, Strategy ia, String name) {
        super(id , ia, name);
    }

    /**
     * Permet d'augmenter la quantité d'une ressource donnée
     * @param s la ressource à modifier
     * @param n valeur à ajouter
     */
    public void increaseValue(EnumRessources s, int n) throws NegativeNumberException {
        if ( n < 0 ) {
            throw new NegativeNumberException(1);
        }
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

    public void casDefausse() throws NegativeNumberException {
        LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) +Colors.itStandard(" défausse")+" sa carte et reçoit"+Colors.gJaune(" 3 ")+Colors.itStandard("pièces")+" !\n");
        increaseValue(EnumRessources.PIECE, 3);
    }

    /**
     * Méthode d'affichage pour la console : choix de la carte par le joueur
     * @param carte la carte
     */
    public void afficheChoixCarte(Carte carte){
        String choisit = " choisit dans sa main la carte ";
        if(carte.getCouleur().equals(EnumRessources.MARRON)){
            LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + choisit + Colors.gBlanc(carte.getNom().toString()));
        }
        else if(carte.getCouleur().equals(EnumRessources.GRISE)){
            LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + choisit + Colors.gStandard(carte.getNom().toString()));
        }
        else if(carte.getCouleur().equals(EnumRessources.BLEUE)){
            LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + choisit + Colors.gBleu(carte.getNom().toString()));
        }
        else if(carte.getCouleur().equals(EnumRessources.JAUNE)){
            LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + choisit + Colors.gJaune(carte.getNom().toString()));
        }
        else if(carte.getCouleur().equals(EnumRessources.ROUGE)){
            LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + choisit + Colors.gRouge(carte.getNom().toString()));
        }
        else if(carte.getCouleur().equals(EnumRessources.VERTE)){
            LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + choisit + Colors.gVert(carte.getNom().toString()));
        }
        else{
            LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + choisit + Colors.gViolet(carte.getNom().toString()));
        }
    }

    /**
     * En cas de construction de la merveille réussite fait gagner les ressources de l'étage en question
     * @param carte la carte
     * @param defausse la défausse
     * @return le paquet de carte de la défausse (au cas ou il aurait été modifié)
     */
    public List<Carte> ressourceMerveille(Carte carte, List<Carte> defausse) throws NegativeNumberException {
        int score = 0;
        for(int i=0; i<carte.getGain().size(); i++) {
            EnumRessources ressource = carte.getGain().get(i);
            increaseValue(ressource, 1);
        }
        LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) +" gagne les ressources suivantes : " + carte.getGain().get(0).toString());
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
            LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur())+ " gagne "+ score + Colors.gJaune(" pièce(s) !\n"));
        }else{
            LoggerSevenWonders.ajoutln("\n");
        }

        return defausse;
    }


    /**
     * Permet au joueur de jouer une carte de la défausse
     * @param defausse la défausse
     * @param plateau le plateau de jeu
     * @return la défausse moins potentiellement la carte jouée
     */
    public List<Carte> jouerCarteDefausse(List<Carte> defausse, Plateau plateau) throws NegativeNumberException {
        LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + " peut jouer gratuitement une carte de la défausse !");

        List<Carte> paquetDefausse = defausse; // on copie la defausse
        boolean choisirUneCarte = true; // le joueur doit choisir une carte a jouer depuis la defausse
        while (choisirUneCarte && !paquetDefausse.isEmpty()) { // tant qu'il doit choisir et que la défausse n'est pas vide
            //int choixDuJoueur = getJoueur().jouerGratuitementDanslaDefausse(defausse, plateau); // le n° de la carte choisie
            int choixDuJoueur = FacadeJoueur.jouerGratuitementDanslaDefausse(getJoueur(),defausse, plateau); // le n° de la carte choisie
            Carte carteDefausse = defausse.get(choixDuJoueur); // la carte en question
            LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + " choisit de jouer gratuitement " + carteDefausse.getNom() + " depuis la défausse");
            if (getListeCarte().contains(carteDefausse.getNom())) { // si il possède déjà cette carte
                LoggerSevenWonders.ajoutln(Colors.gRouge("REFUSE carte déjà possédée veuillez ne choisir une autre"));
                paquetDefausse.remove(choixDuJoueur); // on l'enlève du paquet copie de la defausse pour eviter qu'il se retrompe et que cela ne crée une boucle infinie si il ne peut rien jouer
            } else { // il peut jouer cette carte
                defausse.remove(carteDefausse); // on la supprime de la vraie défausse
                ressourceCarte(carteDefausse, plateau); // on lui fait gagner les ressources de la carte
                choisirUneCarte = false ; // le joueur ne doit plus choisir de carte on sort de la boucle
            }
        }
        if(defausse.isEmpty() && choisirUneCarte){ // si la défausse est vide et si le joueur n'as pas pu choisir
            LoggerSevenWonders.ajoutln(Colors.gVert("LE JOUEUR NE PEUX RIEN JOUER DE LA DEFAUSSE !\n"));
        }else {
            LoggerSevenWonders.ajoutln("\n");
        }
        return defausse ; // on retourne la defausse modifiée
    }

    /**
     * En cas de non défausse, la carte est jouée et cette méthode est appelée pour incrémenter chaque ressources de la carte dans l'inventaire du joueur
     * @param carte la carte
     * @param plateau le plateau de jeu
     */

    public void ressourceCarte(Carte carte, Plateau plateau) throws NegativeNumberException {
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
            piecebonus(carte.getGain().get(0), plateau);
        }else {
            for (int i = 0; i < carte.getGain().size(); i++) {
                EnumRessources ressource = carte.getGain().get(i);
                increaseValue(ressource, 1);
            }
            EnumRessources gain = carte.getGain().get(0);
            if(carte.getCouleur().equals(EnumRessources.VIOLETTE)){
                LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + " gagne le bonus : " + Colors.gStandard(gain.toString()) + "\n");
            }else {
                LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + " gagne les ressources : " + carte.getGain() + "\n");
            }
        }
        ajoutCarteInv(carte);
    }

    /**
     * Attribue les pièces en fonction des bonus de cartes
     * @param bonus le bonus
     * @param plateau le plateau de jeu
     */
    public void piecebonus(EnumRessources bonus, Plateau plateau) throws NegativeNumberException {
        int gain = 0 ;
        Inventaire gauche = FacadeJoueur.getInv(plateau.joueurGauche(getJoueur()));
        Inventaire droit = FacadeJoueur.getInv(plateau.joueurDroit(getJoueur()));

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

        LoggerSevenWonders.ajoutln(FacadeJoueur.getName(getJoueur()) + " gagne le bonus : " + Colors.gStandard(bonus.toString()) + ", et gagne donc "+ gain + Colors.gJaune(" pièces !\n"));
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


