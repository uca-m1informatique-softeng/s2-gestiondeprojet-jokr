package joueur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import utilitaire_jeu.DataToClient;
import java.util.concurrent.TimeUnit;

@RestController
public class FacadeJoueur {


    @Autowired
    Joueur j ;

    public FacadeJoueur() {
        System.out.println(this);
    }


    /**
     * Étant donné une carte et un plateau, renvoie si le joueur veut défausser la carte ou non
     * @param data les données transmise par Json contenant une liste de carte un inventaire et un objet plateau
     * @return true si la carte est à défausser, false sinon
     */
    @PostMapping("/jouer/Defausse")
    public boolean jouerDefausse(@RequestBody DataToClient data) {
        return j.jouerdefausse(data.getListCarte(), data.getInvJoueur(), data.getPlateau());
    }


    /**
     * Demande à l'IA du joueur quelle carte de la défausse elle souhaite jouer
     * @param data les données transmise par Json contenant une liste de carte un inventaire et un objet plateau
     * @return Index de la carte à jouer depuis la défausse
     */
    @PostMapping("/jouer/GratuitementDanslaDefausse")
    public int jouerGratuitementDanslaDefausse(@RequestBody DataToClient data){
        return j.jouerGratuitementDanslaDefausse(data.getListCarte(), data.getInvJoueur(), data.getPlateau());
    }

    /**
     * Étant donné un plateau et une main, renvoie si le joueur souhaite oui ou non construire une étape de merveille
     * @param data les données transmise par Json contenant une liste de carte un inventaire et un objet plateau
     * @return true si le joueur veut construire une étape, false sinon
     */
    @PostMapping("/jouer/Merveille")
    public boolean jouerMerveille(@RequestBody DataToClient data) {
        System.out.println("PROBLEME LAAAAAA 1 ");
        return j.jouerMerveille(data.getListCarte(), data.getInvJoueur(), data.getPlateau());
    }

    /**
     * Étant donné un plateau et une main, renvoie la carte que le joueur choisirait pour construire une étape de
     * merveille
     * @param data les données transmise par Json contenant une liste de carte un inventaire et un objet plateau
     * @return l'indice de la carte à sacrifier
     */
    @PostMapping("/jouer/constructMerveille")
    public int constructMerveille(@RequestBody DataToClient data) {
        return j.constructMerveille(data.getListCarte(), data.getInvJoueur(), data.getPlateau());
    }

    /**
     * Étant donné un plateau et une main, renvoie la carte que le joueur choisirait
     * @param data les données transmise par Json contenant une liste de carte un inventaire et un objet plateau
     * @return l'indice de la carte renvoyée
     */
    @PostMapping("/jouer/choixCarte")
    public int choixCarte(@RequestBody DataToClient data) {
        return j.choixCarte(data.getListCarte(), data.getInvJoueur(), data.getPlateau());
    }

    /**
     * Cette méthode donne la stratégie d'un joueur donné
     * @return la stratégie du joueur
     */
    @PostMapping("/strategie")
    public String getStrategie(){
        return j.getStrategie();
    }

    @PostMapping("/finir")
    public void finir() {
        // fin brutale (pour abréger sur travis), mais il faut répondre un peu après
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Joueur > fin du programme");
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.exit(0);
                }

            }
        });
        t.start();
    }

}
