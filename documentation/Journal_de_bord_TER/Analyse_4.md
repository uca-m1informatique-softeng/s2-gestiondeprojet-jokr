<h1>ANALYSE 4</h1>

Date : 13/01/2020

Dans cette analyse nous avons exclusivement amélioré l'IA ambitieuse, l'IA composite étant aboutie.


<h3>IA GARANTIE</h3>
 Aucun changement

Notre IA garantie est l'IA "Composite" cette dernière choisit sa stratégie en fonction de la merveille obtenue : 

- EPHESOS           IA Civile
- RHODOS            IA Civile
- BABYLON           IA Scientifique
- OLYMPIA           IA Civile
- GIZAH             IA Civile
- HALIKARNASSOS J   IA Civile
- HALIAKRNASSOS N   IA Scientifique
- ALEXANDRIA        IA Civile

Dans cette configuration là les stats sont les suivantes :

  (Strategy.RANDOM, Strategy.SCIENTIFIQUE, Strategy.CIVILE, Strategy.MONETAIRE, Strategy.MILITAIRE, Strategy.MERVEILLE, Strategy.COMPOSITE)
  
7 JOUEURS sur 20000 parties 

- random        00.02 %
- scientifique  32.13 %
- civile        22.05 %
- monétaire     03.38 %
- militaire     03.00 %
- merveille     08.16 % 
- composite     31.05 %

 Ici l'IA Composite gagne moins que l'IA Scientifique, l'IA Civile n'est pas aussi performante que l'IA Scientifique alors pourquoi la choisir plus souvent ?
 
 C'est très simple car à chaque partie l'IA Composite sera soit Civile soit Scientifique alors qu'une autre IA le sera aussi, de ce fait l'IA Composite devra partager sa stratégie avec un autre joueur.
 
 Notre but sera que l'IA Composite ne joue pas contre les IA composante pour qu'elle soit efficace, également étant donné qu'elle joue plus souvent Civile, l'IA Civile est ici beaucoup bridée.
 

Donnée de stratégie : 

CARTES RECHERCHEES

<i>

- scientifique :

  -  ("Métier à Tisser", "Officine","Verrerie", "Atelier","Presse","Scriptorium")

  -  ("Dispensaire","Laboratoire","Bibliothèque","Ecole")

  -  ("Guilde des Scientifiques", "Loge", "Académie", "Observatoire", "Etude", "Université")
 
- civile :

  - ("Bains","Autel","Puits","Théâtre") 

  - ("Aqueduc","Temple","Statue","Tribunal")

  - ("Panthéon", "Jardins", "Palace", "Hôtel de Ville", "Sénat")

- militaire :

  - ("Palissade","Caserne","Tour de Garde","Comptoir OUEST","Comptoir EST")

  - ("Caravansérail","Forum","Ecuries","Champs de Tir","Muraille","Place d'Armes")

  - ("Ludus", "Fortifications", "Cirque", "Arsenal", "Atelier de Siège", "Castrum")

- monétaire :

  - ("Taverne","Marché","Comptoir OUEST","Comptoir EST","Friche","Excavation","Fosse Argileuse","Exploitation Forestière","Gisement","Mine","Verrerie","Presse","Métier à Tisser")

  - ("Vignoble","Bazar","Scierie","Carrière","Briqueterie","Fonderie","Verrerie","Presse","Métier à Tisser")

  - ("Phare","Guilde des Armateurs","Guilde des Espions", "Guilde des Philosophes","Guilde des Magistrats","Port","Chambre de Commerce")

</i>

RESSOURCES RECHERCHEES

<i>

- scientifique :

  - 1 VERRE 1 PAPYRUS 1 TISSU

  - 1 VERRE 1 PAPYRUS 1 TISSU 2 BOIS 3 PIERRE 2 ARGILE 2 MINERAI  

 
- civile :

  - 1 PIERRE 1 TISSU 1 MINERAI 1 ARGILE 1 VERRE

  - 2 BOIS 1 TISSU 2 (3 si on a pas Bains) PIERRE 2 ARGILE 2 MINERAI  + 1 PAPYRUS si on a pas Théâtre 


- militaire :

  - n/a

  - n/a


- monétaire :

  - n/a

  - n/a

Les ressources recherchées pour l'Age III sont désormais fusionnées avec l'Age II étant donné que aucune ressources ne peut être obtenue à l'Age III

