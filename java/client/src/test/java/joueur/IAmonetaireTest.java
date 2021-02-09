package joueur;

import exception.NegativeNumberException;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import metier.Wonder;
import objet_commun.Carte;
import objet_commun.Merveille;
import org.junit.Before;
import org.junit.Test;
import sw_aventure.objetjeu.SetInventaire;
import sw_aventure.seven_wonders.Plateau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class IAmonetaireTest {

    private IAmonetaire iAmonetaire;

    private SetInventaire set1, set2;
    private Joueur joueur1;

    private Carte chantier, taverne, vignoble, phare;
    private List<Carte> main;

    private Merveille ephesos;

    private List<Inventaire> listeInventaire;
    private List<Joueur> listeJoueur;

    private Plateau plateau;


    /**
     * Preparation des tests de la classe IAmonetaire
     */
    @Before
    public void setup() {
        iAmonetaire = new IAmonetaire();

        set1 = new SetInventaire(0, Strategy.SCIENTIFIQUE, "j1");
        set2 = new SetInventaire(1, Strategy.SCIENTIFIQUE, "j2");

        joueur1 = set1.getJoueur();
        Joueur joueur2 = set2.getJoueur();

        chantier = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON);
        taverne = new Carte(EnumCarte.J4, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE), 7, 1, EnumRessources.JAUNE);
        vignoble = new Carte(EnumCarte.J5, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BONUS1MDJG), 3, 2, EnumRessources.JAUNE);
        phare = new Carte(EnumCarte.J9, Arrays.asList(EnumRessources.PIERRE, EnumRessources.VERRE), Collections.singletonList(EnumRessources.BONUS11J), 3, 3, EnumRessources.JAUNE);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(set1);
        listeInventaire.add(set2);

        listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur1);
        listeJoueur.add(joueur2);

        plateau = new Plateau(listeInventaire, listeJoueur);


        List<Carte> etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI,EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        ephesos = new Merveille(Wonder.EPHESOS,EnumRessources.PAPYRUS, etape);
    }


    /**
     * Test de la méthode choixMain()
     */
    @Test
    public void choixMainTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(taverne);
        main.add(phare);
        // L'IA choisie en priorité la carte taverne pour l'âge 1, donc l'index 1
        assertEquals(1, iAmonetaire.choixMain(joueur1, main, plateau, false));

        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(taverne);
        main.add(phare);
        main.add(vignoble);
        // L'IA choisie en priorité la carte vignoble pour l'âge 2, donc l'index 3
        assertEquals(3, iAmonetaire.choixMain(joueur1, main, plateau, false));

        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(phare);
        main.add(chantier);
        main.add(taverne);
        main.add(vignoble);
        // L'IA choisie en priorité la carte phare scientifique pour l'âge 3, donc l'index 0
        assertEquals(0, iAmonetaire.choixMain(joueur1, main, plateau, false));
    }


    /**
     * Test de la méthode choixMerveille()
     */
    @Test
    public void choixMerveilleTest() throws NegativeNumberException {
        set1.modifMerveille(ephesos);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(set1);
        listeInventaire.add(set2);

        plateau = new Plateau(listeInventaire, listeJoueur);

        // Le joueur na pas les ressource pour construire sa merveille
        assertFalse(iAmonetaire.choixMerveille(joueur1, main, plateau));

        // On donne 2 argile au joueurs
        set1.increaseValue(EnumRessources.ARGILE, 2);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(set1);
        listeInventaire.add(set2);

        plateau = new Plateau(listeInventaire, listeJoueur);

        // Le joueur a assez de ressource (2 argile)
        assertTrue(iAmonetaire.choixMerveille(joueur1, main, plateau));
    }


    /**
     * Test de la méthode choisirCarteDeLaDefausse()
     */
    @Test
    public void choisirCarteDeLaDefausseTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(taverne);
        main.add(phare);
        // L'IA choisie en priorité la carte taverne pour l'âge 1, donc l'index 1
        assertEquals(1, iAmonetaire.choisirCarteDeLaDefausse(joueur1, main, plateau));
    }
}