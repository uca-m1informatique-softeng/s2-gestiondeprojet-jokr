#language: fr

Fonctionnalité: Ajout de ressources dans un inventaire

  Contexte:
    Etant donné Le joueur "Enzo" allant jouer son premier coup de la partie

Plan du Scénario:
    Quand Il joue la carte <nomCarte> il obtient <nbBois> bois, <nbPierre> pierre, <nbArgile> argile, et <nbMinerai> minerai
    Alors Son inventaire doit contenir <nbBois> bois, <nbPierre> pierre, <nbArgile> argile, et <nbMinerai> minerai

    Quand Il ne peut jouer aucune carte, il en défausse une
    Alors Il obtient 3 pièces qui sont ajouté a son inventaire

    Exemples:
      | nomCarte          | nbBois | nbPierre | nbArgile | nbMinerai |
      | "Cavite"          |     -1 |        -1|      -10 |       -10 |
      | "Bassin Argileux" |     -2 |        -1|      -20 |       -10 |
      | "Chantier"        |      1 |        0 |        0 |         0 |
      | "Cavite"          |      0 |        1 |        0 |         0 |
      | "Bassin Argileux" |      0 |        0 |        1 |         0 |
      | "Filon"           |      0 |        0 |        0 |         1 |
      | "Chantier ++"     |      5 |        9 |        0 |         0 |
      | "Cavite ++"       |      1 |       16 |        0 |        10 |
      | "Filon ++"        |      1 |        5 |        8 |         0 |
