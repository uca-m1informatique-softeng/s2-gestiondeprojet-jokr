package sw_aventure.joueur;

import exception.NegativeNumberException;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import metier.Wonder;
import org.junit.Before;
import objet_commun.Carte;
import org.junit.Test;
import sw_aventure.objetjeu.Inventaire;
import objet_commun.Merveille;
import sw_aventure.objetjeu.SetInventaire;
import sw_aventure.seven_wonders.Plateau;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class IAmilitaireTest {


    private IAmilitaire iAmilitaire;

    private SetInventaire set1, set2;
    private Joueur joueur1;

    private Carte chantier, palissade, ecurie, arsnale;
    private List<Carte> main;

    private Merveille rhodos;

    private List<Inventaire> listeInventaire;
    private List<Joueur> listeJoueur;

    private Plateau plateau;


    /**
     * Preparation des tests de la classe IAmilitaire
     */
    @Before
    public void setUp() {
        iAmilitaire = new IAmilitaire();

        set1 = new SetInventaire(0, Strategy.SCIENTIFIQUE, "j1");
        set2 = new SetInventaire(1, Strategy.SCIENTIFIQUE, "j2");

        joueur1 = set1.getJoueur();
        Joueur joueur2 = set2.getJoueur();

        chantier = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON);
        palissade = new Carte(EnumCarte.R3, Collections.singletonList(EnumRessources.PIERRE), Collections.singletonList(EnumRessources.BOUCLIER), 3, 1, EnumRessources.ROUGE);
        ecurie = new Carte(EnumCarte.R6, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 3, 2, EnumRessources.ROUGE);
        arsnale = new Carte(EnumCarte.R9, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.TISSU), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 3, 3, EnumRessources.ROUGE);


        listeInventaire = new ArrayList<>();
        listeInventaire.add(set1);
        listeInventaire.add(set2);

        listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur1);
        listeJoueur.add(joueur2);

        plateau = new Plateau(listeInventaire, listeJoueur);

        List<Carte> etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        rhodos = new Merveille(Wonder.RHODOS, EnumRessources.MINERAI, etape);
    }


    /**
     * Test de la méthode choixMain()
     */
    @Test
    public void choixMainTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(palissade);
        main.add(arsnale);
        // L'IA choisie en priorité la carte palissade pour l'âge 1, donc l'index 1
        assertEquals(1, iAmilitaire.choixMain(joueur1, main, plateau, false));

        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(palissade);
        main.add(arsnale);
        main.add(ecurie);
        // L'IA choisie en priorité la carte ecurie pour l'âge 2, donc l'index 3
        assertEquals(3, iAmilitaire.choixMain(joueur1, main, plateau, false));

        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(arsnale);
        main.add(chantier);
        main.add(palissade);
        main.add(ecurie);
        // L'IA choisie en priorité la carte arsenale scientifique pour l'âge 3, donc l'index 0
        assertEquals(0, iAmilitaire.choixMain(joueur1, main, plateau, false));
    }


    /**
     * Test de la méthode choixMerveille()
     */
    @Test
    public void choixMerveilleTest() throws NegativeNumberException {
        set1.modifMerveille(rhodos);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(set1);
        listeInventaire.add(set2);

        plateau = new Plateau(listeInventaire, listeJoueur);

        // Le joueur na pas les ressource pour construire sa merveille
        assertFalse(iAmilitaire.choixMerveille(joueur1, main, plateau));

        // On donne 2 bois au joueurs
        set1.increaseValue(EnumRessources.BOIS, 2);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(set1);
        listeInventaire.add(set2);

        plateau = new Plateau(listeInventaire, listeJoueur);

        // Le joueur a assez de ressource (2 bois)
        assertTrue(iAmilitaire.choixMerveille(joueur1, main, plateau));
    }


    /**
     * Test de la méthode choisirCarteDeLaDefausse
     */
    @Test
    public void choisirCarteDeLaDefausseTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(palissade);
        main.add(arsnale);
        // L'IA choisie en priorité la carte palissade pour l'âge 1, donc l'index 1
        assertEquals(1, iAmilitaire.choisirCarteDeLaDefausse(joueur1, main, plateau));
    }
}
