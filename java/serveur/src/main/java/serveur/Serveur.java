package serveur;

import fichier.GestionnaireDeFichier;
import metier.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import statistique.Statistique;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *      Serveur permettant de recevoir les données du jeu
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
@RestController
public class Serveur {

    private int nbPartie;
    private int nbJoueur;
    private ArrayList<Data[]> dataParties;
    private GestionnaireDeFichier gestionnaireDeFichier;
    private Statistique statistique;

    public Serveur(){
        this.nbPartie = 0;
        this.nbJoueur = 0;
        this.dataParties = new ArrayList<>();
    }

    @PostMapping("/nbJoueur/")
    public void getNbJoueur(@RequestBody Integer nbJoueur){
        this.nbJoueur = nbJoueur;
    }

    @PostMapping("/nbPartie/")
    public void getNbPartie(@RequestBody Integer nbPartie){
        this.nbPartie = nbPartie;
    }

    @PostMapping("/sendStats/")
    public void getStats(@RequestBody Data[] data) throws Exception {
        this.dataParties.add(data);
        this.traiterFinReception();
    }

    @PostMapping("/partie/")
    public void showPartie(@RequestBody StringBuilder stringBuilder) throws IOException {
        this.gestionnaireDeFichier = new GestionnaireDeFichier();
        this.enregistrerPartie(this.gestionnaireDeFichier, this.nbJoueur, stringBuilder);
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

        String path = "output/partie_unique/" + nbJoueur + " Joueurs/";
        String file = format.format(date) + ".txt";
        gestionnaireDeFichier.ecrireDansFichier(path, file, string.toString());
    }

    /**
     * Vérifie si le serveur a fini de recevoir toutes les statistiques. Si c'est le cas, il calcule les stats, les
     * affiche, puis s'arrête.
     * @throws Exception Au cas où certains appels systèmes posent problème
     */
    private void traiterFinReception() throws Exception {
        if (this.dataParties.size() == this.nbPartie) {
            this.statistique = new Statistique(this.nbPartie, this.nbJoueur, this.dataParties);
            this.statistique.calculStat();
            this.statistique.afficheStat(new GestionnaireDeFichier());
            Thread.sleep(1000);
            System.exit(0);
        }
    }
}
