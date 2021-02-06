package sw_aventure.joueur;


import metier.EnumCarte;
import metier.EnumRessources;
import objet_commun.Carte;
import sw_aventure.objetjeu.Inventaire;
import sw_aventure.seven_wonders.FacadeMoteur;
import sw_aventure.seven_wonders.Plateau;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IAambitieuse implements IA {
    /**
     * renvoie si oui ou non il souhaite construire sa merveille
     * @param j joueur
     * @param main les cartes de la main
     * @param plateau le plateau de jeu
     * @return True construire / False sinon
     */
    @Override
    public boolean choixMerveille(Joueur j, List<Carte> main, Plateau plateau){
        return false;
    }

    /**
     * appel la méthode Choix carte pour décider quoi jouer en lui fournissant la liste de ses priorités
     * @param joueur le joueur
     * @param main sa main de cartes
     * @param plateau le plateau de jeu
     * @return Index de la carte à jouer depuis la main
     */
    @Override
    public int choixMain(Joueur joueur , List<Carte> main, Plateau plateau,boolean prix){
        List<EnumRessources> ressourcesrecherchees = rechercheRessources(joueur,plateau);
        List<String> carteRecherchee ;
        if(FacadeMoteur.getAge(plateau)==1){
            carteRecherchee = Collections.emptyList();
        }
        else if(FacadeMoteur.getAge(plateau)==2){
            carteRecherchee = Collections.emptyList();
        }
        else {
            carteRecherchee = Collections.emptyList();
            return choixCarteAge3(joueur,main,plateau,carteRecherchee,ressourcesrecherchees,besoinDeBouclier(joueur,plateau),prix);
        }
        return choixCarte(joueur,main,plateau,carteRecherchee,ressourcesrecherchees,besoinDeBouclier(joueur,plateau),prix);
    }

    /**
     *  Méthode permettant de décider à l'Age 3, quelle carte l'IA souhaite jouer
     * @param joueur le joueur
     * @param main les cartes de la main
     * @param plateau le plateau de jeu
     * @param carteRecherchee la liste des cartes recherchées
     * @param ressourcesRecherchee la liste des ressources recherchées
     * @param bouclier boolean pour savoir si l'IA veut se défendre ou non
     * @param prix si la carte est payante ou non
     * @return retourne la carte choisie
     */
    public int choixCarteAge3(Joueur joueur , List<Carte> main, Plateau plateau, List<String> carteRecherchee, List<EnumRessources> ressourcesRecherchee,boolean bouclier, boolean prix){
        List<Carte> possibilites = getPossibilites(joueur,main,plateau,prix);
        int max = 0 ;
        int temp;
        int idMax = 0 ;
        if (!possibilites.isEmpty()) {
            for(int i=0; i<possibilites.size(); i++){
                if(possibilites.get(i).getCouleur().equals(EnumRessources.BLEUE)){
                    temp = possibilites.get(i).getGain().size();
                }else if(possibilites.get(i).getCouleur().equals(EnumRessources.VIOLETTE) || possibilites.get(i).getCouleur().equals(EnumRessources.JAUNE) || possibilites.get(i).getCouleur().equals(EnumRessources.ROUGE)){
                    temp = getTotalPoints(possibilites.get(i), joueur, plateau);
                }else if(possibilites.get(i).getCouleur().equals(EnumRessources.VERTE)){
                    temp = getScientistPoints(possibilites.get(i).getGain().get(0), joueur) ;
                }else {
                    temp = 0;
                }
                if (temp > max){
                    idMax = i ;
                    max = temp ;
                }
            }

            EnumCarte carte = possibilites.get(idMax).getNom();
            for (int i = 0; i < main.size(); i++) { // recherche à quelle carte le nom correspond dans la main
                if (main.get(i).getNom().equals(carte)) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * Méthode permettant de connaitre le nombre de points que la carte fera gagner au joueur si elle la construit
     * @param carte la carte à calculer
     * @param joueur le joueur
     * @param plateau le plateau de jeu
     * @return les points que la carte fait gagner
     */
    public int getTotalPoints(Carte carte, Joueur joueur, Plateau plateau){
        Inventaire gauche = FacadeMoteur.joueurGauche(plateau,joueur).getInv();
        Inventaire droit = FacadeMoteur.joueurDroit(plateau,joueur).getInv();

        int bouclierGauche = FacadeMoteur.getValue(gauche, EnumRessources.BOUCLIER);
        int bouclierDroit = FacadeMoteur.getValue(droit,EnumRessources.BOUCLIER);
        int bouclierJoueur = FacadeMoteur.getValue(joueur.getInv(),EnumRessources.BOUCLIER);

        if(carte.getGain().get(0).equals(EnumRessources.BONUS11J)){
            return FacadeMoteur.getValue(joueur.getInv() ,EnumRessources.JAUNE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BONUS11M)){
            return FacadeMoteur.getValue(joueur.getInv(), EnumRessources.MARRON);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BONUS22G)){
            return (2*FacadeMoteur.getValue(joueur.getInv(), EnumRessources.GRISE));
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BONUS31R)){
            return FacadeMoteur.getValue(joueur.getInv(), EnumRessources.ROUGE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BONUS31MERVEILLE)){
            return FacadeMoteur.getMerveille(joueur.getInv()).getStade();
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1M)){
            return FacadeMoteur.getValue(droit,EnumRessources.MARRON) + FacadeMoteur.getValue(gauche,EnumRessources.MARRON);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS2G)){
            return 2*(FacadeMoteur.getValue(gauche,EnumRessources.GRISE) + FacadeMoteur.getValue(droit,EnumRessources.GRISE));
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1B)){
            return FacadeMoteur.getValue(gauche,EnumRessources.BLEUE) + FacadeMoteur.getValue(droit,EnumRessources.BLEUE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1J)){
            return FacadeMoteur.getValue(gauche,EnumRessources.JAUNE) + FacadeMoteur.getValue(droit,EnumRessources.JAUNE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1R)){
            return FacadeMoteur.getValue(gauche,EnumRessources.ROUGE) + FacadeMoteur.getValue(droit,EnumRessources.ROUGE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1V)){
            return FacadeMoteur.getValue(gauche,EnumRessources.VERTE) + FacadeMoteur.getValue(droit,EnumRessources.VERTE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUSMGV)){
            return FacadeMoteur.getValue(joueur.getInv(), EnumRessources.MARRON) + FacadeMoteur.getValue(joueur.getInv(), EnumRessources.GRISE) + FacadeMoteur.getValue(joueur.getInv(), EnumRessources.VIOLETTE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1MERVEILLE)){
            return FacadeMoteur.getMerveille(joueur.getInv()).getStade() + FacadeMoteur.getMerveille(gauche).getStade() + FacadeMoteur.getMerveille(droit).getStade();
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS7COMPLETMERVEILLE) && FacadeMoteur.getMerveille(joueur.getInv()).getStade()==FacadeMoteur.getMerveille(joueur.getInv()).getEtape().size()) {
            return 7;
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BOUCLIER) && FacadeMoteur.getAge(plateau)==3 && ((bouclierDroit-bouclierJoueur>=0 && bouclierDroit-bouclierJoueur<=2) && (bouclierGauche-bouclierJoueur>=0 && bouclierGauche-bouclierJoueur<=2))) {
            return 10;
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BOUCLIER) && FacadeMoteur.getAge(plateau)==3 && ((bouclierDroit-bouclierJoueur>=0 && bouclierDroit-bouclierJoueur<=2) || (bouclierGauche-bouclierJoueur>=0 && bouclierGauche-bouclierJoueur<=2))) {
            return 5;
        }
        return 0;
    }
    /**
     * Cette méthode permet de crée la liste des ressources recherchée en fonction de l'AGE
     * @param j le joueur
     * @param plateau le plateau de jeu (pour connaitre l'age)
     * @return la liste de ressources recherchées
     */
    public List<EnumRessources> rechercheRessources(Joueur j, Plateau plateau){
        List<EnumRessources> ressourcesrecherchees= new ArrayList<>();
        int age = FacadeMoteur.getAge(plateau);
        if(age==1){
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.BOIS);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.PIERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.ARGILE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.MINERAI);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.TISSU);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.VERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.PAPYRUS);
        }
        else if(age == 2){
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.BOIS);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.PIERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.ARGILE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.MINERAI);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,2,EnumRessources.BOIS);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,2,EnumRessources.PIERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,2,EnumRessources.ARGILE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,2,EnumRessources.MINERAI);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.TISSU);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.VERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.PAPYRUS);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,3,EnumRessources.BOIS);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,3,EnumRessources.PIERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,3,EnumRessources.ARGILE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,3,EnumRessources.MINERAI);
        }
        return ressourcesrecherchees;
    }

    /**
     * Calcule le nombre de points qu'une nouvelle ressource scientifique ferait gagner au joueur
     * @param objet la ressource scientifique en question
     * @param joueur le joueur
     * @return le nombre de points potentiels
     */
    public int getScientistPoints(EnumRessources objet,Joueur joueur){
        int bonus = 0 ;
        int besoin = 0;
        int stockObjet = FacadeMoteur.getValue(joueur.getInv(),objet);
        bonus += Math.pow((stockObjet+1),2) - Math.pow(stockObjet,2);
        if(!EnumRessources.ROUE.equals(objet) && FacadeMoteur.getValue(joueur.getInv(),EnumRessources.ROUE)>stockObjet){
            besoin += 1 ;
        }
        if(!EnumRessources.COMPAS.equals(objet) && FacadeMoteur.getValue(joueur.getInv(),EnumRessources.COMPAS)>stockObjet){
            besoin += 1 ;
        }
        if(!EnumRessources.PDR.equals(objet) && FacadeMoteur.getValue(joueur.getInv(),EnumRessources.PDR)>stockObjet){
            besoin += 1 ;
        }
        if(besoin==2){
            bonus+=7;
        }
        return bonus;
    }

    /**
     * Méthode qui permet de decider si le joueur devrait se défendre ou non
     * @param joueur le joueur
     * @param plateau le plateau de jeu
     * @return un boolean
     */
    public boolean besoinDeBouclier(Joueur joueur, Plateau plateau){
        int frontGauche = FacadeMoteur.getValue(FacadeMoteur.joueurGauche(plateau,joueur).getInv(),EnumRessources.BOUCLIER);
        int frontDroite = FacadeMoteur.getValue(FacadeMoteur.joueurDroit(plateau,joueur).getInv(),EnumRessources.BOUCLIER);
        int frontSelf = FacadeMoteur.getValue(joueur.getInv(),EnumRessources.BOUCLIER);
        return frontSelf <= frontDroite || frontSelf <= frontGauche;
    }
    /**
     * Choisit une carte à jouer depuis la défausse
     * @param j joueur
     * @param paquetDefausse le paquet de carte de la défausse où le joueur pourra jouer une carte gratuitement
     * @param plateau le plateau de jeu
     * @return Index de la carte à jouer depuis la défausse
     */
    public int choisirCarteDeLaDefausse(Joueur j, List<Carte> paquetDefausse, Plateau plateau){
        return choixMain(j,paquetDefausse,plateau,false);
    }
}
