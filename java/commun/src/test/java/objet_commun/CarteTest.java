package objet_commun;

import metier.EnumCarte;
import metier.EnumRessources;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
public class CarteTest {


    private Carte carte0, carte1, carte2, carte3, carte4, carte5, carteMerveille;
    //private GenererCarte genererCarte;

    /**
     * Preparation des tests de la classe Carte
     */
    @Before
    public void setup() {
        carte0 = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS),3,1,EnumRessources.MARRON);
        carte1 = new Carte(EnumCarte.M5, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PIERRE),6,2,EnumRessources.GRISE);
        carte2 = new Carte(EnumCarte.M4, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.ARGILE),4,1,EnumRessources.BLEUE);
        carte3 = new Carte(EnumCarte.M3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.MINERAI),7,2,EnumRessources.ROUGE);
        carte4 = new Carte(EnumCarte.M2, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIAM),3,3,EnumRessources.VERTE);
        carte5 = new Carte(EnumCarte.M1, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIBP),5,1,EnumRessources.MARRON);

        carteMerveille = new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE));

        //genererCarte = new GenererCarte(1, 3);
    }


    /**
     * Test du getter getNom() sur des cartes initialises
     */
    @Test
    public void getNomTest() {
        assertEquals(EnumCarte.M6,carte0.getNom());
        assertEquals(EnumCarte.M5, carte1.getNom());
        assertEquals(EnumCarte.M4,carte2.getNom());
        assertEquals(EnumCarte.M3,carte3.getNom());
        assertEquals(EnumCarte.M2,carte4.getNom());
        assertEquals(EnumCarte.M1,carte5.getNom());

        assertEquals(EnumCarte.MERVEILLE, carteMerveille.getNom());
    }

    /**
     * Test du getter getPrix() sur des cartes initialises soit gratuites, soit payantes en pieces
     */
    @Test
    public void getPrixTest() {
        assertEquals(Collections.singletonList(EnumRessources.GRATUIT),carte0.getPrix());
        assertEquals(Collections.singletonList(EnumRessources.GRATUIT),carte1.getPrix());
        assertEquals(Collections.singletonList(EnumRessources.GRATUIT),carte2.getPrix());
        assertEquals(Collections.singletonList(EnumRessources.GRATUIT),carte3.getPrix());
        assertEquals(Collections.singletonList(EnumRessources.PIECE),carte4.getPrix());
        assertEquals(Collections.singletonList(EnumRessources.PIECE),carte5.getPrix());

        assertEquals(Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE),carteMerveille.getPrix());
    }



    /**
     * Test du getter GetGain() sur des cartes ayant un gain de ressources differentes
     */
    @Test
    public void getGainTest() {
        assertEquals(Collections.singletonList(EnumRessources.BOIS),carte0.getGain());
        assertEquals(Collections.singletonList(EnumRessources.PIERRE),carte1.getGain());
        assertEquals(Collections.singletonList(EnumRessources.ARGILE),carte2.getGain());
        assertEquals(Collections.singletonList(EnumRessources.MINERAI),carte3.getGain());
        assertEquals(Collections.singletonList(EnumRessources.MULTIAM),carte4.getGain());
        assertEquals(Collections.singletonList(EnumRessources.MULTIBP),carte5.getGain());

        assertEquals(Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE),carteMerveille.getGain());
    }


    /**
     * Test du getter getNbJoueur() pour savoir combien de joueurs il faut avoir dans une partie pour que la carte soit mise en jeu
     */
    @Test
    public void getNbJoueurTest() {
        assertEquals(3,carte0.getNbJoueur());
        assertEquals(6,carte1.getNbJoueur());
        assertEquals(4,carte2.getNbJoueur());
        assertEquals(7,carte3.getNbJoueur());
        assertEquals(3,carte4.getNbJoueur());
        assertEquals(5,carte5.getNbJoueur());
    }


    /**
     * Test du getter getAge() pour savoir de quel Age est la carte
     */
    @Test
    public void getAgeTest() {
        assertEquals(1,carte0.getAge());
        assertEquals(2,carte1.getAge());
        assertEquals(1,carte2.getAge());
        assertEquals(2,carte3.getAge());
        assertEquals(3,carte4.getAge());
        assertEquals(1,carte5.getAge());
    }


    /**
     * Test du getter getCouleur() sur les differentes types de carte via leurs couleurs
     */
    @Test
    public void getCouleurTest() {
        assertEquals(EnumRessources.MARRON , carte0.getCouleur());
        assertEquals(EnumRessources.GRISE , carte1.getCouleur());
        assertEquals(EnumRessources.BLEUE , carte2.getCouleur());
        assertEquals(EnumRessources.ROUGE , carte3.getCouleur());
        assertEquals(EnumRessources.VERTE , carte4.getCouleur());
        assertEquals(EnumRessources.MARRON , carte5.getCouleur());
    }


    /**
     * Test de la redéfinition de la méthode equals
     * On teste si la methode detecte la difference du nom de la carte chantier, ecrit avec une erreur
     */
    @Test
    public void equalsTest() {
        assertEquals(carte0 , new Carte(EnumCarte.M6, Collections.emptyList(), Collections.singletonList(EnumRessources.BOIS),3,1, EnumRessources.MARRON));
        assertNotEquals(carte0 , new Carte(EnumCarte.M5, Collections.emptyList(), Collections.singletonList(EnumRessources.BOIS),3,1, EnumRessources.MARRON));

        //Assertions.assertNotEquals(carte0 , genererCarte.getCards());
        //Assertions.assertNotEquals(carte0 , new MainJoueur());

        assertEquals(carte1 , new Carte(EnumCarte.M5, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PIERRE),6,2,EnumRessources.GRISE));
        assertNotEquals(carte1 , new Carte(EnumCarte.M8, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PIERRE),6,2,EnumRessources.GRISE));


        //Assertions.assertNotEquals(carte1 , genererCarte.getCards());
        //Assertions.assertNotEquals(carte1 , new MainJoueur());

        assertEquals(carte5 , new Carte(EnumCarte.M1, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIBP),5,1,EnumRessources.MARRON));
        assertNotEquals(carte5 , new Carte(EnumCarte.M7, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIBP),5,1,EnumRessources.MARRON));


        //Assertions.assertNotEquals(carte5 , genererCarte.getCards());
        //Assertions.assertNotEquals(carte5 , new MainJoueur());

    }
}