package at.spengergasse.pos3.modernjava.discountenum;

/// # Enum with per-constant behavior: Discount
///
/// A shop knows three kinds of discount. Every kind computes the reduced price differently. Prices are whole cents (`int`).
///
/// | Constant | Reduced price |
/// |---|---|
/// | `NONE` | the price stays the same |
/// | `HALF_PRICE` | half of the price (integer division) |
/// | `FIXED_5` | the price minus 500 cents, but never below 0 |
///
/// 1. Make **`apply(int priceInCents)`** `abstract` in the enum.
/// 2. Give **each constant its own body** that implements `apply`. Do not use a `switch` inside `apply`.
///
/// Example: `Discount.HALF_PRICE.apply(1999)` is 999, and `Discount.FIXED_5.apply(300)` is 0.
public enum Discount {
    NONE, HALF_PRICE, FIXED_5;

    public int apply(int priceInCents) {
        throw new UnsupportedOperationException("TODO");
    }
}
