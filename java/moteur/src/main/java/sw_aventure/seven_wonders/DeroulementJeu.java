package sw_aventure.seven_wonders;

import objet_commun.Carte;
import exception.NegativeNumberException;
import joueur.FacadeJoueur;
import sw_aventure.objetjeu.*;
import metier.EnumRessources;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;
import utilitaire_jeu.SetInventaire;
import utils.affichage.Colors;
import utils.affichage.LoggerSevenWonders;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * toutes les étapes du jeu guerres ages etc...
 */
public class DeroulementJeu {

    private final List<SetInventaire> inv;
    private final ArrayList<Carte> paquetDefausse = new ArrayList<>();
    private List<MainJoueur> mainJoueurs = new ArrayList<>();
    private final SecureRandom r = new SecureRandom();

    public DeroulementJeu(List<SetInventaire> inv){
        this.inv = inv;
    }

    /**
     * Initialise la List "mainJoueur" en fonction du nombre de joueur
     * @param nbJoueurs le nombre de joueur
     */
    public void initMainJoueur(int nbJoueurs) {
        mainJoueurs = new ArrayList<>();
        for (int i = 0; i < nbJoueurs; i++) {
            mainJoueurs.add(new MainJoueur());
        }
    }

    /**
     * Set la graine du SecureRandom à une valeur fixe
     * @param mySeed la valeur initiale auquel on préfixe le SecureRandom
     */
    public void setTheSeed( int mySeed ) {
        r.nextBytes(new byte[8]);
        r.setSeed(mySeed);
    }


    /**
     * Méthode qui renvoie le joueur gagnant avec son nombre de points
     * @param nbjoueurs le nombre de joueur
     * @param plateau le plateau de jeu
     */
    public String gagnante(int nbjoueurs, Plateau plateau) {
        int[] liste = new int[nbjoueurs];
        List<Inventaire> joueurs = plateau.getListeInventaire();
        for(int i = 0 ; i<nbjoueurs ; i++){
            for (SetInventaire s : inv) {
                if(s.getId()==i) {
                    liste[s.getId()] = s.compteFinalScore(plateau.joueurGauche(s),plateau.joueurDroit(s), false);
                    joueurs.add(s);
                }
            }
        }
        return decisionGagnant(liste,joueurs);
    }

    /**
     * Décide de qui est le gagnant en fonction des points et des pièces, en cas d'égalité renvoie un au hasard
     * @param liste tableau de joueurs
     * @param joueurs liste des joueurs
     */
    public String decisionGagnant(int[] liste , List<Inventaire> joueurs ){
        int max = 0;
        int piece = 0;
        ArrayList<Inventaire> gagnant = new ArrayList<>();
        for (int i = 0; i < liste.length; i++) {
            if (liste[i] > max) {
                max = liste[i];
                piece = joueurs.get(i).getValue(EnumRessources.PIECE);
                gagnant.clear();
                gagnant.add(joueurs.get(i));
            }else if(liste[i] == max && joueurs.get(i).getValue(EnumRessources.PIECE)>piece) {
                piece = joueurs.get(i).getValue(EnumRessources.PIECE);
                gagnant.clear();
                gagnant.add(joueurs.get(i));
            }else if(liste[i] == max && joueurs.get(i).getValue(EnumRessources.PIECE)==piece) {
                gagnant.add(joueurs.get(i));
            }
        }
        int rand = r.nextInt(gagnant.size());
        LoggerSevenWonders.ajoutln(Colors.gJaune("Le Vainqueur est ") + gagnant.get(rand).getJoueurName() + Colors.gJaune(" avec " + max + " points !!!"));
        for (SetInventaire s : inv) {
            if (s.getUrl() == gagnant.get(rand).getUrl())
                s.getSac().put(EnumRessources.VICTOIRETOTAL, 1);
            else s.getSac().put(EnumRessources.VICTOIRETOTAL, 0);
        }
        return gagnant.get(rand).getJoueurName();
    }

