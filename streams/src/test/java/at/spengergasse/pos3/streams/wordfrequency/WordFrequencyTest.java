package at.spengergasse.pos3.streams.wordfrequency;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WordFrequencyTest {

    private final List<String> lines = List.of("Java is fun", "java IS great", "", "  fun  fun ");

    @Test
    void frequenciesIgnoreCase() {
        assertThat(WordFrequency.frequencies(lines))
                .isEqualTo(Map.of("java", 2L, "is", 2L, "fun", 3L, "great", 1L));
    }

    @Test
    void frequenciesIgnoreEmptyPiecesAndBlankLines() {
        assertThat(WordFrequency.frequencies(List.of("", "   ", " a  b "))).isEqualTo(Map.of("a", 1L, "b", 1L));
    }

    @Test
    void frequenciesOfNoLinesAreEmpty() {
        assertThat(WordFrequency.frequencies(List.of())).isEmpty();
    }

    @Test
    void wordsByLengthAreDistinctAndKeepFirstAppearanceOrder() {
        assertThat(WordFrequency.wordsByLength(lines)).isEqualTo(Map.of(
                2, List.of("is"),
                3, List.of("fun"),
                4, List.of("java"),
                5, List.of("great")));
    }

    @Test
    void wordsByLengthGroupsSeveralWordsOfTheSameLength() {
        assertThat(WordFrequency.wordsByLength(List.of("bb aa BB cc"))).isEqualTo(Map.of(2, List.of("bb", "aa", "cc")));
    }

    @Test
    void countShortAndLongCountsOccurrences() {
        // short (<= 3): is, is, fun, fun, fun = 5; long: java, java, great = 3
        assertThat(WordFrequency.countShortAndLong(lines, 3)).isEqualTo(Map.of(true, 5L, false, 3L));
    }

    @Test
    void countShortAndLongHasBothKeysEvenWithoutWords() {
        assertThat(WordFrequency.countShortAndLong(List.of(), 3)).isEqualTo(Map.of(true, 0L, false, 0L));
    }
}
