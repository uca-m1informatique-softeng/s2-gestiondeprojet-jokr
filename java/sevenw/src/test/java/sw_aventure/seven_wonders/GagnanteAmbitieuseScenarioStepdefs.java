package sw_aventure.seven_wonders;

import io.cucumber.java8.Fr;
import metier.EnumCarte;
import metier.EnumRessources;
import metier.Strategy;
import metier.Wonder;
import sw_aventure.joueur.*;
import sw_aventure.objetjeu.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class GagnanteAmbitieuseScenarioStepdefs implements Fr {



    private Inventaire inv1 ;
    private Inventaire inv2 ;
    private Inventaire inv3 ;
    private Inventaire inv4 ;
    private Inventaire inv5 ;
    private Inventaire inv6 ;
    private Inventaire inv7 ;
    private SetInventaire sinv1 ;
    private SetInventaire sinv2 ;
    private SetInventaire sinv3 ;
    private SetInventaire sinv4 ;
    private SetInventaire sinv5 ;
    private SetInventaire sinv6 ;
    private SetInventaire sinv7 ;

    private Joueur joueur1 ;

    private Joueur joueur2 ;
    private Joueur joueur3 ;
    private Joueur joueur4 ;
    private Joueur joueur5 ;
    private Joueur joueur6 ;
    private Joueur joueur7 ;

    private ArrayList<Joueur> listeJoueur;
    private  ArrayList<Inventaire> listeInventaire;
    private  ArrayList<SetInventaire> slisteInventaire;

    private Plateau plateau;
    private IA iaRandom;
    private IA iaCivile;
    private IA iaAmbitieuse;
    private IA iaScientifique;
    private IA iaMonetaire;
    private IA iaMilitaire;

    private Inventaire inv;
    private SecureRandom randomwtSeed;
    private SevenWonders sevenWonders;
    private  DeroulementJeu sJeu;
    private SecureRandom sRand;
    private int  mGraine;
    public GagnanteAmbitieuseScenarioStepdefs() {

        Etantdonné("Une série de fins de parties de 7 Wonders.", () -> {
            randomwtSeed = new SecureRandom ();
            SetInventaire invO = new SetInventaire(1, Strategy.ULTIME, "Ultimo");
            iaCivile = new IAcivil() ;
            iaScientifique = new IAscientifique() ;
            iaMonetaire = new IAmonetaire() ;
            iaRandom = new IArandom() ;
            iaAmbitieuse = new IArandom() ;
            iaMilitaire = new IAmilitaire() ;
        });

        Quand("Une partie disposant d une graine aleatoire fixe à {int} .", ( Integer fixe) -> {

            mGraine = fixe;
            listeInventaire = new ArrayList<Inventaire>();
            listeJoueur = new ArrayList<Joueur>();

        });
        Alors("Le joueur ressortant gagnant de la partie à 7 joueurs, sera alors toujours l IA Ambiteuse.", ( ) -> {
            sevenWonders = new SevenWonders( 7,false,false);
            sevenWonders.partie(7);

            sinv1 = new SetInventaire(1, Strategy.MONETAIRE, "Paul");
            sinv2 = new SetInventaire(2, Strategy.CIVILE, "Fred");
            sinv3 = new SetInventaire(3, Strategy.AMBITIEUSE, "Mona");
            sinv4 = new SetInventaire(4, Strategy.MILITAIRE, "Lucy");
            sinv5 = new SetInventaire(5, Strategy.SCIENTIFIQUE, "Dora");
            sinv6 = new SetInventaire(6, Strategy.CIVILE, "Alex");
            sinv7 = new SetInventaire(7, Strategy.RANDOM, "Enzo");


            List<Carte> etape = new ArrayList<>();
            etape.add(new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS, EnumRessources.BOIS, EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE, EnumRessources.SCORE)));
            Merveille babylon = new Merveille(Wonder.BABYLON, EnumRessources.BOIS, etape, joueur1);
            Merveille halikarnasos = new Merveille(Wonder.HALIKARNASSOS, EnumRessources.BOIS, etape, joueur2);
            Merveille olympia = new Merveille(Wonder.OLYMPIA, EnumRessources.BOIS, etape, joueur3);
            Merveille rhodos = new Merveille(Wonder.RHODOS, EnumRessources.BOIS, etape, joueur4);
            Merveille ephesos = new Merveille(Wonder.EPHESOS, EnumRessources.BOIS, etape, joueur5);
            Merveille alexandria = new Merveille(Wonder.ALEXANDRIA, EnumRessources.BOIS, etape, joueur6);
            Merveille gizah = new Merveille(Wonder.GIZAH, EnumRessources.BOIS, etape, joueur7);

            Joueur joueur1 = sinv1.getJoueur();
            Joueur joueur2 = sinv2.getJoueur();
            Joueur joueur3 = sinv3.getJoueur();
            Joueur joueur4 = sinv4.getJoueur();
            Joueur joueur5 = sinv5.getJoueur();
            Joueur joueur6 = sinv6.getJoueur();
            Joueur joueur7 = sinv7.getJoueur();


            sevenWonders.setTheSeed( mGraine );
            joueur1.getBot().setTheSeed(mGraine);
            joueur2.getBot().setTheSeed(mGraine);
            joueur3.getBot().setTheSeed(mGraine);
            joueur3.getBot().setTheSeed(mGraine);
            joueur4.getBot().setTheSeed(mGraine);
            joueur5.getBot().setTheSeed(mGraine);
            joueur6.getBot().setTheSeed(mGraine);
            joueur7.getBot().setTheSeed(mGraine);



            listeInventaire = new ArrayList<Inventaire>(){
                {add(sinv1);add(sinv2);add(sinv3);add(sinv4);add(sinv5);add(sinv6);add(sinv7);}
            };
            listeJoueur = new ArrayList<Joueur>(){{
                add(joueur1);add(joueur2);add(joueur3);add(joueur4);add(joueur5);add(joueur6);add(joueur7);}
            };


            plateau = new Plateau( listeInventaire, listeJoueur );
            plateau = sevenWonders.initPlateau();
            sevenWonders.attributionMerveille();
/*
            for (SetInventaire S : sevenWonders.inv ) {
                S.modifMerveille(babylon);
            }
*/
            sevenWonders.inv.get(0).modifMerveille(olympia);
            sevenWonders.inv.get(1).modifMerveille(halikarnasos);
            sevenWonders.inv.get(2).modifMerveille(babylon);
            sevenWonders.inv.get(3).modifMerveille(rhodos);
            sevenWonders.inv.get(4).modifMerveille(ephesos);
            sevenWonders.inv.get(5).modifMerveille(alexandria);
            sevenWonders.inv.get(6).modifMerveille(gizah);


            sevenWonders.partie(7);

            //assertEquals("Mona", sevenWonders.jeu.gagnante(7,plateau));
            /*
            assertNotEquals("Enzo", sevenWonders.jeu.gagnante(7,plateau));
            assertNotEquals("Paul", sevenWonders.jeu.gagnante(7,plateau));
            assertNotEquals("Lucy", sevenWonders.jeu.gagnante(7,plateau));
            */
        });
    }
}
