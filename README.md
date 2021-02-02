# Seven Wonders

## Groupe Aventure : 
- Nabil YACOUB
- Rémi YACOUB
- Jérémy HIRTH DAUMAS
- Ralph EL CHALFOUN


<h1> Description de l'IA Garantie et Ambitieuse </h1>

<i>
Présentation des éléments importants des IA garantie et ambitieuse du TER Master Informatique Semestre 1
</i>  

  <h3> IA garantie </h3>
  
- On va baser notre IA garantie sur l'IA composite déjà présente dans notre code.

    - Elle se sert des autres IA comme militaire, civile, scientifique, merveille et monétaire.
    - Ces IA ont chacunes des merveilles et des listes de cartes favorites avec un ordre de préference hiérarchiques entre elles.
    - On va améliorer toutes ces IA, dont l'IA composite est dépendante afin de l'optimiser.
      
- L'IA composite va préférer suivre l'IA scientifique en général, sauf dans les cas qui ne lui permettent pas.  

    - La stratégie scientifique est la stratégie susceptible de rapporter le plus de points dans un scénario précis( en ayant la merveille Babylone/ personne ne fait sa stratégie).
    - Les stratégies militaires et monétaires rapportent, d'après nos statistiques, nettement moins de points que celles civiles et merveilles, donc on privilègiera ces stratégies. 

- On va également faire en sorte que l'IA composite possèdent des graphes ou des "chemins" de choix optimisés selon la combinaison de carte qu'elle possède.

    - Cela servira à limiter au maximum les pertes de points au score, si l'IA se trouve dans les pires cas: (main désavantageuse, manque de ressources ou de pièces...), 
    - Elle maximisera son score si elle se trouve avec la bonne combinaison de merveille, ressources, cartes et pièces.


<h3>  IA ambitieuse </h3>
      
<s>
  - On va chercher à avoir une IA ambitieuse capable de s'adapter quasi-immediatement aux choix stratégiques des autres joueurs.
</s><s>
    - Elle reconnaitra très vite les stratégies des joueurs via le plateau.
    - Elle pourra même voir si un joueur change de stratégie en pleine partie. 
</s><s>
 - Elle pourra choisir, de plutôt que de viser le score personel maximum à tout prix, de faire en sorte de minimiser les points des autres joueurs.
</s><s>  
    - Elle défaussera les cartes clées au bon moment pour contrer les joueurs adverses.
    - Elle sera capable de remarquer exactement quand certaines cartes ne sont plus nécessaires à acheter, où si une merveille donnée est obtenue et améliorée.
</s><s>
  - Elle aura aussi une mémoire sur les mains déjà jouées pour savoir quelle carte sera joué par les autres joueurs.
</s><s>
    - Elle détectera si les joueurs sont obligés de jouer des cartes d'un type selon les prochaines cartes restantes , malgré sa propre stratégie initialement établie.
</s>   

UPDATE : 
  


- Sa stratégie actuelle se base sur ces actions : 
  - à l'Age I & II elle va vouloir acheter les bâtiments marrons et gris lui donnant des ressources 
  - à l'Age III, elle aura normalement accumulé suffisamment de ressources pour pouvoir construire quasi tous les bâtiments de l'Age III, soit les plus chères

- En ne construisant que les bâtiments de types ressources (gris et marrons), elle aura moins besoin d'acheter aux voisins des ressources pendant les premiers Ages.

- Cela permettra d'économiser des pièces, de ne pas en donner aux voisins et aussi d'en récupérer beaucoup car les voisins pourront acheter en principe toutes les ressources chez elle.

- L'IA Ambitieuse va également réfléchir à se protéger lors des différentes guerres pour commencer à gagner quelques points facilement notamment si les adversaires ne se concentre pas dessus.

- Lors de l'Age III c'est là que tout se joue : l'IA Ambitieuse grâce à son stock de ressources et de pièces pourra acheter n'importe quelle carte de l'Age III même les plus chères (avec un taux d'achat du Palace à 68% à 7 joueurs)

- L'IA va à l'Age III en plus de se défendre, acheter les cartes les plus rentables pour elle, en calculant le revient de chaque carte (en points de scores) et choisir la plus avantageuse au moment actuel.

- Le fait qu'elle prenne beaucoup de cartes importantes à l'Age III permet d'empêcher les autres IA de compléter leur stratégie (comme par exemples les "grosses" cartes bleues pour l'IA Civile.

<h1> Description des règles prise en compte ou non pour le TER </h1>

<i>
Présentation des règles gardées et ignorées pour le TER concernant l'IA garantie et l'IA ambitieuse dans le jeu 7wonders
</i>  

  <h3> Règles ignorées </h3>

- On ne va pas prendre en compte les règles de "multichoix" des ressources.

    - Les joueurs ne peuvent pas acheter/marchander de ressources "multi-choix" voisines.
    - Ici, le "multi-choix" représente les gains des bâtiments tel que un batiment donne un choix multiple entre plusieurs ressources recues pour le joueur.
    - Par exemple, les gains des bâtiments tel que la FOSSE ARGILEUSE donne le multi-choix ARGILE/MINERAIS au joueur le possedant.

 <h3> Règles gardées </h3>

- On va prendre en compte tout le reste des règles du jeu implanté dans notre jeu numérique 7Wonders qui sont les mêmes règles que le jeu physique originel.

    - Les règles et les bonus des merveilles, cartes et ressources sont fonctionnels, et prêt à être utilisés.
    - Les règles sur la guerre, les differents types de points et le score final sont bien implantés.
