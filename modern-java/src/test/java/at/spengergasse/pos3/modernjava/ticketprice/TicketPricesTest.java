package at.spengergasse.pos3.modernjava.ticketprice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TicketPricesTest {

    private final TicketPrices prices = new TicketPrices();

    @ParameterizedTest
    @CsvSource({
            "CHILD, 5",
            "STUDENT, 8",
            "SENIOR, 8",
            "ADULT, 12"
    })
    void pricePerAgeGroup(AgeGroup group, int expected) {
        assertThat(prices.price(group)).isEqualTo(expected);
    }
}
