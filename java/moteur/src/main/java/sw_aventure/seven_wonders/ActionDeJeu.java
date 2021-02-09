package sw_aventure.seven_wonders;

import exception.NegativeNumberException;
import joueur.FacadeJoueur;
import metier.EnumRessources;
import objet_commun.Carte;
import sw_aventure.objetjeu.MainJoueur;
import utilitaire_jeu.SetInventaire;
import utilitaire_jeu.Construction;
import utilitaire_jeu.Plateau;
import utils.affichage.Colors;
import utils.affichage.LoggerSevenWonders;
import java.util.List;

/**
 * toutes les actions lié au jeu
 */
public class ActionDeJeu {
    protected List<SetInventaire> inv;
    protected List<MainJoueur> mainJoueurs ;
    protected Construction construction = new Construction();
    protected List<Carte> paquetDefausse ;

    /**
     * constructeur de ActionDeJeu
     * @param inv liste des Setinventaires de la partie
     * @param mainJoueurs liste des mains des joueurs
     * @param paquetDefausse le paquet de la défausse
     */
    public ActionDeJeu(List<SetInventaire> inv, List<MainJoueur> mainJoueurs, List<Carte> paquetDefausse){
        this.inv = inv;
        this.mainJoueurs = mainJoueurs;
        this.paquetDefausse = paquetDefausse ;
    }


    /**
     * permet de lancer la construction d'un bâtiment (merveille ou basique)
     * @param carte la carte à construire
     * @param s le setinventaire du joueur
     * @param plateau le plateau de jeu
     */

    public void basicConstruire(Carte carte, SetInventaire s, Plateau plateau ) throws NegativeNumberException {
        if (construction.permisDeConstruction(carte,s, plateau.joueurGauche(s), plateau.joueurDroit(s) ,plateau)){
            int precedent=0;
            int suivant=0;
            for(int set = 0 ; set < inv.size() ; set++){
                if(inv.get(set).getId() == s.getId()){
                    precedent =(set-1)%inv.size();
                    suivant = (set+1)% inv.size();
                    if (precedent<0){
                        precedent += inv.size();
                    }
                }
            }
            if (construction.permisDeConstruction(carte,s,inv.get(precedent), inv.get(suivant),plateau)){
                s.ressourceCarte(carte, plateau.joueurGauche(s),plateau.joueurDroit(s));
            }
        }else{
            LoggerSevenWonders.ajoutln(Colors.gRouge("Payement Refusé"));
            s.casDefausse();
            paquetDefausse.add(carte);
        }
    }

    /**
     * le joueur à choisit de construire sa merveille
     * return true : le joueur à construit sa merveille (échec de l'achat compris et dans ce cas là il défaussera sa carte)
     * @param carte la carte merveille à construire
     * @param s le SetInventaire du joueur
     * @param plateau le plateau de jeu
     * @return True si il a construit un étage de la merveille -ou défausse- / False sinon
     */

    public boolean merveilleConstruire(Carte carte, SetInventaire s, Plateau plateau) throws NegativeNumberException {
        if (construction.permisDeConstruction(carte,s,plateau.joueurGauche(s),plateau.joueurDroit(s),plateau)){

            LoggerSevenWonders.ajoutln(s.getJoueurName() + " décide de construire le "+ (s.getMerveille().getStade()+1)+  "e étage de sa merveille !");
            int precedent=0;
            int suivant=0;
            for(int set = 0 ; set < inv.size() ; set++){
                if(inv.get(set).getId() == s.getId()){
                    precedent =(set-1)%inv.size();
                    suivant = (set+1)% inv.size();
                    if (precedent<0){
                        precedent += inv.size();
                    }
                }
            }
            if (construction.permisDeConstruction(carte,s,inv.get(precedent), inv.get(suivant),plateau)){
                s.getMerveille().incrementeStade();
                s.increaseValue(EnumRessources.STADE,1);
                paquetDefausse = s.ressourceMerveille(carte,paquetDefausse);
                return true;
            }
        }else{
            LoggerSevenWonders.ajout(Colors.gRouge("Payement Refusé "));
            s.casDefausse();
        }
        return false;
    }

    /**
     * Appelé pour demander si le joueur veux construire sa merveille et si il lui reste des étages à construire alors rentre dans la boucle IF
     * si le payement échoue il défaussera sa carte
     * @param s le SetInventaire du joueur
     * @param plateau le plateau de jeu
     * @return True si il souhaite et qu'il peut construire sa merveille / false sinon
     */
    public boolean constructionMerveille(SetInventaire s, Plateau plateau) throws NegativeNumberException {
        Boolean constructMerveille = FacadeJoueur.jouerMerveille(s.getUrl(), mainJoueurs.get(FacadeJoueur.getId(s.getUrl())).getMain(), plateau);

        if(s.getMerveille().peutAmeliorerMerveille() && constructMerveille) {
            int pick = FacadeJoueur.constructMerveille(s.getUrl(), mainJoueurs.get(FacadeJoueur.getId(s.getUrl())).getMain(),plateau);
            Carte aConstruire = s.getMerveille().getCarteAConstruire();
            if (!merveilleConstruire(aConstruire, s , plateau)){
                paquetDefausse.add(mainJoueurs.get(FacadeJoueur.getId(s.getUrl())).getMain().get(pick));
            }
            return true;
        }
        return false ;
    }

