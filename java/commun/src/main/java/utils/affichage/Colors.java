package utils.affichage;


/**
 * Classe permettant d'améliorer les affichages'
 */
public class Colors {
    private static boolean color;
    private static final String CODE = "\033[0m";

    private Colors(){
    }

    public static void setColor(boolean setColor){
        color = setColor;
    }

    // ----------------------   COULEURS BASIQUES ------------------------


    /**
     * @param s string à modifier
     * @return s en rouge
     */
    public static String rouge(String s) {
        if (color) return "\033[31m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en jaune
     */
    public static String jaune(String s) {
        if(color) return "\033[33m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en violet
     */
    public static String violet(String s) {

        if(color) return "\033[35m" + s +CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en blanc
     */
    public static String blanc(String s) {
        if(color) return "\033[37m" + s + CODE;
        else return s;
    }

    // ----------------------   ECRITURE EN GRAS  ------------------------


    /**
     * @param s string à modifier
     * @return s en standard gras
     */
    public static String gStandard(String s) {
        if(color) return "\033[1m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en vert gras
     */
    public static String gVert(String s) {
        if(color) return "\033[1;32m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en rouge gras
     */
    public static String gRouge(String s) {
        if(color) return "\033[1;31m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en bleu gras
     */
    public static String gBleu(String s) {
        if(color) return "\033[1;34m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en jaune gras
     */
    public static String gJaune(String s) {
        if(color) return "\033[1;33m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en violet gras
     */
    public static String gViolet(String s) {
        if(color) return "\033[1;35m" + s + CODE;
        else return s;
    }


    /**
     * @param s string à modifier
     * @return s en blanc gras
     */
    public static String gBlanc(String s) {
        if(color) return "\033[1;37m" + s + CODE;
        else return s;
    }


    // ----------------------   ECRITURE EN ITALIQUE  ------------------------


    /**
     * @param s string à modifier
     * @return s en standard italique
     */
    public static String itStandard(String s) {
        if(color) return "\033[3m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en vert italique
     */
    public static String itVert(String s) {
        if(color) return "\033[3;32m" + s + CODE;
        else return s;
    }



    // ----------------------   ECRITURE INTENSIFIEE  ------------------------

    /**
     * @param s string à modifier
     * @return s en rouge intensifié
     */
    public static String iRouge(String s) {
        if(color) return "\033[91m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en bleu intensifié
     */
    public static String iBleu(String s) {
        if(color) return "\033[94m" + s + CODE;
        else return s;
    }


    // ----------------------   ECRITURE GRAS INTENSIFIEE  ------------------------


    /**
     * @param s string à modifier
     * @return s en vert gras intensifié
     */
    public static String igVert(String s) {
        if(color) return "\033[1;92m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en rouge gras intensifié
     */
    public static String igRouge(String s) {
        if(color) return "\033[1;91m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en vert gras intensifié
     */
    public static String igBleu(String s) {
        if(color) return "\033[1;94m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en jaune gras intensifié
     */
    public static String igJaune(String s) {
        if(color) return "\033[1;93m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en violet gras intensifié
     */
    public static String igViolet(String s) {
        if(color) return "\033[1;95m" + s + CODE;
        else return s;
    }

    /**
     * @param s string à modifier
     * @return s en violet gras intensifié
     */
    public static String igCyan(String s) {
        if(color) return "\033[1;96m" + s + CODE;
        else return s;
    }

}

