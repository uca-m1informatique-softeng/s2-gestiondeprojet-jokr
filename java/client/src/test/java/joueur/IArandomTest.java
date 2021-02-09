package joueur;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import objet_commun.Carte;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sw_aventure.seven_wonders.Plateau;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

public class IArandomTest {

    private ArrayList<Carte> main;
    private Joueur joueur;
    private Inventaire inventaire;
    private Plateau plateau;
    private ArrayList<Inventaire> inv;
    private ArrayList<Joueur> listeJoueur = new ArrayList<>();


    private IA bot;
    @Mock
    private IA mbot;
    private Joueur mjoe;
    private Plateau mplateau;
    private Carte mcart;

    /**
     * Preparation des tests de la classe IArandom
     */
    @Before
    public void setup() {
        inventaire = new Inventaire(1, Strategy.RANDOM, "Enzo");

        joueur = new Joueur(   0, Strategy.RANDOM , "Enzo", inventaire);
        main = new ArrayList<>();
        plateau = new Plateau(inv, listeJoueur);
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

        Mockito.when(iaRandom.commerceAdjacent(EnumRessources.BOIS, joueur, inventaire, inventaire)).thenReturn(true);
        assertTrue(iaRandom.commerceAdjacent(EnumRessources.BOIS, joueur, inventaire, inventaire));

        Mockito.when(iaRandom.commerceAdjacent(EnumRessources.BOIS, joueur, inventaire, inventaire)).thenReturn(false);
        assertFalse(iaRandom.commerceAdjacent(EnumRessources.BOIS, joueur, inventaire, inventaire));
    }


    /**
     * Test de la méthode choixMerveille
     */
    @Test
    public void choixMerveilleTest() {
        IA iaRandom = Mockito.mock(IArandom.class);

        Mockito.when(iaRandom.choixMerveille(joueur, main, plateau)).thenReturn(true);
        assertTrue(iaRandom.choixMerveille(joueur, main, plateau));

        Mockito.when(iaRandom.choixMerveille(joueur, main, plateau)).thenReturn(false);
        assertFalse(iaRandom.choixMerveille(joueur, main, plateau));
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
        doReturn(true).when(mbot).choixDefausse(mjoe, mcart, mplateau);
        mbot.choixDefausse(mjoe, mcart, mplateau);
        assertTrue(mbot.choixDefausse(mjoe, mcart, mplateau));
    }


    /**
     * Test de la méthode choixMain
     */
    @Test
    public void choixMainTest() {
        int choix;

        // On ajoute une carte
        main.add(new Carte(EnumCarte.M6, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        assertEquals(0, bot.choixMain(joueur, main, plateau,true));

        // On ajoute une deuxième carte
        main.add(new Carte(EnumCarte.M5, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        choix = bot.choixMain(joueur, main, plateau,true);
        assertTrue(choix >= 0 && choix <= 1);

        // On ajoute cinq nouvelle carte
        main.add(new Carte(EnumCarte.V11, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        main.add(new Carte(EnumCarte.V1, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        main.add(new Carte(EnumCarte.J4, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        main.add(new Carte(EnumCarte.R4, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        main.add(new Carte(EnumCarte.R8, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        main.add(new Carte(EnumCarte.V9, Collections.emptyList(), Collections.emptyList(),1,1,EnumRessources.MARRON));
        choix = bot.choixMain(joueur, main, plateau,true);
        assertTrue(choix >= 0 && choix <= 9);
    }
}