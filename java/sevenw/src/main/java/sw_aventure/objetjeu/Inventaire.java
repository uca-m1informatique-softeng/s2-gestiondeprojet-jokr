package sw_aventure.objetjeu;

import objet_commun.Merveille;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import sw_aventure.seven_wonders.Plateau;
import utils.affichage.Colors;
import sw_aventure.joueur.Joueur;
import utils.affichage.LoggerSevenWonders;
import java.util.*;


/**
 * Classe inventaire qui regroupe dans un dictionnaire les ressources et la quantité de ces ressources qu'un joueur possède
 */
public class Inventaire {
    protected Map<EnumRessources, Integer> sac = new EnumMap<>(EnumRessources.class);
    protected Joueur joueurName;
    protected List<EnumCarte> listeCarte ;
    protected Merveille merveille ;
    protected Plateau plateau ;
    // CONSTRUCTEUR

    /**
     * Constructeur de la classe inventaire prenant l'ID d'un joueur, l'IA et son Nom
     * @param id l'ID du joueur
     * @param ia l'IA du joueur
     * @param name le nom du joueur
     */

    public Inventaire(int id, Strategy ia, String name) {  // Dictionnaire
        this.joueurName = new Joueur(id,ia,name,this); // création du joueur
        listeCarte = new ArrayList<>();
        initSac();
    }

    /**
     * Initialise le Sac
     */
    protected void initSac() {
        // MERVEILLE
        sac.put(EnumRessources.BONUSAGECOULEUR,0);
        sac.put(EnumRessources.BONUSDEFAUSSEG,0);
        sac.put(EnumRessources.BONUSDEFAUSSEG1,0);
        sac.put(EnumRessources.BONUSDEFAUSSEG2,0);
        sac.put(EnumRessources.BONUSCARTEAGEG2P,0);
        sac.put(EnumRessources.BONUSCARTEAGEG3P,0);
        sac.put(EnumRessources.BONUS7CARTEMAIN,0);
        sac.put(EnumRessources.STADE, 0);

        // RESSOURCES DE BASE
        sac.put(EnumRessources.SCORE, 0);
        sac.put(EnumRessources.PIECE , 3);
        sac.put(EnumRessources.BOIS,0);
        sac.put(EnumRessources.PIERRE,0);
        sac.put(EnumRessources.ARGILE,0);
        sac.put(EnumRessources.MINERAI,0);
        sac.put(EnumRessources.TISSU,0);
        sac.put(EnumRessources.VERRE, 0);
        sac.put(EnumRessources.PAPYRUS,0);
        sac.put(EnumRessources.BOUCLIER, 0);
        sac.put(EnumRessources.COMPAS,0);
        sac.put(EnumRessources.ROUE,0);
        sac.put(EnumRessources.PDR,0);

        // RESSOURCE DE GUERRE
        sac.put(EnumRessources.VICTOIRE, 0);
        sac.put(EnumRessources.DEFAITE, 0);


        // BONUS CARTE JAUNE AGE 1
        sac.put(EnumRessources.REDMARRONDROITE, 0);
        sac.put(EnumRessources.REDMARRONGAUCHE,0);
        sac.put(EnumRessources.REDGRISDROITEGAUCHE,0);

        // BONUS CARTE MARRON AGE 1
        sac.put(EnumRessources.MULTIPA,0);
        sac.put(EnumRessources.MULTIBP,0);
        sac.put(EnumRessources.MULTIAM,0);
        sac.put(EnumRessources.MULTIBM,0);
        sac.put(EnumRessources.MULTIBA,0);
        sac.put(EnumRessources.MULTIPM,0);

        // BONUS CARTE JAUNE AGE 2
        sac.put(EnumRessources.MULTIBPAM,0);
        sac.put(EnumRessources.MULTIVPT,0);
        sac.put(EnumRessources.BONUS1MDJG,0);
        sac.put(EnumRessources.BONUS2GDJG,0);

        // BONUS CARTE JAUNE AGE 3
        sac.put(EnumRessources.BONUS11J,0);
        sac.put(EnumRessources.BONUS11M,0);
        sac.put(EnumRessources.BONUS22G,0);
        sac.put(EnumRessources.BONUS31R,0);
        sac.put(EnumRessources.BONUS31MERVEILLE,0);

        // BONUS CARTE VIOLETTE
        sac.put(EnumRessources.VBONUS1M,0);
        sac.put(EnumRessources.VBONUS2G,0);
        sac.put(EnumRessources.VBONUS1B,0);
        sac.put(EnumRessources.VBONUS1J,0);
        sac.put(EnumRessources.VBONUS1R,0);
        sac.put(EnumRessources.VBONUS1V,0);
        sac.put(EnumRessources.VBONUSMGV,0);
        sac.put(EnumRessources.VBONUS1MERVEILLE,0);
        sac.put(EnumRessources.VBONUS7COMPLETMERVEILLE,0);
        sac.put(EnumRessources.BONUSCPR,0);

        // COULEURS de CARTES
        sac.put(EnumRessources.MARRON,0);
        sac.put(EnumRessources.GRISE,0);
        sac.put(EnumRessources.BLEUE,0);
        sac.put(EnumRessources.JAUNE,0);
        sac.put(EnumRessources.ROUGE,0);
        sac.put(EnumRessources.VERTE,0);
        sac.put(EnumRessources.VIOLETTE,0);
    }


