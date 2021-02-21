# Gestion de projet

## Groupe JOKR : 
- Ossama ASHRAF
- Kevin LEVY
- Jérémy HIRTH DAUMAS
- Ralph EL CHALFOUN

## Livraison : 
- [X] Livraison 1 : https://github.com/uca-m1informatique-softeng/s2-gestiondeprojet-jokr/releases/tag/livraison1

##L’objectif est de poursuivre l’apprentissage du Génie Logiciel commencé au semestre 1 dans les cours Projet de Développement et Génie Logiciel.

Actuellement à la livraison n°2 nous avons : 

- Implémenté Travis, Spring & Docker Compose
- Nos statistiques dans Spring avec un 1er service REST
- Des tests d’intégrations 
- Nos modules moteur et client sont indépendant l'un de l'autre 
- Le moteur lors de la communication avec le client va envoyer un objet DataToClient(Une liste de carte -sa main/défausse-, Son inventaire, Le plateau de Jeu) 
- Plusieurs actions peuvent être demandées par le moteur au client "http://" + adresse_IPV4_du_JOUEUR + ":PORT_DU_JOUEUR_/" + 
  -  "/jouer/choixCarte" return Integer : connaitre quelle carte de sa main il souhaite jouer
  -  "/jouer/constructMerveille" return Integer : connaitre quelle carte sacrifier pour jouer la merveille  
  -  "/jouer/Merveille" return Boolean : connaitre si le joueur souhaite jouer sa merveille
  -  "/jouer/GratuitementDanslaDefausse" return Boolean : connaitre quelle carte jouer depuis la défausse 
  -  "/jouer/Defausse" return Boolean : connaitre si le joueur veut défausser sa carte
  -  "/strategie" -- Uniquement pour les statistiques -- : connaitre la stratégie du joueur 
- Les actions demandées par le Client au moteur "http://" + adresse_DU_SERVEUR + ":PORT_DU_SERVEUR_/" 
  -  "/connexion/" return Boolean : Demande de connexion au Moteur du jeu  
