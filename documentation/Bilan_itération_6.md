
<b> Bilan de l'itération 6 : </b><br><br>

  
  <br>Nous avons mis à jour :
  
        - l'affichage de la console
        - l'ajout de cartes de la nouvelle version de 7Wonders enlevant celles uniques  à l'ancienne version
        - le detail des points en fin de partie
        - les issues pour la prochaine itération
        - les IA avec les nouvelles fonctionnalités (les bonus merveilles): les IA suivent des stratégies en fonction des catégories de points, tel que : militaire, scientifique, civile et monétaire
        - la Javadoc des classes et des méthodes ainsi que leurs tests associés
        - le calcul des points grâce au bonus des merveilles
        - les tests Mockito
        - la classe sevenwonders : une partie du code est allée dans la nouvelle classe DeroulementJeu et une autre partie dans la classe ActionDeJeu
        - les IA : de nouvelles IA avec des stratégies différentes (militaire, civil, scientifique, merveille, monétaire) 
          ainsi qu'une stratégie "composite" qui en fonction de la merveille acquise choisit une stratégie de la liste précédente
        - les noms des bonus afin de mieux les comprendre
        - la structure de certaines classes afin de diminuer la duplication du code (nous sommes à l'heure actuelle à moins de 3%)
        - le déroulement de la partie, les joueurs doivent choisir tous une carte et une action avant que le premier joueur ne prononce son choix (évite la triche)
    
  Nous avons implémenté :
        
        - des Statistiques avec :
           - le score record de chaque joueur ainsi que la merveille associée 
           - les points obtenue dans les differentes catégories : 
              - Bâtiments civils
              - Scientifique
              - Victoire de Guerre
              - Defaite de Guerre
        - possibilité de lancer plusieurs parties d'un coup 
        - possibilité d'activer ou non les couleurs
        - écriture des statistiques obtenues dans un fichier .txt daté du jour et de l'heure à laquelle il a été crée
        - Les 7 merveilles (phase jour et nuit) (mode nuit prévue pour l'itération 6 de base)
        - Le système de commerce permettant aux joueurs d'acheter des ressources aux voisins adjacents
        - les méthodes gérant les bonus (ceux qui comptent le nombre de carte de couleur à droite et gauche du joueur)
        
     
  Nous avons testé : 
  
      - Toutes les classes en partie,  à l'exception des IA, et statistiques car elle vienne d'être recemment faites 

       
  Nous n'avons pas pu :
  
        - Finir tous les tests à 100 % mais nous prévoyons d'atteindre au plus proche ce nombre lors de l'itération final à savoir la suivante.
  	
  Nous prévoyons comme prochaine mise à jour : 
  
        - Améliorer/Optimiser le code selon les critères de SonarQ 
        - Vérifier que toutes les règles sont bien implémentés et respectées
        - Préparer la soutenance et le rapport 
        - Finir les Tests à ~100 %
       
