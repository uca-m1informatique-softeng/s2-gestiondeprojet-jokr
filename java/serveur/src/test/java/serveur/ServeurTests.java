package serveur;

import fichier.GestionnaireDeFichier;
import metier.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ServeurTests {


    @Autowired
    Serveur server;


    @BeforeEach
    public void setup() {
        server = Mockito.spy(server);
    }


    @Test
    public void getNbJoueurTest() {
        server.getNbJoueur(4);
        assertEquals(4, server.getNbJoueur());

        server.getNbJoueur(7);
        assertEquals(7, server.getNbJoueur());

        server.getNbJoueur(3);
        assertEquals(3, server.getNbJoueur());
    }

    
    @Test
    public void getNbPartieTest() {
        server.getNbPartie(500);
        assertEquals(500, server.getNbPartie());

        server.getNbPartie(2000);
        assertEquals(2000, server.getNbPartie());

        server.getNbPartie(30000);
        assertEquals(30000, server.getNbPartie());
    }


    @Test
    public void getStatsTest() throws Exception {
        Data[] data = new Data[2];

        server.getStats(data);
        Mockito.verify(server).traiterFinReception();
        assertEquals(server.getDataParties().size(), 1);

        server.getStats(data);
        assertEquals(server.getDataParties().size(), 2);
    }


    @Test
    public void showPartieTest() throws IOException {
        GestionnaireDeFichier fichier = Mockito.mock(GestionnaireDeFichier.class);
        StringBuilder stringBuilder = new StringBuilder();

        server.showPartie(stringBuilder);
        Mockito.verify(server).enregistrerPartie(Mockito.any(), Mockito.anyInt(), Mockito.any());
    }


    @Test
    public void enregistrerPartieTest() throws IOException {
        GestionnaireDeFichier fichier = Mockito.mock(GestionnaireDeFichier.class);
        StringBuilder stringBuilder = new StringBuilder();

        server.enregistrerPartie(fichier, 5, stringBuilder);
        Mockito.verify(fichier).ecrireDansFichier(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

    }
}
