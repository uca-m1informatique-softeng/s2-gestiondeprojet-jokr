/*package sw_aventure.seven_wonders;

import objet_commun.Carte;
import sw_aventure.objetjeu.GenererCarte;
import io.cucumber.java8.Fr;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SevenWondersStepdefs implements Fr {

    public SevenWondersStepdefs() {
        SevenWonders sw = new SevenWonders(3,false,false);

        Etantdonné("une liste de carte composé de Carte d'Age1",
                       () -> // besoin de refactorer int en Integer car utilisation de la généricité par Cucumber Java 8
    {
        GenererCarte carteA1 = new GenererCarte(1, 3);
        List<Carte> ls = carteA1.getCards();



    });

        Quand("une partie se finie", () -> {
            SevenWonders sevenWonders = new SevenWonders(3,false,false);

        });

        Alors("on compte la taille du paquet de carte", () -> {
            GenererCarte carteA1 = new GenererCarte(1, 3);
            List<Carte> ls = carteA1.getCards();

            assertEquals(21 , ls.size());
        });

    }
 }
*/


