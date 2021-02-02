<h1>ANALYSE 3</h1>

Date : 11/12/2020

Dans cette analyse nous avons implémenté l'IA ambitieuse.

Plusieurs éléments de la stratégie ambitieuse sont réfléchis pour gagner pas mal de points et également désavantager les adversaires adjacents. <i>(plus d'explication dans la section IA AMBITIEUSE)</i>

Lors de cette mise à jour nous nous sommes rendu compte d'un "bug" :
  - les joueurs se retrouvaient parfois dans la situation où ils pouvaient techniquement construire un bâtiment mais le jeu leur refuse cette action.
  - ce bug est dû à la mise à jour sur les rotations des joueurs, les voisins lors de la vérification du commerce n'étaient pas forcément ceux actuellement voisins au joueur. 
  - ce bug a engendré une perte de points conséquente pour les IA évoluées, et a donc faussé (proportionnellement) les statistiques.
  
<h3>IA GARANTIE</h3>
Depuis la correction du bug voici les nouvelles statistiques des joueurs. 

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

- repasser dans les IA si des idées viennent mais ce n'est plus une priorité.
  
  
<h3>IA AMBITIEUSE</h3>

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

- random        00.18 %
- ambitieuse    59.65 %
- composite     40.17 %

Plus précisément : 

GARANTIE : 
<i>
- Un score total de 47.3264 points.
- 1.86475 points grâce à ses Pièces.
- 25.44815 points grâce aux Bâtiments civils.
- 11.6602 points Scientifique.
- 8.73585 points de Victoire de guerre.
- 1.76785 points de Défaite guerre.
- 1.3853 points grâce aux cartes bonus.
</i>
  
AMBITIEUSE : 
<i>
- Un score total de 49.85245 points.
- 9.6254 points grâce à ses Pièces.
- 19.34555 points grâce aux Bâtiments civils.
- 2.3172 points Scientifique.
- 8.8689 points de Victoire de guerre.
- 1.31385 points de Défaite guerre.
- 11.00925 points grâce aux cartes bonus.
</i>
  
A faire : 

  - Développer l'IA Ambitieuse et la rendre plus réceptive à la situation actuelle du jeu au cours de la partie 
  
  
  
