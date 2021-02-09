package sw_aventure.seven_wonders;

import objet_commun.Merveille;
import utilitaire_jeu.Inventaire;


/**
 * Interface servant de Facade Moteur dans l'optique d'une prochaine séparation entre le Moteur-de-jeu et les Joueurs-Clients
 */
public interface FacadeMoteur {

    /**
     * Retourne la Merveille d'un Inventaire
     * @param inv l'inventaire
     * @return la merveille
     */
    static Merveille getMerveille(Inventaire inv){
        return inv.getMerveille();
    }



}
