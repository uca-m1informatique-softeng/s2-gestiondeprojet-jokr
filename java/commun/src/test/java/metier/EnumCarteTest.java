package metier;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnumCarteTest {


    /**
     * Test de la méthode getNom() et de toute les cartes
     * On verifie tout le nom des cartes de chaque couleur: marron,grise,bleue,rouge,verte et violette
     */
    @Test
    public void getNomTest() {
        // Carte Marron
        assertEquals("Exploitation Forestière", EnumCarte.M1.toString());
        assertEquals("Fosse Argileuse", EnumCarte.M2.toString());
        assertEquals("Filon", EnumCarte.M3.toString());
        assertEquals("Bassin Argileux", EnumCarte.M4.toString());
        assertEquals("Cavité", EnumCarte.M5.toString());
        assertEquals("Chantier", EnumCarte.M6.toString());
        assertEquals("Excavation", EnumCarte.M7.toString());
        assertEquals("Gisement", EnumCarte.M8.toString());
        assertEquals("Mine", EnumCarte.M9.toString());
        assertEquals("Friche", EnumCarte.M10.toString());
        assertEquals("Fonderie", EnumCarte.M11.toString());
        assertEquals("Briqueterie", EnumCarte.M12.toString());
        assertEquals("Carrière", EnumCarte.M13.toString());
        assertEquals("Scierie", EnumCarte.M14.toString());

        // Carte Grise
        assertEquals("Presse", EnumCarte.G1.toString());
        assertEquals("Verrerie", EnumCarte.G2.toString());
        assertEquals("Métier à Tisser", EnumCarte.G3.toString());

        // Carte Bleur
        assertEquals("Théâtre", EnumCarte.B1.toString());
        assertEquals("Autel", EnumCarte.B2.toString());
        assertEquals("Bains", EnumCarte.B3.toString());
        assertEquals("Tribunal", EnumCarte.B4.toString());
        assertEquals("Temple", EnumCarte.B5.toString());
        assertEquals("Aqueduc", EnumCarte.B6.toString());
        assertEquals("Statue", EnumCarte.B7.toString());
        assertEquals("Panthéon", EnumCarte.B8.toString());
        assertEquals("Jardins", EnumCarte.B9.toString());
        assertEquals("Hôtel de Ville", EnumCarte.B10.toString());
        assertEquals("Palace", EnumCarte.B11.toString());
        assertEquals("Sénat", EnumCarte.B12.toString());
        assertEquals("Puits", EnumCarte.B13.toString());

        // Carte Jaune
        assertEquals("Marché", EnumCarte.J1.toString());
        assertEquals("Comptoir OUEST", EnumCarte.J2.toString());
        assertEquals("Comptoir EST", EnumCarte.J3.toString());
        assertEquals("Taverne", EnumCarte.J4.toString());
        assertEquals("Vignoble", EnumCarte.J5.toString());
        assertEquals("Forum", EnumCarte.J6.toString());
        assertEquals("Caravansérail", EnumCarte.J7.toString());
        assertEquals("Bazar", EnumCarte.J8.toString());
        assertEquals("Phare", EnumCarte.J9.toString());
        assertEquals("Port", EnumCarte.J10.toString());
        assertEquals("Arène", EnumCarte.J11.toString());
        assertEquals("Chambre de Commerce", EnumCarte.J12.toString());
        assertEquals("Ludus", EnumCarte.J13.toString());

        // Carte Rouge
        assertEquals("Tour de Garde", EnumCarte.R1.toString());
        assertEquals("Caserne", EnumCarte.R2.toString());
        assertEquals("Palissade", EnumCarte.R3.toString());
        assertEquals("Muraille", EnumCarte.R4.toString());
        assertEquals("Champs de Tir", EnumCarte.R5.toString());
        assertEquals("Ecuries", EnumCarte.R6.toString());
        assertEquals("Place d'Armes", EnumCarte.R7.toString());
        assertEquals("Fortification", EnumCarte.R8.toString());
        assertEquals("Arsenal", EnumCarte.R9.toString());
        assertEquals("Atelier de Siège", EnumCarte.R10.toString());
        assertEquals("Castrum", EnumCarte.R11.toString());
        assertEquals("Cirque", EnumCarte.R12.toString());

        // Carte Verte
        assertEquals("Scriptorium", EnumCarte.V1.toString());
        assertEquals("Atelier", EnumCarte.V2.toString());
        assertEquals("Officine", EnumCarte.V3.toString());
        assertEquals("Ecole", EnumCarte.V4.toString());
        assertEquals("Bibliothèque", EnumCarte.V5.toString());
        assertEquals("Laboratoire", EnumCarte.V6.toString());
        assertEquals("Dispensaire", EnumCarte.V7.toString());
        assertEquals("Loge", EnumCarte.V8.toString());
        assertEquals("Académie", EnumCarte.V9.toString());
        assertEquals("Observatoire", EnumCarte.V10.toString());
        assertEquals("Etude", EnumCarte.V11.toString());
        assertEquals("Université", EnumCarte.V12.toString());

        // Carte Violette
        assertEquals("Guilde des Travailleurs", EnumCarte.P1.toString());
        assertEquals("Guilde des Artisans", EnumCarte.P2.toString());
        assertEquals("Guilde des Magistrats", EnumCarte.P3.toString());
        assertEquals("Guilde des Commerçants", EnumCarte.P4.toString());
        assertEquals("Guilde des Espions", EnumCarte.P5.toString());
        assertEquals("Guilde des Philosophes", EnumCarte.P6.toString());
        assertEquals("Guilde des Scientifiques", EnumCarte.P7.toString());
        assertEquals("Guilde des Armateurs", EnumCarte.P8.toString());
        assertEquals("Guilde des Batisseurs", EnumCarte.P9.toString());
        assertEquals("Guilde des Décorateurs", EnumCarte.P10.toString());
    }
}
