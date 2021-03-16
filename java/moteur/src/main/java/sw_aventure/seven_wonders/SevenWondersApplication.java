package sw_aventure.seven_wonders;

import metier.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import utilitaire_jeu.NameURL;
import utils.affichage.Colors;
import utils.affichage.LoggerSevenWonders;

import java.net.InetAddress;
import java.util.Arrays;

@SpringBootApplication
public class SevenWondersApplication {

    @Autowired
    private SevenWonders sw;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SevenWondersApplication.class, args);
    }

    @Bean
    public CommandLineRunner unClient() {
        // les traces sont là juste pour montrer le déroulement et le lancement
        return args -> {
            // Nombres de joueurs
            int nbJoueurs ;
            // Nombres de parties si option des statistique activée
            int nbParties ;
            // Active plusieurs parties avec statistiques
            boolean multiPartieAvecStat = false;
            boolean color = false;

            String url = "http://127.0.0.1:8081/";

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
            try {
                url = args[4];
                SevenWonders.statsServerURL = url;
            }
            catch (Exception ignored) {
                // Ignore
            }

            // Bout de code qui envoie les stats
            if (multiPartieAvecStat) {
                sw.restTemplate.postForObject(url + "nbJoueur/", nbJoueurs, Integer.class);
                if (nbParties == 1) {
                    //SevenWonders sevenWonders = new SevenWonders(nbJoueurs, true, color);
                    LoggerSevenWonders.init(true);
                    Colors.setColor(color);
                    sw.initPlayers(nbJoueurs,true);
                    sw.partie(nbJoueurs);
                    LoggerSevenWonders.show(LoggerSevenWonders.getStringBuilder());
                    sw.restTemplate.postForObject(url + "partie/", LoggerSevenWonders.getStringBuilder(), StringBuilder.class);
                }
                else {
                    sw.restTemplate.postForObject(url + "nbParties/", nbParties, Integer.class);
                    for (int i = 0; i < nbParties; i++) {
                        //SevenWonders sevenWonders = new SevenWonders(nbJoueurs, false, color);
                        LoggerSevenWonders.init(false);
                        Colors.setColor(color);
                        sw.initPlayers(nbJoueurs,true);
                        sw.partie(nbJoueurs);
                    }

                }
            }
            else {
                //SevenWonders sevenWonders  = new SevenWonders(nbJoueurs, true, color);
                LoggerSevenWonders.init(true);
                Colors.setColor(color);
                sw.initPlayers(nbJoueurs,true);
                sw.partie(nbJoueurs);
                LoggerSevenWonders.show(LoggerSevenWonders.getStringBuilder());
            }
            Thread.sleep(1000);
            System.exit(0);
        };
    }

}
