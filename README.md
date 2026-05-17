# SI_2026_lab2_132077
132077 Марко Палоски - Лаб 2

CFG за searchBookByTitle
graph TD
    1[1: if title.isEmpty] -->|True| 2[2: throw IllegalArgumentException]
    1 -->|False| 3[3: List Book results = new ArrayList]
    3 --> 4b{4b: i < books.size}
    4b -->|True| 5{5: if titleMatch && !isBorrowed}
    4b -->|False| 7{7: if results.isEmpty}
    5 -->|True| 6[6: results.add book]
    5 -->|False| 4c[4c: i++]
    6 --> 4c
    4c --> 4b
    7 -->|True| 8[8: return null]
    7 -->|False| 9[9: return results]

  CFG за borrowBook
  graph TD
    1[1: if title.isEmpty || author.isEmpty] -->|True| 2[2: throw IllegalArgumentException]
    1 -->|False| 3b{3b: i < books.size}
    3b -->|True| 4{4: if titleMatch && authorMatch}
    3b -->|False| 10[10: throw RuntimeException Book not found]
    4 -->|True| 5{5: if !book.isBorrowed}
    4 -->|False| 3c[3c: i++]
    3c --> 3b
    5 -->|True| 6[6: book.setBorrowed true]
    6 --> 7[7: Print Borrowed successfully]
    7 --> 9[9: return]
    5 -->|False| 8[8: throw RuntimeException Already borrowed]

За третото барање, цикломатската комплексност за функциите searchBookByTitle и borrowBook се пресметува со методот на предикатни јазли по формулата V(G) = P + 1.За функцијата searchBookByTitle се идентификувани 5 предикати, што резултира со цикломатска комплексност V(G) = 5 + 1 = 6.Кај функцијата borrowBook, поради сложените логички услови во if наредбите (|| и &&), вкупниот број на предикати изнесува 6.Со примена на истата формула, крајниот резултат за комплексноста на borrowBook изнесува V(G) = 6 + 1 = 7.
