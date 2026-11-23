package at.spengergasse.pos3.modernjava.expressionevaluator;

/// # Sealed types and record patterns: ExpressionEvaluator
///
/// A tiny calculator represents expressions as a sealed hierarchy: `Expr` is implemented by the records `Num(int value)`, `Add(Expr left, Expr right)` and `Mul(Expr left, Expr right)` (all given). Implement the three methods of `ExpressionEvaluator` with a **switch expression over the sealed type**, using **record patterns**. Do not write a `default` branch: the compiler checks that all three cases are covered.
///
/// 1. **`evaluate(Expr expr)`**: the value of the expression. `Add` adds, `Mul` multiplies, `Num` is its number. The operands are expressions again, so call `evaluate` recursively.
/// 2. **`show(Expr expr)`**: the expression as text. A number is shown as is, an operation is shown in brackets with spaces around the operator.
/// 3. **`countOperations(Expr expr)`**: how many `Add` and `Mul` nodes the expression contains.
///
/// Example for `new Add(new Num(1), new Mul(new Num(2), new Num(3)))`: `evaluate` gives 7, `show` gives `(1 + (2 * 3))`, `countOperations` gives 2.
public class ExpressionEvaluator {

    public int evaluate(Expr expr) {
        throw new UnsupportedOperationException("TODO");
    }

    public String show(Expr expr) {
        throw new UnsupportedOperationException("TODO");
    }

    public int countOperations(Expr expr) {
        throw new UnsupportedOperationException("TODO");
    }
}
