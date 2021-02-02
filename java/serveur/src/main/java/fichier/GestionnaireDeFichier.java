package fichier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Cette classe permet de gérer l'écriture dans les fichier
 */
public class GestionnaireDeFichier {


    /**
     * Méthode permettant d'écrire les statistiques dasn un fichier
     * @param path chemin du fichier
     * @param str  texte a écrire (les statistiques)
     * @throws java.io.IOException Exception si l'écriture dans le fichier est impossible
     */
    public void ecrireDansFichier(String path, String str) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.println(str);
        out.flush();
        out.close();
    }
}
