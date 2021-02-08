package joueur;

import metier.EnumRessources;
import metier.Wonder;
import objet_commun.Carte;
import sw_aventure.seven_wonders.FacadeMoteur;
import sw_aventure.seven_wonders.Plateau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IAscientifique  implements IA{

    /**
     * appel la méthode Choix carte dans l'interface IA pour décider quoi jouer en lui fournissant la liste de ses priorités
     * @param j le joueur
     * @param main les cartes de la main
     * @param plateau le plateau de jeu
     * @param prix si les cartes sont payantes ou non
     * @return Index de la carte à jouer de la main
     */
    @Override
    public int choixMain(Joueur j, List<Carte> main, Plateau plateau,boolean prix){
        List<EnumRessources> ressourcesrecherchees = rechercheRessources(j,plateau);
        List<String> carteRecherchee;
        if(FacadeMoteur.getAge(plateau)==1) {
            carteRecherchee = Arrays.asList("Métier à Tisser", "Officine","Verrerie", "Atelier","Presse","Scriptorium");
        }

        else if(FacadeMoteur.getAge(plateau)==2) {
            carteRecherchee = Arrays.asList("Dispensaire","Laboratoire","Bibliothèque","Ecole");
        }
        else {
            carteRecherchee = Arrays.asList("Guilde des Scientifiques", "Loge", "Académie", "Observatoire", "Etude", "Université");
        }
        return choixCarte(j,main,plateau,carteRecherchee,ressourcesrecherchees,false,prix);
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
        if(age!=3){ // les ressources primordiales pour la stratégie scientifique on ne les obtiens qu'aux tours 1 et 2
            ressourcesrecherchees = listeRessource(ressourcesrecherchees,j,1,EnumRessources.VERRE);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,1,EnumRessources.PAPYRUS);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,1,EnumRessources.TISSU);
        }
        if(age==2){ // ces autres ressources sont nécessaires pour acheter les cartes verte de l'âge
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,2,EnumRessources.BOIS);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,3,EnumRessources.PIERRE);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,2,EnumRessources.ARGILE);
            ressourcesrecherchees =listeRessource(ressourcesrecherchees,j,2,EnumRessources.MINERAI);
        }
        return ressourcesrecherchees;

    }



    /**
     * renvoie si oui ou non il souhaite construire sa merveille
     * @param j le joueur
     * @param main les cartes de la main
     * @param plateau le plateau de jeu
     * @return True construire / false sinon
     */
    @Override
    public boolean choixMerveille(Joueur j, List<Carte> main, Plateau plateau){
        List<Wonder> merveilles = Collections.emptyList();
        return choixConstrMerveille(j,main,plateau,merveilles);
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