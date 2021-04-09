package sw_aventure.seven_wonders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.affichage.Colors;
import utils.affichage.LoggerSevenWonders;
@SpringBootApplication
public class SevenWondersApplication {

    @Autowired
    private SevenWonders sw;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SevenWondersApplication.class, args);
    }

    @Bean
    public CommandLineRunner sevenWonder() {
        // les traces sont là juste pour montrer le déroulement et le lancement
        return args -> {
            SevenWonders.args = args;
            // Nombres de joueurs
            int nbJoueurs ;
            // Nombres de parties si option des statistique activée
            int nbParties ;
            // Active plusieurs parties avec statistiques
            boolean multiPartieAvecStat = false;
            boolean color = false;

            String url = "http://127.0.0.1:8081/";
/*
            if(args.length == 0){
                System.exit(0);
            }*/
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
                System.out.println("url : ");
                System.out.println(url + "nbJoueur/");
            }
            catch (Exception ignored) {

            }

            // Bout de code qui envoie les stats
            if (multiPartieAvecStat) {
                sw.restTemplate.postForObject(url + "nbJoueur/", nbJoueurs, Integer.class);
                if (nbParties == 1) {
                    //SevenWonders sevenWonders = new SevenWonders(nbJoueurs, true, color);
                    LoggerSevenWonders.init(true);
                    Colors.setColor(color);

/*
                    sw.initPlayers(nbJoueurs,true);
                    sw.partie(nbJoueurs, true);
                    LoggerSevenWonders.show(LoggerSevenWonders.getStringBuilder());
                    sw.restTemplate.postForObject(url + "partie/", LoggerSevenWonders.getStringBuilder(), StringBuilder.class);
*/

                }
                else {
                    sw.restTemplate.postForObject(url + "nbParties/", nbParties, Integer.class);
                    for (int i = 0; i < nbParties; i++) {
                        //SevenWonders sevenWonders = new SevenWonders(nbJoueurs, false, color);
                        LoggerSevenWonders.init(false);
                        Colors.setColor(false);

                        /*sw.initPlayers(nbJoueurs,true);
                        sw.partie(nbJoueurs, true);*/
                    }
                }
            }
            else {
                //SevenWonders sevenWonders  = new SevenWonders(nbJoueurs, true, color);
                LoggerSevenWonders.init(true);
                Colors.setColor(color);
/*
                sw.initPlayers(nbJoueurs,true);
                sw.partie(nbJoueurs, false);
                LoggerSevenWonders.show(LoggerSevenWonders.getStringBuilder());
*/
            }

            /*
            Thread.sleep(1000);
            sw.restTemplate.postForObject(SevenWonders.statsServerURL + "/finir",null, Void.class);
            sw.sendEndToCLient();
            System.exit(0);
             */
        };

    }

}
