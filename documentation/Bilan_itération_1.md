
Bilan de l'itération 1 :

  Nous avons mis à jour :
    - l'arborescence du jeu afin de séparer les différentes classes dans un but d'esthétisme et pour une meilleure gestion des futurs accès entre les classes
    - les issues pour la prochaine itération
    
  Nous avons implémenté :
    - des joueurs (on peut choisir le nombre de joueur)
    - des cartes 1er_Age_provisoire simplifiées :
        - chaque carte donne des points au joueur qui la reçoit en fonction du nombre associé à la carte
    - un inventaire pour chaques joueurs :
        - cette inventaire permet à l'heure actuelle de stocker les points obtenus via les cartes et permet ainsi de determiner par la suite qui est le gagnant 
        - nous avons également ajouté quelques fonctions nous permettant de gérer cet inventaire via la classe setInventaire
    - un système de distribution aléatoire de carte aux joueurs
    - un système de décision du gagnant en regardant dans l'inventaire de chaque joueurs combien de points ils possèdent, le gagnant étant celui qui en possède le plus 
  
  Nous avons testé :
    - la classe Carte Age 1 
    
  Nous prévoyons pour la prochaine mise à jour du jeu :
    - Continuer les tests manquant et à venir 
    - Mise en place du système monétaire : simplifié ( 3 pièces en début de partie, 1 point de victoire par Lot de 3 pièces)
    - Système de défausse de carte (gain de 3 pièces dans ce cas)
    - Ajout de plus de tours -> 6, et système d'échange de carte à chaque tour
    - Ajout de quelques ressources donnant (provisoirement) des points en fin de partie
    - Attribution de ces ressources aux cartes du 1er_Age_provisoire
  