    /**
     * @return toutes les mains des joueurs
     */
    public List<MainJoueur> getMainJoueurs() {
        return mainJoueurs;
    }


    /**
     * @return la liste des setInventaire
     */
    public List<SetInventaire> getSetInventaire() {
        return inv;
    }


    /**
     * Méthode qui permet de distribuer les cartes
     * @param paquetCarte le paquet à distribuer
     */
    public void distribution(List<Carte> paquetCarte) {
        Collections.shuffle(paquetCarte);
        Collections.shuffle(paquetCarte);
        //Collections.shuffle(paquetCarte,new Random(146));
        for (int i = 6; i >= 0; i--) {
            for (MainJoueur main : mainJoueurs) {
                main.add(paquetCarte.get(0));
                paquetCarte.remove(0);
            }
        }
    }

    /**
     * Echange la main des joueurs :
     * On décale d'un index toute les mains dans la List "mainJoueur".
     * Pour ce faire on supprime le dernier élément de la liste puis
     * on le rajoute en début de liste.
     * @param age l'age actuel
     */

    public void echangerMain(int age) {
        if (age == 2) {
            MainJoueur temp = mainJoueurs.remove(0);
            mainJoueurs.add(temp);
        }
        else{
            MainJoueur temp = mainJoueurs.remove(mainJoueurs.size() - 1);
            mainJoueurs.add(0, temp);
        }
        for (int i = 0; i < inv.size(); i++) {
            if (age == 2)
                LoggerSevenWonders.ajoutln(inv.get((i+1)%inv.size()).getJoueurName() + " donne ses cartes à " + inv.get(i).getJoueurName());
            else
                LoggerSevenWonders.ajoutln(inv.get(i).getJoueurName() + " donne ses cartes à " + inv.get((i + 1) % inv.size()).getJoueurName());
        }
    }

    /**
     * Méthode permettant de lancer un Age en fonction du nombre de joueur
     * @param age l'age actuel
     * @param nbJoueurs le nombre de joueur
     * @param plateau le plateau de jeu
     */
    public void joueAge(int age, int nbJoueurs, Plateau plateau) throws NegativeNumberException {
        plateau.restartTour();
        ActionDeJeu action = new ActionDeJeu(inv,mainJoueurs,paquetDefausse);

        for (int carteParMains = 7 ;carteParMains > 1 ; carteParMains--) {
            int tour = 7 - carteParMains+1 ;
            LoggerSevenWonders.ajoutln(Colors.gBleu("\n--------------------- DEBUT TOUR N°" + tour + " ---------------------\n"));
            plateau.incrementeTour();
            int[][] choix = action.decisionDeJeu(nbJoueurs,plateau);
            for (int j = nbJoueurs-1; j>=0; j--) {
                int i = nbJoueurs-1-j;
                SetInventaire s = inv.get(i);
                if (choix[i][0] != 1) {  // si il ne veut pas construire sa merveille
                    // jouer ou défausser une carte
                    Carte choixCarte = mainJoueurs.get(s.getId()).getMain().get(choix[i][1]);
                    if (choix[i][0]==3) { // défausser
                        s.casDefausse();
                        paquetDefausse.add(choixCarte);
                    } else { // jouer une carte
                        s.afficheChoixCarte(choixCarte);
                        action.basicConstruire(choixCarte, s, plateau);
                    }
                    mainJoueurs.get(s.getId()).getMain().remove(choix[i][1]); // retirer la carte de la main
                }



            }
            /////////////          SI QUELQU'UN PEUT JOUER UNE CARTE DE LA DEFAUSSE            ///////////////
            action.jouerLaDefausse(nbJoueurs, plateau);
            LoggerSevenWonders.ajoutln(Colors.gBleu("--------------------- FIN TOUR N°" + tour + "   ---------------------\n"));
            if (tour < 6) echangerMain(age);
        }
        /////////////          SI QUELQU'UN PEUT JOUER SA 7ème CARTE            ///////////////
        action.jouerLa7emeCarte(nbJoueurs, plateau);
    }



