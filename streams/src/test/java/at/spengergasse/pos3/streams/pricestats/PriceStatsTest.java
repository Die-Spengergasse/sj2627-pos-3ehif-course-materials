package at.spengergasse.pos3.streams.pricestats;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PriceStatsTest {

    @Test
    void atLeastKeepsMatchingPricesInOrder() {
        assertThat(PriceStats.atLeast(List.of(120, 80, 300, 100), 100)).containsExactly(120, 300, 100);
    }

    @Test
    void atLeastOfNothingMatchingIsEmpty() {
        assertThat(PriceStats.atLeast(List.of(1, 2), 100)).isEmpty();
        assertThat(PriceStats.atLeast(List.of(), 100)).isEmpty();
    }

    @Test
    void asTextFormatsEurosAndCents() {
        assertThat(PriceStats.asText(List.of(250, 5, 1999, 0)))
                .containsExactly("2.50 EUR", "0.05 EUR", "19.99 EUR", "0.00 EUR");
    }

    @Test
    void asTextOfEmptyListIsEmpty() {
        assertThat(PriceStats.asText(List.of())).isEmpty();
    }

    @Test
    void countBelowCountsOnlySmallerPrices() {
        assertThat(PriceStats.countBelow(List.of(50, 100, 150, 20), 100)).isEqualTo(2L);
    }

    @Test
    void countBelowOfEmptyListIsZero() {
        assertThat(PriceStats.countBelow(List.of(), 100)).isZero();
    }

    @Test
    void totalOfPositiveIgnoresRefundsAndZero() {
        assertThat(PriceStats.totalOfPositive(List.of(100, -30, 0, 250, -5))).isEqualTo(350);
    }

    @Test
    void totalOfPositiveOfEmptyListIsZero() {
        assertThat(PriceStats.totalOfPositive(List.of())).isZero();
        assertThat(PriceStats.totalOfPositive(List.of(-1, -2))).isZero();
    }

    @Test
    void printAllWritesOneLinePerPrice() {
        var buffer = new ByteArrayOutputStream();
        var out = new PrintStream(buffer, true, StandardCharsets.UTF_8);

        PriceStats.printAll(List.of(10, 20, 30), out);

        var nl = System.lineSeparator();
        assertThat(buffer.toString(StandardCharsets.UTF_8)).isEqualTo("10" + nl + "20" + nl + "30" + nl);
    }

    @Test
    void printAllOfEmptyListPrintsNothing() {
        var buffer = new ByteArrayOutputStream();
        PriceStats.printAll(List.of(), new PrintStream(buffer, true, StandardCharsets.UTF_8));
        assertThat(buffer.size()).isZero();
    }
}
