package joueur;

import metier.EnumRessources;
import metier.Wonder;
import objet_commun.Carte;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IAmilitaire  implements IA{

    /**
     * appel la méthode Choix carte dans l'interface IA pour décider quoi jouer en lui fournissant la liste de ses priorités
     * @param j le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @param prix si les cartes sont payantes ou non
     * @return Index de la carte a jouer dans la main
     */
    @Override
    public int choixMain(Joueur j, List<Carte> main,Inventaire invJoueur, Plateau plateau, boolean prix){
        ArrayList<EnumRessources> ressourcesRecherchee = new ArrayList<>();
        List<String> carteRecherchee ;
        if(plateau.getAge()==1){
            carteRecherchee = Arrays.asList("Palissade","Caserne","Tour de Garde","Comptoir OUEST","Comptoir EST") ;
        }

        else if(plateau.getAge()==2){
            carteRecherchee = Arrays.asList("Caravansérail","Forum","Ecuries","Champs de Tir","Muraille","Place d'Armes");
        }
        else {
            carteRecherchee = Arrays.asList("Ludus", "Fortifications", "Cirque", "Arsenal", "Atelier de Siège", "Castrum");
        }
        return choixCarte(j,main,invJoueur,plateau,carteRecherchee,ressourcesRecherchee,true,prix);
    }

    /**
     * renvoie si oui ou non il souhaite construire sa merveille
     * @param j le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @return True construire / False sinon
     */
    @Override
    public boolean choixMerveille(Joueur j, List<Carte> main, Inventaire invJoueur, Plateau plateau){
        List<Wonder> merveilles = Arrays.asList(Wonder.RHODOS, Wonder.RHODOSNUIT, Wonder.HALIKARNASSOS, Wonder.HALIKARNASSOSNUIT, Wonder.OLYMPIA, Wonder.OLYMPIANUIT);
        return choixConstrMerveille(j,main,invJoueur,plateau,merveilles);
    }

    /**
     * Choisit une carte à jouer depuis la défausse
     * @param j joueur
     * @param paquetDefausse le paquet de carte de la défausse où le joueur pourra jouer une carte gratuitement
     * @param plateau le plateau de jeu
     * @return Index de la carte a jouer depuis la défausse
     */
    public int choisirCarteDeLaDefausse(Joueur j, List<Carte> paquetDefausse,Inventaire invJoueur,Plateau plateau){
        return choixMain(j,paquetDefausse,invJoueur,plateau,false);
    }





}
