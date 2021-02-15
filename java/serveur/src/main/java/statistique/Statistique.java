package statistique;

import fichier.GestionnaireDeFichier;
import metier.Data;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Wonder;
import utils.affichage.Colors;
import utils.affichage.LoggerSevenWonders;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *     Cette classe permet de gérer les statistiques pour chaque joueurs sur plusieurs parties
 * @see #nbParties
 *          Le nombre de partie
 * @see #nbJoueur
 *          Le nombre de joueur
 * @see #dataParties
 *          Les données de toutes les parties
 * @see #tabStat
 *          Tableau contenant les statistiques pour chaque joueurs
 * @see #listCarte
 *          HashMap contenant les occurrence des cartes que le joueur a acheté a chaque partie
 * @see #listCarteGagnant
 *          Liste des carte du joueur lorsqu'il a fait son meilleur score
 * @see #merveille
 *          Le nom de la merveille du joueur lorsqu'il a fait son meilleur score
 */

public class Statistique {

    private final int nbParties;
    private final int nbJoueur;
    private final List<Data[]> dataParties;
    private final double[][] tabStat;
    private final List<Map<String, Integer>> listCarte;
    private final List<Map<String, Float>> listMerveille;
    private final List<Map<String, Integer>> accuMerveille;
    private final List<String>[] listCarteGagnant;
    private final String[] merveille;


    /**
     * Constructeur pour les Statistiques des parties du jeu 7wonders
     *
     * @param nbParties   Nombre de parties
     * @param nbJoueur    Nombre de joueurs
     * @param dataParties Données de toutes les parties
     */
    public Statistique(int nbParties, int nbJoueur, List<Data[]> dataParties) {
        this.nbParties = nbParties;
        this.dataParties = dataParties;
        this.nbJoueur = nbJoueur;

        tabStat = new double[nbJoueur][20];
        listCarte = new ArrayList<>();
        listMerveille = new ArrayList<>();
        accuMerveille = new ArrayList<>();
        for (int i = 0; i < nbJoueur; i++){
            listCarte.add(listeCarte());
            listMerveille.add(listeMerveille());
            accuMerveille.add(listeAccumulateurMerveille());
        }
        listCarteGagnant = new ArrayList[nbJoueur];
        merveille = new String[nbJoueur];
    }


    /**
     * @return Le tableau des statistiques des joueurs
     */
    public double[][] getTabStat() {
        return tabStat;
    }


    /**
     * Méthode permettant de calculer les statistiques
     */
    public void calculStat() {
        for (int partie = 0; partie < nbParties; partie++) {
            for (int joueur = 0; joueur < nbJoueur; joueur++) {
                tabStat[joueur][0] += dataParties.get(partie)[joueur].getValue(EnumRessources.VICTOIRETOTAL.toString());
                tabStat[joueur][1] += dataParties.get(partie)[joueur].getValue(EnumRessources.SCOREFINAL.toString());
                tabStat[joueur][2] += dataParties.get(partie)[joueur].getValue(EnumRessources.SCORE.toString());
                tabStat[joueur][7] += dataParties.get(partie)[joueur].getValue(EnumRessources.SCOREPIECE.toString());
                tabStat[joueur][3] += dataParties.get(partie)[joueur].getValue(EnumRessources.BONUSSCIENTIFIQUE.toString());
                tabStat[joueur][4] += dataParties.get(partie)[joueur].getValue(EnumRessources.VICTOIRE.toString());
                tabStat[joueur][5] += dataParties.get(partie)[joueur].getValue(EnumRessources.DEFAITE.toString());
                tabStat[joueur][8] += dataParties.get(partie)[joueur].getValue(EnumRessources.BONUSCARTE.toString());
                tabStat[joueur][9] += dataParties.get(partie)[joueur].getValue(EnumRessources.STADE.toString());
                if (partie == 0 || tabStat[joueur][6] < dataParties.get(partie)[joueur].getValue(EnumRessources.SCOREFINAL.toString())) {
                    tabStat[joueur][6] = dataParties.get(partie)[joueur].getValue(EnumRessources.SCOREFINAL.toString());
                    merveille[joueur] = dataParties.get(partie)[joueur].getMerveille();
                    listCarteGagnant[joueur] = dataParties.get(partie)[joueur].getCartes();
                }
                int lastValue = accuMerveille.get(joueur).get(dataParties.get(partie)[joueur].getMerveille());
                accuMerveille.get(joueur).replace(dataParties.get(partie)[joueur].getMerveille(), lastValue + 1);
                if(dataParties.get(partie)[joueur].getValue(EnumRessources.VICTOIRETOTAL.toString())==1) {
                    float oldvaluemerveille = listMerveille.get(joueur).get(dataParties.get(partie)[joueur].getMerveille());
                    listMerveille.get(joueur).replace(dataParties.get(partie)[joueur].getMerveille(), oldvaluemerveille + 1);
                }

                for (int i = 0; i < dataParties.get(partie)[joueur].getCartes().size(); i++) {
                    String carte = dataParties.get(partie)[joueur].getCartes().get(i);
                    if (listCarte.get(joueur).containsKey(carte)) {
                        int oldValue = listCarte.get(joueur).get(carte);
                        listCarte.get(joueur).replace(carte, oldValue + 1);
                    } else listCarte.get(joueur).put(carte, 1);
                }

            }
        }
    }


