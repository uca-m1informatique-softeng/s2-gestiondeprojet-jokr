package metier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumRessourcesTest {

    /**
     * Test de la méthode getNom()
     * On verifie que les enum fonctionnent bien sur toutes les ressources, les bonus, les reductions commerciales, les points et les couleurs des cartes
     */
    @Test
    void getNom() {
     //RESSOURCES
        assertEquals("Bois", EnumRessources.BOIS.toString());
        assertEquals("Pierre", EnumRessources.PIERRE.toString());
        assertEquals("Argile", EnumRessources.ARGILE.toString());
        assertEquals("Minerai", EnumRessources.MINERAI.toString());
        assertEquals("Verre", EnumRessources.VERRE.toString());
        assertEquals("Tissu", EnumRessources.TISSU.toString());
        assertEquals("Papyrus", EnumRessources.PAPYRUS.toString());
        assertEquals("Compas", EnumRessources.COMPAS.toString());
        assertEquals("Roue", EnumRessources.ROUE.toString());
        assertEquals("Pierre de Rosette", EnumRessources.PDR.toString());
        assertEquals("Bouclier", EnumRessources.BOUCLIER.toString());

        //POINTS SANS BONUS
        assertEquals("Score", EnumRessources.SCORE.toString());
        assertEquals("Pièce", EnumRessources.PIECE.toString());
        assertEquals("Gratuit", EnumRessources.GRATUIT.toString());
        assertEquals("Score Final", EnumRessources.SCOREFINAL.toString());
        assertEquals("Pts de Victoire", EnumRessources.VICTOIRE.toString());
        assertEquals("Pts de Défaite", EnumRessources.DEFAITE.toString());

        // REDUCTION COMMERCIALES
        assertEquals("ReducMarronDroite", EnumRessources.REDMARRONDROITE.toString());
        assertEquals("ReducMarronGauche", EnumRessources.REDMARRONGAUCHE.toString());
        assertEquals("ReducGrisDroiteGauche", EnumRessources.REDGRISDROITEGAUCHE.toString());

        // BONUS CARTE MARRON AGE 1
        assertEquals("Pierre/Argile", EnumRessources.MULTIPA.toString());
        assertEquals("Bois/Pierre", EnumRessources.MULTIBP.toString());
        assertEquals("Argile/Minerai", EnumRessources.MULTIAM.toString());
        assertEquals("Bois/Minerai", EnumRessources.MULTIBM.toString());
        assertEquals("Bois/Argile", EnumRessources.MULTIBA.toString());
        assertEquals("Pierre/Minerai" , EnumRessources.MULTIPM.toString());
        assertEquals("Bois/Pierre/Argile/Minerai", EnumRessources.MULTIBPAM.toString());
        assertEquals("Verre/Papyrus/Tissu", EnumRessources.MULTIVPT.toString());
        assertEquals("1 pièce pour chaque carte de couleur marron à droite joueur et gauche", EnumRessources.BONUS1MDJG.toString());
        assertEquals("2 pièces pour chaque carte de couleur grise à droite joueur et gauche", EnumRessources.BONUS2GDJG.toString());

        // BONUS CARTE JAUNE AGE 3
        assertEquals("1 pièce et 1 score (en fin de partie) pour chaque carte jaune du joueur", EnumRessources.BONUS11J.toString());
        assertEquals("1 pièce et 1 score (en fin de partie) pour chaque carte marron du joueur", EnumRessources.BONUS11M.toString());
        assertEquals("2 pièces et 2 scores (en fin de partie) pour chaque carte grise du joueur", EnumRessources.BONUS22G.toString());
        assertEquals("3 pièces et 1 score (en fin de partie) pour chaque carte rouge du joueur", EnumRessources.BONUS31R.toString());
        assertEquals("3 pièces et 1 score (en fin de partie) pour chaque étage de la merveille du joueur", EnumRessources.BONUS31MERVEILLE.toString());

        // BONUS CARTE VIOLETTE
        assertEquals("1 score en fin de partie pour chaque carte de couleur marron à droite et gauche", EnumRessources.VBONUS1M.toString());
        assertEquals("2 scores en fin de partie pour chaque carte de couleur grise à droite et gauche", EnumRessources.VBONUS2G.toString());
        assertEquals("1 score en fin de partie pour chaque carte de couleur bleue à droite et gauche", EnumRessources.VBONUS1B.toString());
        assertEquals("1 score en fin de partie pour chaque carte de couleur jaune à droite et gauche", EnumRessources.VBONUS1J.toString());
        assertEquals("1 score en fin de partie pour chaque carte de couleur rouge à droite et gauche", EnumRessources.VBONUS1R.toString());
        assertEquals("1 score en fin de partie pour chaque carte de couleur verte à droite et gauche", EnumRessources.VBONUS1V.toString());
        assertEquals("1 score en fin de partie pour chaque carte de couleur marron, grise et violette du joueur", EnumRessources.VBONUSMGV.toString());
        assertEquals("1 score en fin de partie pour chaque étage de merveille à droite joueur et gauche", EnumRessources.VBONUS1MERVEILLE.toString());
        assertEquals("7 scores en fin de partie si la merveille est terminée", EnumRessources.VBONUS7COMPLETMERVEILLE.toString());

        //POINTS DE BONUS
        assertEquals("Compas/PierreDeRosette/Roue", EnumRessources.BONUSCPR.toString());
        assertEquals("Bonus Cartes", EnumRessources.BONUSCARTE.toString());
        assertEquals("Scientifique", EnumRessources.BONUSSCIENTIFIQUE.toString());
        assertEquals("Score Pièce", EnumRessources.SCOREPIECE.toString());

        //COULEURS DES CARTES
        assertEquals("marron", EnumRessources.MARRON.toString());
        assertEquals("grise", EnumRessources.GRISE.toString());
        assertEquals("bleue", EnumRessources.BLEUE.toString());
        assertEquals("jaune", EnumRessources.JAUNE.toString());
        assertEquals("rouge", EnumRessources.ROUGE.toString());
        assertEquals("verte", EnumRessources.VERTE.toString());
        assertEquals("violette", EnumRessources.VIOLETTE.toString());
    }
}