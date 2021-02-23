package joueur;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import objet_commun.Carte;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class JoueurTest {

    private Inventaire inv, inv1, inv2, inv3, inv4, inv5,inv6;
    private Joueur joueur1, joueur2, joueur3, joueur4, joueur5, joueur6;

    private Joueur joueurSpy;
    private IArandom IAmock;

    private Carte carte;
    private Plateau plateau;
    private ArrayList<Carte> listeCarte = new ArrayList<>();


    /**
     * Preparation des tests de la classe Joueur
     */
    @Before
    public void setUp() {
        inv = new Inventaire( 0,"url", "Dupont et Dupont");

        inv1 = new Inventaire( 1,"url1", "Enzo");
        inv2 = new Inventaire( 2,"url2", "Christina");
        inv3 = new Inventaire( 3,"url3", "Mona");
        inv4 = new Inventaire( 4,"url4", "Paul");
        inv5 = new Inventaire( 5,"url5", "Lucie");
        inv6 = new Inventaire( 6,"url6", "Thomas");
        joueur1 = new Joueur(Strategy.RANDOM,inv1.getJoueurName());
        joueur2 = new Joueur(Strategy.MERVEILLE,inv2.getJoueurName());
        joueur3 = new Joueur(Strategy.MERVEILLE,inv3.getJoueurName());
        joueur4 = new Joueur(Strategy.SCIENTIFIQUE,inv4.getJoueurName());
        joueur5 = new Joueur(Strategy.AMBITIEUSE,inv5.getJoueurName());
        joueur6 = new Joueur(Strategy.MILITAIRE,inv6.getJoueurName());


        ArrayList<Inventaire> listeInventaire = new ArrayList<>(){{add(inv1);add(inv2);add(inv3);add(inv4);add(inv5);add(inv6);}};
        ArrayList<Joueur> listeJoueur = new ArrayList<>(){{add(joueur1);add(joueur2);add(joueur3);add(joueur4);add(joueur5);add(joueur6);}};
        plateau = new Plateau(listeInventaire);

        IAmock = Mockito.mock(IArandom.class);
        joueurSpy = new Joueur(Strategy.ULTIME,inv.getJoueurName());
        joueurSpy.setBot(IAmock);
        joueurSpy = Mockito.spy(joueurSpy);

        carte = new Carte(EnumCarte.B13, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 4, 1, EnumRessources.BLEUE);

        listeCarte = Mockito.mock(ArrayList.class);
    }

    /**
     * Test de la méthode jouedefausse()
     * On test si la méthode "choixDefausse()" de la class IArandom a bien été effectué
     */
    /*
    @Test
    public void jouedefausse() {
        Mockito.verify(IAmock).choixDefausse(joueurSpy, carte ,plateau);
    }
*/

    /**
     * Test de la méthode achatRessource()
     */
    @Test
    public void achatRessourceTest() {
        joueurSpy.achatRessource(EnumRessources.BOIS,inv, inv1, inv2);
        Mockito.verify(IAmock).commerceAdjacent(EnumRessources.BOIS, joueurSpy,inv, inv1, inv2);
    }

    /**
     * Test de la méthode jouerGratuitementDanslaDefausse()
     */
    @Test
    public void jouerGratuitementDanslaDefausseTest() {
        joueurSpy.jouerGratuitementDanslaDefausse(listeCarte, inv,plateau);
        Mockito.verify(IAmock).choisirCarteDeLaDefausse(listeCarte, plateau);
    }

    /**
     * Test de la méthode jouerMerveille()
     */
    @Test
    public void jouerMerveilleTest() {
        joueurSpy.jouerMerveille(listeCarte,inv, plateau);
        Mockito.verify(IAmock).choixMerveille(joueurSpy, listeCarte,inv, plateau);
    }

    /**
     * Test de la méthode constructMerveille()
     */
    @Test
    public void constructMerveilleTest() {
        joueurSpy.constructMerveille(listeCarte,inv, plateau);
        Mockito.verify(IAmock).choixCartePourMerveille(joueurSpy, listeCarte,inv, plateau);
    }

    /**
     * On test si la méthode "choixMain()" de la class IArandom a bien été effectué
     */
    /*
    @Test
    public void choixCarteTest() {
        joueurSpy.choixCarte(listeCarte ,inv, plateau);
        Mockito.verify(IAmock).choixMain(Mockito.eq(joueurSpy), Mockito.any(), inv,Mockito.eq(plateau), Mockito.eq(true));
    }
*/
    /**
     * On vérifie si le constructeur Joueur initialise bien le bot (stratégie)
     */
    @Test
    public void getBot() {
        assertTrue(joueur1.getBot() instanceof IArandom);
        assertTrue(joueur2.getBot() instanceof IAmerveille);
        assertTrue(joueur3.getBot() instanceof IAmerveille);
        assertTrue(joueur4.getBot() instanceof IAscientifique);
        assertTrue(joueur5.getBot() instanceof IAambitieuse);
    }

    /**
     * Test du getter getName() pour donner le nom d un joueur
     */
    @Test
    public void getName() {
        assertEquals("Enzo", joueur1.getName());
        assertEquals("Christina",joueur2.getName());
        assertEquals("Mona",joueur3.getName());
        assertEquals("Paul",joueur4.getName());
        assertEquals("Lucie",joueur5.getName());
    }



    /**
     * Test de la méthode getStrategie()
     */
    @Test
    public void getStrategieTest() {
        assertEquals("IA Random", joueur1.getStrategie());
        assertEquals("IA Merveille", joueur2.getStrategie());
        assertEquals("ultime!", joueurSpy.getStrategie());
        assertEquals("IA Random", joueur1.getStrategie());
        assertEquals("IA Merveille", joueur3.getStrategie());
        assertEquals("IA Scientifique", joueur4.getStrategie());
        assertEquals("IA Ambitieuse", joueur5.getStrategie());
        assertEquals("IA Militaire", joueur6.getStrategie());
    }
}