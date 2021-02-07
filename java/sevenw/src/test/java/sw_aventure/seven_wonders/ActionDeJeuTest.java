package sw_aventure.seven_wonders;

import objet_commun.Carte;
import objet_commun.Merveille;
import exception.NegativeNumberException;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import metier.Wonder;
import sw_aventure.joueur.Joueur;
import sw_aventure.objetjeu.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;


public class ActionDeJeuTest {

    private int nbJoueurs;
    private ActionDeJeu actionDeJeu;
    private SetInventaire setInv1, setInv2, setInv3;
    private Joueur joueur1, joueur2, joueur3;

    private Carte chantier, jardins;
    private Merveille babylon;

    private ArrayList<Inventaire> listeInventaire;
    private ArrayList<SetInventaire> listSetInventaire;
    private ArrayList<Joueur> listeJoueur;

    private Plateau plateau;
    private List<MainJoueur> mainJoueurs;
    private ArrayList<Carte> paquetDefausse;
    private GenererCarte fabriqueCarte;


    /**
     * Preparation pour les tests de la classe ActionDeJeu
     */
    @Before
    public void setup() {
        nbJoueurs = 3;
        setInv1 = new SetInventaire(0, Strategy.MERVEILLE, "Enzo");
        setInv2 = new SetInventaire(1, Strategy.MERVEILLE, "Christina");
        setInv3 = new SetInventaire(2, Strategy.MERVEILLE, "Mona");

        joueur1 = setInv1.getJoueur();
        joueur2 = setInv2.getJoueur();
        joueur3 = setInv3.getJoueur();

        chantier = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS), 3, 1, EnumRessources.MARRON);
        jardins = new Carte(EnumCarte.B9, Arrays.asList(EnumRessources.BOIS, EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 3, EnumRessources.BLEUE);

        List<Carte> etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUSCPR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
        babylon = new Merveille(Wonder.BABYLON, EnumRessources.BOIS, etape);

        setInv1.modifMerveille(babylon);
        setInv2.modifMerveille(babylon);
        setInv3.modifMerveille(babylon);

        fabriqueCarte = new GenererCarte(1, 5);

        listeInventaire = new ArrayList<>(){{add(setInv1);add(setInv2);add(setInv3);}};
        listeJoueur = new ArrayList<>(){{add(joueur1);add(joueur2);add(joueur3);}};
        plateau = new Plateau(listeInventaire, listeJoueur);

        mainJoueurs = new ArrayList<>() {{add(new MainJoueur()); add(new MainJoueur()); add(new MainJoueur());}};
        paquetDefausse = new ArrayList<>();

        listSetInventaire = new ArrayList<>(){{add(setInv1);add(setInv2);add(setInv3);}};
        actionDeJeu = new ActionDeJeu(listSetInventaire, mainJoueurs, paquetDefausse);
    }



    /**
     * Test de la méthode basicConstruire()
     * On verifie si on lance la construction d'un bâtiment
     */
    @Test
    public void basicConstruireTest() throws NegativeNumberException {
        assertEquals(0, setInv1.getListeCarte().size());
        // Le joueur peut construire la carte chantier car elle est gratuite, alors elle est ajouté a son inventaire
        actionDeJeu.basicConstruire(chantier, setInv1, plateau);
        assertEquals("Chantier", setInv1.getListeCarte().get(0).toString());
        assertEquals(3, setInv1.getValue(EnumRessources.PIECE));

        setup();
        // Le joueur ne peut pas construire la carte jardins, alors il l'a défausse et obtient 3 pièce (ce qui lui fait 6 pièce)
        actionDeJeu.basicConstruire(jardins, setInv1, plateau);
        assertEquals(0, setInv1.getListeCarte().size());
        assertEquals(6, setInv1.getValue(EnumRessources.PIECE));
    }


    /**
     * Test de la méthode merveilleConstruire()
     * On vérifie que les merveilles construites soient complètes
     * .
     */

    @Test
    public void merveilleConstruire() throws NegativeNumberException {

        ActionDeJeu action = Mockito.mock(ActionDeJeu.class);

        Mockito.when(action.merveilleConstruire(fabriqueCarte.getCards().get(0),setInv1,plateau)).thenAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);
            Object arg2 = invocation.getArgument(2);

            assertEquals(fabriqueCarte.getCards().get(0), arg0);
            assertEquals(setInv1, arg1);
            assertEquals(plateau, arg2);

            return null;
        });
        Boolean decid = action.merveilleConstruire(fabriqueCarte.getCards().get(0), setInv1, plateau);
        Mockito.verify(action).merveilleConstruire( fabriqueCarte.getCards().get(0), setInv1, plateau );
        assertEquals(false,decid);


        // Au départ le stade de construction de la merveille est a 0
        assertEquals(0, actionDeJeu.inv.get(0).getMerveille().getStade());

        // La carte chantier passer en paramètre représente un étage de la merveille
        actionDeJeu.merveilleConstruire(chantier, setInv1, plateau);

        // Le joueur peut construire l'etage de la merveille car c'est gratuit, le stade de la merveille est donc incrémenté
        assertEquals(1, actionDeJeu.inv.get(0).getMerveille().getStade());

        // Le joueur ne peut pas construire la merveille car c'est payant il défausse donc la carte, et obtient 3 pièces
        actionDeJeu.merveilleConstruire(jardins, setInv1, plateau);
        assertEquals(6, setInv1.getValue(EnumRessources.PIECE));
    }

    /**
     * Test de la méthode constructionMerveille()
     * On vérifie que les constructions des merveilles soient complètes .
     */

    @Test
    public void constructionMerveille() throws NegativeNumberException{

        ActionDeJeu action = Mockito.mock(ActionDeJeu.class);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);

            assertEquals(setInv1, arg0);
            assertEquals(plateau, arg1);

            return null;
        }).when(action).constructionMerveille(setInv1,plateau);
        Boolean decide = action.constructionMerveille(setInv1,plateau);
        Mockito.verify(action).constructionMerveille(setInv1 , plateau);
        assertEquals(false,decide);

    }


    /**
     * Test de la méthode jouerLa7emeCarte()
     * On verifie si on fait bien jouer la 7ème carte de la main au joueur ayant ce bonus
     */
    @Test
    public void jouerLa7emeCarteTest() throws NegativeNumberException {
        setInv1.increaseValue(EnumRessources.BONUS7CARTEMAIN, 1);

        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        listeInventaire.add(setInv3);

        plateau = new Plateau(listeInventaire, listeJoueur);

        listSetInventaire = new ArrayList<>();
        listSetInventaire.add(setInv1);
        listSetInventaire.add(setInv2);
        listSetInventaire.add(setInv3);

        MainJoueur main;
        main = new MainJoueur();
        main.add(chantier);

        mainJoueurs = new ArrayList<>();
        mainJoueurs.add(main);
        mainJoueurs.add(main);
        mainJoueurs.add(main);

        actionDeJeu = new ActionDeJeu(listSetInventaire, mainJoueurs, paquetDefausse);

        // Au départ le joueur 1 n'a pas de carte
        assertEquals(0, setInv1.getListeCarte().size());
        // La défausse est vide
        assertEquals(0, paquetDefausse.size());
        // Le joueur 1 a le bonus 7ème carte et va donc jouer sa 7ème carte qui est le chantier
        actionDeJeu.jouerLa7emeCarte(3, plateau);
        // La carte chantier est ajouté a son inventaire
        assertEquals("Chantier", setInv1.getListeCarte().get(0).toString());
        // Les autres joueur n'ont pas le bonus alors leur carte vont dans la défausse (on a lancé a 3 joueur donc 2 carte dans la défausse)
        assertEquals(2, paquetDefausse.size());


        setInv1.clear();
        setInv2.clear();
        setInv3.clear();
        setInv1.increaseValue(EnumRessources.BONUS7CARTEMAIN, 1);
        listSetInventaire = new ArrayList<>();
        listSetInventaire.add(setInv1);
        listSetInventaire.add(setInv2);
        listSetInventaire.add(setInv3);

        main = new MainJoueur();
        main.add(jardins);

        mainJoueurs = new ArrayList<>();
        mainJoueurs.add(main);
        mainJoueurs.add(main);
        mainJoueurs.add(main);
        paquetDefausse = new ArrayList<>();
        actionDeJeu = new ActionDeJeu(listSetInventaire, mainJoueurs, paquetDefausse);

        // Au départ la défausse est vide
        assertEquals(0, paquetDefausse.size());
        // Au départ le joueur a 3 pièce
        assertEquals(3, setInv1.getValue(EnumRessources.PIECE));
        // Le joueur a le bonus 7ème carte mais il ne peut pas construire cette 7ème carte, alors il l'a défausse et obtient 3 pièces
        actionDeJeu.jouerLa7emeCarte(3, plateau);
        assertEquals(6, setInv1.getValue(EnumRessources.PIECE));
        // A la fin la défausse contient 3 cartes
        assertEquals(3, paquetDefausse.size());
    }


    /**
     * Test de la méthode jouerLaDefausse()
     * On verifie si on fait bien jouer une carte de la défausse au joueur ayant ce bonus
     */
    @Test
    public void jouerLaDefausseTest() throws NegativeNumberException {
        ArrayList<SetInventaire> listSetInventaire;

        setInv1.increaseValue(EnumRessources.BONUSDEFAUSSEG, 1);
        listSetInventaire = new ArrayList<>(){{add(setInv1);add(setInv2);add(setInv3);}};
        actionDeJeu.inv = listSetInventaire;
        actionDeJeu.jouerLaDefausse(nbJoueurs, plateau);
        for (SetInventaire set : actionDeJeu.inv) {
            assertEquals(0 , set.getValue(EnumRessources.BONUSDEFAUSSEG));
        }


        setInv1.increaseValue(EnumRessources.BONUSDEFAUSSEG1, 1);
        listSetInventaire = new ArrayList<>(){{add(setInv1);add(setInv2);add(setInv3);}};
        actionDeJeu.inv = listSetInventaire;
        actionDeJeu.jouerLaDefausse(nbJoueurs, plateau);
        for (SetInventaire set : actionDeJeu.inv) {
            assertEquals(0, set.getValue(EnumRessources.BONUSDEFAUSSEG1));
        }


        setInv1.increaseValue(EnumRessources.BONUSDEFAUSSEG2, 1);
        listSetInventaire = new ArrayList<>(){{add(setInv1);add(setInv2);add(setInv3);}};
        actionDeJeu.inv = listSetInventaire;
        actionDeJeu.jouerLaDefausse(nbJoueurs, plateau);
        for (SetInventaire set : actionDeJeu.inv) {
            assertEquals(0, set.getValue(EnumRessources.BONUSDEFAUSSEG2));
        }
    }


    /**
     * Test de la méthode decisionDeJeu()
     */
    @Test
    public void decisionDeJeuTest() throws NegativeNumberException {
        setInv1.modifMerveille(babylon);
        setInv2.modifMerveille(babylon);
        setInv3.modifMerveille(babylon);
        listeInventaire = new ArrayList<>();
        listeInventaire.add(setInv1);
        listeInventaire.add(setInv2);
        listeInventaire.add(setInv3);

        plateau = new Plateau(listeInventaire, listeJoueur);

        listSetInventaire = new ArrayList<>();
        listSetInventaire.add(setInv1);
        listSetInventaire.add(setInv2);
        listSetInventaire.add(setInv3);

        MainJoueur main;
        main = new MainJoueur();
        main.add(chantier);

        mainJoueurs = new ArrayList<>();
        mainJoueurs.add(main);
        mainJoueurs.add(main);
        mainJoueurs.add(main);

        actionDeJeu = new ActionDeJeu(listSetInventaire, mainJoueurs, paquetDefausse);

        int[][] tab = actionDeJeu.decisionDeJeu(3, plateau);
        assertEquals(3, tab.length);
    }
}
