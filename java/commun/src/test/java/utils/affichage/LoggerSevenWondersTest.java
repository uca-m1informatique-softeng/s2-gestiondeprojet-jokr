package utils.affichage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.io.PrintStream;
import static org.mockito.ArgumentMatchers.anyString;


class LoggerSevenWondersTest {

    @Mock
    private PrintStream out;

    /**
     * Initialisation des joueurs de test
     */
    @BeforeEach
    void setUp() {
        out = Mockito.mock(PrintStream.class);

    }

    /**
     * Test des println()
     */
    @Test
    void show() {
        out.println("Hi");
        Mockito.verify(out).println(anyString());
    }

    /**
     * Test de simples print()
     */
    @Test
    void showSans() {
        out.print("Hi");
        Mockito.verify(out).print(anyString());
    }


    /**
     * Test de la méthode ajout()
     */
    @Test
    void ajoutTest() {
        LoggerSevenWonders.init(true);
        assertEquals(new StringBuilder().toString(), LoggerSevenWonders.getStringBuilder().toString());

        LoggerSevenWonders.ajout("Voici une phrase de test !!!!");
        LoggerSevenWonders.ajout("Une autre...");

        assertEquals("Voici une phrase de test !!!!" + "Une autre...", LoggerSevenWonders.getStringBuilder().toString());
    }


    /**
     * Test de la méthode ajoutln()
     */
    @Test
    void ajoutln() {
        LoggerSevenWonders.init(true);
        assertEquals(new StringBuilder().toString(), LoggerSevenWonders.getStringBuilder().toString());

        LoggerSevenWonders.ajoutln("Voici une phrase de test pour l'ajoutln !!!!");
        LoggerSevenWonders.ajoutln("Et une autre...");

        assertEquals("Voici une phrase de test pour l'ajoutln !!!!\n" + "Et une autre...\n", LoggerSevenWonders.getStringBuilder().toString());
    }
}
