<h1>ANALYSE 3</h1>

Date : 06/12/2020

Dans cette analyse nous avons implémenté la liste de piorité de ressources pour l'IA Composite et une modification de la liste des priorité de l'IA Scientifique.
Egalement, nous avons ajouté lde nouvelles statistiques affichantp pour chaque joueurs, pour chaque merveille, que ce soit de jour ou de nuit, le taux de victoire par rapport au taux d'obtention de cette merveille.

(exemple sur X parties, si Dora obtient 50 fois la merveille babylon de nuit et quelle gagne 25 de ces parties, alors son taux est de 50% pour cette merveille)

<i>A l'heure actuelle l'ia Ambitieuse n'est toujours pas en production.</i>


<h3>IA GARANTIE</h3>
Grâce à ces nouvelles statistiques nous nous rendons compte que l'IA Civile est bien plus rentable par rapport aux autres IA quelque soit la merveille, également, pour les IA Civiles et Scientifiques il vaut mieux ne pas perdre de temps à contruire les merveilles quelles qu'elles soient.

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

- random        00.08 %
- scientifique  24.68 %
- civile        28.77 %
- monétaire     04.05 %
- militaire     03.27 %
- merveille     08.09 % 
- conmposite    31.05 %

 

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

  - 1 VERRE 1 PAPYRUS 1 TISSU 1 BOIS 2 PIERRE 2 ARGILE 2 MINERAI  

  - 1 TISSU 1 PAPYRUS 1 VERRE 2 BOIS 3 PIERRE 2 ARGILE 2 MINERAI
 
- civile :

  - 1 PIERRE 1 TISSU 1 MINERAI 1 ARGILE 1 VERRE

  - 1 (3 si on a pas Bains) PIERRE 1 TISSU 1 MINERAI 1 ARGILE 1 VERRE 1 BOIS + 1 PAPYRUS si on a pas Théâtre 

  -  2 PIERRE 1 TISSU 1 MINERAI 2 ARGILE 1 VERRE 2 BOIS   

- militaire :

  - n/a

  - n/a

  - n/a

- monétaire :

  - n/a

  - n/a

  - n/a


Ce set de ressources permetterai à l'IA Scientifique et Civile d'acheter toutes les cartes nécessaire, ( à savoir verte / bleue), on analyse aussi le cas ou on aurait déjà une carte chainée qui nous eviterait d'avoir plus de ressources que nécessaire.

</i>

Les résultats sont désormais en faveur de l'IA Civile ayant reçu une amélioration (la liste des ressources & carte).

A faire : 

  - ajouter les priorité des ressources recherchée pour les autres IA 
  - réfléchir à comment améliorer les IA militaires et monétaires
  - développer les priorités de choix de carte pour les IA merveille
  
  
  
<h3>IA AMBITIEUSE</h3>



A faire : 

  - réfléchir à quelles informations l'IA ambitieuse aura besoin pour établir sa stratégie
  - penser à comment elle devra réagir en fonction des informations reçus
  - établir le "squelette" prototype de cette IA 
  
