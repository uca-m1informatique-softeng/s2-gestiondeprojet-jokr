package utils.affichage;

import java.io.PrintStream;

public class LoggerSevenWonders {

    private static boolean mode;
    private static PrintStream out = System.out;
    private static StringBuilder stringBuilder;

    /**
     * constructeur du Logger
     */
    private LoggerSevenWonders(){
    }

    /**
     * getter string du stringbuilder
     * @return le stringbuilder
     */
    public static StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    /**
     * initialisation du Logger
     * @param displayMode si il faut afficher ou non dans la sortie standard
     */
    public static void init(boolean displayMode) {
        mode = displayMode;
        stringBuilder = new StringBuilder();
    }

    /**
     * agit comme un print et ajoute au stringbuilder le print
     * @param str la chaine de caractère / nombre etc...
     */
    public static void ajout(Object str){
        if (mode)
            stringBuilder.append(str);
    }

    /**
     * agit comme un println et ajoute au stringbuilder le print en ajoutant un retour à la ligne
     * @param str la chaine de caractère / nombre etc...
     */
    public static void ajoutln(Object str){
        if (mode)
            stringBuilder.append(str).append("\n");
    }

    /**
     * Affiche dans la sortie standard l'objet str (StringBuilder, Statistiques, etc....)
     * @param str la chaine de caractère / nombre etc...
     */
    public static void show(Object str) {
        if (mode)
            out.println(str);
    }
}
