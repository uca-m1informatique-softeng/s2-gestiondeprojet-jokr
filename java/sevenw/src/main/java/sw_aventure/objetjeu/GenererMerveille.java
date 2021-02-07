package sw_aventure.objetjeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import objet_commun.Carte;
import objet_commun.Merveille;
import metier.EnumCarte;
import metier.Wonder;
import metier.EnumRessources;

public class GenererMerveille {

    private ArrayList<Carte> etape;


    /**
     * retourne la merveille crée à partir du nom de la merveille et du 
     * @param merveille le nom de la Merveille
     * @return la Merveille
     */
    public Merveille getMerveille(Wonder merveille){
        switch (merveille) {
            case BABYLON:
                return fabriqueB();
            case OLYMPIA:
                return fabriqueO();
            case GIZAH:
                return fabriqueG();
            case RHODOS:
                return fabriqueR();
            case ALEXANDRIA:
                return fabriqueA();
            case HALIKARNASSOS:
                return fabriqueH();
            case EPHESOS:
                return fabriqueE();
            //////////////// DE NUIT ///////////////////
            case BABYLONNUIT:
                return fabriqueBbis();
            case OLYMPIANUIT:
                return fabriqueObis();
            case GIZAHNUIT:
                return fabriqueGbis();
            case RHODOSNUIT:
                return fabriqueRbis();
            case ALEXANDRIANUIT:
                return fabriqueAbis();
            case HALIKARNASSOSNUIT:
                return fabriqueHbis();
            case EPHESOSNUIT:
                return fabriqueEbis();
            default:
                return null;
        }

    }

    /**
     * fabrique la merveille BABYLON 
     * @return Merveille Babylon
     */

    private Merveille fabriqueB() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUSCPR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.BABYLON, EnumRessources.BOIS, etape);
    }

    /**
     * fabrique la merveille OLYMPIA      
     * @return Merveille olympia
     */

    private Merveille fabriqueO() {
        etape = new ArrayList<>();


        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Collections.singletonList(EnumRessources.BONUSAGECOULEUR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.OLYMPIA, EnumRessources.ARGILE, etape);
    }

    /**
     * fabrique la merveille GIZAH      
     * @return Merveille Gizah
     */

    private Merveille fabriqueG() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE,EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.GIZAH, EnumRessources.PIERRE, etape);
    }

    /**
     * fabrique la merveille RHODOS      
     * @return Merveille Rhodos
     */

    private Merveille fabriqueR() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.RHODOS, EnumRessources.MINERAI, etape);
    }

    /**
     * fabrique la merveille ALEXENDRIA      
     * @return Merveille Alexendria
     */

    private Merveille fabriqueA() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI), Collections.singletonList(EnumRessources.MULTIBPAM)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PAPYRUS,EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.ALEXANDRIA,EnumRessources.VERRE, etape);
    }

    /**
     * fabrique la merveille HALIKARNASSOS      
     * @return Merveille Halikarnassos
     */

    private Merveille fabriqueH() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.HALIKARNASSOS,EnumRessources.TISSU, etape);
    }

    /**
     * fabrique la merveille EPHESOS      
     * @return Merveille Ephesos
     */

    private Merveille fabriqueE() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI,EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.EPHESOS,EnumRessources.PAPYRUS, etape);
    }


    ////////////////////////////////          DE   NUIT          //////////////////////////////////////////////////////////

    /**
     * fabrique la merveille BABYLON de nuit      
     * @return Merveille Babylon de nuit
     */

    private Merveille fabriqueBbis() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE), Collections.singletonList(EnumRessources.BONUS7CARTEMAIN)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.VERRE), Collections.singletonList(EnumRessources.BONUSCPR)));

        return new Merveille(Wonder.BABYLONNUIT,EnumRessources.BOIS, etape);
    }

    /**
     * fabrique la merveille OLYMPIA de nuit      
     * @return Merveille Olympia de nuit
     */

    private Merveille fabriqueObis() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUSCARTEAGEG2P)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Collections.singletonList(EnumRessources.BONUSCARTEAGEG3P)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS,EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.OLYMPIANUIT,EnumRessources.ARGILE, etape);
    }

    /**
     * fabrique la merveille GIZAH de nuit      
     * @return Merveille Gizah de nuit
     */

    private Merveille fabriqueGbis() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PAPYRUS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return  new Merveille(Wonder.GIZAHNUIT,EnumRessources.PIERRE, etape);
    }

    /**
     * fabrique la merveille RHODOS de nuit      
     * @return Merveille Rhodos de nuit
     */

    private Merveille fabriqueRbis() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.RHODOSNUIT, EnumRessources.MINERAI, etape);
    }

    /**
     * fabrique la merveille ALEXENDRIA de nuit      
     * @return Merveille Alexendria de nuit
     */

    private Merveille fabriqueAbis() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Collections.singletonList(EnumRessources.MULTIBPAM)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.MULTIVPT)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.ALEXANDRIANUIT, EnumRessources.VERRE, etape);
    }

    /**
     * fabrique la merveille HALIKARNASSOS de nuit      
     * @return Merveille Halikarnassos de nuit
     */

    private Merveille fabriqueHbis() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG2)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG1)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG)));

        return new Merveille(Wonder.HALIKARNASSOSNUIT,EnumRessources.TISSU, etape);
    }

    /**
     * fabrique la merveille EPHESOS de nuit      
     * @return Merveille Ephesos de nuit
     */

    private Merveille fabriqueEbis() {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI,EnumRessources.VERRE), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.EPHESOSNUIT,EnumRessources.PAPYRUS, etape);
    }
}


