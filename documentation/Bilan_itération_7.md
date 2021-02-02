
<h1> Bilan de l'itération 7 : </h1>
<i>
Lors de cette itération le but n'était plus d'avancer dans les fonctionnalités mais de consolider le code, améliorer les méthodes, 
approfondir les tests etc...

Voici le bilan détaillé de l'itération 7 
</i>  
  <h3>Pour améliorer le code nous avons grâce à SonarQube :</h3>
  
        - corrigé plus de 500 Code Smells, pour résoudre ces Code Smells :
            - beaucoup étaient à cause de duplication de String, nous avons donc ajouté deux Enumérations :
                - une pour les ressources : regroupant les noms de ressources, des bonus, des couleurs
                - une pour les cartes : regroupant les noms de cartes 
            - nous avons du renommer des packages, des classes ainsi que des variables, etc... pour correspondre aux normes du langage de programmation
            - modifier des tests qui étaient défaillants ou qui ne couvraient pas toutes les conditions/lignes d'une méthode 
            - repenser certains accès entre les classes 
            - etc ...
        - supprimé les duplications de code dans les différentes classes, en général en ajoutant des méthodes communes  
        - les tests couvrent maintenant plus de 90% du code alors qu'avant nous n'étions qu'à ~50% 
        - complété la Javadoc de toutes les méthodes
        
<h3>  Au niveau des Statistiques : </h3>
      
      - Nous avons amélioré l'affichage
      - Il y a désormais plus de statistiques :
          - Taux d'achat par carte pour chaque joueur 
          - Moyenne d'étages de merveille construite par joueurs
          - Moyenne des points gagnés grâce aux pièces
          - Moyenne des points gagnés grâce aux cartes bonus (guildes,...)
          
<h3>  Concernant le serveur : </h3>
      
      - Pas de grands changements si ce n'est qu'il est désormais possible d'envoyer une unique partie détaillée au serveur ( et donc l'enregistrer 
      sous txt à la date de création ) grâce à l'implémentation d'un StringBuilder, il faut pour cela désactiver les couleurs sinon la retranscription est corrompu 
      par les codes couleurs
          
<h3>  Les Arguments à mettre en paramètres</h3>

      - Il est désormais possible de lancé la partie en : simple couleur ou non/ en multiple avec stats au serveur etc... depuis le terminal, voici comment faire : 
          - 1ème argument : indiquer "false" pour afficher sans couleur (peut être utile si l'on souhaite lire directement le .txt ou si on utilise un terminal qui ne reconnait pas le code ANSI)
          - 2ème argument : indiquer le nombre de parties à lancer (une si rien d'indiqué ou si ce n'est pas un entier)
          - 3ème argument : indiquer le nombre de joueurs qui vont participer aux parties (3-7)
          - 4ème argument : indiquer "false" si on ne veut ne lancer qu'une partie (ne lance pas les statistiques et n'écrit pas dans un fichier)
          - Par défaut : true 1 3 false : on lance une partie à 3 joueurs que l'on affiche sur la sortie standard avec les couleurs
          
<h3>  Pour ce qui est des IA : </h3>
  
      - Les IA ont désormais une liste de cartes qu'elles voudront en priorité acheter, il suffira d'ajouter/supprimer/réordonner 
      la liste de chaque IA pour modifier sa façon de jouer 
      - Elles pourront également choisir des ressources à privilégier, c'est à dire si une IA souhaite avoir plus de bois elle 
      pourra rechercher la ressource parmis les cartes et ainsi obtenir un bâtiment qui lui en produit 
      - Amélioration de l'IA "composite", elle choisit mieux la stratégie en fonction de sa merveille  
  
  
<h3>  Les tests : </h3>
  
      - Mise à part les classes suivante : 
          - SevenWonders (37%) ( la classe du main de la partie ) , 
          - Serveur (37%) ( la classe du main du serveur )
       Toutes les classes restantes ont un coverage de tests supérieur à 90%, ce qui nous donne en moyenne une couverture du code de ~93%

       
<h3>  Ce que nous n'avons pas pu faire :</h3>
  
        - Finir d'implémenter et de respecter à 100% les règles suivantes : 
            - le multi-choix : selon comment est parcouru le multi-choix on ne peut pas forcément payer exactement comme dans le vrai jeu, mais moins ou autant.
            - les joueurs ne peuvent pas acheter/marchander de ressources "multi-choix" voisines, étant donné que le multi-choix 
              n'est pas aboutis à proprement parler. 
              Ils peuvent néanmoins toujours marchander les ressources des merveilles, bâtiments simples, etc...
              (Rappel : le mutli-choix comme nous l'appelons représente les gains des bâtiments tel que la FOSSE ARGILEUSE donne le multi-choix ARGILE/MINERAIS) 
              (Cela étant dis tout les IA/joueurs en sont afféctés et cela ne fausse donc pas les stats, en cas de résolution de ce problème 
              les joueurs marqueraient tous proportionellement plus de points .)
        - Corriger les 5 derniers Code Smells (dont 3 dues à la compléxité de 3 méthodes)
        
<h3>  Pour la prochaine mise à jour (même si techniquement elle n'aura pas lieu) nous prévoyons: </h3>
  
        - De faire aboutir la règle du multi-choix 
        - Permettre aux joueurs d'acheter chez les multi-choix voisins
        - Tester le code jusqu'à frôler les ~100%
        - Réfléchir à comment optimiser le code (réduire la compléxité, etc...)
        - Développer/Ajouter des IA et peut être modifier leurs priorités
        - Ajouter encore plus de statistiques et pourquoi pas sous forme de graphiques, etc... 
       