    /**
     * Méthode permettant de récupérer les statistiques
     * @param color Permet d'activer ou désactiver la couleur
     * @return StringBuilder
     */
    public StringBuilder recupeStat(boolean color) {
        String tiret = "\t- ";
        Colors.setColor(color);
        StringBuilder res = new StringBuilder();
        res.append(Colors.gBleu("\n############## Statistiques pour "+ nbJoueur + " joueurs sur " + nbParties + " parties ##############\n\n"));
        res.append(descriptifIA(color));
        for (int i = 0; i < nbJoueur; i++) {

            res.append(Colors.iRouge("\n\n##### Statistiques de " + dataParties.get(0)[i].getNomJoueur() + " #####\n"));
            res.append(Colors.gViolet(dataParties.get(0)[i].getNomJoueur()) + " (" + dataParties.get(0)[i].getStrategie() + ") a remporté au total " + (int) tabStat[i][0] + " parties sur " + nbParties + ", ");

            res.append("soit un taux de victoire de " + (tabStat[i][0] * 100 / nbParties) + "%.\n");
            res.append("Il a obtenue au maximum " + (int) tabStat[i][6] + " points avec la merveille " + merveille[i] + ", et avec les cartes :\n\t" + listCarteGagnant[i].toString().replace("[", "").replace("]", "") + ".\n\n");

            res.append("Il a construit en moyenne " + Colors.gJaune(tabStat[i][9] / nbParties + "")+" étages de sa merveille.\n\n");

            res.append("De plus, il a obtenue en moyenne :\n");
            res.append(tiret + "Un score total de " + (tabStat[i][1] / nbParties) + Colors.gStandard(" points.\n"));
            res.append(tiret + (tabStat[i][7] / nbParties) + " points grâce à ses " + Colors.gJaune("Pièces.\n"));
            res.append(tiret + (tabStat[i][2] / nbParties) + " points grâce aux " + Colors.gBleu("Bâtiments civils.\n"));
            res.append(tiret + (tabStat[i][3] / nbParties) + " points " + Colors.gVert("Scientifique.\n"));
            res.append(tiret + (tabStat[i][4] / nbParties) + " points de " + Colors.gRouge("Victoire de guerre.\n"));
            res.append(tiret + (tabStat[i][5] / nbParties) + " points de " + Colors.gRouge("Défaite guerre.\n"));
            res.append(tiret + (tabStat[i][8] / nbParties) + " points grâce aux " + Colors.gViolet("cartes bonus.\n\n"));


            res.append("Et au cours des " + nbParties + " parties, son taux d'achat par carte est le suivant :\n");

            List<String> inv = printInventaireCarte(listCarte.get(i));
            int acc = 0;
            for (int p = 0; p < 11; p++) {
                for (int j = 0; j < 7; j++) {
                    res.append(Colors.itStandard(inv.get(acc) + "%\t\t"));
                    acc++;
                }
                res.append("\n");
            }



            res.append("\n");

            List<String> merveilles = printListeMerveille(listMerveille.get(i),accuMerveille.get(i));
            int acc2 = 0;
            for (int k = 0; k < 2; k++) {
                for (int l = 0; l < 7; l++) {
                    res.append(Colors.itStandard(merveilles.get(acc2) + "%\t\t"));
                    acc2++;
                }
                res.append("\n");
            }
        }
        return res;
    }


