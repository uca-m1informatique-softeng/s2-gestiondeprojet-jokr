package sw_aventure.seven_wonders;

import exception.NegativeNumberException;
import metier.EnumRessources;
import metier.Strategy;
import utilitaire_jeu.SetInventaire;
import io.cucumber.java8.Fr;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SetInventaireStepdefs implements Fr {

    private SetInventaire setInventaire;
    private int nbPiece;


    public SetInventaireStepdefs() {
        Etantdonné("Le joueur {string} allant jouer son premier coup de la partie", (String nom) -> {
            setInventaire = new SetInventaire(1, Strategy.RANDOM, nom);
        });


        Quand("Il joue la carte {string} il obtient {int} bois, {int} pierre, {int} argile, et {int} minerai", (String carte, Integer bois, Integer pierre, Integer argile, Integer minerai) -> {
            if( bois < 0 ) {  assertThrows(NegativeNumberException.class, () -> setInventaire.increaseValue(EnumRessources.BOIS,-1)); }
            else {
                setInventaire.increaseValue(EnumRessources.BOIS, bois);
            }
            if( pierre < 0 ) { assertThrows(NegativeNumberException.class, () -> setInventaire.increaseValue(EnumRessources.PIERRE,-1)); }
            else {
                setInventaire.increaseValue(EnumRessources.PIERRE, pierre);
            }
            if( argile < 0 ) { assertThrows(NegativeNumberException.class, () -> setInventaire.increaseValue(EnumRessources.ARGILE,-1)); }
            else {
                setInventaire.increaseValue(EnumRessources.ARGILE, argile);
            }
            if( minerai < 0 ) { assertThrows(NegativeNumberException.class, () -> setInventaire.increaseValue(EnumRessources.MINERAI,-1)); }
            else {
                setInventaire.increaseValue(EnumRessources.MINERAI, minerai);
            }

        });

        Alors("Son inventaire doit contenir {int} bois, {int} pierre, {int} argile, et {int} minerai", (Integer bois, Integer pierre, Integer argile, Integer minerai) -> {
            if( bois < 0 ) {  assertEquals(0, setInventaire.getValue(EnumRessources.BOIS)); }
            else { assertEquals(setInventaire.getValue(EnumRessources.BOIS), bois); }

            if( pierre < 0 ) { assertEquals(0, setInventaire.getValue(EnumRessources.PIERRE));  }
            else { assertEquals(setInventaire.getValue(EnumRessources.PIERRE), pierre); }

            if( argile < 0 ) { assertEquals(0, setInventaire.getValue(EnumRessources.ARGILE));  }
            else { assertEquals(setInventaire.getValue(EnumRessources.ARGILE), argile); }

            if( minerai < 0 ) { assertEquals(0, setInventaire.getValue(EnumRessources.MINERAI));  }
            else { assertEquals(setInventaire.getValue(EnumRessources.MINERAI), minerai); }



        });


        Quand("Il ne peut jouer aucune carte, il en défausse une", () -> {
            nbPiece = setInventaire.getValue(EnumRessources.PIECE);
            setInventaire.casDefausse();
        });

        Alors("Il obtient 3 pièces qui sont ajouté a son inventaire", () -> {
            assertEquals(setInventaire.getValue(EnumRessources.PIECE), nbPiece + 3);
        });
    }
}
