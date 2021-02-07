package sw_aventure.joueur;

import objet_commun.Carte;
import sw_aventure.objetjeu.Inventaire;
import sw_aventure.seven_wonders.FacadeMoteur;
import sw_aventure.seven_wonders.Plateau;
import metier.EnumRessources;
import metier.Wonder;

import java.util.*;
import java.security.SecureRandom;

/**
 * Interface des IA : on implémente des méthodes par défaut qui font des choix aléatoires.
 * Des classes représentant diverses stratégies de jeu implémenteront cette interface.
 */

public interface IA {

    SecureRandom r = new SecureRandom();

    /**
     * Renvoie aléatoirement un entier entre 0 et la taille de la carte
     * @param j le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @param prix si la carte est payante ou non
     * @return Index de la carte à jouer dans la main
     */
    default int choixMain(Joueur j , List<Carte> main, Plateau plateau,boolean prix){
        return r.nextInt(main.size()) ;
    }

    /**
     * Set la graine du SecureRandom à une valeur fixe
     * @param mySeed la valeur initale auquel on préfixe le SecureRandom
     */
    default void setTheSeed(int mySeed) {
        r.nextBytes(new byte[8]);
        r.setSeed(mySeed);
    }


    /**
     * Renvoie Vrai ou Faux quand au choix de défausser ou non
     * @param j le joueur
     * @param carte la carte à défausser
     * @param plateau le plateau de jeu
     * @return True défausse / false sinon
     */
    default boolean choixDefausse(Joueur j , Carte carte , Plateau plateau) {
        return !FacadeMoteur.permisDeConstruction(carte , j.getInv(), FacadeMoteur.joueurGauche(plateau,j).getInv(), FacadeMoteur.joueurDroit(plateau,j).getInv(),plateau);
    }

    /**
     * Choisit si le joueur achètera à gauche ou à droite
     * @param ressource la ressource à acheter
     * @param j  le joueur
     * @param gauche l'inventaire du joueur de gauche
     * @param droite l'inventaire du joueur de droite
     * @return true acheter à gauche / False à droite
     */
    default Boolean commerceAdjacent(EnumRessources ressource, Joueur j,  Inventaire gauche, Inventaire droite){
        int reducDroite = FacadeMoteur.getValue(j.getInv(),EnumRessources.REDMARRONDROITE);
        int reducGauche = FacadeMoteur.getValue(j.getInv(),EnumRessources.REDMARRONGAUCHE);
        if(reducGauche>reducDroite){ /// SI ON A UNE REDUC A GAUCHE ET PAS A DROITE ///
            return true;
        }
        if(reducDroite>reducGauche){ /// SI ON A UNE REDUC A DROITE ET PAS A GAUCHE ///
            return false;
        }
        /// SI ON A UNE REDUC A GAUCHE ET DROITE OU ALORS PAS DU TOUT DE REDUC ///
        return (gauche.getValue(EnumRessources.SCORE)<droite.getValue(EnumRessources.SCORE)); // ET SI LA DROITE A MOINS DE SCORE QUE LA GAUCHE ///
    }

    /**
     * retourne si oui ou non le joueur souhaite construire sa merveille
     * @param j le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @return True Construire / False sinon
     */
    default boolean choixMerveille(Joueur j , List<Carte> main, Plateau plateau){
        int choix = r.nextInt(2);
        return choix == 0 ;
    }

    /**
     * Renvoie un entier entre 0 et la taille de la main
     * @param j le joueur
     * @param main les cartes de sa main (peut également être le paquet de la défausse)
     * @param plateau le plateau de jeu
     * @param carteRecherchee la liste de cartes recherchée classé par ordre de priorité/importance
     * @param ressourcesRecherchee la liste des ressources recherchées en priorité
     * @param prix true si la carte est payante / false sinon (en cas de bonus qui rendrai la carte gratuite)
     * @return Index de la carte à jouer depuis la main
     */
    default int choixCarte(Joueur j , List<Carte> main, Plateau plateau, List<String> carteRecherchee, List<EnumRessources> ressourcesRecherchee,boolean bouclier, boolean prix) {
        List<Carte> possibilites = getPossibilites(j,main,plateau,prix);

        if(!possibilites.isEmpty()){ // si il y a des cartes qu'il est possible d'acheter
            String carte = chercherCarteDansMain(carteRecherchee,possibilites); // renvoie la meilleur carte voulue si il y en a une sinon string vide

            if(carte.equals("") && bouclier ){ // aucune carte demandée n'est trouvée et le joueur souhaite se défendre en cas d'attaque
                carte = seDefendre(j,plateau,possibilites); // recherche une carte rouge pour se défendre si nécessaire sinon retourne string vide
            }
            if(carte.equals("")){ // aucune carte encore trouvée donc cherchons des ressources
                carte = chercherRessourcesDansMain(j,ressourcesRecherchee,possibilites); // cherche un carte donnant des ressources utiles pour le joueur en fonction de ses besoins
            }
            if (carte.equals("")) { // si toujours rien n'est trouvé alors renvoie la première carte possible d'acheter
                carte = possibilites.get(0).getNom().toString();
            }
            for (int i = 0; i < main.size(); i++) { // recherche à quel carte le nom correspond dans la main
                if (main.get(i).getNom().toString().equals(carte)){
                    return i;
                }
            }
        }
        return 0; // retourne la première carte dans la main si il n'est possible d'acheter aucune carte
    }

