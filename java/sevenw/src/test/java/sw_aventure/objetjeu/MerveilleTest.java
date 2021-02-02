package sw_aventure.objetjeu;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import metier.Wonder;
import sw_aventure.joueur.Joueur;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class MerveilleTest {

    private Merveille merveille;
    private Carte carte1, carte2, carte3;
    private ArrayList<Carte> arrayOfCarte;
    private Joueur joueur1;

    @Mock

    private Merveille lMerveille;

    /**
     * Preparation pour les tests de la classe Merveille
     * On fera des tests avec mockito pour verifier les appels des methodes avec leurs arguments associes
     */
    @Before
    public void setUp() {

        carte1 = new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE));
        carte2 = new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUSCPR));
        carte3 = new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE));

        arrayOfCarte = new ArrayList<>(){{ add(carte1); add(carte2); add(carte3); }};

        Inventaire inv1 = new Inventaire(1, Strategy.RANDOM, "Enzo");

        joueur1 = inv1.getJoueur();

        merveille = new Merveille(Wonder.BABYLON, EnumRessources.BOIS, arrayOfCarte, joueur1);

        lMerveille = Mockito.mock(Merveille.class);
    }

    /**
     * Test de la methode getJoueur()
     * On verifie que la methode est bien call avec un test mockito
     * On regarde si la merveille est bien associe au bon joueur
     * On doit verifier que merveille appartient au joueur1 ici
     */
    @Test
    public void getJoueur() {
        assertEquals(merveille.getJoueur(), joueur1);
    }

    /**
     * Test de la methode peutAmeliorerMerveille()
     * On verifie que la methode est bien call avec un test mockito
     * On verifie qu on ne peut ameliorer que 3 fois cette merveille ici
     * Pour cela, on incremente les stades ou etapes de la merveille entre chaque amelioration
     */
    @Test
    public void peutAmeliorerMerveille() {
        assertEquals(true , merveille.peutAmeliorerMerveille());
        merveille.incrementeStade();
        assertEquals(true , merveille.peutAmeliorerMerveille());
        merveille.incrementeStade();
        assertEquals(true , merveille.peutAmeliorerMerveille());
        merveille.incrementeStade();
        assertEquals(false , merveille.peutAmeliorerMerveille());
    }

    /**
     * Test de la methode getCarteAConstruire()
     * On verifie que la methode est bien call avec un test mockito
     * On incremente chaque stade ou etape entre chaque appel de cette methode avec la methode incrementeStade()
     * On verifie si le getter renvoit bien une liste de carte coherente
     */
    @Test
    public void getCarteAConstruire() {
        assertEquals(merveille.getCarteAConstruire(), arrayOfCarte.get(0));
        merveille.incrementeStade();
        assertEquals(merveille.getCarteAConstruire(), arrayOfCarte.get(1));
        merveille.incrementeStade();
        assertEquals(merveille.getCarteAConstruire(), arrayOfCarte.get(2));
    }

    /**
     * Test de la methode getNom()
     * On verifie que la methode est bien call avec un test mockito
     * On regarde si le getter renvoit le nom de la merveille correctememt
     * Ici, il doit renvoyer Babylon
     */
    @Test
    public void getNom() {
        assertEquals(Wonder.BABYLON , merveille.getNom());
    }

    /**
     * Test de la methode getStade()
     * On verifie que la methode est bien call avec un test mockito
     * On verifie s'il renvoit la bonne stade ou etape
     * Ici, on doit avoir 0 en retour pour l appel de cette methode
     */
    @Test
    public void getStade() {
        assertEquals(0 , merveille.getStade());
    }

    /**
     * Test de la methode incrementeStade()
     * On verifie que la methode est bien call avec un test mockito
     * On regarde si l incrementation de la valeur de l etape s est bien realise
     * On utilise un getter pour connaitre la valeur de l etape actuelle
     */
    @Test
    public void incrementeStade() {
        lMerveille.incrementeStade();
        Mockito.verify(lMerveille).incrementeStade();

        assertEquals(0 , merveille.getStade());
        merveille.incrementeStade();
        assertEquals(1 , merveille.getStade());
        merveille.incrementeStade();
        assertEquals(2 , merveille.getStade());
        merveille.incrementeStade();
        assertEquals(3 , merveille.getStade());
    }

    /**
     * Test de la methode getGain()
     * On verifie que la methode est bien call avec un test mockito
     * On verifie si on a recu le bon gain associe a la merveille
     */
    @Test
    public void getGain() {
        assertEquals(EnumRessources.BOIS , merveille.getGain());
    }
    /**
     *
     * Test de la methode getEtape()
     * On verifie que la methode est bien call avec un verify avec un test mockito
     * On verifie si l'ArrayList des Carte associées pour chaque étage de la merveille est la bonne
     * Ici, la methode getEtape() applique sur merveille doit renvoyer arrayOfCarte
     */
    @Test
    public void getEtape() {
        assertEquals(merveille.getEtape(), arrayOfCarte);
    }


    /**
     * Test de la redéfinition de la méthode equals
     * On teste si la methode detecte la difference du nom de la carte chantier, ecrit avec une erreur
     */
    @Test
    public void equalsTest() {
        assertEquals(new Merveille(Wonder.BABYLON, EnumRessources.BOIS, arrayOfCarte, joueur1), merveille);
        assertNotEquals(new Merveille(Wonder.STATUELIBERTE, EnumRessources.BOIS, arrayOfCarte, joueur1), merveille);

        assertNotEquals(merveille , new MainJoueur());
        assertNotEquals(merveille.hashCode(), new Merveille(Wonder.STATUELIBERTE, EnumRessources.BOIS, arrayOfCarte, joueur1));
    }
}