    //METHODES

    /**
     * Crée une liste avec les objets importants à afficher pour chaque joueur dans un but d'affichage lisible dans la console
     * @return inv la liste des objets de l'inventaire du joueur
     */
    public List<String> printInventaire(){
        ArrayList<String> inv = new ArrayList<>();
        inv.add(Colors.gJaune("Pièce\t:\t")+ getValue(EnumRessources.PIECE));
        inv.add(Colors.gStandard("Bois\t:\t")+ getValue(EnumRessources.BOIS));
        inv.add(Colors.gStandard("Pierre\t:\t")+ getValue(EnumRessources.PIERRE));
        inv.add(Colors.gStandard("Argile\t:\t")+ getValue(EnumRessources.ARGILE));
        inv.add(Colors.gStandard("Minerai\t:\t")+ getValue(EnumRessources.MINERAI));
        inv.add(Colors.gStandard("Tissu\t:\t")+ getValue(EnumRessources.TISSU));
        inv.add(Colors.gStandard("Verre\t:\t")+ getValue(EnumRessources.VERRE));
        inv.add(Colors.gStandard("Papyrus\t:\t")+ getValue(EnumRessources.PAPYRUS));
        inv.add(Colors.gBleu("Score\t:\t")+ getValue(EnumRessources.SCORE));
        inv.add(Colors.gRouge("Bouclier:\t")+ getValue(EnumRessources.BOUCLIER));
        inv.add(Colors.gVert("Compas\t:\t")+ getValue(EnumRessources.COMPAS));
        inv.add(Colors.gVert("Roue\t:\t")+ getValue(EnumRessources.ROUE));
        inv.add(Colors.gVert("Rosette\t:\t")+ getValue(EnumRessources.PDR));
        inv.add(Colors.gRouge("P.Victoire:\t")+ getValue(EnumRessources.VICTOIRE));
        inv.add(Colors.gRouge("P.Défaite:\t")+ getValue(EnumRessources.DEFAITE));
        return inv;
    }

    /**
     * Méthode permettant d'afficher l'inventaire du joueur
     */
    public void afficheInventaire() {
        LoggerSevenWonders.ajoutln("Inventaire du joueur " + getJoueur().getName() + " : \n");
        List<String> inv = printInventaire();
        int acc  = 0 ;
        for(int i = 0;i<3;i++){
            for(int j=0;j<5;j++){
                LoggerSevenWonders.ajout(Colors.itStandard(inv.get(acc)+"\t\t"));
                acc++;
            }
            LoggerSevenWonders.ajoutln("");
        }
    }

    /** Méthode permettant de récupérer le score d'un joueur
     * @param plateau le plateau de jeu
     * @param afficher affiche ou non les prints
     * @return le score d'un joueur
     */
    public int compteFinalScore(Plateau plateau, boolean afficher) {

        int sum ;
        int score = getValue(EnumRessources.SCORE);
        int piece = getValue(EnumRessources.PIECE)/3;
        int guerre = getValue(EnumRessources.VICTOIRE);
        guerre -= getValue(EnumRessources.DEFAITE);
        int carteBonus = compteBonus(plateau);
        int scientifique = compteScientifique();
        sum=score+piece+guerre+scientifique+carteBonus;
        sac.put(EnumRessources.SCOREPIECE, piece);
        sac.put(EnumRessources.BONUSCARTE, carteBonus);
        sac.put(EnumRessources.BONUSSCIENTIFIQUE, compteScientifique());
        sac.put(EnumRessources.SCOREFINAL, sum);
        if(afficher) {
            afficheInventaire();
            LoggerSevenWonders.ajoutln("\n" + joueurName.getName() + " ayant déjà " + score + " points grâce à son" + Colors.gBleu(" score") + " obtient :\n"
                    + "\t" + piece + "\t points grâce à ses" + Colors.gJaune(" pièces") + ", \n"
                    + "\t" + guerre + "\t points via les jetons de" + Colors.gRouge(" guerre") + " Victoire/défaite, \n"
                    + "\t" + scientifique + "\t points de ses combos " + Colors.gVert("scientifiques") + "\n"
                    + "\t" + carteBonus + "\t venant du cumul des bâtiments " + Colors.gViolet("bonus") + " !\n"
                    + joueurName.getName() + " à au total " + sum + " points !\n");
        }
        return sum;
    }

