package utilitaire_jeu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import objet_commun.Carte;
import metier.EnumCarte;
import metier.EnumRessources;
import utils.affichage.Colors;
import utils.affichage.LoggerSevenWonders;


public class Construction {


    /**
     * Test si la construction est possible par le joueur ou non
     * @param carte la carte à construire
     * @param sInventaire inventaire du joueur
     * @param gauche inventaire de gauche
     * @param droite inventaire de droite
     * @param plateau le plateau
     * @return True si il peut construire la carte / False sinon
     */

    public static boolean permisDeConstruction(Carte carte, Inventaire sInventaire, Inventaire gauche, Inventaire droite, Plateau plateau) {
        if(laConstructionViaDoublons(carte,sInventaire,false)){
            return false;
        }
        List<EnumRessources> reste = laConstruction(carte,sInventaire,plateau,false);
        if(!reste.isEmpty()){
            return viaAchat(reste,sInventaire,gauche,droite);
        }else{
            return true;
        }
    }


    /**
     * Le joueur a décidé de construire la carte, la construction est lancée étape par étape
     * @param carte la carte à construire
     * @param sInventaire SetInventaire du joueur
     * @param gauche SetInventaire de gauche
     * @param droite SetInventaire de droite
     * @param plateau le plateau
     * @return True si la carte a bien été construite / false sinon
     */
    public boolean permisDeConstruction(Carte carte, SetInventaire sInventaire, SetInventaire gauche, SetInventaire droite, Plateau plateau){
        if(laConstructionViaDoublons(carte,sInventaire,true)){
            return false;
        }
        List<EnumRessources> reste = laConstruction(carte,sInventaire,plateau,true);
        if(!reste.isEmpty()){
            return laConstructionViaCommerce(reste,sInventaire,gauche,droite,true);
        }else{
            return true;
        }
    }
//////////////////////   Construction(que si le joueur peut vraiment payer) //////////////////////////////////

    /**
     * Prend la carte et les inventaires du joueur (et des joueurs adjacent) et fait les transactions/affichages nécessaires pour la construction
     * @param carte la carte à construire
     * @param sInventaire inventaire du joueur
     * @param plateau le plateau
     * @param print afficher ou non les textes (false en cas de test de construction)
     * @return la liste des ressources restante à payer
     */

    public static List<EnumRessources> laConstruction(Carte carte, Inventaire sInventaire, Plateau plateau, boolean print) {
        List<EnumRessources> vrai = Collections.emptyList();
        ArrayList<EnumRessources> aPayer = new ArrayList<>(carte.getPrix());
        if(laConstructionViaChainee(carte,sInventaire,print)){
            return vrai;
        }
        if(laConstructionViaGratuit(aPayer,print)){
            return vrai ;
        }
        if(laConstructionViaBonusCouleur(carte,sInventaire,print)){
            return vrai;
        }
        if(laConstructionViaBonusTour(carte, sInventaire,plateau,print)){
            return vrai;
        }
        /// VIA INVENTAIRE ///
        List<EnumRessources> reste = laConstructionViaInventaire(aPayer,sInventaire,print);
        if(!reste.isEmpty()){
            reste = laConstructionViaMultichoix(reste,sInventaire,print);
            return reste ;
        }else{
            return reste;
        }
    }

    /**
     * Le joueur possède-t-il déjà la carte ?
     * @param carte la carte
     * @param sInventaire inventaire du joueur
     * @param print afficher ou non  (non en cas de tests de construction)
     * @return True si il possède la carte / false sinon
     */

    public static boolean laConstructionViaDoublons(Carte carte, Inventaire sInventaire, boolean print){
        ////////     DOUBLONS !!!   /////////
        if (sInventaire.getListeCarte().contains(carte.getNom())) {
            if(print){LoggerSevenWonders.ajoutln(Colors.gRouge("Carte déjà en possession !"));}
            return true;
        }
        return false;
    }

    /**
     * Le joueur possède-t-il la carte chainée précédente ?
     * @param carte la carte
     * @param sInventaire l'inventaire du joueur
     * @param print afficher ou non les print
     * @return True si il possède la carte chainée antérieur / false sinon
     */

