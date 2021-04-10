package joueur;

import metier.EnumCarte;
import metier.EnumRessources;
import metier.Wonder;
import objet_commun.Carte;
import objet_commun.Merveille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import utilitaire_jeu.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FacadeJoueurITCase {

    NameURL id;
    Carte carte1 , carte2 , carte3 ;
    List<Carte> arrayOfCarte ;
    Merveille merveille;
    SetInventaire inv;
    Inventaire inv1, inv2, inv3, inv4, inv5;
    Plateau plateau;

    @Autowired
    Joueur j;

    Joueur joueur1,joueur2,joueur3,joueur4,joueur5,joueur6;

    @SpyBean
    FacadeJoueur facade;

    Joueur joueur ;
    DataToClient data;

    @BeforeEach
    void setup() throws Exception {
        this.id = new NameURL("HARLOD", "http://" + InetAddress.getLocalHost().getHostAddress());
        inv = new SetInventaire(1, "http://" + InetAddress.getLocalHost().getHostAddress(), "HARLOD");

        carte1 = new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.ARGILE, EnumRessources.ARGILE), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE));
        carte2 = new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.MINERAI, EnumRessources.MINERAI, EnumRessources.MINERAI), Collections.singletonList(EnumRessources.BONUSCPR));
        carte3 = new Carte(EnumCarte.MERVEILLE, Arrays.asList(EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS,EnumRessources.BOIS), Arrays.asList(EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE,EnumRessources.SCORE));

        arrayOfCarte = new ArrayList<>(){{ add(carte1); add(carte2); add(carte3); }};

        inv1 = new Inventaire( 1,"url1", "Enzo");
        inv2 = new Inventaire( 2,"url2", "Christina");
        inv3 = new Inventaire( 3,"url3", "Mona");
        inv4 = new Inventaire( 4,"url4", "Paul");
        inv5 = new Inventaire( 5,"url5", "Lucie");

        merveille = new Merveille(Wonder.BABYLON, EnumRessources.BOIS, arrayOfCarte);
        inv.modifMerveille(merveille);

        ArrayList<Inventaire> listeInventaire = new ArrayList<>(){{add(inv1);add(inv2);add(inv3);add(inv4);add(inv5);add(inv);}};

        plateau = new Plateau(listeInventaire);

        this.data = new DataToClient(arrayOfCarte,inv,plateau);

        joueur = Mockito.spy(j);
        ReflectionTestUtils.setField(facade, "j", joueur);
    }

    @Test
    public void jouerDefausseTest() throws Exception {
        facade.jouerDefausse(data);
        Thread.sleep(2000);
        verify(joueur, times(1)).jouerdefausse(any(), any(), any());

        facade.jouerGratuitementDanslaDefausse(data);
        Thread.sleep(2000);
        verify(joueur, times(1)).jouerGratuitementDanslaDefausse(any(), any(), any());

        facade.jouerMerveille(data);
        Thread.sleep(2000);
        verify(joueur, times(1)).jouerMerveille(any(), any(), any());

        facade.constructMerveille(data);
        Thread.sleep(2000);
        verify(joueur, times(1)).constructMerveille(any(), any(), any());

        facade.choixCarte(data);
        Thread.sleep(2000);
        verify(joueur, times(1)).choixCarte(any(), any(), any());

        facade.getStrategie();
        Thread.sleep(2000);
        verify(joueur, times(1)).getStrategie();

    }
}