    /**
     * Calcul tous les points obtenues par les cartes Bonus
     * @param plateau le plateau de jeu
     * @return le total obtenu
     */
    public int compteBonus(Plateau plateau){
        int acc = 0 ;
        Inventaire gauche = plateau.joueurGauche(this.joueurName).getInv();
        Inventaire droit = plateau.joueurDroit(this.joueurName).getInv();

        if(getValue(EnumRessources.BONUS11J)!=0){
            acc += getValue(EnumRessources.JAUNE);
        }
        if(getValue(EnumRessources.BONUS11M)!=0){
            acc += getValue(EnumRessources.MARRON);
        }
        if(getValue(EnumRessources.BONUS22G)!=0){
            acc += (2*getValue(EnumRessources.GRISE));
        }
        if(getValue(EnumRessources.BONUS31R)!=0){
            acc += getValue(EnumRessources.ROUGE);
        }
        if(getValue(EnumRessources.BONUS31MERVEILLE)!=0){
            acc += merveille.getStade();
        }
        if(getValue(EnumRessources.VBONUS1M)!=0){
            acc += droit.getValue(EnumRessources.MARRON) + gauche.getValue(EnumRessources.MARRON);
        }
        if(getValue(EnumRessources.VBONUS2G)!=0){
            acc += 2*(gauche.getValue(EnumRessources.GRISE) + droit.getValue(EnumRessources.GRISE));
        }
        if(getValue(EnumRessources.VBONUS1B)!=0){
            acc += gauche.getValue(EnumRessources.BLEUE) + droit.getValue(EnumRessources.BLEUE);
        }
        if(getValue(EnumRessources.VBONUS1J)!=0){
            acc += gauche.getValue(EnumRessources.JAUNE) + droit.getValue(EnumRessources.JAUNE);
        }
        if(getValue(EnumRessources.VBONUS1R)!=0){
            acc += gauche.getValue(EnumRessources.ROUGE) + droit.getValue(EnumRessources.ROUGE);
        }
        if(getValue(EnumRessources.VBONUS1V)!=0){
            acc += gauche.getValue(EnumRessources.VERTE) + droit.getValue(EnumRessources.VERTE);
        }
        if(getValue(EnumRessources.VBONUSMGV)!=0){
            acc += getValue(EnumRessources.MARRON) + getValue(EnumRessources.GRISE) + getValue(EnumRessources.VIOLETTE);
        }
        if(getValue(EnumRessources.VBONUS1MERVEILLE)!=0){
            acc += merveille.getStade() + gauche.merveille.getStade() + droit.merveille.getStade();
        }
        if(getValue(EnumRessources.VBONUS7COMPLETMERVEILLE)!=0 && merveille.getStade()==merveille.getEtape().size()) {
            acc += 7;
        }
        return acc;
    }

    /**
     * Compte les points obtenus par les ressources scientifiques
     * @return resultatMAX
     */
    public int compteScientifique(){
        int compas = getValue(EnumRessources.COMPAS);
        int roue = getValue(EnumRessources.ROUE);
        int pdr = getValue(EnumRessources.PDR);
        int resultatMAX ;

        ///////////////   ON CALCUL SANS BONUS   //////////////////////
        resultatMAX = calculScientifique(compas,roue,pdr);

        /////////////////      SI    ON    A    UN    BONUS      /////////////////////
        if(getValue(EnumRessources.BONUSCPR) == 1) {
            return bonusScientifique1(resultatMAX,compas,roue,pdr,1);
        } else if(getValue(EnumRessources.BONUSCPR) == 2) {
           return bonusScientifique2(resultatMAX,compas,roue,pdr);
        }
        return resultatMAX;
    }

