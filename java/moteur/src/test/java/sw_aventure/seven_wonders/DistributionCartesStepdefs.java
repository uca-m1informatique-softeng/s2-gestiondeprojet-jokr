package sw_aventure.seven_wonders;

import objet_commun.Carte;
import sw_aventure.objetjeu.GenererCarte;
import sw_aventure.objetjeu.MainJoueur;
import io.cucumber.java8.Fr;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
public class DistributionCartesStepdefs implements Fr {

    private GenererCarte genererCarte;
    private SevenWonders sevenWonders;
    private DeroulementJeu jeu ;
    private List<Carte> cards;
    private int nbJoueurs;
    private int age;
    private MoteurWebController web;

    public DistributionCartesStepdefs() {



        Etantdonné("Une partie de sevenWonders a {int} joueur", (Integer nbJoueur) -> {
            nbJoueurs = nbJoueur;
            sevenWonders = new SevenWonders(nbJoueur, false, false);
            jeu = new DeroulementJeu(web,sevenWonders.inv);
        });

        Quand("L'âge N°{int} commence, on récupère le paquet de carte correspondant a cet âge", (Integer ages) -> {
            age = ages;
            genererCarte = new GenererCarte(age, nbJoueurs);
            cards = genererCarte.getCards();
        });

        Alors("Sa taille doit être de {int}", (Integer nbCarte) -> {
            assertEquals(cards.size(), nbCarte);
        });



        Quand("Une distribution des cartes en début de chaque âge a lieu", () -> {
            jeu.distribution(cards);
        });

        Alors("Chaque joueur doit avoir 7 carte dans sa main", () -> {

            for (MainJoueur main : jeu.getMainJoueurs()) {
                assertEquals(7 , main.getMain().size());
            }

        });

    }
}
*/