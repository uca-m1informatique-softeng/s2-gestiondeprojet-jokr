package joueur;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Wonder;
import objet_commun.Carte;
import utilitaire_jeu.Construction;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class IAcivil implements IA {

    /**
     * appel la méthode Choix carte dans l'interface IA pour décider quoi jouer en lui fournissant la liste de ses priorités
     * @param j le joueur
     * @param main sa main de cartes
     * @param plateau le plateau de jeu
     * @return Index de la carte à jouer depuis la main
     */
    @Override
    public int choixMain(Joueur j , List<Carte> main, Inventaire invJoueur, Plateau plateau, boolean prix){
        List<EnumRessources> ressourcesrecherchees = rechercheRessources(j,invJoueur,plateau);
        List<String> carteRecherchee ;
        if(plateau.getAge()==1){
            carteRecherchee = Arrays.asList("Bains","Autel","Puits","Théâtre") ;
        }

        else if(plateau.getAge()==2){
            carteRecherchee = Arrays.asList("Aqueduc","Temple","Statue","Tribunal");
        }
        else {
            carteRecherchee = Arrays.asList("Panthéon", "Jardins", "Palace", "Hôtel de Ville", "Sénat");

        }
        return choixCarte(j,main,invJoueur,plateau,carteRecherchee,ressourcesrecherchees,true,prix);
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
        if(age==1){ // les ressources primordiales pour la stratégie civile
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.PIERRE);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.TISSU);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.MINERAI);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.ARGILE);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.VERRE);
        }
        else if(age == 2){ // ces autres ressources sont nécessaires pour acheter les cartes bleues de l'âge
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,invJoueur,2,EnumRessources.BOIS);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,invJoueur,1,EnumRessources.TISSU);
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,invJoueur,2,EnumRessources.PIERRE);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,invJoueur,2,EnumRessources.ARGILE);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,invJoueur,2,EnumRessources.MINERAI);

            if(!invJoueur.getListeCarte().contains(EnumCarte.B1)) {
                ressourcesrecherchees = listeRessource(ressourcesrecherchees, j, invJoueur,1, EnumRessources.PAPYRUS);
            }
            if(!invJoueur.getListeCarte().contains(EnumCarte.B3)) { // si il ne possède pas la carte chainée Bains
                ressourcesrecherchees = listeRessource(ressourcesrecherchees, j, invJoueur,3, EnumRessources.PIERRE);
            }
        }
        return ressourcesrecherchees;
    }


    /**
     * renvoie si oui ou non il souhaite construire sa merveille
     * @param j le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @return True Construire / False sinon
     */
    @Override
    public boolean choixMerveille(Joueur j, List<Carte> main, Inventaire invJoueur, Plateau plateau){
        List<Wonder> merveilles = Collections.emptyList();
        if(merveilles.contains(invJoueur.getMerveille().getNom()) && Boolean.TRUE.equals(invJoueur.getMerveille().peutAmeliorerMerveille())){
            for(int i = 0 ; i < main.size() ; i++) {
                if (Construction.permisDeConstruction( invJoueur.getMerveille().getCarteAConstruire(),invJoueur, plateau.joueurGauche(invJoueur), plateau.joueurDroit(invJoueur),plateau)) {
                    return true; // Si l'étage de la merveille est constructible par le joueur alors il décide de construire la merveille
                }
            }
        }
        return false ;
    }


    /**
     * Choisit une carte a sacrifier pour construire la merveille (de préférence une qu'il ne pouvait pas construire de base
     * @param j le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @return Index de la carte à sacrifier pour construire la Merveille
     */
    @Override
    public int choixCartePourMerveille(Joueur j, List<Carte> main,Inventaire invJoueur, Plateau plateau) {
        for (int i = 0; i < main.size(); i++) {
            if (!Construction.permisDeConstruction(main.get(i), invJoueur, plateau.joueurGauche(invJoueur), plateau.joueurDroit(invJoueur),plateau)) {
                return i;
            }
        }
        return 1;
    }


    /**
     * Choisit une carte à jouer depuis la défausse
     * @param j joueur
     * @param paquetDefausse le paquet de carte de la défausse où le joueur pourra jouer une carte gratuitement
     * @param plateau le plateau de jeu
     * @return Index de la carte à jouer depuis la défausse
     */
    public int choisirCarteDeLaDefausse(Joueur j, List<Carte> paquetDefausse, Inventaire invJoueur, Plateau plateau){
        return choixMain(j,paquetDefausse,invJoueur,plateau,false);
    }


}