    /**
     * Méthode permettant d'afficher les statistiques
     * @param gestionnaireDeFichier le gestionnaire de fichier
     * @throws java.io.IOException renvoie une exception
     */
    public void afficheStat(GestionnaireDeFichier gestionnaireDeFichier) throws IOException {
        LoggerSevenWonders.show(recupeStat(true));

        DateFormat format = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        String path = "java/serveur/output/partie_multiple/" + nbJoueur + " Joueurs/";
        String file = format.format(date) + ".txt";
        gestionnaireDeFichier.ecrireDansFichier(path, file, recupeStat(false).toString());
    }


    /**
     * Méthode permettant de récupérer une description des IA
     * @param color Permet d'activer ou désactiver la couleur
     * @return StringBuilder
     */
    public StringBuilder descriptifIA(boolean color) {
        Colors.setColor(color);
        String iA = "   - IA ";
        StringBuilder res = new StringBuilder();
        res.append("Description des IA :\n");
        res.append(iA + Colors.gStandard("Random")+ "       : IA prenant des décisions aléatoires.\n");
        res.append(iA + Colors.gStandard("Merveille") + "    : IA construisant sa merveille en priorité.\n");
        res.append(iA + Colors.gVert("Scientifique") + " : IA se concentrant sur les points scientifiques.\n");
        res.append(iA + Colors.gBleu("Civil") + "        : IA construisant des bâtiments civils en priorité.\n");
        res.append(iA + Colors.gJaune("Monétaire") + "    : IA essayant de gagner des points grâce à ses pièces et des bonus.\n");
        res.append(iA + Colors.gStandard("Composite") + "    : IA adoptant une stratégie différente en fonction de sa merveille.\n");
        res.append(iA + Colors.gRouge("Militaire") + "    : IA se concentrant sur l'achat de bouclier pour gagner les guerres.\n");
        res.append(iA + Colors.gStandard("Ambitieuse") + "   : IA s’adaptant aux stratégies des autres joueurs et en jouant des coups pour les contrer, toute en s’assurant un minimum de points.\n\n");

        return res;
    }


