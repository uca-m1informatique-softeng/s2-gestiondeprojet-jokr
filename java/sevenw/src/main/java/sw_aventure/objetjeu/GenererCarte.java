package sw_aventure.objetjeu;

import objet_commun.Carte;
import metier.EnumCarte;
import metier.EnumRessources;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GenererCarte {


    private ArrayList<Carte> cards;
    /**
     * Constructeur de GenererCarte
     * @param age  N°de l'âge
     * @param nbJoueur Nombre de joueurs
     */
    public GenererCarte(int age, int nbJoueur) {
        if (age == 1) fabriqueAge1(nbJoueur);
        else if (age == 2) fabriqueAge2(nbJoueur);
        else fabriqueAge3(nbJoueur);
    }

    /**
     * @return Getter de la liste de cartes
     */
    public List<Carte> getCards() {
        return cards;
    }

    /**
     * Fabrique les Cartes De l'Age 1 en fonction du nombre de joueur entré en paramètre 3-7
     * @param nbJoueur Nombre de joueurs
     */

    private void fabriqueAge1(int nbJoueur) {
        cards = new ArrayList<>();
        // Carte Marron
        cards.add(new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON));
        cards.add(new Carte(EnumCarte.M5, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PIERRE), 3, 1, EnumRessources.MARRON));
        cards.add(new Carte(EnumCarte.M4, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.ARGILE), 3, 1, EnumRessources.MARRON));
        cards.add(new Carte(EnumCarte.M3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.MINERAI), 3, 1, EnumRessources.MARRON));
        cards.add(new Carte(EnumCarte.M2, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIAM), 3, 1, EnumRessources.MARRON));
        cards.add(new Carte(EnumCarte.M1, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIBP), 3, 1, EnumRessources.MARRON));

        // Carte Grise

        cards.add(new Carte(EnumCarte.G3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.TISSU), 3, 1, EnumRessources.GRISE));
        cards.add(new Carte(EnumCarte.G2, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.VERRE), 3, 1, EnumRessources.GRISE));
        cards.add(new Carte(EnumCarte.G1, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PAPYRUS), 3, 1, EnumRessources.GRISE));

        // Carte Bleue
        cards.add(new Carte(EnumCarte.B3, Collections.singletonList(EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 1, EnumRessources.BLEUE));
        cards.add(new Carte(EnumCarte.B2, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 1, EnumRessources.BLEUE));
        cards.add(new Carte(EnumCarte.B1, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 1, EnumRessources.BLEUE));

        // Carte Jaune
        cards.add(new Carte(EnumCarte.J3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.REDMARRONDROITE), 3, 1, EnumRessources.JAUNE));
        cards.add(new Carte(EnumCarte.J2, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.REDMARRONGAUCHE), 3, 1, EnumRessources.JAUNE));
        cards.add(new Carte(EnumCarte.J1, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.REDGRISDROITEGAUCHE), 3, 1, EnumRessources.JAUNE));

        // Carte Rouge
        cards.add(new Carte(EnumCarte.R3, Collections.singletonList(EnumRessources.PIERRE), Collections.singletonList(EnumRessources.BOUCLIER), 3, 1, EnumRessources.ROUGE));
        cards.add(new Carte(EnumCarte.R2, Collections.singletonList(EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BOUCLIER), 3, 1, EnumRessources.ROUGE));
        cards.add(new Carte(EnumCarte.R1, Collections.singletonList(EnumRessources.ARGILE), Collections.singletonList(EnumRessources.BOUCLIER), 3, 1, EnumRessources.ROUGE));

        // Carte Verte
        cards.add(new Carte(EnumCarte.V3, Collections.singletonList(EnumRessources.TISSU), Collections.singletonList(EnumRessources.COMPAS), 3, 1, EnumRessources.VERTE));
        cards.add(new Carte(EnumCarte.V2, Collections.singletonList(EnumRessources.VERRE), Collections.singletonList(EnumRessources.ROUE), 3, 1, EnumRessources.VERTE));
        cards.add(new Carte(EnumCarte.V1, Collections.singletonList(EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.PDR), 3, 1, EnumRessources.VERTE));

        if (nbJoueur >= 4) {
            // Carte Marron
            cards.add(new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 4, 1, EnumRessources.MARRON));
            cards.add(new Carte(EnumCarte.M3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.MINERAI), 4, 1, EnumRessources.MARRON));
            cards.add(new Carte(EnumCarte.M7, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIPA), 4, 1, EnumRessources.MARRON));

            // Carte Bleue
            cards.add(new Carte(EnumCarte.B13, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 4, 1, EnumRessources.BLEUE));

            // Carte Jaune
            cards.add(new Carte(EnumCarte.J4, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE), 4, 1, EnumRessources.JAUNE));

            // Carte Rouge
            cards.add(new Carte(EnumCarte.R1, Collections.singletonList(EnumRessources.ARGILE), Collections.singletonList(EnumRessources.BOUCLIER), 4, 1, EnumRessources.ROUGE));

            // Carte Verte
            cards.add(new Carte(EnumCarte.V1, Collections.singletonList(EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.PDR), 4, 1, EnumRessources.VERTE));
        }
        if (nbJoueur >= 5) {
            // Carte Marron
            cards.add(new Carte(EnumCarte.M5, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PIERRE), 5, 1, EnumRessources.MARRON));
            cards.add(new Carte(EnumCarte.M4, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.ARGILE), 5, 1, EnumRessources.MARRON));
            cards.add(new Carte(EnumCarte.M8, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIBM), 5, 1, EnumRessources.MARRON));

            // Carte Bleue
            cards.add(new Carte(EnumCarte.B2, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 5, 1, EnumRessources.BLEUE));

            // Carte Jaune
            cards.add(new Carte(EnumCarte.J4, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE), 5, 1, EnumRessources.JAUNE));

            // Carte Rouge
            cards.add(new Carte(EnumCarte.R2, Collections.singletonList(EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BOUCLIER), 5, 1, EnumRessources.ROUGE));

            // Carte Verte
            cards.add(new Carte(EnumCarte.V3, Collections.singletonList(EnumRessources.TISSU), Collections.singletonList(EnumRessources.COMPAS), 5, 1, EnumRessources.VERTE));
        }
        if (nbJoueur >= 6) {
            // Carte Marron
            cards.add(new Carte(EnumCarte.M10, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIBA), 6, 1, EnumRessources.MARRON));
            cards.add(new Carte(EnumCarte.M9, Collections.singletonList(EnumRessources.PIECE), Collections.singletonList(EnumRessources.MULTIPM), 6, 1, EnumRessources.MARRON));

            // Carte Grise
            cards.add(new Carte(EnumCarte.G3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.TISSU), 6, 1, EnumRessources.GRISE));
            cards.add(new Carte(EnumCarte.G2, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.VERRE), 6, 1, EnumRessources.GRISE));
            cards.add(new Carte(EnumCarte.G1, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PAPYRUS), 6, 1, EnumRessources.GRISE));

            // Carte Bleue
            cards.add(new Carte(EnumCarte.B1, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 6, 1, EnumRessources.BLEUE));

            // Carte Jaune
            cards.add(new Carte(EnumCarte.J1, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.REDGRISDROITEGAUCHE), 6, 1, EnumRessources.JAUNE));
        }
        if (nbJoueur >= 7) {
            // Carte Bleue
            cards.add(new Carte(EnumCarte.B13, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 7, 1, EnumRessources.BLEUE));
            cards.add(new Carte(EnumCarte.B3, Collections.singletonList(EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 7, 1, EnumRessources.BLEUE));

            // Carte Jaune
            cards.add(new Carte(EnumCarte.J4, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE), 7, 1, EnumRessources.JAUNE));
            cards.add(new Carte(EnumCarte.J3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.REDMARRONDROITE), 7, 1, EnumRessources.JAUNE));
            cards.add(new Carte(EnumCarte.J2, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.REDMARRONGAUCHE), 7, 1, EnumRessources.JAUNE));

            // Carte Rouge
            cards.add(new Carte(EnumCarte.R3, Collections.singletonList(EnumRessources.BOIS), Collections.singletonList(EnumRessources.BOUCLIER), 7, 1, EnumRessources.ROUGE));

            // Carte Verte
            cards.add(new Carte(EnumCarte.V2, Collections.singletonList(EnumRessources.VERRE), Collections.singletonList(EnumRessources.ROUE), 7, 1, EnumRessources.VERTE));
        }
    }


    /**
     * Fabrique les Cartes De l'Age 2 en fonction du nombre de joueur entré en paramètre 3-7
     * @param nbJoueur Nombre de joueurs
     */

    private void fabriqueAge2(int nbJoueur) {
        cards = new ArrayList<>();

        // CARTE MARRON

        cards.add(new Carte(EnumCarte.M14, Collections.singletonList(EnumRessources.PIECE), Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), 3, 2, EnumRessources.MARRON));
        cards.add(new Carte(EnumCarte.M13, Collections.singletonList(EnumRessources.PIECE), Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE), 3, 2, EnumRessources.MARRON));
        cards.add(new Carte(EnumCarte.M12, Collections.singletonList(EnumRessources.PIECE), Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), 3, 2, EnumRessources.MARRON));
        cards.add(new Carte(EnumCarte.M11, Collections.singletonList(EnumRessources.PIECE), Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI), 3, 2, EnumRessources.MARRON));

        // CARTE GRISE

        cards.add(new Carte(EnumCarte.G2, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.VERRE), 3, 2, EnumRessources.GRISE));
        cards.add(new Carte(EnumCarte.G1, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PAPYRUS), 3, 2, EnumRessources.GRISE));
        cards.add(new Carte(EnumCarte.G3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.TISSU), 3, 2, EnumRessources.GRISE));

        // CARTE BLEUE

        cards.add(new Carte(EnumCarte.B7, Arrays.asList(EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 2, EnumRessources.BLEUE));
        cards.add(new Carte(EnumCarte.B6, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 2, EnumRessources.BLEUE));
        cards.add(new Carte(EnumCarte.B5, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 2, EnumRessources.BLEUE));
        cards.add(new Carte(EnumCarte.B4, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 2, EnumRessources.BLEUE));

        // CARTE JAUNE

        cards.add(new Carte(EnumCarte.J7, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Collections.singletonList(EnumRessources.MULTIBPAM), 3, 2, EnumRessources.JAUNE));
        cards.add(new Carte(EnumCarte.J6, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Collections.singletonList(EnumRessources.MULTIVPT), 3, 2, EnumRessources.JAUNE));
        cards.add(new Carte(EnumCarte.J5, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BONUS1MDJG), 3, 2, EnumRessources.JAUNE));


        // CARTE ROUGE

        cards.add(new Carte(EnumCarte.R6, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 3, 2, EnumRessources.ROUGE));
        cards.add(new Carte(EnumCarte.R5, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 3, 2, EnumRessources.ROUGE));
        cards.add(new Carte(EnumCarte.R4, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 3, 2, EnumRessources.ROUGE));

        // CARTE VERTE

        cards.add(new Carte(EnumCarte.V7, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.VERRE), Collections.singletonList(EnumRessources.COMPAS), 3, 2, EnumRessources.VERTE));
        cards.add(new Carte(EnumCarte.V6, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.ROUE), 3, 2, EnumRessources.VERTE));
        cards.add(new Carte(EnumCarte.V5, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.TISSU), Collections.singletonList(EnumRessources.PDR), 3, 2, EnumRessources.VERTE));
        cards.add(new Carte(EnumCarte.V4, Arrays.asList(EnumRessources.BOIS, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.PDR), 3, 2, EnumRessources.VERTE));


        if (nbJoueur >= 4) {

            // CARTE MARRON

            cards.add(new Carte(EnumCarte.M14, Collections.singletonList(EnumRessources.PIECE), Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), 4, 2, EnumRessources.MARRON));
            cards.add(new Carte(EnumCarte.M13, Collections.singletonList(EnumRessources.PIECE), Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE), 4, 2, EnumRessources.MARRON));
            cards.add(new Carte(EnumCarte.M12, Collections.singletonList(EnumRessources.PIECE), Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), 4, 2, EnumRessources.MARRON));
            cards.add(new Carte(EnumCarte.M11, Collections.singletonList(EnumRessources.PIECE), Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI), 4, 2, EnumRessources.MARRON));

            // CARTE ROUGE

            cards.add(new Carte(EnumCarte.R7, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 4, 2, EnumRessources.ROUGE));

            //CARTE VERTE

            cards.add(new Carte(EnumCarte.V7, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.VERRE), Collections.singletonList(EnumRessources.COMPAS), 4, 2, EnumRessources.VERTE));

            // CARTE JAUNE

            cards.add(new Carte(EnumCarte.J8, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BONUS2GDJG), 4, 2, EnumRessources.JAUNE));
        }

        if (nbJoueur >= 5) {


            // CARTE GRISE

            cards.add(new Carte(EnumCarte.G2, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.VERRE), 5, 2, EnumRessources.GRISE));
            cards.add(new Carte(EnumCarte.G1, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PAPYRUS), 5, 2, EnumRessources.GRISE));
            cards.add(new Carte(EnumCarte.G3, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.TISSU), 5, 2, EnumRessources.GRISE));

            // CARTE BLEUE

            cards.add(new Carte(EnumCarte.B4, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 5, 2, EnumRessources.BLEUE));

            // CARTE JAUNE

            cards.add(new Carte(EnumCarte.J7, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Collections.singletonList(EnumRessources.MULTIBPAM), 5, 2, EnumRessources.JAUNE));

            // CARTE ROUGE

            cards.add(new Carte(EnumCarte.R6, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 5, 2, EnumRessources.ROUGE));

            // CARTE VERTE

            cards.add(new Carte(EnumCarte.V6, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.ROUE), 5, 2, EnumRessources.VERTE));
        }

        if (nbJoueur >= 6) {

            // CARTE BLEUE

            cards.add(new Carte(EnumCarte.B5, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 6, 2, EnumRessources.BLEUE));

            // CARTE JAUNE

            cards.add(new Carte(EnumCarte.J7, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS), Collections.singletonList(EnumRessources.MULTIBPAM), 6, 2, EnumRessources.JAUNE));
            cards.add(new Carte(EnumCarte.J5, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BONUS1MDJG), 6, 2, EnumRessources.JAUNE));
            cards.add(new Carte(EnumCarte.J6, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Collections.singletonList(EnumRessources.MULTIVPT), 6, 2, EnumRessources.JAUNE));

            // CARTE ROUGE

            cards.add(new Carte(EnumCarte.R7, Arrays.asList(EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 6, 2, EnumRessources.ROUGE));
            cards.add(new Carte(EnumCarte.R5, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 6, 2, EnumRessources.ROUGE));

            // CARTE VERTE

            cards.add(new Carte(EnumCarte.V5, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.TISSU), Collections.singletonList(EnumRessources.PDR), 6, 2, EnumRessources.VERTE));
        }

        if (nbJoueur >= 7) {

            // CARTE BLEUE

            cards.add(new Carte(EnumCarte.B7, Arrays.asList(EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 7, 2, EnumRessources.BLEUE));
            cards.add(new Carte(EnumCarte.B6, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 7, 2, EnumRessources.BLEUE));

            // CARTE JAUNE

            cards.add(new Carte(EnumCarte.J6, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Collections.singletonList(EnumRessources.MULTIVPT), 7, 2, EnumRessources.JAUNE));
            cards.add(new Carte(EnumCarte.J8, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BONUS2GDJG), 7, 2, EnumRessources.JAUNE));

            // CARTE ROUGE

            cards.add(new Carte(EnumCarte.R7, Arrays.asList(EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 7, 2, EnumRessources.ROUGE));
            cards.add(new Carte(EnumCarte.R4, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 7, 2, EnumRessources.ROUGE));

            // CARTE VERTE

            cards.add(new Carte(EnumCarte.V4, Arrays.asList(EnumRessources.BOIS, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.PDR), 7, 2, EnumRessources.VERTE));
        }
    }


    /**
     * Fabrique les Cartes De l'Age 3 en fonction du nombre de joueur entré en paramètre 3-7
     * @param nbJoueur Nombre de joueurs
     */

    private void fabriqueAge3(int nbJoueur) {
        cards = new ArrayList<>();
        ArrayList<Carte> guilde = fabriqueGuilde();

        // CARTE BLEUE 3 JOUEURS

        cards.add(new Carte(EnumCarte.B8, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.MINERAI, EnumRessources.PAPYRUS, EnumRessources.TISSU, EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 3, EnumRessources.BLEUE));
        cards.add(new Carte(EnumCarte.B9, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 3, EnumRessources.BLEUE));
        cards.add(new Carte(EnumCarte.B10, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.MINERAI, EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 3, EnumRessources.BLEUE));
        cards.add(new Carte(EnumCarte.B11, Arrays.asList(EnumRessources.BOIS, EnumRessources.PIERRE, EnumRessources.ARGILE, EnumRessources.MINERAI, EnumRessources.VERRE, EnumRessources.PAPYRUS, EnumRessources.TISSU), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 3, EnumRessources.BLEUE));
        cards.add(new Carte(EnumCarte.B12, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.PIERRE, EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 3, EnumRessources.BLEUE));

        // CARTE JAUNE 3 JOUEURS

        cards.add(new Carte(EnumCarte.J9, Arrays.asList(EnumRessources.PIERRE, EnumRessources.VERRE), Collections.singletonList(EnumRessources.BONUS11J), 3, 3, EnumRessources.JAUNE));
        cards.add(new Carte(EnumCarte.J10, Arrays.asList(EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUS11M), 3, 3, EnumRessources.JAUNE));
        cards.add(new Carte(EnumCarte.J11, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUS31MERVEILLE), 3, 3, EnumRessources.JAUNE));

        // CARTE ROUGE 3 JOUEURS


        cards.add(new Carte(EnumCarte.R8, Arrays.asList(EnumRessources.PIERRE, EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 3, 3, EnumRessources.ROUGE));
        cards.add(new Carte(EnumCarte.R9, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.TISSU), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 3, 3, EnumRessources.ROUGE));
        cards.add(new Carte(EnumCarte.R10, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 3, 3, EnumRessources.ROUGE));

        // CARTE VERTE 3 JOUEURS

        cards.add(new Carte(EnumCarte.V8, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.TISSU, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.COMPAS), 3, 3, EnumRessources.VERTE));
        cards.add(new Carte(EnumCarte.V9, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.VERRE), Collections.singletonList(EnumRessources.COMPAS), 3, 3, EnumRessources.VERTE));
        cards.add(new Carte(EnumCarte.V10, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.VERRE, EnumRessources.TISSU), Collections.singletonList(EnumRessources.ROUE), 3, 3, EnumRessources.VERTE));
        cards.add(new Carte(EnumCarte.V11, Arrays.asList(EnumRessources.BOIS, EnumRessources.PAPYRUS, EnumRessources.TISSU), Collections.singletonList(EnumRessources.ROUE), 3, 3, EnumRessources.VERTE));
        cards.add(new Carte(EnumCarte.V12, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.PAPYRUS, EnumRessources.VERRE), Collections.singletonList(EnumRessources.PDR), 3, 3, EnumRessources.VERTE));

        for (int i = 0; i < (nbJoueur + 2); i++) {
            cards.add(guilde.get(i));
        }


        if (nbJoueur >= 4) {

            // CARTE BLEUE 4 JOUEURS

            cards.add(new Carte(EnumCarte.B9, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 4, 3, EnumRessources.BLEUE));

            // CARTE JAUNE 4 JOUEURS

            cards.add(new Carte(EnumCarte.J10, Arrays.asList(EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUS11M), 4, 3, EnumRessources.JAUNE));
            cards.add(new Carte(EnumCarte.J12, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.BONUS22G), 4, 3, EnumRessources.JAUNE));

            //CARTE ROUGE 4 JOUEUR

            cards.add(new Carte(EnumCarte.R11, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.BOIS, EnumRessources.PAPYRUS), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 4, 3, EnumRessources.ROUGE));
            cards.add(new Carte(EnumCarte.R12, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 4, 3, EnumRessources.ROUGE));

            // CARTE VERTE 4 JOUEUR

            cards.add(new Carte(EnumCarte.V12, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.PAPYRUS, EnumRessources.VERRE), Collections.singletonList(EnumRessources.PDR), 4, 3, EnumRessources.VERTE));
        }

        if (nbJoueur >= 5) {

            // CARTE BLEUE 5 JOUEURS

            cards.add(new Carte(EnumCarte.B12, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.PIERRE, EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 5, 3, EnumRessources.BLEUE));

            // CARTE JAUNE 5 JOUEURS

            cards.add(new Carte(EnumCarte.J13, Arrays.asList(EnumRessources.PIERRE, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUS31R), 5, 3, EnumRessources.JAUNE));
            cards.add(new Carte(EnumCarte.J11, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUS31MERVEILLE), 5, 3, EnumRessources.JAUNE));


            // CARTE ROUGE 5 JOUEURS

            cards.add(new Carte(EnumCarte.R9, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.TISSU), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 5, 3, EnumRessources.ROUGE));
            cards.add(new Carte(EnumCarte.R10, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 5, 3, EnumRessources.ROUGE));

            // CARTE VERTE 5 JOUEURS

            cards.add(new Carte(EnumCarte.V11, Arrays.asList(EnumRessources.BOIS, EnumRessources.PAPYRUS, EnumRessources.TISSU), Collections.singletonList(EnumRessources.ROUE), 5, 3, EnumRessources.VERTE));
        }

        if (nbJoueur >= 6) {

            // CARTE BLEUE 6 JOUEURS

            cards.add(new Carte(EnumCarte.B8, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.MINERAI, EnumRessources.PAPYRUS, EnumRessources.TISSU, EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 6, 3, EnumRessources.BLEUE));
            cards.add(new Carte(EnumCarte.B10, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.MINERAI, EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 6, 3, EnumRessources.BLEUE));

            // CARTE JAUNE 6 JOUEURS

            cards.add(new Carte(EnumCarte.J9, Arrays.asList(EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUS11J), 6, 3, EnumRessources.JAUNE));
            cards.add(new Carte(EnumCarte.J12, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.BONUS22G), 6, 3, EnumRessources.JAUNE));

            // CARTE ROUGE 6 JOUEURS

            cards.add(new Carte(EnumCarte.R12, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 6, 3, EnumRessources.ROUGE));

            // CARTE VERTE 6 JOUEURS

            cards.add(new Carte(EnumCarte.V8, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.TISSU, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.COMPAS), 6, 3, EnumRessources.VERTE));
        }

        if (nbJoueur >= 7) {

            // CARTE BLEUE 7 JOUEURS

            cards.add(new Carte(EnumCarte.B11, Arrays.asList(EnumRessources.BOIS, EnumRessources.PIERRE, EnumRessources.ARGILE, EnumRessources.MINERAI, EnumRessources.TISSU, EnumRessources.PAPYRUS, EnumRessources.VERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 7, 3, EnumRessources.BLEUE));

            // CARTE JAUNE 7 JOUEURS

            cards.add(new Carte(EnumCarte.J13, Arrays.asList(EnumRessources.PIERRE, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUS31R), 7, 3, EnumRessources.JAUNE));

            // CARTE ROUGE 7 JOUEURS

            cards.add(new Carte(EnumCarte.R11, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.BOIS, EnumRessources.PAPYRUS), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 7, 3, EnumRessources.ROUGE));
            cards.add(new Carte(EnumCarte.R8, Arrays.asList(EnumRessources.PIERRE, EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.BOUCLIER, EnumRessources.BOUCLIER, EnumRessources.BOUCLIER), 7, 3, EnumRessources.ROUGE));

            // CARTE VERTE 7 JOUEURS

            cards.add(new Carte(EnumCarte.V9, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.VERRE), Collections.singletonList(EnumRessources.COMPAS), 7, 3, EnumRessources.VERTE));
            cards.add(new Carte(EnumCarte.V10, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.TISSU, EnumRessources.VERRE), Collections.singletonList(EnumRessources.ROUE), 7, 3, EnumRessources.VERTE));

        }
    }

    /**
     * Fabrique les cartes Guildes
     * @return la liste des cartes guildes
     */
    private ArrayList<Carte> fabriqueGuilde() {
        ArrayList<Carte> guilde = new ArrayList<>();
        guilde.add(new Carte(EnumCarte.P1, Arrays.asList(EnumRessources.BOIS, EnumRessources.PIERRE, EnumRessources.ARGILE, EnumRessources.MINERAI, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.VBONUS1M), 3, 3, EnumRessources.VIOLETTE));
        guilde.add(new Carte(EnumCarte.P2, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.MINERAI, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.VBONUS2G), 3, 3, EnumRessources.VIOLETTE));
        guilde.add(new Carte(EnumCarte.P3, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.PIERRE, EnumRessources.TISSU), Collections.singletonList(EnumRessources.VBONUS1B), 3, 3, EnumRessources.VIOLETTE));
        guilde.add(new Carte(EnumCarte.P4, Arrays.asList(EnumRessources.TISSU, EnumRessources.PAPYRUS, EnumRessources.VERRE), Collections.singletonList(EnumRessources.VBONUS1J), 3, 3, EnumRessources.VIOLETTE));
        guilde.add(new Carte(EnumCarte.P5, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.VERRE), Collections.singletonList(EnumRessources.VBONUS1R), 3, 3, EnumRessources.VIOLETTE));
        guilde.add(new Carte(EnumCarte.P6, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.TISSU, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.VBONUS1V), 3, 3, EnumRessources.VIOLETTE));
        guilde.add(new Carte(EnumCarte.P7, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUSCPR), 3, 3, EnumRessources.VIOLETTE));
        guilde.add(new Carte(EnumCarte.P8, Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.PAPYRUS, EnumRessources.VERRE), Collections.singletonList(EnumRessources.VBONUSMGV), 3, 3, EnumRessources.VIOLETTE));
        guilde.add(new Carte(EnumCarte.P9, Arrays.asList(EnumRessources.PIERRE, EnumRessources.PIERRE, EnumRessources.ARGILE, EnumRessources.ARGILE, EnumRessources.VERRE), Collections.singletonList(EnumRessources.VBONUS1MERVEILLE), 3, 3, EnumRessources.VIOLETTE));
        guilde.add(new Carte(EnumCarte.P10, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.PIERRE, EnumRessources.TISSU), Collections.singletonList(EnumRessources.VBONUS7COMPLETMERVEILLE), 3, 3, EnumRessources.VIOLETTE));
        Collections.shuffle(guilde);

        return guilde;
    }

}
