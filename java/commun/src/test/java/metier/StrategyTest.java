package metier;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrategyTest {

    /**
     * Test de la méthode getNom() et de toutes les strategies
     */
    @Test
    public void getNom() {

        assertEquals("random", Strategy.RANDOM.toString());
        assertEquals("scientifique", Strategy.SCIENTIFIQUE.toString());
        assertEquals("monetaire", Strategy.MONETAIRE.toString());
        assertEquals("civile", Strategy.CIVILE.toString());
        assertEquals("militaire", Strategy.MILITAIRE.toString());
        assertEquals("composite", Strategy.COMPOSITE.toString());
        assertEquals("merveille", Strategy.MERVEILLE.toString());
        assertEquals("ambitieuse", Strategy.AMBITIEUSE.toString());
        assertEquals("ultime!", Strategy.ULTIME.toString());
    }

}