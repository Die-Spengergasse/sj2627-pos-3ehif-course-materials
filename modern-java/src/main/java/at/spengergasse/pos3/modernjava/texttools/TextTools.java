package at.spengergasse.pos3.modernjava.texttools;

import java.util.List;

/// # Text blocks and String APIs: TextTools
///
/// Implement the small helper class `TextTools` with the modern String features from the unit:
///
/// 1. **`countContentLines(String text)`**: the number of lines that are not blank. Use `lines()` and `isBlank()`. (`lines()` returns a stream: `filter(...)` keeps matching elements, `count()` counts them and returns a `long`, so cast it: `(int) ...count()`.)
/// 2. **`underline(String title)`**: the stripped title, a line break `\n`, and as many `=` characters as the stripped title has characters. Use `strip()` and `repeat(...)`. Example: `"  Hello "` becomes `"Hello\n====="`.
/// 3. **`cleanedLines(String text)`**: the stripped lines of the text without the empty ones, as an **immutable** `List<String>`. Use `lines()`, `strip()` and `toList()`.
/// 4. **`section(String title)`**: this exact HTML as a **text block** with the title inserted via `formatted(...)`. It ends with a line break and the `<h1>` line is indented by four spaces:
///
/// ```
/// <section>
///     <h1>TITLE</h1>
/// </section>
/// ```
///
/// 5. **`countItems(Iterable<?> items)`**: the number of elements, counted with a `for` loop. You do not need the elements, so use an unnamed variable `_` as the loop variable.
public class TextTools {

    public int countContentLines(String text) {
        throw new UnsupportedOperationException("TODO");
    }

    public String underline(String title) {
        throw new UnsupportedOperationException("TODO");
    }

    public List<String> cleanedLines(String text) {
        throw new UnsupportedOperationException("TODO");
    }

    public String section(String title) {
        throw new UnsupportedOperationException("TODO");
    }

    public int countItems(Iterable<?> items) {
        throw new UnsupportedOperationException("TODO");
    }
}
