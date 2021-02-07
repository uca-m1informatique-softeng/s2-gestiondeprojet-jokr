package sw_aventure.joueur;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import objet_commun.Carte;
import sw_aventure.objetjeu.Inventaire;
import sw_aventure.seven_wonders.Plateau;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
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
        inv = new Inventaire(10, Strategy.ULTIME, "Dupont et Dupont");

        inv1 = new Inventaire(1, Strategy.RANDOM, "Enzo");
        inv2 = new Inventaire(2, Strategy.MERVEILLE, "Christina");
        inv3 = new Inventaire(3, Strategy.MERVEILLE, "Mona");
        inv4 = new Inventaire(4, Strategy.CIVILE, "Paul");
        inv5 = new Inventaire(5, Strategy.SCIENTIFIQUE, "Lucie");
        inv6 = new Inventaire(5, Strategy.AMBITIEUSE, "Thomas");
        joueur1 = inv1.getJoueur();
        joueur2 = inv2.getJoueur();
        joueur3 = inv3.getJoueur();
        joueur4 = inv4.getJoueur();
        joueur5 = inv5.getJoueur();
        joueur6 = inv6.getJoueur();


        ArrayList<Inventaire> listeInventaire = new ArrayList<>(){{add(inv1);add(inv2);add(inv3);add(inv4);add(inv5);add(inv6);}};
        ArrayList<Joueur> listeJoueur = new ArrayList<>(){{add(joueur1);add(joueur2);add(joueur3);add(joueur4);add(joueur5);add(joueur6);}};
        plateau = new Plateau(listeInventaire, listeJoueur);

        IAmock = Mockito.mock(IArandom.class);
        joueurSpy = inv.getJoueur();
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
        joueurSpy.achatRessource(EnumRessources.BOIS, inv1, inv2);
        Mockito.verify(IAmock).commerceAdjacent(EnumRessources.BOIS, joueurSpy, inv1, inv2);
    }


    /**
     * Test de la méthode jouerGratuitementDanslaDefausse()
     */
    @Test
    public void jouerGratuitementDanslaDefausseTest() {
        joueurSpy.jouerGratuitementDanslaDefausse(listeCarte, plateau);
        Mockito.verify(IAmock).choisirCarteDeLaDefausse(listeCarte, plateau);
    }


    /**
     * Test de la méthode jouerMerveille()
     */
    @Test
    public void jouerMerveilleTest() {
        joueurSpy.jouerMerveille(listeCarte, plateau);
        Mockito.verify(IAmock).choixMerveille(joueurSpy, listeCarte, plateau);
    }


    /**
     * Test de la méthode constructMerveille()
     */
    @Test
    public void constructMerveilleTest() {
        joueurSpy.constructMerveille(listeCarte, plateau);
        Mockito.verify(IAmock).choixCartePourMerveille(joueurSpy, listeCarte, plateau);
    }


    /**
     * On test si la méthode "choixMain()" de la class IArandom a bien été effectué
     */
    @Test
    public void choixCarteTest() {
        joueurSpy.choixCarte(listeCarte , plateau);
        Mockito.verify(IAmock).choixMain(Mockito.eq(joueurSpy), Mockito.any(), Mockito.eq(plateau), Mockito.eq(true));
    }




    /**
     * On vérifie si le constructeur Joueur initialise bien le bot (stratégie)
     */
    @Test
    public void getBot() {
        assertTrue(joueur1.getBot() instanceof IArandom);
        assertTrue(joueur2.getBot() instanceof IAmerveille);
        assertTrue(joueur3.getBot() instanceof IAmerveille);
        assertTrue(joueur4.getBot() instanceof IAcivil);
        assertTrue(joueur5.getBot() instanceof IAscientifique);
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
     * Test du getter getInv() pour verifier si on a bien selectionner le bon inventaire pour un joueur specifique
     */
    @Test
    public void getInv() {
        assertEquals(inv1,joueur1.getInv());
        assertEquals(inv2,joueur2.getInv());
        assertEquals(inv3,joueur3.getInv());
        assertEquals(inv4,joueur4.getInv());
        assertEquals(inv5,joueur5.getInv());
    }



    /**
     * Test de la méthode equals()
     */
    @Test
    public void equals() {
        assertEquals(new Joueur(1, Strategy.RANDOM, "j1", inv1), joueur1);
        assertEquals(new Joueur(2, Strategy.RANDOM, "j2", inv3), joueur2);
        assertNotEquals(new Joueur(2, Strategy.RANDOM, "j3", inv3), joueur3);
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
        assertEquals("ultime!", inv.getJoueur().getStrategie());
        assertEquals("IA Composite", new Inventaire(1, Strategy.COMPOSITE, "j1").getJoueur().getStrategie());
        assertEquals("IA Militaire", new Inventaire(1, Strategy.MILITAIRE, "j1").getJoueur().getStrategie());
        assertEquals("IA Monétaire", new Inventaire(1, Strategy.MONETAIRE, "j1").getJoueur().getStrategie());
        assertEquals("IA Civil", new Inventaire(1, Strategy.CIVILE, "j1").getJoueur().getStrategie());
        assertEquals("IA Scientifique", new Inventaire(1, Strategy.SCIENTIFIQUE, "j1").getJoueur().getStrategie());
        assertEquals("IA Ambitieuse", new Inventaire(1, Strategy.AMBITIEUSE, "j1").getJoueur().getStrategie());
    }
}