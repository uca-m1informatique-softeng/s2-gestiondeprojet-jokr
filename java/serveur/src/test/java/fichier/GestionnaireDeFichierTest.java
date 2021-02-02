package fichier;

import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class GestionnaireDeFichierTest {


    private GestionnaireDeFichier fichier;
    private String ecrireFichierTest;


    @Before
    public void setup() {
        fichier = new GestionnaireDeFichier();
        ecrireFichierTest = "src/test/resources/EcrireFichierTest.txt";
    }


    /**
     * Test de la méthode ecrireDansFichier()
     * @throws IOException
     */
    @Test
    public void ecrireDansFichierTest() throws IOException {
        // On supprime le fichier de test dans lequel on va écrire (s'il existe)
        File fichierEcrit = new File(ecrireFichierTest);
        fichierEcrit.delete();


        // On écrit dans le fichier plusieurs lignes de test
        fichier.ecrireDansFichier(ecrireFichierTest, "Voici une phrase de test");
        fichier.ecrireDansFichier(ecrireFichierTest, "Et en voici une autre...");
        fichier.ecrireDansFichier(ecrireFichierTest, "Enzo gagne la partie !!!!!!!");


        // On vérifie que les lignes de test ce sont bien écrit dans le fichier
        BufferedReader br;
        br = new BufferedReader(new FileReader(ecrireFichierTest));
        String line = br.readLine();

        // Vérification de la première ligne écrite
        assertEquals("Voici une phrase de test", line);

        // Vérification de la deuxième ligne écrite
        line = br.readLine();
        assertEquals("Et en voici une autre...", line);

        // Vérification de la dernière ligne écrite
        line = br.readLine();
        assertEquals("Enzo gagne la partie !!!!!!!", line);
    }
}
