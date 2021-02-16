package joueur;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import objet_commun.Carte;
import org.junit.Before;
import org.junit.Test;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;
import utilitaire_jeu.SetInventaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IAambitieuseTest {

    private IAambitieuse iAambitieuse;

    private SetInventaire setInv1;

    private Joueur joueur1;

    private List<Carte> main;
    private List<String> carteRecherche;
    private List<EnumRessources> ressourceRecherche;
    private Carte carte1;
    private Carte carte2;
    private Carte carte4;

    private Plateau plateau;

    /**
     * Preparation des tests de la classe IAambitieuse
     */
    @Before
    public void setup() {
        iAambitieuse = new IAambitieuse();
        setInv1 = new SetInventaire( 1,"url1", "j1");
        SetInventaire setInv2 = new SetInventaire( 2,"url2", "j2");
        joueur1 = new Joueur(1,Strategy.AMBITIEUSE,setInv1.getJoueurName());
        Joueur joueur2 = new Joueur(2,Strategy.AMBITIEUSE,setInv2.getJoueurName());
        carte1 = new Carte(EnumCarte.V3, Collections.singletonList(EnumRessources.TISSU), Collections.singletonList(EnumRessources.COMPAS), 3, 1, EnumRessources.VERTE);
        carte2 = new Carte(EnumCarte.P7, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUSCPR), 3, 3, EnumRessources.VIOLETTE);
        carte4 = new Carte(EnumCarte.V4, Arrays.asList(EnumRessources.BOIS, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.PDR), 3, 2, EnumRessources.VERTE);
        List<Inventaire> listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        List<Joueur> listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur1);
        listeJoueur.add(joueur2);
        plateau = new Plateau(listeInventaire);
        carteRecherche = new ArrayList<>();
        carteRecherche.add("Guilde des Scientifiques");
        ressourceRecherche = new ArrayList<>();
        ressourceRecherche.add(EnumRessources.BOIS);
        ressourceRecherche.add(EnumRessources.BOIS);
        ressourceRecherche.add(EnumRessources.MINERAI);
        ressourceRecherche.add(EnumRessources.MINERAI);
    }


    /**
     * Test de la méthode choixMerveille()
     */
    @Test
    public void choixMerveilleTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte4);
        assertFalse( iAambitieuse.choixMerveille(joueur1, main,setInv1, plateau));
    }


    /**
     * Test de la méthode choixMain()
     */
    @Test
    public void choixMainTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);
        assertEquals(0, iAambitieuse.choixMain(joueur1, main,setInv1, plateau,false));
    }


    /**
     * Test de la méthode choixCarteAge3()
     */
    @Test
    public void choixCarteAge3Test() {
        plateau.incrementeAge();
        plateau.incrementeAge();
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);
        assertEquals(0, iAambitieuse.choixCarteAge3(joueur1,main,setInv1,plateau,carteRecherche,ressourceRecherche,false,false));
    }


    /**
     * Test de la méthode getTotalPoints()
     */
    @Test
    public void getTotalPointsTest() {
        int points = iAambitieuse.getTotalPoints(carte2,joueur1,setInv1,plateau);
        assertEquals(0, points);
    }


    /**
     * Test de la méthode rechercheRessources()
     */
    @Test
    public void rechercheRessourcesTest() {
        List<EnumRessources> listAttendue = new ArrayList<>();
        listAttendue.add(EnumRessources.BOIS);
        listAttendue.add(EnumRessources.PIERRE);
        listAttendue.add(EnumRessources.ARGILE);
        listAttendue.add(EnumRessources.MINERAI);
        listAttendue.add(EnumRessources.TISSU);
        listAttendue.add(EnumRessources.VERRE);
        listAttendue.add(EnumRessources.PAPYRUS);

        // Test à l'âge 1
        plateau.incrementeAge();
        assertEquals(listAttendue, iAambitieuse.rechercheRessources(joueur1,setInv1, plateau));

        // Test à l'âge 2
        plateau.incrementeAge();
        assertEquals(listAttendue, iAambitieuse.rechercheRessources(joueur1,setInv1, plateau));

        // Test à l'âge 3
        plateau.incrementeAge();
        assertEquals(new ArrayList<>(), iAambitieuse.rechercheRessources(joueur1, setInv1,plateau));
    }


    /**
     * Test de la méthode getScientistPoints()
     */
    @Test
    public void getScientistPointsTest() {
        assertEquals(1, iAambitieuse.getScientistPoints(EnumRessources.PDR, joueur1,setInv1));
        assertEquals(1, iAambitieuse.getScientistPoints(EnumRessources.ROUE, joueur1,setInv1));
        assertEquals(1, iAambitieuse.getScientistPoints(EnumRessources.COMPAS, joueur1,setInv1));

        // Le résultat attendue pour Pierre de Rosette en paramètre doit être 3, les autres reste à 1
        setInv1.increaseValue(EnumRessources.PDR, 1);
        assertEquals(3, iAambitieuse.getScientistPoints(EnumRessources.PDR, joueur1,setInv1));
        assertEquals(1, iAambitieuse.getScientistPoints(EnumRessources.ROUE, joueur1,setInv1));
        assertEquals(1, iAambitieuse.getScientistPoints(EnumRessources.COMPAS, joueur1,setInv1));

        // Tous le mode doit être a 3 pour le résultat attendue
        setInv1.increaseValue(EnumRessources.ROUE, 1);
        setInv1.increaseValue(EnumRessources.COMPAS, 1);
        assertEquals(3, iAambitieuse.getScientistPoints(EnumRessources.PDR, joueur1,setInv1));
        assertEquals(3, iAambitieuse.getScientistPoints(EnumRessources.ROUE, joueur1,setInv1));
        assertEquals(3, iAambitieuse.getScientistPoints(EnumRessources.COMPAS, joueur1,setInv1));

        // Le résultat attendue doit être de 10
        setInv1.increaseValue(EnumRessources.ROUE, 1);
        setInv1.increaseValue(EnumRessources.COMPAS, 1);
        assertEquals(10, iAambitieuse.getScientistPoints(EnumRessources.PDR, joueur1,setInv1));
    }


    /**
     * Test de la méthode besoinDeBouclier()
     */
    @Test
    public void besoinDeBouclierTest()  {
        SetInventaire setVoisinDroit = new SetInventaire(3,"url3", "voisinDroit");
        SetInventaire setVoisinGauche = new SetInventaire(4, "url4", "voisinDroit");

        // Le joueur a plus de bouclier que ses voisin
        setInv1.increaseValue(EnumRessources.BOUCLIER, 1);
        List<Inventaire> setInvs = new ArrayList<>();
        setInvs.add(setInv1);
        setInvs.add(setVoisinDroit);
        setInvs.add(setVoisinGauche);
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(joueur1);
        joueurs.add(new Joueur(0,Strategy.AMBITIEUSE,setVoisinDroit.getJoueurName()));
        joueurs.add(new Joueur(2,Strategy.AMBITIEUSE,setVoisinGauche.getJoueurName()));
        plateau = new Plateau(setInvs);

        assertFalse(iAambitieuse.besoinDeBouclier(joueur1,setInv1, plateau));


        // Le joueur a moins de bouclier que sont voisin de droite
        setVoisinDroit.increaseValue(EnumRessources.BOUCLIER, 1);
        setInvs = new ArrayList<>();
        setInvs.add(setInv1);
        setInvs.add(setVoisinDroit);
        setInvs.add(setVoisinGauche);
        joueurs = new ArrayList<>();
        joueurs.add(joueur1);
        joueurs.add(new Joueur(0,Strategy.AMBITIEUSE,setVoisinDroit.getJoueurName()));
        joueurs.add(new Joueur(2,Strategy.AMBITIEUSE,setVoisinGauche.getJoueurName()));
        plateau = new Plateau(setInvs);

        assertTrue(iAambitieuse.besoinDeBouclier(joueur1,setInv1, plateau));


        // Le joueur a moins de bouclier que sont voisin de gauche
        setVoisinDroit.clear();
        setVoisinGauche.increaseValue(EnumRessources.BOUCLIER, 1);
        setInvs = new ArrayList<>();
        setInvs.add(setInv1);
        setInvs.add(setVoisinDroit);
        setInvs.add(setVoisinGauche);
        joueurs = new ArrayList<>();
        joueurs.add(joueur1);
        joueurs.add(new Joueur(0,Strategy.AMBITIEUSE,setVoisinDroit.getJoueurName()));
        joueurs.add(new Joueur(2,Strategy.AMBITIEUSE,setVoisinGauche.getJoueurName()));
        plateau = new Plateau(setInvs);

        assertTrue(iAambitieuse.besoinDeBouclier(joueur1,setInv1, plateau));
    }


    /**
     * Test de la méthode choisirCarteDeLaDefausse()
     */
    @Test
    public void choisirCarteDeLaDefausseTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);
        main.add(carte4);
        assertEquals(0, iAambitieuse.choisirCarteDeLaDefausse(joueur1, main,setInv1, plateau));
    }
}



