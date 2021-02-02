# Designs Patterns

### Patrons de Conception

- Tout d'abord, concernant la conception de notre projet, nous avons fait le choix d'utiliser certains Patron de Conception contrairement à d"autres.
Pour commencer, on a choisi d'utiliser le Patron de Conception nommé Strategy, cela nous permet d'alléger la classe IA, 
en évitant de créer des switch/case ou encore des if/else à répétition.
 - Ensuite, nous avons déjà mis en place des Patrons de Conception : Façade avec un Logger afin de cacher l"affichage via le System.out ainsi
nous facilitant les Test sur les méthodes dont le contenu est principalement par ces System.out
 - Toujours concernant l"affichage,  nous avons mise en place une classe Colors qui permet de surcharger les fonctions liées à l"affichage pour avoir des couleurs. 
On utilise donc le Patron de Conception surnommé Decorator qui surcharge une classe afin d"amener plus de fonctionalitées.
 - De plus, nous avions mise en place le DP Composite avec la classe Carte qui permettait de gérer à la fois le paquet de cartes et à la fois une carte unique.
Suite à la demande du client, nous avons rompu ce Patron de Conception et créer 2 classes : une gérant le paquet et l"autre gérant la carte. 
Cela nous permet de plus facilement gérer ces objets, tout en diminuant la complexité du code.
 - Qui-plus-est, on prévoit de gérer le plateau indépendamment de l'inventaire du joueur avec sa propre classe.
On aurait pû utiliser le Patron de Conception Decorator , pour surcharger la classe Inventaire, en rajoutant la fonctionnalité du plateau ,
mais pour des questions de simplicité, on choisit de ne pas augmenter la complexité de cette classe.
On va faire une classe propre à plateau pour avoir une classe possédant une seule fonctionnalité qui est de stocker les ressources du plateau du joueur.
 - D'autant plus, on ne prévoit pas d'utiliser le Patron de Conception Observer, car nous avons déjà un moyen gérer le passage d'informations entre différentes classes assez simple.
Mettre en place ce Patron de Conception demanderait la mise en place d"un protocole, qui ajouterait en complexité à notre code.

