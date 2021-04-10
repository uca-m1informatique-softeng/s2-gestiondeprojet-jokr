package sw_aventure.seven_wonders;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class MoteurTests {

    @Test
    public  void contextLoads(){
        //A simple sanity check test that will fail if the application context cannot start
        assertEquals("Hala Madrid" , "Hala " + "Madrid");
    }

}
