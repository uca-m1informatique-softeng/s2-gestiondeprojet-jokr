package sw_aventure.seven_wonders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SevenWondersApplication {

    @Autowired
    static SevenWonders moteur;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SevenWondersApplication.class, args);
        SevenWonders.launch(args);
    }

}
