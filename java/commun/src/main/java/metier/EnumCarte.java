package metier;

/**
 * Cette énumération contient le nom de toutes les cartes
 */
public enum EnumCarte {

            // CARTE MARRON
    M1("Exploitation Forestière"),
    M2("Fosse Argileuse"),
    M3("Filon"),
    M4("Bassin Argileux"),
    M5("Cavité"),
    M6("Chantier"),
    M7("Excavation"),
    M8("Gisement"),
    M9("Mine"),
    M10("Friche"),
    M11("Fonderie"),
    M12("Briqueterie"),
    M13("Carrière"),
    M14("Scierie"),

            // CARTE GRISE
    G1("Presse"),
    G2("Verrerie"),
    G3("Métier à Tisser"),

            // CARTE BLEUE
    B1("Théâtre"),
    B2("Autel"),
    B3("Bains"),
    B4("Tribunal"),
    B5("Temple"),
    B6("Aqueduc"),
    B7("Statue"),
    B8("Panthéon"),
    B9("Jardins"),
    B10("Hôtel de Ville"),
    B11("Palace"),
    B12("Sénat"),
    B13("Puits"),

            // CARTE JAUNE
    J1("Marché"),
    J2("Comptoir OUEST"),
    J3("Comptoir EST"),
    J4("Taverne"),
    J5("Vignoble"),
    J6("Forum"),
    J7("Caravansérail"),
    J8("Bazar"),
    J9("Phare"),
    J10("Port"),
    J11("Arène"),
    J12("Chambre de Commerce"),
    J13("Ludus"),

            // CARTE ROUGE
    R1("Tour de Garde"),
    R2("Caserne"),
    R3("Palissade"),
    R4("Muraille"),
    R5("Champs de Tir"),
    R6("Ecuries"),
    R7("Place d'Armes"),
    R8("Fortification"),
    R9("Arsenal"),
    R10("Atelier de Siège"),
    R11("Castrum"),
    R12("Cirque"),

            // CARTE VERTE
    V1("Scriptorium"),
    V2("Atelier"),
    V3("Officine"),
    V4("Ecole"),
    V5("Bibliothèque"),
    V6("Laboratoire"),
    V7("Dispensaire"),
    V8("Loge"),
    V9("Académie"),
    V10("Observatoire"),
    V11("Etude"),
    V12("Université"),

            // CARTE VIOLETTE
    P1("Guilde des Travailleurs"),
    P2("Guilde des Artisans"),
    P3("Guilde des Magistrats"),
    P4("Guilde des Commerçants"),
    P5("Guilde des Espions"),
    P6("Guilde des Philosophes"),
    P7("Guilde des Scientifiques"),
    P8("Guilde des Armateurs"),
    P9("Guilde des Batisseurs"),
    P10("Guilde des Décorateurs"),

    MERVEILLE("M");


    /**
     * Nom de la carte
     */
    private final String nom ;


    /**
     * Constructeur de l'énumération
     * @param nom Le nom de la carte
     */
    EnumCarte(String nom){
        this.nom = nom;
    }

    /**
     * Méthode permettant de récupérer le nom de la carte
     * @return Le nom de la carte
     */
    @Override
    public String toString() {
        return nom;
    }
}
