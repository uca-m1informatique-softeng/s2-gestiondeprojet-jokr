package sw_aventure.seven_wonders;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.*;
import utilitaire_jeu.NameURL;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MoteurWebControlleurITCase {

    NameURL nameURL1;
    NameURL nameURL2;
    NameURL nameURL3;

    @SpyBean
    MoteurWebController webController;

    @Autowired
    SevenWonders sevenWonders;

    SevenWonders swSpy;

    @BeforeEach
    void setup() {
        nameURL1 = new NameURL("GEORGE", "http://127.0.0.1:8091");
        nameURL2 = new NameURL("BOB", "http://127.0.0.1:8092");
        nameURL3 = new NameURL("JACQUES", "http://127.0.0.1:8093");

        swSpy = Mockito.spy(sevenWonders);
        ReflectionTestUtils.setField(webController, "moteur", swSpy);
    }

    @Test
    public void connexionTest() throws Exception {
        int taille = webController.listJoueurId.size();

        webController.getValue(nameURL1);

        Thread.sleep(2000);

        assertEquals(taille + 1, webController.listJoueurId.size());
    }

    @Test
    public void jouerTest() throws Exception {
        webController.getValue(nameURL1);
        webController.getValue(nameURL2);
        webController.getValue(nameURL3);

        Thread.sleep(2000);

        assertEquals(3, webController.listJoueurId.size());

        verify(swSpy, times(1)).launchGame();
        verify(swSpy, times(1)).initPlayers(any());
        verify(swSpy, times(1)).partie(any(), any());
    }

}
