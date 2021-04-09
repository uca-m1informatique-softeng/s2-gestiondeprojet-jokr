package sw_aventure.seven_wonders;

import java.security.SecureRandom;
import java.util.*;
import exception.NegativeNumberException;
import metier.*;
import objet_commun.Merveille;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sw_aventure.objetjeu.*;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;
import utilitaire_jeu.SetInventaire;
import utils.affichage.Colors;
import utils.affichage.LoggerSevenWonders;

/**
 * Classe du main où la partie se lance
 */
@Component
public class SevenWonders {
    private final SecureRandom r = new SecureRandom();
    protected List<SetInventaire> inv = new ArrayList<>();
    private final GenererMerveille genererMerveille = new GenererMerveille();
    public static String statsServerURL = "http://127.0.0.1:8080";
    public static String[] args;

    private static final int TIMEOUT = 3*60; // En secondes

    @Autowired
    MoteurWebController webController;

    @Autowired
    RestTemplate restTemplate;

    /**
     * main : lancement du jeu
     * Arguments en lançant le programme par ligne de commande :
     * 1ème argument : indiquer "false" pour afficher sans couleur (peut être utile si l'on souhaite lire directement le .txt ou si on utilise un terminal qui ne reconnait pas le code ANSI)
     * 2ème argument : indiquer le nombre de parties à lancer (une si rien d'indiqué ou si pas un entier)
     * 3ème argument : indiquer le nombre de joueurs qui vont participer aux parties (3-7)
     * 4ème argument : indiquer false si on ne veut ne lancer qu'une partie (ne lance pas les statistiques et n'écrit pas dans un fichier)
     * Par défaut : true 1 3 false : on lance une partie à 3 joueurs que l'on affiche sur la sortie standard avec les couleurs
     */
    public SevenWonders() {

    }


    // METHODES //

    /**
     * Méthode appelée lorsque tous les joueurs se sont connectés
     * @throws Exception
     */
    public void launchGame() throws Exception {

        int nbJoueurs = Integer.parseInt(SevenWonders.args[2]);
        int nbParties = Integer.parseInt(SevenWonders.args[1]);
        boolean multiPartieAvecStat = SevenWonders.args[3].equals("true");
        String url = SevenWonders.args[4];

        this.initPlayers(nbJoueurs, true);

        if (multiPartieAvecStat) {

            if (nbParties == 1) {
                this.partie(nbJoueurs, true);
                LoggerSevenWonders.show(LoggerSevenWonders.getStringBuilder());
                this.restTemplate.postForObject(url + "partie/", LoggerSevenWonders.getStringBuilder(), StringBuilder.class);
            }
            else{
                this.partie(nbJoueurs, true);
                for(int i = 0 ; i < nbParties-1 ; i++){
                    LoggerSevenWonders.init(false);
                    Colors.setColor(false);
                    this.initPlayers(nbJoueurs, true);
                    this.partie(nbJoueurs, true);
                }
            }
        } else {
            this.partie(nbJoueurs, false);
            LoggerSevenWonders.show(LoggerSevenWonders.getStringBuilder());
        }

        Thread.sleep(1000);
        this.restTemplate.postForObject(SevenWonders.statsServerURL + "/finir",null, Void.class);
        this.sendEndToCLient();
        System.exit(0);
    }


    /**
     * Crée les joueurs, rempli la liste des inventaires
     * @param nbJoueurs le nombre de joueurs qui vont jouer
     */
    public void initPlayers(int nbJoueurs,boolean shuffle) {
        /*
        long t0 = new Date().getTime();
        long t1 = t0;
        int newNbJoueurs = 0;
        while (this.webController.listJoueurId.size() < nbJoueurs){
            try {
                newNbJoueurs = this.webController.listJoueurId.size();
                System.out.println("Joueurs Connectés : " + newNbJoueurs);
                Thread.sleep(1000);
                t1 = new Date().getTime();
                if (t1 - t0 > 1000*SevenWonders.TIMEOUT) { System.exit(404); }
            } catch (Exception e) {
                //e.printStackTrace();
            }

        }*/

        System.out.println("Tous les joueurs sont connectés lancement de la partie !");
        inv = this.webController.listJoueurId;

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
        ArrayList<Inventaire> listeInventaire = new ArrayList<>(inv);
        return new Plateau(listeInventaire);
    }



    /**
     * Donne une merveille aléatoirement à chaque joueurs
     */
    public void attributionMerveille() {
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
            LoggerSevenWonders.ajoutln(inv.get(i).getJoueurName() + " a obtenue la merveille "+ merveille.get(i).get(temporalite) + " et gagne 1 "+ laMerveille.getGain());

            inv.get(i).increaseValue(laMerveille.getGain(),1);
        }
    }


    /**
     * Méthode permettant de créer la partie en appelant les différentes méthode/étapes du jeu
     * @param nbJoueurs le nombre de joueurs
     * @param stat si les stats sont activée
     */
    DeroulementJeu jeu;

    public boolean partie(int nbJoueurs, boolean stat) throws NegativeNumberException {

        Plateau plateau = initPlateau();

        LoggerSevenWonders.ajoutln("____________________     7 Wonders       ________________________");
        LoggerSevenWonders.ajoutln("____________________ Commencement du Jeu ________________________\n");

        attributionMerveille();
        jeu = new DeroulementJeu(webController,inv);
        jeu.laPartie(plateau, nbJoueurs);

        LoggerSevenWonders.ajoutln("_____________________________ Fin de la partie _____________________________");
        inv = triInventaire(inv);
        if (stat) envoyerStat(inv);
        clearGame();
        return true;
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

        List<Data> datas = new ArrayList<>();
        for (SetInventaire s : setInv) {
            nomJoueur = s.getJoueurName();
            strategieJoueur = webController.getStrategie(s.getUrl());
            inventaire = s.getSac();
            merveille = s.getMerveille().getNom().toString();
            cartes = s.getListeCarte();
            datas.add(new Data(nomJoueur, strategieJoueur, getSactoString(inventaire), merveille, getListeCarteToString(cartes)));
        }
        //Connexion.CONNEXION.envoyerMessageArray("DataPartie", jsonArray);
        afficherResumePartie(setInv);
        restTemplate.postForObject(SevenWonders.statsServerURL + "sendStats/", datas.toArray(), Data[].class);
    }

    public void afficherResumePartie(List<SetInventaire> setInv){
        for(int i = 0; i<setInv.size();i++){
            System.out.print(setInv.get(i).getJoueurName()+ " à obtenu la merveille : " + setInv.get(i).getMerveille().getNom() + " et ") ;
            if(setInv.get(i).getValue(EnumRessources.VICTOIRETOTAL) == 1 ){
                System.out.print("gagne ");
            }else{
                System.out.print("perd ");
            }
            System.out.println("avec : " + setInv.get(i).getValue(EnumRessources.SCOREFINAL) + " points");
        }
    }

    public void sendEndToCLient(){
        for (SetInventaire setInventaire : inv) {
            webController.envoyerFin(setInventaire);
        }
    }

    public void clearGame(){
        this.webController.cleanInventory();
        inv = this.webController.listJoueurId;
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
}