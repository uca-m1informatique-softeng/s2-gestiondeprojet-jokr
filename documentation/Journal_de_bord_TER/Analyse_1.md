<h1>ANALYSE 2</h1>

Date : 24/11/2020

Dans cette analyse nous avons implémenté la liste de piorité de ressources pour l'ia Scientifique, et nous avons également ajouté le changement de place automatique entre chaque partie.

<i>A l'heure actuelle l'ia Ambitieuse n'est toujours pas en production.</i>


<h3>IA GARANTIE</h3>

Notre IA garantie est l'IA "Composite" cette dernière choisit sa stratégie en fonction de la merveille obtenue : 

- EPHESOS           IA Civile
- RHODOS            IA Civile
- BABYLON           IA Scientifique
- OLYMPIA           IA Merveille
- GIZAH             IA Civile
- HALIKARNASSOS     IA Scientifique
- ALEXANDRIA        IA Merveille

Dans cette configuration là les stats sont les suivantes :

  (Strategy.RANDOM, Strategy.SCIENTIFIQUE, Strategy.COMPOSITE, Strategy.CIVILE, Strategy.MILITAIRE, Strategy.MERVEILLE, Strategy.MONETAIRE) 
  
  7 JOUEURS sur 5000 parties 

- random        00.18 %
- scientifique  32.10 %
- conmposite    20.54 %
- merveille     10.24 % 
- civile        23.60 % 
- militaire     05.68 %
- monétaire     07.66 %


Donnée de stratégie : 

CARTES RECHERCHEES

<i>

- scientifique :

  - ("Officine","Atelier","Scriptorium")

  -  ("Dispensaire","Laboratoire","Bibliothèque","Ecole")

  -  ("Guilde des Scientifiques", "Loge", "Académie", "Observatoire", "Etude", "Université")
 
- civile :

  - ("Puits","Bains","Autel","Théâtre","Comptoir OUEST","Comptoir EST") 

  - ("Caravansérail","Forum","Statue","Aqueduc","Temple","Tribunal")

  - ("Panthéon", "Jardins", "Hôtel de Ville", "Palace", "Sénat")

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

  - 1 TISSU 1 VERRE 1 PIERRE

  - 1 TISSU 1 PAPYRUS 1 VERRE 1 BOIS 2 PIERRE 2 ARGILE 2 MINERAI  

  - 1 TISSU 1 PAPYRUS 1 VERRE 2 BOIS 3 PIERRE 2 ARGILE 2 MINERAI (4 BOIS si BABYLON jour / 3 ARGILE si BABYLON nuit)
 
- civile :

  - n/a

  - n/a

  - n/a

- militaire :

  - n/a

  - n/a

  - n/a

- monétaire :

  - n/a

  - n/a

  - n/a


Ce set de ressources permetterai à l'IA Scientifique d'acheter toutes les cartes Vertes ainsi que la guilde des scientifiques et, si jamais, la merveille Babylon 
Evidemment il faudrait pouvoir acheter les cartes permettant d'obtenir ces ressources et de preférence "les plus adapté" (c'est en cours de travail).

</i>

Les résultats sont désormais en faveur de l'IA scientifique ayant reçu une amélioration (la liste des ressources).

A faire : 

  - ajouter les priorité des ressources recherchée pour les autres IA 
  - réfléchir à comment améliorer les IA militaires et monétaires
  - développer les priorités de choix de carte pour les IA merveille et civile
  
  
  
<h3>IA AMBITIEUSE</h3>



A faire : 

  - réfléchir à quelles informations l'IA ambitieuse aura besoin pour établir sa stratégie
  - penser à comment elle devra réagir en fonction des informations reçus
  - établir le "squelette" prototype de cette IA 
  
