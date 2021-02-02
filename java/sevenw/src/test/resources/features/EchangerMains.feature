#language: fr


Fonctionnalité: Echange de main

  Plan du Scénario:
    Etant donné  Une partie de SevenWonders à <nombreJoueurs> joueurs
    Et Des joueurs ayant une main de carte

    Quand La fin d'un tour a lieu, les joueurs échangent leur main
    Alors Les joueurs doivent avoir la main de leur voisin

    Exemples:
      | nombreJoueurs |
      |             3 |
      |             4 |
      |             5 |
      |             6 |
      |             7 |
