package at.spengergasse.pos3.streams.orderanalysis;

import java.util.List;
import java.util.Optional;

/// # filter, map, flatMap, reduce: OrderAnalysis
///
/// A shop keeps its orders as `Order` records (`customer`, `items`, `totalCents`). `OrderAnalysis` answers questions about a list of orders. Implement the five methods as **stream pipelines**:
///
/// 1. **`customersWithAtLeast(List<Order> orders, int minCents)`**: the customer names of all orders with `totalCents` greater than or equal to `minCents`, in the original order (a customer with two such orders appears twice). Use `filter`, `map`, `toList()`.
/// 2. **`allItems(List<Order> orders)`**: all items of all orders in one flat list, in order and including duplicates. Use `flatMap`.
/// 3. **`itemCounts(List<Order> orders)`**: the number of items of each order, as `List<Integer>`.
/// 4. **`revenue(List<Order> orders)`**: the sum of all `totalCents`. Use `reduce` with the identity `0` (an empty list gives 0).
/// 5. **`largest(List<Order> orders)`**: the order with the highest `totalCents` as an `Optional<Order>`; empty for an empty list. If several orders have the same highest total, the **first** of them wins. Use `reduce` without an identity.
///
/// Example: for the orders `("Ann", [tea, cake], 450)` and `("Ben", [tea], 200)`, `allItems` is `[tea, cake, tea]` and `revenue` is `650`.
public class OrderAnalysis {

    public static List<String> customersWithAtLeast(List<Order> orders, int minCents) {
        throw new UnsupportedOperationException("TODO");
    }

    public static List<String> allItems(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    public static List<Integer> itemCounts(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    public static int revenue(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Optional<Order> largest(List<Order> orders) {
        throw new UnsupportedOperationException("TODO");
    }
}
