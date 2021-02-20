package sw_aventure.seven_wonders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SevenWondersApplication {
    public static void main(String[] args) {
        SpringApplication.run(SevenWondersApplication.class, args);
    }



    @Bean
    public CommandLineRunner aGame(@Autowired SevenWonders moteur) {
        return args -> {
            System.out.println("************************** aGame **************************************");

            // pour faire la différence entre un lancement via les tests et un lancement par mvn exec:java@id
            if (args.length >0) {
                moteur.main(args);
                //moteur.setExitOnFinish(true);

            }
        };
    }
}
