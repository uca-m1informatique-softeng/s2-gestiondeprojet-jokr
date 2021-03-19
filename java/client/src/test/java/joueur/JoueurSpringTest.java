package joueur;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import objet_commun.Carte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JoueurSpringTest {

    private Inventaire inv, inv1, inv2, inv3, inv4, inv5,inv6;

    @Autowired
    private Joueur joueur1 ;

    @Autowired
    private Joueur joueur2;

    @Autowired
    private Joueur joueur3;

    @Autowired
    private Joueur joueur4;

    @Autowired
    private Joueur joueur5;

    @Autowired
    private Joueur joueur6;

    @Autowired
    private Joueur joueurSpy;

    private IArandom IAmock;

    private Carte carte;

    private Plateau plateau;

    private ArrayList<Carte> listeCarte = new ArrayList<>();


    /**
     * Preparation des tests de la classe Joueur
     */
    @BeforeEach
    public void setUp() throws Exception {
        inv = new Inventaire( 0,"url", "Dupont et Dupont");
        inv1 = new Inventaire( 1,"url1", "Enzo");
        inv2 = new Inventaire( 2,"url2", "Christina");
        inv3 = new Inventaire( 3,"url3", "Mona");
        inv4 = new Inventaire( 4,"url4", "Paul");
        inv5 = new Inventaire( 5,"url5", "Lucie");
        inv6 = new Inventaire( 6,"url6", "Thomas");


        ArrayList<Inventaire> listeInventaire = new ArrayList<>(){{add(inv1);add(inv2);add(inv3);add(inv4);add(inv5);add(inv6);}};
        ArrayList<Joueur> listeJoueur = new ArrayList<>(){{add(joueur1);add(joueur2);add(joueur3);add(joueur4);add(joueur5);add(joueur6);}};
        plateau = new Plateau(listeInventaire);

        IAmock = Mockito.mock(IArandom.class);
        joueurSpy.setBot(IAmock);
        joueurSpy = Mockito.spy(joueurSpy);

        carte = new Carte(EnumCarte.B13, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 4, 1, EnumRessources.BLEUE);

        listeCarte = Mockito.mock(ArrayList.class);
    }

    @Test
    public void ea(){
        System.out.println("Inveeee : " + inv);
    }

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
     * On vérifie si le constructeur Joueur initialise bien le bot (stratégie)
     */
    @Test
    public void getBot() {
        joueur1.setIABot(Strategy.RANDOM , "MBappé");
        assertTrue(joueur1.getBot() instanceof IArandom);
        joueur2.setIABot(Strategy.MERVEILLE , "MBappé");
        assertTrue(joueur2.getBot() instanceof IAmerveille);
        joueur3.setIABot(Strategy.MERVEILLE , "MBappé");
        assertTrue(joueur3.getBot() instanceof IAmerveille);
        joueur4.setIABot(Strategy.SCIENTIFIQUE , "MBappé");
        assertTrue(joueur4.getBot() instanceof IAscientifique);
        joueur5.setIABot(Strategy.AMBITIEUSE , "MBappé");
        assertTrue(joueur5.getBot() instanceof IAambitieuse);
        joueur6.setIABot(Strategy.MILITAIRE , "MBappé");
        assertTrue(joueur6.getBot() instanceof IAmilitaire);
    }

    /**
     * Test du getter getName() pour donner le nom d un joueur
     */
    @Test
    public void getName() {
        joueur1.setIABot(Strategy.RANDOM , "Enzo");
        assertEquals("Enzo", joueur1.getName());
        joueur2.setIABot(Strategy.MERVEILLE , "Christina");
        assertEquals("Christina",joueur2.getName());
        joueur3.setIABot(Strategy.MERVEILLE , "Mona");
        assertEquals("Mona",joueur3.getName());
        joueur4.setIABot(Strategy.SCIENTIFIQUE , "Paul");
        assertEquals("Paul",joueur4.getName());
        joueur5.setIABot(Strategy.AMBITIEUSE , "Lucie");
        assertEquals("Lucie",joueur5.getName());
        joueur6.setIABot(Strategy.MILITAIRE , "MBappé");
        assertEquals("MBappé",joueur6.getName());
    }

    /**
     * Test de la méthode getStrategie()
     */
    @Test
    public void getStrategieTest() {
        joueur1.setIABot(Strategy.RANDOM , "MBappé");
        assertEquals("IA Random", joueur1.getStrategie());
        joueur2.setIABot(Strategy.MERVEILLE , "MBappé");
        assertEquals("IA Merveille", joueur2.getStrategie());
        joueur3.setIABot(Strategy.MERVEILLE , "MBappé");
        assertEquals("IA Merveille", joueur3.getStrategie());
        joueur4.setIABot(Strategy.SCIENTIFIQUE , "MBappé");
        assertEquals("IA Scientifique", joueur4.getStrategie());
        joueur5.setIABot(Strategy.AMBITIEUSE , "MBappé");
        assertEquals("IA Ambitieuse", joueur5.getStrategie());
        joueur6.setIABot(Strategy.MILITAIRE , "MBappé");
        assertEquals("IA Militaire", joueur6.getStrategie());
        joueurSpy.setIABot(Strategy.ULTIME , "MBappé");
        assertEquals("ultime!", joueurSpy.getStrategie());
    }
}
