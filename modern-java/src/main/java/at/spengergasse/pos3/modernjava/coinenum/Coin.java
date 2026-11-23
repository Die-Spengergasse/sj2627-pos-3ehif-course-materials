package at.spengergasse.pos3.modernjava.coinenum;

import java.util.List;
import java.util.Optional;

/// # Enum with fields: Coin
///
/// The enum `Coin` lists the euro coins, but it does not know their value yet. Complete it:
///
/// 1. Give each constant its value in cents: `CENT_10(10)`, `CENT_20(20)`, `CENT_50(50)`, `EURO_1(100)`, `EURO_2(200)`. Add a `private final int cents` field and a constructor.
/// 2. Implement **`cents()`**: returns the value in cents.
/// 3. Implement **`isEuroCoin()`**: true if the coin is worth at least one euro.
/// 4. Implement the static method **`total(List<Coin> coins)`**: the sum of all values in cents (an empty list gives 0).
/// 5. Implement the static method **`largestNotAbove(int cents)`**: the most valuable coin whose value is not greater than `cents`, as an `Optional<Coin>`. Loop over `values()`. If even the smallest coin is too big, return `Optional.empty()`.
///
/// Example: `largestNotAbove(75)` is `CENT_50`, `largestNotAbove(5)` is empty.
public enum Coin {
    CENT_10, CENT_20, CENT_50, EURO_1, EURO_2;

    public int cents() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEuroCoin() {
        throw new UnsupportedOperationException("TODO");
    }

    public static int total(List<Coin> coins) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Optional<Coin> largestNotAbove(int cents) {
        throw new UnsupportedOperationException("TODO");
    }
}
