package utilitaire_jeu;

import metier.Strategy;
import org.junit.Before;
import org.junit.Test;
import utilitaire_jeu.SetInventaire;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class PlateauTest {
    private SetInventaire inv1, inv2, inv3, inv4, inv5;
    private Plateau plateau;
    private ArrayList<Inventaire> listeInventaire;


    /**
     * Preparation des tests de la classe Plateau
     */
    @Before
    public void setUp() {
        inv1 = new SetInventaire(1, Strategy.ULTIME, "Davy");
        inv2 = new SetInventaire(2, Strategy.RANDOM, "Vincent");
        inv3 = new SetInventaire(3, Strategy.RANDOM, "Pierre");
        inv4 = new SetInventaire(4, Strategy.ULTIME, "Hichem");
        inv5 = new SetInventaire(5, Strategy.RANDOM, "Thomas");



        listeInventaire = new ArrayList<Inventaire>(){{add(inv1);add(inv2);add(inv3);add(inv4);add(inv5);}};

        plateau = new Plateau(listeInventaire);
    }


    /**
     * Test du Getter getAge()
     */
    @Test
    public void getAgeTest() {
        assertEquals(0, plateau.getAge());
    }


    /**
     * Test de la méthode incrementeAge() pour chaque age en incrementant l age teste avec la metohde incrementeAge()
     */
    @Test
    public void incrementeAgeTest() {
        assertEquals(0, plateau.getAge());
        plateau.incrementeAge();
        assertEquals(1, plateau.getAge());
        plateau.incrementeAge();
        assertEquals(2, plateau.getAge());
        plateau.incrementeAge();
        assertEquals(3, plateau.getAge());
    }


    /**
     * Test de la méthode incrementeTour()
     * On regarde si avant et apres si la methode incremente bien de 1 les tours avec la methode getTour() et une boucle for associe
     */
    @Test
    public void incrementeTourTest() {
        for (int i = 0; i < 8; i++) {
            assertEquals(i, plateau.getTour());
            plateau.incrementeTour();
        }
    }


    /**
     * Test de la méthode restartTour()
     * On verifie si la methode a bien restart les tours en regardant la valeur du tour en cours avant et apres le restart
     */
    @Test
    public void restartTourTest() {
        for (int i = 0; i < 8; i++) {
            assertEquals(i, plateau.getTour());
            plateau.incrementeTour();
        }
        plateau.restartTour();
        for (int i = 0; i < 8; i++) {
            assertEquals(i, plateau.getTour());
            plateau.incrementeTour();
        }
    }


    /**
     * Test du Getter getTour() en regardant si le tour actuel est bien le 1er
     * La methode doit renvoyer 0 ici
     */
    @Test
    public void getTourTest() {
        assertEquals(0, plateau.getTour());
    }


    /**
     * Test de la méthode getListeInventaire() ayant une liste des inventaires
     * La methode doit renvoyer listeInventaire ici
     */
    @Test
    public void getListeInventaire() {
        assertEquals(plateau.getListeInventaire(),listeInventaire);
    }




    /**
     * Test de la méthode joueurDroit() donnant le joueur de droite d un joueur specifique
     *  Le joueur 1 a pour joueur de droite joueur2, Le joueur 2 a pour joueur de droite joueur3
     *  Le joueur 3 a pour joueur de droite joueur4, Le joueur 4 a pour joueur de droite joueur5
     */
    @Test
    public void joueurDroit() {
        assertEquals(plateau.joueurDroit(joueur1),joueur2);
        assertEquals(plateau.joueurDroit(joueur2),joueur3);
        assertEquals(plateau.joueurDroit(joueur3),joueur4);
        assertEquals(plateau.joueurDroit(joueur4),joueur5);

        Plateau pl = new Plateau(new ArrayList<Inventaire>(), new ArrayList<Joueur>());
        assertNull(pl.joueurDroit(joueur1));
    }


    /**
     * Test de la méthode joueurGauche() donnant le joueur de gauche d un joueur specifique
     * Le joueur 2 a pour joueur de gauche joueur1, Le joueur 3 a pour joueur de gauche joueur2
     * Le joueur 4 a pour joueur de gauche joueur3, Le joueur 5 a pour joueur de gauche joueur4
     */
    @Test
    public void joueurGauche() {
        assertEquals(plateau.joueurGauche(joueur2),joueur1);
        assertEquals(plateau.joueurGauche(joueur3),joueur2);
        assertEquals(plateau.joueurGauche(joueur4),joueur3);
        assertEquals(plateau.joueurGauche(joueur5),joueur4);

        Plateau pl = new Plateau(new ArrayList<Inventaire>(), new ArrayList<Joueur>());
        assertNull(pl.joueurGauche(joueur1));
    }
}