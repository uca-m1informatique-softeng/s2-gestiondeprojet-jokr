#language: fr

Fonctionnalité: Assure la gagne de la strategie Ambitieuse pour des scénarios données.

  Contexte:
    Etant donné Une série de fins de parties de 7 Wonders.

  Plan du scénario:
    Quand Une partie disposant d une graine aleatoire fixe à <graine> .
    Alors Le joueur ressortant gagnant de la partie à 7 joueurs, sera alors toujours l IA Ambiteuse.

    Exemples: :
  |  graine |
  |  212    |
  |  425    |
  |  1072   |
  |  1030   |
  |  204    |
  |  109    |
  |  48     |
  |  964    |
  |  2442   |
  |  32     |
  |  2323   |
  |  2332   |
