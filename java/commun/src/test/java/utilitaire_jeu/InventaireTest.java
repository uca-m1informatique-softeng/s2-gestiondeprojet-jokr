package utilitaire_jeu;

import objet_commun.Carte;
import objet_commun.Merveille;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Wonder;
import org.junit.Before;
import org.junit.Test;
import utils.affichage.Colors;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class InventaireTest {

    private List<Inventaire> listInv ;
    private SetInventaire setInv1, setInv2, setInv3, setInv4, setInv5;
    private Merveille babylon;

    /**
     * Preparation des tests de la classe Inventaire
     */
    @Before
    public void setUp() {
        setInv1 = new SetInventaire( 1,"random", "Enzo");
        setInv2 = new SetInventaire(2, "militaire", "Christina");
        setInv3 = new SetInventaire( 3,"scientifique", "Mona");
        setInv4 = new SetInventaire( 4,"random", "Paul");
        setInv5 = new SetInventaire(5, "civile", "Lucie");

        listInv = new ArrayList<>();
        listInv.add(setInv1);
        listInv.add(setInv2);
        listInv.add(setInv3);
        listInv.add(setInv4);
        listInv.add(setInv5);

        List<Carte> etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUSCPR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        babylon = new Merveille(Wonder.BABYLON, EnumRessources.BOIS, etape);

        setInv1.modifMerveille(babylon);
        setInv2.modifMerveille(babylon);
        setInv3.modifMerveille(babylon);
        setInv4.modifMerveille(babylon);
        setInv5.modifMerveille(babylon);
    }


    /**
     * Test de la méthode printInventaire()
     */
    @Test
    public void printInventaireTest() {
        for (Inventaire s : listInv) {
            ArrayList<String> invPrint = new ArrayList<>();
            invPrint.add(Colors.gJaune("Pièce\t:\t") + s.getValue(EnumRessources.PIECE));
            invPrint.add(Colors.gStandard("Bois\t:\t") + s.getValue(EnumRessources.BOIS));
            invPrint.add(Colors.gStandard("Pierre\t:\t") + s.getValue(EnumRessources.PIERRE));
            invPrint.add(Colors.gStandard("Argile\t:\t") + s.getValue(EnumRessources.ARGILE));
            invPrint.add(Colors.gStandard("Minerai\t:\t") + s.getValue(EnumRessources.MINERAI));
            invPrint.add(Colors.gStandard("Tissu\t:\t") + s.getValue(EnumRessources.TISSU));
            invPrint.add(Colors.gStandard("Verre\t:\t") + s.getValue(EnumRessources.VERRE));
            invPrint.add(Colors.gStandard("Papyrus\t:\t") + s.getValue(EnumRessources.PAPYRUS));
            invPrint.add(Colors.gBleu("Score\t:\t") + s.getValue(EnumRessources.SCORE));
            invPrint.add(Colors.gRouge("Bouclier:\t") + s.getValue(EnumRessources.BOUCLIER));
            invPrint.add(Colors.gVert("Compas\t:\t") + s.getValue(EnumRessources.COMPAS));
            invPrint.add(Colors.gVert("Roue\t:\t") + s.getValue(EnumRessources.ROUE));
            invPrint.add(Colors.gVert("Rosette\t:\t") + s.getValue(EnumRessources.PDR));
            invPrint.add(Colors.gRouge("P.Victoire:\t") + s.getValue(EnumRessources.VICTOIRE));
            invPrint.add(Colors.gRouge("P.Défaite:\t") + s.getValue(EnumRessources.DEFAITE));

            assertEquals(invPrint, s.printInventaire());
        }
    }


    /**
     * Test de la méthode compteFinalScore() en regardant l'inventaire de joueurs remis a dans leur etat initial au debut de la partie
     * Chaque joueur a donc un score de 1 venant des 3 pieces dont chaque inventaire initial est dote
     */
    @Test
    public void compteFinalScoreTest() {
        assertEquals(1 , setInv1.compteFinalScore(setInv5, setInv2,false));
        assertEquals(1 , setInv2.compteFinalScore(setInv1, setInv3,false));
        assertEquals(1, setInv3.compteFinalScore(setInv2, setInv4, false));
        assertEquals(1, setInv4.compteFinalScore(setInv3, setInv5, false));
        assertEquals(1, setInv5.compteFinalScore(setInv4, setInv1, false));
    }


    /**
     * Test de la méthode compteBonus
     */
    @Test
    public void compteBonusTest() {
        setInv1.increaseValue(EnumRessources.BONUS11J, 1);
        setInv1.increaseValue(EnumRessources.BONUS11M, 1);
        setInv1.increaseValue(EnumRessources.BONUS22G, 1);
        setInv1.increaseValue(EnumRessources.BONUS31R, 1);
        setInv1.increaseValue(EnumRessources.BONUS31MERVEILLE, 1);
        setInv1.increaseValue(EnumRessources.VBONUS1M, 1);
        setInv1.increaseValue(EnumRessources.VBONUS2G, 1);
        setInv1.increaseValue(EnumRessources.VBONUS1B, 1);
        setInv1.increaseValue(EnumRessources.VBONUS1J, 1);
        setInv1.increaseValue(EnumRessources.VBONUS1R, 1);
        setInv1.increaseValue(EnumRessources.VBONUS1V, 1);
        setInv1.increaseValue(EnumRessources.VBONUSMGV, 1);
        setInv1.increaseValue(EnumRessources.VBONUS1MERVEILLE, 1);
        setInv1.increaseValue(EnumRessources.VBONUS7COMPLETMERVEILLE, 1);

        setInv1.getMerveille().incrementeStade();
        setInv1.getMerveille().incrementeStade();
        setInv1.getMerveille().incrementeStade();

        // Le joueur remporte 19 point avec ses bonus de merveille réussi
        assertEquals(19, setInv1.compteBonus(setInv5, setInv2));
    }


    /**
     * Test de la méthode compteScientifique
     */
    @Test
    public void compteScientifiqueTest() {
        setInv1.increaseValue(EnumRessources.ROUE, 1);
        setInv1.increaseValue(EnumRessources.COMPAS, 1);
        setInv1.increaseValue(EnumRessources.PDR, 1);

        // Le joueur remporte 10 pts scientifique car : 1 roue = 1 pts, 1 Compas = 1 pts, 1 Pierre de Rosette = 1 pts, et + 7 pts car il possède un groupe de 3
        assertEquals(10, setInv1.compteScientifique());

        setInv1.increaseValue(EnumRessources.BONUSCPR, 1);
        assertEquals(13, setInv1.compteScientifique());

        setInv1.increaseValue(EnumRessources.BONUSCPR, 1);
        assertEquals(18, setInv1.compteScientifique());
    }


    /**
     * Test de la méthode bonusScientifique1()
     */
    @Test
    public void bonusScientifique1Test() {
        assertEquals(38, setInv1.bonusScientifique1(10, 3, 2, 2, 1));
        assertEquals(38, setInv1.bonusScientifique1(10, 2, 3, 2, 1));
        assertEquals(38, setInv1.bonusScientifique1(10, 2, 2, 3, 1));
    }


    /**
     * Test de la méthode bonusScientifique2()
     */
    @Test
    public void bonusScientifique2Test() {
        assertEquals(48, setInv1.bonusScientifique2(10, 3, 2, 2));
        assertEquals(48, setInv1.bonusScientifique2(10, 2, 3, 2));
        assertEquals(48, setInv1.bonusScientifique2(10, 2, 2, 3));
    }


    /**
     * Test de la méthode calculScientifique()
     */
    @Test
    public void calculScientifiqueTest() {
        // Le joueur remporte 10 pts scientifique car : 1 roue = 1 pts, 1 Compas = 1 pts, 1 Pierre de Rosette = 1 pts, et + 7 pts car il possède un groupe de 3
        assertEquals(10, setInv1.calculScientifique(1, 1, 1));

        assertEquals(21, setInv1.calculScientifique(2, 3, 1));
    }


    /**
     * Test du getter getListeCarte()
     */
    @Test
    public void getListeCarteTest() {
        List<EnumCarte> listCarte = new ArrayList<>();
        listCarte.add(EnumCarte.M6);
        listCarte.add(EnumCarte.M5);
        listCarte.add(EnumCarte.B9);
        listCarte.add(EnumCarte.J5);

        setInv1.listeCarte = listCarte;
        assertEquals("Chantier", setInv1.getListeCarte().get(0).toString());
        assertEquals("Cavité", setInv1.getListeCarte().get(1).toString());
        assertEquals("Jardins", setInv1.getListeCarte().get(2).toString());
        assertEquals("Vignoble", setInv1.getListeCarte().get(3).toString());
    }


    /**
     * Test le getter getJoueur() pour verifier si le joueur est bien affilie au bon inventaire
     */
    @Test
    public void getJoueurNameTest() {
        assertEquals(setInv1.getJoueurName(), "Enzo");
        assertEquals(setInv2.getJoueurName(), "Christina");
        assertEquals(setInv3.getJoueurName(), "Mona");
        assertEquals(setInv4.getJoueurName(), "Paul");
        assertEquals(setInv5.getJoueurName(), "Lucie");
    }


    
    /**
     * On vérifie si l'inventaire est bien initialisé en regardant la valeur des arguments avec seulement 3 pieces
     */
    @Test
    public void getValueTest() {
        for (Inventaire s : listInv) {
            assertEquals(0 , s.getValue(EnumRessources.SCORE));
            assertEquals(3 , s.getValue(EnumRessources.PIECE));
            assertEquals(0 , s.getValue(EnumRessources.PIERRE));
            assertEquals(0 , s.getValue(EnumRessources.ARGILE));
            assertEquals(0 , s.getValue(EnumRessources.MINERAI));
            assertEquals(0 , s.getValue(EnumRessources.TISSU));
            assertEquals(0 , s.getValue(EnumRessources.VERRE));
            assertEquals(0 , s.getValue(EnumRessources.PAPYRUS));
            assertEquals(0 , s.getValue(EnumRessources.VICTOIRE));
            assertEquals(0 , s.getValue(EnumRessources.DEFAITE));
            assertEquals(0 , s.getValue(EnumRessources.COMPAS));
            assertEquals(0 , s.getValue(EnumRessources.ROUE));
            assertEquals(0 , s.getValue(EnumRessources.PDR));
        }
    }


    /**
     * Test du getter getSac()
     */
    @Test
    public void getSacTest() {
        for (Inventaire s : listInv) {
            for (Map.Entry<EnumRessources, Integer> entry : s.getSac().entrySet()) {
                EnumRessources key = entry.getKey();
                int value = entry.getValue();

                if (key.equals(EnumRessources.PIECE))
                    assertEquals(3, value);
                else
                    assertEquals(0, value);
            }
        }
    }


    /**
     * Test de la méthode getMerveille()
     */
    @Test
    public void getMerveille() {
        setInv1.merveille = babylon;

        assertEquals(setInv1.getMerveille(), babylon);
    }
}


