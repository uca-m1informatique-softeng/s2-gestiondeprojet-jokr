package utilitaire_jeu;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class PlateauTest {
    
    private SetInventaire setInv1, setInv2, setInv3, setInv4, setInv5;
    private Plateau plateau;
    private ArrayList<Inventaire> listeInventaire;


    /**
     * Preparation des tests de la classe Plateau
     */
    @Before
    public void setUp() {
        setInv1 = new SetInventaire(1, "url1", "Davy");
        setInv2 = new SetInventaire(2, "url2", "Vincent");
        setInv3 = new SetInventaire(3, "url3", "Pierre");
        setInv4 = new SetInventaire(4, "url4", "Hichem");
        setInv5 = new SetInventaire(5, "url5", "Thomas");

        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        listeInventaire.add(setInv3);
        listeInventaire.add(setInv4);
        listeInventaire.add(setInv5);

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
        assertEquals(plateau.joueurDroit(setInv1), setInv2);
        assertEquals(plateau.joueurDroit(setInv2), setInv3);
        assertEquals(plateau.joueurDroit(setInv3), setInv4);
        assertEquals(plateau.joueurDroit(setInv4), setInv5);

        Plateau pl = new Plateau(new ArrayList<>());
        assertNull(pl.joueurDroit(setInv1));
    }


    /**
     * Test de la méthode joueurGauche() donnant le joueur de gauche d un joueur specifique
     * Le joueur 2 a pour joueur de gauche joueur1, Le joueur 3 a pour joueur de gauche joueur2
     * Le joueur 4 a pour joueur de gauche joueur3, Le joueur 5 a pour joueur de gauche joueur4
     */
    @Test
    public void joueurGauche() {
        assertEquals(plateau.joueurGauche(setInv2), setInv1);
        assertEquals(plateau.joueurGauche(setInv3), setInv2);
        assertEquals(plateau.joueurGauche(setInv4), setInv3);
        assertEquals(plateau.joueurGauche(setInv5), setInv4);

        Plateau pl = new Plateau(new ArrayList<>());
        assertNull(pl.joueurGauche(setInv1));
    }
}