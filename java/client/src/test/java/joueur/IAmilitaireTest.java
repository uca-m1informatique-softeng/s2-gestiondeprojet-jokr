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


public class IAmilitaireTest {


    private IAmilitaire iAmilitaire;

    private SetInventaire setInv1, setInv2;
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

        setInv1 = new SetInventaire("url1", "j1");
        setInv2 = new SetInventaire( "url2", "j2");

        joueur1 = new Joueur(1,Strategy.AMBITIEUSE,setInv1.getJoueurName());

        Joueur joueur2 = new Joueur(2,Strategy.AMBITIEUSE,setInv2.getJoueurName());

        chantier = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON);
        palissade = new Carte(EnumCarte.R3, Collections.singletonList(EnumRessources.PIERRE), Collections.singletonList(EnumRessources.BOUCLIER), 3, 1, EnumRessources.ROUGE);
        ecurie = new Carte(EnumCarte.R6, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 3, 2, EnumRessources.ROUGE);
        arsnale = new Carte(EnumCarte.R9, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.TISSU), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 3, 3, EnumRessources.ROUGE);


        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);

        listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur1);
        listeJoueur.add(joueur2);

        plateau = new Plateau(listeInventaire);

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
        assertEquals(1, iAmilitaire.choixMain(joueur1, main,setInv1, plateau, false));

        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(palissade);
        main.add(arsnale);
        main.add(ecurie);
        // L'IA choisie en priorité la carte ecurie pour l'âge 2, donc l'index 3
        assertEquals(3, iAmilitaire.choixMain(joueur1, main,setInv1, plateau, false));

        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(arsnale);
        main.add(chantier);
        main.add(palissade);
        main.add(ecurie);
        // L'IA choisie en priorité la carte arsenale scientifique pour l'âge 3, donc l'index 0
        assertEquals(0, iAmilitaire.choixMain(joueur1, main, setInv1,plateau, false));
    }


    /**
     * Test de la méthode choixMerveille()
     */
    @Test
    public void choixMerveilleTest() {
        setInv2.modifMerveille(rhodos);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);

        plateau = new Plateau(listeInventaire);

        // Le joueur na pas les ressource pour construire sa merveille
        //assertFalse(iAmilitaire.choixMerveille(joueur1, main, plateau));

        // On donne 2 bois au joueurs
        setInv1.increaseValue(EnumRessources.BOIS, 2);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);

        plateau = new Plateau(listeInventaire);

        // Le joueur a assez de ressource (2 bois)
        //assertTrue(iAmilitaire.choixMerveille(joueur1, main, plateau));
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
        assertEquals(1, iAmilitaire.choisirCarteDeLaDefausse(joueur1, main,setInv1, plateau));
    }
}
