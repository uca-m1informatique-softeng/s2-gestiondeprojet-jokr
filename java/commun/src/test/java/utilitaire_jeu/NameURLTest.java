package utilitaire_jeu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NameURLTest {

    private NameURL nameURL1, nameURL2, nameURL3;

    @Before
    public void setup() {
        nameURL1 = new NameURL("Joueur_1", "url_1");
        nameURL2 = new NameURL("Joueur_2", "url_2");
        nameURL3 = new NameURL("Joueur_3", "url_3");
    }

    @Test
    public void getNameTest() {
        assertEquals("Joueur_1", nameURL1.getName());
        assertEquals("Joueur_2", nameURL2.getName());
        assertEquals("Joueur_3", nameURL3.getName());
    }

    @Test
    public void setNameTest() {
        nameURL1.setName("Joueur1_BIS");
        nameURL2.setName("Joueur2_BIS");
        nameURL3.setName("Joueur3_BIS");

        assertEquals("Joueur1_BIS", nameURL1.getName());
        assertEquals("Joueur2_BIS", nameURL2.getName());
        assertEquals("Joueur3_BIS", nameURL3.getName());
    }

    @Test
    public void getUrl() {
        assertEquals("url_1", nameURL1.getUrl());
        assertEquals("url_2", nameURL2.getUrl());
        assertEquals("url_3", nameURL3.getUrl());
    }

    @Test
    public void setUrl() {
        nameURL1.setUrl("url1_BIS");
        nameURL2.setUrl("url2_BIS");
        nameURL3.setUrl("url3_BIS");

        assertEquals("url1_BIS", nameURL1.getUrl());
        assertEquals("url2_BIS", nameURL2.getUrl());
        assertEquals("url3_BIS", nameURL3.getUrl());
    }
}
