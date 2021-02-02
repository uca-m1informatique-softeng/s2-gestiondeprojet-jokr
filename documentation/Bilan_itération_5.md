
<b> Bilan de l'itération 5 : </b><br><br>

<i>
  Nous avons rédigé un nouveau rapport vis-à-vis des "Design Pattern".</i>
  
  <br>Nous avons mis à jour :
  
        - l'affichage de la console
        - les issues pour la prochaine itération
        - les IA avec les nouvelles fonctionnalité (à savoir les merveilles)
        - la Javadoc. 
        - le calcul des points grâce au bonus des cartes Guildes
        - l'inventaire du joueur en rajoutant la merveille et les bonus de la merveille ainsi qu'un compteur de couleur de cartes
        - le pom.xml pour SonarQube
        - les tests Mockito
        - le système de gagnant qui prend en compte ce lui qui a le plus de pièce en cas d'égalité
        
    
    
  Nous avons implémenté :
  
        - Un serveur ( dans lequel on envoie certaines donnée à des fins de statistiques prochaines)
        - Un plateau (regroupant les inventaires et les joueurs de la partie)
        - Les 7 merveilles (phase jour et nuit) (mode nuit prévue pour l'itération 6 de base)
        - Le système de commerce permettant aux joueurs d'acheter des ressources aux voisins adjacents
        - les méthodes gérant les bonus (ceux qui comptent le nombre de carte de couleur à droite et gauche du joueur)
        
     
  Nous avons testé : 
  
      - les classes suivantes :
    
        - IA logique
        - IA random
        - IA semilogique
        - Plateau 
        - Merveille
        - GenererMerveille
        - Data
        - Construction
	    - Carte
	    - setInventaire
	    - Inventaire
        - Joueur
	    - SevenWonders
	
	
       
  Nous n'avons pas pu :
  
        - Finir tous les tests (dont ceux complexes)
  	
  Nous prévoyons comme prochaine mise à jour : 
  
        - Ajout de Statistiques
        - Amélioration d'IA
        - Ajout d'IA
        - Amélioration du code ( alléger, optimiser )
        - Gérer les derniers bonus qui ne sont toujours pas traités 
        - Proposer aux joueurs à qui acheter des ressources et mieux gérer les achats (réduction, etc...)
        - Finir les Tests au mieux 
       
