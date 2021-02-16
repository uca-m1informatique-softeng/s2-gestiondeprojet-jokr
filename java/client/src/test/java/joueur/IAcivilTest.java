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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class IAcivilTest {


    private IAcivil iAcivil;

    private SetInventaire setInv1, setInv2;
    private Joueur joueur1;

    private Carte chantier, puits, statue, pantheon;
    private List<Carte> main;

    private List<Inventaire> listeInventaire;
    private List<Joueur> listeJoueur;

    private Plateau plateau;

    private Merveille gizah;


    /**
     * Preparation pour les tests de la classe IAcivil
     */
    @Before
    public void setup() {
        iAcivil = new IAcivil();

        setInv1 = new SetInventaire(1,"GREBERB", "j1");
        setInv2 = new SetInventaire( 2,"GRGERHER", "j2");

        joueur1 = new Joueur(1,Strategy.AMBITIEUSE,setInv1.getJoueurName());
        Joueur joueur2 = new Joueur(2,Strategy.AMBITIEUSE,setInv2.getJoueurName());

        chantier = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON);
        puits = new Carte(EnumCarte.B13, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 4, 1, EnumRessources.BLEUE);
        statue = new Carte(EnumCarte.B7, Arrays.asList(EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 2, EnumRessources.BLEUE);
        pantheon = new Carte(EnumCarte.B9, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.MINERAI,EnumRessources.PAPYRUS,EnumRessources.TISSU,EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 3, EnumRessources.BLEUE);


        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);

        listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur1);
        listeJoueur.add(joueur2);

        plateau = new Plateau(listeInventaire);


        List<Carte> etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE,EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        gizah = new Merveille(Wonder.GIZAH, EnumRessources.PIERRE, etape);
    }


    /**
     * Test de la méthode choixMain()
     */
    @Test
    public void choixMainTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(puits);
        main.add(pantheon);
        // L'IA choisie en priorité la carte puits pour l'âge 1, donc l'index 1
        assertEquals(1, iAcivil.choixMain(joueur1, main,setInv1, plateau, false));

        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(puits);
        main.add(pantheon);
        main.add(statue);
        // L'IA choisie en priorité la carte statue pour l'âge 2, donc l'index 3
        assertEquals(3, iAcivil.choixMain(joueur1, main,setInv1, plateau, false));

        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(pantheon);
        main.add(chantier);
        // L'IA choisie en priorité la carte pantheon pour l'âge 3, donc l'index 0
        //assertEquals(0, iAcivil.choixMain(joueur1, main, plateau, false));
    }


    /**
     * Test de la méthode choixMerveille()
     */
    @Test
    public void choixMerveilleTest() {

      
        main = new ArrayList<>();
        main.add(chantier);
        main.add(pantheon);

        setInv1.modifMerveille(gizah);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        plateau = new Plateau(listeInventaire);

        // Le joueur n'a pas assez de ressource pour construire sa merveille
        assertFalse(iAcivil.choixMerveille(joueur1, main,setInv1, plateau));

        // On donne 2 bois au joueur
        setInv1.increaseValue(EnumRessources.BOIS, 2);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        plateau = new Plateau(listeInventaire);
        // Le joueur peut construire le premier étage de sa merveille avec ses ressources
        //assertTrue(iAcivil.choixMerveille(joueur1, main, plateau));


        setInv1.clear();
        gizah.incrementeStade();
        gizah.incrementeStade();
        gizah.incrementeStade();
        setInv1.modifMerveille(gizah);
        setInv1.increaseValue(EnumRessources.BOIS, 2);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        plateau = new Plateau(listeInventaire);
        // Le joueur ne peut pas construire sa merveille car elle est déjà construite en entière
        assertFalse(iAcivil.choixMerveille(joueur1, main,setInv1, plateau));
    }

    /**
     * Test de la méthode rechercheRessources()
     */
    @Test
    public void rechercheRessourcesTest() {
        plateau.incrementeAge();
        List<EnumRessources> rendu = new ArrayList<>();
        rendu.add(EnumRessources.PIERRE);
        rendu.add(EnumRessources.TISSU);
        rendu.add(EnumRessources.MINERAI);
        rendu.add(EnumRessources.ARGILE);
        rendu.add(EnumRessources.VERRE);
        assertEquals(iAcivil.rechercheRessources(joueur1,setInv1, plateau), rendu);

        plateau.incrementeAge();
        List<EnumRessources> rend = new ArrayList<>();
        rend.add(EnumRessources.BOIS);
        rend.add(EnumRessources.TISSU);
        rend.add(EnumRessources.PIERRE);
        rend.add(EnumRessources.ARGILE);
        rend.add(EnumRessources.MINERAI);
        rend.add(EnumRessources.PAPYRUS);

        assertEquals( rend,iAcivil.rechercheRessources(joueur1,setInv1, plateau));

        plateau.incrementeAge();
        rendu = new ArrayList<>();
        assertEquals(rendu,iAcivil.rechercheRessources(joueur1,setInv1, plateau));
    }


    /**
     * Test de méthode choixCartePourMerveille()
     */
    @Test
    public void choixCartePourMerveilleTest() {
        main = new ArrayList<>();
        main.add(pantheon);
        main.add(statue);
        // Le joueur ne peut pas construire la carte jardin alors il ultilise pour sa merveille
        assertEquals(0, iAcivil.choixCartePourMerveille(joueur1, main,setInv1, plateau));

        main = new ArrayList<>();
        main.add(chantier);
        main.add(puits);
        main.add(statue);
        main.add(pantheon);
        // Le joueur ne peut pas construire la carte statue alors il utilise pour sa merveille, son index est renvoyé, c'est a dire 2
        assertEquals(2, iAcivil.choixCartePourMerveille(joueur1, main,setInv1, plateau));

        main = new ArrayList<>();
        main.add(chantier);
        main.add(puits);
        main.add(pantheon);
        // On donne des ressources au joueur
        setInv1.increaseValue(EnumRessources.MINERAI, 1);
        setInv1.increaseValue(EnumRessources.TISSU, 1);
        setInv1.increaseValue(EnumRessources.PAPYRUS, 1);
        setInv1.increaseValue(EnumRessources.VERRE, 1);
        setInv1.increaseValue(EnumRessources.ARGILE, 2);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        plateau = new Plateau(listeInventaire);

        // Le joueur peut construire toute les carte alors 1 est renvoyé
        assertEquals(1, iAcivil.choixCartePourMerveille(joueur1, main,setInv1, plateau));
    }


    /**
     * Test de la méthode choisirCarteDeLaDefausse()
     */
    @Test
    public void choisirCarteDeLaDefausseTest() {
        plateau.incrementeAge();
        main = new ArrayList<>();
        main.add(chantier);
        main.add(puits);
        main.add(pantheon);
        // L'IA choisie en priorité la carte puits pour l'âge 1, donc l'index 1
        assertEquals(1, iAcivil.choisirCarteDeLaDefausse(joueur1, main,setInv1, plateau));
    }


}
