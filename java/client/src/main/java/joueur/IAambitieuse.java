package joueur;


import metier.EnumCarte;
import metier.EnumRessources;
import objet_commun.Carte;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;

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
    public boolean choixMerveille(Joueur j, List<Carte> main, Inventaire invJoueur, Plateau plateau){
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
    public int choixMain(Joueur joueur , List<Carte> main, Inventaire invJoueur,  Plateau plateau,boolean prix){
        List<EnumRessources> ressourcesrecherchees = rechercheRessources(joueur,invJoueur,plateau);
        List<String> carteRecherchee ;
        if(plateau.getAge()==1){
            carteRecherchee = Collections.emptyList();
        }
        else if(plateau.getAge()==2){
            carteRecherchee = Collections.emptyList();
        }
        else {
            carteRecherchee = Collections.emptyList();
            return choixCarteAge3(joueur,main,invJoueur,plateau,carteRecherchee,ressourcesrecherchees,besoinDeBouclier(joueur,invJoueur,plateau),prix);
        }
        return choixCarte(joueur,main,invJoueur,plateau,carteRecherchee,ressourcesrecherchees,besoinDeBouclier(joueur,invJoueur,plateau),prix);
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
    public int choixCarteAge3(Joueur joueur , List<Carte> main, Inventaire invJoueur,Plateau plateau, List<String> carteRecherchee, List<EnumRessources> ressourcesRecherchee,boolean bouclier, boolean prix){
        List<Carte> possibilites = getPossibilites(joueur,main,invJoueur,plateau,prix);
        int max = 0 ;
        int temp;
        int idMax = 0 ;
        if (!possibilites.isEmpty()) {
            for(int i=0; i<possibilites.size(); i++){
                if(possibilites.get(i).getCouleur().equals(EnumRessources.BLEUE)){
                    temp = possibilites.get(i).getGain().size();
                }else if(possibilites.get(i).getCouleur().equals(EnumRessources.VIOLETTE) || possibilites.get(i).getCouleur().equals(EnumRessources.JAUNE) || possibilites.get(i).getCouleur().equals(EnumRessources.ROUGE)){
                    temp = getTotalPoints(possibilites.get(i), joueur,invJoueur, plateau);
                }else if(possibilites.get(i).getCouleur().equals(EnumRessources.VERTE)){
                    temp = getScientistPoints(possibilites.get(i).getGain().get(0), joueur, invJoueur) ;
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
    public int getTotalPoints(Carte carte, Joueur joueur,Inventaire invJoueur, Plateau plateau){
        Inventaire gauche = plateau.joueurGauche(invJoueur);
        Inventaire droit = plateau.joueurDroit(invJoueur);

        int bouclierGauche = gauche.getValue(EnumRessources.BOUCLIER);
        int bouclierDroit = droit.getValue(EnumRessources.BOUCLIER);
        int bouclierJoueur = invJoueur.getValue(EnumRessources.BOUCLIER);

        if(carte.getGain().get(0).equals(EnumRessources.BONUS11J)){
            return invJoueur.getValue(EnumRessources.JAUNE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BONUS11M)){
            return invJoueur.getValue(EnumRessources.MARRON);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BONUS22G)){
            return (2*invJoueur.getValue(EnumRessources.GRISE));
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BONUS31R)){
            return invJoueur.getValue( EnumRessources.ROUGE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BONUS31MERVEILLE)){
            return invJoueur.getMerveille().getStade();
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1M)){
            return droit.getValue(EnumRessources.MARRON) + gauche.getValue(EnumRessources.MARRON);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS2G)){
            return 2*(gauche.getValue(EnumRessources.GRISE) + droit.getValue(EnumRessources.GRISE));
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1B)){
            return gauche.getValue(EnumRessources.BLEUE) + droit.getValue(EnumRessources.BLEUE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1J)){
            return gauche.getValue(EnumRessources.JAUNE) + droit.getValue(EnumRessources.JAUNE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1R)){
            return gauche.getValue(EnumRessources.ROUGE) + droit.getValue(EnumRessources.ROUGE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1V)){
            return gauche.getValue(EnumRessources.VERTE) + droit.getValue(EnumRessources.VERTE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUSMGV)){
            return invJoueur.getValue(EnumRessources.MARRON) + invJoueur.getValue( EnumRessources.GRISE) + invJoueur.getValue(EnumRessources.VIOLETTE);
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS1MERVEILLE)){
            return invJoueur.getMerveille().getStade() + gauche.getMerveille().getStade() + droit.getMerveille().getStade();
        }
        else if(carte.getGain().get(0).equals(EnumRessources.VBONUS7COMPLETMERVEILLE) && invJoueur.getMerveille().getStade()==invJoueur.getMerveille().getEtape().size()) {
            return 7;
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BOUCLIER) && plateau.getAge()==3 && ((bouclierDroit-bouclierJoueur>=0 && bouclierDroit-bouclierJoueur<=2) && (bouclierGauche-bouclierJoueur>=0 && bouclierGauche-bouclierJoueur<=2))) {
            return 10;
        }
        else if(carte.getGain().get(0).equals(EnumRessources.BOUCLIER) && plateau.getAge()==3 && ((bouclierDroit-bouclierJoueur>=0 && bouclierDroit-bouclierJoueur<=2) || (bouclierGauche-bouclierJoueur>=0 && bouclierGauche-bouclierJoueur<=2))) {
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
    public List<EnumRessources> rechercheRessources(Joueur j,Inventaire invJoueur, Plateau plateau){
        List<EnumRessources> ressourcesrecherchees= new ArrayList<>();
        int age = plateau.getAge();
        if(age==1){
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.BOIS);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.PIERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.ARGILE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.MINERAI);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.TISSU);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.VERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.PAPYRUS);
        }
        else if(age == 2){
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.BOIS);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.PIERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.ARGILE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.MINERAI);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,2,EnumRessources.BOIS);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,2,EnumRessources.PIERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,2,EnumRessources.ARGILE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,2,EnumRessources.MINERAI);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.TISSU);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.VERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.PAPYRUS);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,3,EnumRessources.BOIS);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,3,EnumRessources.PIERRE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,3,EnumRessources.ARGILE);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,3,EnumRessources.MINERAI);
        }
        return ressourcesrecherchees;
    }

    /**
     * Calcule le nombre de points qu'une nouvelle ressource scientifique ferait gagner au joueur
     * @param objet la ressource scientifique en question
     * @param joueur le joueur
     * @return le nombre de points potentiels
     */
    public int getScientistPoints(EnumRessources objet,Joueur joueur,Inventaire invJoueur){
        int bonus = 0 ;
        int besoin = 0;
        int stockObjet = invJoueur.getValue(objet);
        bonus += Math.pow((stockObjet+1),2) - Math.pow(stockObjet,2);
        if(!EnumRessources.ROUE.equals(objet) && invJoueur.getValue(EnumRessources.ROUE)>stockObjet){
            besoin += 1 ;
        }
        if(!EnumRessources.COMPAS.equals(objet) && invJoueur.getValue(EnumRessources.COMPAS)>stockObjet){
            besoin += 1 ;
        }
        if(!EnumRessources.PDR.equals(objet) && invJoueur.getValue(EnumRessources.PDR)>stockObjet){
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
    public boolean besoinDeBouclier(Joueur joueur,Inventaire invJoueur, Plateau plateau){
        int frontGauche = plateau.joueurGauche(invJoueur).getValue(EnumRessources.BOUCLIER);
        int frontDroite = plateau.joueurDroit(invJoueur).getValue(EnumRessources.BOUCLIER);
        int frontSelf = invJoueur.getValue(EnumRessources.BOUCLIER);
        return frontSelf <= frontDroite || frontSelf <= frontGauche;
    }
    /**
     * Choisit une carte à jouer depuis la défausse
     * @param j joueur
     * @param paquetDefausse le paquet de carte de la défausse où le joueur pourra jouer une carte gratuitement
     * @param plateau le plateau de jeu
     * @return Index de la carte à jouer depuis la défausse
     */
    public int choisirCarteDeLaDefausse(Joueur j, List<Carte> paquetDefausse,Inventaire invJoueur, Plateau plateau){
        return choixMain(j,paquetDefausse,invJoueur,plateau,false);
    }
}
