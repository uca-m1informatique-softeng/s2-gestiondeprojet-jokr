package reseau;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import io.socket.client.Socket;

public class ConnexionTest {


    private Socket socket;


    /**
     * Preparation pour les tests de la classe ConnexionTest
     */
    @Before
    public void setup() {
        socket = Mockito.mock(Socket.class);
        Connexion.CONNEXION.setmSocket(socket);
    }


    /**
     * On vérifie si la méthode "envoyerMessageStringBuilder()" a bien été effectué
     */
    @Test
    public void envoyerMessageStringBuilderTest() {
        StringBuilder stringBuilder = new StringBuilder();
        Connexion.CONNEXION.envoyerMessageStringBuilder("StringBuilder", stringBuilder);
        Mockito.verify(socket).emit("StringBuilder", stringBuilder);
    }

    /**
     * On vérifie si la méthode "emit()" a bien été effectué
     */
    @Test
    public void envoyerMessageIntTest(){
        Connexion.CONNEXION.envoyerMessageInt("int", 4);
        Mockito.verify(socket).emit("int", 4);
    }


    /**
     * On vérifie si la méthode "emit()" a bien été effectué
     */
    @Test
    public void envoyerMessageBooleanTest(){
        Connexion.CONNEXION.envoyerMessageBoolean("boolean", true);
        Mockito.verify(socket).emit("boolean", true);
    }


    /**
     * On vérifie si la méthode "emit()" a bien été effectué
     */
    @Test
    public void envoyerMessageArrayTest(){
        Connexion.CONNEXION.envoyerMessageArray("array", Mockito.mock(JSONArray.class));
        Mockito.verify(socket).emit(Mockito.eq("array"), Mockito.any());
    }


    /**
     * On vérifie si la méthode "connect()" a bien été effectué
     */
    @Test
    public void demarrerEcouteTest() {
        Connexion.CONNEXION.demarrerEcoute();
        Mockito.verify(socket).connect();
    }


    /**
     * On vérifie si la méthode "disconnect()" a bien été effectué
     */
    @Test
    public void disconnecteTest(){
        Connexion.CONNEXION.disconnect();
        Mockito.verify(socket).disconnect();
    }
}
