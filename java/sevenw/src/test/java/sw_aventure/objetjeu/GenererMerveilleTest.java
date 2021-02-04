package sw_aventure.objetjeu;

import objet_commun.Carte;
import objet_commun.Merveille;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Wonder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


public class GenererMerveilleTest {

    private GenererMerveille genererMerveille;
    private ArrayList<Carte> etape;

    @Mock
    private GenererMerveille gMerveille;


    /**
     * Preparation pour les tests de la classe GenererMerveille
     * On fera des tests avec mockito pour verifier les appels des methodes avec leurs arguments associes
     */
    @Before
    public void setup() {
        genererMerveille = new GenererMerveille();
        etape = new ArrayList<>();
        gMerveille = Mockito.mock(GenererMerveille.class);
    }


    /**
     * Test de la methode getMerveille()
     * On verifie que la methode est bien call avec les bons arguments
     */
    @Test
    public void getMerveille() {
        gMerveille.getMerveille(Wonder.HALIKARNASSOS);
        Mockito.verify(gMerveille).getMerveille(Wonder.HALIKARNASSOS);
    }


    /**
     * Test de la Merveille BABYLON
     */
    @Test
    public void fabriqueBTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI,EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUSCPR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille babylon = new Merveille(Wonder.BABYLON,EnumRessources.BOIS, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.BABYLON), babylon);
    }


    /**
     * Test de la Merveille OLYMPIA
     */
    @Test
    public void fabriqueOTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Collections.singletonList(EnumRessources.BONUSAGECOULEUR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille olympia = new Merveille(Wonder.OLYMPIA,EnumRessources.ARGILE, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.OLYMPIA), olympia);
    }


    /**
     * Test de la Merveille GIZAH
     */
    @Test
    public void fabriqueGTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille gizah = new Merveille(Wonder.GIZAH,EnumRessources.PIERRE, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.GIZAH), gizah);
    }


    /**
     * Test de la Merveille RHODOS
     */
    @Test
    public void fabriqueRTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.BOUCLIER,EnumRessources.BOUCLIER,EnumRessources.BOUCLIER)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI,EnumRessources.MINERAI,EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille rhodos = new Merveille(Wonder.RHODOS,EnumRessources.MINERAI, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.RHODOS), rhodos);
    }


    /**
     * Test de la Merveille ALEXANDRIA
     */
    @Test
    public void fabriqueATest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI), Collections.singletonList(EnumRessources.MULTIBPAM)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PAPYRUS,EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille alexandria = new Merveille(Wonder.ALEXANDRIA,EnumRessources.VERRE, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.ALEXANDRIA), alexandria);
    }


    /**
     * Test de la Merveille HALIKARNASSOS
     */
    @Test
    public void fabriqueHTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille halikarnassos = new Merveille(Wonder.HALIKARNASSOS,EnumRessources.TISSU, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.HALIKARNASSOS), halikarnassos);
    }


    /**
     * Test de la Merveille EPHESOS
     */
    @Test
    public void fabriqueETest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI,EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille ephesos = new Merveille(Wonder.EPHESOS,EnumRessources.PAPYRUS, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.EPHESOS), ephesos);
    }



    /////////// Merveille de nuit ////////////

    /**
     * Test de la Merveille BABYLON de nuit
     */
    @Test
    public void fabriqueBbisTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE), Collections.singletonList(EnumRessources.BONUS7CARTEMAIN)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.VERRE), Collections.singletonList(EnumRessources.BONUSCPR)));

        Merveille babylon = new Merveille(Wonder.BABYLONNUIT,EnumRessources.BOIS, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.BABYLONNUIT), babylon);
    }


    /**
     * Test de la Merveille OLYMPIA de nuit
     */
    @Test
    public void fabriqueObisTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUSCARTEAGEG2P)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Collections.singletonList(EnumRessources.BONUSCARTEAGEG3P)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS,EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille olympia = new Merveille(Wonder.OLYMPIANUIT,EnumRessources.ARGILE, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.OLYMPIANUIT), olympia);
    }


    /**
     * Test de la Merveille GIZAH de nuit
     */
    @Test
    public void fabriqueGbisTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PAPYRUS), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille gizah = new Merveille(Wonder.GIZAHNUIT,EnumRessources.PIERRE, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.GIZAHNUIT), gizah);
    }


    /**
     * Test de la Merveille RHODOS de nuit
     */
    @Test
    public void fabriqueRbisTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.BOUCLIER,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI,EnumRessources.MINERAI,EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille rhodos = new Merveille(Wonder.RHODOSNUIT,EnumRessources.MINERAI, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.RHODOSNUIT), rhodos);
    }


    /**
     * Test de la Merveille ALEXANDRIA de nuit
     */
    @Test
    public void fabriqueAbisTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Collections.singletonList(EnumRessources.MULTIBPAM)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI,EnumRessources.MINERAI), Collections.singletonList(EnumRessources.MULTIVPT)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille alexandria = new Merveille(Wonder.ALEXANDRIANUIT,EnumRessources.VERRE, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.ALEXANDRIANUIT), alexandria);
    }


    /**
     * Test de la Merveille HALIKARNASSOS de nuit
     */
    @Test
    public void fabriqueHbisTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG2)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG1)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG)));

        Merveille halikarnassos = new Merveille(Wonder.HALIKARNASSOSNUIT,EnumRessources.TISSU, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.HALIKARNASSOSNUIT), halikarnassos);
    }


    /**
     * Test de la Merveille EPHESOS de nuit
     */
    @Test
    public void fabriqueEbisTest() {
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI,EnumRessources.VERRE), Arrays.asList(EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.PIECE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));

        Merveille ephesos = new Merveille(Wonder.EPHESOSNUIT,EnumRessources.PAPYRUS, etape);

        assertEquals(genererMerveille.getMerveille(Wonder.EPHESOSNUIT), ephesos);
    }


    /**
     * Test si la méthode renvoie bien null si merveille inconnue et sert de detecteur d'erreur d'ortographe
     * On verifie surtout les erreurs les plus communes sur les noms de merveilles de jour et de merveilles de nuit
     */
    @Test
    public void tset() {
        assertNull(genererMerveille.getMerveille(Wonder.STATUELIBERTE));
    }
}