package at.spengergasse.pos3.streams.operationtable;

import java.util.Map;

/// # Own functional interface: OperationTable
///
/// `IntOperation` is a functional interface with one method, `int apply(int a, int b)`. `OperationTable` keeps a table of such operations by symbol. Implement it:
///
/// 1. **`operations()`**: a `Map<String, IntOperation>` with these entries: `"+"` (sum), `"-"` (difference), `"*"` (product), `"/"` (integer division) and `"max"` (the larger value). Write `"+"` and `"max"` as **method references** (`Integer::sum`, `Math::max`), the others as lambdas. Hint: `Map.of(...)` creates the map.
/// 2. **`apply(String symbol, int a, int b)`**: looks the symbol up in the table and applies the operation. An unknown symbol throws an `IllegalArgumentException` whose message contains the symbol. (`"/"` with 0 as the second operand may simply throw the `ArithmeticException` from Java.)
/// 3. **`flip(IntOperation operation)`**: a new operation that swaps the operands: `flip(minus).apply(10, 3)` is `3 - 10 = -7`. Return a lambda.
public class OperationTable {

    public static Map<String, IntOperation> operations() {
        throw new UnsupportedOperationException("TODO");
    }

    public static int apply(String symbol, int a, int b) {
        throw new UnsupportedOperationException("TODO");
    }

    public static IntOperation flip(IntOperation operation) {
        throw new UnsupportedOperationException("TODO");
    }
}
