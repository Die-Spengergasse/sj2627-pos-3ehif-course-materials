package at.spengergasse.pos3.modernjava.temperaturerange;

/// # Record: TemperatureRange
///
/// A weather app stores the lowest and the highest temperature of a day as a `TemperatureRange`.
/// The record is already declared. Make it safe and useful:
///
/// 1. **Compact constructor:** throw an `IllegalArgumentException` if `min` is greater than `max`.
///    `min == max` is allowed.
/// 2. **`contains(double temperature)`** returns `true` if the temperature lies inside the range,
///    including both limits.
/// 3. **`width()`** returns the difference between `max` and `min`.
///
/// Example: `new TemperatureRange(-2.5, 7.0).width()` is `9.5`.
public record TemperatureRange(double min, double max) {

    public TemperatureRange {
        // TODO: reject a range where min is greater than max
    }

    public boolean contains(double temperature) {
        throw new UnsupportedOperationException("TODO");
    }

    public double width() {
        throw new UnsupportedOperationException("TODO");
    }
}
