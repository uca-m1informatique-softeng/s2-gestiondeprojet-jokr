package sw_aventure.objetjeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import metier.EnumCarte;
import metier.Wonder;
import sw_aventure.joueur.*;
import metier.EnumRessources;

public class GenererMerveille {

    private ArrayList<Carte> etape;


    /**
     * retourne la merveille crée à partir du nom de la merveille et du joueur
     * @param merveille le nom de la Merveille
     * @param joueur le joueur
     * @return la Merveille
     */
    public Merveille getMerveille(Wonder merveille, Joueur joueur){
        switch (merveille) {
            case BABYLON:
                return fabriqueB(joueur);
            case OLYMPIA:
                return fabriqueO(joueur);
            case GIZAH:
                return fabriqueG(joueur);
            case RHODOS:
                return fabriqueR(joueur);
            case ALEXANDRIA:
                return fabriqueA(joueur);
            case HALIKARNASSOS:
                return fabriqueH(joueur);
            case EPHESOS:
                return fabriqueE(joueur);
            //////////////// DE NUIT ///////////////////
            case BABYLONNUIT:
                return fabriqueBbis(joueur);
            case OLYMPIANUIT:
                return fabriqueObis(joueur);
            case GIZAHNUIT:
                return fabriqueGbis(joueur);
            case RHODOSNUIT:
                return fabriqueRbis(joueur);
            case ALEXANDRIANUIT:
                return fabriqueAbis(joueur);
            case HALIKARNASSOSNUIT:
                return fabriqueHbis(joueur);
            case EPHESOSNUIT:
                return fabriqueEbis(joueur);
            default:
                return null;
        }

    }

    /**
     * fabrique la merveille BABYLON pour le Joueur
     * @param joueur le joueur
     * @return Merveille Babylon
     */

    private Merveille fabriqueB(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUSCPR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.BABYLON, EnumRessources.BOIS, etape, joueur);
    }

    /**
     * fabrique la merveille OLYMPIA pour le Joueur
     * @param joueur le joueur
     * @return Merveille olympia
     */

    private Merveille fabriqueO(Joueur joueur) {
        etape = new ArrayList<>();


        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Collections.singletonList(EnumRessources.BONUSAGECOULEUR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.OLYMPIA, EnumRessources.ARGILE, etape, joueur);
    }

    /**
     * fabrique la merveille GIZAH pour le Joueur
     * @param joueur le joueur
     * @return Merveille Gizah
     */

    private Merveille fabriqueG(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE,EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.GIZAH, EnumRessources.PIERRE, etape, joueur);
    }

    /**
     * fabrique la merveille RHODOS pour le Joueur
     * @param joueur le joueur
     * @return Merveille Rhodos
     */

    private Merveille fabriqueR(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.RHODOS, EnumRessources.MINERAI, etape, joueur);
    }

    /**
     * fabrique la merveille ALEXENDRIA pour le Joueur
     * @param joueur le joueur
     * @return Merveille Alexendria
     */

    private Merveille fabriqueA(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI), Collections.singletonList(EnumRessources.MULTIBPAM)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PAPYRUS,EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.ALEXANDRIA,EnumRessources.VERRE, etape,joueur);
    }

    /**
     * fabrique la merveille HALIKARNASSOS pour le Joueur
     * @param joueur le joueur
     * @return Merveille Halikarnassos
     */

    private Merveille fabriqueH(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.HALIKARNASSOS,EnumRessources.TISSU, etape, joueur);
    }

    /**
     * fabrique la merveille EPHESOS pour le Joueur
     * @param joueur le joueur
     * @return Merveille Ephesos
     */

    private Merveille fabriqueE(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI,EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.EPHESOS,EnumRessources.PAPYRUS, etape,joueur);
    }


    ////////////////////////////////          DE   NUIT          //////////////////////////////////////////////////////////

    /**
     * fabrique la merveille BABYLON de nuit pour le Joueur
     * @param joueur le joueur
     * @return Merveille Babylon de nuit
     */

    private Merveille fabriqueBbis(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE), Collections.singletonList(EnumRessources.BONUS7CARTEMAIN)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.VERRE), Collections.singletonList(EnumRessources.BONUSCPR)));

        return new Merveille(Wonder.BABYLONNUIT,EnumRessources.BOIS, etape, joueur);
    }

    /**
     * fabrique la merveille OLYMPIA de nuit pour le Joueur
     * @param joueur le joueur
     * @return Merveille Olympia de nuit
     */

    private Merveille fabriqueObis(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUSCARTEAGEG2P)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Collections.singletonList(EnumRessources.BONUSCARTEAGEG3P)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS,EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.OLYMPIANUIT,EnumRessources.ARGILE, etape, joueur);
    }

    /**
     * fabrique la merveille GIZAH de nuit pour le Joueur
     * @param joueur le joueur
     * @return Merveille Gizah de nuit
     */

    private Merveille fabriqueGbis(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PAPYRUS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return  new Merveille(Wonder.GIZAHNUIT,EnumRessources.PIERRE, etape, joueur);
    }

    /**
     * fabrique la merveille RHODOS de nuit pour le Joueur
     * @param joueur le joueur
     * @return Merveille Rhodos de nuit
     */

    private Merveille fabriqueRbis(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.RHODOSNUIT, EnumRessources.MINERAI, etape, joueur);
    }

    /**
     * fabrique la merveille ALEXENDRIA de nuit pour le Joueur
     * @param joueur le joueur
     * @return Merveille Alexendria de nuit
     */

    private Merveille fabriqueAbis(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Collections.singletonList(EnumRessources.MULTIBPAM)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.MULTIVPT)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.ALEXANDRIANUIT, EnumRessources.VERRE, etape,joueur);
    }

    /**
     * fabrique la merveille HALIKARNASSOS de nuit pour le Joueur
     * @param joueur le joueur
     * @return Merveille Halikarnassos de nuit
     */

    private Merveille fabriqueHbis(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG2)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG1)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG)));

        return new Merveille(Wonder.HALIKARNASSOSNUIT,EnumRessources.TISSU, etape, joueur);
    }

    /**
     * fabrique la merveille EPHESOS de nuit pour le Joueur
     * @param joueur le joueur
     * @return Merveille Ephesos de nuit
     */

    private Merveille fabriqueEbis(Joueur joueur) {
        etape = new ArrayList<>();

        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI,EnumRessources.VERRE), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));

        return new Merveille(Wonder.EPHESOSNUIT,EnumRessources.PAPYRUS, etape,joueur);
    }
}


