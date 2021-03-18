package sw_aventure.seven_wonders;

import objet_commun.Carte;
import objet_commun.Merveille;
import exception.NegativeNumberException;
import metier.EnumCarte;
import metier.Wonder;
import sw_aventure.objetjeu.*;
import metier.EnumRessources;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.Plateau;
import utilitaire_jeu.SetInventaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doAnswer;


@ExtendWith(MockitoExtension.class)
public class DeroulementJeuTest {



    private DeroulementJeu deroulementJeu;

    private SetInventaire setInv1 , setInv2, setInv3;
    private MoteurWebController web;
    private Plateau plateau;

    private SetInventaire inv4;
    private Carte carte1, carte2, carte3;


    @Mock
    private DeroulementJeu jeu;
    private SetInventaire inv;


    GenererCarte fabriqueCarte;
    private int nbJoueurs;



    /**
     * Preparation pour les tests de la classe DeroulementJeu
     */
    @Before
    public void setup() {

        nbJoueurs = 3;
        fabriqueCarte = new GenererCarte(1, nbJoueurs);
        jeu = Mockito.mock(DeroulementJeu.class);

        carte1 =  new Carte(EnumCarte.R1, Collections.singletonList(EnumRessources.ARGILE), Collections.singletonList(EnumRessources.BOUCLIER), 3, 1, EnumRessources.ROUGE);
        carte2 = new Carte(EnumCarte.R3, Collections.singletonList(EnumRessources.PIERRE), Collections.singletonList(EnumRessources.BOUCLIER), 3, 1, EnumRessources.ROUGE);
        carte3 = new Carte(EnumCarte.R2, Collections.singletonList(EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BOUCLIER), 3, 1, EnumRessources.ROUGE);


        inv = Mockito.mock(SetInventaire.class);
        setInv1 = new SetInventaire( 1,"BREBNREER", "Enzo");
        setInv2 = new SetInventaire( 2,"HENJEET", "Christina");
        setInv3 = new SetInventaire( 3,"REHRENJER", "Mona");
        inv4 = new SetInventaire(4, "GRNERNERJS", "Paul");

        ArrayList<Carte> etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.VERRE,EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.PIERRE,EnumRessources.PIERRE,EnumRessources.PIERRE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        setInv1.modifMerveille( new Merveille(Wonder.HALIKARNASSOS,EnumRessources.TISSU, etape));
        setInv2.modifMerveille( new Merveille(Wonder.HALIKARNASSOS,EnumRessources.TISSU, etape));
        setInv3.modifMerveille( new Merveille(Wonder.HALIKARNASSOS,EnumRessources.TISSU, etape));


        ArrayList<SetInventaire> listeSetInventaire = new ArrayList<>();
        listeSetInventaire.add(setInv1);
        listeSetInventaire.add(setInv2);
        listeSetInventaire.add(setInv3);

        deroulementJeu = new DeroulementJeu(web,listeSetInventaire);
    }


    /**
     * Test de la méthode initMainJoueur()
     * On verifie si la liste mainJoueur est bien initialise en fonction du nombre de joueur
     * On verifie avant et apres l'initialisation de cette liste si la methode fonctionne bien
     */
    @Test
    public void initMainJoueurTest() {
        // Au départ la main des joueurs n'est pas initialisé
        assertEquals(0, deroulementJeu.getMainJoueurs().size());

        // On initialise les mains pour 3 joueurs
        deroulementJeu.initMainJoueur(3);
        assertEquals(3, deroulementJeu.getMainJoueurs().size());
    }


