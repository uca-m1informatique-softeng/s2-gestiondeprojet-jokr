package sw_aventure.joueur;

import metier.EnumRessources;
import metier.Wonder;
import objet_commun.Carte;
import sw_aventure.seven_wonders.FacadeMoteur;
import sw_aventure.seven_wonders.Plateau;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IAmonetaire implements IA {

    /**
     * appel la méthode Choix carte dans l'interface IA pour décider quoi jouer en lui fournissant la liste de ses priorités
     * @param j le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @param prix si les cartes sont gratuites ou non
     * @return Index de la carte à jouer dans la main
     */
    @Override
    public int choixMain(Joueur j, List<Carte> main, Plateau plateau,boolean prix){
        ArrayList<EnumRessources> ressourcesRecherchee = new ArrayList<>();
        List<String> carteRecherchee ;
        if(FacadeMoteur.getAge(plateau)==1){
            carteRecherchee = Arrays.asList("Taverne","Marché","Comptoir OUEST","Comptoir EST","Friche","Excavation","Fosse Argileuse","Exploitation Forestière","Gisement","Mine","Verrerie","Presse","Métier à Tisser") ;
        }
        else if(FacadeMoteur.getAge(plateau)==2){
            carteRecherchee = Arrays.asList("Vignoble","Bazar","Scierie","Carrière","Briqueterie","Fonderie","Verrerie","Presse","Métier à Tisser");
        }
        else {
            carteRecherchee = Arrays.asList("Phare","Guilde des Armateurs","Guilde des Espions", "Guilde des Philosophes","Guilde des Magistrats","Port","Chambre de Commerce");
        }
        return choixCarte(j,main,plateau,carteRecherchee,ressourcesRecherchee,true,prix);
    }


    /**
     * renvoie si oui ou non il souhaite construire sa merveille
     * @param j le joueur
     * @param main les cartes de sa main
     * @param plateau le plateau de jeu
     * @return True construire / False sinon
     */
    @Override
    public boolean choixMerveille(Joueur j, List<Carte> main, Plateau plateau){
        List<Wonder> merveilles = Arrays.asList(Wonder.EPHESOS, Wonder.EPHESOSNUIT, Wonder.RHODOS, Wonder.RHODOSNUIT);
        return choixConstrMerveille(j,main,plateau,merveilles);
    }

    /**
     * Choisit une carte à jouer depuis la défausse
     * @param j joueur
     * @param paquetDefausse le paquet de carte de la défausse où le joueur pourra jouer une carte gratuitement
     * @param plateau le plateau de jeu
     * @return Index de la carte dans la défausse
     */
    public int choisirCarteDeLaDefausse(Joueur j, List<Carte> paquetDefausse, Plateau plateau){
        return choixMain(j,paquetDefausse,plateau,false);
    }
}
