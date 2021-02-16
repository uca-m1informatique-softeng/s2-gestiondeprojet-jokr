package joueur;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import metier.Wonder;
import objet_commun.Carte;
import objet_commun.Merveille;
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

public class IAscientifiqueTest {

    private IAscientifique iAscientifique;

    private SetInventaire setInv1, setInv2;
    private Joueur joueur1, joueur2;
    private Carte chantier, officine, ecole, guildScientifiques;
    private List<Carte> main;
    private List<EnumRessources> listeRessource;

    private Merveille babylon;

    private List<Inventaire> listeInventaire;
    private List<Joueur> listeJoueur;

    private Plateau plateau;

    /**
     * Preparation des tests de la classe IAscientifique
     */
    @Before
    public void setup() {
        iAscientifique = new IAscientifique();

        setInv1 = new SetInventaire( "url1", "j1");
        Merveille merveille1 = new Merveille(Wonder.BABYLON, EnumRessources.BOIS, new ArrayList<>());
        setInv1.modifMerveille(merveille1);

        setInv2 = new SetInventaire( "url2", "j2");
        Merveille merveille2 = new Merveille(Wonder.BABYLONNUIT, EnumRessources.BOIS, new ArrayList<>());
        setInv2.modifMerveille(merveille2);
        listeRessource = new ArrayList<>();

        joueur1 = new Joueur(setInv1.getId(),Strategy.AMBITIEUSE,setInv1.getJoueurName());
        joueur2 = new Joueur(setInv2.getId(),Strategy.AMBITIEUSE,setInv2.getJoueurName());

        officine = new Carte(EnumCarte.V3, Collections.singletonList(EnumRessources.TISSU), Collections.singletonList(EnumRessources.COMPAS), 3, 1, EnumRessources.VERTE);
        guildScientifiques = new Carte(EnumCarte.P7, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUSCPR), 3, 3, EnumRessources.VIOLETTE);
        chantier = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON);
        ecole = new Carte(EnumCarte.V4, Arrays.asList(EnumRessources.BOIS, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.PDR), 3, 2, EnumRessources.VERTE);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);

        listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur1);
        listeJoueur.add(joueur2);

        plateau = new Plateau(listeInventaire);


        List<Carte> etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUSCPR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        babylon = new Merveille(Wonder.BABYLON, EnumRessources.BOIS, etape);
    }


    /**
     * Test de la méthode choixMain()
     */
    @Test
    public void choixMainTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(officine);
        main.add(guildScientifiques);
        // L'IA choisie en priorité la carte officine pour l'âge 1, donc l'index 1
        assertEquals(1, iAscientifique.choixMain(joueur1, main,setInv1, plateau, false));

        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(officine);
        main.add(guildScientifiques);
        main.add(ecole);
        // L'IA choisie en priorité la carte ecole pour l'âge 2, donc l'index 3
        assertEquals(3, iAscientifique.choixMain(joueur1, main,setInv1, plateau, false));

        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(guildScientifiques);
        main.add(chantier);
        main.add(officine);
        main.add(ecole);
        // L'IA choisie en priorité la guilde scientifique pour l'âge 3, donc l'index 0
        assertEquals(0, iAscientifique.choixMain(joueur1, main,setInv1, plateau, false));
    }



    /**
     * Test de la méthode rechercheRessources()
     */
    @Test
    public void rechercheRessourcesTest() {
        plateau.incrementeAge();
        List<EnumRessources> listeRessources = new ArrayList<>();
        listeRessources.add(EnumRessources.VERRE);
        listeRessources.add(EnumRessources.PAPYRUS);
        listeRessources.add(EnumRessources.TISSU);
        assertEquals(iAscientifique.rechercheRessources(joueur1,setInv1,plateau), listeRessources);
        plateau.incrementeAge();
        listeRessources.add(EnumRessources.BOIS);
        listeRessources.add(EnumRessources.PIERRE);
        listeRessources.add(EnumRessources.ARGILE);
        listeRessources.add(EnumRessources.MINERAI);
        assertEquals(iAscientifique.rechercheRessources(joueur1,setInv1,plateau), listeRessources);
        plateau.incrementeAge();
        assertEquals(iAscientifique.rechercheRessources(joueur1,setInv1,plateau),listeRessource);

    }

    /**
     * Test de la méthode choixMerveille()
     */
    @Test
    public void choixMerveilleTest(){
        setInv1.modifMerveille(babylon);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);

        plateau = new Plateau(listeInventaire);

        // Le joueur na pas les ressource pour construire sa merveille
        assertFalse(iAscientifique.choixMerveille(joueur1, main,setInv1, plateau));

        // On donne 2 argile au joueurs
        setInv1.increaseValue(EnumRessources.ARGILE, 2);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);

        plateau = new Plateau(listeInventaire);

        // Le joueur a 2 argile mais il a la stratégie Scientifique donc il ne construit pas du tout sa merveille
        assertFalse(iAscientifique.choixMerveille(joueur1, main,setInv1, plateau));
    }


    /**
     * Test de la méthode choisirCarteDeLaDefausse()
     */
    @Test
    public void choisirCarteDeLaDefausseTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(officine);
        main.add(guildScientifiques);
        // L'IA choisie en priorité la carte officine pour l'âge 1, donc l'index 1
        assertEquals(1, iAscientifique.choisirCarteDeLaDefausse(joueur1, main,setInv1, plateau));
    }

    /**
     * Test de la méthode commerceAdjacent() de l'interface IA
     */
    @Test
    public void commerceAdjacentTest(){
        setInv1.increaseValue(EnumRessources.REDMARRONGAUCHE, 1);
        // Renvoie true car le joueur a la réduc a gauche
        assertTrue(iAscientifique.commerceAdjacent(EnumRessources.BOIS, joueur1,setInv1, setInv1, setInv2));


        setInv1.clear();
        setInv1.increaseValue(EnumRessources.REDMARRONDROITE, 1);
        // Renvoie false car le joueur a la réduc a droite
        assertFalse(iAscientifique.commerceAdjacent(EnumRessources.BOIS, joueur1,setInv1, setInv1, setInv2));

        setInv1.clear();
        setInv1.increaseValue(EnumRessources.SCORE, 2);
        // Renvoie false car le joueur gauche a plus de point
        assertFalse(iAscientifique.commerceAdjacent(EnumRessources.BOIS, joueur1,setInv1, setInv1, setInv2));


        setInv1.clear();
        setInv2.increaseValue(EnumRessources.SCORE, 1);
        // Renvoie true car le joueur droit a plus de point
        assertTrue(iAscientifique.commerceAdjacent(EnumRessources.BOIS, joueur1,setInv1, setInv1, setInv2));
    }
}