    /**
     * retourne la main réduite aux cartes pouvant être achetées
     * @param j le joueur
     * @param main sa main de cartes
     * @param plateau le plateau de jeu
     * @param prix si les cartes sont payantes ou non
     * @return la main dépourvu des cartes impossibles à acheter
     */

    default List<Carte> getPossibilites(Joueur j , List<Carte> main, Plateau plateau, boolean prix){
        ArrayList<Carte> possibilites = new ArrayList<>();
        if(prix) {
            for (int i = 0; i < main.size(); i++) {
                if (FacadeMoteur.permisDeConstruction(main.get(i), j.getInv(), FacadeMoteur.joueurGauche(plateau,j).getInv(), FacadeMoteur.joueurDroit(plateau,j).getInv(),plateau)) {
                    possibilites.add(main.get(i));
                }
            }
        }else{
            for (int i = 0; i < main.size(); i++) {
                if (!FacadeMoteur.laConstructionViaDoublons(main.get(i), j.getInv(), false)) {
                    possibilites.add(main.get(i));
                }
            }
        }
        return possibilites;
    }

    /**
     * le joueur recherche dans l'ordre d'importance de ses préférence une carte qu'il peut acheter
     * @param carteRecherchee la liste des cartes recherchée
     * @param possibilites la main réduite aux cartes pouvant être achetées
     * @return le nom de la carte a jouer depuis la main
     */
    default String chercherCarteDansMain(List<String> carteRecherchee, List<Carte> possibilites){
        int indiceDeLaMeilleurCarte = 999;
        String carte = "";
        for (int i = 0; i < possibilites.size(); i++) {
            if (carteRecherchee.contains(possibilites.get(i).getNom().toString()) && ( carteRecherchee.indexOf(possibilites.get(i).getNom().toString()) < indiceDeLaMeilleurCarte)) {
                carte = possibilites.get(i).getNom().toString();
            }
        }
        return carte;
    }

    /**
     * si le joueur n'a pas trouvé la carte souhaitée il essaye de se défendre si nécessaire
     * @param j le joueur
     * @param plateau le plateau de jeu
     * @param possibilites la main réduite aux cartes pouvant être achetées
     * @return le nom de la carte à jouer depuis la main
     */
    default String seDefendre(Joueur j , Plateau plateau , List<Carte> possibilites){
        int boucliers = FacadeMoteur.getValue(j.getInv(),EnumRessources.BOUCLIER);
        String carte = "";
        if ((FacadeMoteur.getValue(FacadeMoteur.joueurGauche(plateau,j).getInv(),EnumRessources.BOUCLIER) > boucliers || FacadeMoteur.getValue(FacadeMoteur.joueurDroit(plateau,j).getInv(),EnumRessources.BOUCLIER) > boucliers)) {
            for (int i = 0; i < possibilites.size(); i++) {
                if (possibilites.get(i).getCouleur().equals(EnumRessources.ROUGE)) {
                    carte = possibilites.get(i).getNom().toString();
                    break;
                }
            }
        }
        return carte ;
    }