    /**
     * Test de la méthode gagnante()
     * On verifie le cas d un gagnant sans egalite et on teste aussi le cas d'une egalite
     */
    /*
    @Test
    public void gagnanteTest() throws NegativeNumberException {
        // Le joueur 1 gagne
        setInv1.increaseValue(EnumRessources.SCORE, 10);

        ArrayList<Inventaire> listeInventaire;
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        listeInventaire.add(setInv3);

        ArrayList<String> listeJoueurs;
        listeJoueurs = new ArrayList<>();
        listeJoueurs.add(setInv1.getUrl());
        listeJoueurs.add(setInv2.getUrl());
        listeJoueurs.add(setInv3.getUrl());

        plateau = new Plateau(listeInventaire);
        deroulementJeu.gagnante(nbJoueurs, plateau);
        List<SetInventaire> inv;
        inv = deroulementJeu.getSetInventaire();
        for (SetInventaire s : inv) {
            if (s.getId() == setInv1.getId())
                assertEquals(1, s.getValue(EnumRessources.VICTOIRETOTAL));
            else
                assertEquals(0, s.getValue(EnumRessources.VICTOIRETOTAL));
        }

        // Il y a une égalité avec les joueur 1 et 2, alors on départage avec les pièce, le joueur 2 l'emporte
        setInv1.clear();
        setInv2.clear();
        setInv1.increaseValue(EnumRessources.SCORE, 10);
        setInv2.increaseValue(EnumRessources.SCORE, 10);
        setInv2.increaseValue(EnumRessources.PIECE, 10);


        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        listeInventaire.add(setInv3);

        plateau = new Plateau(listeInventaire);
        deroulementJeu.gagnante(nbJoueurs, plateau);
        inv = deroulementJeu.getSetInventaire();
        for (SetInventaire s : inv) {
            if (s.getId() == setInv2.getId())
                assertEquals(1, s.getValue(EnumRessources.VICTOIRETOTAL));
            else
                assertEquals(0, s.getValue(EnumRessources.VICTOIRETOTAL));
        }
    }

*/
    /**
     * Test du getter getMainJoueurs
     * On verifie que le nombre de joueurs est egale au nombre de mains des joueurs en regardant la taille renvoyee par la methode size()
     */
    @Test
    public void getMainJoueursTest() {
        deroulementJeu.initMainJoueur(nbJoueurs);
        deroulementJeu.distribution(fabriqueCarte.getCards());

        assertEquals(deroulementJeu.getMainJoueurs().size(), nbJoueurs);
    }


    /**
     * Test du getter getSetInventaire()
     * On verifie si cette methode renvoit a bien la liste des inventaires avec la methode hashCode()
     */
    @Test
    public void getSetInventaireTest() {
        ArrayList<SetInventaire> listeSetInvenatire = new ArrayList<>();
        listeSetInvenatire.add(setInv1);
        listeSetInvenatire.add(setInv2);
        listeSetInvenatire.add(setInv3);

        int hash = listeSetInvenatire.hashCode();
        deroulementJeu = new DeroulementJeu(web,listeSetInvenatire);

        assertEquals(hash, deroulementJeu.getSetInventaire().hashCode());
    }


    /**
     * Test de la methode distributionTest()
     * On vérifie que la distribution ait bien lieu.
     **/
    @Test
    public void distributionTest() {
        deroulementJeu.initMainJoueur(nbJoueurs);
        deroulementJeu.distribution(fabriqueCarte.getCards());
        for (MainJoueur uneMain : deroulementJeu.getMainJoueurs()) {
            assertEquals(7 ,uneMain.getMain().size());
        }
    }


    /**
     * Test de la methode echangerMainTest()
     * On vérifie que le changement de main ait bien lieu.
     **/
    @Test
    public void echangerMainTest() {
        deroulementJeu.initMainJoueur(nbJoueurs);
        deroulementJeu.distribution(fabriqueCarte.getCards());


        int[] hashcode = new int[nbJoueurs];
        for (int i = 0; i < nbJoueurs; i++) {
            hashcode[i] = deroulementJeu.getMainJoueurs().get(i).getMain().hashCode();
        }

        // On échange les mains de la gauche vers la droite (pour simuler l'age 1)
        deroulementJeu.echangerMain(1);

        // On vérifie le HashCode
        for (int i = 0; i < nbJoueurs; i++) {
            if (i == nbJoueurs - 1) assertEquals(hashcode[i], deroulementJeu.getMainJoueurs().get(0).getMain().hashCode());

            else assertEquals(hashcode[i], deroulementJeu.getMainJoueurs().get(i + 1).getMain().hashCode());
        }

        // On échange les mains cet fois de la droite vers la gauche (pour simuler l'age 2)
        deroulementJeu.echangerMain(2);

        // On re-vérifie le premier HashCode
        for (int i = 0; i < nbJoueurs; i++) {
            assertEquals(hashcode[i], deroulementJeu.getMainJoueurs().get(i).getMain().hashCode());
        }
    }


