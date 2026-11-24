package at.spengergasse.pos3.testingdesign.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    /** Arrange helper for the total tests: 600 + 500 + 400 = 1500 cents, 7 pieces. */
    private void fillCart() {
        cart.add("Tea", 300, 2);
        cart.add("Coffee", 500, 1);
        cart.add("Milk", 100, 4);
    }

    @Test
    void newCartIsEmpty() {
        assertThat(cart.isEmpty()).isTrue();
        assertThat(cart.itemCount()).isZero();
        assertThat(cart.totalCents()).isZero();
        assertThat(cart.items()).isEmpty();
    }

    // ---- add ----

    @Test
    void addedItemAppearsInItems() {
        cart.add("Tea", 300, 2);

        assertThat(cart.isEmpty()).isFalse();
        assertThat(cart.items()).containsExactly(new CartItem("Tea", 300, 2));
    }

    @Test
    void itemsKeepInsertionOrder() {
        cart.add("Tea", 300, 1);
        cart.add("Coffee", 500, 1);
        cart.add("Milk", 120, 1);

        assertThat(cart.items()).extracting(CartItem::name)
                .containsExactly("Tea", "Coffee", "Milk");
    }

    @Test
    @DisplayName("adding the same name increases the quantity and keeps the first price")
    void addingSameNameIncreasesQuantityAndKeepsFirstPrice() {
        cart.add("Tea", 300, 1);
        cart.add("Tea", 999, 2);

        assertThat(cart.items()).containsExactly(new CartItem("Tea", 300, 3));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void blankNameIsRejected(String name) {
        assertThatThrownBy(() -> cart.add(name, 100, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void negativePriceIsRejected() {
        assertThatThrownBy(() -> cart.add("Tea", -1, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    void quantityBelowOneIsRejected(int quantity) {
        assertThatThrownBy(() -> cart.add("Tea", 300, quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void priceZeroIsAllowed() {
        cart.add("Flyer", 0, 1);

        assertThat(cart.items()).hasSize(1);
    }

    @Test
    void itemsListCannotBeModified() {
        cart.add("Tea", 300, 1);

        assertThatThrownBy(() -> cart.items().clear())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(cart.items()).hasSize(1);
    }

    // ---- remove ----

    @Test
    void removeDeletesTheWholeItem() {
        cart.add("Tea", 300, 3);
        cart.add("Coffee", 500, 1);

        cart.remove("Tea");

        assertThat(cart.items()).extracting(CartItem::name).containsExactly("Coffee");
        assertThat(cart.itemCount()).isEqualTo(1);
    }

    @Test
    void removingUnknownItemFailsWithItsName() {
        cart.add("Tea", 300, 1);

        assertThatThrownBy(() -> cart.remove("Cake"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Cake");
    }

    // ---- totals ----

    @Test
    void itemCountSumsAllQuantities() {
        fillCart();

        assertThat(cart.itemCount()).isEqualTo(7);
    }

    @Test
    void totalIsSumOfPriceTimesQuantity() {
        fillCart();

        assertThat(cart.totalCents()).isEqualTo(1500);
    }

    @ParameterizedTest(name = "{0}% discount gives {1} cents")
    @CsvSource({
            "0, 1500",
            "10, 1350",
            "15, 1275",
            "50, 750",
            "100, 0"
    })
    void discountIsSubtractedFromTotal(int percent, int expected) {
        fillCart();

        assertThat(cart.totalWithDiscountCents(percent)).isEqualTo(expected);
    }

    @Test
    void discountIsRoundedDown() {
        cart.add("Gum", 999, 1);

        // discount = 999 * 10 / 100 = 99 (integer division)
        assertThat(cart.totalWithDiscountCents(10)).isEqualTo(900);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 101, 1000})
    void discountOutsideZeroToHundredIsRejected(int percent) {
        fillCart();

        assertThatThrownBy(() -> cart.totalWithDiscountCents(percent))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
