package at.spengergasse.pos3.modernjava.temperaturerange;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TemperatureRangeTest {

    @Test
    void rejectsMinGreaterThanMax() {
        assertThatThrownBy(() -> new TemperatureRange(5.0, 4.9))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void allowsMinEqualToMax() {
        var range = new TemperatureRange(3.0, 3.0);

        assertThat(range.width()).isZero();
    }

    @Test
    void widthIsMaxMinusMin() {
        var range = new TemperatureRange(-2.5, 7.0);

        assertThat(range.width()).isEqualTo(9.5);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2.5, 0.0, 7.0})
    void containsTemperaturesInsideIncludingTheLimits(double temperature) {
        var range = new TemperatureRange(-2.5, 7.0);

        assertThat(range.contains(temperature)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2.6, 7.1})
    void doesNotContainTemperaturesOutside(double temperature) {
        var range = new TemperatureRange(-2.5, 7.0);

        assertThat(range.contains(temperature)).isFalse();
    }

    @Test
    void comparesByValue() {
        assertThat(new TemperatureRange(1.0, 2.0)).isEqualTo(new TemperatureRange(1.0, 2.0));
    }
}
