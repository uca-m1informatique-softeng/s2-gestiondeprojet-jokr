package serveur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.affichage.LoggerSevenWonders;

@SpringBootApplication
public class ServeurApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServeurApplication.class,args);
        LoggerSevenWonders.init(true);
    }

    @Bean
    public CommandLineRunner aGame(@Autowired Serveur serveur) {
        return args -> {
            System.out.println("************************** aGame **************************************");

            // pour faire la différence entre un lancement via les tests et un lancement par mvn exec:java@id
            if (args.length >0) {
                serveur.main(args);
                //moteur.setExitOnFinish(true);

            }
        };
    }
}
