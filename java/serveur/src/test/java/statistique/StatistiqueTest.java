package java.statistique;

import fichier.GestionnaireDeFichier;
import metier.Data;
import metier.EnumCarte;
import metier.EnumRessources;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import statistique.Statistique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class StatistiqueTest {


    private Statistique statistique;


    /**
     * Preparation pour les tests de la classe Statistique
     */
    @Before
    public void setup() {
        Map<String, Integer> map = new HashMap<>();
        map.put(EnumRessources.VICTOIRETOTAL.toString(), 2);
        map.put(EnumRessources.SCOREFINAL.toString(), 12);
        map.put(EnumRessources.SCOREPIECE.toString() , 10);
        map.put(EnumRessources.SCORE.toString(), 16);
        map.put(EnumRessources.BONUSSCIENTIFIQUE.toString(), 7);
        map.put(EnumRessources.VICTOIRE.toString(), 3);
        map.put(EnumRessources.DEFAITE.toString(), 1);
        map.put(EnumRessources.BONUSCARTE.toString(), 2);
        map.put(EnumRessources.STADE.toString(), 3);

        List<String> listeCarte = new ArrayList<>();
        listeCarte.add(EnumCarte.M1.toString());
        listeCarte.add(EnumCarte.M2.toString());

        Data data = new Data("Joueur1", "random", map, "BABYLON", listeCarte);

        Data[] tabData = new Data[1];
        tabData[0] = data;

        List<Data[]> listeData = new ArrayList<>();
        listeData.add(tabData);
        listeData.add(tabData);


        statistique = new Statistique(2, 1, listeData);
    }


    /**
     * Test de la methode caculStat()
     */
    @Test
    public void calculStatTest() {
        statistique.calculStat();

        double[][] tab = statistique.getTabStat();

        // On multiplie tout par 2 car il y a deux parties, donc le tableau se multiplie par 2
        assertEquals(1, tab.length);
        assertEquals(4, (int) tab[0][0]);
        assertEquals(24, (int) tab[0][1]);
        assertEquals(32, (int) tab[0][2]);
        assertEquals(14, (int) tab[0][3]);
        assertEquals(6, (int) tab[0][4]);
        assertEquals(2, (int) tab[0][5]);
        assertEquals(12 , (int) tab[0][6]);
        assertEquals(20 , (int) tab[0][7]);
        assertEquals(4 , (int) tab[0][8]);
        assertEquals(6, (int) tab[0][9]);
    }


    /**
     * Test de la méthode recupeStat()
     */
    @Test
    public void recupeStatTest() {
        statistique.calculStat();
        StringBuilder resultat = statistique.recupeStat(false);
        assertNotNull(resultat);
        // On récupère tout le détail des statistiques, ce qui fait au minimum plus de 100 caractère...
        assertTrue(resultat.length() > 100);
    }


    /**
     * Test de la methode afficheStat()
     */
    @Test
    public void afficheStatTest() throws IOException {
        GestionnaireDeFichier gestionnaireDeFichier;
        gestionnaireDeFichier = Mockito.mock(GestionnaireDeFichier.class);

        List<Data[]> data = new ArrayList<>();
        Statistique st = new Statistique(10, 0, data);

        st.afficheStat(gestionnaireDeFichier);
        Mockito.verify(gestionnaireDeFichier).ecrireDansFichier(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    /**
     * Test de la méthode descriptifIA()
     */
    @Test
    public void descriptifIATest() {

        StringBuilder descriptifIA = statistique.descriptifIA(false);

        String stringBuilderAttendu = "Description des IA :\n" +
                "   - IA Random       : IA prenant des décisions aléatoires.\n" +
                "   - IA Merveille    : IA construisant sa merveille en priorité.\n" +
                "   - IA Scientifique : IA se concentrant sur les points scientifiques.\n" +
                "   - IA Civil        : IA construisant des bâtiments civils en priorité.\n" +
                "   - IA Monétaire    : IA essayant de gagner des points grâce à ses pièces et des bonus.\n" +
                "   - IA Composite    : IA adoptant une stratégie différente en fonction de sa merveille.\n" +
                "   - IA Militaire    : IA se concentrant sur l'achat de bouclier pour gagner les guerres.\n" +
                "   - IA Ambitieuse   : IA s’adaptant aux stratégies des autres joueurs et en jouant des coups pour les contrer, toute en s’assurant un minimum de points.\n\n";
        assertEquals(stringBuilderAttendu, descriptifIA.toString());
    }


    /**
     * Test de la méthode listeCarte()
     */
    @Test
    public void listeCarteTest() {
        Map<String, Integer> map = statistique.listeCarte();

        assertEquals(0, map.get(EnumCarte.M1.toString()));
        assertEquals(0, map.get(EnumCarte.M2.toString()));
        assertEquals(0, map.get(EnumCarte.M3.toString()));
        assertEquals(0, map.get(EnumCarte.M4.toString()));
        assertEquals(0, map.get(EnumCarte.M5.toString()));
        assertEquals(0, map.get(EnumCarte.M6.toString()));
        assertEquals(0, map.get(EnumCarte.M7.toString()));
        assertEquals(0, map.get(EnumCarte.M8.toString()));
        assertEquals(0, map.get(EnumCarte.M9.toString()));
        assertEquals(0, map.get(EnumCarte.M10.toString()));
        assertEquals(0, map.get(EnumCarte.M11.toString()));
        assertEquals(0, map.get(EnumCarte.M12.toString()));
        assertEquals(0, map.get(EnumCarte.M13.toString()));
        assertEquals(0, map.get(EnumCarte.M14.toString()));

        assertEquals(0, map.get(EnumCarte.G1.toString()));
        assertEquals(0, map.get(EnumCarte.G2.toString()));
        assertEquals(0, map.get(EnumCarte.G3.toString()));

        assertEquals(0, map.get(EnumCarte.B1.toString()));
        assertEquals(0, map.get(EnumCarte.B2.toString()));
        assertEquals(0, map.get(EnumCarte.B3.toString()));
        assertEquals(0, map.get(EnumCarte.B4.toString()));
        assertEquals(0, map.get(EnumCarte.B5.toString()));
        assertEquals(0, map.get(EnumCarte.B6.toString()));
        assertEquals(0, map.get(EnumCarte.B7.toString()));
        assertEquals(0, map.get(EnumCarte.B8.toString()));
        assertEquals(0, map.get(EnumCarte.B9.toString()));
        assertEquals(0, map.get(EnumCarte.B10.toString()));
        assertEquals(0, map.get(EnumCarte.B11.toString()));
        assertEquals(0, map.get(EnumCarte.B12.toString()));
        assertEquals(0, map.get(EnumCarte.B13.toString()));

        assertEquals(0, map.get(EnumCarte.J1.toString()));
        assertEquals(0, map.get(EnumCarte.J2.toString()));
        assertEquals(0, map.get(EnumCarte.J3.toString()));
        assertEquals(0, map.get(EnumCarte.J4.toString()));
        assertEquals(0, map.get(EnumCarte.J5.toString()));
        assertEquals(0, map.get(EnumCarte.J6.toString()));
        assertEquals(0, map.get(EnumCarte.J7.toString()));
        assertEquals(0, map.get(EnumCarte.J8.toString()));
        assertEquals(0, map.get(EnumCarte.J9.toString()));
        assertEquals(0, map.get(EnumCarte.J10.toString()));
        assertEquals(0, map.get(EnumCarte.J11.toString()));
        assertEquals(0, map.get(EnumCarte.J12.toString()));
        assertEquals(0, map.get(EnumCarte.J13.toString()));

        assertEquals(0, map.get(EnumCarte.R1.toString()));
        assertEquals(0, map.get(EnumCarte.R2.toString()));
        assertEquals(0, map.get(EnumCarte.R3.toString()));
        assertEquals(0, map.get(EnumCarte.R4.toString()));
        assertEquals(0, map.get(EnumCarte.R5.toString()));
        assertEquals(0, map.get(EnumCarte.R6.toString()));
        assertEquals(0, map.get(EnumCarte.R7.toString()));
        assertEquals(0, map.get(EnumCarte.R8.toString()));
        assertEquals(0, map.get(EnumCarte.R9.toString()));
        assertEquals(0, map.get(EnumCarte.R10.toString()));
        assertEquals(0, map.get(EnumCarte.R11.toString()));
        assertEquals(0, map.get(EnumCarte.R12.toString()));

        assertEquals(0, map.get(EnumCarte.V1.toString()));
        assertEquals(0, map.get(EnumCarte.V2.toString()));
        assertEquals(0, map.get(EnumCarte.V3.toString()));
        assertEquals(0, map.get(EnumCarte.V4.toString()));
        assertEquals(0, map.get(EnumCarte.V5.toString()));
        assertEquals(0, map.get(EnumCarte.V6.toString()));
        assertEquals(0, map.get(EnumCarte.V7.toString()));
        assertEquals(0, map.get(EnumCarte.V8.toString()));
        assertEquals(0, map.get(EnumCarte.V9.toString()));
        assertEquals(0, map.get(EnumCarte.V10.toString()));
        assertEquals(0, map.get(EnumCarte.V11.toString()));
        assertEquals(0, map.get(EnumCarte.V12.toString()));

        assertEquals(0, map.get(EnumCarte.P1.toString()));
        assertEquals(0, map.get(EnumCarte.P2.toString()));
        assertEquals(0, map.get(EnumCarte.P3.toString()));
        assertEquals(0, map.get(EnumCarte.P4.toString()));
        assertEquals(0, map.get(EnumCarte.P5.toString()));
        assertEquals(0, map.get(EnumCarte.P6.toString()));
        assertEquals(0, map.get(EnumCarte.P7.toString()));
        assertEquals(0, map.get(EnumCarte.P8.toString()));
        assertEquals(0, map.get(EnumCarte.P9.toString()));
        assertEquals(0, map.get(EnumCarte.P10.toString()));
    }


    /**
     * Test de la méthode printInventaire()
     */
    @Test
    public void printInventaireTest() {
        Map<String, Integer> map = statistique.listeCarte();

        List<String> inv = statistique.printInventaireCarte(map);

        // Il doit y avoir 77 element dans la liste ce qui correspond a 77 carte
        assertEquals(77, inv.size());
    }
}