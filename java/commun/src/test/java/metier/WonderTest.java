package metier;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class WonderTest {
    /**
     * Test de la méthode getNom() et de toutes les merveilles
     */
    @Test
    public void getNom() {

        assertEquals("BABYLON", Wonder.BABYLON.toString());
        assertEquals("BABYLON DE NUIT", Wonder.BABYLONNUIT.toString());
        assertEquals("HALIKARNASSOS", Wonder.HALIKARNASSOS.toString());
        assertEquals("HALIKARNASSOS DE NUIT", Wonder.HALIKARNASSOSNUIT.toString());
        assertEquals("OLYMPIA", Wonder.OLYMPIA.toString());
        assertEquals("OLYMPED DE NUIT", Wonder.OLYMPIANUIT.toString());
        assertEquals("GIZAH", Wonder.GIZAH.toString());
        assertEquals("GIZAH DE NUIT", Wonder.GIZAHNUIT.toString());
        assertEquals("RHODOS", Wonder.RHODOS.toString());
        assertEquals("RHODOS DE NUIT", Wonder.RHODOSNUIT.toString());
        assertEquals("EPHESOS", Wonder.EPHESOS.toString());
        assertEquals("EPHESOS DE NUIT", Wonder.EPHESOSNUIT.toString());
        assertEquals("GIZAH DE NUIT", Wonder.GIZAHNUIT.toString());
        assertEquals("ALEXANDRIA", Wonder.ALEXANDRIA.toString());
        assertEquals("ALEXANDRIA DE NUIT", Wonder.ALEXANDRIANUIT.toString());
        assertEquals("STATUE DE LA LIBERTE", Wonder.STATUELIBERTE.toString());

    }


}