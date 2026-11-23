package at.spengergasse.pos3.modernjava.shapearea;

/// # Pattern matching: AreaCalculator
///
/// `Circle` and `Rectangle` are records (already given). Implement
/// **`area(Object shape)`** with a **switch expression with pattern matching**:
///
/// - a `Circle` → `π · radius²` (use `Math.PI`)
/// - a `Rectangle` → `width · height`
/// - `null` → throw an `IllegalArgumentException`
/// - anything else → throw an `IllegalArgumentException` whose message contains the object
///   (e.g. `"Unknown shape: hello"`)
///
/// Bonus: use **record patterns**, e.g. `case Rectangle(double width, double height) -> …`, so you
/// don't need the accessors.
public class AreaCalculator {

    public double area(Object shape) {
        throw new UnsupportedOperationException("TODO");
    }
}
