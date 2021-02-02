package sw_aventure.joueur;

import exception.NegativeNumberException;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import metier.Wonder;
import org.junit.Before;
import org.junit.Test;
import sw_aventure.objetjeu.Carte;
import sw_aventure.objetjeu.Inventaire;
import sw_aventure.objetjeu.Merveille;
import sw_aventure.objetjeu.SetInventaire;
import sw_aventure.seven_wonders.Plateau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class IAmerveilleTest {


    private IAmerveille iAmerveille;

    private SetInventaire setInv1, setInv2;
    private Joueur joueur1, joueur2;

    private List<Carte> main;
    private Carte carte1, carte2, carte3, carte4;
    private Merveille babylon;

    private List<Inventaire> listeInventaire;
    private List<Joueur> listeJoueur;

    private Plateau plateau;


    /**
     * Preparation des tests de la classe IAmerveille
     */
    @Before
    public void setup() {
        iAmerveille = new IAmerveille();

        setInv1 = new SetInventaire(0, Strategy.MERVEILLE, "j1");
        setInv2 = new SetInventaire(1, Strategy.MERVEILLE, "j2");

        joueur1 = setInv1.getJoueur();
        joueur2 = setInv2.getJoueur();


        carte1 = new Carte(EnumCarte.V3, Collections.singletonList(EnumRessources.TISSU), Collections.singletonList(EnumRessources.COMPAS), 3, 1, EnumRessources.VERTE);
        carte2 = new Carte(EnumCarte.P7, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUSCPR), 3, 3, EnumRessources.VIOLETTE);
        carte3 = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON);
        carte4 = new Carte(EnumCarte.V4, Arrays.asList(EnumRessources.BOIS, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.PDR), 3, 2, EnumRessources.VERTE);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);

        listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur1);
        listeJoueur.add(joueur2);

        plateau = new Plateau(listeInventaire, listeJoueur);


        List<Carte> etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUSCPR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        babylon = new Merveille(Wonder.BABYLON, EnumRessources.BOIS, etape, joueur1);
    }


    /**
     * Test de la méthode choixMain()
     */
    @Test
    public void choixMainTest() throws NegativeNumberException {
        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);
        main.add(carte4);
        // Le joueur peut construire aucune carte alors 1 est retourné
        assertEquals(1, iAmerveille.choixMain(joueur1, main, plateau, true));

        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);
        main.add(carte4);
        main.add(carte3);
        // Le joueur peut construire la carte 3 car elle est gratuite, son index est renvoyé c'est a dire 3
        assertEquals(3, iAmerveille.choixMain(joueur1, main, plateau, true));

        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);
        main.add(carte4);
        main.add(carte3);
        // On donne 1 bois et 1 papyrus au joueur
        setInv1.increaseValue(EnumRessources.BOIS, 1);
        setInv1.increaseValue(EnumRessources.PAPYRUS, 1);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        plateau = new Plateau(listeInventaire, listeJoueur);

        // Le joueur peut acheter la carte 4 avec ses ressources alors son index est renvoyé, c'est a dire 2
        assertEquals(2, iAmerveille.choixMain(joueur1, main, plateau, true));
    }


    /**
     * Test de la méthode choixMerveille()
     */
    @Test
    public void choixMerveilleTest() throws NegativeNumberException {
        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);

        setInv1.modifMerveille(babylon);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        plateau = new Plateau(listeInventaire, listeJoueur);

        // Le joueur n'a pas assez de ressource pour construire sa merveille
        assertFalse(iAmerveille.choixMerveille(joueur1, main, plateau));

        // On donne 2 argiles au joueur
        setInv1.increaseValue(EnumRessources.ARGILE, 2);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        plateau = new Plateau(listeInventaire, listeJoueur);
        // Le joueur peut construire le premier étage de sa merveille avec ses ressources
        assertTrue(iAmerveille.choixMerveille(joueur1, main, plateau));


        setInv1.clear();
        babylon.incrementeStade();
        babylon.incrementeStade();
        babylon.incrementeStade();
        setInv1.modifMerveille(babylon);
        setInv1.increaseValue(EnumRessources.ARGILE, 2);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        plateau = new Plateau(listeInventaire, listeJoueur);
        // Le joueur ne peut pas construire sa merveille car elle est déja construite en entière
        assertFalse(iAmerveille.choixMerveille(joueur1, main, plateau));
    }


    /**
     * Test de la méthode choisirCarteDeLaDefausse
     */
    @Test
    public void choisirCarteDeLaDefausseTest() {
        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);
        main.add(carte4);
        main.add(carte3);
        // Le joueur peut construire la carte 3 car elle est gratuite, son index est renvoyé c'est a dire 3
        assertEquals(3, iAmerveille.choisirCarteDeLaDefausse(joueur1, main, plateau));
    }


    /**
     * Test de la méthode choixConstrMerveille() de l'interface IA
     */
    @Test
    public void choixConstrMerveilleTest() throws NegativeNumberException {
        ArrayList<Wonder> listeMerveille = new ArrayList<>();

        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);
        setInv1.modifMerveille(babylon);

        // Le joueur ne peut pas construire sa merveille car elle ne figure pas dans la classe
        assertFalse(iAmerveille.choixConstrMerveille(joueur1, main, plateau, listeMerveille));
        setInv1.increaseValue(EnumRessources.ARGILE, 2);
        assertFalse(iAmerveille.choixConstrMerveille(joueur1, main, plateau, listeMerveille));

        listeMerveille.add(Wonder.BABYLON);
        // Le joueur n'a pas les ressources nécessaire pour construire sa merveille
        setInv1.clear();
        assertFalse(iAmerveille.choixConstrMerveille(joueur1, main, plateau, listeMerveille));

        // On donne 2 argiles au joueur (prix du premier étage de la merveille)
        setInv1.clear();
        setInv1.increaseValue(EnumRessources.ARGILE, 2);
        setInv1.modifMerveille(babylon);
        // Le joueur peut alors construire sa merveille
        assertTrue(iAmerveille.choixConstrMerveille(joueur1, main, plateau, listeMerveille));

        // Faux car le joueur a déja construit totalement sa merveille
        setInv1.clear();
        babylon.incrementeStade();
        babylon.incrementeStade();
        babylon.incrementeStade();
        setInv1.modifMerveille(babylon);
        setInv1.increaseValue(EnumRessources.ARGILE, 2);
        assertFalse(iAmerveille.choixConstrMerveille(joueur1, main, plateau, listeMerveille));
    }


    /**
     * Test de la méthode choixCartePourMerveille() de l'interface IA
     */
    @Test
    public void choixCartePourMerveilleTest() throws NegativeNumberException {
        main = new ArrayList<>();
        main.add(carte3);
        main.add(carte1);
        main.add(carte2);
        main.add(carte4);
        // Le joueur ne peut pas construire la carte 1 donc il utilise pour sa merveille
        assertEquals(1, iAmerveille.choixCartePourMerveille(joueur1, main, plateau));

        main = new ArrayList<>();
        main.add(carte3);
        main.add(carte1);
        main.add(carte4);
        // Le joueur peut construire toutes les cartes alors 0 est renvoyée
        setInv1.increaseValue(EnumRessources.TISSU, 1);
        setInv1.increaseValue(EnumRessources.BOIS, 1);
        setInv1.increaseValue(EnumRessources.PAPYRUS, 1);
        assertEquals(0, iAmerveille.choixCartePourMerveille(joueur1, main, plateau));
    }


    /**
     * Test de la méthode approvisionnementDeRessource() de l'interface IA
     */
    @Test
    public void approvisionnementDeRessourceTest() {
        main = new ArrayList<>();
        main.add(carte3);
        main.add(carte2);
        main.add(carte4);
        main.add(carte1);

        assertEquals(carte1.getNom().toString(), iAmerveille.approvisionnementDeRessource(joueur1, main, EnumRessources.COMPAS));
    }


    /**
     * Test de la méthode chercherRessourcesDansMain() de l'interface IA
     */
    @Test
    public void chercherRessourcesDansMainTest() throws NegativeNumberException {
        Carte carteBois = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON);
        Carte cartePierre = new Carte(EnumCarte.M5, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PIERRE), 3, 1, EnumRessources.MARRON);
        Carte carteArgile = new Carte(EnumCarte.M4, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.ARGILE), 3, 1, EnumRessources.MARRON);
        Carte carteMinerai = new Carte(EnumCarte.M3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.MINERAI), 3, 1, EnumRessources.MARRON);

        main = new ArrayList<>();
        main.add(carteBois);
        main.add(cartePierre);
        main.add(carteArgile);
        main.add(carteMinerai);

        // Le joueur a pas de bois donc il va choisir la carte chantier
        assertEquals(carteBois.getNom().toString(), iAmerveille.chercherRessourcesDansMain(joueur1, new ArrayList<>(), main));

        // Le joueur a pas de minerai donc il va choisir la carte chantier
        setInv1.clear();
        setInv1.increaseValue(EnumRessources.BOIS, 10);
        assertEquals(carteMinerai.getNom().toString(), iAmerveille.chercherRessourcesDansMain(joueur1, new ArrayList<>(), main));

        // Le joueur a pas d'argile donc il va choisir la carte chantier
        setInv1.clear();
        for (EnumRessources enumRessources : Arrays.asList(EnumRessources.BOIS, EnumRessources.MINERAI)) {
            setInv1.increaseValue(enumRessources, 10);
        }
        assertEquals(carteArgile.getNom().toString(), iAmerveille.chercherRessourcesDansMain(joueur1, new ArrayList<>(), main));

        // Le joueur a pas de pierre donc il va choisir la carte chantier
        setInv1.clear();
        setInv1.increaseValue(EnumRessources.BOIS, 10);
        setInv1.increaseValue(EnumRessources.MINERAI, 10);
        setInv1.increaseValue(EnumRessources.ARGILE, 10);
        assertEquals(cartePierre.getNom().toString(), iAmerveille.chercherRessourcesDansMain(joueur1, new ArrayList<>(), main));

        // Le joueur a toutes les ressources donc une chaine vide est renvoyée
        setInv1.clear();
        setInv1.increaseValue(EnumRessources.BOIS, 10);
        setInv1.increaseValue(EnumRessources.MINERAI, 10);
        setInv1.increaseValue(EnumRessources.ARGILE, 10);
        setInv1.increaseValue(EnumRessources.PIERRE, 10);
        assertEquals("", iAmerveille.chercherRessourcesDansMain(joueur1, new ArrayList<>(), main));
    }


    /**
     * Test de la méthode seDefendre() de l'interface IA
     */
    @Test
    public void seDefendreTest() throws NegativeNumberException {
        Carte carteBouclier = new Carte(EnumCarte.R3, Collections.singletonList(EnumRessources.PIERRE), Collections.singletonList(EnumRessources.BOUCLIER), 3, 1, EnumRessources.ROUGE);


        main = new ArrayList<>();
        main.add(carte3);
        main.add(carte2);
        main.add(carte4);
        main.add(carteBouclier);
        main.add(carte1);

        // Les joueur de droite et gauche n'ont pas plus de bouclier alors une chaine vide est renvoyée
        assertEquals("", iAmerveille.seDefendre(joueur1, plateau, main));

        // Le joueur gauche a plus de bouclier que lui alors une carte donnant des bouclier est retourné
        setInv2.increaseValue(EnumRessources.BOUCLIER, 1);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv2);
        listeInventaire.add(setInv1);
        listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur1);
        listeJoueur.add(joueur2);
        plateau = new Plateau(listeInventaire, listeJoueur);
        assertEquals("Palissade", iAmerveille.seDefendre(joueur1, plateau, main));

        // Le joueur droit a plus de bouclier que lui alors une carte donnant des bouclier est retourné
        setInv2.clear();
        setInv1.clear();
        setInv2.increaseValue(EnumRessources.BOUCLIER, 1);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv2);
        listeInventaire.add(setInv1);
        listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur2);
        listeJoueur.add(joueur1);
        plateau = new Plateau(listeInventaire, listeJoueur);
        assertEquals("Palissade", iAmerveille.seDefendre(joueur1, plateau, main));


        main = new ArrayList<>();
        main.add(carte1);
        setInv2.increaseValue(EnumRessources.BOUCLIER, 1);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv2);
        listeInventaire.add(setInv1);
        plateau = new Plateau(listeInventaire, listeJoueur);
        assertEquals("", iAmerveille.seDefendre(joueur1, plateau, main));
    }


    /**
     * Test de la méthode getPossibilites() de l'interface IA
     */
    @Test
    public void getPossibilitesTest() throws NegativeNumberException {
        List<Carte> resultat;
        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);
        main.add(carte3);
        main.add(carte4);

        // Toutes les carte sont renvoyé car on a dit qu'elle sont gratuite et le joueur ne les a pas déja
        resultat = iAmerveille.getPossibilites(joueur1, main, plateau, false);
        assertTrue(resultat.contains(carte1));
        assertTrue(resultat.contains(carte2));
        assertTrue(resultat.contains(carte3));
        assertTrue(resultat.contains(carte4));

        // On donne la carte 1 au joueur, alors vue que le joueur a déja la carte 1 elle n'est pas renvoyé mais les autres si
        setInv1.ajoutCarteInv(carte1);
        resultat = iAmerveille.getPossibilites(joueur1, main, plateau, false);
        assertFalse(resultat.contains(carte1));
        assertTrue(resultat.contains(carte2));
        assertTrue(resultat.contains(carte3));
        assertTrue(resultat.contains(carte4));

        // Les carte sont gratuite alors le joueur peut construire que les carte 3 et 4.
        // Il possède déja la carte 1 et il n'a pas les ressources pour la carte 2.
        setInv1.increaseValue(EnumRessources.BOIS, 1);
        setInv1.increaseValue(EnumRessources.PAPYRUS, 1);
        resultat = iAmerveille.getPossibilites(joueur1, main, plateau, true);
        assertFalse(resultat.contains(carte1));
        assertFalse(resultat.contains(carte2));
        assertTrue(resultat.contains(carte3));
        assertTrue(resultat.contains(carte4));
    }


    /**
     * Test de la méthode choixCarte() de l'interface IA
     */
    @Test
    public void choixCarteTest() {
        main = new ArrayList<>();
        main.add(carte1);
        main.add(carte2);
        main.add(carte3);
        main.add(carte4);

        // Liste vide donc l'index de la carte gratuite est renvoyé (carte 3)
        assertEquals(2, iAmerveille.choixCarte(joueur1, main, plateau, new ArrayList<>(), new ArrayList<>(),true, true));
    }
}