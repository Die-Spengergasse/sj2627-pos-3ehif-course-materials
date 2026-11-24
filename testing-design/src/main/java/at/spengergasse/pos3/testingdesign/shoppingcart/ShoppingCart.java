package at.spengergasse.pos3.testingdesign.shoppingcart;

import java.util.List;

/// # JUnit & AssertJ: ShoppingCart
///
/// `ShoppingCart` holds `CartItem`s (a record with `name`, `priceCents` and `quantity`, already given). The behaviour is **specified by the tests** in `ShoppingCartTest`: read them first, then implement the class until they are all green. Use the test names as your to-do list.
///
/// In short:
///
/// 1. **`add(String name, int priceCents, int quantity)`**: adds an item. If an item with the same name is already in the cart, only its quantity grows (the price of the first `add` stays). Throws `IllegalArgumentException` for a blank name, a negative price or a quantity below 1.
/// 2. **`remove(String name)`**: removes the item completely. Throws `NoSuchElementException` (message contains the name) if there is no such item.
/// 3. **`items()`**: the items in the order they were first added, as an **unmodifiable** list.
/// 4. **`itemCount()`**: the sum of all quantities. **`isEmpty()`**: `true` if there are no items.
/// 5. **`totalCents()`**: the sum of `priceCents * quantity`.
/// 6. **`totalWithDiscountCents(int percent)`**: the total minus the discount, where the discount is `total * percent / 100` with integer division. `percent` must be between 0 and 100, otherwise `IllegalArgumentException`.
///
/// **Your own tests (not checked automatically):** add one or two tests to `ShoppingCartTest` yourself, for example for a case you think is missing (what happens with a total of 0 and a discount?). Name them so that the name says what is expected, use Arrange-Act-Assert and AssertJ. The automatic check only runs the given tests; your own tests are for you.
public class ShoppingCart {

    public void add(String name, int priceCents, int quantity) {
        throw new UnsupportedOperationException("TODO");
    }

    public void remove(String name) {
        throw new UnsupportedOperationException("TODO");
    }

    public List<CartItem> items() {
        throw new UnsupportedOperationException("TODO");
    }

    public int itemCount() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    public int totalCents() {
        throw new UnsupportedOperationException("TODO");
    }

    public int totalWithDiscountCents(int percent) {
        throw new UnsupportedOperationException("TODO");
    }
}
