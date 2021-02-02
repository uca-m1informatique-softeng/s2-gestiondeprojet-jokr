
Bilan de l'itération 4 :

Lors de cette itération nous avons pris la décision d'inverser quelques fonctionnalités avec l'itération 5 (mise à jour).

  Nous avons mis à jour :
      - l'affichage.
      - les issues pour la prochaine itération- Les cartes (Prix)- la Javadoc. 
      - la répartition des cartes violettes en fonction du nombre de joueur.
      - l'inventaire du joueur, en rajoutant une liste de noms de cartes qu'il possède.
      - le joueur qui peut ainsi accéder à son inventaire directement sans passer par le jeu ( facilité pour les prochaines IA, qui devront voir leur ressource et jouer en fonction).
      - l'échange de mains selon les Âges (il faut alterner).
    
    
  Nous avons implémenté :
      - Les prix des cartes
      - une méthode de verification, qui sert à savoir si un joueur peux contruire la carte qu'il a demandé à jouer.
      - une IA logique qui ne proposera de construire que les batiments qu'elle pourra, si elle ne peut pas elle décidera de mettre à la défausse une carte. 
      - le mutlichoix : bonus de carte qui permet de choisir quelle ressource utiliser (pour contruire un batiment) parmis quelques unes en fonction du bonus. 
      - la contruction gratuite si le joueur possède le batiment précédant demandé.
      - l'interdiction d'avoir deux bâtiments identiques.
      
     
  Nous avons testé : 
      - les classes suivantes :
      	- Construction
	- Carte
	- setInventaire
	- Inventaire
	- Joueur
	- SevenWonders
	
	
        
	
	
  Nous n'avons pas pu :
        - Implémenter le système d'achat chez les autres joueurs. 
        - Mettre le serveur, ni Sonarq.  
  	
  Nous prévoyons comme prochaine mise à jour : 
      -  Système d'achat chez les autres joueurs. 
        - Implémentation de quelques merveilles. 
        - Système de construction de merveille. 
        - Serveur et Sonarq.
