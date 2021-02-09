package sw_aventure.seven_wonders;

import exception.NegativeNumberException;
import org.junit.Before;
import org.junit.Test;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;
import utilitaire_jeu.SetInventaire;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class SevenWondersTest {

    private SevenWonders sevenWonders;
    private SetInventaire setInv1, setInv2, setInv3;
    private String joueur1, joueur2, joueur3;
    private Plateau plateau;

    /**
     * Preparation des tests de la classe SevenWonders
     */
    @Before
    public void setup() {
        sevenWonders = new SevenWonders(3, false, false);

        setInv1 = new SetInventaire(1, "RHERBEN", "Enzo");
        setInv2 = new SetInventaire(2, "EBREBEB", "Christina");
        setInv3 = new SetInventaire(3, "GBREBEB", "Mona");


        joueur1 = setInv1.getUrl();
        joueur2 = setInv2.getUrl();
        joueur3 = setInv3.getUrl();

        ArrayList<Inventaire> listeInventaire = new ArrayList<>(){{add(setInv1);add(setInv2);add(setInv3);}};
        plateau = new Plateau(listeInventaire);



    }


    /**
     * Test de la méthode initPlayers()
     */
    @Test
    public void initPlayersTest() {
        SevenWonders sw = new SevenWonders(0, false,false);

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

    }


    /**
     * Test de la méthode initPlateau()
     */
    @Test
    public void initPlateauTest() {
        Plateau plateau = sevenWonders.initPlateau();
        assertEquals(3, plateau.getListeInventaire().size());
    }


    /**
     * Test de la méthode attributionMerveille()
     */
    @Test
    public void attributionMerveilleTest() throws NegativeNumberException {
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