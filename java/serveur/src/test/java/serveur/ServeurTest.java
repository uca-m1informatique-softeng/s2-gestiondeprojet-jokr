package serveur;

import fichier.GestionnaireDeFichier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.IOException;


public class ServeurTest {

    private Serveur serveur;

    /**
     * Preparation des tests de la classe serveur.Serveur
     */
    @Before
    public void setup() {

        serveur = new Serveur();
        serveur = Mockito.spy(serveur);
    }

    /**
     * Test de la méthode enregistrerPartie()
     */
    @Test
    public void enregistrerPartieTest() throws IOException {
        GestionnaireDeFichier gestionnaireDeFichier;
        gestionnaireDeFichier = Mockito.mock(GestionnaireDeFichier.class);

        serveur.enregistrerPartie(gestionnaireDeFichier, 3, new StringBuilder());
        Mockito.verify(gestionnaireDeFichier).ecrireDansFichier(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }
}