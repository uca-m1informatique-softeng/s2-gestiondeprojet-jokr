package metier;

public enum Wonder {

    BABYLON("BABYLON"),
    BABYLONNUIT("BABYLON DE NUIT"),
    HALIKARNASSOS("HALIKARNASSOS"),
    HALIKARNASSOSNUIT("HALIKARNASSOS DE NUIT"),
    OLYMPIA("OLYMPIA"),
    OLYMPIANUIT("OLYMPIA DE NUIT"),
    GIZAH("GIZAH"),
    GIZAHNUIT("GIZAH DE NUIT"),
    RHODOS("RHODOS"),
    RHODOSNUIT("RHODOS DE NUIT"),
    EPHESOS("EPHESOS"),
    EPHESOSNUIT("EPHESOS DE NUIT"),
    ALEXANDRIA("ALEXANDRIA"),
    ALEXANDRIANUIT("ALEXANDRIA DE NUIT"),

    STATUELIBERTE("STATUE DE LA LIBERTE"); // pour les tests



    /**
     * Nom de la ressources
     */
    private final String nom ;


    /**
     * Constructeur de l'énumération
     * @param nom Le nom de la ressource
     */
    Wonder(String nom){
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