    /**
     * Méthode permettant de déclarer une guerre en fonction de l'époque (AGE)
     * @param age l'age actuel
     */
    public void guerre(int age) {
        LoggerSevenWonders.ajoutln(Colors.gRouge("##################### La GUERRE n°"+age+" est déclarée ######################\n"));
        int reward;
        if(age==1) {
            reward = 1;
        }
        else if(age==2) {
            reward = 3;
        }
        else{
            reward = 5;
        }
        for (int i = 0; i < inv.size(); i++) {
            LoggerSevenWonders.ajoutln(inv.get(i).getJoueurName() + " ("+ inv.get(i).getValue(EnumRessources.BOUCLIER) + " Boucliers) a engagé le combat contre " + inv.get((i + 1) % inv.size()).getJoueurName() +" (" + inv.get((i + 1) % inv.size()).getValue(EnumRessources.BOUCLIER)+" Boucliers).");
            if(inv.get(i).getValue(EnumRessources.BOUCLIER) > inv.get((i + 1) % inv.size()).getValue(EnumRessources.BOUCLIER)){
                LoggerSevenWonders.ajoutln(inv.get(i).getJoueurName() + " gagne la bataille et remporte " + reward + " Pts de Victoire.");
                LoggerSevenWonders.ajoutln(inv.get((i + 1) % inv.size()).getJoueurName() + " perd et remporte 1 Pts de Défaite\n");
                inv.get(i).increaseValue(EnumRessources.VICTOIRE, reward);
                inv.get((i + 1) % inv.size()).increaseValue(EnumRessources.DEFAITE, 1);
            }
            else if(inv.get(i).getValue(EnumRessources.BOUCLIER) < inv.get((i + 1) % inv.size()).getValue(EnumRessources.BOUCLIER)){
                LoggerSevenWonders.ajoutln(inv.get((i + 1) % inv.size()).getJoueurName() + " gagne la bataille et remporte " + reward + " Pts de Victoire.");
                LoggerSevenWonders.ajoutln(inv.get(i).getJoueurName() + " perd et remporte 1 Pts de Défaite.\n");
                inv.get((i + 1) % inv.size()).increaseValue(EnumRessources.VICTOIRE, reward);
                inv.get(i).increaseValue(EnumRessources.DEFAITE, 1);
            }
            else{
                LoggerSevenWonders.ajoutln("Egalité des Boucliers, personne ne gagne.\n");
            }
        }
        LoggerSevenWonders.ajoutln(Colors.gRouge("##################### Fin de la GUERRE n°"+age+" ######################\n"));
    }

    /**
     * lancement de la partie un fois que tout est initialisé
     * @param plateau le plateau de jeu
     * @param nbJoueurs le nombre de joueur
     */
    public void laPartie(Plateau plateau , int nbJoueurs ) throws NegativeNumberException {
        for (int age = 1; age <= 3; age++) {
            plateau.incrementeAge();
            initMainJoueur(nbJoueurs);
            LoggerSevenWonders.ajoutln(Colors.gVert("\n\n########################## DEBUT Age N°" + age + " ###########################\n"));

            GenererCarte carte = new GenererCarte(age, nbJoueurs);
            distribution(carte.getCards());

            joueAge(age, nbJoueurs, plateau);
            initMainJoueur(nbJoueurs);

            LoggerSevenWonders.ajoutln(Colors.gVert("########################## Fin Age N°" + age + " ###########################\n"));
            guerre(age);
        }
        LoggerSevenWonders.ajoutln("\n");
        LoggerSevenWonders.ajoutln(Colors.gJaune("########################## SCORE FINAL ###########################\n"));
        for (int i = 0; i < nbJoueurs; i++) {
            SetInventaire s = inv.get(i);
            s.compteFinalScore(plateau.joueurGauche(s),plateau.joueurDroit(s),true);
            LoggerSevenWonders.ajoutln("Inventaire de Cartes : " +s.getListeCarte());
            LoggerSevenWonders.ajoutln(Colors.gStandard("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
        }
        gagnante(nbJoueurs, plateau);
    }
}
