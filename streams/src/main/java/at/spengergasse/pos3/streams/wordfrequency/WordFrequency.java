package at.spengergasse.pos3.streams.wordfrequency;

import java.util.List;
import java.util.Map;

/// # flatMap and Collectors: WordFrequency
///
/// `WordFrequency` analyses a text that is given as a list of lines. A **word** is a non-empty piece of a line between whitespace; compare words in **lower case**. Splitting `" a  b"` with `split("\\s+")` produces an empty string at the start, so filter empty strings out. Implement the three methods as one stream pipeline each:
///
/// 1. **`frequencies(List<String> lines)`**: how often each (lower-case) word occurs, as `Map<String, Long>`. Use `flatMap` to get all words, then `groupingBy` with `counting()`.
/// 2. **`wordsByLength(List<String> lines)`**: the **distinct** lower-case words grouped by their length, as `Map<Integer, List<String>>`. Within a list the words are in order of first appearance. Use `distinct()` before `groupingBy`.
/// 3. **`countShortAndLong(List<String> lines, int maxShort)`**: a `Map<Boolean, Long>` that counts all word occurrences (duplicates included): under `true` the words with at most `maxShort` characters, under `false` the longer ones. Use `partitioningBy` with `counting()`.
///
/// Example: for the lines `"Java is fun"` and `"java IS great"`, `frequencies` is `{java=2, is=2, fun=1, great=1}`.
public class WordFrequency {

    public static Map<String, Long> frequencies(List<String> lines) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Map<Integer, List<String>> wordsByLength(List<String> lines) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Map<Boolean, Long> countShortAndLong(List<String> lines, int maxShort) {
        throw new UnsupportedOperationException("TODO");
    }
}