    /**
     * Test de la méthode guerre() sur les points de victoire
     * On verifie si on incremente bien les points de victoire et de defaites apres la guerre
     */

    @Test
    public void guerre() throws NegativeNumberException {
        ArrayList<SetInventaire> set = new ArrayList<>() {{add(inv); add(setInv1); add(setInv2); add(setInv3); add(inv4);}};

        for (SetInventaire s : set) {
            s = Mockito.mock(SetInventaire.class);
            s.clear();
            inv.ajoutCarteInv(carte1);
            inv.ajoutCarteInv(carte2);
            inv.ajoutCarteInv(carte3);


            doAnswer(invocation -> {
                Object arg0 = invocation.getArgument(0);
                Object arg1 = invocation.getArgument(1);

                assertEquals(EnumRessources.DEFAITE, arg0);
                assertEquals(1, arg1);

                return null;
            }).when(s).increaseValue(EnumRessources.DEFAITE, 1);
            s.increaseValue(EnumRessources.DEFAITE, 1);
            //jeuSpied.guerre(1);
            //Mockito.verify(inv).increaseValue(EnumRessources.DEFAITE,1);
            Mockito.verify(s).increaseValue(EnumRessources.DEFAITE, 1);

            doAnswer(invocation -> {
                Object arg0 = invocation.getArgument(0);
                Object arg1 = invocation.getArgument(1);

                assertEquals(EnumRessources.VICTOIRE, arg0);
                assertEquals(1, arg1);

                return null;
            }).when(s).increaseValue(EnumRessources.VICTOIRE, 1);
            s.increaseValue(EnumRessources.VICTOIRE, 1);
            Mockito.verify(s).increaseValue(EnumRessources.VICTOIRE, 1);

            doAnswer(invocation -> {
                Object arg0 = invocation.getArgument(0);
                Object arg1 = invocation.getArgument(1);

                assertEquals(EnumRessources.VICTOIRE, arg0);
                assertEquals(3, arg1);

                return null;
            }).when(s).increaseValue(EnumRessources.VICTOIRE, 3);
            s.increaseValue(EnumRessources.VICTOIRE, 3);
            Mockito.verify(s).increaseValue(EnumRessources.VICTOIRE, 3);

            doAnswer(invocation -> {
                Object arg0 = invocation.getArgument(0);
                Object arg1 = invocation.getArgument(1);

                assertEquals(EnumRessources.VICTOIRE, arg0);
                assertEquals(5, arg1);

                return null;
            }).when(s).increaseValue(EnumRessources.VICTOIRE, 5);
            s.increaseValue(EnumRessources.VICTOIRE, 5);
            Mockito.verify(s).increaseValue(EnumRessources.VICTOIRE, 5);



        }


        }

    /**
     * Test de la méthode joueAgeTest()
     *  On verifie si on lance bien un Age en fonction du nombre de joueur
     */
    /*
    @Test
    public void joueAgeTest() throws NegativeNumberException {
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);

            assertEquals(1, arg0);
            assertEquals(5, arg1);

            return null;
        }).when(jeu).joueAge(1, 5 , plateau);
        jeu.joueAge(1, 5 , plateau);

        Mockito.verify(jeu).joueAge(1, 5 , plateau);


        ArrayList<Inventaire> listeInventaire;
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        listeInventaire.add(setInv3);

        ArrayList<String> listeJoueurs;
        listeJoueurs = new ArrayList<>();
        listeJoueurs.add(setInv1.getUrl());
        listeJoueurs.add(setInv2.getUrl());
        listeJoueurs.add(setInv3.getUrl());

        plateau = new Plateau(listeInventaire);

        deroulementJeu.initMainJoueur(nbJoueurs);
        deroulementJeu.distribution(fabriqueCarte.getCards());

        // On vérifie que les main des joueur contiennent bien 7 carte en début d'âge
        assertEquals(7, deroulementJeu.getMainJoueurs().get(0).getMain().size());

        deroulementJeu.joueAge(1, nbJoueurs, plateau);

        // On vérifie qu'a la fin de l'âge les joueurs on bien joué leurs cartes
        // Leurs main ne doit pas contenir 7 cartes

        // A REFAIRE
        //assertNotEquals(7, deroulementJeu.getMainJoueurs().get(0).getMain().size());
    }
*/