    /**
     * si le joueur n'a pas trouvé la carte souhaitée il essaye d'approvisionner son inventaire de ressources
     * @param j le joueur
     * @param ressourcesRecherchee la ressource recherchée
     * @param possibilites la main réduite aux cartes pouvant être achetées
     * @return le nom de la carte à jouer depuis la main
     */
    default String chercherRessourcesDansMain(Joueur j , List<EnumRessources> ressourcesRecherchee, List<Carte> possibilites){
        String carte = "";
        for(int i = 0 ; i < ressourcesRecherchee.size(); i++){
            carte = approvisionnementDeRessource(j,possibilites,ressourcesRecherchee.get(i));
            if(!carte.equals("")){
                return carte;
            }
        }
        if (FacadeMoteur.getValue(j.getInv(),EnumRessources.BOIS) < 2) {
            carte = approvisionnementDeRessource(j,possibilites,EnumRessources.BOIS);
        }
        if (carte.equals("") && FacadeMoteur.getValue(j.getInv(),EnumRessources.MINERAI) < 2) {
            carte = approvisionnementDeRessource(j,possibilites,EnumRessources.MINERAI);
        }
        if (carte.equals("") && FacadeMoteur.getValue(j.getInv(),EnumRessources.ARGILE) < 2) {
            carte = approvisionnementDeRessource(j,possibilites,EnumRessources.ARGILE);
        }
        if (carte.equals("") && FacadeMoteur.getValue(j.getInv(),EnumRessources.PIERRE) < 2) {
            carte = approvisionnementDeRessource(j,possibilites,EnumRessources.PIERRE);
        }
        return carte;
    }

    /**
     * Recherche la ressource parmi les cartes possible dans le but d'approvisionner son inventaire
     * @param j le joueur
     * @param possibilites la main réduite aux cartes pouvant être achetées
     * @param ressourceVoulu la ressource recherchée
     * @return le nom de la carte à jouer depuis la main
     */
    default String approvisionnementDeRessource(Joueur j , List<Carte> possibilites, EnumRessources ressourceVoulu){
        String carte = "";
        for (int i = 0; i < possibilites.size(); i++) {
            if (possibilites.get(i).getGain().contains(ressourceVoulu)) {
                carte = possibilites.get(i).getNom().toString();
                break;
            }
        }
        return carte ;
    }
    /**
     * renvoie si oui ou non il souhaite construire sa merveille
     * @param j le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @param merveilles les merveilles que le joueur souhaiterai construire (en fonction de sa stratégie)
     * @return True Construire / False sinon
     */

    default boolean choixConstrMerveille(Joueur j, List<Carte> main, Plateau plateau, List<Wonder> merveilles){
        return merveilles.contains(FacadeMoteur.getMerveille(j.getInv()).getNom()) && FacadeMoteur.getMerveille(j.getInv()).peutAmeliorerMerveille() && (FacadeMoteur.permisDeConstruction(FacadeMoteur.getMerveille(j.getInv()).getCarteAConstruire(),j.getInv(),FacadeMoteur.joueurGauche(plateau,j).getInv(), FacadeMoteur.joueurDroit(plateau,j).getInv(),plateau));
             // Si l'étage de la merveille est constructible par le joueur alors il décide de construire la merveille
    }

    /**
     * Choisit une carte a sacrifier pour construire la merveille (de préférence une qu'il ne pouvait pas construire de base)
     * @param j le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @return Index de la carte à sacrifier pour construire la merveille
     */
    default int choixCartePourMerveille(Joueur j, List<Carte> main, Plateau plateau){
        for(int i = 0 ; i < main.size() ; i++) {
            if (!FacadeMoteur.permisDeConstruction(main.get(i), j.getInv(), FacadeMoteur.joueurGauche(plateau,j).getInv(), FacadeMoteur.joueurDroit(plateau,j).getInv(),plateau)) {
                return i;
            }
        }
        return 0 ;
    }


    /**
     * Choisit aléatoirement une carte depuis la défausse
     * @param paquetDefausse le paquet de carte de la défausse où le joueur pourra jouer une carte gratuitement
     * @param plateau le plateau de jeu
     * @return Index de la carte à jouer depuis la défausse
     */
    default int choisirCarteDeLaDefausse(List<Carte> paquetDefausse, Plateau plateau){
        return r.nextInt(paquetDefausse.size());
    }


    /**
     * Ajoute à la liste passée en paramètre la ressource recherchée si elle n'y est pas déjà et si le joueur n'en possède pas assez
     * @param ressourcesrecherchees la liste préexistante
     * @param j le joueur
     * @param nombreVoulu la quantité souhaitée
     * @param ressource la ressource recherchée
     * @return la liste modifiée ou non selon la condition
     */
    default List<EnumRessources> listeRessource(List<EnumRessources> ressourcesrecherchees, Joueur j, int nombreVoulu, EnumRessources ressource){
        if(FacadeMoteur.getValue(j.getInv(),ressource)<nombreVoulu && !ressourcesrecherchees.contains(ressource)){
            ressourcesrecherchees.add(ressource);
        }
        return ressourcesrecherchees;
    }
}
