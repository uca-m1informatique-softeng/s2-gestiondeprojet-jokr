package sw_aventure.objetjeu;

import exception.NegativeNumberException;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import metier.Wonder;
import sw_aventure.joueur.Joueur;
import sw_aventure.seven_wonders.Plateau;
import org.junit.Before;
import org.junit.Test;
import utils.affichage.Colors;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class InventaireTest {

    private List<Inventaire> inv ;
    private SetInventaire inv1, inv2, inv3, inv4, inv5;
    private Joueur joueur1, joueur2, joueur3, joueur4, joueur5;
    private Plateau plateau;
    private Merveille babylon;

    /**
     * Preparation des tests de la classe Inventaire
     */
    @Before
    public void setUp() {
        inv1 = new SetInventaire(1, Strategy.RANDOM, "Enzo");
        inv2 = new SetInventaire(2, Strategy.MILITAIRE, "Christina");
        inv3 = new SetInventaire(3, Strategy.SCIENTIFIQUE, "Mona");
        inv4 = new SetInventaire(4, Strategy.RANDOM, "Paul");
        inv5 = new SetInventaire(5, Strategy.CIVILE, "Lucie");

        inv = new ArrayList<>() {{add(inv1); add(inv2); add(inv3); add(inv4); add(inv5);}};

        joueur1 = inv1.getJoueur();
        joueur2 = inv2.getJoueur();
        joueur3 = inv3.getJoueur();
        joueur4 = inv4.getJoueur();
        joueur5 = inv5.getJoueur();

        List<Carte> etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUSCPR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        babylon = new Merveille(Wonder.BABYLON, EnumRessources.BOIS, etape, joueur1);

        inv1.modifMerveille(babylon);
        inv2.modifMerveille(babylon);
        inv3.modifMerveille(babylon);
        inv4.modifMerveille(babylon);
        inv5.modifMerveille(babylon);

        ArrayList<Inventaire> listeInventaire = new ArrayList<>();
        listeInventaire.add(inv1);
        listeInventaire.add(inv2);
        listeInventaire.add(inv3);

        ArrayList<Joueur> listeJoueur = new ArrayList<>();
        listeJoueur.add(joueur1);
        listeJoueur.add(joueur2);
        listeJoueur.add(joueur3);

        plateau = new Plateau(new ArrayList<>(){{add(inv1); add(inv2); add(inv3); add(inv4); add(inv5);}},
                new ArrayList<>(){{add(joueur1); add(joueur2); add(joueur3); add(joueur4); add(joueur5);}});
    }


    /**
     * Test de la méthode printInventaire()
     */
    @Test
    public void printInventaireTest() {
        for (Inventaire s : inv) {
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
        assertEquals(1 , inv1.compteFinalScore(plateau,false));
        assertEquals(1 , inv2.compteFinalScore(plateau,false));
        assertEquals(1, inv3.compteFinalScore(plateau, false));
        assertEquals(1, inv4.compteFinalScore(plateau, false));
        assertEquals(1, inv5.compteFinalScore(plateau, false));
    }


    /**
     * Test de la méthode compteBonus
     */
    @Test
    public void compteBonusTest() throws NegativeNumberException {
        inv1.increaseValue(EnumRessources.BONUS11J, 1);
        inv1.increaseValue(EnumRessources.BONUS11M, 1);
        inv1.increaseValue(EnumRessources.BONUS22G, 1);
        inv1.increaseValue(EnumRessources.BONUS31R, 1);
        inv1.increaseValue(EnumRessources.BONUS31MERVEILLE, 1);
        inv1.increaseValue(EnumRessources.VBONUS1M, 1);
        inv1.increaseValue(EnumRessources.VBONUS2G, 1);
        inv1.increaseValue(EnumRessources.VBONUS1B, 1);
        inv1.increaseValue(EnumRessources.VBONUS1J, 1);
        inv1.increaseValue(EnumRessources.VBONUS1R, 1);
        inv1.increaseValue(EnumRessources.VBONUS1V, 1);
        inv1.increaseValue(EnumRessources.VBONUSMGV, 1);
        inv1.increaseValue(EnumRessources.VBONUS1MERVEILLE, 1);
        inv1.increaseValue(EnumRessources.VBONUS7COMPLETMERVEILLE, 1);

        inv1.getMerveille().incrementeStade();
        inv1.getMerveille().incrementeStade();
        inv1.getMerveille().incrementeStade();

        // Le joueur remporte 19 point avec ses bonus de merveille réussi
        assertEquals(19, inv1.joueurName.getInv().compteBonus(plateau));
    }


    /**
     * Test de la méthode compteScientifique
     */
    @Test
    public void compteScientifiqueTest() throws NegativeNumberException {
        inv1.increaseValue(EnumRessources.ROUE, 1);
        inv1.increaseValue(EnumRessources.COMPAS, 1);
        inv1.increaseValue(EnumRessources.PDR, 1);

        // Le joueur remporte 10 pts scientifique car : 1 roue = 1 pts, 1 Compas = 1 pts, 1 Pierre de Rosette = 1 pts, et + 7 pts car il possède un groupe de 3
        assertEquals(10, inv1.compteScientifique());

        inv1.increaseValue(EnumRessources.BONUSCPR, 1);
        assertEquals(13, inv1.compteScientifique());

        inv1.increaseValue(EnumRessources.BONUSCPR, 1);
        assertEquals(18, inv1.compteScientifique());
    }


    /**
     * Test de la méthode bonusScientifique1()
     */
    @Test
    public void bonusScientifique1Test() {
        assertEquals(38, inv1.bonusScientifique1(10, 3, 2, 2, 1));
        assertEquals(38, inv1.bonusScientifique1(10, 2, 3, 2, 1));
        assertEquals(38, inv1.bonusScientifique1(10, 2, 2, 3, 1));
    }


    /**
     * Test de la méthode bonusScientifique2()
     */
    @Test
    public void bonusScientifique2Test() {
        assertEquals(48, inv1.bonusScientifique2(10, 3, 2, 2));
        assertEquals(48, inv1.bonusScientifique2(10, 2, 3, 2));
        assertEquals(48, inv1.bonusScientifique2(10, 2, 2, 3));
    }


    /**
     * Test de la méthode calculScientifique()
     */
    @Test
    public void calculScientifiqueTest() {
        // Le joueur remporte 10 pts scientifique car : 1 roue = 1 pts, 1 Compas = 1 pts, 1 Pierre de Rosette = 1 pts, et + 7 pts car il possède un groupe de 3
        assertEquals(10, inv1.calculScientifique(1, 1, 1));

        assertEquals(21, inv1.calculScientifique(2, 3, 1));
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

        inv1.listeCarte = listCarte;
        assertEquals("Chantier", inv1.getListeCarte().get(0).toString());
        assertEquals("Cavité", inv1.getListeCarte().get(1).toString());
        assertEquals("Jardins", inv1.getListeCarte().get(2).toString());
        assertEquals("Vignoble", inv1.getListeCarte().get(3).toString());
    }


    /**
     * Test le getter getJoueur() pour verifier si le joueur est bien affilie au bon inventaire
     */
    @Test
    public void getJoueurTest() {
        assertEquals(inv1.getJoueur(), joueur1);
        assertEquals(inv2.getJoueur(), joueur2);
        assertEquals(inv3.getJoueur(), joueur3);
        assertEquals(inv4.getJoueur(), joueur4);
        assertEquals(inv5.getJoueur(), joueur5);
    }


    /**
     * Test le getter getId() pour verifier si l identifant du joueur est bien affilie au bon inventaire
     */
    @Test
    public void getIdTest() {
        assertEquals(1, inv1.getId());
        assertEquals(2 , inv2.getId());
        assertEquals(3 , inv3.getId());
        assertEquals(4 , inv4.getId());
        assertEquals(5 , inv5.getId());
    }


    /**
     * On vérifie si l'inventaire est bien initialisé en regardant la valeur des arguments avec seulement 3 pieces
     */
    @Test
    public void getValueTest() {
        for (Inventaire s : inv) {
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
        for (Inventaire s : inv) {
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
        inv1.merveille = babylon;

        assertEquals(inv1.getMerveille(), babylon);
    }
}


