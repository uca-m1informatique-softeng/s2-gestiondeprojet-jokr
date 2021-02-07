package sw_aventure.seven_wonders;


import java.security.SecureRandom;
import java.util.*;
import exception.NegativeNumberException;
import metier.*;
import objet_commun.Merveille;
import sw_aventure.objetjeu.*;
import sw_aventure.joueur.Joueur;
import org.json.JSONArray;
import utils.affichage.Colors;
import utils.affichage.LoggerSevenWonders;
import reseau.Connexion;

/**
 * Classe du main où la partie se lance
 */
public class SevenWonders {
    private final SecureRandom r = new SecureRandom();
    protected List<SetInventaire> inv = new ArrayList<>();
    private final GenererMerveille genererMerveille = new GenererMerveille();
    private static boolean color = true;



    /**
     * Initialise une partie avec le nombre de joueur
     * @param nbJoueurs le nombre de joueurs
     * @param print (pour les tests) afficher ou non les prints
     * @param color afficher ou non en couleur
     */

    public SevenWonders(int nbJoueurs, boolean print, boolean color) {
        Colors.setColor(color);
        LoggerSevenWonders.init(print);
        initPlayers(nbJoueurs,true);
    }




    // METHODES //



    /**
     * Crée les joueurs, rempli la liste des inventaires
     * @param nbJoueurs le nombre de joueurs qui vont jouer
     */
    public void initPlayers(int nbJoueurs,boolean shuffle) {
        inv = new ArrayList<>();
        List<String> names = Arrays.asList(Colors.igBleu("Enzo"), Colors.igJaune("Mona"),Colors.igCyan("Fred"), Colors.igVert("Paul"), Colors.igRouge("Lucy"),  Colors.igViolet("Dora"),Colors.igViolet("Alex"));
        List<Strategy> strategies = Arrays.asList(Strategy.RANDOM, Strategy.AMBITIEUSE, Strategy.COMPOSITE,Strategy.MONETAIRE, Strategy.MILITAIRE, Strategy.SCIENTIFIQUE,Strategy.CIVILE);
        for (int i = 0; i < nbJoueurs; i++) {
            inv.add(new SetInventaire(i, strategies.get(i), names.get(i)));
        }
        if(shuffle){
            Collections.shuffle(inv);
            Collections.shuffle(inv);
        }
    }



    /**
     * Crée le plateau de jeu en lui attribuant les inventaires et les joueurs
     * @return le plateau de jeu
     */
    public Plateau initPlateau(){
        ArrayList<Inventaire> listeInventaire = new ArrayList<>();
        ArrayList<Joueur> listeJoueur = new ArrayList<>();
        for (SetInventaire setInventaire : inv) {
            listeInventaire.add(setInventaire);
            listeJoueur.add(setInventaire.getJoueur());
        }
        return new Plateau(listeInventaire, listeJoueur);
    }


    /**
     * Donne une merveille aléatoirement à chaque joueurs
     */
    public void attributionMerveille() throws NegativeNumberException {
        List<List<Wonder>> merveille = new ArrayList<>();
        merveille.add(Arrays.asList(Wonder.BABYLON,Wonder.BABYLONNUIT));
        merveille.add(Arrays.asList(Wonder.OLYMPIA,Wonder.OLYMPIANUIT));
        merveille.add(Arrays.asList(Wonder.GIZAH,Wonder.GIZAHNUIT));
        merveille.add(Arrays.asList(Wonder.RHODOS,Wonder.RHODOSNUIT));
        merveille.add(Arrays.asList(Wonder.ALEXANDRIA,Wonder.ALEXANDRIANUIT));
        merveille.add(Arrays.asList(Wonder.HALIKARNASSOS,Wonder.HALIKARNASSOSNUIT));
        merveille.add(Arrays.asList(Wonder.EPHESOS,Wonder.EPHESOSNUIT));

        Collections.shuffle(merveille);
        Collections.shuffle(merveille);
        int temporalite;
        for(int i = 0 ; i < inv.size() ; i++){
            temporalite = r.nextInt(2);
            Merveille laMerveille = genererMerveille.getMerveille(merveille.get(i).get(temporalite));
            inv.get(i).modifMerveille(laMerveille);
            LoggerSevenWonders.ajoutln(FacadeJoueur.getName(inv.get(i).getJoueur()) + " a obtenue la merveille "+ merveille.get(i).get(temporalite) + " et gagne 1 "+ laMerveille.getGain());

            inv.get(i).increaseValue(laMerveille.getGain(),1);
        }
    }


    /**
     * Méthode permettant de créer la partie en appelant les différentes méthode/étapes du jeu
     * @param nbJoueurs le nombre de joueurs
     */
    DeroulementJeu jeu;

    public void partie(int nbJoueurs) throws NegativeNumberException {

        Plateau plateau = initPlateau();

        LoggerSevenWonders.ajoutln("____________________     7 Wonders       ________________________");
        LoggerSevenWonders.ajoutln("____________________ Commencement du Jeu ________________________\n");

        attributionMerveille();
         jeu = new DeroulementJeu(inv);
        jeu.laPartie(plateau, nbJoueurs);

        LoggerSevenWonders.ajoutln("_____________________________ Fin de la partie _____________________________");
        inv = triInventaire(inv);
        envoyerStat(inv);
    }
    /**
     * Set la graine du SecureRandom à une valeur fixe
     * @param mySeed la valeur initale auquel on préfixe le SecureRandom
     */
    public void setTheSeed( int mySeed ) {
        r.nextBytes(new byte[8]);
        r.setSeed(mySeed);
        jeu.setTheSeed(mySeed);
    }
    /**
     * Permet de ré-ordonner les joueurs pour permettre au serveur de faire des statistiques correctes
     * @param setInv la liste de tous les SetInventaires
     * @return la liste triée de tous les SetInventaires
     */
    public List<SetInventaire> triInventaire(List<SetInventaire> setInv){
        List<SetInventaire> inventaireClasse = new ArrayList<>();
        for(int i = 0 ; i<setInv.size();i++) {
            for (SetInventaire s : setInv) {
                if (s.getId() == i) {
                    inventaireClasse.add(s);
                }
            }
        }
        return inventaireClasse;
    }

