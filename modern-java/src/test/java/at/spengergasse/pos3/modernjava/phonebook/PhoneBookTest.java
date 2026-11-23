package at.spengergasse.pos3.modernjava.phonebook;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PhoneBookTest {

    private final PhoneBook book = new PhoneBook(Map.of(
            "Anna", "+43 1 234 567",
            "Ben", "+43 660 111 222"));

    @Test
    void findsTheNumberOfAKnownName() {
        assertThat(book.findNumber("Anna")).contains("+43 1 234 567");
    }

    @Test
    void returnsAnEmptyOptionalForAnUnknownName() {
        assertThat(book.findNumber("Zoe")).isEmpty();
    }

    @Test
    void numberOrUnknownReturnsTheNumber() {
        assertThat(book.numberOrUnknown("Ben")).isEqualTo("+43 660 111 222");
    }

    @Test
    void numberOrUnknownFallsBackToUnknown() {
        assertThat(book.numberOrUnknown("Zoe")).isEqualTo("unknown");
    }

    @Test
    void numberOrThrowReturnsTheNumber() {
        assertThat(book.numberOrThrow("Anna")).isEqualTo("+43 1 234 567");
    }

    @Test
    void numberOrThrowNamesTheMissingContact() {
        assertThatThrownBy(() -> book.numberOrThrow("Zoe"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Zoe");
    }
}
