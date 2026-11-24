package at.spengergasse.pos3.testingdesign.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests are ordered from simplest to hardest. Make them pass ONE AT A TIME.
 */
class StringCalculatorTest {

    // 1
    @Test
    void emptyStringIsZero() {
        assertThat(StringCalculator.add("")).isEqualTo(0);
    }

    // 2
    @Test
    void singleNumberIsItself() {
        assertThat(StringCalculator.add("5")).isEqualTo(5);
    }

    // 3
    @Test
    void twoNumbersAreAdded() {
        assertThat(StringCalculator.add("1,2")).isEqualTo(3);
    }

    // 4
    @Test
    void anyAmountOfNumbersIsAdded() {
        assertThat(StringCalculator.add("1,2,3,4")).isEqualTo(10);
    }

    // 5
    @Test
    void newLineIsAlsoASeparator() {
        assertThat(StringCalculator.add("1\n2,3")).isEqualTo(6);
    }

    // 6
    @Test
    void customDelimiterIsDeclaredInFirstLine() {
        assertThat(StringCalculator.add("//;\n1;2")).isEqualTo(3);
        assertThat(StringCalculator.add("//|\n4|5|6")).isEqualTo(15);
    }

    // 7
    @Test
    void negativeNumbersThrowWithAllNegativesInMessage() {
        assertThatThrownBy(() -> StringCalculator.add("1,-2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("negatives not allowed: -2");
        assertThatThrownBy(() -> StringCalculator.add("-1,2,-3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("negatives not allowed: -1, -3");
    }

    // 8
    @Test
    void numbersGreaterThan1000AreIgnored() {
        assertThat(StringCalculator.add("2,1001")).isEqualTo(2);
        assertThat(StringCalculator.add("2,1000")).isEqualTo(1002);
    }
}
