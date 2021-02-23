# Gestion de projet

## Groupe JOKR : 
- Jérémy HIRTH DAUMAS
- Ossama ASHRAF
- Kevin LEVY
- Ralph EL CHALFOUN

## Livraison : 
- [X] Livraison 1 : https://github.com/uca-m1informatique-softeng/s2-gestiondeprojet-jokr/releases/tag/livraison1

## Livraison : 
- [X] Livraison 2 :

L’objectif est de poursuivre l’apprentissage du Génie Logiciel commencé au semestre 1 dans les cours Projet de Développement et Génie Logiciel.
Actuellement à la livraison n°2 nous avons : 

- Implémenté Travis, Spring & Docker Compose
- Les Statistiques sont maintenant transférées du moteur au serveur avec Spring. Les eventListeners de SocketIOServer
  ont été remplacées par des routes POST avec un RestController Spring, et le moteur utilise son RestTemplate pour faire
  les appels nécessaires, grâce à l'IP du serveur renseigné en paramètres.
- Un test d'intégration Spring sur le moteur, qu'il faudra tester lorsque le liens entre le moteur et les clients seront
  fonctionnels.
- Nos modules moteur et client sont indépendant l'un de l'autre 
- Le moteur lors de la communication avec le client va envoyer un objet DataToClient(Une liste de carte -sa main/défausse-, Son inventaire, Le plateau de Jeu) 
- Plusieurs actions peuvent être demandées par le moteur au client <b>"http://" + adresse_IPV4_du_JOUEUR + ":PORT_DU_JOUEUR_/" + </b> 
  -  <b>"/jouer/choixCarte"</b>  return Integer : <i> connaitre quelle carte de sa main il souhaite jouer</i>
  -  <b>"/jouer/constructMerveille"</b>  return Integer :<i> connaitre quelle carte sacrifier pour jouer la merveille  </i>
  -  <b>"/jouer/Merveille"</b>  return Boolean :<i> connaitre si le joueur souhaite jouer sa merveille</i>
  -  <b>"/jouer/GratuitementDanslaDefausse"</b>  return Boolean :<i> connaitre quelle carte jouer depuis la défausse </i>
  -  <b>"/jouer/Defausse"</b>  return Boolean : connaitre si le joueur veut défausser sa carte</i>
  -  <b>"/strategie"</b>  -- Uniquement pour les statistiques -- :<i> connaitre la stratégie du joueur </i>
- Les actions demandées par le Client au moteur <b>"http://" + adresse_DU_SERVEUR + ":PORT_DU_SERVEUR_/"</b> 
  -  <b>"/connexion/"</b>  return Boolean :<i> Demande de connexion au Moteur du jeu  </i>
