# Feedback 1 mit Frank Schimmel am 19.01: Dialoge

## Vorheriger Code: 

Vor dem Feedback hatte ich meinen Dialog in einer Map gelagert. Dabei war die erste Instanz meiner Map die Zahl welcher Dialog gerade abgespielt wird und die zweite Instanz immer was im jeweiligen Dialog gesagt wird.

Was noch gefehlt hatte, war ein richtiges System wie viele Dialoge es geben würde bis zum ende des gewünschten Dialoges. Mein eigentlicher Gedanke war dass egal welchen Dialog man wählt es immer das selbe System geben würde, allerdings ist mir damals noch nicht aufgefallen, dass ich dann keine großen Variationen im Dialog haben könnte und jeder NPC die gleiche Strucktur hätte

## Das Feedback und meine Implementierung:

Frank meinte, dass meine Idee mit der Map schon eine gute Idee wäre, allerdings fehlte mir eine wichtige Sache. Anstatt eines Intes und eines Strings sollte die nähmlich aus einem Int und eines Pairs bestehen, welcher wieder aus einem String und ner Liste aus Ints besteht. So würde mein Code im grunde das selbe sein, aber mit der extra Liste aus Ints konnte ich ein besseres System zum antworten erstellen. Wenn man jetzt eine Antwortmöglichkeit auswällt wurde mein Code den jeweiligen Int meiner Liste auswählen und diesen Int mit der Map abgleichen und zum richtigen Dialog springen.

# Feedback 2 mit Frank Schimmel am 26.01:

## Vorheriger Code:

Vor dem Feedback hatte ich viele Variablen offen und man konnte die überall umändern. Außerdem habe ich in vielen meiner Klassen eine when Schleife die nach "this" gesucht hat, damit spezielle Events passieren, wenn die Klasse aufgerufen wird

## Das Feedback und meine Implementierung:

Frank meinte ich sollte meine Variablen mit einer private Set Methode ausstatten. Diese macht, dass sie nur innerhalb meiner Klasse verändert werden kann. Dies sorgt dafür, dass nicht so viele Fehler passieren können wenn der Code läuft.

Außerdem hat er mir das Konzept von Unterklassen erzählt. Ich habe alles was ich in meine Whens rein geschrieben habe in eine Unterklasse mit overides geschrieben und konnte die bestimmten Events triggern und mit dem "super" befehl wieder die Originalle Funktion aufrufen.

Er hat mir ausserdem kleine verbesserungen an meinen Dialogen gezeigt, wodurch ich mir etwas code sparen würde.

# Feedback 3 mit Merlin Daniel Schwethelm am 11.02:

## Vorheriger Code:

Vor dem Feedback hatte ich meine ganzen Outputs direkt in meinen Klassen stehen. Dies hat dazu geführt, dass meine Klassen sehr voll aussehen und Sätze die eigentlich gleich bleiben sollten, oft kleine Grammatikalische Unterschiede hatten, die nicht existieren sollten.

## Das Feedback und meine Implementierung:

Merlin hat mir mithilfe einer enum Klasse und properties Datein gezeigt wie ich meine Dialoge alle in einzelnen TextDatein speichern kann. So konnte ich meinen ganzen Dialog an einem Ort speichern und könnte theoretisch auch meine Sprache ändern falls ich verlangen danach hätte.

# Feedback 4 mit Frank Schimmel am 19.02:

## Vorheriger Code:

Ich hatte einige meiner Variablen welche ich nur in Unterklassen gesetzt habe, mit einem protected set ausgerüstet. Ich habe außerdem versucht meine Handler Liste mit Components zu machen, allerdings scheint diese nicht zu funkionieren.

## Das Feedback und meine Implementierung:

Frank meinte zu mir, dass wenn man eine Variable nur in einer Unterklasse verwendet, sollte man diese mit protected, statt internal machen. Außerdem erklärte er mir, dass mein Händler Package ein Unterpackage von study.coco.project_code ist. Er meinte, ich sollte alle meine Packages da rein legen, alledings, würde dies zu Problemen an anderen Stellen sorgen, weshalb ich nur mein Handler Package da rein gelegt habe. Ich habe außerdem meine Antworten in eine Data Class mit hilfe von Chat-GPT gelegt und Frank hat mir etwas mehr erklärt, wie mein der Code richtig funktioniert.