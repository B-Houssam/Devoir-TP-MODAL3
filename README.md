# Devoir TP MODAL3

# Enoncé:
-Ecrire un programme Java qui permet de créer un fichier XML (ou plusieurs fichiers XML) à partir d’un projet Java quelconque. Un projet Java contient plusieurs classes Java.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*Indication:
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Vous avez deux choix (il faut justifier votre choix), soit :
-Un seul fichier XML qui représente toutes les classes du projet.
-Plusieurs fichiers XML où chaque fichier représente une seule classe de projet.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Exemple d'une structure XML pour une classe (Juste un exemple, vous devez proposer votre propre structure)
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
<Class name="Entreprise">
<Package name="Pack"/>
<attributs>3</attributs>
<list>x - y - z</list>
<attribut name="x">int</attribut>
<attribut name="y">double</attribut>
<attribut name="z">int</attribut>
<Methods nbr="3">
<Method spec="m1:int-int:int" name="m1" param="2" type="int-int" retour="int"> m1:int-int:int</Method>
<Method spec= "m2:int-double-int:double" name="m2" param="3" type="int-double-int" retour="double">m2:int-double-int:double</Method>
<Method spec= "m3:int-int:int" name="m3" param="2" type="int-int" retour="int">m3:int-int:int</Method>
</Methods>
..........
</Class>
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-Le programme à réaliser doit prendre en considération la gestion des exceptions. Ces exceptions doivent être sauvegardées dans un fichier XML dont la structure est donnée par (à respecter):

<Exceptions>
<Exception>
<Date>... </Date>
<ClassPackage> ... </ClassPackage>
<ClassName>... </ClassName>
<MethodName> ... </MethodName>
<LigneNumber> ... </LigneNumber>
</Exception>
......................
</Exceptions>


