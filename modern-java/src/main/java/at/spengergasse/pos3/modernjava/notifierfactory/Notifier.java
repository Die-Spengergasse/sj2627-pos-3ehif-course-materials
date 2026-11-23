package at.spengergasse.pos3.modernjava.notifierfactory;

/// # Factory methods: Notifier
///
/// The interface `Notifier` sends messages. The implementations `EmailNotifier`, `SmsNotifier` and `SilentNotifier` are already given (and package-private), so callers should only ever create notifiers through the **static factory methods** of `Notifier`. Implement them:
///
/// 1. **`email(String address)`**: returns an `EmailNotifier`. The address must contain an `@`, otherwise throw an `IllegalArgumentException` (also for `null`).
/// 2. **`sms(String number)`**: returns an `SmsNotifier`. The number must start with `+`, otherwise throw an `IllegalArgumentException` (also for `null`).
/// 3. **`silent()`**: returns the one shared `SilentNotifier.INSTANCE`, never a new object.
/// 4. **`to(String target)`**: chooses the type for the caller: a target with an `@` becomes an email notifier, a target starting with `+` becomes an SMS notifier, anything else throws an `IllegalArgumentException`. Reuse your other factory methods.
///
/// Example: `Notifier.to("anna@school.at").send("Hi")` returns `"EMAIL to anna@school.at: Hi"`.
public interface Notifier {

    String send(String message);

    static Notifier email(String address) {
        throw new UnsupportedOperationException("TODO");
    }

    static Notifier sms(String number) {
        throw new UnsupportedOperationException("TODO");
    }

    static Notifier silent() {
        throw new UnsupportedOperationException("TODO");
    }

    static Notifier to(String target) {
        throw new UnsupportedOperationException("TODO");
    }
}
