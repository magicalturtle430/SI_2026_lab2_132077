import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SI2026Lab2Test {

    // --- Барање 5: Every Statement за searchBookByTitle ---
    @Test
    public void searchBookEveryStatementTest() {
        Library library = new Library();
        
        // Тест случај 1: Празен наслов (Исклучок)
        assertThrows(IllegalArgumentException.class, () -> {
            library.searchBookByTitle("");
        });

        // Подготовка на книги за останатите тестови
        Book book1 = new Book("Clean Code", "Robert C. Martin", "Programming");
        Book book2 = new Book("Clean Code", "Robert C. Martin", "Programming");
        book2.setBorrowed(true); // Книгата е изнајмена
        library.addBook(book1);
        library.addBook(book2);

        // Тест случај 2: Книгата постои и е слободна
        List<Book> found = library.searchBookByTitle("Clean Code");
        assertNotNull(found);
        assertEquals(1, found.size());
        assertFalse(found.getOriginal(0).isBorrowed());

        // Тест случај 3: Книгата не е пронајдена во резултатите (results.isEmpty)
        assertNull(library.searchBookByTitle("The Hobbit"));
    }

    // --- Барање 7: Every Branch за borrowBook ---
    @Test
    public void borrowBookEveryBranchTest() {
        Library library = new Library();

        // Тест случај 1: Невалиден влез (Празен наслов/автор)
        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "Author");
        });

        // Подготовка на книги
        Book b1 = new Book("1984", "George Orwell", "Dystopian");
        Book b2 = new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy");
        b2.setBorrowed(true);
        library.addBook(b1);
        library.addBook(b2);

        // Тест случај 2: Книгата успешно се изнајмува
        library.borrowBook("1984", "George Orwell");
        assertTrue(b1.isBorrowed());

        // Тест случај 3: Книгата е веќе изнајмена
        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("The Hobbit", "J.R.R. Tolkien");
        });

        // Тест случај 4: Книгата воопшто не постои во листата
        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("Clean Code", "Robert C. Martin");
        });
    }

    // --- Барање 9: Multiple Condition за двете функции ---
    @Test
    public void searchBookMultipleConditionTest() {
        // Услов: if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed())
        // Потребни се комбинации: TT, TF, FX (поради short-circuit, ако првиот е F, вториот не се евалуира)
        
        Library library = new Library();
        Book b1 = new Book("Java", "Author", "Tech"); // Слободна
        Book b2 = new Book("Java", "Author", "Tech"); 
        b2.setBorrowed(true);                         // Изнајмена
        library.addBook(b1);
        library.addBook(b2);

        // 1. Комбинација TT (Насловот се совпаѓа И НЕ е изнајмена)
        List<Book> resTT = library.searchBookByTitle("Java");
        assertEquals(1, resTT.size());

        // 2. Комбинација TF (Насловот се совпаѓа И е изнајмена) -> резултира со лажен израз
        // Ова сценарио се извршува за b2 при итерацијата

        // 3. Комбинација FX (Насловот НЕ се совпаѓа)
        assertNull(library.searchBookByTitle("C++"));
    }

    @Test
    public void borrowBookMultipleConditionTest() {
        // Услов: if (title.isEmpty() || author.isEmpty())
        // Потребни комбинации: TT, TF, FT, FF (бидејќи е ||, short-circuit го прекинува извршувањето на TT и TF веднаш по првиот T)
        
        Library library = new Library();

        // 1. Прв услов Точен (ТX): title е празен -> се фрла исклучок веднаш (ги покрива TT/TF поради short-circuit кај `||`)
        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "Author");
        });

        // 2. Втор услов Точен (FT): title не е празен, но author е празен
        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("Title", "");
        });

        // 3. Двата услови се Лажни (FF): Точен формат на влезни податоци
        // Фрла RuntimeException бидејќи книгата ја нема, но успешно ја поминува if проверката
        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("Valid Title", "Valid Author");
        });
    }
}
