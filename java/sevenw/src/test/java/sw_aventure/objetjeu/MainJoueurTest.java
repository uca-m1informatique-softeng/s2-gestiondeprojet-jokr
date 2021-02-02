package sw_aventure.objetjeu;

import metier.EnumCarte;
import metier.EnumRessources;
import org.junit.Before;
import org.junit.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class MainJoueurTest {

    private MainJoueur mainJoueur;

    /**
     * Preparation des tests de la classe MainJoueur
     */
    @Before
    public void setUp() {
        mainJoueur =  new MainJoueur();
    }


    /**
     * Test de la methode add(Carte)
     * On a ajoute les cartes Chantier 1,2 et 3 dans la main du Joueur 1,2 et 3
     * On verifie si les cartes sont bien dans la main du joueur
     */
    @Test
    public void add() {

        mainJoueur.add(new Carte(EnumCarte.M4, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.ARGILE), 3, 1, EnumRessources.MARRON));
        mainJoueur.add(new Carte(EnumCarte.M3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.MINERAI), 3, 1, EnumRessources.MARRON));
        mainJoueur.add(new Carte(EnumCarte.M2, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIAM), 3, 1, EnumRessources.MARRON));

        assertTrue(mainJoueur.getMain().contains(new Carte(EnumCarte.M4, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.ARGILE), 3, 1, EnumRessources.MARRON)));
        assertTrue(mainJoueur.getMain().contains(new Carte(EnumCarte.M3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.MINERAI), 3, 1, EnumRessources.MARRON)));
        assertTrue(mainJoueur.getMain().contains(new Carte(EnumCarte.M2, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIAM), 3, 1, EnumRessources.MARRON)));

        assertFalse(mainJoueur.getMain().contains(new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON)));
    }

    /**
     * Test de la methode getMain()
     */
    @Test
    public void getMain() {

        mainJoueur.add(new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON));
        mainJoueur.add(new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON));
        mainJoueur.add(new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON));
        assertNotNull(mainJoueur.getMain());
    }
}