package sw_aventure.seven_wonders;


import exception.NegativeNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import utilitaire_jeu.DataToClient;
import utilitaire_jeu.Inventaire;
import utilitaire_jeu.NameURL;
import utilitaire_jeu.SetInventaire;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MoteurWebController {
    List<SetInventaire> listJoueurId = new ArrayList<>();

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
    @Autowired
    private RestTemplate restTemplate;


    // juste pour le MockMvcTest, sans param
    @PostMapping("/essai/")
    public boolean getValue() {
        return true;
    }


    @PostMapping("/connexion/")
    public boolean getValue(@RequestBody NameURL nameURL) throws NegativeNumberException {
        System.out.println("Moteur > connexion acceptée de " + nameURL.getName());

        this.listJoueurId.add(new SetInventaire(listJoueurId.size(), nameURL.getUrl(), nameURL.getName()));
        //if(listJoueurId.size()==3) { return moteur.partie(3); }
        return true;
    }

    public Boolean jouerMerveille(Inventaire joueurId, DataToClient data) {
        Boolean resultat = false;
        if (joueurId != null) {
            resultat = restTemplate.postForObject(joueurId.getUrl()+"/jouer/Merveille", data, Boolean.class); // le "/" de /jouer par sécurité
        }
        return resultat ;
    }

    public int constructMerveille(Inventaire joueurId, DataToClient data) {
        int resultat = 0;
        if (joueurId != null) {
            resultat = restTemplate.postForObject(joueurId.getUrl()+"/jouer/constructMerveille", data, Integer.class); // le "/" de /jouer par sécurité
        }
        return resultat ;
    }

    public boolean jouerDefausse(Inventaire joueurId, DataToClient data) {
        Boolean resultat = false;
        if (joueurId != null) {
            resultat = restTemplate.postForObject(joueurId.getUrl()+"/jouer/Defausse", data, Boolean.class); // le "/" de /jouer par sécurité
        }
        return resultat ;
    }

    public int choixCarte(Inventaire joueurId, DataToClient data) {
        int resultat = 0;
        if (joueurId != null) {
            resultat = restTemplate.postForObject(joueurId.getUrl()+"/jouer/choixCarte", data, Integer.class); // le "/" de /jouer par sécurité
        }
        return resultat ;
    }

    public Integer jouerGratuitementDanslaDefausse(Inventaire joueurId , DataToClient data) {
        int resultat = 0;
        if (joueurId != null) {
            resultat = restTemplate.postForObject(joueurId.getUrl()+"/jouer/GratuitementDanslaDefausse", data, Integer.class); // le "/" de /jouer par sécurité
        }
        return resultat ;
    }

    public String getStrategie(String url) {
        String res;
        res = restTemplate.postForObject(url + "/strategie", null, String.class);
        return res;
    }

    /*
    public String getNomJoueur(joueur) {
        String resultat = "[NULL]";
        if (joueurId != null) {
            resultat = joueurId.getJoueurName();
        }
        return resultat ;
    }
    */

    public void envoyerFin(Inventaire joueurId) {
        restTemplate.exchange(joueurId.getUrl()+"/finir", HttpMethod.POST, null, Void.class);
    }


}