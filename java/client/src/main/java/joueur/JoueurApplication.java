package joueur;

import metier.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import utilitaire_jeu.NameURL;

import java.net.InetAddress;
import java.util.Arrays;

@SpringBootApplication
public class JoueurApplication {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    Joueur j;

    public static void main(String[] args) {
        // les traces sont là juste pour montrer le déroulement et le lancement
        System.out.println("args = " + args.length+ " " + Arrays.toString(args));
        SpringApplication.run(JoueurApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    /**
     * Appel du client. On se connecte au serveur avec args[1] l'adresse du serveur, et args[2] le nom du joueur.
     * @param restTemplate le restTemplate à utiliser pour faire les requêtes au moteur
     * @return un CommandLineRunner super stylé
     */
    @Bean
    public CommandLineRunner unClient(RestTemplate restTemplate) {
        // les traces sont là juste pour montrer le déroulement et le lancement
        System.out.println("----------------- CommandLineRunner -----------------");
        return args -> {
            j.setIABot(Strategy.COMPOSITE , args[2]);
            // les traces sont là juste pour montrer le déroulement et le lancement
            System.out.println("----------------- args = " + args.length + " " +Arrays.toString(args) + " -----------------");
            if ((args.length > 0) && (args[0].equals("autoconnect"))) {
                // les traces sont là juste pour montrer le déroulement et le lancement
                System.out.println("----------------- début de joueur -----------------");
                // connexion
                String port = "8090";
                if (args.length == 4) port = args[3];
                String adresse =  "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port;
                System.out.println("mon adresse = " + adresse);
                NameURL nameURL = new NameURL(args[2], adresse);
                Boolean val = restTemplate.postForObject(args[1] + "/connexion/", nameURL, Boolean.class);

                // les traces sont là juste pour montrer le déroulement et le lancement
                System.out.println("Nom du joueur : " + args[2]);
                System.out.println("Joueur > état de la connexion : " + val);

                FacadeJoueur facadeJoueur = new FacadeJoueur();
                facadeJoueur.j = j;
            }
        };
    }
}
