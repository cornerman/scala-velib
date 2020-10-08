# Velib - Scala Tech Homework

## App

This is a scala-js project for a Velib-Dashboard. It shows the closest Velib stations around the Splio HQ and how many bikes are available.

Run:
```
sbt dev
```

Then visit `http://localhost:12345` with your browser.

## Data

This is a scala project for analyzing historical Velib data.

Run:
```
sbt data/run
```

Results:
```
===
Available bikes per hour
Rochechouart lamartine:
	15h: 5
	16h: 5
Gare Saint-Lazare - Isly:
	15h: 4
Victoire - Chaussée d'Antin:
	16h: 6
Saint-Marc - Feydeau:
	5h: 0
	6h: 0
	7h: 0
	8h: 0
	9h: 0
	10h: 0
	11h: 0
	12h: 0
	13h: 0
	14h: 0
	15h: 0
	16h: 0
Mairie du 9ème:
	5h: 5
	6h: 5
	7h: 8
	8h: 10
	9h: 11
	10h: 10
	11h: 11
	12h: 9
	13h: 9
	14h: 8
	15h: 8
	16h: 3
Saint Georges - d'Aumale:
	16h: 1
Enghien - Faubourg Poissonnière:
	15h: 0
	16h: 0
Vivienne - Montmartre:
	5h: 6
	6h: 8
	7h: 11
	8h: 20
	9h: 21
	10h: 20
	11h: 22
	12h: 22
	13h: 24
	14h: 25
	15h: 28
	16h: 22
Uzès - Montmartre:
	16h: 21
Choiseul - Quatre Septembre:
	5h: 1
	6h: 7
	7h: 27
	8h: 37
	9h: 35
	10h: 33
	11h: 30
	12h: 31
	13h: 32
	14h: 31
	15h: 29
	16h: 15
Daunou - Louis le Grand:
	16h: 11
Boudreau - Auber:
	15h: 8
	16h: 1
Taitbout - La Fayette:
	16h: 2
Rossini - Laffitte:
	5h: 9
	6h: 12
	7h: 19
	8h: 28
	9h: 19
	10h: 19
	11h: 19
	12h: 20
	13h: 19
	14h: 21
	15h: 20
	16h: 9
Faubourg Montmartre - La Fayette:
	16h: 2
Louis le Grand - Italiens:
	5h: 5
	6h: 8
	7h: 19
	8h: 23
	9h: 24
	10h: 25
	11h: 25
	12h: 26
	13h: 24
	14h: 23
	15h: 23
	16h: 20
Filles Saint-Thomas - Place de la Bourse:
	5h: 5
	6h: 11
	7h: 32
	8h: 47
	9h: 46
	10h: 43
	11h: 39
	12h: 36
	13h: 33
	14h: 30
	15h: 26
	16h: 12
Favart - Italiens:
	5h: 2
	6h: 4
	7h: 6
	8h: 9
	9h: 9
	10h: 8
	11h: 7
	12h: 7
	13h: 7
	14h: 6
	15h: 6
	16h: 3
Laffitte - Italiens:
	5h: 5
	6h: 9
	7h: 25
	8h: 29
	9h: 18
	10h: 15
	11h: 13
	12h: 14
	13h: 16
	14h: 19
	15h: 16
	16h: 7
Mathurins - Auber:
	16h: 14
Bibliothèque Nationale:
	5h: 5
	6h: 6
	7h: 11
	8h: 14
	9h: 9
	10h: 12
	11h: 13
	12h: 15
	13h: 15
	14h: 13
	15h: 11
	16h: 2
===
Available docks per hour
Rochechouart lamartine:
	15h: 18
	16h: 19
Gare Saint-Lazare - Isly:
	15h: 23
Victoire - Chaussée d'Antin:
	16h: 18
Saint-Marc - Feydeau:
	5h: 37
	6h: 37
	7h: 37
	8h: 37
	9h: 37
	10h: 37
	11h: 37
	12h: 37
	13h: 37
	14h: 37
	15h: 37
	16h: 37
Mairie du 9ème:
	5h: 19
	6h: 19
	7h: 16
	8h: 14
	9h: 13
	10h: 14
	11h: 13
	12h: 15
	13h: 15
	14h: 16
	15h: 16
	16h: 21
Saint Georges - d'Aumale:
	16h: 22
Enghien - Faubourg Poissonnière:
	15h: 33
	16h: 33
Vivienne - Montmartre:
	5h: 32
	6h: 30
	7h: 27
	8h: 18
	9h: 17
	10h: 18
	11h: 16
	12h: 16
	13h: 14
	14h: 13
	15h: 10
	16h: 16
Uzès - Montmartre:
	16h: 8
Choiseul - Quatre Septembre:
	5h: 42
	6h: 36
	7h: 16
	8h: 6
	9h: 8
	10h: 10
	11h: 13
	12h: 12
	13h: 11
	14h: 12
	15h: 14
	16h: 28
Daunou - Louis le Grand:
	16h: 6
Boudreau - Auber:
	15h: 6
	16h: 13
Taitbout - La Fayette:
	16h: 51
Rossini - Laffitte:
	5h: 23
	6h: 20
	7h: 13
	8h: 4
	9h: 13
	10h: 13
	11h: 13
	12h: 12
	13h: 13
	14h: 11
	15h: 12
	16h: 23
Faubourg Montmartre - La Fayette:
	16h: 32
Louis le Grand - Italiens:
	5h: 27
	6h: 24
	7h: 13
	8h: 9
	9h: 8
	10h: 7
	11h: 7
	12h: 6
	13h: 8
	14h: 9
	15h: 9
	16h: 12
Filles Saint-Thomas - Place de la Bourse:
	5h: 50
	6h: 44
	7h: 23
	8h: 8
	9h: 9
	10h: 12
	11h: 16
	12h: 19
	13h: 22
	14h: 25
	15h: 29
	16h: 43
Favart - Italiens:
	5h: 9
	6h: 8
	7h: 5
	8h: 3
	9h: 3
	10h: 3
	11h: 4
	12h: 4
	13h: 4
	14h: 5
	15h: 5
	16h: 8
Laffitte - Italiens:
	5h: 27
	6h: 23
	7h: 7
	8h: 3
	9h: 14
	10h: 17
	11h: 19
	12h: 18
	13h: 16
	14h: 13
	15h: 16
	16h: 25
Mathurins - Auber:
	16h: 22
Bibliothèque Nationale:
	5h: 11
	6h: 10
	7h: 5
	8h: 2
	9h: 7
	10h: 4
	11h: 3
	12h: 1
	13h: 1
	14h: 3
	15h: 6
	16h: 14
===
Top 3 stations with available bikes
Uzès - Montmartre: 21
Choiseul - Quatre Septembre: 25
Filles Saint-Thomas - Place de la Bourse: 30
```
