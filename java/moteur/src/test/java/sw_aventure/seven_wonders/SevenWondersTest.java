package sw_aventure.seven_wonders;

import metier.EnumCarte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;
import utilitaire_jeu.SetInventaire;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SevenWondersTest {

    @Autowired
    private SevenWonders sevenWonders;

    private SetInventaire setInv1, setInv2, setInv3;

    /**
     * Preparation des tests de la classe SevenWonders
     */
    @BeforeEach
    public void setup() {
        SevenWonders.args = new String[]{"true", "1", "3", "false", "http://127.0.0.1:8085/"};
        sevenWonders = new SevenWonders();


        setInv1 = new SetInventaire( 1,"RHERBEN", "Enzo");
        setInv2 = new SetInventaire( 2,"EBREBEB", "Christina");
        setInv3 = new SetInventaire( 3,"GBREBEB", "Mona");

        String joueur1 = setInv1.getUrl();
        String joueur2 = setInv2.getUrl();
        String joueur3 = setInv3.getUrl();

        ArrayList<Inventaire> listeInventaire = new ArrayList<>(){{add(setInv1);add(setInv2);add(setInv3);}};
        Plateau plateau = new Plateau(listeInventaire);
    }


    @Test
    public void getListeCarteToStringTest() {
        List<EnumCarte> listCarte = new ArrayList<>();
        listCarte.add(EnumCarte.M1);
        listCarte.add(EnumCarte.J5);
        listCarte.add(EnumCarte.B9);
        List<String> listNomCarte = sevenWonders.getListeCarteToString(listCarte);

        List<String> listCarteAttendue = new ArrayList<>();
        listCarteAttendue.add("Exploitation Forestière");
        listCarteAttendue.add("Vignoble");
        listCarteAttendue.add("Jardins");

        for (String carte : listNomCarte) {
            assertTrue(listCarteAttendue.contains(carte));
        }
    }


    /**
     * Test de la méthode initPlayers()
     */
    /*
    @Test
    public void initPlayersTest() {
        SevenWonders sw = new SevenWonders();

        assertEquals(0, sw.inv.size());

        sw.initPlayers(3,false);
        assertEquals(3, sw.inv.size());

        sw.initPlayers(4,false);
        assertEquals(4, sw.inv.size());

        sw.initPlayers(5,false);
        assertEquals(5, sw.inv.size());

        sw.initPlayers(6,false);
        assertEquals(6, sw.inv.size());

        sw.initPlayers(7,false);
        assertEquals(7, sw.inv.size());
    }*/


    /**
     * Test de la méthode initPlateau()
     */
    /*
    @Test
    public void initPlateauTest() {
        Plateau plateau = sevenWonders.initPlateau();
        assertEquals(3, plateau.getListeInventaire().size());
    }
     */


    /**
     * Test de la méthode attributionMerveille()
     */
    @Test
    public void attributionMerveilleTest() {
        // Au départ les joueurs n'on pas de merveille donc NULL
        for (SetInventaire s : sevenWonders.inv) {
            assertNull(s.getMerveille());
        }

        sevenWonders.attributionMerveille();
        // Une fois les merveille attribuée ce n'est plus égale a NULL
        for (SetInventaire s : sevenWonders.inv) {
            assertNotNull(s.getMerveille());
        }
    }
}

