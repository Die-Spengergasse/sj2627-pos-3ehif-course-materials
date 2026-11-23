package at.spengergasse.pos3.streams.lambdaworkshop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LambdaWorkshopTest {

    @Test
    void longerThanIsStrict() {
        var test = LambdaWorkshop.longerThan(3);
        assertThat(test.test("abcd")).isTrue();
        assertThat(test.test("abc")).isFalse();
        assertThat(test.test("")).isFalse();
    }

    @Test
    void longerThanRemembersItsOwnLength() {
        var short3 = LambdaWorkshop.longerThan(3);
        var short0 = LambdaWorkshop.longerThan(0);
        assertThat(short0.test("a")).isTrue();
        assertThat(short3.test("a")).isFalse();
    }

    @Test
    void shoutUpperCasesAndAddsAnExclamationMark() {
        assertThat(LambdaWorkshop.shout().apply("hi")).isEqualTo("HI!");
        assertThat(LambdaWorkshop.shout().apply("")).isEqualTo("!");
    }

    @Test
    void parserParsesIntegers() {
        assertThat(LambdaWorkshop.parser().apply("42")).isEqualTo(42);
        assertThat(LambdaWorkshop.parser().apply("-7")).isEqualTo(-7);
    }

    @Test
    void parserRejectsInvalidText() {
        assertThatThrownBy(() -> LambdaWorkshop.parser().apply("x")).isInstanceOf(NumberFormatException.class);
    }

    @Test
    void listFactoryCreatesANewEmptyModifiableListEachTime() {
        var factory = LambdaWorkshop.listFactory();
        List<String> first = factory.get();
        List<String> second = factory.get();

        assertThat(first).isEmpty();
        assertThat(first).isNotSameAs(second);
        first.add("x");
        assertThat(first).containsExactly("x");
        assertThat(second).isEmpty();
    }

    @Test
    void comparatorOrdersByLengthFirst() {
        var words = new ArrayList<>(List.of("pear", "fig", "apple", "kiwi", "date"));
        words.sort(LambdaWorkshop.byLengthThenAlphabet());
        assertThat(words).containsExactly("fig", "date", "kiwi", "pear", "apple");
    }

    @Test
    void comparatorTreatsEqualStringsAsEqual() {
        var comparator = LambdaWorkshop.byLengthThenAlphabet();
        assertThat(comparator.compare("abc", "abc")).isZero();
        assertThat(comparator.compare("b", "aa")).isNegative();
        assertThat(comparator.compare("ab", "b")).isPositive();
    }

    @Test
    void applyTwiceAppliesTheFunctionTwice() {
        assertThat(LambdaWorkshop.applyTwice(n -> n * 3, 2)).isEqualTo(18);
        assertThat(LambdaWorkshop.applyTwice(n -> n + 1, 0)).isEqualTo(2);
    }
}
