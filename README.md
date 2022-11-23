Popescu Mihai Costel 324CD

Prezentarea functionalitatii claselor:
---------------------------------------------Main---------------------------------------------
    Parcurg array-ul de jocuri, iar pentru fiecare joc se creaza doi jucatori, se intializeaza
deckurile cu care vor juca, se seteaza eroii pentru fiecare jucator, se seteaza cine va incepe
meciul, iar apoi se parcurge array-ul de actiuni, in functie de randul jucatorului se va apela
interpretorul in care se executa inctructiunile pentru fiecare actiune.

----------------------------------------InitDecks---------------------------------------------
    In clasa InitDecks sunt prezente doua campuri deckPlayer1 si deckPlayer2, iar in contrucotr
apoi fiecare deck corespunzator jucatorului este parcurs card cu card si pentru fiecare card 
este apelata functia CardTypeIdentificator, iar apoi se amesteca, cartile din fiecare pachet.

----------------------------------CardTypeIdentificator---------------------------------------
    In clasa CardTypeIdentificator este prezent un singur camp card de tip Card si un constructor
in care se identifica dupa nume tipul card-ului (Minion, Environmet, Hero acestea fiind niste
clase extinse din clasa Card) si se seteaza campurie caracteristice pentru fiecare tip de card
in parte.

------------------------------------------Player----------------------------------------------
    In clasa Player sunt prezente urmatoarele campuri idx (indexul jucatorului 1/2),
turn(indica daca este randul jucatorului la mutare sau nu), noTurns (de cate ori mai este randul
jucatorului in runda curenta), inHandCard (cardurile pe care le are in mana jucatorul), hero 
(eroul jucatorului), deck, mana, noRound (numarul rundei curente), frontRow (ranadul din fata al
jucatorului), backRow (randul din spate al jucatorului), environmentInHand (cartile de tip
environment care le are jucatorul in mana). Metoda drawCard adauga primul card din deck in mana
jucatorului, apoi se verifica dupa nume daca, cartea este de tip environment aceasta este
adaugata in environmentInHand, iar pe urma este scoasa din deckul jucatorului. Metoda endTurn
seteaza campul turn al jucatorului curent pe false, iar pentru celalalt jucator pe true si
seteaza numarul de ture al jucatorului curent pe 0. Metoda enoughManaToPlaceCard verifica 
daca jucatorul are suficienta mana pentru a folosi cardul restectiv. Metoda ifAttackTank 
vreifica daca jucatorul curent ataca o carte de tip tank a celuilalt jucator.


--------------------------------------RemoveDeadCard------------------------------------------
    In clasa RemoveDeadCard in constructorul acesteia se parcrge masa de joc si se verifica viata
fiecarui card, iar daca aceasta este mai mica sau egala cu 0 aceasta este scoasa de pe masa.

----------------------------------------Interpret---------------------------------------------
    In clasa Interpret sunt prezente urmatoarele clase cmd (actiunea curenta), player1Win 
(castigurile jucatorului 1), player2Win (castigurile jucatorului 2). In constructorul acestei
clase se verifica numele comenzii si se executa instructiunile fiecarei comenzi care nu depinde
de randul jucatorului, iar pentru cele care depind de randul jucatorului se verifica acest camp
pentru jucator si se executa actiunile corestunzatoare fiecarei comenzi.

------------------------------------------Card------------------------------------------------
    Clasa Card este o clasa generica a unui card cu campurile description, colors, name aceste
campuri fiind prezente in fiecare card.

--------------------------------------Environment---------------------------------------------
    Clasa Environment este o clasa extinsa de la clasa Card care mai contine in plus mana si
implementarea pentru metoda action care verifica dupa nume ce card environment este si 
implementeaza actiunile specifice fiecarui card de tip environment.

-------------------------------------------Hero-----------------------------------------------
    Clasa Hero este o clasa extinsa de la clasa Card care mai contine in plus mana, health,
frozen (daca eroul a atacat in runda curenta) si implementarea pentru metoda action care
verifica dupa nume ce card hero este si implementeaza actiunile specifice fiecarui
card de tip hero.

-----------------------------------------Minion-----------------------------------------------
    Clasa Minion este o clasa extinsa de la clasa Card care mai contine in plus mana, health,
attackDamage, frozen (daca minionul a atacat in runda curenta), frozenForRound (daca minionul
a fost ingetat pentru runda uramtoare de catre o carte environment), unfrozenRound (runda din 
care carte isi poate folosi puterile), tank (daca, cartea este de tip tank) si
implementarea pentru metoda action care verifica dupa nume ce card minion este si 
implementeaza actiunile specifice fiecarui card de tip minion.

----------------------------------------EndRound----------------------------------------------
    Clasa EndRound prin constructor seteaza numarul de ture pentru fiecare jucator la 1 si
invereseaza valoarea campurilor care indica randul jucatorului

----------------------------------------NewRound----------------------------------------------
    Clasa NewRound prin constructor seteaza numarul rundei in campul fiecarui jucator, 
daca deckul jucatorilor mai contin carti se extrage o carte, se incrementeaza mana,
se reseteaza campurile frozen (adica se seteaza ca fiind false), pentru fiecare carte a 
jucatorilor, iar daca unfrozenRound este egal cu numarul rundei se reseteaza campul 
frozenforround, si se reseteaza campurile frozen ale eroilor.

---------------------------------------PlaceCard----------------------------------------------
    Clasa PlaceCard prin constructor verifica daca, cartea de plasat este The Ripper, Miraj, 
Goliath, Warden daca da acestea se plaseaza pe primul rand al jucatorului curent, iar daca nu se
plaseaza pe randul din spate, apoi se adauga pe masa randurile jucatorilor, se scade mana
utilizata pentru folosirea cartii din mata totala a jucatorului si se scoate carte din mana
jucatorului.