package reseau;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONArray;
import java.net.URISyntaxException;

public enum Connexion {

    CONNEXION;

    private Socket mSocket;


    /**
     * Constructeur, essaye de se connecter au serveur
     */
    Connexion() {
        try {
            mSocket = IO.socket("http://127.0.0.1:8081");
        }
        catch (URISyntaxException ignored) {
            // Connexion échoué
        }
    }


    /**
     * Permet d'envoyer un StringBuilder au serveur
     * @param msg L'objet du message
     * @param stringBuilder StringBuilder
     */
    public void envoyerMessageStringBuilder(String msg, StringBuilder stringBuilder) {
        mSocket.emit(msg, stringBuilder);
    }


    /**
     * Permet d'envoyer un int au serveur
     * @param msg L'objet du message
     * @param obj int
     */
    public void envoyerMessageInt(String msg, int obj) {
        mSocket.emit(msg, obj);
    }


    /**
     * Permet d'envoyer un boolean au serveur
     * @param msg L'objet du message
     * @param obj boolean
     */
    public void envoyerMessageBoolean(String msg, boolean obj) {
        mSocket.emit(msg, obj);
    }


    /**
     * Permet d'envoyer un JSONArray au serveur
     * @param msg L'objet du message
     * @param obj JSONArray
     */
    public void envoyerMessageArray(String msg, JSONArray obj) {
        mSocket.emit(msg, obj);
    }


    /**
     * Permet de se connecter au serveur et de rester en attente d'éventuel message
     */
    public void demarrerEcoute() {
        mSocket.connect();
    }


    /**
     * Permet de rompre la connexion avec le serveur
     */
    public void disconnect() {
        if (mSocket != null) mSocket.disconnect();
    }


    /**
     * Setter pour mSocket
     * @param mSocket Socket
     */
    public void setmSocket(Socket mSocket) {
        this.mSocket = mSocket;
    }
}
