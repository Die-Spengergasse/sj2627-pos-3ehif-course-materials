package at.spengergasse.pos3.modernjava.discountenum;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountTest {

    @Test
    void noneKeepsThePrice() {
        assertThat(Discount.NONE.apply(1999)).isEqualTo(1999);
        assertThat(Discount.NONE.apply(0)).isZero();
    }

    @Test
    void halfPriceHalvesWithIntegerDivision() {
        assertThat(Discount.HALF_PRICE.apply(2000)).isEqualTo(1000);
        assertThat(Discount.HALF_PRICE.apply(1999)).isEqualTo(999);
    }

    @Test
    void fixedFiveSubtractsFiveEuros() {
        assertThat(Discount.FIXED_5.apply(1200)).isEqualTo(700);
    }

    @Test
    void fixedFiveNeverGoesBelowZero() {
        assertThat(Discount.FIXED_5.apply(300)).isZero();
        assertThat(Discount.FIXED_5.apply(500)).isZero();
    }

    @Test
    void applyIsAbstractAndEveryConstantOverridesIt() throws NoSuchMethodException {
        assertThat(Modifier.isAbstract(Discount.class.getDeclaredMethod("apply", int.class).getModifiers())).isTrue();
        for (var discount : Discount.values()) {
            assertThat(discount.getClass().getDeclaredMethods())
                    .as("%s has its own apply", discount)
                    .anyMatch(m -> m.getName().equals("apply"));
        }
    }
}
