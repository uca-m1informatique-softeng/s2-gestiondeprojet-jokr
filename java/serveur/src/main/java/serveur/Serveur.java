package serveur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import fichier.GestionnaireDeFichier;
import metier.Data;
import metier.*;
import statistique.Statistique;
import utils.affichage.LoggerSevenWonders;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *      Serveur permettant de recevoir les données du jeu
 * @see #server
 *          SocketIOServer
 * @see #nbPartie
 *          Le nombre de partie joué
 * @see #nbJoueur
 *          Le nombre de joueur
 * @see #dataParties
 *          Les données de toutes les parties
 * @see #gestionnaireDeFichier
 *          Permet d'écrire les données dans un fichier
 * @see #statistique
 *          Permet de calcumer les statistiques si plusieurs parties
 */
public class Serveur {

    private final SocketIOServer server;
    private int nbPartie = 0;
    private int nbJoueur = 0;
    private ArrayList<Data[]> dataParties = new ArrayList<>();
    private GestionnaireDeFichier gestionnaireDeFichier;
    private Statistique statistique;

    /**
     * Constructeur pour le serveur
     * @param server SocketIOServer
     */
    public Serveur(SocketIOServer server) {
        this.server = server;

        /**
         * Evénement qui signale que des données vont être envoyée
         */
        this.server.addEventListener("Initialisation", boolean.class, new DataListener<Boolean>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Boolean aBoolean, AckRequest ackRequest) throws Exception {
                if (Boolean.TRUE.equals(aBoolean)) {
                    nbPartie = 0;
                    nbJoueur = 0;
                    dataParties = new ArrayList<>();
                }
            }
        });

        /**
         * Evénement qui permet de recevoir le nombre de partie
         */
        this.server.addEventListener("NombresPartie", int.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Integer integer, AckRequest ackRequest) throws Exception {
                nbPartie = integer;
            }
        });

        /**
         * Evénement qui permet de recevoir le nombre de joueur
         */
        this.server.addEventListener("NombresJoueur", int.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Integer integer, AckRequest ackRequest) throws Exception {
                nbJoueur = integer;
            }
        });

        /**
         * Evénement qui permet de recevoir les données d'une partie
         */
        this.server.addEventListener("DataPartie", Data[].class, new DataListener<Data[]>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Data[] data, AckRequest ackRequest) throws IOException {
                dataParties.add(data);
                if (dataParties.size() == nbPartie) {
                    statistique = new Statistique(nbPartie, nbJoueur, dataParties);
                    statistique.calculStat();
                    statistique.afficheStat(new GestionnaireDeFichier());
                }
            }
        });

        /**
         * Evénement qui permet de recevoir le déroulé d'une partie normale
         */
        this.server.addEventListener("Partie", StringBuilder.class, new DataListener<StringBuilder>() {
            @Override
            public void onData(SocketIOClient socketIOClient, StringBuilder stringBuilder, AckRequest ackRequest) throws Exception {
                gestionnaireDeFichier = new GestionnaireDeFichier();
                enregistrerPartie(gestionnaireDeFichier, nbJoueur, stringBuilder);
            }
        });
    }


    /**
     *  Methode demarrer qui lance le serveur
     */
    public void demarrer() {
        server.start();
    }


    /**
     * Méthode permettant d'enregistrer le déroulé d'une partie dans un fichier
     * @param gestionnaireDeFichier le gestionnaire de fichier
     * @param nbJoueur Le nombre de joueur
     * @param string Les données a enregistrer
     * @throws IOException Exception si l'enregistrement échoue
     */
    public void enregistrerPartie(GestionnaireDeFichier gestionnaireDeFichier, int nbJoueur, StringBuilder string) throws IOException {
        DateFormat format = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();

        String path = "serveur/output/partie_unique/" + nbJoueur + " Joueurs/" + format.format(date) + ".txt";
        gestionnaireDeFichier.ecrireDansFichier(path, string.toString());
    }


    /**
     * Methode du main
     * @param args argument du main
     */
    public static void main(String[] args) {
        LoggerSevenWonders.init(true);
        Configuration config = new Configuration();

        config.setHostname("127.0.0.1");
        config.setPort(10101);
        SocketIOServer server = new SocketIOServer(config);

        Serveur serveur = new Serveur(server);

        serveur.demarrer();
    }
}
