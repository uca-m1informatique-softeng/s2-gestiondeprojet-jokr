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
}
