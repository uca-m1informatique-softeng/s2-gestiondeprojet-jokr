package joueur;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import metier.Wonder;
import objet_commun.Carte;
import objet_commun.Merveille;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;
import utilitaire_jeu.SetInventaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class IAcompositeTest {

    private IAcomposite iaComposite;

    private SetInventaire setInv1;

    private Plateau plateau;

    private Carte carteGratuite, cartePayante;

    private List<Carte> mainJoueur;

    private Joueur joueur;
    private Carte cart;
    private List<EnumRessources> lCart;
    private List<String> lCarte;
    private List<Carte> main;

    @Mock
    private IA mocIA;



    /**
     * Preparation des tests de la classe IAcomposite
    */
    @Before
    public void setUp() {
        Inventaire inv0 = new Inventaire(1,"EBREERBBE", "Omega");
        cart = new Carte(EnumCarte.B2, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 1, EnumRessources.BLEUE);
        lCart = Collections.singletonList(EnumRessources.BOIS);
        lCarte = Collections.singletonList("Atelier");
        main = new ArrayList<>();
        joueur = new Joueur(0, Strategy.COMPOSITE, "Omega", inv0);
        mocIA = Mockito.mock(IAcomposite.class);
        iaComposite = new IAcomposite();

        setInv1 = new SetInventaire(1, "GFBSENZN", "j1");
        SetInventaire setInv2 = new SetInventaire(2, "GEBEZNEQBN", "j2");
        SetInventaire setInv3 = new SetInventaire(3, "GREKCKEOKF", "j3");

        carteGratuite = new Carte(EnumCarte.B2, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 1, EnumRessources.BLEUE);
        cartePayante = new Carte(EnumCarte.B2, Collections.singletonList(EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 1, EnumRessources.BLEUE);

        mainJoueur = new ArrayList<>();
        mainJoueur.add(cartePayante);

        ArrayList<Carte> etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUSCPR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        Merveille babylon = new Merveille(Wonder.BABYLON, EnumRessources.BOIS, etape);


        setInv1.modifMerveille(babylon);
        List<Inventaire> listeInv = new ArrayList<>();
        listeInv.add(setInv1);
        listeInv.add(setInv2);
        listeInv.add(setInv3);



        plateau = new Plateau(listeInv);
    }


    /**
     * Test de la méthode choixDefausse()
     */
    @Test
    public void choixDefausseTest() {
        // Merveille Babylon donc botChameleon scientifique
        // Retourne True car le joueur peut que défausser la carte (il ne peut pas la construire)
        assertTrue(iaComposite.choixDefausse(setInv1.getJoueur(), cartePayante, plateau));

        // Retourne False car le joueur peut construire la carte
        assertFalse(iaComposite.choixDefausse(setInv1.getJoueur(), carteGratuite, plateau));
    }


    /**
     * Test de la méthode choixMain()
     */
    @Test
    public void choixMainTest() {
        // Merveille Babylon donc botChameleon scientifique
        // Retourne 0, car il y a une seul carte dans la main
        int choixDuneMain = mocIA.choixCarte(joueur, main, plateau, lCarte, lCart, true,true);
        assertEquals(0,choixDuneMain);
        assertEquals(0,iaComposite.choixMain(setInv1.getJoueur(), mainJoueur, plateau, false));
    }


    /**
     * Test de la méthode choixMerveille()
     */
    @Test
    public void choixMerveilleTest(){
        // Merveille Babylon donc botChameleon scientifique
        // Le joueur ne peut pas construire sa merveille car il lui manque 2 argiles
        assertFalse(iaComposite.choixMerveille(setInv1.getJoueur(), mainJoueur, plateau));

        // On donne 2 argiles au joueur
        setInv1.increaseValue(EnumRessources.ARGILE, 2);

        // Le joueur a la merveille babylon donc l'IA composite se branche sur l'IA Scientifique, alors le joueur ne peut pas construire sa merveille
        // car l'IA Scientifique ne construit aucune merveille
        assertFalse(iaComposite.choixMerveille(setInv1.getJoueur(), mainJoueur, plateau));
    }


    /**
     * Test de la méthode choixCartePourMerveille()
     */
    @Test
    public void choixCartePourMerveilleTest() {
        // Merveille Babylon donc botChameleon scientifique
        // La premiere carte dans la main est payante donc son index est renvoyé
        assertEquals(0,iaComposite.choixCartePourMerveille(setInv1.getJoueur(), mainJoueur, plateau));
        main.add(cart);
        main.add(carteGratuite);
        int leChoix = mocIA.choixCartePourMerveille(joueur, main, plateau);
        assertEquals(0, leChoix);
    }


    /**
     * Test de la méthode choixMerveilleStrat()
     */
    @Test
    public void choixMerveilleStratTest() {
      // Vérification du bon choix de stratégie selon la merveille
        assertTrue(iaComposite.choixMerveilleStrat(Wonder.EPHESOS) instanceof IAcivil);
        assertTrue(iaComposite.choixMerveilleStrat(Wonder.EPHESOSNUIT) instanceof IAcivil);

        assertTrue(iaComposite.choixMerveilleStrat(Wonder.RHODOS) instanceof IAcivil);
        assertTrue(iaComposite.choixMerveilleStrat(Wonder.RHODOSNUIT) instanceof IAcivil);

        assertTrue(iaComposite.choixMerveilleStrat(Wonder.BABYLON) instanceof IAscientifique);
        assertTrue(iaComposite.choixMerveilleStrat(Wonder.BABYLONNUIT) instanceof IAscientifique);

        assertTrue(iaComposite.choixMerveilleStrat(Wonder.OLYMPIA) instanceof IAcivil);
        assertTrue(iaComposite.choixMerveilleStrat(Wonder.OLYMPIANUIT) instanceof IAcivil);

        assertTrue(iaComposite.choixMerveilleStrat(Wonder.GIZAH) instanceof IAcivil);
        assertTrue(iaComposite.choixMerveilleStrat(Wonder.GIZAHNUIT) instanceof IAcivil);

        assertTrue(iaComposite.choixMerveilleStrat(Wonder.HALIKARNASSOS) instanceof IAcivil);
        assertTrue(iaComposite.choixMerveilleStrat(Wonder.HALIKARNASSOSNUIT) instanceof IAscientifique);

        assertTrue(iaComposite.choixMerveilleStrat(Wonder.ALEXANDRIA) instanceof IAcivil);
        assertTrue(iaComposite.choixMerveilleStrat(Wonder.ALEXANDRIANUIT) instanceof IAcivil);
    }
}
