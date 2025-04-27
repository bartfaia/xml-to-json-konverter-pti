# XML – JSON Konvertáló

Egy egyszerű, grafikus felületű Java alkalmazás, amely XML és JSON fájlok konvertálását teszi lehetővé egymás között. A program célja, hogy a felhasználó néhány kattintással megnyithasson egy fájlt, átalakítsa a kívánt formátumra, majd elmentse azt. A program oktatási céllal, a THE PTI / Fordítóprogramok tantárgy keretében készült.

##  Funkciók

- XML → JSON konvertálás
- JSON → XML konvertálás
- Formátumellenőrzés megnyitás előtt (hibás fájl figyelmeztetés)
- Fájlmentés natív Windows fájlkezelővel
- Kilépés megerősítése „Kilépés / Mégsem” választással
- Névjegy ablak fejlesztői információkkal

##  Fejlesztési környezet

- Java JDK 21+
- IntelliJ IDEA 2024.3.4.1
- Külső könyvtár: [org.json](https://mvnrepository.com/artifact/org.json/json)

##  Felület

A program fix méretű, három oszlopos elrendezésű, általános felülettel rendelkezik. A vezérlőgombok logikusan, egymás alatt helyezkednek el.

##  Használat

1. Töltsd le vagy klónozd a repository-t
2. Add hozzá a `json-20240303.jar` fájlt a projekt könyvtáradhoz (lib mappába)
3. Fordítsd le és futtasd a `Main.java` fájlt
4. Kattints a kívánt konvertálási irányra és válassz fájlt

##  Licenc

Ez a projekt kizárólag oktatási célokra készült. Szabadon használható, módosítható, de kereskedelmi célra nem engedélyezett!

---

**Készítette:** Bártfai Attila  - THE PTI @2025
