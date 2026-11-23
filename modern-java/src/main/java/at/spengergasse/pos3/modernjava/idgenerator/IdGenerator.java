package at.spengergasse.pos3.modernjava.idgenerator;

/// # Singleton: IdGenerator
///
/// An application needs **one** generator that hands out consecutive ids (1, 2, 3, ...). Make `IdGenerator` a Singleton:
///
/// 1. Make the constructor **private**.
/// 2. Keep the single instance in a `private static` field and return it from **`getInstance()`**. Create it lazily, the first time it is requested. It must be safe if several threads call `getInstance()` at the same time (for example `synchronized`, or double-checked locking with `volatile`).
/// 3. Implement **`next()`**: returns 1 on the first call, then 2, 3, ... The counter belongs to the single instance. Several threads may call `next()` at once, and no id may be handed out twice. A `java.util.concurrent.atomic.AtomicLong` helps.
///
/// Example: `IdGenerator.getInstance().next()` returns 1; a later `IdGenerator.getInstance().next()` returns 2, because it is the same object.
public class IdGenerator {

    public IdGenerator() {
    }

    public static IdGenerator getInstance() {
        throw new UnsupportedOperationException("TODO");
    }

    public long next() {
        throw new UnsupportedOperationException("TODO");
    }
}
