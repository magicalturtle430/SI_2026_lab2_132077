# SI_2026_lab2_132077
### 132077 Марко Палоски - Лаб 2

---

## 1. Control Flow Graph (CFG)

### CFG за searchBookByTitle

![Control Flow Graph за searchBookByTitle](222.jpg)

### CFG за borrowBook

![Control Flow Graph за borrowBook](111.jpg)

---

## 2. Цикломатска комплексност

Цикломатската комплексност $V(G)$ за функциите е пресметана со помош на методот на предикатни јазли (одлуки) по формулата $V(G) = P + 1$.

* **searchBookByTitle:** Идентификувани се 5 предикатни јазли (вклучувајќи ја декомпозицијата на `&&` операторот), што дава комплексност $V(G) = 5 + 1 = 6$.
* **borrowBook:** Поради сложените логички услови во `if` наредбите (`||` и `&&`), вкупниот број на предикати изнесува 6, што резултира со комплексност $V(G) = 6 + 1 = 7$.

---

## 3. Тест случаи според критериумот Every statement за searchBookByTitle

| | test 1 | test 2 | test 3 |
|---|:---:|:---:|:---:|
| **line 1** (if title.isEmpty) | * | * | * |
| **line 2** (throw IllegalArgumentException) | * | | |
| **line 3** (List Book results = new ArrayList) | | * | * |
| **line 4** (for Book book : books) | | * | * |
| **line 5** (if titleMatch && !isBorrowed) | | * | * |
| **line 6** (results.add book) | | * | |
| **line 7** (if results.isEmpty) | | * | * |
| **line 8** (return null) | | | * |
| **line 9** (return results) | | * | |

Минималниот број на тест случаи за оваа функција според Every statement критериумот е **3**.

---

## 4. Тест случаи според критериумот Every branch за borrowBook

| | test 1 | test 2 | test 3 | test 4 |
|---|:---:|:---:|:---:|:---:|
| **branch 1** (излез True од првиот if - Exception) | * | | | |
| **branch 2** (излез False од првиот if) | | * | * | * |
| **branch 3** (излез True од for - има елементи) | | * | * | * |
| **branch 4** (излез False од for - завршил циклусот) | | | | * |
| **branch 5** (излез True од if за совпаѓање) | | * | * | |
| **branch 6** (излез False од if за совпаѓање) | | | | * |
| **branch 7** (излез True од if !isBorrowed) | | * | | |
| **branch 8** (излез False од if !isBorrowed - else) | | | * | |
| **branch 9** (надворешна линија 10 - Book not found) | | | | * |

Минималниот број на тест случаи за оваа функција според Every branch критериумот е **4**.

---

## 5. Тест случаи според критериумот Multiple condition

### 5.1. За условот во `searchBookByTitle`: `if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed())`

| комбинација | подуслов 1 (Title Match) | подуслов 2 (!isBorrowed) | test |
|:---:|:---:|:---:|---|
| **TT** | True | True | **test за TT** (наоѓа слободна книга) |
| **TF** | True | False | **test за TF** (книгата со тој наслов е зафатена) |
| **FX** | False | X | **test за FX** (насловот воопшто не се совпаѓа) |

Минималниот број на тест случаи за овој услов според Multiple condition критериумот е **3**.

### 5.2. За условот во `borrowBook`: `if (title.isEmpty() || author.isEmpty())`

| комбинација | подуслов 1 (title.isEmpty) | подуслов 2 (author.isEmpty) | test |
|:---:|:---:|:---:|---|
| **TX** | True | X | **test за TX** (празен наслов) |
| **FT** | False | True | **test за FT** (празен автор) |
| **FF** | False | False | **test за FF** (валидни влезни стрингови) |

Минималниот број на тест случаи за овој услов според Multiple condition критериумот е **3**.
