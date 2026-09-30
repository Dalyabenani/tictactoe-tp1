# TP 1 — Écriture de tests

## 2. Tests fonctionnels

### Question 2.1 : Classes d'équivalence et cas aux limites

#### Classes d'équivalence 
1. **Coup valide** : Jouer dans une case vide.
2. **Coup invalide** : Jouer avec des coordonnées hors limites (ligne -1 ou colonne 3).
3. **Coup invalide** : Jouer sur une case déjà remplie.
4. **Coup invalide** : Un joueur essaie de jouer deux fois de suite.

#### Classes d'équivalence
1. **Partie en cours** : Moins de 5 coups joués, ou grille non pleine sans alignement.
2. **Partie gagnée** : 3 symboles identiques alignés (ligne, colonne ou diagonale).
3. **Partie nulle (Égalité)** : Grille pleine sans aucun alignement gagnant.

#### Cas aux limites
- Le 1er coup de la partie (vérifier que 'X' commence bien).
- Le 5e coup (première possibilité théorique de gagner).
- Le 9e et dernier coup (grille qui se remplit au moment exact d'une victoire ou d'une égalité).

---

## Question 2.2 : Cas de tests à vérifier

Voici la liste des cas de tests à effectuer pour valider le comportement du jeu :

1. Initialisation de la partie
- Action : Lancer une nouvelle partie.
- Résultat attendu : La grille doit être entièrement vide et c'est au joueur X de jouer.

2. Coup valide
- Action : Le joueur X joue sur la case (0, 0) qui est vide.
- Résultat attendu : La case (0, 0) contient 'X' et le tour passe au joueur O.

3. Tentative de jouer sur une case occupée
- Action : Le joueur O tente de jouer sur la case (0, 0) déjà prise par X.
- Résultat attendu : Le coup est refusé, la case reste inchangée et c'est toujours au joueur O de jouer.

4. Tentative de jouer hors de la grille
- Action : Le joueur X tente de jouer sur la case (-1, 0) ou (3, 3).
- Résultat attendu : Le jeu refuse le coup et l'état de la grille ne change pas.

5. Victoire en ligne
- Action : Le joueur X aligne 3 'X' sur la première ligne (0,0), (0,1) et (0,2).
- Résultat attendu : Le jeu annonce la victoire de X et la partie s'arrête.

6. Victoire en colonne
- Action : Le joueur O aligne 3 'O' sur la deuxième colonne (0,1), (1,1) et (2,1).
- Résultat attendu : Le jeu annonce la victoire de O et la partie s'arrête.

7. Victoire en diagonale
- Action : Le joueur X aligne 3 'X' sur la diagonale principale (0,0), (1,1) et (2,2).
- Résultat attendu : Le jeu annonce la victoire de X et la partie s'arrête.

8. Partie nulle (Égalité)
- Action : Les joueurs remplissent les 9 cases sans aligner 3 symboles identiques.
- Résultat attendu : Le jeu annonce une égalité et la partie s'arrête.

## 3. Tests structurels

### Question 3.1 : Implémentation des cas de tests pour la classe `Board`

J'ai écrit la classe `BoardTest` avec JUnit 5 pour tester les cas clés :

1. **`testInitialState`** : Vérifie qu'à la création du plateau, le premier joueur est bien `X`, le gagnant est `null` et le jeu est en mode `IN_PROGRESS`.
2. **`testValidMove`** : Vérifie que la méthode `mark()` alterne bien le tour du joueur (`flipCurrentTurn()`) après un coup valide.
3. **`testMarkOccupiedCell`** : Vérifie la méthode privée `isValid()` : si une case est déjà prise, la méthode `mark()` ne fait rien et le tour ne change pas.
4. **`testOutOfBounds`** : Vérifie que passer des coordonnées hors limites (ex. `< 0` ou `> 2`) est bloqué sans planter.
5. **`testHorizontalWin`** : Vérifie la méthode `isWinningMoveByPlayer()` : lorsque 3 symboles sont alignés, `state` passe à `FINISHED` et `winner` enregistre le bon joueur.
6. **`testCannotPlayAfterGameIsFinished`** : Vérifie la condition limite dans `isValid()` qui empeche de rejouer si `state == GameState.FINISHED`.