    /**
     * Méthode permettant de récupérer une HashMap contenant les noms des cartes
     * @return Map des nom de carte initialisé à 0
     */
    public Map<String, Integer> listeCarte() {
        Map<String, Integer> sac = new HashMap<>();
        sac.put(EnumCarte.M1.toString(),0);
        sac.put(EnumCarte.M2.toString(),0);
        sac.put(EnumCarte.M3.toString(),0);
        sac.put(EnumCarte.M4.toString(),0);
        sac.put(EnumCarte.M5.toString(),0);
        sac.put(EnumCarte.M6.toString(),0);
        sac.put(EnumCarte.M7.toString(),0);
        sac.put(EnumCarte.M8.toString(),0);
        sac.put(EnumCarte.M9.toString(),0);
        sac.put(EnumCarte.M10.toString(),0);
        sac.put(EnumCarte.M11.toString(),0);
        sac.put(EnumCarte.M12.toString(),0);
        sac.put(EnumCarte.M13.toString(),0);
        sac.put(EnumCarte.M14.toString(),0);

        sac.put(EnumCarte.G1.toString(),0);
        sac.put(EnumCarte.G2.toString(),0);
        sac.put(EnumCarte.G3.toString(),0);

        sac.put(EnumCarte.B1.toString(),0);
        sac.put(EnumCarte.B2.toString(),0);
        sac.put(EnumCarte.B3.toString(),0);
        sac.put(EnumCarte.B4.toString(),0);
        sac.put(EnumCarte.B5.toString(),0);
        sac.put(EnumCarte.B6.toString(),0);
        sac.put(EnumCarte.B7.toString(),0);
        sac.put(EnumCarte.B8.toString(),0);
        sac.put(EnumCarte.B9.toString(),0);
        sac.put(EnumCarte.B10.toString(),0);
        sac.put(EnumCarte.B11.toString(),0);
        sac.put(EnumCarte.B12.toString(),0);
        sac.put(EnumCarte.B13.toString(),0);

        sac.put(EnumCarte.J1.toString(),0);
        sac.put(EnumCarte.J2.toString(),0);
        sac.put(EnumCarte.J3.toString(),0);
        sac.put(EnumCarte.J4.toString(),0);
        sac.put(EnumCarte.J5.toString(),0);
        sac.put(EnumCarte.J6.toString(),0);
        sac.put(EnumCarte.J7.toString(),0);
        sac.put(EnumCarte.J8.toString(),0);
        sac.put(EnumCarte.J9.toString(),0);
        sac.put(EnumCarte.J10.toString(),0);
        sac.put(EnumCarte.J11.toString(),0);
        sac.put(EnumCarte.J12.toString(),0);
        sac.put(EnumCarte.J13.toString(),0);

        sac.put(EnumCarte.R1.toString(),0);
        sac.put(EnumCarte.R2.toString(),0);
        sac.put(EnumCarte.R3.toString(),0);
        sac.put(EnumCarte.R4.toString(),0);
        sac.put(EnumCarte.R5.toString(),0);
        sac.put(EnumCarte.R6.toString(),0);
        sac.put(EnumCarte.R7.toString(),0);
        sac.put(EnumCarte.R8.toString(),0);
        sac.put(EnumCarte.R9.toString(),0);
        sac.put(EnumCarte.R10.toString(),0);
        sac.put(EnumCarte.R11.toString(),0);
        sac.put(EnumCarte.R12.toString(),0);

        sac.put(EnumCarte.V1.toString(),0);
        sac.put(EnumCarte.V2.toString(),0);
        sac.put(EnumCarte.V3.toString(),0);
        sac.put(EnumCarte.V4.toString(),0);
        sac.put(EnumCarte.V5.toString(),0);
        sac.put(EnumCarte.V6.toString(),0);
        sac.put(EnumCarte.V7.toString(),0);
        sac.put(EnumCarte.V8.toString(),0);
        sac.put(EnumCarte.V9.toString(),0);
        sac.put(EnumCarte.V10.toString(),0);
        sac.put(EnumCarte.V11.toString(),0);
        sac.put(EnumCarte.V12.toString(),0);

        sac.put(EnumCarte.P1.toString(), 0);
        sac.put(EnumCarte.P2.toString(), 0);
        sac.put(EnumCarte.P3.toString(), 0);
        sac.put(EnumCarte.P4.toString(), 0);
        sac.put(EnumCarte.P5.toString(), 0);
        sac.put(EnumCarte.P6.toString(), 0);
        sac.put(EnumCarte.P7.toString(), 0);
        sac.put(EnumCarte.P8.toString(), 0);
        sac.put(EnumCarte.P9.toString(), 0);
        sac.put(EnumCarte.P10.toString(), 0);

        return sac ;
    }
    /**
     * Méthode permettant de récupérer une HashMap contenant les noms des cartes
     * @return Map des nom de carte initialisé à 0
     */
    public Map<String, Float> listeMerveille() {
        Map<String, Float> sac = new HashMap<>();
        sac.put(Wonder.BABYLON.toString(), (float)0);
        sac.put(Wonder.OLYMPIA.toString(), (float)0);
        sac.put(Wonder.HALIKARNASSOS.toString(),(float) 0);
        sac.put(Wonder.GIZAH.toString(), (float)0);
        sac.put(Wonder.ALEXANDRIA.toString(), (float)0);
        sac.put(Wonder.RHODOS.toString(),(float) 0);
        sac.put(Wonder.EPHESOS.toString(), (float)0);

        sac.put(Wonder.BABYLONNUIT.toString(),(float) 0);
        sac.put(Wonder.OLYMPIANUIT.toString(),(float) 0);
        sac.put(Wonder.HALIKARNASSOSNUIT.toString(), (float)0);
        sac.put(Wonder.GIZAHNUIT.toString(), (float)0);
        sac.put(Wonder.ALEXANDRIANUIT.toString(), (float)0);
        sac.put(Wonder.RHODOSNUIT.toString(), (float)0);
        sac.put(Wonder.EPHESOSNUIT.toString(), (float)0);


        return sac;
    }
    /**
     * Méthode permettant de récupérer une HashMap contenant les noms des merveilles
     * @return Map des nom de merveille initialisé à 0
     */
    public Map<String, Integer> listeAccumulateurMerveille() {
        Map<String, Integer> sac = new HashMap<>();
        sac.put(Wonder.BABYLON.toString(), 0);
        sac.put(Wonder.OLYMPIA.toString(), 0);
        sac.put(Wonder.HALIKARNASSOS.toString(), 0);
        sac.put(Wonder.GIZAH.toString(), 0);
        sac.put(Wonder.ALEXANDRIA.toString(), 0);
        sac.put(Wonder.RHODOS.toString(), 0);
        sac.put(Wonder.EPHESOS.toString(), 0);

        sac.put(Wonder.BABYLONNUIT.toString(), 0);
        sac.put(Wonder.OLYMPIANUIT.toString(), 0);
        sac.put(Wonder.HALIKARNASSOSNUIT.toString(), 0);
        sac.put(Wonder.GIZAHNUIT.toString(), 0);
        sac.put(Wonder.ALEXANDRIANUIT.toString(), 0);
        sac.put(Wonder.RHODOSNUIT.toString(), 0);
        sac.put(Wonder.EPHESOSNUIT.toString(), 0);


        return sac;
    }


