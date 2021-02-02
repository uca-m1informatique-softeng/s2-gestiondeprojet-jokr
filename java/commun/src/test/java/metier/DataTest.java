package metier;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


public class DataTest {

    private Map<String, Integer> map1, map2, map3;
    private Data data1, data2, data3, data4;


    /**
     * Preparation des tests de la classe Data
     */
    @Before
    public void setup() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        map3 = new HashMap<>();

        map1.put(EnumRessources.SCORE.toString(), 1);
        map2.put(EnumRessources.SCORE.toString(), 2);
        map3.put(EnumRessources.SCORE.toString(), 3);

        List<String> listeCarte = new ArrayList<>();
        listeCarte.add(EnumCarte.M3.toString());
        listeCarte.add(EnumCarte.M5.toString());
        listeCarte.add(EnumCarte.M6.toString());

        data1 = new Data("Philippe Renevier", "Ultime", map1,"BABYLON", listeCarte);
        data2 = new Data("j2", "logique", map2,"OLYMPIA", listeCarte);
        data3 = new Data("j3", "random", map3,"RHODOS", listeCarte);
        data4 = new Data();
    }


    /**
     * Test du getter getNomJoueur()
     */
    @Test
    public void getNomJoueurTest() {
        assertEquals("Philippe Renevier", data1.getNomJoueur());
        assertEquals("j2", data2.getNomJoueur());
        assertEquals("j3", data3.getNomJoueur());
    }


    /**
     * Test du setter setNomJoueur()
     */
    @Test
    public void setNomJoueurTest() {
        data1.setNomJoueur("Joueur1Bis");
        data2.setNomJoueur("Joueur2Bis");
        data3.setNomJoueur("Joueur3Bis");
        data4.setNomJoueur("Joueur4Bis");

        assertEquals("Joueur1Bis", data1.getNomJoueur());
        assertEquals("Joueur2Bis", data2.getNomJoueur());
        assertEquals("Joueur3Bis", data3.getNomJoueur());
        assertEquals("Joueur4Bis", data4.getNomJoueur());
    }


    /**
     * Test du getter getStrategie()
     */
    @Test
    public void getStrategieTest() {
        assertEquals("Ultime", data1.getStrategie());
        assertEquals("logique", data2.getStrategie());
        assertEquals("random", data3.getStrategie());
    }


    /**
     * Test du setter setStrategie()
     */
    @Test
    public void setStrategieTest() {
        data1.setStrategie("randomBis");
        data2.setStrategie("randomBis2");
        data3.setStrategie("randomBis3");

        assertEquals("randomBis", data1.getStrategie());
        assertEquals("randomBis2", data2.getStrategie());
        assertEquals("randomBis3", data3.getStrategie());
    }


    /**
     * Test du getter getSac()
     */
    @Test
    public void getSacTest() {
        assertEquals(data1.getSac(), map1);
        assertEquals(data2.getSac(), map2);
        assertEquals(data3.getSac(), map3);
    }


    /**
     * Test du setter setSac()
     */
    @Test
    public void setSacTest() {
        Map<String, Integer> mapBis1 = new HashMap<>();
        Map<String, Integer> mapBis2 = new HashMap<>();
        Map<String, Integer> mapBis3 = new HashMap<>();

        mapBis1.put(EnumRessources.PIECE.toString(), 3);
        mapBis2.put(EnumRessources.PIECE.toString(), 5);
        mapBis3.put(EnumRessources.PIECE.toString(), 7);

        data1.setSac(mapBis1);
        data2.setSac(mapBis2);
        data3.setSac(mapBis3);

        assertEquals(3, data1.getSac().get(EnumRessources.PIECE.toString()));
        assertEquals(5, data2.getSac().get(EnumRessources.PIECE.toString()));
        assertEquals(7, data3.getSac().get(EnumRessources.PIECE.toString()));
    }


    /**
     * Test de la méthode getValue()
     */
    @Test
    public void getValue() {
        assertEquals(1, data1.getValue(EnumRessources.SCORE.toString()));
        assertEquals(2, data2.getValue(EnumRessources.SCORE.toString()));
        assertEquals(3, data3.getValue(EnumRessources.SCORE.toString()));
    }


    /**
     * Test du getter getMerveille()
     */
    @Test
    public void getMerveilleTest() {
        assertEquals("BABYLON", data1.getMerveille());
        assertEquals("OLYMPIA", data2.getMerveille());
        assertEquals("RHODOS", data3.getMerveille());
    }


    /**
     * Test du setter setMerveille()
     */
    @Test
    public void setMerveilleTest() {
        data1.setMerveille("GIZAH");
        data2.setMerveille("ALEXANDRIA");
        data3.setMerveille("HALIKARNASSOS");

        assertEquals("GIZAH", data1.getMerveille());
        assertEquals("ALEXANDRIA", data2.getMerveille());
        assertEquals("HALIKARNASSOS", data3.getMerveille());
    }


    /**
     * Test du getter getCartes()
     */
    @Test
    public void getCartesTest() {
        assertEquals("Filon", data1.getCartes().get(0).toString());
        assertEquals("Cavité", data1.getCartes().get(1).toString());
        assertEquals("Chantier", data1.getCartes().get(2).toString());
    }


    /**
     * Test du setter setCartes()
     */
    @Test
    public void setCartesTest() {
        List<String> listeCarte = new ArrayList<>();
        listeCarte.add(EnumCarte.P1.toString());
        listeCarte.add(EnumCarte.P2.toString());

        data1.setCartes(listeCarte);

        assertEquals("Guilde des Travailleurs", data1.getCartes().get(0));
        assertEquals("Guilde des Artisans", data1.getCartes().get(1));
    }


    /**
     * Test de la méthode toJSON()
     */
    @Test
    public void toJSONTest() throws JSONException {
        JSONObject json1 = data1.toJSON();
        JSONObject json2 = data2.toJSON();
        JSONObject json3 = data3.toJSON();

        System.out.println(json1);

        assertEquals("{\"cartes\":[\"Filon\",\"Cavité\",\"Chantier\"],\"nomJoueur\":\"Philippe Renevier\",\"sac\":{\"Score\":1},\"strategie\":\"Ultime\",\"merveille\":\"BABYLON\"}", json1.toString());
        assertEquals("{\"cartes\":[\"Filon\",\"Cavité\",\"Chantier\"],\"nomJoueur\":\"j2\",\"sac\":{\"Score\":2},\"strategie\":\"logique\",\"merveille\":\"OLYMPIA\"}", json2.toString());
        assertEquals("{\"cartes\":[\"Filon\",\"Cavité\",\"Chantier\"],\"nomJoueur\":\"j3\",\"sac\":{\"Score\":3},\"strategie\":\"random\",\"merveille\":\"RHODOS\"}", json3.toString());

    }
}