</i>

A faire : 

- rien
  
  
<h3>IA AMBITIEUSE</h3>

AMELIORATIONS UPDATE: 
  - EQUILIBRE DES RESSOURCES :
    - Lors de la phase d'achat de ressource (AGE I et II) l'IA va mieux répartir la recherche des ressources.
      ( exemple : au lieu de chercher 3 de bois , elle va d'abord essayer d'avoir toutes les ressources marrons en quantité supérieure ou égale à 2 )
  - PRISE NE COMPTE CARTE SCIENTIFIQUE : 
    - Lors de l'AGE III l'IA ne considerais pas les cartes scientiques comme un potentiel gain de points, désormais elle fera elle même son propre calcul pour savoir si acheter une ressource scientifique est plus rentable que les autres cartes de sa main.
  - POINTS DE GUERRES : 
    - Lors du choix de carte à tout AGEs, l'IA se défendait uniquement si aucune carte recherchée n'est disponible, désormais elle réfléchira à se proteger en achetant des cartes militaire si cela peut engendrer un gain de points supérieur à toutes autres possibilités.


Sa stratégie actuelle se base sur ces actions : 
  - à l'Age I & II elle va vouloir acheter les bâtiments marrons et gris lui donnant des ressources 
  - à l'Age III, elle aura normalement accumulé suffisamment de ressources pour pouvoir construire quasi tous les bâtiments de l'Age III, soit les plus chères

En ne construisant que les bâtiments de types ressources (gris et marrons), elle aura moins besoin d'acheter aux voisins des ressources pendant les premiers Ages.

Cela permettra d'économiser des pièces, de ne pas en donner aux voisins et aussi d'en récupérer beaucoup car les voisins pourront acheter en principe toutes les ressources chez elle.

L'IA Ambitieuse va également réfléchir à se protéger lors des différentes guerres pour commencer à gagner quelques points facilement notamment si les adversaires ne se concentre pas dessus.

Lors de l'Age III c'est là que tout se joue : l'IA Ambitieuse grâce à son stock de ressources et de pièces pourra acheter n'importe quelle carte de l'Age III même les plus chères (avec un taux d'achat du Palace à 68% à 7 joueurs)

L'IA va à l'Age III en plus de se défendre, acheter les cartes les plus rentables pour elle, en calculant le revient de chaque carte (en points de scores) et choisir la plus avantageuse au moment actuel.

Le fait qu'elle prenne beaucoup de cartes importantes à l'Age III permet d'empêcher les autres IA de compléter leur stratégie (comme par exemples les "grosses" cartes bleues pour l'IA Civile.

STATISTIQUES

3 JOUEURS sur 20000 parties  

- random        00.055 %
- ambitieuse    72.500 %
- garantie      27.445 %

Plus précisément : 

GARANTIE : 
<i>
	- Un score total de 46.26655 points.
	- 1.8518 points grâce à ses Pièces.
	- 26.32805 points grâce aux Bâtiments civils.
	- 11.06125 points Scientifique.
	- 7.39625 points de Victoire de guerre.
	- 2.05675 points de Défaite guerre.
	- 1.68595 points grâce aux cartes bonus.
</i>
  
AMBITIEUSE : 
<i>
	- Un score total de 52.276 points.
	- 9.5364 points grâce à ses Pièces.
	- 18.087 points grâce aux Bâtiments civils.
	- 4.47065 points Scientifique.
	- 11.5934 points de Victoire de guerre.
	- 0.8819 points de Défaite guerre.
	- 9.47045 points grâce aux cartes bonus.
</i>
  
  
Une très nette amélioration des scores pour l'IA Ambitieuse, même si elle ne marque pas autant de points dans les catégories Civiles et Scientiques, l'IA a plutôt de bons scores dans toutes les catégories et permettra de contrer sur tout les flancs, en plus de cela en cas d'égalité elle a plus de chance de remporter la partie avec la quantité finale de pièces.

(Sans rentrer dans des calculs statistiques trop appronfondis, nous avons remarqué que l'IA ambitieuse n'obtient pas d'aussi gros score record (que l'IA composite) au cours des parties mais sa moyenne est plus élevée que l'IA Composite, on peut estimer que sa stratégie est plus stable, et que donc le median et les écart-types sont meilleurs. Cela reste une estimation.)

A faire : 

  - Rapport et Diaporama pour la soutenance.
  
  
  
