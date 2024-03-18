# Bugged Challenge Flux

## Contexte
Le projet :bug: BuggedFlux est un exercice qui vous permettra de prendre conscience de l’importance de certains petits détails en programmation Java avec les flux.

## Explications
Ce projet NetBeans très simple qui vous est donné réalise à peu de choses près ceci :
- Créer un tableau de `bytes` à deux dimensions d’une taille aléatoire rempli de valeurs aléatoires
- Sauvegarder ce tableau dans un fichier sur disque
- Relire ce tableau du fichier
- Vérifier qu’il contient bien la même chose que celui qu’on avait préalablement écrit
- Affiche la durée totale des opérations en [ms]

Voici ce que l’on devrait voir sur la console si tout se passait correctement :
```
L'écriture a réussi ! Le tableau fait 798 x 1007 = 803586 bytes
La lecture a réussi !
Le contenu est identique !!!
L'ensemble a pris 187 ms.
``` 

Malheureusement, le code qui vous a été remis n’est de loin pas optimal. Et .. il comporte même un ou plusieurs bugs !
``` 
Voici ce que l’on devrait voir sur la console :
L'écriture a réussi ! Le tableau fait 795 x 975 = 775125 bytes
La lecture a réussi !
Le contenu n'est pas identique !!!
L'ensemble a pris 4321 ms.
```
Vous noterez au passage la grande différence de vitesse/performance… révélateur d’un problème ?

### Travail à réaliser

- Prendre connaissance du code fourni et vous en imprégner
- Trouverez-vous les `3` problèmes de nature différente qui s’y cachent ?