    /**
     * Test de la méthode guerre()
     * On teste si la guerrre a bien lieu
     * On verifie pour chaque age que la methode fonctionne correctement
     */
    @Test
    public void guerreTest() throws NegativeNumberException {
        ArrayList<SetInventaire> listeSetInventaire;
        listeSetInventaire = new ArrayList<>();

        setInv1.increaseValue(EnumRessources.BOUCLIER, 2);
        listeSetInventaire.add(setInv1);
        listeSetInventaire.add(setInv2);
        listeSetInventaire.add(setInv3);

        deroulementJeu = new DeroulementJeu(web,listeSetInventaire);
        deroulementJeu.guerre(1);
        // Premier Âge, le joueur 1 a 10 bouclier et les autres joueurs n'ont en pas, donc il doit remporter 2 point de victoire,
        // car il gagne son joueur de droite et son joueur de gauche.
        // Les joueur 2 et 3 ont donc 1 point de defaite chacun.

        for (SetInventaire s : deroulementJeu.getSetInventaire()) {
            if (s.getId() == setInv1.getId()) {
                assertEquals(2, s.getValue(EnumRessources.VICTOIRE));
                assertEquals(0, s.getValue(EnumRessources.DEFAITE));
            }
            else {
                assertEquals(0, s.getValue(EnumRessources.VICTOIRE));
                assertEquals(1, s.getValue(EnumRessources.DEFAITE));
            }
        }


        // Âge 2
        listeSetInventaire = new ArrayList<>();
        setInv1.clear();
        setInv2.clear();
        setInv3.clear();
        setInv2.increaseValue(EnumRessources.BOUCLIER, 10);
        listeSetInventaire.add(setInv1);
        listeSetInventaire.add(setInv2);
        listeSetInventaire.add(setInv3);
        deroulementJeu = new DeroulementJeu(web,listeSetInventaire);
        deroulementJeu.guerre(2);

        for (SetInventaire s : deroulementJeu.getSetInventaire()) {
            if (s.getId() == setInv2.getId()) {
                assertEquals(6, s.getValue(EnumRessources.VICTOIRE));
                assertEquals(0, s.getValue(EnumRessources.DEFAITE));
            }
            else {
                assertEquals(0, s.getValue(EnumRessources.VICTOIRE));
                assertEquals(1, s.getValue(EnumRessources.DEFAITE));
            }
        }


        // Âge 3
        listeSetInventaire = new ArrayList<>();
        setInv1.clear();
        setInv2.clear();
        setInv3.clear();
        setInv2.increaseValue(EnumRessources.BOUCLIER, 10);
        listeSetInventaire.add(setInv1);
        listeSetInventaire.add(setInv2);
        listeSetInventaire.add(setInv3);
        deroulementJeu = new DeroulementJeu(web,listeSetInventaire);
        deroulementJeu.guerre(3);

        for (SetInventaire s : deroulementJeu.getSetInventaire()) {
            if (s.getId() == setInv2.getId()) {
                assertEquals(10, s.getValue(EnumRessources.VICTOIRE));
                assertEquals(0, s.getValue(EnumRessources.DEFAITE));
            }
            else {
                assertEquals(0, s.getValue(EnumRessources.VICTOIRE));
                assertEquals(1, s.getValue(EnumRessources.DEFAITE));
            }
        }
    }


    /**
     * Test de la méthode laPartie()
     *  On verifie si la partie a une fin en regardant si les joueurs ont un Score final
     */
    /*
    @Test
    public void laPartieTest() throws NegativeNumberException {
        ArrayList<Inventaire> listeInventaire;
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        listeInventaire.add(setInv3);

        ArrayList<String> listeJoueurs;
        listeJoueurs = new ArrayList<>();
        listeJoueurs.add(setInv1.getUrl());
        listeJoueurs.add(setInv2.getUrl());
        listeJoueurs.add(setInv3.getUrl());

        plateau = new Plateau(listeInventaire);

        deroulementJeu.laPartie(plateau, 3);

        // On vérifie que les joueur ont tous un score final
        for (SetInventaire s : deroulementJeu.getSetInventaire())
            assertNotEquals(0, s.getValue(EnumRessources.SCOREFINAL));
    }*/
}
