#language: fr

Fonctionnalité: Comptage des cartes Scientifique


  Contexte:
    Etant donné Une fin de partie, les cartes scientifique sont convertis en points de victoire


  Plan du scénario:
    Soit Le joueur disposant de <nbCompas> Compas, <nbRoue> Roue, et <nbPdt> Pierre de Rosette
    Alors Le joueur doit obtenir grâce à ses cartes scientifique <pointVictoire> point de victoire

    Exemples:
      | nbCompas | nbRoue | nbPdt | pointVictoire |
      |        0 |      0 |     0 |             0 |
      |        1 |      0 |     0 |             1 |
      |        0 |      1 |     0 |             1 |
      |        0 |      0 |     1 |             1 |
      |        2 |      0 |     0 |             4 |
      |        1 |      0 |     2 |             5 |
      |        2 |      2 |     2 |            26 |
      |        0 |      3 |     0 |             9 |
      |        1 |      2 |     3 |            21 |
      |        4 |      1 |     1 |            25 |
      |        3 |      2 |     4 |            43 |
      |        0 |      5 |     0 |            25 |
      |        6 |      5 |     0 |            61 |
      |        4 |      6 |     3 |            82 |