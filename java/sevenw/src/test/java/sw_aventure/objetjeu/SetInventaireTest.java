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
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class SetInventaireTest {

    private SetInventaire set1, set2, set3, set4, set5, set6;
    private Carte carte1, carte2, carte3, carte4, carte5;
    private Joueur joueur1, joueur2, joueur3,joueur4;
    private Plateau plateau;
    private ArrayList<Inventaire> listInventaire;
    private ArrayList<Joueur> listJoueur;

    private ArrayList<Carte> etape;
    private Merveille babylon, olympia, rhodos, gizah, ephesos, alexandria, halikarnassos;

    /**
     * Preparation des tests de la classe SetInventaire
     */
    @Before
    public void setUp() {
        set1 = new SetInventaire(1, Strategy.RANDOM, "j1");
        set2 = new SetInventaire(2, Strategy.RANDOM, "j2");
        set3 = new SetInventaire(3, Strategy.RANDOM, "j3");
        set4 = new SetInventaire(4, Strategy.RANDOM, "j4");
        set5 = new SetInventaire(5, Strategy.RANDOM, "j5");
        set6 = new SetInventaire(6, Strategy.RANDOM, "j6");


        joueur1 = set1.getJoueur();
        joueur2 = set2.getJoueur();
        joueur3 = set3.getJoueur();
        joueur4 = set6.getJoueur();

        carte1 = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS),3,1, EnumRessources.MARRON);
        carte2 = new Carte(EnumCarte.V3, Collections.singletonList(EnumRessources.TISSU), Collections.singletonList(EnumRessources.COMPAS), 3, 1, EnumRessources.VERTE);
        carte3 = new Carte(EnumCarte.V2, Collections.singletonList(EnumRessources.VERRE), Collections.singletonList(EnumRessources.ROUE), 3, 1, EnumRessources.GRISE);
        carte4 = new Carte(EnumCarte.V1, Collections.singletonList(EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.PDR), 3, 1, EnumRessources.ROUGE);
        carte5 = new Carte(EnumCarte.J4, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE, EnumRessources.PIECE), 5, 1, EnumRessources.JAUNE);

        listInventaire = new ArrayList<>();
        listJoueur = new ArrayList<>();

        etape = new ArrayList<>();
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI,EnumRessources.MINERAI,EnumRessources.TISSU), Collections.singletonList(EnumRessources.BONUSCPR)));
        etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE)));
        babylon = new Merveille(Wonder.BABYLON,EnumRessources.BOIS, etape, set1.getJoueur());
        olympia = new Merveille(Wonder.OLYMPIA,EnumRessources.ARGILE, etape, set6.getJoueur());
        rhodos = new Merveille(Wonder.RHODOS,EnumRessources.MINERAI, etape, set6.getJoueur());
        gizah = new Merveille(Wonder.GIZAH,EnumRessources.PIERRE, etape, set6.getJoueur());
        ephesos = new Merveille(Wonder.EPHESOS,EnumRessources.PAPYRUS, etape, set6.getJoueur());
        alexandria = new Merveille(Wonder.ALEXANDRIA,EnumRessources.VERRE, etape, set6.getJoueur());
        halikarnassos = new Merveille(Wonder.HALIKARNASSOS,EnumRessources.TISSU, etape, set6.getJoueur());

    }


    /**
     * Test de la méthode increaseValue()
     * On recupere les valeurs des arguments de l'inventaire du joueur
     * On les incremente avec la méthode increaseValue() et regarde si l'implementation s est bien faite
    */
    @Test
    public void increaseValue() throws NegativeNumberException {
        assertEquals(3 , set1.getValue(EnumRessources.PIECE));
        assertNotEquals(0, set1.getValue(EnumRessources.PIECE));
        assertEquals(0 , set2.getValue(EnumRessources.PIERRE));
        assertEquals(0, set3.getValue(EnumRessources.COMPAS));
        assertEquals(0, set4.getValue(EnumRessources.BOUCLIER));
        assertEquals(0, set5.getValue(EnumRessources.VERRE));

        set1.increaseValue(EnumRessources.PIECE, 3);
        set2.increaseValue(EnumRessources.PIERRE, 5);
        set3.increaseValue(EnumRessources.COMPAS, 2);
        set4.increaseValue(EnumRessources.BOUCLIER, 10);
        set5.increaseValue(EnumRessources.VERRE, 3);

        assertEquals(6, set1.getValue(EnumRessources.PIECE));
        assertNotEquals(3, set1.getValue(EnumRessources.PIECE));
        assertEquals(5, set2.getValue(EnumRessources.PIERRE));
        assertEquals(2, set3.getValue(EnumRessources.COMPAS));
        assertEquals(10, set4.getValue(EnumRessources.BOUCLIER));
        assertEquals(3, set5.getValue(EnumRessources.VERRE));
    }


    /**
     * Test de la méthode decreaseValue()
     * On recupere les valeurs des arguments de l'inventaire du joueur
     * On les decremente avec la méthode decreaseValue() et regarde si l'implementation s est bien faite
    */
    @Test
    public void decreaseValue() {
        assertEquals(3 , set1.getValue(EnumRessources.PIECE));
        assertNotEquals(0 , set1.getValue(EnumRessources.PIECE));
        assertEquals(3 , set2.getValue(EnumRessources.PIECE));
        assertEquals(3 , set3.getValue(EnumRessources.PIECE));
        assertEquals(3 , set4.getValue(EnumRessources.PIECE));
        assertEquals(3 , set5.getValue(EnumRessources.PIECE));

        set1.decreaseValue(EnumRessources.PIECE, 3);
        set2.decreaseValue(EnumRessources.PIECE, 2);
        set3.decreaseValue(EnumRessources.PIECE, 1);
        set4.decreaseValue(EnumRessources.PIECE, 0);
        set5.decreaseValue(EnumRessources.PIECE, 1);

        assertEquals(0,set1.getValue(EnumRessources.PIECE));
        assertEquals(1,set2.getValue(EnumRessources.PIECE));
        assertEquals(2,set3.getValue(EnumRessources.PIECE));
        assertEquals(3,set4.getValue(EnumRessources.PIECE));
        assertEquals(2,set5.getValue(EnumRessources.PIECE));

        assertFalse(set1.decreaseValue(EnumRessources.PIECE, 2));
        assertFalse(set2.decreaseValue(EnumRessources.PIECE, 2));
        assertFalse(set3.decreaseValue(EnumRessources.PIECE, 3));
        assertFalse(set4.decreaseValue(EnumRessources.PIECE, 4));
        assertFalse(set5.decreaseValue(EnumRessources.PIECE, 3));
    }


    /**
     * Test de la méthode casDefausse()
     * On recupere les valeurs des arguments de l'inventaire du joueur
     * La méthode incrémente 3 pièce a l'inventaire du joueur et on verifie si l incrementation s est bien faite
    */
    @Test
    public void casDefausse() throws NegativeNumberException{
        assertEquals(3,set1.getValue(EnumRessources.PIECE));
        assertNotEquals(0,set1.getValue(EnumRessources.PIECE));
        assertEquals(3,set2.getValue(EnumRessources.PIECE));
        assertEquals(3,set3.getValue(EnumRessources.PIECE));
        assertEquals(3,set4.getValue(EnumRessources.PIECE));
        assertEquals(3,set5.getValue(EnumRessources.PIECE));

        int acc = 6;
        for (int i = 0; i < 100; i++) {
            set1.casDefausse();
            set2.casDefausse();
            set3.casDefausse();
            set4.casDefausse();
            set5.casDefausse();

            assertEquals(set1.getValue(EnumRessources.PIECE), acc);
            assertEquals(set2.getValue(EnumRessources.PIECE), acc);
            assertEquals(set3.getValue(EnumRessources.PIECE), acc);
            assertEquals(set4.getValue(EnumRessources.PIECE), acc);
            assertEquals(set5.getValue(EnumRessources.PIECE), acc);
            acc += 3;
        }
    }


    /**
     * Test de la méthode afficheChoixCarte()
     * On vérifie tout les cas possible des couleurs des cartes.
     * On teste pour une cartes marron, grise, bleue, jaune, rouge, verte ou violette.
    */
    @Test
    public void afficheChoixCarte() {
        Carte carte1 = Mockito.spy(new Carte(EnumCarte.MERVEILLE, Collections.emptyList(), Collections.emptyList(), 3, 1, EnumRessources.MARRON));
        Carte carte2 = Mockito.spy(new Carte(EnumCarte.MERVEILLE, Collections.emptyList(), Collections.emptyList(), 3, 1, EnumRessources.GRISE));
        Carte carte3 = Mockito.spy(new Carte(EnumCarte.MERVEILLE, Collections.emptyList(), Collections.emptyList(), 3, 1, EnumRessources.BLEUE));
        Carte carte4 = Mockito.spy(new Carte(EnumCarte.MERVEILLE, Collections.emptyList(), Collections.emptyList(), 3, 1, EnumRessources.JAUNE));
        Carte carte5 = Mockito.spy(new Carte(EnumCarte.MERVEILLE, Collections.emptyList(), Collections.emptyList(), 3, 1, EnumRessources.ROUGE));
        Carte carte6 = Mockito.spy(new Carte(EnumCarte.MERVEILLE, Collections.emptyList(), Collections.emptyList(), 3, 1, EnumRessources.VERTE));
        Carte carte7 = Mockito.spy(new Carte(EnumCarte.MERVEILLE, Collections.emptyList(), Collections.emptyList(), 3, 1, EnumRessources.VIOLETTE));

        set1.afficheChoixCarte(carte1);
        set1.afficheChoixCarte(carte2);
        set1.afficheChoixCarte(carte3);
        set1.afficheChoixCarte(carte4);
        set1.afficheChoixCarte(carte5);
        set1.afficheChoixCarte(carte6);
        set1.afficheChoixCarte(carte7);

        Mockito.verify(carte1).getNom();
        Mockito.verify(carte2).getNom();
        Mockito.verify(carte3).getNom();
        Mockito.verify(carte4).getNom();
        Mockito.verify(carte5).getNom();
        Mockito.verify(carte6).getNom();
        Mockito.verify(carte7).getNom();
    }


    /**
     * Test de la méthode ressourceMerveille()
     * On verifie que le score est bien incrementé en fonction de la ressource pour la merveille attribué
     *
     */
    @Test
    public void ressourceMerveilleTest() throws NegativeNumberException {
        Carte carte;
        ArrayList<Carte> defausse = new ArrayList<>();
        defausse.add(carte1);
        defausse.add(carte2);

        assertEquals(0, set1.getValue(EnumRessources.BOIS));
        set1.ressourceMerveille(carte1, defausse);
        assertEquals(1, set1.getValue(EnumRessources.BOIS));

        carte = new Carte(EnumCarte.M6 , Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG1),3,1, EnumRessources.MARRON);
        set1.clear();
        assertEquals(0, set1.getValue(EnumRessources.SCORE));
        set1.ressourceMerveille(carte, defausse);
        assertEquals(1, set1.getValue(EnumRessources.SCORE));

        carte = new Carte(EnumCarte.M6 , Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BONUSDEFAUSSEG2),3,1, EnumRessources.MARRON);
        set1.clear();
        assertEquals(0, set1.getValue(EnumRessources.SCORE));
        set1.ressourceMerveille(carte, defausse);
        assertEquals(2, set1.getValue(EnumRessources.SCORE));

        carte = new Carte(EnumCarte.M6 , Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BONUSCARTEAGEG2P),3,1, EnumRessources.MARRON);
        set1.clear();
        assertEquals(0, set1.getValue(EnumRessources.SCORE));
        set1.ressourceMerveille(carte, defausse);
        assertEquals(2 ,set1.getValue(EnumRessources.SCORE));

        carte = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BONUSCARTEAGEG3P),3,1, EnumRessources.MARRON);
        set1.clear();
        assertEquals(0,set1.getValue(EnumRessources.SCORE));
        set1.ressourceMerveille(carte, defausse);
        assertEquals(3,set1.getValue(EnumRessources.SCORE));
    }


    /**
     * Test de la méthode jouerCarteDefausse()
     * On verifie la taille de la defausse qu il doit augmenter de 1 apres l appel de cette methode
     * On ajoute 2 cartes et en defausse 1 en utilisant cette methode
     * Donc on doit avoir une taille de defausse de 1 ici
     */
    @Test
    public void jouerCarteDefausseTest() throws NegativeNumberException{
        listInventaire.add(set1);
        listInventaire.add(set2);
        listInventaire.add(set3);

        listJoueur.add(joueur1);
        listJoueur.add(joueur2);
        listJoueur.add(joueur3);

        plateau = new Plateau(listInventaire, listJoueur);

        ArrayList<Carte> defausse = new ArrayList<>();

        defausse.add(carte1);
        defausse.add(carte2);

        assertEquals(2, defausse.size());
        set1.jouerCarteDefausse(defausse, plateau);
        assertEquals(1, defausse.size());
    }


    /**
     * Test de la méthode ressourceCarte()
     * On regarde si les ressource de la carte ont bien modifiees les inventaires des joueurs associes
     * */
    @Test
    public void ressourceCarte() throws NegativeNumberException {
        set1.increaseValue(EnumRessources.GRISE, 2);

        listInventaire.add(set1);
        listInventaire.add(set2);
        listInventaire.add(set3);

        listJoueur.add(joueur1);
        listJoueur.add(joueur2);
        listJoueur.add(joueur3);

        plateau = new Plateau(listInventaire, listJoueur);

        set1.ressourceCarte(carte1, plateau);
        set2.ressourceCarte(carte2, plateau);
        set3.ressourceCarte(carte3, plateau);
        set4.ressourceCarte(carte4, plateau);
        set5.ressourceCarte(carte5, plateau);

        assertEquals(1, set1.getValue(EnumRessources.BOIS));
        assertEquals(1 , set2.getValue(EnumRessources.COMPAS));
        assertEquals(1 , set3.getValue(EnumRessources.ROUE));
        assertEquals(1 , set4.getValue(EnumRessources.PDR));
        assertEquals(8 , set5.getValue(EnumRessources.PIECE));             // Le joueur a deja 3 pièces au départ + les 5 de la carte

        Carte carte = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BONUS22G),3,1, EnumRessources.MARRON);
        Carte cartebis = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.PIECE, EnumRessources.PIERRE),3,1, EnumRessources.VIOLETTE);

        set1.ressourceCarte(carte, plateau);
        assertEquals(7, set1.getValue(EnumRessources.PIECE));

        set1.ressourceCarte(cartebis, plateau);
        assertEquals(3, set1.getValue(EnumRessources.BOIS));
        assertEquals(1 ,set1.getValue(EnumRessources.PIERRE));
        assertEquals(8 ,set1.getValue(EnumRessources.PIECE));
    }


    /**
     * Test de la méthode piecebonus()
     * On verifie les scores obtenu pour chaque bonus
     *
     */
    @Test
    public void piecebonusTest() throws NegativeNumberException {
        babylon.incrementeStade();
        babylon.incrementeStade();
        set1.modifMerveille(babylon);
        set1.increaseValue(EnumRessources.MARRON, 2);
        set1.increaseValue(EnumRessources.JAUNE, 4);
        set1.increaseValue(EnumRessources.GRISE, 1);
        set1.increaseValue(EnumRessources.ROUGE, 10);

        set2.increaseValue(EnumRessources.MARRON, 3);
        set3.increaseValue(EnumRessources.MARRON, 4);

        set2.increaseValue(EnumRessources.GRISE, 2);

        listInventaire.add(set1);
        listInventaire.add(set2);
        listInventaire.add(set3);

        listJoueur.add(joueur1);
        listJoueur.add(joueur2);
        listJoueur.add(joueur3);

        plateau = new Plateau(listInventaire, listJoueur);

        // Au départ le joueur a 3 pièces
        assertEquals(3, set1.getValue(EnumRessources.PIECE));

        // Le joueur obtient 2 pièces supplémentaire grâce à ses carte marron
        set1.piecebonus(EnumRessources.BONUS11M, plateau);
        assertEquals(5 ,set1.getValue(EnumRessources.PIECE));

        // Le joueur obtient 4 pièces supplémentaire grâce à ses carte jaune
        set1.piecebonus(EnumRessources.BONUS11J, plateau);
        assertEquals(9, set1.getValue(EnumRessources.PIECE));

        // Le joueur obtient 2 pièces supplémentaire grâce à sa carte grise (2 pièce par carte)
        set1.piecebonus(EnumRessources.BONUS22G, plateau);
        assertEquals(11 , set1.getValue(EnumRessources.PIECE));

        // Le joueur obtient 30 pièces supplémentaire grâce à ses carte rouge (3 pièce par carte)
        set1.piecebonus(EnumRessources.BONUS31R, plateau);
        assertEquals(41, set1.getValue(EnumRessources.PIECE));

        // Le joueur obtient 6 pièces supplémentaire car il a construit 2 étage de sa merveille (3 pièces par étage)
        set1.piecebonus(EnumRessources.BONUS31MERVEILLE, plateau);
        assertEquals(47 , set1.getValue(EnumRessources.PIECE));

        // Le joueur obtient 9 pièces car il a 2 carte marron, son voisin droit 3 carte marron, et son voisin gauche 4 carte marron
        set1.piecebonus(EnumRessources.BONUS1MDJG, plateau);
        assertEquals(56 , set1.getValue(EnumRessources.PIECE));

        // Le joueur obtient 6 pièces car il a 1 carte grise, son voisin droit 2 carte grise, et son voisin gauche 0 carte grise
        set1.piecebonus(EnumRessources.BONUS2GDJG, plateau);
        assertEquals(62 , set1.getValue(EnumRessources.PIECE));
    }


    /**
     * Test de la méthode ajoutCarteInv
     * On ajoute des cartes dans des inventaires
     * On verifie si les cartes se sont contenues dans ces inventaires avec la methode contains()
     */
    @Test
    public void ajoutCarteInvTest() {
        set1.ajoutCarteInv(carte1);
        set1.ajoutCarteInv(carte2);
        assertTrue(set1.getListeCarte().contains(carte1.getNom()));
        assertTrue(set1.getListeCarte().contains(carte2.getNom()));
        assertFalse(set1.getListeCarte().contains(carte3.getNom()));
        assertFalse(set1.getListeCarte().contains(carte4.getNom()));
    }




    /**
     * Test de la méthode modifMerveille()
     * On ajoute une merveille dans un inventaire en modifiant son contenu tout entier avec cette methode
     * Ensuite, on regarde si la merveille est bien ajoute avec la methode getMerveille()
     */
    @Test
    public void modifMerveille() {
        set1.modifMerveille(babylon);
        assertEquals(babylon, set1.getMerveille());
        set6.modifMerveille(olympia);
        assertEquals(olympia, set6.getMerveille());
        set6.modifMerveille(rhodos);
        assertEquals(rhodos, set6.getMerveille());
        set6.modifMerveille(ephesos);
        assertEquals(ephesos, set6.getMerveille());
        set6.modifMerveille(alexandria);
        assertEquals(alexandria, set6.getMerveille());
        set6.modifMerveille(gizah);
        assertEquals(gizah, set6.getMerveille());
        set6.modifMerveille(halikarnassos);
        assertEquals(halikarnassos, set6.getMerveille());



    }



    /**
     * Test de la méthode clear()
     * On initialise les inventaires des joueurs avec leurs valeurs initiales: 0 par tout et 3 pour les pieces
     * On verifie si ces variables ont pour valeur 0 et sauf les pieces.
    */
    @Test
    public void clear() {
        ArrayList<SetInventaire> set = new ArrayList<>() {{add(set1); add(set2); add(set3); add(set4); add(set5);}};

        for (SetInventaire s : set) {
            s.clear();
/*
            // ACTIONS DANS LE JEU
            assertEquals(0 , s.getValue("Jouer la première carte Age de chaque couleur"));
            assertEquals(0 , s.getValue("Jouer une carte de la défausse gratuitement"));
            assertEquals(0 , s.getValue("Jouer une carte de la défausse gratuitement + 1 point"));
            assertEquals(0 , s.getValue("Jouer une carte de la défausse gratuitement + 2 points"));
            assertEquals(0, s.getValue("Construire gratuitement la première carte de l'Age + 2 points de Score"));
            assertEquals(0 , s.getValue("Construire gratuitement la dernière carte de l'Age + 3 points de Score"));
            assertEquals(0 , s.getValue("Jouer la 7ème carte de la main"));
            assertEquals(0,s.getValue("Stade"));
*/
            // RESSOURCES DE BASE
            assertEquals(0, s.getValue(EnumRessources.SCORE));
            assertNotEquals(0 , s.getValue(EnumRessources.PIECE));
            assertEquals(3 , s.getValue(EnumRessources.PIECE));
            assertEquals(0, s.getValue(EnumRessources.BOIS));
            assertEquals(0, s.getValue(EnumRessources.PIERRE));
            assertEquals(0, s.getValue(EnumRessources.ARGILE));
            assertEquals(0, s.getValue(EnumRessources.MINERAI));
            assertEquals(0, s.getValue(EnumRessources.TISSU));
            assertEquals(0, s.getValue(EnumRessources.VERRE));
            assertEquals(0, s.getValue(EnumRessources.PAPYRUS));
            assertEquals(0, s.getValue(EnumRessources.BOUCLIER));
            assertEquals(0, s.getValue(EnumRessources.COMPAS));
            assertEquals(0, s.getValue(EnumRessources.ROUE));
            assertEquals(0, s.getValue(EnumRessources.PDR));
/*
            // RESSOURCE DE GUERRE
            assertEquals(0, s.getValue("Pts de Victoire"));
            assertEquals(0, s.getValue("Pts de Défaite"));

            // BONUS CARTE JAUNE AGE 1
            assertEquals(0, s.getValue("ReducMarronDroite"));
            assertEquals(0, s.getValue("ReducMarronGauche"));
            assertEquals(0, s.getValue("ReducGrisDroiteGauche"));

            // BONUS CARTE MARRON AGE 1
            assertEquals(0, s.getValue("Pierre/Argile"));
            assertEquals(0, s.getValue("Bois/Pierre"));
            assertEquals(0, s.getValue("Argile/Minerai"));
            assertEquals(0, s.getValue("Bois/Minerai"));
            assertEquals(0, s.getValue("Bois/Argile"));
            assertEquals(0, s.getValue("Pierre/Minerai"));

            // BONUS CARTE JAUNE AGE 2
            assertEquals(0, s.getValue("Bois/Pierre/Argile/Minerai"));
            assertEquals(0, s.getValue("Verre/Papyrus/Tissu"));
            assertEquals(0, s.getValue("1 pièce pour chaque carte de couleur marron à droite joueur et gauche"));
            assertEquals(0, s.getValue("2 pièces pour chaque carte de couleur grise à droite joueur et gauche"));

            // BONUS CARTE JAUNE AGE 3
            assertEquals(0, s.getValue("1 pièce et 1 score (en fin de partie) pour chaque carte jaune du joueur"));
            assertEquals(0, s.getValue("1 pièce et 1 score (en fin de partie) pour chaque carte marron du joueur"));
            assertEquals(0, s.getValue("2 pièces et 2 scores (en fin de partie) pour chaque carte grise du joueur"));
            assertEquals(0, s.getValue("3 pièces et 1 score (en fin de partie) pour chaque carte rouge du joueur"));
            assertEquals(0, s.getValue("3 pièces et 1 score (en fin de partie) pour chaque étage de la merveille du joueur"));

            // BONUS CARTE VIOLETTE
            assertEquals(0, s.getValue("1 score en fin de partie pour chaque carte de couleur marron à droite et gauche"));
            assertEquals(0, s.getValue("2 scores en fin de partie pour chaque carte de couleur grise à droite et gauche"));
            assertEquals(0, s.getValue("1 score en fin de partie pour chaque carte de couleur bleue à droite et gauche"));
            assertEquals(0, s.getValue("1 score en fin de partie pour chaque carte de couleur jaune à droite et gauche"));
            assertEquals(0, s.getValue("1 score en fin de partie pour chaque carte de couleur rouge à droite et gauche"));
            assertEquals(0, s.getValue("1 score en fin de partie pour chaque carte de couleur verte à droite et gauche"));
            assertEquals(0, s.getValue("1 score en fin de partie pour chaque carte de couleur marron, grise et violette du joueur"));
            assertEquals(0, s.getValue("1 score en fin de partie pour chaque étage de merveille à droite joueur et gauche"));
            assertEquals(0, s.getValue("7 scores en fin de partie si la merveille est terminée"));
            */
            assertEquals(0, s.getValue(EnumRessources.BONUSCPR));


            // COULEURS de CARTES
            assertEquals(0, s.getValue(EnumRessources.MARRON));
            assertEquals(0, s.getValue(EnumRessources.GRISE));
            assertEquals(0, s.getValue(EnumRessources.BLEUE));
            assertEquals(0, s.getValue(EnumRessources.JAUNE));
            assertEquals(0, s.getValue(EnumRessources.ROUGE));
            assertEquals(0, s.getValue(EnumRessources.VERTE));
            assertEquals(0, s.getValue(EnumRessources.VIOLETTE));


        }
    }
}