    public static boolean laConstructionViaChainee(Carte carte, Inventaire sInventaire, boolean print){
        ////////   CARTE CHAINEE     /////////
        List<EnumCarte> liste = Arrays.asList(EnumCarte.B13, EnumCarte.B7, EnumCarte.B3, EnumCarte.B6, EnumCarte.B2, EnumCarte.B8, EnumCarte.B1, EnumCarte.B9, EnumCarte.J1, EnumCarte.J7, EnumCarte.J7, EnumCarte.J9, EnumCarte.J3, EnumCarte.J6, EnumCarte.J6, EnumCarte.J10, EnumCarte.J2, EnumCarte.J6, EnumCarte.V3, EnumCarte.R6, EnumCarte.V3, EnumCarte.V7, EnumCarte.V7, EnumCarte.J11, EnumCarte.V7, EnumCarte.V8, EnumCarte.V2, EnumCarte.R5, EnumCarte.V2, EnumCarte.V6, EnumCarte.V6, EnumCarte.R10, EnumCarte.V6, EnumCarte.V10, EnumCarte.V1, EnumCarte.B4, EnumCarte.V1, EnumCarte.V5, EnumCarte.V5, EnumCarte.B12, EnumCarte.V5, EnumCarte.V12, EnumCarte.V4, EnumCarte.V9, EnumCarte.V4, EnumCarte.V11, EnumCarte.R4, EnumCarte.R8, EnumCarte.R7, EnumCarte.R12);
        for (int i = 0; i < liste.size() / 2; i += 2) {
            if (carte.getNom().equals(liste.get(i + 1)) && sInventaire.getListeCarte().contains(liste.get(i))) {
                if(print){LoggerSevenWonders.ajoutln("Carte chainée antérieur possédée " + liste.get(i) + " -> Construction Gratuite");}
                return true;
            }
        }
        return false ;
    }

    /**
     * La carte est-t-elle gratuite ?
     * @param aPayer prix de la carte
     * @param print afficher ou non les prints
     * @return True si la carte est gratuite / false sinon
     */

    public static boolean laConstructionViaGratuit(List<EnumRessources> aPayer, boolean print){
        ///////     PRIX GRATUIT     /////////
        if (aPayer.get(0).equals(EnumRessources.GRATUIT)) {
            if(print){LoggerSevenWonders.ajoutln("Carte Gratuite !");}
            return true;
        }
        return false ;
    }

    /**
     * Si le joueur possède un bonus couleur et qu'il peut l'utiliser
     * @param carte la carte
     * @param inv l'inventaire du joueur
     * @param print afficher les prints ou non
     * @return True si le carte est gratuite grâce à un potentiel bonus / false sinon
     */
    public static boolean laConstructionViaBonusCouleur(Carte carte, Inventaire inv, boolean print){
        if((inv.getValue(EnumRessources.BONUSAGECOULEUR)>0 && carte.getNom().toString().length()>=3)&& (inv.getValue(carte.getCouleur())== 0)) {
            if(print){LoggerSevenWonders.ajoutln("Bonus Couleur en possession ! C'est sa première carte de cette couleur elle est donc gratuite !");}
            return true;
        }
        return false;
    }

    /**
     * Si le joueur possède un bonus tour et qu'il peut l'utiliser
     * @param carte la carte
     * @param inventaire l'inventaire du joueur
     * @param plateau le plateau de jeu
     * @param print afficher les prints ou non
     * @return true si la construction est gratuite frâce à un potentiel bonus / false sinon
     */
    public static boolean laConstructionViaBonusTour(Carte carte, Inventaire inventaire, Plateau plateau, boolean print){
        if(inventaire.getValue(EnumRessources.BONUSCARTEAGEG2P) > 0 && plateau.getTour()==1 && carte.getNom().toString().length()>=3){
            if(print) {
                LoggerSevenWonders.ajoutln("Bonus 1er Tour en possession ! C'est la première carte de cette âge elle est donc gratuite !");
            }return true ;
        }
        if(inventaire.getValue(EnumRessources.BONUSCARTEAGEG3P) > 0 && plateau.getTour()==6 && carte.getNom().toString().length()>=3){
            if(print) {
                LoggerSevenWonders.ajoutln("Bonus dernier Tour en possession ! C'est la dernière carte de cette âge elle est donc gratuite !");
            }return true ;
        }
        return false;
    }

    /**
     * construction avec les ressources présente dans l'inventaire
     * @param aPayer le restant à payer
     * @param sInventaire l'inventaire du joueur
     * @param print afficher les prints ou non
     * @return le restant à payer après payement via inventaire
     */

