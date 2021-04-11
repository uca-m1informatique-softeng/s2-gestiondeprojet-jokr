package serveur;

import static org.junit.jupiter.api.Assertions.*;

import metier.Data;
import metier.EnumCarte;
import metier.EnumRessources;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import statistique.Statistique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ServeurITCase {


    @SpyBean
    Serveur serveur;

    Data[] dataPartie1, dataPartie2;
    Data data1 ,data2, data3;
    StringBuilder stringBuilders;
    int nbJoueurRecu;
    int nbPartiesRecu;

    Statistique statistique, statsSpy;

    @BeforeEach
    void setup() {
        nbJoueurRecu = 3;
        nbPartiesRecu = 2 ;


        Map<String, Integer> map = new HashMap<>();
        map.put(EnumRessources.VICTOIRETOTAL.toString(), 2);
        map.put(EnumRessources.SCOREFINAL.toString(), 12);
        map.put(EnumRessources.SCOREPIECE.toString() , 10);
        map.put(EnumRessources.SCORE.toString(), 16);
        map.put(EnumRessources.BONUSSCIENTIFIQUE.toString(), 7);
        map.put(EnumRessources.VICTOIRE.toString(), 3);
        map.put(EnumRessources.DEFAITE.toString(), 1);
        map.put(EnumRessources.BONUSCARTE.toString(), 2);
        map.put(EnumRessources.STADE.toString(), 3);

        List<String> listeCarte = new ArrayList<>();
        listeCarte.add(EnumCarte.M1.toString());
        listeCarte.add(EnumCarte.M2.toString());

        data1 = new Data("Joueur1", "random", map, "BABYLON", listeCarte);
        data2 = new Data("Joueur2", "random", map, "BABYLON", listeCarte);
        data3 = new Data("Joueur3", "random", map, "BABYLON", listeCarte);

        dataPartie1 = new Data[3];
        dataPartie2 = new Data[3];
        dataPartie1[0] = data1;
        dataPartie1[1] = data2;
        dataPartie1[2] = data3;
        dataPartie2[0] = data1;
        dataPartie2[1] = data2;
        dataPartie2[2] = data3;

        List<Data[]> listeData = new ArrayList<>();
        listeData.add(dataPartie1);
        listeData.add(dataPartie2);

        stringBuilders  = new StringBuilder();

        statsSpy = new Statistique(nbPartiesRecu,nbJoueurRecu,listeData);
        statsSpy = Mockito.spy(statsSpy);
        //ReflectionTestUtils.setField(serveur, "statistique", statsSpy);

    }

    /**
     * On vérifie la réception du nombre de joueur
     * @throws Exception
     */
    @Test
    public void getNbJoueurTest() throws Exception {
        assertEquals(0, serveur.getNbJoueur());
        serveur.getNbJoueur(nbJoueurRecu);
        Thread.sleep(2000);
        assertEquals(3, serveur.getNbJoueur());
    }

    /**
     * On vérifie la réception du nombre de partie attendu
     * @throws Exception
     */
    @Test
    public void getNbPartieTest() throws Exception {
        assertEquals(0, serveur.getNbPartie());
        serveur.getNbPartie(nbPartiesRecu);
        Thread.sleep(2000);
        assertEquals(2, serveur.getNbPartie());
    }

    /**
     * On vérifie la réceptions des datas des parties ainsi que la réaction du serveur lorsque le nombre de partie reçu est atteint
     * @throws Exception
     */
    @Test
    public void getStatsTest() throws Exception {
        serveur.getNbJoueur(nbJoueurRecu);
        serveur.getNbPartie(nbPartiesRecu);


        assertEquals(0, serveur.getDataParties().size());
        serveur.getStats(dataPartie1);
        Thread.sleep(2000);
        assertNull(serveur.getStatistique());
        assertEquals(1, serveur.getDataParties().size());
        verify(serveur, times(1)).traiterFinReception();

        serveur.getStats(dataPartie2);
        Thread.sleep(2000);
        assertEquals(2, serveur.getDataParties().size());
        assertNotNull(serveur.getStatistique());
        verify(serveur, times(2)).traiterFinReception();
    }

    /**
     * On vérifie que showpartie test lorqu'il est appelé crée bien un GestionnaireDeFichier
     * @throws Exception
     */
    @Test
    public void showPartieTest() throws Exception {
        assertNull(serveur.getGestionnaireDeFichier());
        serveur.showPartie(stringBuilders);
        Thread.sleep(2000);
        assertNotNull(serveur.getGestionnaireDeFichier());
        //verify(serveur ,times(1)).enregistrerPartie(any(),any(),any());


    }



}
