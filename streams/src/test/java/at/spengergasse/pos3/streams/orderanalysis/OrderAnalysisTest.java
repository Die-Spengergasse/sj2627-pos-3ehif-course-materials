package at.spengergasse.pos3.streams.orderanalysis;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderAnalysisTest {

    private final Order ann1 = new Order("Ann", List.of("tea", "cake"), 450);
    private final Order ben = new Order("Ben", List.of("tea"), 200);
    private final Order ann2 = new Order("Ann", List.of(), 0);
    private final Order cleo = new Order("Cleo", List.of("coffee", "tea", "milk"), 450);
    private final List<Order> orders = List.of(ann1, ben, ann2, cleo);

    @Test
    void customersWithAtLeastKeepsOrderAndDuplicates() {
        assertThat(OrderAnalysis.customersWithAtLeast(orders, 200)).containsExactly("Ann", "Ben", "Cleo");
        assertThat(OrderAnalysis.customersWithAtLeast(orders, 0)).containsExactly("Ann", "Ben", "Ann", "Cleo");
    }

    @Test
    void customersWithAtLeastCanBeEmpty() {
        assertThat(OrderAnalysis.customersWithAtLeast(orders, 1000)).isEmpty();
        assertThat(OrderAnalysis.customersWithAtLeast(List.of(), 0)).isEmpty();
    }

    @Test
    void allItemsFlattensInOrderIncludingDuplicates() {
        assertThat(OrderAnalysis.allItems(orders)).containsExactly("tea", "cake", "tea", "coffee", "tea", "milk");
    }

    @Test
    void allItemsOfNothingIsEmpty() {
        assertThat(OrderAnalysis.allItems(List.of())).isEmpty();
        assertThat(OrderAnalysis.allItems(List.of(ann2))).isEmpty();
    }

    @Test
    void itemCountsPerOrder() {
        assertThat(OrderAnalysis.itemCounts(orders)).containsExactly(2, 1, 0, 3);
    }

    @Test
    void revenueSumsAllTotals() {
        assertThat(OrderAnalysis.revenue(orders)).isEqualTo(1100);
    }

    @Test
    void revenueOfNoOrdersIsZero() {
        assertThat(OrderAnalysis.revenue(List.of())).isZero();
    }

    @Test
    void largestIsTheOrderWithTheHighestTotal() {
        assertThat(OrderAnalysis.largest(List.of(ben, ann2, new Order("Dan", List.of("x"), 999))))
                .hasValueSatisfying(o -> assertThat(o.customer()).isEqualTo("Dan"));
    }

    @Test
    void largestPrefersTheFirstOfSeveralEqualTotals() {
        assertThat(OrderAnalysis.largest(orders)).containsSame(ann1);
    }

    @Test
    void largestOfNoOrdersIsEmpty() {
        assertThat(OrderAnalysis.largest(List.of())).isEmpty();
    }
}
