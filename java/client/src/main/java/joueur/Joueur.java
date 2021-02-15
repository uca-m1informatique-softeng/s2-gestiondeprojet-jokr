package joueur;

import metier.EnumRessources;
import metier.Strategy;
import objet_commun.Carte;
import utilitaire_jeu.*;
import java.util.List;
import java.util.Objects;

public class Joueur {
    private final int id;
    private IA bot;
    private final String name;
    private final Strategy strategie;



    /**
     * Constructeur Joueur
     * @param id L'identifiant unique du joueur
     * @param strategie La stratégie du joueur
     * @param name Le nom du joueur
     */
    public Joueur(int id, Strategy strategie, String name) {
        this.id = id;
        this.strategie = strategie;
        this.name = name;
        switch (strategie) {
            case MONETAIRE:
                this.bot = new IAmonetaire();
                break;
            case MERVEILLE:
                this.bot = new IAmerveille();
                break;
            case CIVILE:
                this.bot = new IAcivil();
                break;
            case MILITAIRE:
                this.bot = new IAmilitaire();
                break;
            case SCIENTIFIQUE:
                this.bot = new IAscientifique();
                break;
            case COMPOSITE:
                this.bot = new IAcomposite();
                break;
            case AMBITIEUSE:
                this.bot = new IAambitieuse();
                break;
            default:
                this.bot = new IArandom();
                break;
        }
    }

    // METHODE


    /**
     * retourne la réponse du joueur selon son IA de si oui ou non il choisit de défausser sa carte
     * @param carte La carte que le joueur va joueur ou défausser
     * @param plateau Le plateau du jeu
     * @return True Défausse / False sinon (jouer)
     */
    public Boolean jouerdefausse(List<Carte> carte, Inventaire invJoueur, Plateau plateau){
        return bot.choixDefausse(this, carte.get(0) ,invJoueur , plateau);
    }

    /**
     * Demande à l'IA si elle veut acheter à gauche ou à droite
     * @param ressource la ressource à acheter
     * @param gauche l'inventaire du joueur de gauche
     * @param droite l'inventaire du joueur de droite
     * @return True Gauche / False Droite
     */
    public Boolean achatRessource(EnumRessources ressource, Inventaire invJoueur, Inventaire gauche, Inventaire droite){
        return bot.commerceAdjacent(ressource,this,invJoueur, gauche,droite);
    }

    /**
     * Demande à l'IA quelle carte de la défausse elle souhaite jouer
     * @param paquetDefausse Le paquet de défausse
     * @param plateau Le plateau de jeu (contenant les inventaires des autres joueur)
     * @return Index de la carte à jouer depuis la défausse
     */
    public int jouerGratuitementDanslaDefausse(List<Carte> paquetDefausse,Inventaire invJoueur, Plateau plateau){
        return bot.choisirCarteDeLaDefausse(paquetDefausse,plateau);
    }


    /**
     * Retourne si oui ou non le joueur souhaite construire sa merveille
     * @param main La main de carte du joueur
     * @param plateau Le plateau de jeu
     * @return True Construire la merveille / False sinon
     */
    public Boolean jouerMerveille(List<Carte> main,Inventaire invJoueur, Plateau plateau){
        return bot.choixMerveille(this, main,invJoueur, plateau);
    }

    /**
     * Choisit une carte dans la main à sacrifier pour construire la merveille
     * @param main les cartes de la main
     * @param plateau le plateau de jeu
     * @return Index de la carte à sacrifier pour construire la merveille
     */
    public int constructMerveille(List<Carte> main,Inventaire invJoueur, Plateau plateau){
        return bot.choixCartePourMerveille(this, main,invJoueur, plateau);
    }


    /**
     * retourne la réponse du joueur selon son IA de quel carte il  choisit de jouer/défausser dans sa main
     * @param main La main de carte du joueur
     * @param plateau le plateau de jeu
     * @return L'indice de la carte que le joueur veut jouer
     */
    public int choixCarte(List<Carte> main,Inventaire invJoueur, Plateau plateau){
        return bot.choixMain(this , main,invJoueur, plateau,true);
    }


    /**
     * @return l'IA du joueur
     */
    public IA getBot() {
        return bot;
    }

    /**
     * @return le nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * @return l'ID du joueur
     */
    public int getId() {
        return id;
    }


    /**
     * Redefinition de la la méthode equals
     * @param obj L'objet a comparer
     * @return True les deux objets sont égaux / False sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Joueur) {
            Joueur j = (Joueur) obj;
            return this.getId() == (j.getId());
        }
        return false;
    }


    /**
     * Redéfinition de la méthode hashCode
     * @return Hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    /**
     * Pour les tests Mockito
     * @param bot L'IA du joueur
     */
    public void setBot(IA bot) {
        this.bot = bot;
    }



    /**
     * Permet d'obtenir la stratégie du joueur
     * @return La stratégie du joueur
     */
    public String getStrategie() {
        switch (strategie) {
            case RANDOM:
                return "IA Random";
            case MERVEILLE:
                return "IA Merveille";
            case MONETAIRE:
                return "IA Monétaire";
            case CIVILE:
                return "IA Civil";
            case MILITAIRE:
                return "IA Militaire";
            case SCIENTIFIQUE:
                return "IA Scientifique";
            case COMPOSITE:
                return "IA Composite";
            case AMBITIEUSE:
                return "IA Ambitieuse";
            default:
                return strategie.toString();
        }
    }
}