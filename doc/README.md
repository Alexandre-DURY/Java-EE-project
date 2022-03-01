# SA11 Sites marchands intelligents pour le commerce électronique    
## Informations générales  

Le projet de ce dépôt Git est un site de e-commerce intelligent. Il présente un seul produit (voiture). Un utilisateur qui se rend sur ce site peut se créer un compte utilisateur qu'il pourra utiliser pour être à la fois acheteur et vendeur. L'utilisateur peut consulter l'ensemble des offres sur la page d'accueil ainsi que la liste des utilisateurs du site. Ces fonctionnalités sont accessibles sans créer de compte ni se connecter. Il est possible de créer un compte pour pouvoir accéder à la partie achat/vente du site. En effet une fois connecter un utilisateur pourra créer une demande d'achat ou une offre de vente dans l'onglet "Offres". Il est aussi possible de consulter l'historique des achats et des ventes du compte. Une fois la demande d'achat ou de vente effectuée c'est l'algorithme de négociation qui s'occupe des négociations, il ne reste plus qu'as valider cette transaction.

## Installation et démarrage

Dans cette section nous allons vous détailler les étapes pour mettre en place le projet sur votre machine.
Nous allons le faire pour les deux OS Windows et Linux.

La première chose à faire pour pouvoir mettre en place notre projet est d'installer MySQL pour pouvoir y exécuter notre script sql pour mettre en place notre base de données.

--> Installer MySQL

WINDOWS
La première chose à faire est de se rendre sur le site suivant https://dev.mysql.com/downloads/installer/ afin de télécharger l'installeur MSI pour mysql.

Vous devez ensuite l'exécuter. 
Une fenetre s'ouvre, et normalement une checkbox avec écrit à côté "Launch the MySQL Instance Configuration Wizard" est présente en bas de la fenêtre. Veuillez vous assurez que cette checkbox est coché avant de cliquer sur Finish.

Ensuite une autre fenêtre s'ouvre et il vous faut cocher la checkbox "Includ Bin Directory in WINDOWS PATH" pour pouvoir appler mySQL depuis le terminal de commande. Pour notre projet vous n'avez besoin de cocher aucune autre option. 

Finalement cliquez que Execute et voilà l'installation de MySQL terminée.

LINUX

Sous linux, ouvrez un terminal et rentrez la commande suivante pour installer mysql: 
sudo apt-get install mysql -server mysql-client
puis pour modifier le mot de passe mis par défaut à root pour mysql entrez la commande:
sudo mysql admin -u root -h localhost password 'entrer ici le nv mdp'
 
Et l'installation Linux est terminée.

--> Mettre en place la BD et le jeu d'essai pour le projet

Une fois notre base donnée télécharger (base fournie dans le dossier) il suffit de l'importer pour l'utiliser.

--> IDE Eclipse pour Java EE Developers

Nous avons travailler avec l'IDE Eclipe for Java EE Developers et c'est pourquoi je vais vous expliquer comment mettre en place le projet sous Eclipe JEE. Libre à vous de choisir un autre IDE et de trouver la documentation pour importer notre projet et le lier à un serveur d'application.

Pour installer Eclipse, le plus simple est encore de telecharger Eclipse Installer (en haut de la page dans un cadre bleu) et de l'executer à la page suivante: https://www.eclipse.org/downloads/packages/.
Vous choisirez alors d'installer la version pour Java EE developers ou Entreprise Java Developers (même chose, juste écrit différemment). 
L'autre manière et de directement télécharger le package du nom de Eclipse IDE for Enterprise Java Developers. Mais nous avons eu des problèmes lors de l'installation de celui-ci sous Windows donc je vous conseille la première solution.

--> Serveur d'application Tomcat v8.5

Rendez-vous à l'adresse suivante : https://tomcat.apache.org/download-80.cgi 
Vous trouverez un lien pour télécharger Tomcat, il vous suffira juste de choisir le fichier Core format zip correspondant au système d'exploitation que vous avez.

WINDOWS
Décompresser ce fichier zip dans le répertoire où vous voudrez installer Tomcat.
Le dossier apache-tomcat-8.5.x contient alors l'installation qu'il faudra pointer depuis l'IDE.

LINUX
Créer un dossier tomcat à l'endroit suivant /usr/local
et copier puis décompresser le fichier tar téléchargé.
cp ~/apache-tomcat-8.5.x.tar.gz
tar -xvzf ~/apache-tomcat-8.5.x.tar.gz

Puis vérifier que tomcat a été installé
cd bin 
./version-sh

--> Importer le projet et lier le serveur d'application au projet

télécharger depuis le dépot git notre projet et ouvrez Eclipse et choissiez ce dossier "TDS" comme workspace.

Une fois dans Eclipse Aller sur la bare de menu en haut et selectionnez File > Import Projects from File System or Archive et selectionner le dossier "TDS" dans le dossier de votre workspace et cliquez sur Finish.

Puis aller dans Project Explorer et faites un clic droit sur le dossier "TDS" puis aller sur Run As > Run On Server.
Une fenêtre va alors s'ouvrir et vous selectionnerais manuellement le type du Server.
Dans Select the server type, choississez Apache > Tomcat v8.5 Server.
Laisser le Server's host name mis par défaut à localhost.
Cocher la checkbow Always use this server when running this project.
Dans Tomcat installation directory pointé le dossier apache-tomcat-8.5.x que je vous ai indiqué dans l'installation de Tomcat.

Vous pouvez alors lancer le serveur depuis le bouton vert Run As dans la barre d'outils. 
L'installation du projet est maintenant terminée.

## Résultats  

La page d'entrée du site est la page accueil, qui peut être atteinte une fois le projet mis en place et le serveur en marche par l'url suivante: http://localhost:8080/TDS/accueil. 
Une fois sur le site vous pouvez accéder à la liste des objets en ventes ainsi que la liste des utilisateurs. Il faudra vous créer un compte ou utiliser un des comptes déjà crée et stocker dans la base de données comme "administrateur : root" pour utiliser l'ensemble des fonctionnalités du site. Une fois connecter vous pourrez vendre et acheter un article et donc tester l'algorithme de négociation.

## Auteurs  
* Said Ali Fourkane
* Dury Alexandre
* Tracol Dimitri