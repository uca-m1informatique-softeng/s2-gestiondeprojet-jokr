package joueur;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import objet_commun.Carte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;


@SpringBootTest
public class IArandomSpringTest {

    private ArrayList<Carte> main;

    @Autowired
    private Joueur joueur;

    private Inventaire inventaire;
    private Plateau plateau;

    private IA bot;

    @Mock
    private IA mbot;

    private Joueur mjoe;
    private Plateau mplateau;
    private Carte mcart;

    /**
     * Preparation des tests de la classe IArandom
     */
    @BeforeEach
    public void setup() {
        inventaire = new Inventaire( 1,"FZEEGVB", "Enzo");

        joueur.setStrategie(Strategy.RANDOM);
        joueur.setName(inventaire.getJoueurName());

        main = new ArrayList<>();
        plateau = new Plateau(new ArrayList<>());
        bot = new  IArandom();

        mbot = Mockito.mock (IArandom.class);
        mjoe = Mockito.mock(Joueur.class);
        mcart = Mockito.mock(Carte.class);
        mplateau = Mockito.mock(Plateau.class);
        main = new ArrayList<>();
    }


    /**
     * Test de la méthode commerceAdjacent()
     */
    @Test
    public void commerceAdjacentTest() {
        IA iaRandom = Mockito.mock(IArandom.class);

        Mockito.when(iaRandom.commerceAdjacent(EnumRessources.BOIS, joueur,inventaire, inventaire, inventaire)).thenReturn(true);
        assertTrue(iaRandom.commerceAdjacent(EnumRessources.BOIS, joueur,inventaire, inventaire, inventaire));

        Mockito.when(iaRandom.commerceAdjacent(EnumRessources.BOIS, joueur,inventaire, inventaire, inventaire)).thenReturn(false);
        assertFalse(iaRandom.commerceAdjacent(EnumRessources.BOIS, joueur, inventaire,inventaire, inventaire));
    }


    /**
     * Test de la méthode choixMerveille
     */
    @Test
    public void choixMerveilleTest() {
        IA iaRandom = Mockito.mock(IArandom.class);

        Mockito.when(iaRandom.choixMerveille(joueur, main,inventaire, plateau)).thenReturn(true);
        assertTrue(iaRandom.choixMerveille(joueur, main,inventaire, plateau));

        Mockito.when(iaRandom.choixMerveille(joueur, main,inventaire, plateau)).thenReturn(false);
        assertFalse(iaRandom.choixMerveille(joueur, main,inventaire, plateau));
    }


    /**
     * Test de la méthode toString()
     */
    @Test
    public void ToStringTest() {
        assertEquals("IA random", bot.toString());
    }


    /**
     * Test de la méthode choixDefausse()
     */
    @Test
    public void choixDefausseTest() {
        doReturn(true).when(mbot).choixDefausse(mjoe, mcart,inventaire, mplateau);
        mbot.choixDefausse(mjoe, mcart,inventaire, mplateau);
        assertTrue(mbot.choixDefausse(mjoe, mcart,inventaire, mplateau));
    }


    /**
     * Test de la méthode choixMain
     */
    @Test
    public void choixMainTest() {
        int choix;

        // On ajoute une carte
        main.add(new Carte(EnumCarte.M6, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        assertEquals(0, bot.choixMain(joueur, main,inventaire, plateau,true));

        // On ajoute une deuxième carte
        main.add(new Carte(EnumCarte.M5, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        choix = bot.choixMain(joueur, main,inventaire, plateau,true);
        assertTrue(choix >= 0 && choix <= 1);

        // On ajoute cinq nouvelle carte
        main.add(new Carte(EnumCarte.V11, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        main.add(new Carte(EnumCarte.V1, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        main.add(new Carte(EnumCarte.J4, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        main.add(new Carte(EnumCarte.R4, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        main.add(new Carte(EnumCarte.R8, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        main.add(new Carte(EnumCarte.V9, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        choix = bot.choixMain(joueur, main,inventaire, plateau,true);
        assertTrue(choix >= 0 && choix <= 9);
    }
}