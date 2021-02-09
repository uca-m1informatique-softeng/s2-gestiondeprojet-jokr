package utilitaire_jeu;

import objet_commun.Carte;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import joueur.Joueur;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


public class ConstructionTest {

    private Construction construction;

    private SetInventaire setInventaireCourant;
    private SetInventaire setInventaireGauche;
    private SetInventaire setInventaireDroit;

    private Joueur joueurCourant;
    private Joueur joueurDroit;
    private Joueur joueurGauche;

    private Plateau plateau;

    // Carte
    private Carte carteChantier, carteCavite, carteVerrerie, cartePuit, carteStatue, carteEcole;

    // Carte Merveille
    private Carte carteB1;



    /**
     * Preparation des tests de la classe Construction
     */
    @Before
    public void setUp() {
        construction = new Construction();

        setInventaireCourant = new SetInventaire(1, Strategy.RANDOM,"Melanie");
        setInventaireGauche = new SetInventaire(3,Strategy.RANDOM,"Davy");
        setInventaireDroit = new SetInventaire(2,Strategy.RANDOM,"Vincent");

        joueurCourant = setInventaireCourant.getJoueur();
        joueurGauche = setInventaireGauche.getJoueur();
        joueurDroit = setInventaireDroit.getJoueur();

        ArrayList<Inventaire> listeInventaire = new ArrayList<>(){{add(setInventaireCourant);add(setInventaireDroit);add(setInventaireGauche);}};
        ArrayList<Joueur> listeJoueur = new ArrayList<>(){{add(joueurCourant);add(joueurDroit);add(joueurGauche);}};
        plateau = new Plateau(listeInventaire, listeJoueur);

        carteB1 = new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE,EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE));

        carteChantier = new Carte(EnumCarte.M6, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.BOIS),3,1,EnumRessources.MARRON);

        carteCavite = new Carte(EnumCarte.M5, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.PIERRE), 3, 1, EnumRessources.MARRON);
        carteVerrerie = new Carte(EnumCarte.G2, Collections.singletonList(EnumRessources.GRATUIT), Collections.singletonList(EnumRessources.VERRE), 3, 1, EnumRessources.GRISE);
        cartePuit = new Carte(EnumCarte.B13, Collections.singletonList(EnumRessources.GRATUIT), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 4, 1, EnumRessources.BLEUE);
        carteStatue = new Carte(EnumCarte.B7, Arrays.asList(EnumRessources.BOIS, EnumRessources.MINERAI, EnumRessources.MINERAI), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE), 3, 2, EnumRessources.BLEUE);
        carteEcole = new Carte(EnumCarte.V4, Arrays.asList(EnumRessources.BOIS, EnumRessources.PAPYRUS), Collections.singletonList(EnumRessources.PDR), 3, 2, EnumRessources.VERTE);
    }


    /**
     * Test de la méthode permisDeConstruction(Inventaire)
     * On cree des cas permettant pour verifier si il peut construire ou non
     * On reinitialise l inventaire pour chaque test avec la methode clear()
     */
    @Test
    public void permisDeConstructionInventaireTest() throws NegativeNumberException {
        setInventaireCourant.increaseValue(EnumRessources.BOIS, 1);
        setInventaireCourant.increaseValue(EnumRessources.MINERAI, 2);
        assertTrue(construction.permisDeConstruction(carteChantier, (Inventaire) setInventaireCourant, (Inventaire) setInventaireGauche, (Inventaire) setInventaireDroit, plateau));

        setInventaireCourant.clear();
        setInventaireDroit.increaseValue(EnumRessources.BOIS, 1);
        setInventaireGauche.increaseValue(EnumRessources.MINERAI, 2);
        assertTrue(construction.permisDeConstruction(carteChantier, (Inventaire) setInventaireCourant, (Inventaire) setInventaireGauche, (Inventaire) setInventaireDroit, plateau));

        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.MINERAI, 1);
        setInventaireDroit.increaseValue(EnumRessources.BOIS, 1);
        setInventaireGauche.increaseValue(EnumRessources.MINERAI, 1);
        assertTrue(construction.permisDeConstruction(carteChantier, (Inventaire) setInventaireCourant, (Inventaire) setInventaireGauche, (Inventaire) setInventaireDroit, plateau));

        setInventaireCourant.clear();
        setInventaireCourant.ajoutCarteInv(carteChantier);
        assertFalse(construction.permisDeConstruction(carteChantier, (Inventaire) setInventaireCourant, (Inventaire) setInventaireGauche, (Inventaire) setInventaireDroit, plateau));
    }


    /**
     * Test de la méthode permisDeConstruction(SetInventaire)
     */
    @Test
    public void permisDeConstructionSetInventaireTest() throws NegativeNumberException {
        setInventaireCourant.increaseValue(EnumRessources.BOIS, 1);
        setInventaireCourant.increaseValue(EnumRessources.MINERAI, 2);
        assertTrue(construction.permisDeConstruction(carteChantier, setInventaireCourant, setInventaireGauche, setInventaireDroit, plateau));

        setInventaireCourant.clear();
        setInventaireDroit.increaseValue(EnumRessources.BOIS, 1);
        setInventaireGauche.increaseValue(EnumRessources.MINERAI, 2);
        assertTrue(construction.permisDeConstruction(carteChantier, setInventaireCourant, setInventaireGauche, setInventaireDroit, plateau));

        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.MINERAI, 1);
        setInventaireDroit.increaseValue(EnumRessources.BOIS, 1);
        setInventaireGauche.increaseValue(EnumRessources.MINERAI, 1);
        assertTrue(construction.permisDeConstruction(carteChantier, setInventaireCourant, setInventaireGauche, setInventaireDroit, plateau));

        setInventaireCourant.clear();
        setInventaireCourant.ajoutCarteInv(carteChantier);
        assertFalse(construction.permisDeConstruction(carteChantier, setInventaireCourant, setInventaireGauche, setInventaireDroit, plateau));
    }


    /**
     * Test de la méthode laConstruction()
     */
    @Test
    public void laConstructionTest() throws NegativeNumberException {
        // Via doublon
        setInventaireCourant.ajoutCarteInv(carteChantier);
        assertEquals(0, construction.laConstruction(carteChantier, (Inventaire)setInventaireCourant, plateau, false).size());

        // Via chaînage
        setInventaireCourant.clear();
        setInventaireCourant.ajoutCarteInv(cartePuit);
        assertEquals(0, construction.laConstruction(carteStatue, (Inventaire)setInventaireCourant, plateau, false).size());

        // Via gratuit
        setInventaireCourant.clear();
        assertEquals(0, construction.laConstruction(carteChantier, (Inventaire)setInventaireCourant, plateau, false).size());

        // Via bonus couleur
        setInventaireCourant.clear();
        setInventaireCourant.listeCarte = new ArrayList<>();
        System.out.println(setInventaireCourant.getValue(EnumRessources.BLEUE));
        setInventaireCourant.increaseValue(EnumRessources.BONUSAGECOULEUR, 1);
        assertEquals(0, construction.laConstruction(carteStatue, (Inventaire)setInventaireCourant, plateau, false).size());

        // Via bonus tour
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.BONUSCARTEAGEG2P, 1);
        plateau.incrementeTour();
        assertEquals(0, construction.laConstruction(carteStatue, (Inventaire)setInventaireCourant, plateau, false).size());

        // Via Inventaire (sans MultiChoix)
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.BOIS, 1);
        setInventaireCourant.increaseValue(EnumRessources.MINERAI, 2);
        assertEquals(0, construction.laConstruction(carteChantier, (Inventaire)setInventaireCourant, plateau, false).size());

        // Via Inventaire avec MultiChoix
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.MULTIBA, 1);
        setInventaireCourant.increaseValue(EnumRessources.MULTIBPAM, 2);
        assertEquals(0, construction.laConstruction(carteChantier, (Inventaire)setInventaireCourant, plateau, false).size());
    }


    /**
     * Test de la méthode laConstructionViaDoublons()
     */
    @Test
    public void laConstructionViaDoublonsTest() {
        setInventaireCourant.ajoutCarteInv(carteChantier);
        assertTrue(construction.laConstructionViaDoublons(carteChantier, setInventaireCourant, false));
        assertFalse(construction.laConstructionViaDoublons(carteVerrerie, setInventaireCourant, false));
    }


    /**
     * Test de la méthode laConstructionViaChainee()
     * La carte Statue est gratuite si le joueur possède la carte Puits
     */
    @Test
    public void laConstructionViaChaineeTest() {
        setInventaireCourant.ajoutCarteInv(cartePuit);

        assertTrue(construction.laConstructionViaChainee(carteStatue, setInventaireCourant, false));
        assertFalse(construction.laConstructionViaChainee(cartePuit, setInventaireCourant, false));
        assertFalse(construction.laConstructionViaChainee(carteChantier, setInventaireCourant, false));
        assertFalse(construction.laConstructionViaChainee(carteVerrerie, setInventaireCourant, false));
    }


    /**
     * Test de la méthode laConstructionViaGratuit()
     * La carte chantier est gratuite, mais la carte statue est payante
     */
    @Test
    public void laConstructionViaGratuitTest() {
        assertTrue(construction.laConstructionViaGratuit(carteChantier.getPrix(), false));
        assertFalse(construction.laConstructionViaGratuit(carteStatue.getPrix(), false));
    }


    /**
     * Test de la méthode laConstructionViaBonusCouleur()
     */
    @Test
    public void laConstructionViaBonusCouleurTest() throws NegativeNumberException {
        // Le joueur a pas encore le bonus couleur
        assertFalse(construction.laConstructionViaBonusCouleur(carteChantier, setInventaireCourant, false));

        // On donne le bonus couleur au joueur
        setInventaireCourant.increaseValue(EnumRessources.BONUSAGECOULEUR, 1);
        assertTrue(construction.laConstructionViaBonusCouleur(carteChantier, setInventaireCourant, false));
        assertFalse(construction.laConstructionViaBonusCouleur(carteB1, setInventaireCourant, false));

        setInventaireCourant.ajoutCarteInv(carteChantier);
        setInventaireCourant.increaseValue(carteChantier.getCouleur(), 1);
        assertFalse(construction.laConstructionViaBonusCouleur(carteCavite, setInventaireCourant, false));
    }


    /**
     * Test de la méthode laConstructionViaBonusTour()
     */
    @Test
    public void laConstructionViaBonusTourTest() throws NegativeNumberException {
        assertFalse(construction.laConstructionViaBonusTour(carteB1, setInventaireCourant, plateau, false));
        assertFalse(construction.laConstructionViaBonusTour(carteChantier, setInventaireCourant, plateau, false));

        plateau.incrementeTour();
        assertFalse(construction.laConstructionViaBonusTour(carteChantier, setInventaireCourant, plateau, false));

        setInventaireCourant.increaseValue(EnumRessources.BONUSCARTEAGEG2P, 1);
        assertTrue(construction.laConstructionViaBonusTour(carteChantier, setInventaireCourant, plateau, false));

        setInventaireCourant.decreaseValue( EnumRessources.BONUSCARTEAGEG2P, 1);
        assertFalse(construction.laConstructionViaBonusTour(carteChantier, setInventaireCourant, plateau, false));

        setInventaireCourant.increaseValue(EnumRessources.BONUSCARTEAGEG3P, 1);
        plateau.incrementeTour();
        plateau.incrementeTour();
        plateau.incrementeTour();
        plateau.incrementeTour();
        plateau.incrementeTour();
        assertTrue(construction.laConstructionViaBonusTour(carteChantier, setInventaireCourant, plateau, false));

        plateau.incrementeTour();
        assertFalse(construction.laConstructionViaBonusTour(carteChantier, setInventaireCourant, plateau, false));
    }


    /**
     * Test de la méthode laConstructionViaInventaire()
     */
    @Test
    public void laConstructionViaInventaireTest() throws NegativeNumberException {
        ArrayList<EnumRessources> arraylist;
        // Le joueur a aucune ressources. La liste retourné a 1 élément
        assertEquals(3, construction.laConstructionViaInventaire(carteStatue.getPrix(), setInventaireCourant, false).size());

        // Le joureur peut payer le bois, mais pas les deux minerais. La liste retourné a 2 élément
        setInventaireCourant.increaseValue(EnumRessources.BOIS, 1);
        arraylist = new ArrayList<>(carteStatue.getPrix());
        assertEquals(2 , construction.laConstructionViaInventaire(arraylist, setInventaireCourant, false).size());

        // Le joueur peut payer un bois et un minerai, mais pas le deuxième minerai. La liste retourné a 1 élément
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireCourant.increaseValue(EnumRessources.MINERAI, 1);
        assertEquals(1 ,construction.laConstructionViaInventaire(arraylist, setInventaireCourant, false).size());

        // Le joueur peut payer toute les ressources. La liste retourné est vide
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireCourant.increaseValue(EnumRessources.MINERAI, 1);
        assertEquals(0 , construction.laConstructionViaInventaire(arraylist, setInventaireCourant, false).size());
    }


    /**
     * Test de la méthode laConstructionViaMultichoix()
     */
    @Test
    public void laConstructionViaMultichoixTest() throws NegativeNumberException {
        ArrayList<EnumRessources> arraylist;

        // Le joueur a aucun multi choix, il ne peut pas payer
        arraylist = new ArrayList<>(carteStatue.getPrix());
        assertEquals(3 , construction.laConstructionViaMultichoix(arraylist, setInventaireCourant, false).size());

        // Le joueur paye le bois
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireCourant.increaseValue(EnumRessources.MULTIBP, 1);
        assertEquals(2 , construction.laConstructionViaMultichoix(arraylist, setInventaireCourant, false).size());

        // Le joueur paye le bois et 1 minerai
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.MULTIBP, 1);
        setInventaireCourant.increaseValue(EnumRessources.MULTIAM, 1);
        assertEquals(1 , construction.laConstructionViaMultichoix(arraylist, setInventaireCourant, false).size());

        // Le joueur peut payer avec ses multiChoix
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.MULTIBP, 1);
        setInventaireCourant.increaseValue(EnumRessources.MULTIAM, 1);
        setInventaireCourant.increaseValue(EnumRessources.MULTIPM, 1);
        assertEquals(0 , construction.laConstructionViaMultichoix(arraylist, setInventaireCourant, false).size());

        // Le joueur peut payer 1 ressource avec son multiChoix
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.MULTIBPAM, 1);
        assertEquals(2 , construction.laConstructionViaMultichoix(arraylist, setInventaireCourant, false).size());

        // Le joueur peut payer aucune ressources
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.MULTIPA, 1);
        assertEquals(3 , construction.laConstructionViaMultichoix(arraylist, setInventaireCourant, false).size());

        // Le joueur peut payer 1 ressources
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.MULTIBM, 1);
        assertEquals(2 , construction.laConstructionViaMultichoix(arraylist, setInventaireCourant, false).size());

        // Le joueur peut payer toutes les ressources
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.MULTIBA, 1);
        setInventaireCourant.increaseValue(EnumRessources.MULTIBPAM, 2);
        assertEquals(0 , construction.laConstructionViaMultichoix(arraylist, setInventaireCourant, false).size());

        // Le joueur peut payer toutes les ressources
        arraylist = new ArrayList<>(carteEcole.getPrix());
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.MULTIBA, 1);
        setInventaireCourant.increaseValue(EnumRessources.MULTIVPT, 2);
        assertEquals(0 , construction.laConstructionViaMultichoix(arraylist, setInventaireCourant, false).size());
    }


    /**
     * Test de la méthode viaAchat()
     */
    @Test
    public void viaAchatTest() throws NegativeNumberException {
        ArrayList<EnumRessources> arraylist;
        arraylist = new ArrayList<>(carteStatue.getPrix());
        assertFalse(construction.viaAchat(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit));


        setInventaireCourant.increaseValue(EnumRessources.REDMARRONDROITE, 1);
        setInventaireGauche.increaseValue(EnumRessources.BOIS, 1);
        setInventaireGauche.increaseValue(EnumRessources.MINERAI, 2);
        // Le joueur n'a pas assez de pièces pour payer aux autres joueur
        assertFalse(construction.viaAchat(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit));

        // Le joueur a assez de pièces
        setInventaireCourant.increaseValue(EnumRessources.PIECE, 9);
        assertTrue(construction.viaAchat(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit));

        // Le joueur a une réduction
        setInventaireCourant.increaseValue(EnumRessources.REDMARRONGAUCHE, 1);
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireCourant.decreaseValue(EnumRessources.PIECE, 12);
        assertFalse(construction.viaAchat(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit));
        setInventaireCourant.increaseValue(EnumRessources.PIECE, 9);
        assertTrue(construction.viaAchat(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit));


        arraylist = new ArrayList<>(carteEcole.getPrix());
        setInventaireCourant.clear();
        setInventaireGauche.clear();
        setInventaireDroit.clear();
        setInventaireCourant.increaseValue(EnumRessources.REDGRISDROITEGAUCHE, 1);
        assertFalse(construction.viaAchat(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit));

        setInventaireDroit.increaseValue(EnumRessources.BOIS, 1);
        setInventaireGauche.increaseValue(EnumRessources.PAPYRUS, 1);
        assertTrue(construction.viaAchat(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit));


        arraylist = new ArrayList<>(carteEcole.getPrix());
        setInventaireCourant.clear();
        setInventaireGauche.clear();
        setInventaireDroit.clear();
        setInventaireCourant.increaseValue(EnumRessources.REDGRISDROITEGAUCHE, 1);
        setInventaireCourant.increaseValue(EnumRessources.REDMARRONGAUCHE, 1);
        setInventaireDroit.increaseValue(EnumRessources.PAPYRUS, 1);
        setInventaireGauche.increaseValue(EnumRessources.BOIS, 1);
        setInventaireCourant.increaseValue(EnumRessources.PIECE, 5);
        assertTrue(construction.viaAchat(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit));
    }


    /**
     * Test de la méthode laConstructionViaCommerce()
     */
    @Test
    public void laConstructionViaCommerceTest() throws NegativeNumberException {
        ArrayList<EnumRessources> arraylist;
        arraylist = new ArrayList<>(carteStatue.getPrix());
        assertFalse(construction.laConstructionViaCommerce(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit, false));

        // On donne 47 pièce au joueur courant, il a donc 50 pièce
        setInventaireCourant.increaseValue(EnumRessources.PIECE, 47);
        assertEquals(50 , setInventaireCourant.getValue(EnumRessources.PIECE));


        // Le joueur achète 1 bois et 2 minerai a son voisin de droite. Il paye 6 pièces
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireDroit.increaseValue(EnumRessources.BOIS, 1);
        setInventaireDroit.increaseValue(EnumRessources.MINERAI, 2);
        assertTrue(construction.laConstructionViaCommerce(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit, false));
        assertEquals(9, setInventaireDroit.getValue(EnumRessources.PIECE));
        assertEquals(44 , setInventaireCourant.getValue(EnumRessources.PIECE));


        // Même test que précédemment mais le joueur dispose d'une réduction (grâce au comptoir Est)
        // Il paye donc que 3 pièces
        setInventaireDroit.clear();
        setInventaireDroit.increaseValue(EnumRessources.BOIS, 1);
        setInventaireDroit.increaseValue(EnumRessources.MINERAI, 2);
        setInventaireCourant.increaseValue(EnumRessources.REDMARRONDROITE, 1);
        arraylist = new ArrayList<>(carteStatue.getPrix());
        assertTrue(construction.laConstructionViaCommerce(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit, false));
        assertEquals(6 , setInventaireDroit.getValue(EnumRessources.PIECE));
        assertEquals(41 , setInventaireCourant.getValue(EnumRessources.PIECE));


        // Le joueur achète 1 bois et 1 minerai a gauche, et 1 minerai a droite
        // Il dispose d'une réduction a gauche mais pas a droite
        // Il paye donc 2 pièce a gauche et 2 pièce a droite
        // Le joueur dispose de 10 pièce, après l'achat il doit en avoir plus que 6
        arraylist = new ArrayList<>(carteStatue.getPrix());
        setInventaireGauche.clear();
        setInventaireDroit.clear();
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.REDMARRONGAUCHE, 1);
        setInventaireCourant.increaseValue(EnumRessources.PIECE, 7);
        assertFalse(construction.laConstructionViaCommerce(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit, false));
        setInventaireGauche.increaseValue(EnumRessources.BOIS, 1);
        setInventaireGauche.increaseValue(EnumRessources.MINERAI, 1);
        setInventaireDroit.increaseValue(EnumRessources.MINERAI, 1);
        assertTrue(construction.laConstructionViaCommerce(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit, false));
        assertEquals(5 , setInventaireDroit.getValue(EnumRessources.PIECE));
        assertEquals(5 , setInventaireGauche.getValue(EnumRessources.PIECE));
        assertEquals(6 , setInventaireCourant.getValue(EnumRessources.PIECE));


        // Le joueur achète 1 bois et 1 papyrus pour construire la carte Ecole, il n'a pas de réduction
        // Il paye 2 pièce a gauche et 2 pièce a droite
        arraylist = new ArrayList<>(carteEcole.getPrix());
        setInventaireGauche.clear();
        setInventaireDroit.clear();
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.PIECE, 7);
        setInventaireDroit.increaseValue(EnumRessources.BOIS, 1);
        setInventaireGauche.increaseValue(EnumRessources.PAPYRUS, 1);
        assertTrue(construction.laConstructionViaCommerce(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit, false));
        assertEquals(5 , setInventaireDroit.getValue(EnumRessources.PIECE));
        assertEquals(5 , setInventaireGauche.getValue(EnumRessources.PIECE));
        assertEquals(6 , setInventaireCourant.getValue(EnumRessources.PIECE));


        // Même test mais le joueur dispose d'une réduction pour le papyrus
        // Il paye 2 pièce a droite, et 1 pièce a gauche (le papyrus est a gauche)
        arraylist = new ArrayList<>(carteEcole.getPrix());
        setInventaireGauche.clear();
        setInventaireDroit.clear();
        setInventaireCourant.clear();
        setInventaireCourant.increaseValue(EnumRessources.PIECE, 7);
        setInventaireCourant.increaseValue(EnumRessources.REDGRISDROITEGAUCHE, 1);
        setInventaireDroit.increaseValue(EnumRessources.BOIS, 1);
        setInventaireGauche.increaseValue(EnumRessources.PAPYRUS, 1);
        assertTrue(construction.laConstructionViaCommerce(arraylist, setInventaireCourant, setInventaireGauche, setInventaireDroit, false));
        assertEquals(5 , setInventaireDroit.getValue(EnumRessources.PIECE));
        assertEquals(4 , setInventaireGauche.getValue(EnumRessources.PIECE));
        assertEquals(7 , setInventaireCourant.getValue(EnumRessources.PIECE));
    }


    /**
     * Test de la méthode achatVoisin()
     */
    @Test
    public void achatVoisinTest() throws NegativeNumberException {
        assertEquals(3 , setInventaireCourant.getValue(EnumRessources.PIECE));
        assertEquals(3  ,setInventaireGauche.getValue(EnumRessources.PIECE));
        assertEquals(3 , setInventaireDroit.getValue(EnumRessources.PIECE));

        construction.achatVoisin(setInventaireCourant, setInventaireGauche, EnumRessources.BOIS, 2, false);

        assertEquals(1, setInventaireCourant.getValue(EnumRessources.PIECE));
        assertEquals(5 , setInventaireGauche.getValue(EnumRessources.PIECE));
        assertEquals(3 , setInventaireDroit.getValue(EnumRessources.PIECE));

        construction.achatVoisin(setInventaireGauche, setInventaireDroit, EnumRessources.BOIS, 4, false);

        assertEquals(1 , setInventaireCourant.getValue(EnumRessources.PIECE));
        assertEquals(1 , setInventaireGauche.getValue(EnumRessources.PIECE));
        assertEquals(7 , setInventaireDroit.getValue(EnumRessources.PIECE));
    }
}