    public static List<EnumRessources> laConstructionViaInventaire(List<EnumRessources> aPayer, Inventaire sInventaire, boolean print){
        ///////      VIA INVENTAIRE   ////////

        ArrayList<EnumRessources> payeInv = new ArrayList<>();
        EnumRessources ressource = null;
        int nbRessource = 0;
        for (int i = aPayer.size()-1; i >=0  ; i--) {
            if (!aPayer.get(i).equals(ressource)) {
                ressource = aPayer.get(i);
                nbRessource = sInventaire.getValue(aPayer.get(i));
            }
            if (nbRessource >= 1) {
                nbRessource -= 1;
                payeInv.add(aPayer.get(i));
                aPayer.remove(i);
            }
        }
        if (!payeInv.isEmpty() && print){
            LoggerSevenWonders.ajoutln(sInventaire.joueurName+ " a payé via son Inventaire les ressources suivantes : " + payeInv);
        }
        return aPayer;
    }

    /**
     * Construction via les ressources multi-choix
     * @param aPayer le restant à payer
     * @param sInventaire l'inventaire du joueur
     * @param print afficher ou non les prints
     * @return le restant à payer après la construction via multi-choix
     */

    public static List<EnumRessources> laConstructionViaMultichoix(List<EnumRessources> aPayer, Inventaire sInventaire, boolean print) {
        ///////   VIA MULTICHOIX    /////////
        List<List<EnumRessources>> multiChoix = listeMultiChoix(sInventaire);
        ArrayList<EnumRessources> payeMulti = new ArrayList<>();

        if (!multiChoix.isEmpty()) {
            EnumRessources ressource = null;
            for (int i = aPayer.size()-1; i >= 0  ; i--) {
                boolean paye = false;
                if (!multiChoix.isEmpty()) {
                    if (!aPayer.get(i).equals(ressource)) {
                        ressource = aPayer.get(i);
                    }
                    for (int j =  multiChoix.size()-1 ; j >= 0; j--) {
                        for (int k = 0; k < multiChoix.get(j).size(); k++) {
                            if (ressource.equals(multiChoix.get(j).get(k))) {
                                payeMulti.add(aPayer.get(i));
                                aPayer.remove(i);
                                multiChoix.remove(j);
                                paye = true ;
                                break;
                            }
                        }
                        if (paye) {
                            break;
                        }
                    }
                }
            }
        }

        if (!payeMulti.isEmpty() && print) {
            LoggerSevenWonders.ajoutln(sInventaire.joueurName + " a payé via ses bonus Multi-Choix les ressources suivantes : " + payeMulti);
        }
        return aPayer;
    }

