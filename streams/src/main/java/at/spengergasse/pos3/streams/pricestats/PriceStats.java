package at.spengergasse.pos3.streams.pricestats;

import java.io.PrintStream;
import java.util.List;

/// # Loops to streams: PriceStats
///
/// `PriceStats` works with prices in cents (`List<Integer>`). Implement the five methods as **stream pipelines**, without a `for` loop:
///
/// 1. **`atLeast(List<Integer> cents, int minimum)`**: the prices that are greater than or equal to `minimum`, in the original order. Use `filter` and `toList()`.
/// 2. **`asText(List<Integer> cents)`**: every price as text with euros and cents, e.g. `250` becomes `"2.50 EUR"` and `5` becomes `"0.05 EUR"`. Use `map` and `"%d.%02d EUR".formatted(euros, rest)`. The prices are never negative here.
/// 3. **`countBelow(List<Integer> cents, int limit)`**: how many prices are smaller than `limit`. `count()` returns a `long`.
/// 4. **`totalOfPositive(List<Integer> cents)`**: the sum of all prices greater than 0 (negative values are refunds and are ignored). Use `mapToInt(...)` and `sum()`; the sum of an empty stream is 0.
/// 5. **`printAll(List<Integer> cents, PrintStream out)`**: print every price on its own line with `out.println(...)`. Use `forEach`.
///
/// Example: `atLeast(List.of(120, 80, 300), 100)` is `[120, 300]`.
public class PriceStats {

    public static List<Integer> atLeast(List<Integer> cents, int minimum) {
        throw new UnsupportedOperationException("TODO");
    }

    public static List<String> asText(List<Integer> cents) {
        throw new UnsupportedOperationException("TODO");
    }

    public static long countBelow(List<Integer> cents, int limit) {
        throw new UnsupportedOperationException("TODO");
    }

    public static int totalOfPositive(List<Integer> cents) {
        throw new UnsupportedOperationException("TODO");
    }

    public static void printAll(List<Integer> cents, PrintStream out) {
        throw new UnsupportedOperationException("TODO");
    }
}
