package at.spengergasse.pos3.modernjava.shapearea;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

class AreaCalculatorTest {

    private final AreaCalculator calculator = new AreaCalculator();

    @Test
    void areaOfACircle() {
        assertThat(calculator.area(new Circle(2.0))).isCloseTo(12.566, within(0.001));
    }

    @Test
    void areaOfARectangle() {
        assertThat(calculator.area(new Rectangle(3.0, 4.5))).isEqualTo(13.5);
    }

    @Test
    void rejectsNull() {
        assertThatThrownBy(() -> calculator.area(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectsUnknownShapesAndNamesThem() {
        assertThatThrownBy(() -> calculator.area("hello"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("hello");
    }
}
