git init <- zur Initialisierung des git repositories
danach
git clone <remote> <- wobei f�r remote in unserem Falle ohne die "<>" https://github.com/LUH-Smash-Bros/Informatik.git
bspw. mit command shell: $ git clone https://github.com/LUH-Smash-Bros/Informatik.git
danach
git branch -a <- verwenden weil Ihr mit -a auch noch die hidden also f�r euch noch versteckten branches seht
danach
git checkout origin/branch-name <- wobei f�r branch-name die Branch steht in die Ihr lokal wechseln wollt. Vorsicht mit origin/ legt Ihr den HEAD also Suchkopf automatisch auf den aktuellen und letzten Commit der get�tigt wurde, ohne origin/ wechselt ihr einfach nur die branch ohne den Suchkopf anzupassen.
danach
git pull <remote> <branch-name> <- siehe oben!
bspw. mit command shell: $ git pull https://github.com/LUH-Smash-Bros/Informatik.git Analysis-B <- damit holt Ihr euch die aktuellsten Dateien und Pfadstrukturen auf euer derzeit aktuelles lokales git repository, also dort wo ihr urspr�nglich git init gemacht habt und idealerweise vorher auch git clone gemacht habt.

Falls Ihr neue Sachen habt die Ihr hochladen wollt, geht es am schnellsten und am einfachsten indem ihr folgendes tut: 

-> die git bash bzw. eine Kommandokonsole von git oder linux mit git verbunden �ffnen
-> euch in euer lokales Repository reinklinken
-> in die branch maneuvrieren in die ihr die Sachen ablegen wollt.
-> Pfadstruktur falls n�tig aufbauen bspw. Skript/hier_soll_was_hin.txt (das hei�t die Dateien inklusive m�glicher Ordner in die lokale Repository per copy paste packen, also per drag and drop falls ihr so wollt - aber immer aufpassen auf die branch das hei�t nicht im master das rein packen sondern die branch zu der es geh�rt)
-> git add Skript/hier_soll_was_hin.txt
-> dann git commit -m "irgendein String hier als Beschreibung reinschreiben"
-> dann git push origin <branch-name> <- bspw. mit command shell: $ git push origin Analysis-B
-> Prima alle anderen k�nnen nun die aktualisierte Fassung sich via git pull auf ihre Lokalen repositories ziehen.

F�r mehr insider stuff hier ein paar gute links, die ich schon seit Ewigkeiten habe.
http://rogerdudler.github.io/git-guide/
https://githowto.com/create_a_project