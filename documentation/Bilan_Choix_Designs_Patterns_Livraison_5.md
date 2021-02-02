# Designs Patterns 2: Livraison 5

### Patrons de Conception 2

- Pour commencer, nous avons pris en compte les remarques faites sur les anti-patterns vu en cours.
On a remarqué que la classe Construction est une classe "God" ; Ainsi, nous avons donc essayer de la réduire au maximum sans pour autant la transformer en une classe Poltergeist.
De surcroît, on va essayer de penser aux niveaux des fonctionnalités et non aux niveaux des lignes de code présent dans cette classe.
Toufefois, on ne cherche pas à faire du " Management by numbers " sur l"ensemble de notre code, car certaines classes (comme GenererCarte) sont de fait :  - longues et complexes par choix d'implémentation du code.
On continue d'ajouter des classes pour l 'IA en suivant le pattern Strategy.

 - Ensuite, le client nous a precisé que la facade du Logger n"en était pas vraiment une, et la Classe Colors ne suivait pas un pattern Decorator concernant l'affichage.
 Nous en prenons bien compte dans notre conception de code.

 - De plus, on a choisit de créer 2 classes pour les merveilles : une gérant le paquet avec la classe GenererMerveille, et l'autre gérant la merveille toute seule, avec la classe Merveille.
 On évite par la même d'utiliser le pattern Composite sans créer de classe gérant le paquet et la merveille seule en même temps, car on juge que cela rajouterait de la complexité que l"on juge inutile.
 
 En outre, on a choisit de pas utiliser le pattern Proxy, comme les cartes et les merveilles ne sont pas assez lourdes en terme de code et de complexité pour nécessiter son utilisation selon notre opinion sur ce code.

D'autant plus, nous avons implémentés un serveur dont le fonctionnement ne nécessite pas le patron Adapter pour son fonctionnement actuelle. 
Cependant, s' il s"avère nécessaire pour son fonctionnement plus tard, nous utiliserons ce type de Designs Patterns.


