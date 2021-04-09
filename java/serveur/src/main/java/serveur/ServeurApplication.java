package serveur;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utils.affichage.LoggerSevenWonders;

@SpringBootApplication
public class ServeurApplication {

    @Value("${server.address}")
    private static String serverAddress;

    @Value("${server.port}")
    private static String serverPort;

    public static void main(String[] args) {
        SpringApplication.run(ServeurApplication.class,args);
        LoggerSevenWonders.init(true);
    }

}
