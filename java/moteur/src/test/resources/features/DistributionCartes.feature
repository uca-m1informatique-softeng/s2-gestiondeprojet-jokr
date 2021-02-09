#language: fr


Fonctionnalité: Distribution des cartes


  Plan du scénario:
    Etant donné Une partie de sevenWonders a <nbJoueur> joueur
    Quand L'âge N°<age> commence, on récupère le paquet de carte correspondant a cet âge
    Alors Sa taille doit être de <nbCarte>


    Quand Une distribution des cartes en début de chaque âge a lieu
    Alors Chaque joueur doit avoir 7 carte dans sa main

    Exemples:
      | nbJoueur | age | nbCarte |
      |        3 |   1 |      21 |
      |        3 |   2 |      21 |
      |        3 |   3 |      21 |
      |        4 |   1 |      28 |
      |        4 |   2 |      28 |
      |        4 |   3 |      28 |
      |        5 |   1 |      35 |
      |        5 |   2 |      35 |
      |        5 |   3 |      35 |
      |        6 |   1 |      42 |
      |        6 |   2 |      42 |
      |        6 |   3 |      42 |
      |        7 |   1 |      49 |
      |        7 |   2 |      49 |
      |        7 |   3 |      49 |


