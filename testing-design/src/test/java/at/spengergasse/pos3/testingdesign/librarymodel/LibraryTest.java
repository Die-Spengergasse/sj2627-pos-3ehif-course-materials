package at.spengergasse.pos3.testingdesign.librarymodel;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LibraryTest {

    private final Library library = new Library("City Library");

    // --- generalization and realization

    @Test
    void bookRealizesBorrowable() {
        assertThat(Borrowable.class).isAssignableFrom(Book.class);
    }

    @Test
    void memberIsAbstractWithTwoSubclasses() {
        assertThat(Modifier.isAbstract(Member.class.getModifiers())).isTrue();
        assertThat(Member.class).isAssignableFrom(Student.class);
        assertThat(Member.class).isAssignableFrom(Teacher.class);
    }

    @Test
    void subclassesDefineTheLoanLimit() {
        assertThat(new Student("Anna").maxLoans()).isEqualTo(3);
        assertThat(new Teacher("Mr. Berger").maxLoans()).isEqualTo(10);
    }

    // --- composition: Library creates and owns its books

    @Test
    void libraryCreatesItsBooks() {
        Book book = library.addBook("Effective Java", "111");

        assertThat(book.title()).isEqualTo("Effective Java");
        assertThat(book.isbn()).isEqualTo("111");
        assertThat(book.isBorrowed()).isFalse();
        assertThat(library.books()).containsExactly(book);
    }

    @Test
    void booksListCannotBeModifiedFromOutside() {
        Book book = library.addBook("Effective Java", "111");

        assertThatThrownBy(() -> library.books().add(book)).isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> library.books().clear()).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void findByIsbn() {
        Book book = library.addBook("Effective Java", "111");
        library.addBook("Clean Code", "222");

        assertThat(library.findByIsbn("111")).containsSame(book);
        assertThat(library.findByIsbn("999")).isEmpty();
    }

    // --- aggregation: members exist on their own

    @Test
    void membersAreHandedInAndListIsUnmodifiable() {
        Member anna = new Student("Anna");
        library.join(anna);

        assertThat(library.members()).containsExactly(anna);
        assertThatThrownBy(() -> library.members().add(new Teacher("Mr. Berger")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    // --- directed association: Member knows Book, Book knows nobody

    @Test
    void bookHasNoReferenceToMemberOrLibrary() {
        for (Field field : Book.class.getDeclaredFields()) {
            assertThat(field.getType()).isNotIn(Member.class, Library.class, Student.class, Teacher.class);
        }
    }

    @Test
    void allFieldsArePrivate() {
        for (Class<?> type : new Class<?>[]{Book.class, Member.class, Library.class}) {
            assertThat(Arrays.stream(type.getDeclaredFields()))
                    .as(type.getSimpleName())
                    .allMatch(f -> Modifier.isPrivate(f.getModifiers()));
        }
    }

    // --- behaviour

    @Test
    void borrowingABookTwiceFails() {
        Book book = library.addBook("Effective Java", "111");
        book.borrow();

        assertThat(book.isBorrowed()).isTrue();
        assertThatThrownBy(book::borrow).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void givingBackABookThatIsNotBorrowedFails() {
        Book book = library.addBook("Effective Java", "111");

        assertThatThrownBy(book::giveBack).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void memberBorrowsAndGivesBack() {
        Member anna = new Student("Anna");
        Book book = library.addBook("Effective Java", "111");

        anna.borrow(book);
        assertThat(anna.loans()).containsExactly(book);
        assertThat(book.isBorrowed()).isTrue();

        anna.giveBack(book);
        assertThat(anna.loans()).isEmpty();
        assertThat(book.isBorrowed()).isFalse();
    }

    @Test
    void studentCannotBorrowMoreThanThreeBooks() {
        Member anna = new Student("Anna");
        for (int i = 1; i <= 3; i++) anna.borrow(library.addBook("Book " + i, "isbn" + i));
        Book fourth = library.addBook("Book 4", "isbn4");

        assertThatThrownBy(() -> anna.borrow(fourth)).isInstanceOf(IllegalStateException.class);
        assertThat(fourth.isBorrowed()).isFalse();
        assertThat(anna.loans()).hasSize(3);
    }

    @Test
    void teacherMayBorrowTenBooks() {
        Member teacher = new Teacher("Mr. Berger");
        for (int i = 1; i <= 10; i++) teacher.borrow(library.addBook("Book " + i, "isbn" + i));

        assertThat(teacher.loans()).hasSize(10);
    }

    @Test
    void givingBackSomeoneElsesBookFails() {
        Member anna = new Student("Anna");
        Member ben = new Student("Ben");
        Book book = library.addBook("Effective Java", "111");
        anna.borrow(book);

        assertThatThrownBy(() -> ben.giveBack(book)).isInstanceOf(IllegalStateException.class);
        assertThat(book.isBorrowed()).isTrue();
    }

    @Test
    void blankNamesAreRejected() {
        assertThatThrownBy(() -> new Library(" ")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Student("")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Teacher(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
