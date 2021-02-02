package serveur;

import com.corundumstudio.socketio.SocketIOServer;
import fichier.GestionnaireDeFichier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;


public class ServeurTest {

    private SocketIOServer socketIOServer;
    private Serveur serveur;

    /**
     * Preparation des tests de la classe serveur.Serveur
     */
    @Before
    public void setup() {
        socketIOServer = Mockito.mock(SocketIOServer.class);

        serveur = new Serveur(socketIOServer);
        serveur = Mockito.spy(serveur);
    }


    /**
     * Test de la methode demarrer()
     */
    @Test
    public void demarrerTest() {
        serveur.demarrer();
        Mockito.verify(socketIOServer).start();
    }

    /**
     * Test de la méthode enregistrerPartie()
     */
    @Test
    public void enregistrerPartieTest() throws IOException {
        GestionnaireDeFichier gestionnaireDeFichier;
        gestionnaireDeFichier = Mockito.mock(GestionnaireDeFichier.class);

        serveur.enregistrerPartie(gestionnaireDeFichier, 3, new StringBuilder());
        Mockito.verify(gestionnaireDeFichier).ecrireDansFichier(Mockito.anyString(), Mockito.anyString());
    }
}