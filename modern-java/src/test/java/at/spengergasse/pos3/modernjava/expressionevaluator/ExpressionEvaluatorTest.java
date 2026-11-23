package at.spengergasse.pos3.modernjava.expressionevaluator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExpressionEvaluatorTest {

    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    // 1 + (2 * 3)
    private final Expr sample = new Add(new Num(1), new Mul(new Num(2), new Num(3)));

    @Test
    void aNumberEvaluatesToItself() {
        assertThat(evaluator.evaluate(new Num(42))).isEqualTo(42);
        assertThat(evaluator.evaluate(new Num(-5))).isEqualTo(-5);
    }

    @Test
    void evaluatesAddAndMul() {
        assertThat(evaluator.evaluate(new Add(new Num(2), new Num(3)))).isEqualTo(5);
        assertThat(evaluator.evaluate(new Mul(new Num(4), new Num(5)))).isEqualTo(20);
    }

    @Test
    void evaluatesNestedExpressions() {
        assertThat(evaluator.evaluate(sample)).isEqualTo(7);
        assertThat(evaluator.evaluate(new Mul(new Add(new Num(1), new Num(2)), new Add(new Num(3), new Num(4)))))
                .isEqualTo(21);
    }

    @Test
    void showsANumberAsIs() {
        assertThat(evaluator.show(new Num(7))).isEqualTo("7");
        assertThat(evaluator.show(new Num(-2))).isEqualTo("-2");
    }

    @Test
    void showsOperationsInBrackets() {
        assertThat(evaluator.show(new Add(new Num(1), new Num(2)))).isEqualTo("(1 + 2)");
        assertThat(evaluator.show(sample)).isEqualTo("(1 + (2 * 3))");
    }

    @Test
    void countsOperations() {
        assertThat(evaluator.countOperations(new Num(1))).isZero();
        assertThat(evaluator.countOperations(sample)).isEqualTo(2);
        assertThat(evaluator.countOperations(new Mul(sample, sample))).isEqualTo(5);
    }
}
