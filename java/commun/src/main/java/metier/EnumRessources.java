package metier;

/**
 * Cette énumération contient le nom de toutes les ressources, bonus, etc...
 */
public enum EnumRessources {

    BOIS("Bois"),
    PIERRE("Pierre"),
    ARGILE("Argile"),
    MINERAI("Minerai"),
    VERRE("Verre"),
    TISSU("Tissu"),
    PAPYRUS("Papyrus"),
    COMPAS("Compas"),
    ROUE("Roue"),
    PDR("Pierre de Rosette"),
    BOUCLIER("Bouclier"),
    SCORE("Score"),
    PIECE("Pièce"),
    GRATUIT("Gratuit"),
    SCOREFINAL("Score Final"),
    VICTOIRE("Pts de Victoire"),
    DEFAITE("Pts de Défaite"),
    STADE("Stade"),

    VICTOIRETOTAL("Victoire"),

    BONUSCPR("Compas/PierreDeRosette/Roue"),


    REDMARRONDROITE("ReducMarronDroite"),
    REDMARRONGAUCHE("ReducMarronGauche"),
    REDGRISDROITEGAUCHE("ReducGrisDroiteGauche"),

    // BONUS CARTE MARRON AGE 1

    MULTIPA("Pierre/Argile"),
    MULTIBP("Bois/Pierre"),
    MULTIAM("Argile/Minerai"),
    MULTIBM("Bois/Minerai"),
    MULTIBA("Bois/Argile"),
    MULTIPM("Pierre/Minerai"),
    MULTIBPAM("Bois/Pierre/Argile/Minerai"),
    MULTIVPT("Verre/Papyrus/Tissu"),

    BONUS1MDJG("1 pièce pour chaque carte de couleur marron à droite joueur et gauche"),
    BONUS2GDJG("2 pièces pour chaque carte de couleur grise à droite joueur et gauche"),

    // BONUS CARTE JAUNE AGE 3
    BONUS11J("1 pièce et 1 score (en fin de partie) pour chaque carte jaune du joueur"),
    BONUS11M("1 pièce et 1 score (en fin de partie) pour chaque carte marron du joueur"),
    BONUS22G("2 pièces et 2 scores (en fin de partie) pour chaque carte grise du joueur"),
    BONUS31R("3 pièces et 1 score (en fin de partie) pour chaque carte rouge du joueur"),
    BONUS31MERVEILLE("3 pièces et 1 score (en fin de partie) pour chaque étage de la merveille du joueur"),

    // BONUS CARTE VIOLETTE
    VBONUS1M("1 score en fin de partie pour chaque carte de couleur marron à droite et gauche"),
    VBONUS2G("2 scores en fin de partie pour chaque carte de couleur grise à droite et gauche"),
    VBONUS1B("1 score en fin de partie pour chaque carte de couleur bleue à droite et gauche"),
    VBONUS1J("1 score en fin de partie pour chaque carte de couleur jaune à droite et gauche"),
    VBONUS1R("1 score en fin de partie pour chaque carte de couleur rouge à droite et gauche"),
    VBONUS1V("1 score en fin de partie pour chaque carte de couleur verte à droite et gauche"),
    VBONUSMGV("1 score en fin de partie pour chaque carte de couleur marron, grise et violette du joueur"),
    VBONUS1MERVEILLE("1 score en fin de partie pour chaque étage de merveille à droite joueur et gauche"),
    VBONUS7COMPLETMERVEILLE("7 scores en fin de partie si la merveille est terminée"),

    BONUSAGECOULEUR("Jouer la première carte Age de chaque couleur"),
    BONUSDEFAUSSEG("Jouer une carte de la défausse gratuitement"),
    BONUSDEFAUSSEG1("Jouer une carte de la défausse gratuitement + 1 point"),
    BONUSDEFAUSSEG2("Jouer une carte de la défausse gratuitement + 2 points"),
    BONUSCARTEAGEG2P("Construire gratuitement la première carte de l'Age + 2 points de Score"),
    BONUSCARTEAGEG3P("Construire gratuitement la dernière carte de l'Age + 3 points de Score"),
    BONUS7CARTEMAIN("Jouer la 7ème carte de la main"),

    BONUSCARTE("Bonus Cartes"),
    BONUSSCIENTIFIQUE("Scientifique"),
    SCOREPIECE("Score Pièce"),

    MARRON("marron"),
    GRISE("grise"),
    BLEUE("bleue"),
    JAUNE("jaune"),
    ROUGE("rouge"),
    VERTE("verte"),
    VIOLETTE("violette");



    /**
     * Nom de la ressources
     */
    private final String nom ;


    /**
     * Constructeur de l'énumération
     * @param nom Le nom de la ressource
     */
    EnumRessources(String nom){
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
