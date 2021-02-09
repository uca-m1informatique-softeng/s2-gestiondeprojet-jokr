package sw_aventure.seven_wonders;

import metier.EnumRessources;
import metier.Strategy;
import utilitaire_jeu.SetInventaire;
import io.cucumber.java8.Fr;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;


public class ScientifiqueStepdefs implements Fr {

    private SetInventaire inv;

    public ScientifiqueStepdefs() {

        Etantdonné("Une fin de partie, les cartes scientifique sont convertis en points de victoire", () -> {
            inv = new SetInventaire(1, Strategy.RANDOM, "Enzo");
        });


        Soit("Le joueur disposant de {int} Compas, {int} Roue, et {int} Pierre de Rosette", (Integer compas, Integer roue, Integer pdt) -> {
            inv.increaseValue(EnumRessources.COMPAS, compas);
            inv.increaseValue(EnumRessources.ROUE, roue);
            inv.increaseValue(EnumRessources.PDR, pdt);
        });

        Alors("Le joueur doit obtenir grâce à ses cartes scientifique {int} point de victoire", (Integer pointVictoire) -> {
            assertEquals(inv.compteScientifique(), pointVictoire);
        });
    }
}
