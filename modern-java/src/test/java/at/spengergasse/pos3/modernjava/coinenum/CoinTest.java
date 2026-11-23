package at.spengergasse.pos3.modernjava.coinenum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CoinTest {

    @ParameterizedTest
    @CsvSource({
            "CENT_10, 10",
            "CENT_20, 20",
            "CENT_50, 50",
            "EURO_1, 100",
            "EURO_2, 200"
    })
    void centsPerCoin(Coin coin, int expected) {
        assertThat(coin.cents()).isEqualTo(expected);
    }

    @Test
    void onlyEuroAndTwoEuroAreEuroCoins() {
        assertThat(Coin.EURO_1.isEuroCoin()).isTrue();
        assertThat(Coin.EURO_2.isEuroCoin()).isTrue();
        assertThat(Coin.CENT_50.isEuroCoin()).isFalse();
        assertThat(Coin.CENT_10.isEuroCoin()).isFalse();
    }

    @Test
    void totalAddsUpAllCoins() {
        assertThat(Coin.total(List.of(Coin.EURO_2, Coin.CENT_50, Coin.CENT_50, Coin.CENT_10))).isEqualTo(310);
    }

    @Test
    void totalOfNoCoinsIsZero() {
        assertThat(Coin.total(List.of())).isZero();
    }

    @ParameterizedTest
    @CsvSource({
            "10, CENT_10",
            "75, CENT_50",
            "99, CENT_50",
            "100, EURO_1",
            "5000, EURO_2"
    })
    void largestNotAboveFindsTheBiggestFittingCoin(int cents, Coin expected) {
        assertThat(Coin.largestNotAbove(cents)).contains(expected);
    }

    @Test
    void largestNotAboveIsEmptyIfNoCoinFits() {
        assertThat(Coin.largestNotAbove(9)).isEmpty();
        assertThat(Coin.largestNotAbove(0)).isEmpty();
    }
}
