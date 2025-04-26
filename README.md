# Key Consulting Test Technique
 ## Technologies utilisées

**Backend :**
- Java 17
- Spring Boot 3.4.5

**Frontend :**
- Angular 19 ​
- Node 20.19.1
## Lancement du projet en local

### Prérequis

**Backend :**
- Java 17 installé
- Maven installé

**Frontend :**
- Node.js (version compatible avec Angular 19, node 20.19.1 par exemple)
- Angular CLI installé globalement

### Démarrer le Backend

Naviguez dans le répertoire du backend :​
    
    cd backend

Compilez et lancez l'application Spring Boot :​
    
    mvn spring-boot:run

L'application sera accessible sur http://localhost:8080.
### Démarrer le Frontend

Naviguez dans le répertoire du frontend :​

    cd frontend

Installez les dépendances :​

    npm install

Lancez l'application Angular :​

    ng serve

L'application sera accessible sur http://localhost:4200.

## Tests
### Backend

Pour exécuter les tests unitaires du backend :​

    mvn test

### Frontend

Pour exécuter les tests unitaires du frontend :​
GitHub

    ng test

## Usage de l'application
L'application se présente sous la forme d'un "tableau" avec volet dépliant (expansion-panel). Le clic sur une ligne fait apparaitre le reste des informations des tâches.

En haut à droite se trouve trois boutons :

- Filtre des données : applique ou retire un filtre n'affichant que les tâches en attente.
- Création d'une nouvelle tâche : affiche une pop-in permettant de créer une nouvelle tâche.
- Accès au Swagger : ouvre l'affichage du Swagger dans un nouvel onglet, permettant d'avoir accès aux informations de l'API.
