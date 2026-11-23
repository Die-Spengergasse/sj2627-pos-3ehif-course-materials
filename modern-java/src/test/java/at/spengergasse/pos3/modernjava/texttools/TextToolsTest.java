package at.spengergasse.pos3.modernjava.texttools;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TextToolsTest {

    private final TextTools tools = new TextTools();

    @Test
    void countsOnlyLinesWithContent() {
        assertThat(tools.countContentLines("one\n\n  \ntwo\nthree")).isEqualTo(3);
    }

    @Test
    void countsZeroContentLinesForBlankText() {
        assertThat(tools.countContentLines("")).isZero();
        assertThat(tools.countContentLines("  \n\t\n")).isZero();
    }

    @Test
    void underlineMatchesTheStrippedTitle() {
        assertThat(tools.underline("  Hello ")).isEqualTo("Hello\n=====");
        assertThat(tools.underline("Java")).isEqualTo("Java\n====");
    }

    @Test
    void cleanedLinesAreStrippedAndWithoutEmptyOnes() {
        assertThat(tools.cleanedLines("  a  \n\n b\n   \nc")).containsExactly("a", "b", "c");
    }

    @Test
    void cleanedLinesAreImmutable() {
        var lines = tools.cleanedLines("a\nb");
        assertThatThrownBy(() -> lines.add("c")).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void sectionIsTheExactTextBlock() {
        assertThat(tools.section("Hello")).isEqualTo("<section>\n    <h1>Hello</h1>\n</section>\n");
    }

    @Test
    void countsItemsOfAnyIterable() {
        assertThat(tools.countItems(List.of("a", "b", "c"))).isEqualTo(3);
        assertThat(tools.countItems(Set.of())).isZero();
    }
}
