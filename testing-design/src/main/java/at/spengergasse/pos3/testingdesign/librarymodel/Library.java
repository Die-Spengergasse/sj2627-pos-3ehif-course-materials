package at.spengergasse.pos3.testingdesign.librarymodel;

import java.util.List;
import java.util.Optional;

/// # UML to Java: Library
///
/// Turn this UML class diagram into Java. The diagram, as a list:
///
/// **Types**
///
/// - `interface Borrowable`: `+ isBorrowed(): boolean`, `+ borrow()`, `+ giveBack()` (already given)
/// - `class Book`: `- title: String`, `- isbn: String`, `- borrowed: boolean`, and the operations of `Borrowable`
/// - `abstract class Member`: `- name: String`, `+ name(): String`, `+ {abstract} maxLoans(): int`, `+ loans(): List<Book>`, `+ borrow(book: Book)`, `+ giveBack(book: Book)`
/// - `class Student`: a `Member` with `maxLoans() = 3`
/// - `class Teacher`: a `Member` with `maxLoans() = 10`
/// - `class Library`: `- name: String`, `+ name(): String`, `+ addBook(title: String, isbn: String): Book`, `+ books(): List<Book>`, `+ findByIsbn(isbn: String): Optional<Book>`, `+ join(member: Member)`, `+ members(): List<Member>`
///
/// **Relationships**
///
/// - `Borrowable <|.. Book`: `Book` realizes `Borrowable`.
/// - `Member <|-- Student` and `Member <|-- Teacher`: generalization.
/// - `Library "1" *-- "*" Book`: **composition**. The library creates its books in `addBook` (nobody passes a `Book` in) and `books()` returns a list that cannot be modified from outside.
/// - `Library "1" o-- "*" Member`: **aggregation**. Members exist on their own and are handed in with `join`. `members()` also returns an unmodifiable list.
/// - `Member "1" --> "*" Book`: **directed association**. A `Member` keeps its borrowed `loans()`, but a `Book` has **no** field of type `Member` or `Library`.
///
/// **Behaviour**
///
/// - `borrow()` on a `Book` that is already borrowed, or `giveBack()` on one that is not, throws `IllegalStateException`.
/// - `Member.borrow(book)` borrows the book and adds it to the loans. If the member already has `maxLoans()` loans, it throws `IllegalStateException` and does not touch the book. `Member.giveBack(book)` of a book that is not in the loans throws `IllegalStateException`.
/// - All fields are `private`. Names must not be null or blank (`IllegalArgumentException`).
/// - `findByIsbn` returns an empty `Optional` for an unknown ISBN.
///
/// The starter code already has the class hierarchy (`extends`, `implements`), but every method throws `UnsupportedOperationException`. Add the fields and the behaviour yourself, and make `Member` abstract as the diagram says.
public class Library {

    public Library(String name) {
        throw new UnsupportedOperationException("TODO");
    }

    public String name() {
        throw new UnsupportedOperationException("TODO");
    }

    public Book addBook(String title, String isbn) {
        throw new UnsupportedOperationException("TODO");
    }

    public List<Book> books() {
        throw new UnsupportedOperationException("TODO");
    }

    public Optional<Book> findByIsbn(String isbn) {
        throw new UnsupportedOperationException("TODO");
    }

    public void join(Member member) {
        throw new UnsupportedOperationException("TODO");
    }

    public List<Member> members() {
        throw new UnsupportedOperationException("TODO");
    }
}
