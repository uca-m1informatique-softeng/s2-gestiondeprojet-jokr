package utilitaire_jeu;

import objet_commun.Carte;

import java.util.List;

public class DataToClient {

    List<Carte> listCarte;
    Inventaire invJoueur;
    Plateau plateau;

    public DataToClient(List<Carte> listCarte, Inventaire invJoueur, Plateau plateau){
        this.invJoueur = invJoueur;
        this.listCarte = listCarte;
        this.plateau = plateau;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Inventaire getInvJoueur() {
        return invJoueur;
    }

    public List<Carte> getListCarte() {
        return listCarte;
    }
}
