package sw_aventure.seven_wonders;

import exception.NegativeNumberException;
import sw_aventure.objetjeu.GenererCarte;
import io.cucumber.java8.Fr;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EchangerMainsStepdefs implements Fr {

    GenererCarte genererCarte;
    SevenWonders sevenWonders;
    DeroulementJeu jeu ;
    int[] hashcode;
    int nbJoueur;
    private MoteurWebController web;

    public EchangerMainsStepdefs() {

        Etantdonné("Une partie de SevenWonders à {int} joueurs", (Integer nombreJoueurs) -> {
            nbJoueur = nombreJoueurs;
            sevenWonders = new SevenWonders(nbJoueur, false, false);
            jeu = new DeroulementJeu(web,sevenWonders.inv);
            genererCarte = new GenererCarte(1, nombreJoueurs);
            jeu.distribution(genererCarte.getCards());

        });

        Et("Des joueurs ayant une main de carte", () -> {
            // On mémorise leur HashCode
            jeu.initMainJoueur(nbJoueur);
            hashcode = new int[nbJoueur];
            for (int i = 0; i < nbJoueur; i++) {
                hashcode[i] = jeu.getMainJoueurs().get(i).getMain().hashCode();
            }
        });

        Quand("La fin d'un tour a lieu, les joueurs échangent leur main", () -> {
            jeu.echangerMain(1);
        });

        Alors("Les joueurs doivent avoir la main de leur voisin", () -> {

            // On vérifie le HashCode
            for (int i = 0; i < nbJoueur; i++) {
                assertEquals(hashcode[i], jeu.getMainJoueurs().get((i + 1) % nbJoueur).getMain().hashCode());
            }

        });
    }
}
