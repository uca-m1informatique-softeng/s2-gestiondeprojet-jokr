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
        inv = new Inventaire( "url", "Dupont et Dupont");

        inv1 = new Inventaire( "url1", "Enzo");
        inv2 = new Inventaire( "url2", "Christina");
        inv3 = new Inventaire( "url3", "Mona");
        inv4 = new Inventaire( "url4", "Paul");
        inv5 = new Inventaire( "url5", "Lucie");
        inv6 = new Inventaire( "url6", "Thomas");
        joueur1 = new Joueur(1,Strategy.RANDOM,inv1.getJoueurName());
        joueur2 = new Joueur(2,Strategy.MERVEILLE,inv2.getJoueurName());
        joueur3 = new Joueur(3,Strategy.MERVEILLE,inv3.getJoueurName());
        joueur4 = new Joueur(4,Strategy.SCIENTIFIQUE,inv4.getJoueurName());
        joueur5 = new Joueur(5,Strategy.AMBITIEUSE,inv5.getJoueurName());
        joueur6 = new Joueur(6,Strategy.MILITAIRE,inv6.getJoueurName());


        ArrayList<Inventaire> listeInventaire = new ArrayList<>(){{add(inv1);add(inv2);add(inv3);add(inv4);add(inv5);add(inv6);}};
        ArrayList<Joueur> listeJoueur = new ArrayList<>(){{add(joueur1);add(joueur2);add(joueur3);add(joueur4);add(joueur5);add(joueur6);}};
        plateau = new Plateau(listeInventaire);

        IAmock = Mockito.mock(IArandom.class);
        joueurSpy = new Joueur(8,Strategy.ULTIME,inv.getJoueurName());
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
     * Test du getter getId() pour verifier si on a bien selectionner le bon identifiant pour un joueur specifique
     */
    @Test
    public void getId() {
        assertEquals(1,joueur1.getId());
        assertEquals(2,joueur2.getId());
        assertEquals(3,joueur3.getId());
        assertEquals(4,joueur4.getId());
        assertEquals(5,joueur5.getId());
    }

    /**
     * Test du getter hashCode() pour donner le hash de l'id d'un joueur
     */
    @Test
    public void hashCodeTest() {

        assertEquals(32, joueur1.hashCode());
        assertEquals(33,joueur2.hashCode());
        assertEquals(34,joueur3.hashCode());
        assertEquals(35,joueur4.hashCode());
        assertEquals(36,joueur5.hashCode());
    }



    /**
     * Test de la méthode equals()
     */
    @Test
    public void equals() {
        assertEquals(new Joueur(1, Strategy.RANDOM, "j1"), joueur1);
        assertEquals(new Joueur(2, Strategy.RANDOM, "j2"), joueur2);
        assertNotEquals(new Joueur(2, Strategy.RANDOM, "j3"), joueur3);
        assertNotEquals(joueur1.hashCode(), joueur2.hashCode());
        assertNotEquals(joueur2.hashCode(), joueur3.hashCode());
        assertNotEquals(joueur3.hashCode(), joueur4.hashCode());
        assertNotEquals(joueur1 , inv);
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