    /**
     * Crée une liste avec les objets importants à afficher pour chaque joueur dans un but d'affichage lisible dans la console
     * @param map la map des achats de cartes
     * @return inv la liste des objets de l'inventaire du joueur
     */
    public List<String> printInventaireCarte(Map<String,Integer> map){
        ArrayList<String> inv = new ArrayList<>();
        inv.add(Colors.gStandard("E. Forestière :\t")+ map.get(EnumCarte.M1.toString())*100 / nbParties);
        inv.add(Colors.gStandard("F. Argileuse  :\t")+ map.get(EnumCarte.M2.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Filon         :\t")+ map.get(EnumCarte.M3.toString())*100 / nbParties);
        inv.add(Colors.gStandard("B. Argileux   :\t")+ map.get(EnumCarte.M4.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Cavité        :\t")+ map.get(EnumCarte.M5.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Chantier      :\t")+ map.get(EnumCarte.M6.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Excavation    :\t")+ map.get(EnumCarte.M7.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Gisement      :\t")+ map.get(EnumCarte.M8.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Mine          :\t")+ map.get(EnumCarte.M9.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Friche        :\t")+ map.get(EnumCarte.M10.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Fonderie      :\t")+ map.get(EnumCarte.M11.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Briqueterie   :\t")+ map.get(EnumCarte.M12.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Carrière      :\t")+ map.get(EnumCarte.M13.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Scierie       :\t")+ map.get(EnumCarte.M14.toString())*100 / nbParties);

        inv.add(Colors.gStandard("Presse        :\t")+ map.get(EnumCarte.G1.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Verrerie      :\t")+ map.get(EnumCarte.G2.toString())*100 / nbParties);
        inv.add(Colors.gStandard("Métier Tisser :\t")+ map.get(EnumCarte.G3.toString())*100 / nbParties);

        inv.add(Colors.gBleu("Théâtre       :\t")+ map.get(EnumCarte.B1.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Autel         :\t")+ map.get(EnumCarte.B2.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Bains         :\t")+ map.get(EnumCarte.B3.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Tribunal      :\t")+ map.get(EnumCarte.B4.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Temple        :\t")+ map.get(EnumCarte.B5.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Aqueduc       :\t")+ map.get(EnumCarte.B6.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Statue        :\t")+ map.get(EnumCarte.B7.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Panthéon      :\t")+ map.get(EnumCarte.B8.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Jardins       :\t")+ map.get(EnumCarte.B9.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Hôtel de V    :\t")+ map.get(EnumCarte.B10.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Palace        :\t")+ map.get(EnumCarte.B11.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Sénat         :\t")+ map.get(EnumCarte.B12.toString())*100 / nbParties);
        inv.add(Colors.gBleu("Puits         :\t")+ map.get(EnumCarte.B13.toString())*100 / nbParties);

        inv.add(Colors.gJaune("Marché        :\t")+ map.get(EnumCarte.J1.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Compt OUEST   :\t")+ map.get(EnumCarte.J2.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Compt EST     :\t")+ map.get(EnumCarte.J3.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Taverne       :\t")+ map.get(EnumCarte.J4.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Vignoble      :\t")+ map.get(EnumCarte.J5.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Forum         :\t")+ map.get(EnumCarte.J6.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Caravansérail :\t")+ map.get(EnumCarte.J7.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Bazar         :\t")+ map.get(EnumCarte.J8.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Phare         :\t")+ map.get(EnumCarte.J9.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Port          :\t")+ map.get(EnumCarte.J10.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Arène         :\t")+ map.get(EnumCarte.J11.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Ch.de Commerce:\t")+ map.get(EnumCarte.J12.toString())*100 / nbParties);
        inv.add(Colors.gJaune("Ludus         :\t")+ map.get(EnumCarte.J13.toString())*100 / nbParties);

        inv.add(Colors.gRouge("Tour de Garde :\t")+ map.get(EnumCarte.R1.toString())*100 / nbParties);
        inv.add(Colors.gRouge("Caserne       :\t")+ map.get(EnumCarte.R2.toString())*100 / nbParties);
        inv.add(Colors.gRouge("Palissade     :\t")+ map.get(EnumCarte.R3.toString())*100 / nbParties);
        inv.add(Colors.gRouge("Muraille      :\t")+ map.get(EnumCarte.R4.toString())*100 / nbParties);
        inv.add(Colors.gRouge("Champs de tir :\t")+ map.get(EnumCarte.R5.toString())*100 / nbParties);
        inv.add(Colors.gRouge("Ecurie        :\t")+ map.get(EnumCarte.R6.toString())*100 / nbParties);
        inv.add(Colors.gRouge("Place d'Armes :\t")+ map.get(EnumCarte.R7.toString())*100 / nbParties);
        inv.add(Colors.gRouge("Fortification :\t")+ map.get(EnumCarte.R8.toString())*100 / nbParties);
        inv.add(Colors.gRouge("Arsenal       :\t")+ map.get(EnumCarte.R9.toString())*100 / nbParties);
        inv.add(Colors.gRouge("A. de Siège   :\t")+ map.get(EnumCarte.R10.toString())*100 / nbParties);
        inv.add(Colors.gRouge("Castrum       :\t")+ map.get(EnumCarte.R11.toString())*100 / nbParties);
        inv.add(Colors.gRouge("Cirque        :\t")+ map.get(EnumCarte.R12.toString())*100 / nbParties);

        inv.add(Colors.gVert("Scriptorium   :\t")+ map.get(EnumCarte.V1.toString())*100 / nbParties);
        inv.add(Colors.gVert("Atelier       :\t")+ map.get(EnumCarte.V2.toString())*100 / nbParties);
        inv.add(Colors.gVert("Officine      :\t")+ map.get(EnumCarte.V3.toString())*100 / nbParties);
        inv.add(Colors.gVert("Ecole         :\t")+ map.get(EnumCarte.V4.toString())*100 / nbParties);
        inv.add(Colors.gVert("Bibliothèque  :\t")+ map.get(EnumCarte.V5.toString())*100 / nbParties);
        inv.add(Colors.gVert("Laboratoire   :\t")+ map.get(EnumCarte.V6.toString())*100 / nbParties);
        inv.add(Colors.gVert("Dispensaire   :\t")+ map.get(EnumCarte.V7.toString())*100 / nbParties);
        inv.add(Colors.gVert("Loge          :\t")+ map.get(EnumCarte.V8.toString())*100 / nbParties);
        inv.add(Colors.gVert("Académie      :\t")+ map.get(EnumCarte.V9.toString())*100 / nbParties);
        inv.add(Colors.gVert("Observatoire  :\t")+ map.get(EnumCarte.V10.toString())*100 / nbParties);
        inv.add(Colors.gVert("Etude         :\t")+ map.get(EnumCarte.V11.toString())*100 / nbParties);
        inv.add(Colors.gVert("Université    :\t")+ map.get(EnumCarte.V12.toString())*100 / nbParties);

        inv.add(Colors.gViolet("G.Travailleurs:\t")+ map.get(EnumCarte.P1.toString())*100 / nbParties);
        inv.add(Colors.gViolet("G.Artisans    :\t")+ map.get(EnumCarte.P2.toString())*100 / nbParties);
        inv.add(Colors.gViolet("G.Magistrats  :\t")+ map.get(EnumCarte.P3.toString())*100 / nbParties);
        inv.add(Colors.gViolet("G.Commerçants :\t")+ map.get(EnumCarte.P4.toString())*100 / nbParties);
        inv.add(Colors.gViolet("G.Espions     :\t")+ map.get(EnumCarte.P5.toString())*100 / nbParties);
        inv.add(Colors.gViolet("G.Philosophes :\t")+ map.get(EnumCarte.P6.toString())*100 / nbParties);
        inv.add(Colors.gViolet("G.Scientifique:\t")+ map.get(EnumCarte.P7.toString())*100 / nbParties);
        inv.add(Colors.gViolet("G.Armateurs   :\t")+ map.get(EnumCarte.P8.toString())*100 / nbParties);
        inv.add(Colors.gViolet("G.Bâtisseurs  :\t")+ map.get(EnumCarte.P9.toString())*100 / nbParties);
        inv.add(Colors.gViolet("G.Décorateurs :\t")+ map.get(EnumCarte.P10.toString())*100 / nbParties);


        return inv;
    }

    /**
     * Crée une liste avec les merveilles à afficher pour chaque joueur dans un but d'affichage lisible dans la console
     * @param map la map du nombre de fois qu'un joueur gagne avec une merveille
     * @param totalMerveille la map du nombre de fois qu'un joueur obtient une merveille
     * @return les taux de victoires par merveille
     */
    public List<String> printListeMerveille(Map<String,Float> map,Map<String,Integer> totalMerveille) {
        ArrayList<String> inv = new ArrayList<>();
        inv.add(Colors.gViolet("BABYLON         :\t")+ map.get(Wonder.BABYLON.toString())*100 / totalMerveille.get(Wonder.BABYLON.toString()));
        inv.add(Colors.gViolet("OLYMPIA         :\t")+ map.get(Wonder.OLYMPIA.toString())*100 / totalMerveille.get(Wonder.OLYMPIA.toString()));
        inv.add(Colors.gViolet("HALIKARNASSOS   :\t")+ map.get(Wonder.HALIKARNASSOS.toString())*100 / totalMerveille.get(Wonder.HALIKARNASSOS.toString()));
        inv.add(Colors.gViolet("GIZAH           :\t")+ map.get(Wonder.GIZAH.toString())*100 / totalMerveille.get(Wonder.GIZAH.toString()));
        inv.add(Colors.gViolet("ALEXANDRIA      :\t")+ map.get(Wonder.ALEXANDRIA.toString())*100 / totalMerveille.get(Wonder.ALEXANDRIA.toString()));
        inv.add(Colors.gViolet("RHODOS          :\t")+ map.get(Wonder.RHODOS.toString())*100 / totalMerveille.get(Wonder.RHODOS.toString()));
        inv.add(Colors.gViolet("EPHESOS         :\t")+ map.get(Wonder.EPHESOS.toString())*100 / totalMerveille.get(Wonder.EPHESOS.toString()));

        inv.add(Colors.gViolet("BABYLON(N)      :\t")+ map.get(Wonder.BABYLONNUIT.toString())*100 / totalMerveille.get(Wonder.BABYLONNUIT.toString()));
        inv.add(Colors.gViolet("OLYMPIA(N)      :\t")+ map.get(Wonder.OLYMPIANUIT.toString())*100 / totalMerveille.get(Wonder.OLYMPIANUIT.toString()));
        inv.add(Colors.gViolet("HALIKARNASSOS(N):\t")+ map.get(Wonder.HALIKARNASSOSNUIT.toString())*100 / totalMerveille.get(Wonder.HALIKARNASSOSNUIT.toString()));
        inv.add(Colors.gViolet("GIZAH(N)        :\t")+ map.get(Wonder.GIZAHNUIT.toString())*100 / totalMerveille.get(Wonder.GIZAHNUIT.toString()));
        inv.add(Colors.gViolet("ALEXANDRIA(N)   :\t")+ map.get(Wonder.ALEXANDRIANUIT.toString())*100 / totalMerveille.get(Wonder.ALEXANDRIANUIT.toString()));
        inv.add(Colors.gViolet("RHODOS(N)       :\t")+ map.get(Wonder.RHODOSNUIT.toString())*100 / totalMerveille.get(Wonder.RHODOSNUIT.toString()));
        inv.add(Colors.gViolet("EPHESOS(N)      :\t")+ map.get(Wonder.EPHESOSNUIT.toString())*100 / totalMerveille.get(Wonder.EPHESOSNUIT.toString()));

        return inv;
    }
}