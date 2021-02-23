package joueur;

import metier.Strategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import utilitaire_jeu.NameURL;

import java.net.InetAddress;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FacadeMoteurITCase {

    NameURL id;
    Joueur j;

    @SpyBean
    FacadeJoueur facade;

    @BeforeEach
    void setup() throws Exception {
        this.id = new NameURL("HARLOD", "http://"+ InetAddress.getLocalHost().getHostAddress());
        this.j = new Joueur(Strategy.COMPOSITE, this.id.getName());
    }

}
