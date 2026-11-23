package at.spengergasse.pos3.streams.operationtable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OperationTableTest {

    @Test
    void tableContainsExactlyTheFiveSymbols() {
        assertThat(OperationTable.operations()).containsOnlyKeys("+", "-", "*", "/", "max");
    }

    @ParameterizedTest
    @CsvSource({
            "+, 7, 3, 10",
            "-, 7, 3, 4",
            "*, 7, 3, 21",
            "/, 7, 3, 2",
            "max, 7, 3, 7",
            "max, -2, -5, -2"
    })
    void applyUsesTheMatchingOperation(String symbol, int a, int b, int expected) {
        assertThat(OperationTable.apply(symbol, a, b)).isEqualTo(expected);
    }

    @Test
    void tableEntriesAreUsableOperations() {
        assertThat(OperationTable.operations().get("*").apply(6, 7)).isEqualTo(42);
    }

    @Test
    void unknownSymbolIsRejectedWithItsName() {
        assertThatThrownBy(() -> OperationTable.apply("^", 2, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("^");
    }

    @Test
    void divisionByZeroThrowsArithmeticException() {
        assertThatThrownBy(() -> OperationTable.apply("/", 1, 0)).isInstanceOf(ArithmeticException.class);
    }

    @Test
    void flipSwapsTheOperands() {
        IntOperation minus = (a, b) -> a - b;
        assertThat(minus.apply(10, 3)).isEqualTo(7);
        assertThat(OperationTable.flip(minus).apply(10, 3)).isEqualTo(-7);
    }

    @Test
    void flipOfFlipIsTheOriginalBehaviour() {
        IntOperation divide = (a, b) -> a / b;
        assertThat(OperationTable.flip(OperationTable.flip(divide)).apply(9, 3)).isEqualTo(3);
        assertThat(OperationTable.flip(divide).apply(3, 9)).isEqualTo(3);
    }
}