    /**
     * Calcul le resultat Max obtenable avec un bonus scientifique
     * @param resultatMAX le resultat obtenu avant les bonus
     * @param compas le nombre de compas
     * @param roue le nombre de roue
     * @param pdr le nombre de pierre de rosette
     * @param nbBonus le nombre fois que le joueur a de bonus
     * @return le resultat max avec un bonus scientifique
     */
    public int bonusScientifique1(int resultatMAX, int compas,  int roue , int pdr, int nbBonus){
        int calculBonus ;
        //////////// TEST DE RAJOUTER UN COMPAS //////////////
        calculBonus = calculScientifique(compas+nbBonus,roue,pdr);
        if(calculBonus>resultatMAX){
            resultatMAX = calculBonus ;
        }

        //////////// TEST DE RAJOUTER UNE ROUE //////////////
        calculBonus = calculScientifique(compas,roue+nbBonus,pdr);
        if(calculBonus>resultatMAX){
            resultatMAX = calculBonus ;
        }

        //////////// TEST DE RAJOUTER UNE PIERRE DE ROSETTE //////////////
        calculBonus = calculScientifique(compas,roue,pdr+nbBonus);
        if(calculBonus>resultatMAX){
            resultatMAX = calculBonus ;
        }
        return resultatMAX ;
    }


    /**
     * Calcul le resultat Max obtenable avec deux bonus scientifique
     * @param resultatMAX le résultat max obtenu avant bonus
     * @param compas le nombre de compas
     * @param roue le nombre de roue
     * @param pdr le nombre de pierre de rosette
     * @return le resultat max obtenu avec 2 bonus scientifiques
     */

    public int bonusScientifique2(int resultatMAX, int compas,  int roue , int pdr ){
        int calculBonus ;
        //////////// TEST DE RAJOUTER 2 RESSOURCES IDENTIQUES //////////////
        resultatMAX = bonusScientifique1(resultatMAX,compas,roue,pdr,2);

        //////////// TEST DE RAJOUTER 1 COMPAS & 1 ROUE //////////////
        calculBonus = calculScientifique(compas + 1, roue + 1, pdr);
        if (calculBonus > resultatMAX) {
            resultatMAX = calculBonus;
        }

        //////////// TEST DE RAJOUTER 1 ROUE & 1 PIERRE DE ROSETTE //////////////
        calculBonus = calculScientifique(compas, roue + 1, pdr + 1);
        if (calculBonus > resultatMAX) {
            resultatMAX = calculBonus;
        }

        //////////// TEST DE RAJOUTER 1 COMPAS & 1 PIERRE DE ROSETTE //////////////
        calculBonus = calculScientifique(compas + 1, roue, pdr + 1);
        if (calculBonus > resultatMAX) {
            resultatMAX = calculBonus;
        }

        return resultatMAX ;
    }

    /**
     * Effectue les calculs pour les points scientifiques liés aux ressources compas, pierreDeRosette et roue en fin de partie
     * @param compas la quantité de ressource compas
     * @param roue la quantité de ressource roue
     * @param pierreDeRosette la quantité de ressource pierre de rosette
     * @return resultat
     */
    public int calculScientifique(int compas, int roue, int pierreDeRosette){
        int resultat = (int) (Math.pow(compas, 2) + Math.pow(roue, 2) + Math.pow(pierreDeRosette, 2));
        boolean next = true ;
        while (next) {
            if (compas > 0 && roue > 0 && pierreDeRosette > 0) {
                resultat += 7;
                compas -= 1;
                roue -= 1;
                pierreDeRosette -= 1;
            } else {
                next = false;
            }
        }
        return resultat;
    }


    //GETTER

    /**
     * @return Liste des noms de carte que possède le joueur
     */
    public List<EnumCarte> getListeCarte() {
        return listeCarte;
    }

    /**
     * @return le joueur associé à l'inventaire
     */
    public Joueur getJoueur() { return joueurName; }


    /**
     * @return Permet d'obtenir l'Id du joueur
     */
    public int getId(){
        return joueurName.getId();
    }

    /**
     * Permet d'obtenir la quantité d'une ressource donnée que le joueur possède
     * @param s Chaine de caractère (key)
     * @return la valeur associée à la chaîne
     */
    public int getValue(EnumRessources s){
        return sac.get(s);
    }

    /**
     * @return la merveille
     */
    public Merveille getMerveille() {
        return merveille;
    }


    /**
     * @return le sac du joueur (qui représente son inventaire)
     */
    public Map<EnumRessources, Integer> getSac() {
        return sac;
    }
}


