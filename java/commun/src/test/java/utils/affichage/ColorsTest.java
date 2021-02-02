package utils.affichage;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ColorsTest {

    private String phraseTest;
    private String code;


    @Before
    public void setup() {
        phraseTest = "Une phrase de test !!!";
        code = "\033[0m";
    }


    /**
     * Test quand color est activé
     */
    @Test
    public void colorActiveTest() {
        Colors.setColor(true);

        assertEquals("\033[31m" + phraseTest + code, Colors.rouge(phraseTest));
        assertEquals("\033[33m" + phraseTest + code, Colors.jaune(phraseTest));
        assertEquals("\033[35m" + phraseTest + code, Colors.violet(phraseTest));
        assertEquals("\033[37m" + phraseTest + code, Colors.blanc(phraseTest));
        assertEquals("\033[1m" + phraseTest + code, Colors.gStandard(phraseTest));
        assertEquals("\033[1;32m" + phraseTest + code, Colors.gVert(phraseTest));
        assertEquals("\033[1;31m" + phraseTest + code, Colors.gRouge(phraseTest));
        assertEquals("\033[1;37m" + phraseTest + code, Colors.gBlanc(phraseTest));
        assertEquals("\033[1;33m" + phraseTest + code, Colors.gJaune(phraseTest));
        assertEquals("\033[1;35m" + phraseTest + code, Colors.gViolet(phraseTest));
        assertEquals("\033[1;37m" + phraseTest + code, Colors.gBlanc(phraseTest));
        assertEquals("\033[3m" + phraseTest + code, Colors.itStandard(phraseTest));
        assertEquals("\033[3;32m" + phraseTest + code, Colors.itVert(phraseTest));
        assertEquals("\033[91m" + phraseTest + code, Colors.iRouge(phraseTest));
        assertEquals("\033[94m" + phraseTest + code, Colors.iBleu(phraseTest));
        assertEquals("\033[1;92m" + phraseTest + code, Colors.igVert(phraseTest));
        assertEquals("\033[1;91m" + phraseTest + code, Colors.igRouge(phraseTest));
        assertEquals("\033[1;94m" + phraseTest + code, Colors.igBleu(phraseTest));
        assertEquals("\033[1;93m" + phraseTest + code, Colors.igJaune(phraseTest));
        assertEquals("\033[1;95m" + phraseTest + code, Colors.igViolet(phraseTest));
        assertEquals("\033[1;96m" + phraseTest + code, Colors.igCyan(phraseTest));
    }


    /**
     * Test quand color est désactivé
     */
    @Test
    public void colorDesactiveTest() {
        Colors.setColor(false);

        assertEquals(phraseTest, Colors.rouge(phraseTest));
        assertEquals(phraseTest, Colors.jaune(phraseTest));
        assertEquals(phraseTest, Colors.violet(phraseTest));
        assertEquals(phraseTest, Colors.blanc(phraseTest));
        assertEquals(phraseTest, Colors.gStandard(phraseTest));
        assertEquals(phraseTest, Colors.gVert(phraseTest));
        assertEquals(phraseTest, Colors.gRouge(phraseTest));
        assertEquals(phraseTest, Colors.gBlanc(phraseTest));
        assertEquals(phraseTest, Colors.gJaune(phraseTest));
        assertEquals(phraseTest, Colors.gViolet(phraseTest));
        assertEquals(phraseTest, Colors.gBlanc(phraseTest));
        assertEquals(phraseTest, Colors.itStandard(phraseTest));
        assertEquals(phraseTest, Colors.itVert(phraseTest));
        assertEquals(phraseTest, Colors.iRouge(phraseTest));
        assertEquals(phraseTest, Colors.iBleu(phraseTest));
        assertEquals(phraseTest, Colors.igVert(phraseTest));
        assertEquals(phraseTest, Colors.igRouge(phraseTest));
        assertEquals(phraseTest, Colors.igBleu(phraseTest));
        assertEquals(phraseTest, Colors.igJaune(phraseTest));
        assertEquals(phraseTest, Colors.igViolet(phraseTest));
        assertEquals(phraseTest, Colors.igCyan(phraseTest));
    }
}
