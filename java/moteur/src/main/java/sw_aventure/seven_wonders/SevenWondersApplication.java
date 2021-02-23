package sw_aventure.seven_wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SevenWondersApplication {
/*
    @Autowired
    static SevenWonders moteur;*/

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SevenWondersApplication.class, args);
        SevenWonders.launch(args);
    }

}