    /**
     * Méthode permettant d'envoyer les données de la partie au serveur
     * @param setInv Liste des SetInventaires
     */
    public void envoyerStat(List<SetInventaire> setInv) {
        String nomJoueur;
        String strategieJoueur;
        List<EnumCarte> cartes;
        String merveille;
        Map<EnumRessources, Integer> inventaire;

        JSONArray jsonArray = new JSONArray();
        for (SetInventaire s : setInv) {
            nomJoueur = FacadeJoueur.getName(s.getJoueur());
            strategieJoueur = FacadeJoueur.getStrategie(s.getJoueur());
            inventaire = FacadeJoueur.getInv(s.getJoueur()).getSac();
            merveille = s.getMerveille().getNom().toString();
            cartes = s.getListeCarte();
            try {
                jsonArray.put(new Data(nomJoueur, strategieJoueur, getSactoString(inventaire), merveille, getListeCarteToString(cartes)).toJSON());
            }
            catch (Exception ignored){
                // JsonArray échoué
            }
        }
        Connexion.CONNEXION.envoyerMessageArray("DataPartie", jsonArray);
    }

    /**
     * Permet de changer les EnumCartes en strings pour les envoyer au serveur
     * @param cartes la liste de carte d'un joueur
     * @return une liste de strings
     */
    public List<String> getListeCarteToString(List<EnumCarte> cartes){
        List<String> listeCarte = new ArrayList<>();
        for(EnumCarte carte : cartes){
            listeCarte.add(carte.toString());
        }
        return listeCarte ;
    }

    /**
     * Permet d'obtenir les strings des enumsRessources pour les envoyer au serveur
     * @param sac le sac d'un joueur
     * @return un sac de Strings
     */
    public Map<String,Integer> getSactoString(Map<EnumRessources,Integer> sac){
        Map<String,Integer> sactoString = new HashMap<>() ;
        for (Map.Entry<EnumRessources,Integer> key : sac.entrySet()){
            sactoString.put( key.getKey().toString(), key.getValue());
        }
        return sactoString ;
    }


    /**
     * main : lancement du jeu
     * @param args Arguments en lançant le programme par ligne de commande :
     *             1ème argument : indiquer "false" pour afficher sans couleur (peut être utile si l'on souhaite lire directement le .txt ou si on utilise un terminal qui ne reconnait pas le code ANSI)
     *             2ème argument : indiquer le nombre de parties à lancer (une si rien d'indiqué ou si pas un entier)
     *             3ème argument : indiquer le nombre de joueurs qui vont participer aux parties (3-7)
     *             4ème argument : indiquer false si on ne veut ne lancer qu'une partie (ne lance pas les statistiques et n'écrit pas dans un fichier)
     *             Par défaut : true 1 3 false : on lance une partie à 3 joueurs que l'on affiche sur la sortie standard avec les couleurs
     */
    public static void main(String[] args) throws NegativeNumberException {
        // Nombres de joueurs
        int nbJoueurs ;
        // Nombres de parties si option des statistique activée
        int nbParties ;
        // Active plusieurs parties avec statistiques
        boolean multiPartieAvecStat = false;
        try {
            if (args[0].equals("false")) {
                color = false;
            }
        } catch (Exception e) {
            color = true;
        }
        try {
            nbParties = Integer.parseInt(args[1]);
        } catch(Exception e) {
            nbParties = 1;
        }
        try {
            nbJoueurs = Integer.parseInt(args[2]);
        } catch(Exception e) {
            nbJoueurs = 3;
        }
        try {
            if (args[3].equals("true")) {
                multiPartieAvecStat = true;
                color = false;
            }
        }
        catch (Exception ignored) {
            // Lancement d'une partie normale
        }


        if (multiPartieAvecStat) {
            Connexion.CONNEXION.demarrerEcoute();
            Connexion.CONNEXION.envoyerMessageBoolean("Initialisation", true);
            Connexion.CONNEXION.envoyerMessageInt("NombresJoueur", nbJoueurs);
            if (nbParties == 1) {
                SevenWonders sevenWonders = new SevenWonders(nbJoueurs, true, color);
                LoggerSevenWonders.init(true);
                sevenWonders.partie(nbJoueurs);
                LoggerSevenWonders.show(LoggerSevenWonders.getStringBuilder());
                Connexion.CONNEXION.envoyerMessageStringBuilder("Partie", LoggerSevenWonders.getStringBuilder());
                Connexion.CONNEXION.disconnect();
            }
            else {
                Connexion.CONNEXION.envoyerMessageInt("NombresPartie", nbParties);
                for (int i = 0; i < nbParties; i++) {
                    SevenWonders sevenWonders  = new SevenWonders(nbJoueurs, false, color);
                    sevenWonders.partie(nbJoueurs);
                }
                Connexion.CONNEXION.disconnect();
            }
        }
        else {
            SevenWonders sevenWonders  = new SevenWonders(nbJoueurs, true, color);
            sevenWonders.partie(nbJoueurs);
            LoggerSevenWonders.show(LoggerSevenWonders.getStringBuilder());
        }
    }
}