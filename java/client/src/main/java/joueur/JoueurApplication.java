package joueur;

import com.github.javafaker.Faker;
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

    Faker faker = new Faker();

    /**
     * Appel du client. On se connecte au serveur avec args[1] l'adresse du serveur, et args[2] le nom du joueur.
     * @param restTemplate le restTemplate à utiliser pour faire les requêtes au moteur
     * @return un CommandLineRunner super stylé
     */
    @Bean
    public CommandLineRunner unClient(RestTemplate restTemplate) {//It allows you to do operations before the Spring Boot’s run() method finishes its execution.
        // les traces sont là juste pour montrer le déroulement et le lancement
        System.out.println("----------------- CommandLineRunner -----------------");
        return args -> {
            String firstName = faker.name().firstName();
            j.setIABot(Strategy.COMPOSITE, firstName);
            // les traces sont là juste pour montrer le déroulement et le lancement
            System.out.println("----------------- args = " + args.length + " " +Arrays.toString(args) + " -----------------");
            if ((args.length > 0) && (args[0].equals("autoconnect"))) {
                // les traces sont là juste pour montrer le déroulement et le lancement
                System.out.println("----------------- début de joueur -----------------");
                // connexion
                String port = "8090";
                if (args.length == 3) port = args[2];
                String adresse =  "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port;
                System.out.println("mon adresse = " + adresse);

                NameURL nameURL = new NameURL(firstName , adresse);
                Boolean val = restTemplate.postForObject(args[1] + "/connexion/", nameURL, Boolean.class);

                // les traces sont là juste pour montrer le déroulement et le lancement
                System.out.println("Nom du joueur : " + firstName);
                System.out.println("Joueur > état de la connexion : " + val);
            }
        };
    }
}
