
Bilan de l'itération 2 :

  Nous avons mis à jour :
    	- l'inventaire pour les nouvelles ressources
	- l'affichage en rajoutant des couleurs pour la lisibilité du jeu 
	- les joueurs ont désormais des noms pour rendre le jeu plus réel 
	- la désignation du gagnant il peut y avoir une égalité 
    	- les issues pour la prochaine itération
	- les cartes, elles donnent désormais des ressouces ou des points
	- la Javadoc des méthodes classes etc...
    
  Nous avons implémenté :
   	- un système monétaire :
		- ajout de pièce 
		- gain de pièce lors de la défausse 
		- comptage de pièce pour les scores
	- une main pour chaque joueurs
	- rotation des mains à chaque fin de tours
    	- la défausse des cartes
    	- un IA random qui choisit de maniere aléatoire :
		- quelle carte de sa main elle compte jouer 
		- si elle compte défausser sa carte choisit ou non 
	- de nouvelles ressources : 
		- bois pierre argile minerai
	- système de tours qui se rapproche de plus en plus au jeu d'origine : 6 tours avec rotation des mains et une distribution uniforme
	- un affichage de l'inventaire en fin de partie 
	
  Nous avons pris en compte les demandes du client suite à la précédente itération:
    	- nous avons enlever les méthodes statique, pour pouvoir par la suite lancer plusieurs parties.
    	- Simplification du code présent dans le main de SevenWonders pour pouvoir tester les différentes étapes et ne pas surcharger la classe au fur et à mesure des semaines.
    	- Modification de la classe Carte qui correspondait à un patron Composite jugé inutile par le client. 
		La classe Carte est maintenant juste une ArrayList de cartes au régle simplifiées.
    	- Correction du contenu de la derniere milestone donnée avec une description plus précise.
    	- Précision dans les issues concernant les tests et les connexions entre task et subtask.
    	
  Nous avons testé : 
  	- la classe GénérerCarte
	
  Nous n'avons pas pu :
  	- faire tous les tests des méthodes donc nous les reportons à la prochaine itération (3)
	
    
  Nous prévoyons pour la prochaine mise à jour du jeu :
	- Commencer à séparer les cartes en différentes catégories
	- Ajout des ressources manquantes(pour les cartes marron et bleues)
	- Ajout des autres couleurs/catégories de cartes avec les noms des bâtiments
	- Attribution correcte des ressources gagnées pour les cartes bleues marrons et grises	
