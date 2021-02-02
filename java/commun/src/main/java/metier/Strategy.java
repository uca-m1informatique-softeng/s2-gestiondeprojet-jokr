package metier;

/**
 * Cette énumération contient le nom de toutes les stratégies
 */
public enum Strategy {

    RANDOM("random"),
    SCIENTIFIQUE("scientifique"),
    MONETAIRE("monetaire"),
    CIVILE("civile"),
    MILITAIRE("militaire"),
    COMPOSITE("composite"),
    MERVEILLE("merveille"),
    AMBITIEUSE("ambitieuse"),
    ULTIME("ultime!"); // pour les tests

    /**
     * Nom de la ressources
     */
    private final String nom ;


    /**
     * Constructeur de l'énumération
     * @param nom Le nom de la ressource
     */
    Strategy(String nom){
        this.nom = nom;
    }


    /**
     * Méthode permettant de récupérer le nom de la ressource
     * @return Le nom de la ressource
     */
    @Override
    public String toString() {
        return nom;
    }
}