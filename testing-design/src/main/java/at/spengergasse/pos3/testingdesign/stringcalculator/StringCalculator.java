package at.spengergasse.pos3.testingdesign.stringcalculator;

/// # TDD: StringCalculator
///
/// `StringCalculator.add(String numbers)` adds the numbers in a text. This is a classic TDD kata, and the point is **how** you work, not only the result. The tests in `StringCalculatorTest` are already written and **ordered from simplest to hardest**. Work through them like this:
///
/// 1. Run the whole test class once. Everything is red (the starter throws `UnsupportedOperationException`).
/// 2. Take the **first** test only. Run just that one (▶ in the gutter next to the method). Read the failure message.
/// 3. Write the **smallest** change that makes it pass. Hard-coding a value is allowed if that is the smallest change.
/// 4. Run it again: green. Then run the whole class to see that nothing else broke, and **refactor** if something is ugly (all tests that passed before must stay green).
/// 5. Move on to the next test. Do not write code for a test you have not looked at yet.
///
/// What the tests ask for, in order:
///
/// 1. An empty string gives `0`.
/// 2. A single number gives that number: `"5"` gives `5`.
/// 3. Two numbers separated by a comma are added: `"1,2"` gives `3`.
/// 4. Any number of numbers: `"1,2,3,4"` gives `10`.
/// 5. A new line is a separator, too: `"1\n2,3"` gives `6`.
/// 6. A custom delimiter can be given in a first line `//<delimiter>\n`: `"//;\n1;2"` gives `3`. The delimiter is one character.
/// 7. Negative numbers throw an `IllegalArgumentException` with the message `negatives not allowed: ` followed by all negatives separated by `", "`, e.g. `negatives not allowed: -1, -3`.
/// 8. Numbers greater than `1000` are ignored: `"2,1001"` gives `2`, but `1000` itself still counts.
///
/// If you implement everything in one go after reading all the tests, you have done the kata, but not the practice. Try the small steps at least once.
public class StringCalculator {

    public static int add(String numbers) {
        throw new UnsupportedOperationException("TODO");
    }
}
