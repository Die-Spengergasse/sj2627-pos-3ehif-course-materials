package at.spengergasse.pos3.modernjava.ticketprice;

/// # Switch expression: TicketPrices
///
/// A cinema sells tickets for four age groups (`AgeGroup`, already given).
/// Implement **`price(AgeGroup group)`** with a **switch expression** (arrow syntax) that returns the
/// price in euros:
///
/// | Age group | Price |
/// |---|---|
/// | `CHILD` | 5 |
/// | `STUDENT`, `SENIOR` | 8 |
/// | `ADULT` | 12 |
///
/// Put `STUDENT` and `SENIOR` into **one** `case`. You don't need a `default`: the switch covers every
/// constant of the enum, so the compiler knows it is complete.
public class TicketPrices {

    public int price(AgeGroup group) {
        throw new UnsupportedOperationException("TODO");
    }
}
