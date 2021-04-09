package serveur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utils.affichage.LoggerSevenWonders;

@SpringBootApplication
public class ServeurApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServeurApplication.class,args);
        LoggerSevenWonders.init(true);
    }

}