    /**
     * Crée la liste des multi-choix disponibles du joueur
     * @param sInventaire l'inventaire du joueur
     * @return la liste en question
     */
    public static List<List<EnumRessources>> listeMultiChoix(Inventaire sInventaire){

        ArrayList<List<EnumRessources>> multiChoix = new ArrayList<>();

        if (sInventaire.getValue(EnumRessources.MULTIPA) != 0) {
            multiChoix.add(Arrays.asList(EnumRessources.PIERRE,EnumRessources.ARGILE));

        }
        if (sInventaire.getValue(EnumRessources.MULTIBP) != 0) {
            multiChoix.add(Arrays.asList(EnumRessources.BOIS,EnumRessources.PIERRE));

        }
        if (sInventaire.getValue(EnumRessources.MULTIAM) != 0) {
            multiChoix.add(Arrays.asList(EnumRessources.ARGILE,EnumRessources.MINERAI));
        }
        if (sInventaire.getValue(EnumRessources.MULTIBM) != 0) {
            multiChoix.add(Arrays.asList(EnumRessources.BOIS,EnumRessources.MINERAI));

        }
        if (sInventaire.getValue(EnumRessources.MULTIBA) != 0) {
            multiChoix.add(Arrays.asList(EnumRessources.BOIS,EnumRessources.ARGILE));

        }
        if (sInventaire.getValue(EnumRessources.MULTIPM) != 0) {
            multiChoix.add(Arrays.asList(EnumRessources.PIERRE,EnumRessources.MINERAI));
        }
        for(int i =0 ; i < sInventaire.getValue(EnumRessources.MULTIBPAM) ; i++) {
            multiChoix.add(Arrays.asList(EnumRessources.BOIS,EnumRessources.PIERRE,EnumRessources.ARGILE,EnumRessources.MINERAI));
        }
        if (sInventaire.getValue(EnumRessources.MULTIVPT) != 0) {
            multiChoix.add(Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS,EnumRessources.TISSU));
        }
        return multiChoix;
    }


    /**
     * Essayer de payer en achetant aux autres joueurs (dans le cadre d'un test de construction)
     * Sinon renvoie false
     * @param aPayer restant à payer
     * @param stock inventaire joueur
     * @param gauche inventaire gauche
     * @param droite inventaire droit
     * @return True si le restant à payer est vide
     */

    public static boolean viaAchat(List<EnumRessources> aPayer, Inventaire stock, Inventaire gauche, Inventaire droite){

        int pieceJoueur = stock.getValue(EnumRessources.PIECE);
        int nbRessourceGauche = 0 ;
        int nbRessourceDroite = 0 ;
        int prix;
        EnumRessources ressource = null;
        EnumRessources couleur = null;
        boolean reducMarronDroite = false ;
        boolean reducMarronGauche = false ;
        boolean reducGrisDroiteGauche = false ;

        if(stock.getValue(EnumRessources.REDMARRONDROITE)>0) {
            reducMarronDroite = true;
        }
        if(stock.getValue(EnumRessources.REDMARRONGAUCHE)>0) {
            reducMarronGauche = true;
        }
        if(stock.getValue(EnumRessources.REDGRISDROITEGAUCHE)>0) {
            reducGrisDroiteGauche = true;
        }

        for (int i = aPayer.size()-1; i >= 0 ; i--) {
            if (!aPayer.get(i).equals(ressource)) {
                ressource = aPayer.get(i);

                nbRessourceGauche = gauche.getValue(ressource);
                nbRessourceDroite = droite.getValue(ressource);
                if (ressource.equals(EnumRessources.BOIS) || ressource.equals(EnumRessources.PIERRE) || ressource.equals(EnumRessources.ARGILE) || ressource.equals(EnumRessources.MINERAI)) {
                    couleur = EnumRessources.MARRON;
                } else {
                    couleur = EnumRessources.GRISE;
                }
            }

            if (nbRessourceGauche >= 1) {
                if (couleur.equals(EnumRessources.MARRON) && reducMarronGauche || couleur.equals(EnumRessources.GRISE) && reducGrisDroiteGauche) {
                    prix = 1;
                } else {
                    prix = 2;
                }
                pieceJoueur -= prix;
                aPayer.remove(i);
                nbRessourceGauche -= 1;
            } else if (nbRessourceDroite >= 1) {
                if (couleur.equals(EnumRessources.MARRON) && reducMarronDroite || couleur.equals(EnumRessources.GRISE) && reducGrisDroiteGauche) {
                    prix = 1;
                } else {
                    prix = 2;
                }
                pieceJoueur -= prix;
                aPayer.remove(i);
                nbRessourceDroite -= 1;
            }
        }
        return (aPayer.isEmpty() && pieceJoueur>=0);
    }


    /**
     * Dernière étape possible pour la construction : le commerce avec les joueurs adjacents
     * @param aPayer restant à payer
     * @param sInventaire SetInventaire du joueur
     * @param gauche SetInventaire du joueur gauche
     * @param droite SetInventaire du joueur droite
     * @param print afficher ou non les prints
     * @return True si tout est payé / false sinon
     */
    public boolean laConstructionViaCommerce(List<EnumRessources> aPayer ,SetInventaire sInventaire, SetInventaire gauche, SetInventaire droite, boolean print){
        //////////     VIA  COMMERCE      //////////
        int pieceGauche = 0 ;
        int pieceDroite = 0 ;
        int pieceJoueur = 0 ;

        int prix;

        int nbRessourceGauche = 0;
        int nbRessourceDroite = 0;
        EnumRessources ressource = null;
        EnumRessources couleur = null;

        boolean reducMarronDroite = false ;
        boolean reducMarronGauche = false ;
        boolean reducGrisDroiteGauche = false ;

        if(sInventaire.getValue(EnumRessources.REDMARRONDROITE)>0) {
            reducMarronDroite = true;
        }
        if(sInventaire.getValue(EnumRessources.REDMARRONGAUCHE)>0) {
            reducMarronGauche = true;
        }
        if(sInventaire.getValue(EnumRessources.REDGRISDROITEGAUCHE)>0) {
            reducGrisDroiteGauche = true;
        }

        for (int j = aPayer.size()-1; j >= 0; j--) {
            if (!aPayer.get(j).equals(ressource)) {
                ressource = aPayer.get(j);
                nbRessourceGauche = gauche.getValue(ressource);
                nbRessourceDroite = droite.getValue(ressource);
                if (ressource.equals(EnumRessources.BOIS) || ressource.equals(EnumRessources.PIERRE) || ressource.equals(EnumRessources.ARGILE) || ressource.equals(EnumRessources.MINERAI)) {
                    couleur = EnumRessources.MARRON;
                } else {
                    couleur = EnumRessources.GRISE;
                }
            }
            boolean reducPossibleGauche = couleur.equals(EnumRessources.MARRON) && reducMarronGauche || couleur.equals(EnumRessources.GRISE) && reducGrisDroiteGauche;
            boolean reducPossibleDroite = couleur.equals(EnumRessources.MARRON) && reducMarronDroite || couleur.equals(EnumRessources.GRISE) && reducGrisDroiteGauche;
            if (nbRessourceGauche >= 1 && nbRessourceDroite >= 1) {
                ////////        DONNER    LE    CHOIX    AU    JOUEUR    DE    CHOISIR    GAUCHE    OU   DROITE       ///////////
                //if (Boolean.TRUE.equals(sInventaire.getJoueur().achatRessource(aPayer.get(j), gauche, droite))) {
                //if (Boolean.TRUE.equals(FacadeJoueur.achatRessource(sInventaire.getUrl(),aPayer.get(j), gauche, droite))) {
                 if(true){   ///  TRUE = GAUCHE  ///
                    if (reducPossibleGauche) {
                        prix = 1;
                    } else {
                        prix = 2;
                    }
                    achatVoisin(sInventaire, gauche, aPayer.get(j), prix, print);
                    nbRessourceGauche -= 1;
                    pieceJoueur += prix;
                    pieceGauche += prix;
                } else {
                    /// FALSE = DROITE  ///
                    if (reducPossibleDroite) {
                        prix = 1;
                    } else {
                        prix = 2;
                    }
                    achatVoisin(sInventaire, droite, aPayer.get(j), prix, print);
                    nbRessourceDroite -= 1;
                    pieceJoueur += prix;
                    pieceDroite += prix;
                }
                aPayer.remove(j);

            } else if (nbRessourceGauche >= 1) {
                if (reducPossibleGauche) {
                    prix = 1;
                } else {
                    prix = 2;
                }
                achatVoisin(sInventaire, gauche, aPayer.get(j), prix, print);
                nbRessourceGauche -= 1;
                pieceJoueur += prix;
                pieceGauche += prix;
                aPayer.remove(j);

            } else if (nbRessourceDroite >= 1) {
                if (reducPossibleDroite) {
                    prix = 1;
                } else {
                    prix = 2;
                }
                achatVoisin(sInventaire, droite, aPayer.get(j), prix, print);
                nbRessourceDroite -= 1;
                pieceJoueur += prix;
                pieceDroite += prix;
                aPayer.remove(j);

            }
        }

        if (aPayer.isEmpty() && (sInventaire.getValue(EnumRessources.PIECE)>=0)){
            return true;
        }
        if(print){LoggerSevenWonders.ajoutln(Colors.gRouge("PAYEMENT REFUSE ! achats annulés ! \n"));}
        sInventaire.increaseValue(EnumRessources.PIECE, pieceJoueur);
        droite.decreaseValue(EnumRessources.PIECE, pieceDroite);
        gauche.decreaseValue(EnumRessources.PIECE, pieceGauche);
        return false ;
    }

    /**
     * Joueur achète la "ressource" à voisin
     * @param joueur le SetInventaire du joueur
     * @param voisin le SetInventaire de son voisin
     * @param ressource la ressource à acheter
     * @param prix le prix que devra payer le joueur à son voisin
     * @param print afficher ou non les prints
     */
    public void achatVoisin(SetInventaire joueur, SetInventaire voisin,  EnumRessources ressource, int prix,boolean print){
        joueur.decreaseValue(EnumRessources.PIECE, prix);
        voisin.increaseValue(EnumRessources.PIECE, prix);
        if (print) {
            LoggerSevenWonders.ajoutln(joueur.getJoueurName() + " a acheter à " + voisin.joueurName
                    + " la ressource " + ressource + " pour " + prix + " Pièces ! ");
        }
    }










}
