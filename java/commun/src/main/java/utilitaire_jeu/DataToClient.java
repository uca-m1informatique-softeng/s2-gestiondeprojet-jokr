package utilitaire_jeu;

import objet_commun.Carte;
import java.util.List;

public class DataToClient {

    private Inventaire invJoueur;
    private List<Carte> listCarte;
    private Plateau plateau;
    

    public DataToClient(List<Carte> listCarte, Inventaire invJoueur, Plateau plateau){
        this.invJoueur = invJoueur;
        this.listCarte = listCarte;
        this.plateau = plateau;
    }

    public DataToClient(){}

    public Plateau getPlateau() {
        return plateau;
    }

    public Inventaire getInvJoueur() {
        return invJoueur;
    }

    public List<Carte> getListCarte() {
        return listCarte;
    }

    public void setInvJoueur(Inventaire invJoueur) {
        this.invJoueur = invJoueur;
    }

    public void setListCarte(List<Carte> listCarte) {
        this.listCarte = listCarte;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
}