    /**
     * permet de faire jouer la 7ème carte de la main au joueur ayant ce bonus
     * @param nbJoueurs le nombre de joueur
     * @param plateau le plateau de jeu
     */
    public void jouerLa7emeCarte(int nbJoueurs,Plateau plateau) throws NegativeNumberException {
        for (int i = 0; i < nbJoueurs; i++) {
            SetInventaire s = inv.get(i);
            if (s.getValue(EnumRessources.BONUS7CARTEMAIN) > 0) {
                LoggerSevenWonders.ajoutln(FacadeJoueur.getName(s.getUrl()) + " à le droit de jouer sa 7ème carte !");
                Carte la7emeCarte = mainJoueurs.get(FacadeJoueur.getId(s.getUrl())).getMain().get(0);
                // jouer ou défausser une carte sinon
                boolean defausse = FacadeJoueur.jouerDefausse(s.getUrl(),la7emeCarte, plateau);
                if (defausse) { // défausser
                    s.casDefausse();
                    paquetDefausse.add(la7emeCarte);
                } else { // jouer une carte
                    s.afficheChoixCarte(la7emeCarte);
                    basicConstruire(la7emeCarte, s, plateau);
                }
            }
            else{
                paquetDefausse.add(mainJoueurs.get(s.getId()).getMain().get(0));
            }
        }
    }

    /**
     * permet de faire jouer une carte de la défausse au joueur ayant ce bonus
     * @param nbJoueurs le nombre de joueur
     * @param plateau le plateau de jeu
     */
    public void jouerLaDefausse(int nbJoueurs,Plateau plateau) {
        for (int i = 0; i < nbJoueurs; i++) {
            SetInventaire s = inv.get(i);
            if (s.getValue(EnumRessources.BONUSDEFAUSSEG) > 0){
                paquetDefausse = jouerCarteDefausse(s,paquetDefausse, plateau);
                s.decreaseValue(EnumRessources.BONUSDEFAUSSEG,1);
            }
            else if (s.getValue(EnumRessources.BONUSDEFAUSSEG1) > 0){
                paquetDefausse = jouerCarteDefausse(s,paquetDefausse, plateau);
                s.decreaseValue(EnumRessources.BONUSDEFAUSSEG1, 1);
            }
            else if (s.getValue(EnumRessources.BONUSDEFAUSSEG2) > 0){
                paquetDefausse = jouerCarteDefausse(s,paquetDefausse, plateau);
                s.decreaseValue(EnumRessources.BONUSDEFAUSSEG2,1);

            }
        }
    }

    /**
     * rassemble les décisions de jeu pour le tour (construire,construire merveille, défausser)
     * cela permet de les faire choisir tous avant que quelqu'un ne commence à jouer
     * @param nbJoueurs le nombre de joueur
     * @param plateau le plateau de jeu
     * @return le tableau des décisions des joueurs ainsi que les cartes associées
     */
    public int[][] decisionDeJeu(int nbJoueurs, Plateau plateau) throws NegativeNumberException {
        int[][] choix = new int[nbJoueurs][2] ; // table de tableau de taille 2, 1ère case (3 valeurs possibles 1 merveille, 2 construire et 3 défausser)
        //                               2ème case (le numéro de la carte à jouer depuis la main du joueur)
        for (int i = 0; i < nbJoueurs; i++) {
            SetInventaire s = inv.get(i);
            if(constructionMerveille(s, plateau)){
                choix[i][0] = 1 ; // construire merveille
            } else {
                int pick = FacadeJoueur.choixCarte(s.getUrl(), mainJoueurs.get(s.getId()).getMain(), plateau);
                Carte choixCarte = mainJoueurs.get(s.getId()).getMain().get(pick);
                choix[i][1] = pick; // quelle carte jouer
                boolean defausse = FacadeJoueur.jouerDefausse(s.getUrl(), choixCarte, plateau);
                if (defausse) {
                    choix[i][0] = 3 ; // défausser
                } else {
                    choix[i][0] = 2 ; // construire
                }
            }
        }
        return choix ;
    }



    /**
     * Permet au joueur de jouer une carte de la défausse
     * @param defausse la défausse
     * @param plateau le plateau de jeu
     * @return la défausse moins potentiellement la carte jouée
     */
    public List<Carte> jouerCarteDefausse(SetInventaire s , List<Carte> defausse, Plateau plateau) {
        LoggerSevenWonders.ajoutln(s.getJoueurName() + " peut jouer gratuitement une carte de la défausse !");

        List<Carte> paquetDefausse = defausse; // on copie la defausse
        boolean choisirUneCarte = true; // le joueur doit choisir une carte a jouer depuis la defausse
        while (choisirUneCarte && !paquetDefausse.isEmpty()) { // tant qu'il doit choisir et que la défausse n'est pas vide
            //int choixDuJoueur = getUrl().jouerGratuitementDanslaDefausse(defausse, plateau); // le n° de la carte choisie
            int choixDuJoueur = FacadeJoueur.jouerGratuitementDanslaDefausse(s.getUrl(),defausse, plateau); // le n° de la carte choisie
            Carte carteDefausse = defausse.get(choixDuJoueur); // la carte en question
            LoggerSevenWonders.ajoutln(s.getJoueurName() + " choisit de jouer gratuitement " + carteDefausse.getNom() + " depuis la défausse");
            if (s.getListeCarte().contains(carteDefausse.getNom())) { // si il possède déjà cette carte
                LoggerSevenWonders.ajoutln(Colors.gRouge("REFUSE carte déjà possédée veuillez ne choisir une autre"));
                paquetDefausse.remove(choixDuJoueur); // on l'enlève du paquet copie de la defausse pour eviter qu'il se retrompe et que cela ne crée une boucle infinie si il ne peut rien jouer
            } else { // il peut jouer cette carte
                defausse.remove(carteDefausse); // on la supprime de la vraie défausse
                s.ressourceCarte(carteDefausse, plateau.joueurGauche(s), plateau.joueurDroit(s)); // on lui fait gagner les ressources de la carte
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

}